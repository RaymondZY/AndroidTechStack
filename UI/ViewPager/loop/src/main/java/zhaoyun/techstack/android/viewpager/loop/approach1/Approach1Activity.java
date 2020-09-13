package zhaoyun.techstack.android.viewpager.loop.approach1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.viewpager.loop.R;

public class Approach1Activity extends AppCompatActivity {

    private LoopViewPagerApproach1 mLoopViewPagerApproach1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_pager_approach1);

        mLoopViewPagerApproach1 = findViewById(R.id.viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoopViewPagerApproach1.scheduleAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoopViewPagerApproach1.stopAutoPlay();
    }
}
