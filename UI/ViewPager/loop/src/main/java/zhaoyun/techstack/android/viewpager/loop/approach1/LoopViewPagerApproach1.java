package zhaoyun.techstack.android.viewpager.loop.approach1;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import zhaoyun.techstack.android.viewpager.loop.FixedSpeedScroller;
import zhaoyun.techstack.android.viewpager.loop.R;
import zhaoyun.techstack.android.viewpager.loop.Utils;

/**
 * @author zhaoyun
 * @version 2020/5/20
 */
public class LoopViewPagerApproach1 extends ViewPager {

    private AutoPlayHandler mAutoPlayHandler;

    public LoopViewPagerApproach1(@NonNull Context context) {
        this(context, null);
    }

    public LoopViewPagerApproach1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Adapter adapter = new Adapter(context);
        setAdapter(adapter);
        setCurrentItem(adapter.calcStartPosition());

        mAutoPlayHandler = new AutoPlayHandler(this);

        FixedSpeedScroller scroller = new FixedSpeedScroller(context, new LinearOutSlowInInterpolator());
        scroller.setDuration(800);
        Utils.setViewPagerScroller(this, scroller);
    }

    public void scheduleAutoPlay() {
        mAutoPlayHandler.schedule();
    }

    public void stopAutoPlay() {
        mAutoPlayHandler.stop();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAutoPlayHandler.schedule();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAutoPlayHandler.stop();
    }

    private static class Adapter extends PagerAdapter {

        private static final int N = 10;

        private LayoutInflater mLayoutInflater;

        private Adapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        private int calcStartPosition() {
            return Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % N;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.item_view_number, container, false);
            TextView textView = itemView.findViewById(R.id.textView);
            textView.setText(String.valueOf(adaptPosition(position)));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View itemView = (View) object;
            container.removeView(itemView);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        private int adaptPosition(int position) {
            return position % N;
        }
    }

    private static class AutoPlayHandler extends Handler implements OnPageChangeListener {

        private static final int MESSAGE_AUTO_PLAY = 0;
        private static final int DURATION = 3000;

        private WeakReference<ViewPager> mViewPagerWeakReference;
        private int mState;

        private AutoPlayHandler(ViewPager viewPager) {
            mViewPagerWeakReference = new WeakReference<>(viewPager);
            viewPager.addOnPageChangeListener(this);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == MESSAGE_AUTO_PLAY) {
                ViewPager viewPager = mViewPagerWeakReference.get();
                if (viewPager != null) {
                    if (mState == ViewPager.SCROLL_STATE_IDLE) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    }
                    schedule();
                }
            }
        }

        private void schedule() {
            if (!hasMessages(MESSAGE_AUTO_PLAY)) {
                sendEmptyMessageDelayed(MESSAGE_AUTO_PLAY, DURATION);
            }
        }

        private void stop() {
            removeMessages(MESSAGE_AUTO_PLAY);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mState = state;
        }
    }
}
