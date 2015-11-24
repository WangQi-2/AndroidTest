package com.wq.androidtest.activity.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.util.ViewFinder;

/**
 * Created by wangqi on 15/11/24.
 */
public class ZoomActivity extends BaseActivity implements View.OnClickListener {
    ImageView thumb;
    ImageView expanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        setUpViews();
    }

    private void setUpViews() {
        thumb = ViewFinder.find(decorView, R.id.thumb1);
        thumb.setOnClickListener(this);
        expanded = ViewFinder.find(decorView, R.id.expanded_image);
    }

    @Override
    public void onClick(View v) {
        zoomImage();
    }

    private void zoomImage() {

        if (mCurrentAnimator != null) {
            return;
        }

        final Rect startBounds = new Rect();
        // TODO: 15/11/24  rect rectf and those method get this two
        thumb.getGlobalVisibleRect(startBounds);
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        // TODO: 15/11/24 这么精细，真
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumb.setAlpha(0f);
        expanded.setVisibility(View.VISIBLE);
        expanded.setPivotX(0f);
        expanded.setPivotY(0f);

        //// TODO: 15/11/24 这几个常量
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expanded, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expanded, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expanded, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expanded, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;
    }

    int mShortAnimationDuration = 5000;
    Animator mCurrentAnimator;
}
