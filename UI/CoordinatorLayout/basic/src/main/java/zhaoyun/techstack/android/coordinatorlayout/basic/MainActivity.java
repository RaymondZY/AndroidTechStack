package zhaoyun.techstack.android.coordinatorlayout.basic;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView baseTextView = findViewById(R.id.textView_base);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            int x, y;
            if (progress <= 0.5) {
                x = y = (int) (progress * 400);
            } else {
                x = y = 400 - (int) (progress * 400);
            }
            baseTextView.setX(x);
            baseTextView.setY(y);
        });
        valueAnimator.start();
    }
}
