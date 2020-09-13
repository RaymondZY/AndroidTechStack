package zhaoyun.techstack.android.draw.particle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.draw.R;

/**
 * @author zhaoyun
 * @version 2019/11/30
 */
public class ParticleEffectView extends View {

    private static final int PARTICLE_WIDTH = 8;
    private static final int PARTICLE_HEIGHT = 8;

    private Paint mPaint;
    private Particle[][] mParticles;
    private ValueAnimator mValueAnimator;
    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;

    public ParticleEffectView(Context context) {
        this(context, null);
    }

    public ParticleEffectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        initPaint();
        initParticle();
        initAnimator();
        initListener();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initParticle() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xuxu);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        int countX = mBitmapWidth / PARTICLE_WIDTH;
        int countY = mBitmapHeight / PARTICLE_HEIGHT;
        mParticles = new Particle[countX][];
        for (int i = 0; i < mParticles.length; i++) {
            mParticles[i] = new Particle[countY];
        }
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                mParticles[i][j] = new Particle(
                        i * PARTICLE_WIDTH,
                        j * PARTICLE_HEIGHT,
                        mBitmap.getPixel(i * PARTICLE_WIDTH, j * PARTICLE_HEIGHT)
                );
            }
        }
    }

    private void initAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(2000);
        mValueAnimator.addUpdateListener(animation -> {
            for (Particle[] particles : mParticles) {
                for (Particle particle : particles) {
                    particle.move();
                }
            }
            invalidate();
        });
    }

    private void initListener() {
        setOnClickListener(view -> {
            if (!mValueAnimator.isRunning()) {
                resetParticle();
                mValueAnimator.start();
            }
        });
    }

    private void resetParticle() {
        for (Particle[] particles : mParticles) {
            for (Particle particle : particles) {
                particle.reset();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int offsetX = getWidth() / 2 - mBitmapWidth / 2;
        int offsetY = getHeight() / 4 - mBitmapHeight / 2;
        if (mValueAnimator.isRunning()) {
            for (Particle[] particles : mParticles) {
                for (Particle particle : particles) {
                    mPaint.setColor(particle.mColor);
                    canvas.drawRect(
                            offsetX + particle.mPx,
                            offsetY + particle.mPy,
                            offsetX + particle.mPx + PARTICLE_WIDTH,
                            offsetY + particle.mPy + PARTICLE_HEIGHT,
                            mPaint
                    );
                }
            }
        } else {
            canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
        }
    }

    private static class Particle {

        private float mOriginalX;
        private float mOriginalY;
        private float mPx;
        private float mPy;
        private float mVx;
        private float mVy;
        private float mAx;
        private float mAy;

        private int mColor;

        private static Random sRandom = new Random();

        private Particle(float px, float py, int color) {
            mOriginalX = px;
            mOriginalY = py;
            mColor = color;
        }

        private void reset() {
            mPx = mOriginalX;
            mPy = mOriginalY;
            mAx = (sRandom.nextFloat() - 0.5f) / 2;
            mAy = (sRandom.nextFloat() - 0.5f) / 2 + 0.98F;
            mVx = (sRandom.nextFloat() - 0.5f) * 10;
            mVy = (sRandom.nextFloat() - 0.5f) * 10 - 15;
        }

        private void move() {
            mPx += mVx;
            mPy += mVy;
            mVx += mAx;
            mVy += mAy;
        }
    }
}
