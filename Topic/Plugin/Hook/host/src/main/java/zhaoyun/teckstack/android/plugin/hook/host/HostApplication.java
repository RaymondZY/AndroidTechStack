package zhaoyun.teckstack.android.plugin.hook.host;

import android.app.Application;

import zhaoyun.teckstack.android.plugin.hook.library.PluginLoader;

/**
 * @author zhaoyun
 * @version 2020/7/1
 */
public class HostApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PluginLoader.getInstance().init();
    }
}
