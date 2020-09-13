package zhaoyun.teckstack.android.plugin.hook.library;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author zhaoyun
 * @version 2020/6/30
 */
public class PluginLoader {

    /* ---------- Singleton ---------- */

    private static class SingletonHolder {
        private static final PluginLoader sInstance = new PluginLoader();
    }

    public static PluginLoader getInstance() {
        return SingletonHolder.sInstance;
    }

    private PluginLoader() {
    }

    /* ---------- End of Singleton ---------- */

    public void init() {
        try {
            hookActivityThread();
        } catch (Exception e) {
            Log.e("DEBUG", e.getMessage(), e);
        }
    }

    private void hookActivityThread() throws Exception {
        Object activityThread = Reflector
                .on("android.app.ActivityThread")
                .field("sCurrentActivityThread")
                .noTarget()
                .get();
        Object instrumentation = Reflector
                .on("android.app.ActivityThread")
                .field("mInstrumentation")
                .target(activityThread)
                .get();
        Reflector.on("android.app.ActivityThread")
                 .field("mInstrumentation")
                 .target(activityThread)
                 .set(new InstrumentationProxy((Instrumentation) instrumentation));
    }

    private Resources mResources;

    public Resources getResources() {
        return mResources;
    }

    public void loadPlugin(Context context) {
        try {
            File pluginApk = new File(context.getExternalFilesDir(null), "plugin-debug.apk");
            if (pluginApk.exists()) {
                processClassLoader(context, pluginApk);
                processResources(context, pluginApk);
            }
        } catch (Exception e) {
            Log.e("DEBUG", e.getMessage(), e);
        }
    }

    private void processClassLoader(Context context, File pluginApk) throws Exception {
        PathClassLoader hostClassLoader = (PathClassLoader) context.getClassLoader();
        DexClassLoader pluginClassLoader = new DexClassLoader(
                pluginApk.getAbsolutePath(),
                context.getCacheDir().getAbsolutePath(),
                null,
                context.getClassLoader()
        );

        Object hostPathList = Reflector
                .on("dalvik.system.BaseDexClassLoader")
                .field("pathList")
                .target(hostClassLoader)
                .get();
        Object hostElementArray = Reflector
                .on("dalvik.system.DexPathList")
                .field("dexElements")
                .target(hostPathList)
                .get();

        Object pluginPathList = Reflector
                .on("dalvik.system.BaseDexClassLoader")
                .field("pathList")
                .target(pluginClassLoader)
                .get();
        Object pluginElementArray = Reflector
                .on("dalvik.system.DexPathList")
                .field("dexElements")
                .target(pluginPathList)
                .get();

        int hostSize = Array.getLength(hostElementArray);
        int pluginSize = Array.getLength(pluginElementArray);
        Object newArray = Array.newInstance(Class.forName("dalvik.system.DexPathList$Element"), hostSize + pluginSize);
        for (int i = 0; i < hostSize + pluginSize; i++) {
            if (i < hostSize) {
                Array.set(newArray, i, Array.get(hostElementArray, i));
            } else {
                Array.set(newArray, i, Array.get(pluginElementArray, i - hostSize));
            }
        }

        Reflector.on("dalvik.system.DexPathList")
                 .field("dexElements")
                 .target(hostPathList)
                 .set(newArray);
    }

    private void processResources(Context context, File pluginApk) throws Exception {
        AssetManager assetManager = AssetManager.class.newInstance();
        Reflector.on(AssetManager.class)
                 .method("addAssetPath", String.class)
                 .target(assetManager)
                 .invoke(pluginApk.getAbsolutePath());

        mResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }
}
