package zhaoyun.techstack.android.tablayout.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author zhaoyun
 * @version 2020/5/22
 */
public class CustomTabLayout extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private static final int SELECT_NONE = -1;
    private static final int INDICATOR_POSITION_NONE = -1;
    private static final int INDICATOR_HEIGHT = 30;
    private static final int INDICATOR_WIDTH = 30;

    private ViewPager mViewPager;
    private LinearLayout mContainer;
    private Path mTrianglePath;
    private List<TextView> mTextViewList;
    private int mSelectedPosition = SELECT_NONE;
    private int mIndicatorPosition = INDICATOR_POSITION_NONE;

    public CustomTabLayout(Context context) {
        this(context, null);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTrianglePath = new Path();
        mTextViewList = new ArrayList<>();
        mContainer = new LinearLayout(getContext());
        addView(mContainer, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mIndicatorPosition != INDICATOR_POSITION_NONE) {
            mTrianglePath.reset();
            mTrianglePath.moveTo(mIndicatorPosition, getHeight() - INDICATOR_HEIGHT);
            mTrianglePath.lineTo(mIndicatorPosition - INDICATOR_WIDTH / 2f, getHeight());
            mTrianglePath.lineTo(mIndicatorPosition + INDICATOR_WIDTH / 2f, getHeight());
            mTrianglePath.close();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                canvas.clipOutPath(mTrianglePath);
            } else {
                canvas.clipRect(0, 0, mContainer.getWidth(), getHeight());
                canvas.clipPath(mTrianglePath, Region.Op.DIFFERENCE);
            }
        }
        super.draw(canvas);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                CharSequence pageTitle = adapter.getPageTitle(i);
                TextView textView;
                if (i >= mTextViewList.size()) {
                    textView = createTextView(i);
                    mTextViewList.add(textView);
                    mContainer.addView(textView, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                } else {
                    textView = mTextViewList.get(i);
                }
                textView.setText(pageTitle);
            }
        }
        viewPager.addOnPageChangeListener(this);
        updateSelectedTab(viewPager.getCurrentItem());
        scrollToItem(viewPager.getCurrentItem(), false, true);
    }

    private TextView createTextView(final int position) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_text_view, mContainer, false);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToItem(position, true, false);
                mViewPager.setCurrentItem(position, true);
            }
        });
        return textView;
    }

    private void scrollToItem(int position, boolean smooth, boolean updateIndicator) {
        scrollToItem(position, 0, smooth, updateIndicator);
    }

    private void scrollToItem(int position, float positionOffset, boolean smooth, boolean updateIndicator) {
        int leftSpace = 0;
        int rightSpace = 0;

        TextView targetItem = mTextViewList.get(position);
        for (int i = 0; i < position; i++) {
            leftSpace += mTextViewList.get(i).getWidth();
        }
        for (int i = position + 1; i < mTextViewList.size(); i++) {
            rightSpace += mTextViewList.get(i).getWidth();
        }

        int totalWidth = leftSpace + rightSpace + targetItem.getWidth();
        int leftMargin = (getWidth() - targetItem.getWidth()) / 2;
        int rightMargin = (getWidth() - targetItem.getWidth()) / 2;
        int offset = 0;

        if (positionOffset > 0) {
            TextView nextItem = mTextViewList.get(position + 1);
            offset = (int) ((targetItem.getWidth() + nextItem.getWidth()) / 2 * positionOffset);
            leftMargin -= offset;
            rightMargin += offset;
        }

        int scrollDestination;
        if (leftMargin > leftSpace) {
            scrollDestination = 0;
        } else if (rightMargin > rightSpace) {
            scrollDestination = totalWidth - getWidth();
        } else {
            scrollDestination = leftSpace - leftMargin;
        }

        if (updateIndicator) {
            if (leftMargin > leftSpace) {
                mIndicatorPosition = leftSpace + targetItem.getWidth() / 2 + offset;
            } else if (rightMargin > rightSpace) {
                mIndicatorPosition = totalWidth - rightSpace - targetItem.getWidth() / 2 + offset;
            } else {
                mIndicatorPosition = leftSpace + targetItem.getWidth() / 2 + offset;
            }
        }

        if (smooth) {
            smoothScrollTo(scrollDestination, 0);
        } else {
            scrollTo(scrollDestination, 0);
        }
        invalidate();
    }

    private void updateSelectedTab(int position) {
        if (mSelectedPosition != SELECT_NONE) {
            mTextViewList.get(mSelectedPosition).setTextColor(Color.BLACK);
        }
        mSelectedPosition = position;
        mTextViewList.get(mSelectedPosition).setTextColor(Color.RED);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scrollToItem(position, positionOffset, false, true);
    }

    @Override
    public void onPageSelected(int position) {
        updateSelectedTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
