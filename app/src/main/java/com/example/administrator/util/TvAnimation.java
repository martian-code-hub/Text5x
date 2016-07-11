package com.example.administrator.util;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/7/11.
 */
public class TvAnimation extends Animation{


    private int nCenterWidth ;
    private int nCenterHeight ;
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        nCenterWidth = width/2;
        nCenterHeight = height/2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix =    t.getMatrix();
        matrix.preScale(1,1-interpolatedTime,nCenterWidth,nCenterHeight);
//        if (interpolatedTime < 0.8) {
//            matrix.preScale(1+0.625f*interpolatedTime, 1-interpolatedTime/0.8f+0.01f,nCenterWidth,nCenterHeight);
//        }else{
//            matrix.preScale(7.5f*(1-interpolatedTime),0.01f,nCenterWidth,nCenterHeight);
//        }
    }

}
