package com.example.liliuhuan.aidlproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liliuhuan.aidlproject.service.MyAidlAdvanceService;

public class AidlAdvanceActivity extends AppCompatActivity {
    private ServiceConnection conn1 = new MyAidlSeviceConnection();
    private SeekBar seekBar;
    private TextView tvShow;
    private Button btnRandom;
    private IMyAidlAdvanceInterface aidlAdvanceInterface;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyAidlAdvanceService.class);
        startService(intent);
        bindService(intent, conn1, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_advance);
        seekBar = (SeekBar) findViewById(R.id.seek_bar1);
        tvShow = (TextView) findViewById(R.id.tv_show1);
        btnRandom = (Button) findViewById(R.id.btn1);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    aidlAdvanceInterface.startDownloading();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn1);
        try {
            aidlAdvanceInterface.unRegisterListener(mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final class MyAidlSeviceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlAdvanceInterface = IMyAidlAdvanceInterface.Stub.asInterface(service);
            try {
                aidlAdvanceInterface.registerListener(mCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        public void onServiceDisconnected(ComponentName name) {
            aidlAdvanceInterface = null;
        }
    }

    private AidlCallback mCallback = new AidlCallback.Stub() {

        @Override
        public void onPreparedStart() throws RemoteException {
            Toast.makeText(AidlAdvanceActivity.this,"开始前",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDownloadProgress(final long progress, final long total) throws RemoteException {
            Log.d("pro=---",(int)((((float)progress/total))*100)+"");
                    seekBar.setProgress((int)((((float)progress/total))*100));
                    tvShow.setText(progress+" / " +total);
        }

        @Override
        public void onDownloadCompletion() throws RemoteException {
            Toast.makeText(AidlAdvanceActivity.this,"下载完成",Toast.LENGTH_LONG).show();
        }
    };
}
