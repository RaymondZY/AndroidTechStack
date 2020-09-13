package zhaoyun.techstack.android.draw.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/18
 */
public class SweepGradientView extends View {

    private Paint mPaint;
    private Rect mRect;
    private SweepGradient mSweepGradient;

    public SweepGradientView(Context context) {
        this(context, null);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mSweepGradient = new SweepGradient(
                100, 100,
                new int[] {Color.RED, Color.GREEN, Color.BLUE},
                null
        );
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0, 0, 200, 200);
        mPaint.setShader(mSweepGradient);
        canvas.drawRect(mRect, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 200);
    }
}
