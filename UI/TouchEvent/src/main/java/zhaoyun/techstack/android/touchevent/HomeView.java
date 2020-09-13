package zhaoyun.techstack.android.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/12/15
 */
public class HomeView extends LinearLayout {

    private static final String TAG = "HomeView";

    public HomeView(Context context) {
        super(context);
    }

    public HomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.dispatchTouchEvent(event);
        Log.i(TAG, "dispatchTouchEvent() "  + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TAG, "onInterceptTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.onInterceptTouchEvent(event);
        Log.i(TAG, "onInterceptTouchEvent() "  + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = true;
        Log.i(TAG, "onTouchEvent() "  + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }
}
