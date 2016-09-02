package com.example.administrator.recordplayground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016/9/1.
 */
public class RecordView extends View {

    private static final String TAG = "RecordView";
    private int width;
    private int height;
    private int space;//每个间隔距离
    private int quarter;
    private Paint secendPaint;
    private int secend=0;
    private int preview;
    private int s;


    public void setSecend(int secend) {
        this.s = secend;
        invalidate();
    }

    public RecordView(Context context) {
        super(context);
        init();
    }

    public RecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public RecordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public RecordView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
    }
    public static int getDefaultSize(int size,int measureSpec){
        int result=size;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result=size;
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
        }
        return result;
    }

    private void init() {
        secend=0;
        s=0;
        initPaint();
    }

    private void initPaint() {
        secendPaint=new Paint();
        secendPaint.setColor(R.color.colorAccent);
        secendPaint.setAntiAlias(true);
        secendPaint.setStrokeWidth(6);
        secendPaint.setTextSize(30);
        Log.e("s",s+" ");
    }


    @Override
    protected void onDraw(Canvas canvas) {
       // Log.e(TAG,"onDraw");
        width=getWidth();
        height=getHeight();
        space=width/5;
        quarter=space/4;
        int quarter40=space/40;
        int startX=0;
        canvas.drawLine(0,0,width,0,secendPaint);
       // Log.e("此时此刻",s*quarter40+" ");
        int lenght=((s*quarter40)%space);
        for (int i=0;i<=5+lenght;++i){
            startX=space*i;
            int tempx=startX-quarter40*s;
            canvas.drawLine(tempx,0,tempx,100,secendPaint);
            for (int l = 0; l < 3; l++) {
                int temp = tempx + quarter * (l + 1);
                canvas.drawLine(temp, 0, temp, 50, secendPaint);
            }
//            int l=s%40;
            canvas.drawText(String.format("%01d:%01d",i/60,i),tempx+20,100,secendPaint);
        }
        s=0;
        super.onDraw(canvas);
    }
}
