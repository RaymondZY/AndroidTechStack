package zhaoyun.techstack.android.coordinatorlayout.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * @author zhaoyun
 * @version 2020/5/27
 */
public class HeaderBehavior extends CoordinatorLayout.Behavior {

    private static final Interpolator sQuinticInterpolator = t -> {
        t -= 1.0f;
        return t * t * t * t * t + 1.0f;
    };

    private int[] mLastTouchPosition;
    private int mTouchSlop;
    private boolean mInBound;
    private VelocityTracker mVelocityTracker;
    private FlingRunnable mFlingRunnable;
    private OverScroller mOverScroller;

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        mLastTouchPosition = new int[2];
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mInBound = false;
        mVelocityTracker = VelocityTracker.obtain();
        mOverScroller = new OverScroller(context, sQuinticInterpolator);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull MotionEvent event) {
        Log.d("DEBUG", "HeaderBehavior.onInterceptTouchEvent() parent = [" + parent + "], child = [" + child + "], event = [" + event + "]");

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mVelocityTracker.clear();
                mOverScroller.abortAnimation();
                child.removeCallbacks(mFlingRunnable);
                mInBound = parent.isPointInChildBounds(child, x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mInBound) {
                    int dy = y - mLastTouchPosition[1];
                    if (Math.abs(dy) > mTouchSlop) {
                        return true;
                    }
                }
                break;
        }
        mLastTouchPosition[0] = x;
        mLastTouchPosition[1] = y;
        mVelocityTracker.addMovement(event);
        return false;
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull MotionEvent event) {
        Log.d("DEBUG", "HeaderBehavior.onTouchEvent() parent = [" + parent + "], child = [" + child + "], event = [" + event + "]");

        int y = (int) event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                int dy = mLastTouchPosition[1] - y;
                scroll(child, dy);
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                fling(child, (int) mVelocityTracker.getYVelocity());
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        mLastTouchPosition[1] = y;
        return mInBound;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        consumed[1] = scroll(child, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (needConsumePreScroll(child)) {
            consumed[1] = scroll(child, dy);
        }
    }

    private boolean needConsumePreScroll(View child) {
        int currentPosition = child.getTop();
        int collapsePosition = -child.getHeight();
        return currentPosition > collapsePosition;
    }

    private int scroll(View child, int dy) {
        Log.d("DEBUG", "HeaderBehavior.scroll() child = [" + child + "], dy = [" + dy + "]");

        int currentPosition = child.getTop();
        int collapsePosition = -child.getHeight();
        int expandPosition = 0;

        int dyConsumed;
        if (dy > 0) {
            // collapse
            dyConsumed = Math.min(currentPosition - collapsePosition, dy);
        } else {
            // expand
            dyConsumed = Math.max(currentPosition - expandPosition, dy);
        }
        child.offsetTopAndBottom(-dyConsumed);
        return dyConsumed;
    }

    private void fling(View child, int velocityY) {
        Log.d("DEBUG", "HeaderBehavior.fling() child = [" + child + "], velocityY = [" + velocityY + "]");

        int currentPosition = child.getTop();

        mOverScroller.fling(
                0,
                currentPosition,
                0,
                velocityY,
                0,
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
        mFlingRunnable = new FlingRunnable(child);
        child.removeCallbacks(mFlingRunnable);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
    }

    private class FlingRunnable implements Runnable {

        private View mChild;

        private FlingRunnable(View child) {
            mChild = child;
        }

        @Override
        public void run() {
            if (mOverScroller.computeScrollOffset()) {
                int currentTop = mChild.getTop();
                int newTop = mOverScroller.getCurrY();
                scroll(mChild, currentTop - newTop);
                ViewCompat.postOnAnimation(mChild, mFlingRunnable);
            }
        }
    }
}
