package com.example.martian.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.martian.R;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Canvas mCanvas;
    private SurfaceHolder mHolder;
    private boolean mIsDrawing = true;


    private int x, y;
    private Path mPath;
    private Paint mPaint;

    public MySurfaceView(Context context) {
        super(context);
        iniView();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniView();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniView();
    }

    private void iniView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
//        mPaint.setColor(Color.parseColor("#FF5DA2FF"));
        mPaint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 500);
            Log.d("---","x:"+x+"y:"+y);
            if(x == 1){
                mPath.moveTo(x,y);
            }else{
                mPath.lineTo(x, y);
            }
        }
    }

    private void draw() {
        Log.d("---","---draw---");
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
            Log.d("---","---Exception--e-:"+e.toString());
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
