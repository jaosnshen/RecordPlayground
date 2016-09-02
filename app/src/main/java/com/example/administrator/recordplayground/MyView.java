package com.example.administrator.recordplayground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.audiofx.LoudnessEnhancer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by chenhangye on 16/9/4.
 */
public class MyView extends SurfaceView {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private int width;
    private int height;
    private int space;//每个间隔距离
    private int quarter;
    private Paint secendPaint;
    private int s;

    public MyView(Context context) {
        super(context);
        initView();
    }

    public void setSecend(int s) {
        this.s = s;
        invalidate();

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView() {
        mHolder=getHolder();
//        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        secendPaint=new Paint();
        secendPaint.setColor(R.color.colorAccent);
        secendPaint.setAntiAlias(true);
        secendPaint.setStrokeWidth(6);
        secendPaint.setTextSize(30);
    }

//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        mIsDrawing=true;
//        new Thread(this).start();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//        Log.e("大小",width+"  "+getHeight()+" ");
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//       mIsDrawing=false;
//    }
//
//
//    @Override
//    public void run() {
//        while(mIsDrawing){
//            Log.e("run","绘制");
//            draw();
//        }
//    }
//
//    private void draw() {
//        try {
//
//
//        }catch (Exception e) {
//
//        }finally {
//            if (mCanvas!=null){
//                mHolder.unlockCanvasAndPost(mCanvas);
//            }
//        }
//
//    }

    @Override
    protected void onDraw(Canvas mCanvas) {
        width=getWidth();
//        height=getHeight();
       // mCanvas = mHolder.lockCanvas();
        space=width/5;
        quarter=space/4;
        int quarter40=space/20;
        int startX=0;
        mCanvas.drawLine(0,0,width,0,secendPaint);
        // Log.e("此时此刻",s*quarter40+" ");
        int lenght=((s*quarter40)%space);
        for (int i=0;i<=5+lenght;++i){
            startX=space*i;
            int tempx=startX-quarter40*s;
            mCanvas.drawLine(tempx,0,tempx,100,secendPaint);
            for (int l = 0; l < 3; l++) {
                int temp = tempx + quarter * (l + 1);
                mCanvas.drawLine(temp, 0, temp, 50, secendPaint);
            }
            mCanvas.drawText(String.format("%01d:%01d",i/60,i),tempx+20,100,secendPaint);
        }
        s=0;
        super.onDraw(mCanvas);
    }
}
