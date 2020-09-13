package zhaoyun.techstack.android.draw.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2020/1/14
 */
public class LightingColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private LightingColorFilter mRedColorFilter;
    private LightingColorFilter mGreenColorFilter;
    private LightingColorFilter mBlueColorFilter;

    public LightingColorFilterView(Context context) {
        this(context, null);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xuxu);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedColorFilter = new LightingColorFilter(0xef0000, 0);
        mGreenColorFilter = new LightingColorFilter(0x00ef00, 0);
        mBlueColorFilter = new LightingColorFilter(0x0000ef, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight() * 4 + 150);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int offsetX = (width - mBitmap.getWidth()) / 2;
        int offsetY = 0;

        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);

        offsetY += mBitmap.getHeight() + 50;

        mPaint.setColorFilter(mRedColorFilter);
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);

        offsetY += mBitmap.getHeight() + 50;

        mPaint.setColorFilter(mGreenColorFilter);
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);

        offsetY += mBitmap.getHeight() + 50;

        mPaint.setColorFilter(mBlueColorFilter);
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }
}
