package com.example.lyricsdownload.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author wanlijun
 * @description
 * @time 2018/2/2 14:19
 */

public class Indicator extends LinearLayout {
    public Indicator(Context context){
        this(context,null);
    }
    public Indicator(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public Indicator(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    @Override
    public void setPressed(boolean pressed) {
        if(pressed && ((View)getParent()).isPressed()){
            return;
        }
        super.setPressed(pressed);
    }
}
