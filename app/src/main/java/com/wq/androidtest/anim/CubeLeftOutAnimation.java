package com.wq.androidtest.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by wangqi on 15/10/2.
 */
public class CubeLeftOutAnimation extends Animation {
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
        float rotate = (-finalDegree * interpolatedTime);
        camera.save();
        camera.translate(width - interpolatedTime * width, 0, 0);
        // TODO: 15/10/8 环绕Y轴旋转
        camera.rotateY(rotate);
        camera.getMatrix(matrix);
        camera.restore();

//        public static final int MSCALE_X = 0;   //!< use with getValues/setValues
//        public static final int MSKEW_X  = 1;   //!< use with getValues/setValues
//        public static final int MTRANS_X = 2;   //!< use with getValues/setValues
//        public static final int MSKEW_Y  = 3;   //!< use with getValues/setValues
//        public static final int MSCALE_Y = 4;   //!< use with getValues/setValues
//        public static final int MTRANS_Y = 5;   //!< use with getValues/setValues
//        public static final int MPERSP_0 = 6;   //!< use with getValues/setValues
//        public static final int MPERSP_1 = 7;   //!< use with getValues/setValues
//        public static final int MPERSP_2 = 8;   //!< use with getValues/setValues
        // TODO: 15/10/8 后乘是行转换
        matrix.postTranslate(0, height / 2);
        // TODO: 15/10/8 前乘是列转换
        matrix.preTranslate(-width, -height / 2);

        t.getMatrix().postConcat(matrix);
    }
}
