package zhaoyun.techstack.android.touchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author zhaoyun
 * @version 2018/11/1
 */
public class ContainerConstraintLayout extends ConstraintLayout {

    private static final String TAG = "Container";

    public ContainerConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.dispatchTouchEvent(event);
        Log.i(TAG, "dispatchTouchEvent() " + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TAG, "onInterceptTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = getId() == R.id.container4 || super.onInterceptTouchEvent(event);
        Log.i(TAG, "onInterceptTouchEvent() " + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = getId() == R.id.container2 ||
                getId() == R.id.container3 ||
                getId() == R.id.container4 ||
                super.onTouchEvent(event);
        Log.i(TAG, "onTouchEvent() " + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }
}
