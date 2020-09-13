package zhaoyun.techstack.android.draw.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/18
 */
public class LinearGradientView extends View {

    private Paint mPaint;
    private Rect mRect;
    private LinearGradient mLinearGradientTileModeRepeat;
    private LinearGradient mLinearGradientTileModeMirror;
    private LinearGradient mLinearGradientTileModeClamp;

    public LinearGradientView(Context context) {
        this(context, null);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mLinearGradientTileModeRepeat = new LinearGradient(
                0, 0,
                100, 100,
                new int[] {Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.REPEAT
        );
        mLinearGradientTileModeMirror = new LinearGradient(
                0, 0,
                100, 100,
                new int[] {Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.MIRROR
        );
        mLinearGradientTileModeClamp = new LinearGradient(
                0, 0,
                100, 100,
                new int[] {Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.CLAMP
        );
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0, 0, 200, 200);
        mPaint.setShader(mLinearGradientTileModeRepeat);
        canvas.drawRect(mRect, mPaint);

        canvas.translate(250, 0);

        mRect.set(0, 0, 200, 200);
        mPaint.setShader(mLinearGradientTileModeMirror);
        canvas.drawRect(mRect, mPaint);

        canvas.translate(250, 0);

        mRect.set(0, 0, 200, 200);
        mPaint.setShader(mLinearGradientTileModeClamp);
        canvas.drawRect(mRect, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 200);
    }
}
