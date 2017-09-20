package com.liliuhuan.com.mylibrary.net.util;

import android.text.TextUtils;

import com.liliuhuan.com.mylibrary.net.callback.IRequestCallBack;
import com.liliuhuan.com.mylibrary.net.callback.WrapperCallBack;
import com.liliuhuan.com.mylibrary.net.retrofit.RetrofitUtil;
import com.liliuhuan.com.mylibrary.net.service.UploadService;
import com.liliuhuan.com.mylibrary.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 上传文件
 * nanfeifei
 * 2017/2/27 15:19
 *
 * @version 3.7.0
 */
public class UploadUtils {
  private static UploadUtils instance;
  private UploadUtils(){}
  public static UploadUtils getInstance(){
    if(null==instance){
      instance = new UploadUtils();
    }
    return instance;
  }
  /**
   * 单文件上传
   * @param uploadCallBack
   */
  public Call<ResponseBody> uploadFile(String filePath, final UploadCallBack uploadCallBack) {
    File file = new File(filePath);
    RequestBody requestFile =
        RequestBody.create(MediaType.parse("multipart/form-data"), file);
    MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
    String descriptionString = "file";
    RequestBody description =
        RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
    UploadService service = RetrofitUtil.getInstance().createService(UploadService.class);
    Call<ResponseBody> call = service.uploadFile(description, body);
    call.enqueue(new WrapperCallBack<ResponseBody>(new IRequestCallBack() {
      @Override public void onStartLoading() {

      }

      @Override public void onSuccess(String result) {
 //       List<String> list = new ArrayList(JSONObject.parseArray(result, String.class));
//        if(list == null||list.isEmpty()){
//          uploadCallBack.uploadFailure("上传失败");
//          return;
//        }
//        uploadCallBack.uploadSucess(list);
      }

      @Override public void onError(String error) {
        uploadCallBack.uploadFailure(error);
      }
    }));
    return call;
  }
  /**
   * 多文件上传
   * @param uploadCallBack
   */
  public Call<String> uploadFiles(List<String> filePathList, final UploadCallBack uploadCallBack) {
    if(filePathList==null){
      ToastUtils.showToast("请选择要上传的图片");
      return null;
    }
    Map<String, RequestBody> params = new HashMap<>();
    for(int i = 0; i<filePathList.size(); i++){
      if(!TextUtils.isEmpty(filePathList.get(i))&&!"add".equals(filePathList.get(i))){
        File file = new File(filePathList.get(i));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        params.put("file\"; filename=\""+i+ file.getName(), requestBody);
      }
    }

    UploadService service = RetrofitUtil.getInstance().createService(UploadService.class);
    Call<String> call = service.uploadFiles(params);
    call.enqueue(new WrapperCallBack<String>(new IRequestCallBack() {
      @Override public void onStartLoading() {

      }

      @Override public void onSuccess(String result) {
//        List<String> list = new ArrayList(JSONObject.parseArray(result, String.class));
//        if(list == null||list.isEmpty()){
//          uploadCallBack.uploadFailure("上传失败");
//          return;
//        }
//        uploadCallBack.uploadSucess(list);
      }

      @Override public void onError(String error) {
        uploadCallBack.uploadFailure(error);
      }
    }));
    return call;
  }
  public interface UploadCallBack{
    void uploadSucess(List<String> list);
    void uploadFailure(String error);
  }
}
