package com.liliuhuan.com.simplyskill.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.R;

public class IPCActivity extends AppCompatActivity implements View.OnClickListener {


    private IBinder mIBinder;
    private EditText etNumber;
    private TextView tvShow;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBinder = service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);

        //绑定远程Service
        Intent service = new Intent(this, IPCService.class);
        startService(service);
        bindService(service, conn, BIND_AUTO_CREATE);

        etNumber = (EditText) findViewById(R.id.et_number);
        Button btnQuery = (Button) findViewById(R.id.btn_query);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int num = Integer.parseInt(etNumber.getText().toString());
        if (mIBinder == null) {
            Toast.makeText(this, "未连接服务端或服务端被异常杀死", Toast.LENGTH_SHORT).show();
        } else {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            String _result = null;
            try {
                _data.writeInterfaceToken(IPCService.DESCRIPTOR);
                _data.writeInt(num);
                mIBinder.transact(0x001, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readString();
                tvShow.setText(_result);
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }
}
