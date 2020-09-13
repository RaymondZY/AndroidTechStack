package zhaoyun.techstack.android.viewpager.transformer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setBackgroundColor(Color.BLACK);
        viewPager.setPageTransformer(true, new CustomPageTransformer());
        viewPager.setAdapter(new ViewAdapter(this));
    }

    private static class CustomPageTransformer implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.1f;

        @Override
        public void transformPage(@NonNull View page, float position) {
            int width = page.getWidth();

            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 0) {
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else if (position <= 1) {
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
