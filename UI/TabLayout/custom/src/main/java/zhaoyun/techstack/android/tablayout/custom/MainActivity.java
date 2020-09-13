package zhaoyun.techstack.android.tablayout.custom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new NumberAdapter(getSupportFragmentManager(), 10));

        CustomTabLayout customTabLayout = findViewById(R.id.tabLayout);
        customTabLayout.setViewPager(viewPager);
    }
}
