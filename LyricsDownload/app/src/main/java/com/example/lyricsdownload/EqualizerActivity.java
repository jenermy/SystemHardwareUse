package com.example.lyricsdownload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.Virtualizer;
import android.media.audiofx.Visualizer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EqualizerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Visualizer visualizer;
    private Equalizer equalizer;
    private BassBoost bassBoost;
    private Virtualizer virtualizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mediaPlayer = new MediaPlayer();
        if(ActivityCompat.checkSelfPermission(EqualizerActivity.this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EqualizerActivity.this,new String[]{Manifest.permission.RECORD_AUDIO},101);
        }else {
            visualizer = new Visualizer(mediaPlayer.getAudioSessionId()); //定义频谱
            equalizer = new Equalizer(1, mediaPlayer.getAudioSessionId()); //定义均衡器
            bassBoost = new BassBoost(1, mediaPlayer.getAudioSessionId()); // 定义重低音控制器
            virtualizer = new Virtualizer(1, mediaPlayer.getAudioSessionId()); //定义环绕音
            initValue();
        }

    }
    private  void initValue(){
        //设置频谱采集精度，每次采样的范围或内容长度
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        //设置监听器，用于处理返回的波形数据
        //四个参数：监听者，采样率（每秒从连续信号中提取并组成离散信号的采样个数），是否采集波形数据，是否采集频域数据
        visualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int i) {

            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int i) {

            }
        },Visualizer.getMaxCaptureRate()/2,true,false);
        //以下方法调用后才会进行波形数据采集
        visualizer.setEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 101 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            visualizer = new Visualizer(mediaPlayer.getAudioSessionId()); //定义频谱
            equalizer = new Equalizer(1, mediaPlayer.getAudioSessionId()); //定义均衡器
            bassBoost = new BassBoost(1, mediaPlayer.getAudioSessionId()); // 定义重低音控制器
            virtualizer = new Virtualizer(1, mediaPlayer.getAudioSessionId()); //定义环绕音
            initValue();
        }else{
            Toast.makeText(EqualizerActivity.this,"您拒绝了频谱的权限，无法查看频谱信息",Toast.LENGTH_LONG).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
