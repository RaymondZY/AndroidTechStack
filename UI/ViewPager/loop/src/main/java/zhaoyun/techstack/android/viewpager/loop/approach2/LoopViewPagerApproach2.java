package zhaoyun.techstack.android.viewpager.loop.approach2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import zhaoyun.techstack.android.viewpager.loop.R;

/**
 * @author zhaoyun
 * @version 2020/5/20
 */
public class LoopViewPagerApproach2 extends ViewPager {

    public LoopViewPagerApproach2(@NonNull Context context) {
        this(context, null);
    }

    public LoopViewPagerApproach2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setAdapter(new Adapter(context));
        setCurrentItem(1);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        addOnPageChangeListener(new OnPageChangeListener() {

            private boolean mNeedResetPosition;
            private int mResetPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mNeedResetPosition = true;
                    mResetPosition = Adapter.N;
                } else if (position == Adapter.N + 1) {
                    mNeedResetPosition = true;
                    mResetPosition = 1;
                } else {
                    mNeedResetPosition = false;
                    mResetPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE && mNeedResetPosition) {
                    setCurrentItem(mResetPosition, false);
                }
            }
        });
    }

    private static class Adapter extends PagerAdapter {

        private static final int N = 10;

        private LayoutInflater mLayoutInflater;

        private Adapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.item_view_number, container, false);
            itemView.setTag(position);
            container.addView(itemView);
            TextView textView = itemView.findViewById(R.id.textView);
            textView.setText(String.valueOf(adaptPosition(position)));
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View itemView = container.findViewWithTag(position);
            container.removeView(itemView);
        }

        @Override
        public int getCount() {
            return N + 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        private int adaptPosition(int position) {
            if (position == 0) {
                return N - 1;
            } else if (position == N + 1) {
                return 0;
            } else {
                return position - 1;
            }
        }
    }
}
