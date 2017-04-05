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

public class SingleLineLayout extends RelativeLayout {

    private int defultWidth = 50;
    private int width = 0;
    private int leftViewWidth;
    private int rightViewWidth;
    private int leftViewHight;
    private int rightViewHight;


    private TextView leftView;
    private TextView rightView;

    public SingleLineLayout(Context context) {
        super(context);
    }

    public SingleLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        int len = getPaddingLeft()+getPaddingRight()+
                   +lm_lv+rm_lv+
                   +lm_rv+rm_rv+
                   leftViewWidth+rightViewWidth;
        Log.d("---","len:"+len+"--width:"+width);
//       if(len<=width){
//           leftView.layout(getPaddingLeft()+lm_lv,0,getPaddingLeft()+lm_lv+leftViewWidth,leftViewHight);
//           rightView.layout(getPaddingLeft()+lm_lv+leftViewWidth+rm_lv+lm_rv,0,getPaddingLeft()+lm_lv+leftViewWidth+rm_lv+lm_rv+rightViewWidth,rightViewHight);
//       }else{
//           leftView.layout(getPaddingLeft()+lm_lv,0,width-(getPaddingRight()+rm_rv+rightViewWidth+lm_rv+rm_lv),leftViewHight);
//           rightView.layout(width-(getPaddingRight()+rm_rv+rightViewWidth),0,width-(getPaddingRight()+rm_rv),rightViewHight);
//       }

        if(len<=width){
            lp_lv.width = leftViewWidth;
        }else{
            lp_lv.width = width-(getPaddingLeft()+getPaddingRight()+rightViewWidth+lm_lv+rm_lv+lm_rv+rm_rv);
        }
        leftView.setLayoutParams(lp_lv);
        super.onLayout( changed,  l,  t,  r,  b);
    }
}
