package zhaoyun.techstack.android.draw.shader.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2020/1/13
 */
public class RadialGradientExampleView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public RadialGradientExampleView(Context context) {
        this(context, null);
    }

    public RadialGradientExampleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialGradientExampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xuxu);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() - mBitmap.getWidth(), getHeight() - mBitmap.getHeight());
        canvas.drawRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int newWidth;
        int newHeight;
        if (w * height > h * width) {
            newWidth = (int) (1.0f * width * h / height);
            newHeight = h;
        } else {
            newWidth = w;
            newHeight = (int) (1.0f * height * w / width);
        }

        int radius = Math.min(newWidth, newHeight);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, newWidth, newHeight, true);

        RadialGradient radialGradient = new RadialGradient(0, mBitmap.getHeight(), radius, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient, PorterDuff.Mode.DST_IN);
        mPaint.setShader(composeShader);
    }
}
