package zhaoyun.techstack.android.draw.transfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author zhaoyun
 * @version 2019/11/23
 */
public class PorterDuffTransferModeView extends View {

    private Paint mSrcPaint;
    private Paint mDstPaint;
    private Paint mTransparentPaint;
    private Paint mTextPaint;
    private List<Xfermode> mModeList;
    private Path mPath;

    public PorterDuffTransferModeView(Context context) {
        this(context, null);
    }

    public PorterDuffTransferModeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffTransferModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        /*
         * CLEAR与一些其它的操作不支持硬件加速，会绘制出黑色的情况。
         */
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPath = new Path();

        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.FILL);
        mSrcPaint.setColor(0xFF33A9D8);

        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.FILL);
        mDstPaint.setColor(0xFFFFC90E);

        mTransparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransparentPaint.setStyle(Paint.Style.FILL);
        mTransparentPaint.setColor(Color.TRANSPARENT);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(30);

        mModeList = new ArrayList<>();
        for (PorterDuff.Mode mode : PorterDuff.Mode.values()) {
            mModeList.add(new PorterDuffXfermode(mode));
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int baseX = (width - 4 * 200 - 3 * 50) / 2;
        int baseY = baseX;

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        /*
         * 关闭硬件加速，或者saveLayer, restore
         * canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
         */
        for (int i = 0; i < mModeList.size(); i++) {
            Xfermode xfermode = mModeList.get(i);
            PorterDuff.Mode mode = PorterDuff.Mode.values()[i];

            mTransparentPaint.setXfermode(xfermode);
            mSrcPaint.setXfermode(xfermode);
            mPath.addCircle(baseX + 150, baseY + 150, 50, Path.Direction.CW);

            canvas.drawRect(baseX, baseY, baseX + 150, baseY + 150, mDstPaint);
            canvas.save();
            canvas.clipOutPath(mPath);
            canvas.drawRect(baseX, baseY, baseX + 200, baseY + 200, mTransparentPaint);
            canvas.restore();
            canvas.drawCircle(baseX + 150, baseY + 150, 50, mSrcPaint);
            canvas.drawText(mode.toString(), baseX, baseY + 230, mTextPaint);

            baseX += 250;
            if (baseX + 150 > width) {
                baseX = (width - 4 * 200 - 3 * 50) / 2;
                baseY += 300;
            }
        }
        /*
         * 关闭硬件加速，或者saveLayer, restore
         * canvas.restore();
         */
    }
}
