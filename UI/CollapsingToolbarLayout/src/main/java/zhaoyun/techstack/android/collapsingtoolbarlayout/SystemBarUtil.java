package zhaoyun.techstack.android.collapsingtoolbarlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author zhaoyun
 * @version 2020/1/20
 */
public class SystemBarUtil {

    public static void setTransparent(Activity activity, boolean setStatusBar, boolean setNavigationBar, boolean fitWindow) {
        if (setStatusBar) {
            setStatusBarTransparent(activity);
        }
        if (setNavigationBar) {
            setNavigationBarTransparent(activity);
        }
        if (fitWindow) {
            setContentViewFitWindow(activity);
        }
    }

    public static void setSystemTranslucent(Activity activity, boolean setStatusBar, boolean setNavigationBar, boolean fitWindow) {
        if (setStatusBar) {
            setStatusBarTranslucent(activity);
        }
        if (setNavigationBar) {
            setNavigationBarTranslucent(activity);
        }
        if (fitWindow) {
            setContentViewFitWindow(activity);
        }
    }

    public static void setCustomTranslucent(Activity activity, boolean setStatusBar, boolean setNavigationBar, boolean fitWindow, int alpha) {
        int color = Color.argb(alpha, 0, 0, 0);
        setColor(activity, setStatusBar, setNavigationBar, fitWindow, color, color);
    }

    public static void setColor(Activity activity, boolean setStatusBar, boolean setNavigationBar, boolean fitWindow, int statusBarColor, int navigationBarColor) {
        if (setStatusBar) {
            setStatusBarColor(activity, statusBarColor);
        }
        if (setNavigationBar) {
            setNavigationBarColor(activity, navigationBarColor);
        }
        if (fitWindow) {
            setContentViewFitWindow(activity);
        }
    }

    public static void setDarkMode(Activity activity, boolean isDarkStatusBar, boolean isDarkNavigationBar) {
        View decorView = activity.getWindow().getDecorView();
        int flags = decorView.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isDarkStatusBar) {
                flags = flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                flags = flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (isDarkNavigationBar) {
                flags = flags | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            } else {
                flags = flags & ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
        }
        decorView.setSystemUiVisibility(flags);
    }

    private static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColor(activity, Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);

            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.setStatusBarContrastEnforced(false);
            }
        }
    }

    private static void setStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private static void setNavigationBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setNavigationBarColor(activity, Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private static void setNavigationBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setNavigationBarColor(color);

            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.setNavigationBarContrastEnforced(false);
            }
        }
    }

    private static void setNavigationBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private static void setContentViewFitWindow(Activity activity) {
        ViewGroup contentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        for (int i = 0; i < contentView.getChildCount(); i++) {
            View child = contentView.getChildAt(i);
            if (child instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) child;
                viewGroup.setFitsSystemWindows(true);
                viewGroup.setClipToPadding(false);
            }
        }
    }
}
