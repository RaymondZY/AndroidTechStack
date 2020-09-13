package zhaoyun.techstack.android.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Approach2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approach_2);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new NumberAdapter(getSupportFragmentManager(), 10));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.tab_indicator, null));
    }
}
