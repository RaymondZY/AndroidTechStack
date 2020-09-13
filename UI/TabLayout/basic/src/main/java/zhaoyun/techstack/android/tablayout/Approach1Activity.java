package zhaoyun.techstack.android.tablayout;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Approach1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approach_1);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new NumberAdapter(getSupportFragmentManager(), 10));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicator(R.drawable.tab_indicator);
//        tabLayout.setTabRippleColor(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), android.R.color.holo_green_light, null)));
//        tabLayout.setTabIndicatorFullWidth(false);
    }
}
