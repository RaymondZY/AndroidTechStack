package zhaoyun.techstack.android.draw.shader.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2020/1/12
 */
public class BitmapShaderExampleView extends View {

    private Path mPath;
    private Paint mPaint;
    private float mPrevX;
    private float mPrevY;
    private int mScaledTouchSlop;
    private Bitmap mBitmap;

    public BitmapShaderExampleView(Context context) {
        this(context, null);
    }

    public BitmapShaderExampleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderExampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xuxu);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPath = new Path();

        setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if (Math.abs(x - mPrevX) >= mScaledTouchSlop ||
                        Math.abs(y - mPrevY) >= mScaledTouchSlop) {
                    float controlPointX = (mPrevX + x) / 2;
                    float controlPointY = (mPrevY + y) / 2;
                    mPath.quadTo(controlPointX, controlPointY, x, y);
                    invalidate();
                }
                break;
        }

        mPrevX = x;
        mPrevY = y;

        return true;
    }
}
