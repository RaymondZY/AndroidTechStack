package zhaoyun.teckstack.android.plugin.stub.library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

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

    private DexClassLoader mDexClassLoader;
    private Resources mResources;

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }

    public String getLauncherActivityClassName(Context context) {
        File pluginApk = new File(context.getExternalFilesDir(null), "plugin-debug.apk");
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(pluginApk.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
        if (packageInfo != null && packageInfo.activities.length > 0) {
            return packageInfo.activities[0].name;
        } else {
            return null;
        }
    }

    public void loadPlugin(Context context) {
        File pluginApk = new File(context.getExternalFilesDir(null), "plugin-debug.apk");
        if (pluginApk.exists()) {
            try {
                mDexClassLoader = new DexClassLoader(
                        pluginApk.getAbsolutePath(),
                        context.getCacheDir().getAbsolutePath(),
                        null,
                        context.getClassLoader()
                );

                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPathMethod.invoke(assetManager, pluginApk.getAbsolutePath());

                mResources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
            } catch (Exception e) {
                Log.e("DEBUG", e.getMessage(), e);
            }
        }
    }
}
