package zhaoyun.techstack.android.draw.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/18
 */
public class RadialGradientView extends View {

    private Paint mPaint;
    private RadialGradient mRadialGradientTileRepeat;
    private RadialGradient mRadialGradientTileMirror;
    private RadialGradient mRadialGradientTileClamp;

    public RadialGradientView(Context context) {
        this(context, null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mRadialGradientTileRepeat = new RadialGradient(
                100, 100,
                50,
                new int[] { Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.REPEAT
        );
        mRadialGradientTileMirror = new RadialGradient(
                100, 100,
                50,
                new int[] { Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.MIRROR
        );
        mRadialGradientTileClamp = new RadialGradient(
                100, 100,
                50,
                new int[] { Color.RED, Color.GREEN, Color.BLUE},
                null,
                Shader.TileMode.CLAMP
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setShader(mRadialGradientTileRepeat);
        canvas.drawCircle(100, 100, 100, mPaint);

        canvas.translate(250, 0);

        mPaint.setShader(mRadialGradientTileMirror);
        canvas.drawCircle(100, 100, 100, mPaint);

        canvas.translate(250, 0);

        mPaint.setShader(mRadialGradientTileClamp);
        canvas.drawCircle(100, 100, 100, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 200);
    }
}
