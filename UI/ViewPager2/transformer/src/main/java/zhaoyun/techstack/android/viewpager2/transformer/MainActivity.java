package zhaoyun.techstack.android.viewpager2.transformer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new Adapter(this));
        viewPager.setBackgroundColor(Color.BLACK);
        viewPager.setPageTransformer(new CustomTransformer());
    }

    private static class Adapter extends FragmentStateAdapter {

        private static final int N = 10;

        private Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return NumberFragment.createInstance(position);
        }

        @Override
        public int getItemCount() {
            return N;
        }
    }

    private static class CustomTransformer implements ViewPager2.PageTransformer {

        private static final float MIN_SCALE = 0.6f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 0) {
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else if (position <= 1) {
                int width = page.getWidth();
                float scale = MIN_SCALE + (1 - position) * (1 - MIN_SCALE);
                float alpha = MIN_ALPHA + (1 - position) * (1 - MIN_ALPHA);
                page.setAlpha(alpha);
                page.setTranslationX(width * -position);
                page.setScaleX(scale);
                page.setScaleY(scale);
            } else {
                page.setAlpha(0);
            }
        }
    }
}
