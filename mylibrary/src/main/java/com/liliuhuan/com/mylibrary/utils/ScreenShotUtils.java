package com.liliuhuan.com.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 李刘欢
 * 2017/4/21
 * 描述:${截屏保存本地}
 */

public class ScreenShotUtils {
    /**
     * 进行截取屏幕
     * @param pActivity
     * @return bitmap
     */
    public static Bitmap takeScreenShotAndCropTitle(Activity pActivity)
    {
        Bitmap bitmap=null;
        View view=pActivity.getWindow().getDecorView();
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


        int width=pActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height=pActivity.getWindowManager().getDefaultDisplay().getHeight();
        Log.d("jiangqq", "状态栏的高度为:"+stautsHeight+"kuan=="+width+"gao=="+height);
        // 根据坐标点和需要的宽和高创建bitmap
        bitmap=Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height-stautsHeight);
        Bitmap bitmap1 = centerSquareScaleBitmap(bitmap, width,height-stautsHeight- UIUtil.dip2px(48));
        return bitmap1;
    }


    /**
     * 截图
     * @param pActivity
     * @return 截图并且保存sdcard成功返回true，否则返回false
     */
    public static boolean shotBitmapAndSaveImage(Activity pActivity) {
       // String absolutePath = pActivity.getFilesDir().getAbsolutePath()+  "/00007.png";
       // Log.d("path====",absolutePath);

        //获取当前程序路径

        //getApplicationContext().getFilesDir().getAbsolutePath();

        //获取该程序的安装包路径

       // String path=getApplicationContext().getPackageResourcePath();

        //获取程序默认数据库路径

       // getApplicationContext().getDatabasePath(s).getAbsolutePath();
       // boolean b = ScreenShotUtils.savePic(pActivity, takeScreenShot(pActivity),absolutePath);

        return  ScreenShotUtils.saveImageToGallery(pActivity,takeScreenShotAndCropTitle(pActivity));
    }

    /**

     * @param bitmap      原图
     * @param edgeLength  希望得到的正方形部分的边长
     * @param
     * @return  缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength, int edgeHeigh)
    {
        if(null == bitmap || edgeLength <= 0 ||edgeHeigh <= 0)
        {
            return  null;
        }

        Bitmap result = bitmap;
        int heightOrg = bitmap.getHeight();
        if (heightOrg > edgeLength){
            int yH = heightOrg-edgeHeigh;
            try{
                Log.d("jiangqq", "剪裁后的高度为:kuan=="+edgeLength+"gao=="+edgeLength);
                result = Bitmap.createBitmap(bitmap, 0, yH, edgeLength, edgeHeigh);
            }
            catch(Exception e){
                return null;
            }
        }

        return result;
    }
    /**
     * 保存图片至相册
     */
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        //context.getApplicationContext()
        //String path=context.getApplicationContext().getPackageResourcePath();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        // 首先保存图片
       // File appDir = new File(Environment.getExternalStorageState(), "mypicure");
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
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 保存图片至相册
     */
    public static File saveImageToGalleryReturnFile(Context context, Bitmap bmp) {
        //context.getApplicationContext()
        //String path=context.getApplicationContext().getPackageResourcePath();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        // 首先保存图片
        // File appDir = new File(Environment.getExternalStorageState(), "mypicure");
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
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    /**
     * 保存图片至相册
     */
    public static File saveImage(Context context, Bitmap bmp) {
        //context.getApplicationContext()
        //String path=context.getApplicationContext().getPackageResourcePath();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        // 首先保存图片
        // File appDir = new File(Environment.getExternalStorageState(), "mypicure");
        File appDir = new File(absolutePath, "picture_youxuejia");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "video_image.png";
        File file = new File(appDir, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    /**
     * 保存图片至相册
     */
    public static File saveImageIdcard(Context context, Bitmap bmp) {
        //context.getApplicationContext()
        //String path=context.getApplicationContext().getPackageResourcePath();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        // 首先保存图片
        // File appDir = new File(Environment.getExternalStorageState(), "mypicure");
        File appDir = new File(absolutePath, "idcard");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        if (file.exists()) file.delete();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
           // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
