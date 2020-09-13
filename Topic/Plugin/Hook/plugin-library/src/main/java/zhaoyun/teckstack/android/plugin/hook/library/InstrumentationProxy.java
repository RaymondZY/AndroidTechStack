package zhaoyun.teckstack.android.plugin.hook.library;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import androidx.annotation.RequiresApi;

/**
 * @author zhaoyun
 * @version 2020/7/1
 */
public class InstrumentationProxy extends Instrumentation {

    private Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
        Log.d("DEBUG", "InstrumentationProxy.execStartActivity()");
        List<ResolveInfo> resolveInfo = who.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);

        if (resolveInfo.size() == 0) {
            if (intent.getComponent() != null) {
                //保存目标插件
                intent.putExtra("original_class", intent.getComponent().getClassName());
                //设置为占坑Activity
                intent.setClassName(who, "zhaoyun.teckstack.android.plugin.hook.library.PluginHostActivity");
            }
        }
        try {
            return (ActivityResult) Reflector
                    .on(Instrumentation.class)
                    .method("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class)
                    .target(mInstrumentation)
                    .invoke(who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            Log.e("DEBUG", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String data = intent.getStringExtra("original_class");
        if (!TextUtils.isEmpty(data)) {
            return super.newActivity(cl, data, intent);
        }
        return super.newActivity(cl, className, intent);
    }
}
