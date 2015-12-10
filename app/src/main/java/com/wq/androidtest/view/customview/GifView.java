package com.wq.androidtest.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/12/1.
 */
public class GifView extends ImageView {

    private Movie mMovie;
    private long mMovieStart;


    public GifView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GifView, defStyleAttr, 0);

        int srcid = a.getResourceId(R.styleable.GifView_src2, 0);

        if (srcid > 0) {
            mMovie = Movie.decodeStream(context.getResources().openRawResource(srcid));
        }

    }

    Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long now = SystemClock.currentThreadTimeMillis();

        if (mMovie == null) {
            return;
        }

        if (mMovieStart == 0) {
            mMovieStart = now;
        }

        int dur = mMovie.duration();

        if (dur == 0) {
            dur = 2000;
        }

        int reltime = (int) ((now - mMovieStart) % dur);

        mMovie.setTime(reltime);


//        final float scale = Math.min((float) getWidth() / mMovie.width(), (float) getHeight() / mMovie.height());
//
//        canvas.scale(scale, scale);
//        canvas.translate(((float) getWidth() / scale - (float) mMovie.width()) / 2f,
//                ((float) getHeight() / scale - (float) mMovie.height()) / 2f);

//        mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight() - mMovie.height(), mPaint);
        mMovie.draw(canvas,0,0,mPaint);
        invalidate();
    }

    int width = 400;
    int height = 400;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
