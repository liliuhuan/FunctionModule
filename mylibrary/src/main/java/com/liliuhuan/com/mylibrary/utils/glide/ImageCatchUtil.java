package com.liliuhuan.com.mylibrary.utils.glide;

import android.os.Looper;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.liliuhuan.com.mylibrary.LibraryApp;

import java.io.File;
import java.math.BigDecimal;

/**
 * Glide缓存工具类
 * nanfeifei
 * 2017/2/10 10:14
 *
 * @version 3.7.0
 */
public class ImageCatchUtil {
  private static ImageCatchUtil inst;
  private final String ImageExternalCatchDir = LibraryApp.getInstance().getExternalCacheDir()
      +File.separator+ GlideConfiguration.GLIDE_CARCH_DIR;

  public static ImageCatchUtil getInstance() {
    if (inst == null) {
      inst = new ImageCatchUtil();
    }
    return inst;
  }

  /**
   * 清除图片磁盘缓存
   */
  public void clearImageDiskCache() {
    try {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            Glide.get(LibraryApp.getInstance()).clearDiskCache();
          }
        }).start();
      } else {
        Glide.get(LibraryApp.getInstance()).clearDiskCache();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 清除图片内存缓存
   */
  public void clearImageMemoryCache() {
    try {
      if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
        Glide.get(LibraryApp.getInstance()).clearMemory();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * 清除图片所有缓存
   */
  public void clearImageAllCache() {
    clearImageDiskCache();
    clearImageMemoryCache();
    deleteFolderFile(ImageExternalCatchDir, true);
  }

  /**
   * 获取Glide造成的缓存大小
   *
   * @return CacheSize
   */
  public String getCacheSize() {
    try {
      File cacheDir = LibraryApp.getInstance().getCacheDir();
      return getFormatSize(getFolderSize(new File(LibraryApp.getInstance().getCacheDir()
          +File.separator +GlideConfiguration.GLIDE_CARCH_DIR)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取指定文件夹内所有文件大小的和
   *
   * @param file file
   * @return size
   * @throws Exception
   */
  public long getFolderSize(File file) throws Exception {
    long size = 0;
    try {
      File[] fileList = file.listFiles();
      for (File aFileList : fileList) {
        if (aFileList.isDirectory()) {
          size = size + getFolderSize(aFileList);
        } else {
          size = size + aFileList.length();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return size;
  }

  /**
   * 删除指定目录下的文件，这里用于缓存的删除
   *
   * @param filePath       filePath
   * @param deleteThisPath deleteThisPath
   */
  public void deleteFolderFile(String filePath, boolean deleteThisPath) {
    if (!TextUtils.isEmpty(filePath)) {
      try {
        File file = new File(filePath);
        if (file.isDirectory()) {
          File files[] = file.listFiles();
          for (File file1 : files) {
            deleteFolderFile(file1.getAbsolutePath(), true);
          }
        }
        if (deleteThisPath) {
          if (!file.isDirectory()) {
            file.delete();
          } else {
            if (file.listFiles().length == 0) {
              file.delete();
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 格式化单位
   *
   * @param size size
   * @return size
   */
  public static String getFormatSize(double size) {

    double kiloByte = size / 1024;
    if (kiloByte < 1) {
      return size + "";
    }

    double megaByte = kiloByte / 1024;
    if (megaByte < 1) {
      BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
      return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
    }

    double gigaByte = megaByte / 1024;
    if (gigaByte < 1) {
      BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
      return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
    }

    double teraBytes = gigaByte / 1024;
    if (teraBytes < 1) {
      BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
      return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
    }
    BigDecimal result4 = new BigDecimal(teraBytes);

    return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
  }
}
