package com.example.martian;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangpei on 2016/11/26.
 */

public class MyView extends View {


    int id;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a =  context.obtainStyledAttributes(attrs,R.styleable.MyAttr);
        int size = a.getIndexCount();
//        for (int i = 0; i < size; i++) {
//            int type = a.getIndex(i);
//            if(type == R.styleable.MyAttr_target){
//                id =    a.getResourceId(type,-1);
//                break;
//            }
//        }
        id =   a.getResourceId(R.styleable.MyAttr_target,-1);
//       id =   a.getInt(R.styleable.MyAttr_target,-1);
        a.recycle();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getId(){
        return id;
    }
}
