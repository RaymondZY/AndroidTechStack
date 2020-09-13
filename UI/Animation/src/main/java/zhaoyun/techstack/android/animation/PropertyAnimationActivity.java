package zhaoyun.techstack.android.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PropertyAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        TextView textView = findViewById(R.id.textView);

        findViewById(R.id.button_value_animator).setOnClickListener(
                view -> {
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(400, 200);
                    valueAnimator.setDuration(2000);
                    valueAnimator.setRepeatCount(-1);
                    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    valueAnimator.addUpdateListener(animation -> {
                        int size = (int) animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                        layoutParams.width = size;
                        layoutParams.height = size;
                        textView.setLayoutParams(layoutParams);
                    });
                    valueAnimator.start();
                }
        );

        findViewById(R.id.button_value_animator_object).setOnClickListener(
                view -> {
                    ValueAnimator valueAnimator = ValueAnimator.ofObject(
                            (fraction, startValue, endValue) -> {
                                Point startPoint = (Point) startValue;
                                Point endPoint = (Point) endValue;
                                int x = (int) ((endPoint.x - startPoint.x) * fraction + startPoint.x);
                                int y = (int) ((endPoint.y - startPoint.y) * fraction + startPoint.y);
                                return new Point(x, y);
                            },
                            new Point(0, 0),
                            new Point(100, 100)
                    );
                    valueAnimator.setDuration(2000);
                    valueAnimator.setRepeatCount(-1);
                    valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    valueAnimator.addUpdateListener(animation -> {
                        Point point = (Point) animation.getAnimatedValue();
                        textView.setTranslationX(point.x);
                        textView.setTranslationY(point.y);
                    });
                    valueAnimator.start();
                }
        );

        findViewById(R.id.button_object_animator).setOnClickListener(
                view -> {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 1, 0.5f);
                    objectAnimator.setDuration(2000);
                    objectAnimator.setRepeatCount(-1);
                    objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                    objectAnimator.start();
                }
        );
    }
}
