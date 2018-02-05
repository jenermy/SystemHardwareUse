package com.example.lyricsdownload;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wanlijun
 * @description 自定义频谱波形图
 * @time 2018/1/31 18:34
 */

public class VisualizerView extends View {
    private Paint mPaint;
    private byte[] bytes; //保存波形抽样点的值
    private byte type = 0;
    private Rect rect;
    private float[] point;
    public VisualizerView(Context context){
        this(context,null);
    }
    public VisualizerView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public VisualizerView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init();
    }
    private void init(){
        bytes = null;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1f);
        rect = new Rect();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //当用户触碰该组件时切换波形类型
//        if(event.getAction() != MotionEvent.ACTION_DOWN){
//            return false;
//        }
//        type++;
//        if(type >= 3){
//            type = 0;
//        }
//        return true;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bytes == null){
            return;
        }
        canvas.drawColor(Color.parseColor("#22000000"));
        rect.set(0,0,getWidth(),getHeight());
        if(point == null ||point.length <bytes.length * 4){
            point = new float[bytes.length * 4];
        }
        for (int i=0;i<bytes.length-1;i++){
            point[i * 4] = rect.width() * i / (bytes.length - 1);
            point[i*4+1]=(rect.height()/2)+((byte)(bytes[i]+128))*128/(rect.height()/2);
            point[i*4+2]=rect.width() *(i+1)/(bytes.length -1);
            point[i*4+3]=(rect.height()/2)+((byte)(bytes[i+1]+128))*128/(rect.height()/2);
        }
        canvas.drawLines(point,mPaint);
    }
}
