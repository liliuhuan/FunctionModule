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
import com.liliuhuan.com.simplyskill.ISalary;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.entity.Person;
import com.liliuhuan.com.simplyskill.entity.Salary;

public class AidlMutilActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNumber;
    private TextView tvShow;
    private ServiceConnection conn = new QueryMutilSalary();
    private ISalary iSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_mutil);

        //绑定远程Service
        Intent service = new Intent(this, AidlMutilService.class);
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
            if (iSalary != null) try {
                Salary iSalaryPerson = iSalary.getPerson(new Person(integer, "Jay"));
                if (iSalaryPerson != null)
                tvShow.setText(iSalaryPerson.toString());
                else tvShow.setText("输入的数字有误");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtils.showToast("请输入数字");
        }
    }

    private class QueryMutilSalary implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iSalary = ISalary.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iSalary = null;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(conn);
    }
}
