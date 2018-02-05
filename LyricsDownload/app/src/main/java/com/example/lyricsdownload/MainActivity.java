package com.example.lyricsdownload;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyricsdownload.design.SecretaryActivity;

public class MainActivity extends AppCompatActivity {
    private TextView lyricsTv;
    private AsynDownload asynDownload;
    private Button mySecretaryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        asynDownload = new AsynDownload();
        //括号里传的参数为doInBackground所需要的参数
        asynDownload.execute("http://geci.me/api/lyric/","青春纪念册");
        checkPermission();
    }
    private void initView(){
        lyricsTv = (TextView)findViewById(R.id.lyricsTv);
        mySecretaryBtn = (Button)findViewById(R.id.mySecretaryBtn);
        mySecretaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecretaryActivity.class));
            }
        });
    }
    //需要动态申请，不然拦截不到短信，奇怪的是，动态申请竟然没有弹窗
    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_PHONE_STATE},101);
        }
        Log.i("wanlijun","123");
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneCallListener(),PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.download:
                startActivity(new Intent(this,VolumeActivity.class));
                break;
            case R.id.equlizer:
                startActivity(new Intent(this,EqualizerActivity.class));
                break;
            case R.id.sensor:
                startActivity(new Intent(this,SensorActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class AsynDownload extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            Log.i("wanlijun",strings[0]);
            Log.i("wanlijun",strings[1]);
            return "空空如也";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("wanlijun",s);
            super.onPostExecute(s);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 101 && grantResults.length > 0){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"我长得那么可爱，你怎么忍心拒绝我",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    class PhoneCallListener extends PhoneStateListener{

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.i("wanlijun","incomingNumber="+incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    //没有手机号码
                    Log.i("wanlijun","空闲");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.i("wanlijun","通话中");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("wanlijun","振铃");
                    break;
            }
        }
    }
}
