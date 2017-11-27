package com.liliuhuan.com.simplyskill.Permission;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class PermissionsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_permissions;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        String camera = Manifest.permission.READ_EXTERNAL_STORAGE;
        RxPermissions rxPermissions = new RxPermissions(this);
        switch (view.getId()) {
            case R.id.button1:
                rxPermissions.request(camera).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) { // 在android 6.0之前会默认返回true
                            // 已经获取权限
                            Toast.makeText(PermissionsActivity.this, "已经授权", Toast.LENGTH_SHORT).show();
                        } else {
                            // 未获取权限
                            Toast.makeText(PermissionsActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.button2:
                startActivity(new Intent(this,ReadContractActivity.class));
                break;
            case R.id.button3:
                rxPermissions.request(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) { // 在android 6.0之前会默认返回true
                                    // 已经获取权限
                                    Toast.makeText(PermissionsActivity.this, "已经授权", Toast.LENGTH_SHORT).show();
                                } else {
                                    // 未获取权限
                                    Toast.makeText(PermissionsActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.button4:
                rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                        .subscribe(new Consumer<Permission>() {
                            @Override public void accept(Permission permission) throws Exception {
                                if (permission.name.equals(Manifest.permission.CAMERA)){
                                    if (permission.granted) {
                                        Toast.makeText(PermissionsActivity.this, "相机权限已经授权", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //  未获取权限
                                        Toast.makeText(PermissionsActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                                    }

                                }else {

                                }
                            }
                        });
                break;
        }
    }
}
