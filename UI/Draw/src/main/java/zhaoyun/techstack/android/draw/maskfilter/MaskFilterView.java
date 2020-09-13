package zhaoyun.techstack.android.draw.maskfilter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2020/1/14
 */
public class MaskFilterView extends View {

    private int mBlurValue;
    private Paint mPaint;
    private Bitmap mBitmap;

    public MaskFilterView(Context context) {
        this(context, null);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskFilterView, defStyleAttr, 0);
        mBlurValue = typedArray.getInteger(R.styleable.MaskFilterView_blur_value, 0);
        typedArray.recycle();

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xuxu);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.values()[mBlurValue]));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createScaledBitmap(mBitmap, w * 2 / 3, h * 2 / 3, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int offsetX = (width - mBitmap.getWidth()) / 2;
        int offsetY = (height - mBitmap.getHeight()) / 2;
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }
}
