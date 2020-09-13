package zhaoyun.techstack.android.animation;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation combinedAnimation = AnimationUtils.loadAnimation(this, R.anim.combined);

        Switch programmaticallySwitch = findViewById(R.id.switch_programmatically);
        TextView textView = findViewById(R.id.textView);

        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("DEBUG", "ViewAnimationActivity.onAnimationStart()");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("DEBUG", "ViewAnimationActivity.onAnimationEnd()");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("DEBUG", "ViewAnimationActivity.onAnimationRepeat()");
            }
        };

        findViewById(R.id.button_translate_animation).setOnClickListener(
                view -> {
                    Animation animation = programmaticallySwitch.isChecked() ? createTranslateAnimation() : translateAnimation;
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
        findViewById(R.id.button_alpha_animation).setOnClickListener(
                view -> {
                    Animation animation = programmaticallySwitch.isChecked() ? createAlphaAnimation() : alphaAnimation;
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
        findViewById(R.id.button_scale_animation).setOnClickListener(
                view -> {
                    Animation animation = programmaticallySwitch.isChecked() ? createScaleAnimation() : scaleAnimation;
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
        findViewById(R.id.button_rotate_animation).setOnClickListener(
                view -> {
                    Animation animation = programmaticallySwitch.isChecked() ? createRotateAnimation() : rotateAnimation;
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
        findViewById(R.id.button_combined_animation).setOnClickListener(
                view -> {
                    Animation animation = programmaticallySwitch.isChecked() ? createCombinedAnimation() : combinedAnimation;
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
        findViewById(R.id.button_custom_animation).setOnClickListener(
                view -> {
                    Animation animation = createCustomAnimation();
                    animation.setAnimationListener(animationListener);
                    textView.startAnimation(animation);
                }
        );
    }

    private Animation createTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 300);
        translateAnimation.setDuration(2000L);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }

    private Animation createAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);
        alphaAnimation.setDuration(2000L);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        return alphaAnimation;
    }

    private Animation createScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.3f, 1, 0.3f, 50f, 50f);
        scaleAnimation.setDuration(2000L);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        return scaleAnimation;
    }

    private Animation createRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 100, 100);
        rotateAnimation.setDuration(2000L);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        return rotateAnimation;
    }

    private Animation createCombinedAnimation() {
        AnimationSet combined = new AnimationSet(true);
        combined.addAnimation(createTranslateAnimation());
        combined.addAnimation(createAlphaAnimation());
        combined.addAnimation(createScaleAnimation());
        combined.addAnimation(createRotateAnimation());
        return combined;
    }

    private Animation createCustomAnimation() {
        CustomAnimation customAnimation = new CustomAnimation();
        customAnimation.setDuration(2000L);
        customAnimation.setRepeatCount(Animation.INFINITE);
        customAnimation.setRepeatMode(Animation.REVERSE);
        return customAnimation;
    }
}
