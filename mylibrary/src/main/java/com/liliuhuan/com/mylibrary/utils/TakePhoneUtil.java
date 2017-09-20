package com.liliuhuan.com.mylibrary.utils;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;

import java.io.File;

/**
 * 选择图片工具管理类
 * nanfeifei
 * 2017/2/24 16:21
 *
 * @version 3.7.0
 */
public class TakePhoneUtil {
  private String imagePath = "/Android/data/com.bolooo.studyhometeacher/cache/";
  private static TakePhoneUtil instance;
  private TakePhoneUtil(){}
  public static TakePhoneUtil getInstance(){
    if(null==instance){
      instance = new TakePhoneUtil();
    }
    return instance;
  }
  /**
   * 从相机获取图片并裁剪
   */
  public void onPickFromCaptureWithCrop(TakePhoto takePhoto){
    takePhoto.onEnableCompress(getCompressOptions(),true);
    takePhoto.onPickFromCaptureWithCrop(getImageUri(),getCropOptions());
  }
  /**
   * 从相册中获取图片并裁剪
   */
  public void onPickFromGalleryWithCrop(TakePhoto takePhoto){
    takePhoto.onEnableCompress(getCompressOptions(),true);
    takePhoto.onPickFromGalleryWithCrop(getImageUri(),getCropOptions());
  }
  /**
   * 从相册中获取图片（不裁剪）
   */
  public void onPickFromGallery(TakePhoto takePhoto){
    takePhoto.onEnableCompress(getCompressOptions(),true);
    takePhoto.onPickFromGallery();
  }
  /**
   * 从相机获取图片(不裁剪)
   */
  public void onPickFromCapture(TakePhoto takePhoto){
    takePhoto.onEnableCompress(getCompressOptions(),true);
    takePhoto.onPickFromCapture(getImageUri());
  };
  /**
   * 图片多选
   * @param limit 最多选择图片张数的限制
   **/
  public void onPickMultiple(TakePhoto takePhoto, int limit){
    takePhoto.onEnableCompress(getCompressOptions(),true);
    takePhoto.onPickMultiple(limit);
  }

  /**
   * 获得裁剪或压缩后的图片保存地址
   * @return
   */
  public Uri getImageUri(){
    File file=new File(
        Environment.getExternalStorageDirectory(), imagePath+System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists())file.getParentFile().mkdirs();
    Uri imageUri = Uri.fromFile(file);
    return imageUri;
  }
  /**
   * 获得图片裁剪设置
   * @return
   */
  public CropOptions getCropOptions(){
    CropOptions.Builder builder=new CropOptions.Builder();
    builder.setAspectX(1).setAspectY(1);
    builder.setWithOwnCrop(false);
    return builder.create();
  }

  /**
   * 获得图片压缩设置
   * @return
   */
  public CompressConfig getCompressOptions(){
    CompressConfig compressConfig=new CompressConfig.Builder()
        .setMaxSize(200*1024)
        .setMaxPixel(800)
        .create();
    return compressConfig;
  }
}
