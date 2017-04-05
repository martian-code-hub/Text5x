package com.example.martian.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author martian on 2017/3/10.
 */

public class NewSingleLineLayout extends LinearLayout {

    private int defultWidth = 50;
    private int width = 0;
    private int leftViewWidth;
    private int rightViewWidth;
    private int leftViewHight;
    private int rightViewHight;


    private TextView leftView;
    private TextView rightView;

    public NewSingleLineLayout(Context context) {
        super(context);
        ini();
    }

    public NewSingleLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public NewSingleLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    private void ini() {
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iniView();
    }

    private void iniView() {
        int size = getChildCount();
        if(size<1){
            return;
        }
        leftView = (TextView) getChildAt(0);
        rightView = (TextView) getChildAt(1);

        leftView.setSingleLine();
        rightView.setSingleLine();
        leftView.addTextChangedListener(new LeftTextWatch());
        rightView.addTextChangedListener(new RightTextWatch());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);


        switch (specMode) {
            // Mode = UNSPECIFIED,AT_MOST时使用提供的默认大小
            case MeasureSpec.UNSPECIFIED:
                width = defultWidth;
                break;
            case MeasureSpec.AT_MOST:
                // Mode = EXACTLY时使用测量的大小
            case MeasureSpec.EXACTLY:
                width = specSize;
                break;
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
        leftViewWidth = leftView.getMeasuredWidth();
        rightViewWidth = rightView.getMeasuredWidth();
        leftViewHight = leftView.getMeasuredHeight();
        rightViewHight = rightView.getMeasuredHeight();

        MarginLayoutParams lp_lv = (MarginLayoutParams)leftView.getLayoutParams();
        int lm_lv = lp_lv.leftMargin;
        int rm_lv = lp_lv.rightMargin;
//        leftViewHight = lp_lv.height;

        MarginLayoutParams lp_rv = (MarginLayoutParams)rightView.getLayoutParams();
        int lm_rv = lp_rv.leftMargin;
        int rm_rv = lp_rv.rightMargin;
//        rightViewHight = lp_rv.height;

        Log.d("---","getPaddingLeft:"+getPaddingLeft()+"getPaddingRight:"+getPaddingRight());
        Log.d("---","lm_lv:"+lm_lv+"rm_lv:"+rm_lv);
        Log.d("---","lm_rv:"+lm_rv+"rm_rv:"+rm_rv);
        Log.d("---","leftViewWidth:"+leftViewWidth+"rightViewWidth:"+rightViewWidth);
        Log.d("---","leftViewHight:"+leftViewHight+"rightViewHight:"+rightViewHight);
//        rightViewWidth = 33;

        int len = getPaddingLeft()+getPaddingRight()+
                   +lm_lv+rm_lv+
                   +lm_rv+rm_rv+
                   leftViewWidth+rightViewWidth;
        Log.d("---","len:"+len+"--width:"+width);

//
       if(len<=width){
           lp_lv.width = leftViewWidth;
       }else{
           lp_lv.width = width-(getPaddingLeft()+getPaddingRight()+
                   +lm_lv+rm_lv+
                   +lm_rv+rm_rv+rightViewWidth);
       }
        leftView.setLayoutParams(lp_lv);



//invalidate();
    }

    private class LeftTextWatch implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            leftViewWidth = s.length();
            requestLayout();
        }
    }

    private class RightTextWatch implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            rightViewWidth = s.length();

            requestLayout();
        }
    }
}
