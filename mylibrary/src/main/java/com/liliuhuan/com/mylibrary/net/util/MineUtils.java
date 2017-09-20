package com.liliuhuan.com.mylibrary.net.util;

/**
 * ${tags}
 * nanfeifei
 * 2017/2/22 13:28
 *
 * @version 3.7.0
 */
public class MineUtils {
  private static MineUtils instance;
  private MineUtils(){}
  public static MineUtils getInstance(){
    if(null==instance){
      instance = new MineUtils();
    }
    return instance;
  }
  /**
   * 根据家长Id查询家长不同状态订单列表
   * @param callBack
   * @return
   */
//  public Call<JSONObject> getLessonsList(int status, int page, int count, IRequestCallBack callBack) {
//    MineService service = RetrofitUtil.getInstance().createService(MineService.class);
//    Call<JSONObject> call = service.getLessonsList(status, page, count);
//    call.enqueue(new WrapperCallBack<JSONObject>(callBack));
//    return call;
//  }


}
