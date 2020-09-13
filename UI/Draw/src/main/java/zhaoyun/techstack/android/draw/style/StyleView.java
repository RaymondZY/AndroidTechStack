package zhaoyun.techstack.android.draw.style;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2020/1/13
 */
public class StyleView extends View {

    private Paint mPaint;

    public StyleView(Context context) {
        this(context, null);
    }

    public StyleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.StyleView, defStyleAttr, 0);
        String style = typedArray.getString(R.styleable.StyleView_paint_style);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        switch (style) {
            case "fill":
                mPaint.setStyle(Paint.Style.FILL);
                break;

            case "stroke":
                mPaint.setStyle(Paint.Style.STROKE);
                break;

            case "fill_and_stroke":
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(100, 100, 50, mPaint);
    }
}
