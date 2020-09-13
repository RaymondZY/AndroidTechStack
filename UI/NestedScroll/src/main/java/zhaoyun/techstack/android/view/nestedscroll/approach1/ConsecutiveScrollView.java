package zhaoyun.techstack.android.view.nestedscroll.approach1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/**
 * @author zhaoyun
 * @version 2020/4/23
 */
public class ConsecutiveScrollView extends ViewGroup {

    private static final String TAG = "ConsecutiveScrollView";

    static final Interpolator sQuinticInterpolator = t -> {
        t -= 1.0f;
        return t * t * t * t * t + 1.0f;
    };

    private int mScrollRange;
    private int mTotalScroll;
    private int mInitialY;
    private int mLastY;
    private int mTouchSlop;
    private boolean mDragging;

    private OverScroller mScroller;
    private int mLastScrollY;
    private VelocityTracker mVelocityTracker;
    private int mMinimumFlingVelocity;
    private int mMaximumFlingVelocity;

    public ConsecutiveScrollView(Context context) {
        this(context, null);
    }

    public ConsecutiveScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConsecutiveScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWillNotDraw(false);
        setVerticalScrollBarEnabled(true);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mScroller = new OverScroller(context, sQuinticInterpolator);
        mVelocityTracker = VelocityTracker.obtain();
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);

        child.setVerticalScrollBarEnabled(false);
        child.setHorizontalScrollBarEnabled(false);
        child.setOverScrollMode(OVER_SCROLL_NEVER);
        if (child instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) child;
            viewGroup.setClipToPadding(false);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mScrollRange = 0;
        int top = getPaddingTop();
        int left = getPaddingLeft();

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int right = left + childView.getMeasuredWidth();
            int bottom = top + childView.getMeasuredHeight();
            childView.layout(left, top, right, bottom);
            top = bottom;
            mScrollRange += childView.getHeight();
        }

        mScrollRange -= getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "dispatchTouchEvent() event = [" + event + "]");
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.abortAnimation();
                mDragging = false;
                mInitialY = mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetY = (int) event.getY() - mInitialY;
                if (!mDragging) {
                    if (Math.abs(offsetY) > mTouchSlop) {
                        mDragging = true;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDragging = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptTouchEvent() event = [" + event + "]");
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_MOVE) {
            if (mDragging) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent() event = [" + event + "]");
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) event.getY();
                initOrResetVelocityTracker();
                mVelocityTracker.addMovement(event);
                break;

            case MotionEvent.ACTION_MOVE:
                int y = (int) event.getY();
                int offsetY = y - mLastY;
                mLastY = y;
                scrollBy(0, -offsetY);
                initVelocityTrackerIfNotExist();
                mVelocityTracker.addMovement(event);
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mVelocityTracker != null) {
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
                    int velocityY = (int) mVelocityTracker.getYVelocity();
                    velocityY = Math.max(-mMaximumFlingVelocity, Math.min(velocityY, mMaximumFlingVelocity));
                    recycleVelocityTracker();
                    fling(-velocityY);
                }
                break;
        }
        return true;
    }

    @Override
    public void scrollBy(int x, int y) {
        scrollTo(x, mTotalScroll + y);
    }

    @Override
    public void scrollTo(int x, int y) {
        dispatchScroll(y);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int y = mScroller.getCurrY();
            int offsetY = y - mLastScrollY;
            mLastScrollY = y;
            if (offsetY > 0) {
                if (!hitBottom()) {
                    scrollBy(0, offsetY);
                    invalidate();
                } else {
                    Log.d(TAG, "computeScroll() hitBottom = true, abort scroller animation");
                    mScroller.abortAnimation();
                }
            } else {
                if (!hitTop()) {
                    scrollBy(0, offsetY);
                    invalidate();
                } else {
                    Log.d(TAG, "computeScroll() hitTop = true, abort scroller animation");
                    mScroller.abortAnimation();
                }
            }
        }
    }

    @Override
    protected int computeVerticalScrollRange() {
        int scrollRange = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            scrollRange += ScrollUtils.computeVerticalScrollRange(childView);
        }
        return scrollRange;
    }

    @Override
    protected int computeVerticalScrollOffset() {
        return mTotalScroll;
    }

    @Override
    protected int computeVerticalScrollExtent() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    private void dispatchScroll(int y) {
        int offsetY = y - mTotalScroll;
        if (offsetY > 0) {
            scrollDown(offsetY);
        } else {
            scrollUp(offsetY);
        }
    }

    private void scrollDown(int offsetY) {
        Log.d(TAG, "scrollDown() offsetY = [" + offsetY + "]");
        int remainOffsetY = offsetY;
        while (remainOffsetY > 0 && !hitBottom()) {
            awakenScrollBars();
            View firstView = findFirstVisibleView();
            if (firstView != null) {
                int hitBottomOffset = ScrollUtils.getScrollBottomOffset(firstView);
                int scrollOutOffset = firstView.getBottom() - getPaddingTop() - getScrollY();
                if (hitBottomOffset > 0) {
                    offsetY = Math.min(hitBottomOffset, remainOffsetY);
                    firstView.scrollBy(0, offsetY);
                    Log.d(TAG, "scrollDown() " + firstView + " scrollBy " + offsetY);
                } else {
                    offsetY = Math.min(scrollOutOffset, remainOffsetY);
                    scrollSelfBy(offsetY);
                }
                mTotalScroll += offsetY;
                remainOffsetY -= offsetY;
                Log.d(TAG, "scrollDown() mTotalScroll = " + mTotalScroll);
            }
        }
        if (hitBottom() && remainOffsetY > 0) {
            Log.d(TAG, "scrollDown() hitBottom() = true, remainOffsetY = " + remainOffsetY);
        }
    }

    private void scrollUp(int offsetY) {
        Log.d(TAG, "scrollUp() offsetY = [" + offsetY + "]");
        int remainOffsetY = offsetY;
        while (remainOffsetY < 0 && !hitTop()) {
            awakenScrollBars();
            View lastView = findLastVisibleView();
            if (lastView != null) {
                int hitTopOffset = ScrollUtils.getScrollTopOffset(lastView);
                int scrollOutOffset = lastView.getTop() + getPaddingBottom() - getScrollY() - getHeight();
                if (hitTopOffset < 0) {
                    offsetY = Math.max(hitTopOffset, remainOffsetY);
                    lastView.scrollBy(0, offsetY);
                    Log.d(TAG, "scrollUp() " + lastView + " scrollBy " + offsetY);
                } else {
                    offsetY = Math.max(scrollOutOffset, remainOffsetY);
                    scrollSelfBy(offsetY);
                }
                mTotalScroll += offsetY;
                remainOffsetY -= offsetY;
                Log.d(TAG, "scrollUp() mTotalScroll = " + mTotalScroll);
            }
        }
        if (hitTop() && remainOffsetY < 0) {
            Log.d(TAG, "scrollUp() hitTop() = true, remainOffsetY = " + remainOffsetY);
        }
    }

    private void scrollSelfBy(int offsetY) {
        Log.d(TAG, "scrollSelfBy() offsetY = [" + offsetY + "]");
        int y = getScrollY() + offsetY;
        y = Math.min(y, mScrollRange);
        y = Math.max(y, 0);
        super.scrollTo(0, y);
    }

    public void fling(int velocityY) {
        Log.d(TAG, "fling() velocityY = [" + velocityY + "]");
        if (Math.abs(velocityY) > mMinimumFlingVelocity) {
            mLastScrollY = getScrollY();
            mScroller.fling(0, getScrollY(), 0, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            invalidate();
        }
    }

    private boolean hitBottom() {
        if (getChildCount() > 0) {
            View childView = getChildAt(getChildCount() - 1);
            return getScrollY() == mScrollRange && !childView.canScrollVertically(1);
        }
        return true;
    }

    private boolean hitTop() {
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            return getScrollY() == 0 && !childView.canScrollVertically(-1);
        }
        return true;
    }

    private View findFirstVisibleView() {
        int topPosition = getScrollY() + getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getTop() <= topPosition && topPosition < childView.getBottom()) {
                return childView;
            }
        }
        return null;
    }

    private View findLastVisibleView() {
        int bottomPosition = getHeight() - getPaddingBottom() + getScrollY();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getTop() < bottomPosition && bottomPosition <= childView.getBottom()) {
                return childView;
            }
        }
        return null;
    }

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExist() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
