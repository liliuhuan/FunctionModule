package com.liliuhuan.com.mylibrary.net.service;

/**
 * 我的页面接口管理
 * nanfeifei
 * 2017/2/22 13:22
 *
 * @version 3.7.0
 */
public interface MineService {
//  /**
//   * 我的课程订单
//   * @param status
//   * @return
//   */
//  @GET("")
//  Call<JSONObject> getLessonsList(@Query("status") int status, @Query("page") int page, @Query("count") int count);
//  /**
//   * 扫码上课
//   * @param frequencyDetailId
//   * @return
//   */
//  @GET(Constants.QR_CODE_LESSONS_URL)
//  Call<JSONObject> getQRodeLessons(@Query("frequencyDetailId") String frequencyDetailId, @Query("orderId") String orderId);
//  /**
//   * 家长信息
//   * @return
//   */
//  @GET(Constants.PARENT_URL)
//  Call<JSONObject> getParent();
//
//  /**
//   * 我关注的讲师列表
//   * @return
//   */
//  @GET(Constants.FAVORITE_TEACHER_LIST_URL)
//  Call<JSONObject> getFavoriteTeacherList(@Query("page") int page, @Query("count") int count);
//  /**
//   * 我关注的课程列表
//   * @return
//   */
//  @GET(Constants.FAVORITE_CORRICULUM_LIST_URL)
//  Call<JSONObject> getFavoriteCurriculumList(@Query("myLatitude") String myLatitude,
//                                             @Query("myLongitude") String myLongitude,
//                                             @Query("page") int page,
//                                             @Query("count") int count);
//
//  /**
//   * 我的宝贝
//   * @return
//   */
//  @GET(Constants.BABY_LIST_URL)
//  Call<JSONObject> getBabyList();
//
//  /**
//   * 添加宝贝
//   * @param fields
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.ADD_BABY_URL)
//  Call<JSONObject> addBaby(@FieldMap Map<String, String> fields);
//  /**
//   * 获取宝贝信息
//   * @return
//   */
//  @GET(Constants.BABY_INFO_URL)
//  Call<JSONObject> getBabyInfo(@Query("childId") String childId);
//  /**
//   * 编辑宝贝
//   * @param fields
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.EDIT_BABY_URL)
//  Call<JSONObject> editBaby(@Query("isEdit") int isEdit, @FieldMap Map<String, String> fields);
//
//  /**
//   * 消息通知列表
//   * @return
//   */
//  @GET(Constants.MESSAGE_LIST_URL)
//  Call<JSONObject> getMessageList(@Query("page") int page, @Query("count") int count);
//
//  /**
//   * 修改家长信息
//   * @param fieldName
//   * @param value
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.PARENT_URL)
//  Call<JSONObject> editParent(@Query("fieldName") String fieldName, @Query("value") String value
//          , @FieldMap Map<String, String> fields);
//  /**
//   * 邮票购买列表
//   * @return
//   */
//  @GET(Constants.UTICKET_LIST_URL)
//  Call<JSONObject> getUTicketList(@Query("parentId") String parentId);
//  /**
//   * 邮票购买(支付宝)
//   * @return
//   */
//  @GET(Constants.UTICKET_ALIPAY_URL)
//  Call<JSONObject> getUTicketAlipayInfo(@Query("uTicketId") String uTicketId, @Query("amount") String amount, @Query("childId") String childId);
//  /**
//   * 邮票购买(微信)
//   * @return
//   */
//  @GET(Constants.UTICKET_WEICHAT_URL)
//  Call<JSONObject> getUTicketWeichatInfo(@Query("uTicketId") String uTicketId, @Query("amount") String amount, @Query("childId") String childId, @Query("ip") String ip);
//  /**
//   * 邮票购买(支付结果验证)
//   * @return
//   */
//  @GET(Constants.AUTH_PAY_RSULT_URL)
//  Call<JSONObject> authPayResult(@Query("orderNum") String orderNum);
//  /**
//   * 游票购买记录列表
//   * @return
//   */
//  @GET(Constants.UTICKET_RECORD_LIST_URL)
//  Call<JSONObject> getUTicketRecordList(@Query("param") String param, @Query("page") int page, @Query("count") int count);
//  /**
//   * 游票购买记录详情
//   * @return
//   */
//  @GET(Constants.UTICKET_RECORD_DETAIL_URL)
//  Call<JSONObject> getUTicketRecordDetail(@Query("rId") String rId);
//  /**
//   * 积分兑换
//   * @param points
//   * @param ticketCount
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.POINT_EXCHANGE_URL)
//  Call<JSONObject> submitPointExchange(@Query("points") int points
//          , @Query("ticketCount") int ticketCount,
//                                       @FieldMap Map<String, String> fields);
//  /**
//   * 获取点评标签
//   * @return
//   */
//  @GET(Constants.COMMENT_TAG_URL)
//  Call<JSONObject> getCommentTagList();
//  /**
//   * 点评课程
//   * @param TagIds
//   * @param ImgIds
//   * @param ImgIds
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.SUBMIT_COMMENT_URL)
//  Call<JSONObject> submitComment(@Field("TagIds") List<String> TagIds,
//                                 @Field("ImgIds") List<String> ImgIds,
//                                 @FieldMap Map<String, String> ImgIdsImgIds);
//  /**
//   * 获取购课详情
//   * @return
//   */
//  @GET(Constants.LESSONS_ORDER_DETAIL_URL)
//  Call<JSONObject> getLessonsOrderDetail(@Query("orderId") String orderId, @Query("frequencyId") String frequencyId);
//
//  /**
//   * 获取VIP状态
//   * @return
//   */
//  @GET(Constants.VIP_CHILDLIST)
//  Call<JSONObject> getVipState();
//
//  /**
//   * 获取VIP列表
//   * @return
//   */
//  @GET(Constants.VIP_PRODUCT)
//  Call<JSONObject> getVipProduct(@Query("childId") String childId);
//
//  /**
//   * 删除baby
//   * @param fields
//   * @return
//   */
//  @FormUrlEncoded
//  @POST(Constants.CHILD_DELETE)
//  Call<JSONObject> deleteBaby(@Query("childId") String childId, @FieldMap Map<String, String> fields);
//
//
//  /**
//   * 获取根据字段名获取字段信息（获取讲师详情页面的图片）
//   * @return
//   */
//  @GET(Constants.UTIKET_URL)
//  Call<JSONObject> getUticketImage(@Query("key") String key);
//
//  /**
//   * 新消息列表
//   * @return
//   */
//  @GET(Constants.NEW_MESSAGE_URL)
//  Call<JSONObject> getNewMessageList(@Query("page") int page, @Query("count") int count);  /**
//   * 新消息列表
//   * @return
//   */
//  @GET(Constants.CHAT_LIST_URL)
//  Call<JSONObject> getChatList(@Query("page") int page, @Query("count") int count);
//
// /* * 新消息列表
//  * @return
//          */
//  @GET(Constants.CHAT_MESSAGE_URL)
//  Call<JSONObject> getChatMessageDetail(@Query("page") int page, @Query("count") int count, @Query("teacherId") String teacherId, @Query("courseId") String courseId);
//  /* 添加消息
//          */
//  @FormUrlEncoded
//  @POST(Constants.CHAT_MESSAGE_URL)
//  Call<JSONObject> addChatMessage(@FieldMap Map<String, String> fields);
//  /**
//   * 家长端：获取教师最新课程、与教师所有留言列表
//   * @return
//   */
//  @GET(Constants.LEAVE_MESSAGE_URL)
//  Call<JSONObject> getLeaveMessageList(@Query("tId") String tId, @Query("page") int page, @Query("count") int count);
//  /**
//   * 根据Tid取得教师的基本信息想
//   * @return
//   */
//  @GET(Constants.TEACHER_BASIC_INFO)
//  Call<JSONObject> getTeacherBasicInfo(@Query("tId_basic") String tid);
//
//  /**
//   * 我的订单列表
//   * @param
//   * @return
//   */
//  @GET(Constants.MY_ORDER_LIST)
//  Call<JSONObject> getMyOrderList(@Query("param") int param, @Query("page") int page, @Query("count") int count);
//
//  /**
//   * 取消订单
//   * @param
//   * @return
//   */
//  @GET(Constants.CANCEL_ORDER)
//  Call<JSONObject> cancelMyOrder(@Query("c_oId") String orderId);
//  /**
//   * 支付订单
//   * @param
//   * @return
//   */
//  @GET(Constants.CANCEL_ORDER)
//  Call<JSONObject> payMyOrder(@Query("p_oId") String orderId);
//
//
//  /**
//   * 获取首页的广告轮播图
//   * @param
//   * @return
//   */
//  @GET(Constants.ADVERTISEMENT_IMGAES)
//  Call<JSONObject> getAdVertisementList(@Query("type") int type);
//
//  /**
//   * 获取套餐的信息的
//   * @param
//   * @return
//   */
//  @GET(Constants.COMBO_INFO)
//  Call<JSONObject> getComboInfo(@Query("pId") String tcId);
//  /**
//   * 获取套餐的信息的
//   * @param
//   * @return
//   */
//  @GET(Constants.COMBO_INFO)
//  Call<JSONObject> getComboBuyInfo(@Query("packageId") String tId);
//
//  /**
//   *
//   * @param PackageId 套餐Id
//   * @param list 孩子列表
//   * @return
//     */
//  //创建套餐订单
//  @FormUrlEncoded
//  @POST(Constants.CREATE_COMBO_ORDER)
//  Call<JSONObject> confirmOrder(@Field("PackageId") String PackageId,
//                                @Field("ChildrenIds") List<String> list);
// /**
//   *检查孩子是否已经购买过某套餐
//   * @param PackageId 套餐Id
//   * @param
//   * @return
//     */
//  @GET(Constants.CHECK_CHILD_COMBO)
//  Call<JSONObject> checkChildCanCombo(@Query("packageId") String PackageId, @Query("childId") String childId);
//  /**
//   *家长端：支付套餐订单
//   * @param   pOrderId 套餐订单Id
//   * @param
//   * @return
//     */
//  @GET(Constants.CHECK_CHILD_COMBO)
//  Call<JSONObject> payComboOrder(@Query("pOrderId") String pOrderId);
//  /**
//   *家长端：获取套餐订单分页列表
//   * @param
//   * @param
//   * @return
//     */
//  @GET(Constants.GET_COMBO_LIST)
//  Call<JSONObject> getComboOrderList(@Query("page") int page, @Query("count") int count);
//
//  /**
//   *家长端：取消套餐订单
//   * @param   pOrderId 套餐订单Id
//   * @param
//   * @return
//   */
//  @GET(Constants.CHECK_CHILD_COMBO)
//  Call<JSONObject> cancelComboOrder(@Query("c_pOrderId") String pOrderId);
//
//  /**
//   *家长端：套餐订单详情
//   * @param   pOrderId 套餐订单Id
//   * @param
//   * @return
//   */
//  @GET(Constants.CHECK_CHILD_COMBO)
//  Call<JSONObject> getComboOrderDetail(@Query("d_pOrderId") String pOrderId);
//  /**
//   *家长端：获取订单统计
//   * @param
//   * @param
//   * @return
//   */
//  @GET(Constants.GET_NO_PAY_ORDER)
//  Call<JSONObject> getNoPayOrder(@Query("param") String param);
}
