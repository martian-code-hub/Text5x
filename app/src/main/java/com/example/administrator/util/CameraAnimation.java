package com.example.administrator.util;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/7/11.
 */
public class CameraAnimation extends Animation{


    private int nCenterWidth ;
    private int nCenterHeight ;

    private Camera camera;
    private int mRotateY = 360;

    private int halfParentWidth;
    private int halfParentHeight;
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        nCenterWidth = width/2;
        nCenterHeight = height/2;

        halfParentWidth = parentWidth/2;
        halfParentHeight = parentHeight/2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix =    t.getMatrix();

        camera.save();
        camera.rotateY(mRotateY * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-nCenterWidth, -nCenterHeight);
        matrix.postTranslate(nCenterWidth, nCenterHeight);
    }

}
