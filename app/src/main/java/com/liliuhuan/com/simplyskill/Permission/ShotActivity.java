package com.liliuhuan.com.simplyskill.Permission;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
/**
 * @author : 李刘欢
 * @date   : 2017-11-27
 * @des    : view剪切  写到图库要权限
 */
public class ShotActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        view = findViewById(R.id.card_view);
        imageView = (ImageView) findViewById(R.id.image);
        findViewById(R.id.sot).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        saveImageToGallery(takeScreenShotAndCropTitle());
//        Toast.makeText(ShotActivity.this,"已截图",Toast.LENGTH_LONG).show();

        imageView.setImageBitmap(shot());
    }
    private Bitmap shot() {
        view.setDrawingCacheEnabled(true);
        try {
            File myCaptureFile = new File("/mnt/sdcard/" + System.currentTimeMillis() + ".jpg");
            //File myCaptureFile = new File("/mnt/sdcard/123.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            Bitmap drawingCache = view.getDrawingCache();
            drawingCache.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(getContentResolver(), myCaptureFile.getAbsolutePath(), myCaptureFile.getName(), null);
            // 最后通知图库更新
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(myCaptureFile)));
            return drawingCache;
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setDrawingCacheEnabled(false);
        return null;
    }

    /**
     * 进行截取屏幕
     * @param
     * @return bitmap
     */
    public  Bitmap takeScreenShotAndCropTitle() {
        Bitmap bitmap=null;
        View view=getWindow().getDecorView();
        // 设置是否可以进行绘图缓存
        view.setDrawingCacheEnabled(true);
        // 如果绘图缓存无法，强制构建绘图缓存
        view.buildDrawingCache();
        // 返回这个缓存视图
        bitmap=view.getDrawingCache();

        // 获取状态栏高度
        Rect frame=new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int stautsHeight=frame.top;
        int width=getWindowManager().getDefaultDisplay().getWidth();
        int height=getWindowManager().getDefaultDisplay().getHeight();

        bitmap=Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height-stautsHeight);
        return bitmap;
    }
    /**
     * 保存图片至相册
     */
    public  boolean saveImageToGallery(Bitmap bmp) {
        String absolutePath = getFilesDir().getAbsolutePath();
        File appDir = new File(absolutePath, "picture_youxuejia");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "截屏文件已保存至SDCard/mcc/currentImage/下", Toast.LENGTH_LONG).show();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
