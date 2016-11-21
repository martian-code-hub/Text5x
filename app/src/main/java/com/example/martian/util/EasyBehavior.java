package com.example.martian.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by yangpei on 2016/11/15.
 */

public class EasyBehavior extends CoordinatorLayout.Behavior {

    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setX(dependency.getX()+100);
        child.setY(dependency.getY()+100);
        return true;


    }
}
