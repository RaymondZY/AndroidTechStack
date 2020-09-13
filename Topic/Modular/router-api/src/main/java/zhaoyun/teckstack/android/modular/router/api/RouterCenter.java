package zhaoyun.teckstack.android.modular.router.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import zhaoyun.teckstack.android.modular.router.annotation.RouteBean;
import zhaoyun.teckstack.android.modular.router.annotation.RouteMap;

/**
 * @author zhaoyun
 * @version 2020/6/29
 */
public class RouterCenter {

    private static final String TAG = "RouterCenter";

    /* ---------- Singleton ---------- */

    private static class SingletonHolder {
        private static final RouterCenter sInstance = new RouterCenter();
    }

    public static RouterCenter getInstance() {
        return SingletonHolder.sInstance;
    }

    private RouterCenter() {
    }

    /* ---------- End of Singleton ---------- */

    public void navigate(Context context, String path) {
        if (BuildConfig.IS_MODULAR) {
            Toast.makeText(context, "组件化运行中，忽略跳转请求：" + path + "。", Toast.LENGTH_SHORT).show();
        } else {
            String group = path.substring(1, path.indexOf('/', 1));
            try {
                Class<?> routeMapClass = Class.forName("zhaoyun.teckstack.android.modular.apt.Router$$" + group);
                RouteMap routeMap = (RouteMap) routeMapClass.newInstance();
                RouteBean routeBean = routeMap.getRouteBeanMap().get(path);
                if (routeBean != null) {
                    Class<?> clazz = routeBean.getClazz();
                    context.startActivity(new Intent(context, clazz));
                } else {
                    Log.d(TAG, "RouterCenter.navigate() failed to find RouteBean for " + path);
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
}
