package com.example.lyricsdownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class VolumeActivity extends AppCompatActivity{
    private TextView upTv,downTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        upTv = (TextView)findViewById(R.id.upTv);
        downTv = (TextView)findViewById(R.id.downTv);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            upTv.setText("樱花树下的约定");
            downTv.setText("");
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            downTv.setText("空空如也");
            upTv.setText("");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
