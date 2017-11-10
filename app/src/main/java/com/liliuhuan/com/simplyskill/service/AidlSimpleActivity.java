package com.liliuhuan.com.simplyskill.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liliuhuan.com.mylibrary.utils.ToastUtils;
import com.liliuhuan.com.simplyskill.IPerson;
import com.liliuhuan.com.simplyskill.R;

public class AidlSimpleActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection conn = new PersonConnection();
    private IPerson iPerson;
    private EditText etNumber;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_simple);

        //绑定远程Service
        Intent service = new Intent(this, AidlSimpleService.class);
        startService(service);
        bindService(service, conn, BIND_AUTO_CREATE);

        etNumber = (EditText) findViewById(R.id.et_number);
        Button btnQuery = (Button) findViewById(R.id.btn_query);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = etNumber.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            int integer = Integer.valueOf(s);
            if (iPerson != null) try {
                String name = iPerson.getName(integer);
                tvShow.setText(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtils.showToast("请输入数字");
        }
    }

    private class PersonConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iPerson = IPerson.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iPerson = null;
        }
    }
}
