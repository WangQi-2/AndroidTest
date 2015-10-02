package com.wq.androidtest.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by wangqi on 15/10/2.
 */
public class CubeRightInAnimation extends Animation{
    Camera camera;
    Matrix matrix;
    int width;
    int height;
    int finalDegree = 90;

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

        float rotate = (finalDegree - finalDegree * interpolatedTime);
        camera.save();
        camera.translate(-width * interpolatedTime, 0, 0);
        camera.rotateY(rotate);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.postTranslate(width, height / 2);
        matrix.preTranslate(0, -height / 2);
        t.getMatrix().postConcat(matrix);

    }
}
