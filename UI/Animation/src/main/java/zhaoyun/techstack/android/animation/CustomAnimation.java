package zhaoyun.techstack.android.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author zhaoyun
 * @version 2020/6/20
 */
public class CustomAnimation extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        transformation.getMatrix().setTranslate(
                (float) Math.sin(interpolatedTime * 50) * 8,
                (float) Math.sin(interpolatedTime * 50) * 8
        );
    }
}
