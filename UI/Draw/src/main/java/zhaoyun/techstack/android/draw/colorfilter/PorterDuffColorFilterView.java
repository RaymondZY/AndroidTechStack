package zhaoyun.techstack.android.draw.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2019/11/24
 */
public class PorterDuffColorFilterView extends View {

    private List<ColorFilter> mColorFilterList;
    private Paint mBitmapPaint;
    private Paint mTextPaint;
    private Bitmap mBitmap;

    public PorterDuffColorFilterView(Context context) {
        this(context, null);
    }

    public PorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.demo);

        mColorFilterList = new ArrayList<>();
        for (PorterDuff.Mode mode : PorterDuff.Mode.values()) {
            mColorFilterList.add(new PorterDuffColorFilter(Color.YELLOW, mode));
        }

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int offset = (width - 3 * mBitmap.getWidth() - 2 * 50) / 2;
        int baseX = offset;
        int baseY = offset;

        for (int i = 0; i < mColorFilterList.size(); i++) {
            ColorFilter colorFilter = mColorFilterList.get(i);
            PorterDuff.Mode mode = PorterDuff.Mode.values()[i];

            mBitmapPaint.setColorFilter(colorFilter);
            canvas.drawBitmap(mBitmap, baseX, baseY, mBitmapPaint);
            canvas.drawText(mode.toString(), baseX, baseY + mBitmap.getHeight() + 40, mTextPaint);

            baseX += mBitmap.getWidth() + 50;
            if (baseX + mBitmap.getWidth() > width) {
                baseX = offset;
                baseY += mBitmap.getHeight() + offset;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int offset = (width - 3 * mBitmap.getWidth() - 2 * 50) / 2;
        int rowCount = (PorterDuff.Mode.values().length - 1) / 3 + 1;
        int height = mBitmap.getHeight() * rowCount + offset * (rowCount - 1) + 2 * offset;
        setMeasuredDimension(width, height);
    }
}
