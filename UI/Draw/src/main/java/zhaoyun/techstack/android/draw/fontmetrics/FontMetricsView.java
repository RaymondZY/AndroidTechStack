package zhaoyun.techstack.android.draw.fontmetrics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2019/11/20
 */
public class FontMetricsView extends View {

    private static final String TAG = FontMetricsView.class.getSimpleName();

    private Paint mPaint;
    private Paint.FontMetrics mFontMetrics;

    public FontMetricsView(Context context) {
        this(context, null);
    }

    public FontMetricsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(200);
        mPaint.setStrokeWidth(3);
        mFontMetrics = mPaint.getFontMetrics();

        Log.d(TAG, "mFontMetrics.ascent = " + mFontMetrics.ascent);
        Log.d(TAG, "mFontMetrics.descent = " + mFontMetrics.descent);
        Log.d(TAG, "mFontMetrics.top = " + mFontMetrics.top);
        Log.d(TAG, "mFontMetrics.bottom = " + mFontMetrics.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float baseline = 300;
        float width = mPaint.measureText("abcdefg");

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseline + mFontMetrics.ascent, width + 100, baseline + mFontMetrics.ascent, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, baseline, width + 100, baseline, mPaint);
        canvas.drawText("abcdefg", 50, baseline, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, baseline + mFontMetrics.descent, width + 100, baseline + mFontMetrics.descent, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, baseline + mFontMetrics.descent + mFontMetrics.leading, width + 100, baseline + mFontMetrics.descent + mFontMetrics.leading, mPaint);

        mPaint.setTextSize(30);

        float offsetX = 0, offsetY = baseline + mFontMetrics.descent + 200;
        mPaint.setColor(Color.BLACK);
        canvas.drawText("Baseline = " + baseline, offsetX, offsetY, mPaint);
        offsetX += mPaint.measureText("Baseline = " + baseline) + 50;

        mPaint.setColor(Color.RED);
        canvas.drawText("ascent = " + mFontMetrics.ascent, offsetX, offsetY, mPaint);
        offsetX = 0;
        offsetY += mPaint.getFontMetrics().descent + 50;

        mPaint.setColor(Color.GREEN);
        canvas.drawText("descent = " + mFontMetrics.descent, offsetX, offsetY, mPaint);
        offsetX += mPaint.measureText("descent = " + mFontMetrics.descent) + 50;

        mPaint.setColor(Color.BLUE);
        canvas.drawText("leading = " + mFontMetrics.leading, offsetX, offsetY, mPaint);
    }
}
