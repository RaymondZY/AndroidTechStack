package zhaoyun.techstack.android.draw.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/22
 */
public class BezierView extends View {

    private static final int CONTROL_POINT_COUNT = 5;
    private static final int BEZIER_POINT_COUNT = 500;

    private Paint mCommonPaint;
    private Paint mBezierPaint;
    private PointF[] mControlPointArray;
    private PointF[] mMiddleStatePointArray;
    private int[] mCombination;
    private PointF mBezierPoint;
    private Path mControlPointPath;
    private int mPointRadius;
    private Random mRandom;

    private ValueAnimator mValueAnimator;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mRandom = new Random();

        mCommonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCommonPaint.setStyle(Paint.Style.STROKE);
        mCommonPaint.setStrokeWidth(2);
        mCommonPaint.setColor(Color.BLACK);

        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setStrokeWidth(3);
        mBezierPaint.setColor(Color.RED);

        mPointRadius = 5;

        mControlPointArray = new PointF[CONTROL_POINT_COUNT];
        for (int i = 0; i < mControlPointArray.length; i++) {
            mControlPointArray[i] = new PointF();
        }
        mMiddleStatePointArray = new PointF[CONTROL_POINT_COUNT];
        for (int i = 0; i < mMiddleStatePointArray.length; i++) {
            mMiddleStatePointArray[i] = new PointF();
        }
        mControlPointPath = new Path();
        mBezierPoint = new PointF();

        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setDuration(3000);
        mValueAnimator.setRepeatCount(-1);
        mValueAnimator.addUpdateListener(animation -> invalidate());
        mValueAnimator.start();

        randomControlPoint();
        setControlPointPath();
        calculateCombination();

        setOnClickListener(view -> {
            randomControlPoint();
            setControlPointPath();
        });
    }

    private void calculateCombination() {
        mCombination = new int[CONTROL_POINT_COUNT];
        int[] prevCombination = new int[CONTROL_POINT_COUNT];
        mCombination[0] = 1;
        mCombination[1] = 1;
        for (int totalCount = 2; totalCount <= CONTROL_POINT_COUNT - 1; totalCount++) {
            System.arraycopy(mCombination, 0, prevCombination, 0, totalCount);
            for (int selectCount = 0; selectCount <= totalCount; selectCount++) {
                if (selectCount == 0 || selectCount == totalCount) {
                    mCombination[selectCount] = 1;
                } else {
                    mCombination[selectCount] = prevCombination[selectCount - 1] + prevCombination[selectCount];
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawControlPoint(canvas);
        drawControlPointPath(canvas);
        drawBezierCurve(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        for (int i = 0; i < mControlPointArray.length; i++) {
            mMiddleStatePointArray[i].x = mControlPointArray[i].x;
            mMiddleStatePointArray[i].y = mControlPointArray[i].y;

        }
        float t = mValueAnimator.getAnimatedFraction();
        for (int order = mControlPointArray.length - 1; order >= 1; order--) {
            for (int i = 0; i < order; i++) {
                mMiddleStatePointArray[i].x = mMiddleStatePointArray[i].x * (1 - t) + mMiddleStatePointArray[i + 1].x * t;
                mMiddleStatePointArray[i].y = mMiddleStatePointArray[i].y * (1 - t) + mMiddleStatePointArray[i + 1].y * t;

                canvas.drawCircle(mMiddleStatePointArray[i].x, mMiddleStatePointArray[i].y, mPointRadius, mCommonPaint);

                if (i > 0) {
                    canvas.drawLine(
                            mMiddleStatePointArray[i - 1].x,
                            mMiddleStatePointArray[i - 1].y,
                            mMiddleStatePointArray[i].x,
                            mMiddleStatePointArray[i].y,
                            mCommonPaint
                    );
                }
            }
        }
    }

    private void randomControlPoint() {
        for (PointF point : mControlPointArray) {
            float x = mRandom.nextFloat() * 800 + 200;
            float y = mRandom.nextFloat() * 800 + 200;
            point.set(x, y);
        }
    }

    private void setControlPointPath() {
        mControlPointPath.reset();
        mControlPointPath.moveTo(mControlPointArray[0].x, mControlPointArray[0].y);
        for (int i = 1; i < mControlPointArray.length; i++) {
            mControlPointPath.lineTo(mControlPointArray[i].x, mControlPointArray[i].y);
        }
    }

    private void drawControlPoint(Canvas canvas) {
        for (PointF point : mControlPointArray) {
            canvas.drawCircle(point.x, point.y, mPointRadius, mCommonPaint);
        }
    }

    private void drawControlPointPath(Canvas canvas) {
        canvas.drawPath(mControlPointPath, mCommonPaint);
    }

    private void drawBezierCurve(Canvas canvas) {
        for (float t = 0; t <= mValueAnimator.getAnimatedFraction(); t += 1.0 / BEZIER_POINT_COUNT) {
            calculateBezierPoint(mBezierPoint, mControlPointArray, t);
            canvas.drawPoint(mBezierPoint.x, mBezierPoint.y, mBezierPaint);
        }
    }

    private void calculateBezierPoint(PointF bezierPoint, PointF[] controlPointArray, float t) {
        float x = 0, y = 0;
        int n = controlPointArray.length;
        for (int i = 0; i < n; i++) {
            double coefficient = mCombination[i] * Math.pow(1 - t, n - 1 - i) * Math.pow(t, i);
            x += controlPointArray[i].x * coefficient;
            y += controlPointArray[i].y * coefficient;
        }
        bezierPoint.set(x, y);
    }
}
