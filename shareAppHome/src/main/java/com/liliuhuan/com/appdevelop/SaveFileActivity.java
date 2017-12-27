package com.liliuhuan.com.appdevelop;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * author:llh
 * date:
 * tag: 当用户卸载应用程序,Android系统删除 以下几点:
        你保存在内部存储的所有文件
        所有文件保存在外部存储使用 getExternalFilesDir() 。
 */
public class SaveFileActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_save_file;
    }

    @Override
    protected void initData() {
        //init  data
    }

    @Override
    protected void initView() {
        //bind view
        findViewById(R.id.inner).setOnClickListener(this);
        findViewById(R.id.out).setOnClickListener(this);
    }

    @Override
    protected void requestNet() {
        //http

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inner:saveInner();break;
            case R.id.out:saveOut();break;
        }
    }

    private void saveOut() {

    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
          //  Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
           // Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
    private void saveInner() {
        //内部存储目录
        String filename = "myfile";
        File file = new File(getFilesDir(), filename);
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //内部缓存
       // getTempFile()
    }
    public File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }
}


