package zhaoyun.techstack.android.view.nestedscroll.approach1;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

/**
 * @author zhaoyun
 * @version 2020/4/23
 */
@SuppressWarnings("WeakerAccess")
public class ScrollUtils {

    private static final String TAG = "ScrollUtils";

    public static int computeVerticalScrollOffset(View view) {
        try {
            Method method = View.class.getDeclaredMethod("computeVerticalScrollOffset");
            method.setAccessible(true);
            return (int) method.invoke(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return view.getScrollY();
    }

    public static int computeVerticalScrollRange(View view) {
        try {
            Method method = View.class.getDeclaredMethod("computeVerticalScrollRange");
            method.setAccessible(true);
            return (int) method.invoke(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return view.getHeight();
    }

    public static int computeVerticalScrollExtent(View view) {
        try {
            Method method = View.class.getDeclaredMethod("computeVerticalScrollExtent");
            method.setAccessible(true);
            return (int) method.invoke(view);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return view.getHeight();
    }

    public static int getScrollTopOffset(View view) {
        if (canScrollVertically(view, -1)) {
            return -computeVerticalScrollOffset(view);
        } else {
            return 0;
        }
    }

    public static int getScrollBottomOffset(View view) {
        if (canScrollVertically(view, 1)) {
            return computeVerticalScrollRange(view) - computeVerticalScrollOffset(view) - computeVerticalScrollExtent(view);
        } else {
            return 0;
        }
    }

    public static boolean canScrollVertically(View view, int direction) {
        return view.canScrollVertically(direction);
    }
}
