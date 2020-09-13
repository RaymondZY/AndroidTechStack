package zhaoyun.techstack.android.draw.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2019/11/19
 */
public class BitmapShaderView extends View {

    private Paint mPaint;
    private BitmapShader mBitmapShaderTileRepeat;
    private BitmapShader mBitmapShaderTileMirror;
    private BitmapShader mBitmapShaderTileClamp;
    private Bitmap mBitmap;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.demo);
        mBitmapShaderTileRepeat = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mBitmapShaderTileMirror = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mBitmapShaderTileClamp = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShader(mBitmapShaderTileRepeat);
        canvas.drawRect(0, 0, mBitmap.getWidth() * 3, mBitmap.getHeight(), mPaint);

        canvas.translate(0, mBitmap.getHeight() + 50);

        mPaint.setShader(mBitmapShaderTileMirror);
        canvas.drawRect(0, 0, mBitmap.getWidth() * 3, mBitmap.getHeight(), mPaint);

        canvas.translate(0, mBitmap.getHeight() + 50);

        mPaint.setShader(mBitmapShaderTileClamp);
        canvas.drawRect(0, 0, mBitmap.getWidth() * 3, mBitmap.getHeight(), mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, mBitmap.getHeight() * 3 + 100);
    }
}
