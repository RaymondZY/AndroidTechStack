package zhaoyun.techstack.android.draw.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/19
 */
public class PathDashPathEffectView extends View {

    private Path mPath;
    private Paint mPathPaint;
    private PathEffect mPathEffect1;
    private PathEffect mPathEffect2;
    private PathEffect mPathEffect3;
    private PathEffect mPathEffect4;
    private PathEffect mPathEffect5;
    private Paint mTextPaint;

    public PathDashPathEffectView(Context context) {
        this(context, null);
    }

    public PathDashPathEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathDashPathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        Path shape = new Path();
        shape.addRect(0, 0, 10, 10, Path.Direction.CW);

        mPathEffect1 = new PathDashPathEffect(shape, 20, 0, PathDashPathEffect.Style.TRANSLATE);
        mPathEffect2 = new PathDashPathEffect(shape, 40, 0, PathDashPathEffect.Style.TRANSLATE);
        mPathEffect3 = new PathDashPathEffect(shape, 40, 30, PathDashPathEffect.Style.TRANSLATE);
        mPathEffect4 = new PathDashPathEffect(shape, 20, 0, PathDashPathEffect.Style.ROTATE);
        mPathEffect5 = new PathDashPathEffect(shape, 20, 0, PathDashPathEffect.Style.MORPH);

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
        mPathPaint.setPathEffect(mPathEffect1);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("advance = 20, phase = 0, Style = TRANSLATE", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect2);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("advance = 40, phase = 0, Style = TRANSLATE", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect3);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("advance = 40, phase = 30, Style = TRANSLATE", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect4);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("advance = 20, phase = 0, Style = ROTATE", 450, 50, mTextPaint);

        canvas.translate(0, 100);

        mPathPaint.setPathEffect(mPathEffect5);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawText("advance = 20, phase = 0, Style = MORPH", 450, 50, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 500);
    }
}
