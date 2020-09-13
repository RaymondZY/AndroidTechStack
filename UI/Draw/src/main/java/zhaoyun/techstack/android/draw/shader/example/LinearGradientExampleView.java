package zhaoyun.techstack.android.draw.shader.example;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/12/28
 */
public class LinearGradientExampleView extends View {

    private Paint mPaint;
    private String mText;

    public LinearGradientExampleView(Context context) {
        this(context, null);
    }

    public LinearGradientExampleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientExampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mText = "Shader LinearGradient Example";

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(40);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float textWidth = mPaint.measureText(mText);
        float x = (w - textWidth) / 2;
        Shader shader = new LinearGradient(
                x - textWidth, 0, 0, 0,
                new int[] {Color.BLACK, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.BLACK},
                null,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(shader);

        Matrix matrix = new Matrix();
        matrix.setTranslate(textWidth * 2, 0);
        shader.setLocalMatrix(matrix);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, textWidth * 2).setDuration(5000);
        valueAnimator.addUpdateListener(animator -> {
            float offsetX = (float) animator.getAnimatedValue();
            shader.getLocalMatrix(matrix);
            matrix.setTranslate(offsetX, 0);
            shader.setLocalMatrix(matrix);
            mPaint.setShader(shader);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            invalidate();
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float textWidth = mPaint.measureText(mText);
        float textHeight = mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top;
        float x = (width - textWidth) / 2;
        float y = (height + textHeight) / 2;
        canvas.drawText(mText, x, y, mPaint);
    }
}
