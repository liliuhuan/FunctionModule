package com.example.liliuhuan.aidlproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.liliuhuan.aidlproject.service.MyAidlService;

public class AidlActivity extends AppCompatActivity {

    private ServiceConnection conn = new MySeviceConnection();
    private IMyAidlInterface aidlInterface;
    private SeekBar seekBar;
    private TextView tvShow;
    private Button btnRandom;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyAidlService.class);
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnRandom = (Button) findViewById(R.id.btn);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String randomNumber = aidlInterface.randomNumber();
                    tvShow.setText(randomNumber);
                    seekBar.setProgress(Integer.valueOf(randomNumber));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    public final class MySeviceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName name) {
            aidlInterface = null;
        }
    }
}
