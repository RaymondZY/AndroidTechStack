package zhaoyun.techstack.android.draw.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/19
 */
public class DiscretePathEffectView extends View {

    private Path mPath;
    private Paint mPathPaint;
    private PathEffect mPathEffect1;
    private PathEffect mPathEffect2;
    private PathEffect mPathEffect3;
    private Paint mTextPaint;

    public DiscretePathEffectView(Context context) {
        this(context, null);
    }

    public DiscretePathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscretePathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPathEffect1 = new DiscretePathEffect(20, 5);
        mPathEffect2 = new DiscretePathEffect(5, 20);
        mPathEffect3 = new DiscretePathEffect(20, 20);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(30);

        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        mPath.lineTo(0, 0);
        mPath.lineTo(50, 100);
        mPath.lineTo(100, 50);
        mPath.lineTo(150, 80);
        mPath.lineTo(200, 40);
        mPath.lineTo(250, 90);
        mPath.lineTo(300, 30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("original", 450, 50, mTextPaint);

        canvas.translate(0, 100);


        mPathPaint.setPathEffect(mPathEffect1);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("segment = 20, deviation = 5", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect2);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("segment = 5, deviation = 20", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect3);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("segment = 20, deviation = 20", 450, 50, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 400);
    }
}
