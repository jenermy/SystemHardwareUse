package com.example.lyricsdownload.design;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.lyricsdownload.MainActivity;
import com.example.lyricsdownload.R;

public class SecretaryActivity extends AppCompatActivity {
    private ListView listView;
    private ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary);
        initView();
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SecretaryActivity.this,0,new Intent(),0);
        smsManager.sendTextMessage("13530300134",null,"哈哈哈",pendingIntent,null);
    }
    private void initView(){
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ScheduleAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    class ScheduleAdapter extends BaseAdapter{
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(SecretaryActivity.this).inflate(R.layout.schedule_item,null,false);

            return view;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

    }
}
