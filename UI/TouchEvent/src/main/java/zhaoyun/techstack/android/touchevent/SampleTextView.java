package zhaoyun.techstack.android.touchevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author zhaoyun
 * @version 2018/11/1
 */
public class SampleTextView extends AppCompatTextView {

    private static final String TAG = "TextView";

    public SampleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.dispatchTouchEvent(event);
        Log.i(TAG, "dispatchTouchEvent() " + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() " + MotionEvent.actionToString(event.getAction()));
        boolean result = getId() == R.id.textView3 ||
                getId() == R.id.textView4 ||
                super.onTouchEvent(event);
        Log.i(TAG, "onTouchEvent() " + MotionEvent.actionToString(event.getAction()) + " returned: " + result);
        return result;
    }
}
