package com.wq.androidtest.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/10/2.
 */
public class MatrixAnimation extends Animation {
    Camera camera;
    Matrix matrix;
    int width;
    int height;
    int scale = 2;
    int T = 400;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
        matrix = new Matrix();
        this.width = width;
        this.height = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        camera.save();
        camera.translate(width - interpolatedTime * width, 0, 0);
        camera.rotateY(interpolatedTime * 60);
        camera.getMatrix(matrix);
        Logger.e(matrix.toString());
        camera.restore();
//        matrix.preScale(1,1+interpolatedTime);
//        matrix.preTranslate(540, 0);
        t.getMatrix().postConcat(matrix);
    }
}
