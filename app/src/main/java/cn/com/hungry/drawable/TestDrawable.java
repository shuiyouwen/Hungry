package cn.com.hungry.drawable;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Property;

/**
 * Created by Shui on 2018/4/24.
 */

public class TestDrawable extends Drawable implements Drawable.Callback, Animatable {

    private final Paint mPaint;
    private Rect mBounds;
    private int mX;//圆形x坐标

    private static Property<TestDrawable, Integer> X_PROPERTY = new Property<TestDrawable, Integer>(Integer.class, "mX") {
        @Override
        public Integer get(TestDrawable object) {
            return object.getX();
        }

        @Override
        public void set(TestDrawable object, Integer value) {
            object.setX(value);
        }
    };
    private ObjectAnimator mObjectAnimator;

    public TestDrawable() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }


    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int radius = Math.min((mBounds.bottom - mBounds.top), (mBounds.right - mBounds.left)) / 2;
        canvas.drawCircle(mX, (mBounds.bottom - mBounds.top) / 2, radius, mPaint);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mBounds = bounds;
        animation();
    }

    private void animation() {
        Log.d("TestDrawable", "mBounds.left:" + mBounds.left);
        Log.d("TestDrawable", "mBounds.right:" + mBounds.right);

        mObjectAnimator = ObjectAnimator.ofInt(this, X_PROPERTY, mBounds.left, mBounds.right);
        mObjectAnimator.setDuration(3000);
        start();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {

    }

    @Override
    public void start() {
        if (mObjectAnimator != null && !mObjectAnimator.isRunning()) {
            mObjectAnimator.start();
        }
    }

    @Override
    public void stop() {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            mObjectAnimator.cancel();
        }
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
