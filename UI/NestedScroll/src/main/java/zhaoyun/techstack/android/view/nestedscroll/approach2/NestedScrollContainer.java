package zhaoyun.techstack.android.view.nestedscroll.approach2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zhaoyun.techstack.android.view.nestedscroll.R;
import zhaoyun.techstack.android.view.nestedscroll.approach1.ScrollUtils;

/**
 * @author zhaoyun
 * @version 2020/4/24
 */
@SuppressWarnings("NullableProblems")
public class NestedScrollContainer extends ViewGroup implements NestedScrollingParent2 {

    private static final String TAG = "NestedScrollContainer";

    private int mTotalScroll;
    private int mScrollRange;

    public NestedScrollContainer(Context context) {
        this(context, null);
    }

    public NestedScrollContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setAdapter(new Adapter(getContext()));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setAdapter(new Adapter(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mScrollRange = 0;
        int top = 0;
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int right = left + childView.getMeasuredWidth();
            int bottom = top + childView.getMeasuredHeight();
            childView.layout(left, top, right, bottom);
            top = bottom;
            mScrollRange += childView.getHeight();
        }
        mScrollRange -= getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onStartNestedScroll() child = [" + child + "], target = [" + target + "], axes = [" + axes + "], type = [" + type + "]");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onNestedScrollAccepted() child = [" + child + "], target = [" + target + "], axes = [" + axes + "], type = [" + type + "]");
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        Log.d(TAG, "onStopNestedScroll() target = [" + target + "], type = [" + type + "]");
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d(TAG, "onNestedPreScroll() target = [" + target + "], dx = [" + dx + "], dy = [" + dy + "], consumed = [" + Arrays.toString(consumed) + "], type = [" + type + "]");
        if (dy > 0) {
            scrollDown(dy);
        } else {
            scrollUp(dy);
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onNestedScroll() target = [" + target + "], dxConsumed = [" + dxConsumed + "], dyConsumed = [" + dyConsumed + "], dxUnconsumed = [" + dxUnconsumed + "], dyUnconsumed = [" + dyUnconsumed + "], type = [" + type + "]");
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.d(TAG, "onNestedPreFling() target = [" + target + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling() target = [" + target + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "], consumed = [" + consumed + "]");
        return super.onNestedFling(target, velocityX, velocityY, consumed);
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
}
