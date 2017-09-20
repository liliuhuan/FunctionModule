package com.liliuhuan.com.mylibrary;

/**
 * 接口管理类
 */

public class Config {
    /*是否是开发模式*/
    public final static boolean DEVELOPER_MODE = false;
    //public static String url = "http://uxueja.com/";
    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxb85d07757ce75b74";
    public static String url = "http://test.uxueja.com/";//测试地址
    public static String imageUrl = url+"file/";
    static {
        if(DEVELOPER_MODE){  /*如果是开发模式，则使用测试环境*/
            url = "http://test.uxueja.com/";
            imageUrl = url+"file/";
        }else{  /*如果上线模式，则使用正式环境*/
            url = "http://uxueja.com/";
            imageUrl = url+"file/";
        }
    }
    public final static String shareCourseUrl = "http://uxueja.com/vue/teacher/lesson?";
    public static final String currentOutputVideoPath = "/storage/emulated/0/DCIM/movie/courseout.mp4";
    public static final String UploadcurrentOutputVideoPath = "/storage/emulated/0/DCIM/movie/out.mp4";
    //设备类型
    public static String DEVICE_TYPE = "2";
    public static String PREVIEW_URL = url+"vue/lessonPreview?";
    public static String CITY_CACHE = "CITY_DATA";
    //获取验证码
    public static String getCode = url + "api/VerifyCode";
    //注册
    public static String teacherAccount = url + "api/TeacherAccount";
    public static String teacher = url + "api/Teacher";
    //上传文件
    public static String fileUpload = url + "api/FileUpload";

    //认证相册
    public static String teacherImage = url + "api/TeacherImage";
    //履历
    public static String teacherResume = url + "api/TeacherResume";
    //我的课程
    public static String course = url + "api/Course";
    //课程类型
    public static String courseTypeurl = url + "api/CourseType";
    //得到市区列表
    public static String areaUrl = url + "api/Area";

    //获取标签
    public static String tagUrl = url + "api/Tag";
    //收入记录
    public static String recordurl = url + "api/UTicketRecord";
    //我的课程
    public static String courseFrequency = url + "api/CourseFrequency";

    public static String requencyDetail = url + "api/FrequencyDetail";
    public static String message = url + "api/Message";
    public static String messageReply = url + "api/MessageReply";
    public static String courseDynamic = url + "api/CourseDynamic";
    public static String dynamicComment = url + "api/DynamicComment";
    public static String order = url + "api/Order";
    public static String uTicketRecord = url + "api/UTicketRecord";
    public static String children = url + "api/Children";
    public static String myCourse = url + "api/MyCourse";
    public static String frequencyDetail = url + "api/FrequencyDetail";
    public static String parent = url + "api/Parent";
    public static String chat_list = url + "api/UChat";
    /*使用帮助*/
    public static String faq = "http://www.us2080.com/doc/uteacher_faq.html";
    /*帮助与说明*/
    public static String helpAndExplain = "http://www.us2080.com/doc/uteacher_qualify.html";

    /*关于我们*/
    public static String aboutUs = "http://www.us2080.com/doc/uteacher_about.html";
    //public static String aboutUs = "http://bolooo.com/mobile/about-mobile.html";
    /*游学家协议*/
    public static String contract = "http://www.us2080.com/doc/uteacher_contract.html";
    /*评价反馈中的举报*/
    public static String dynamicReport = url + "api/Report";
    /*首页消息中心列表*/
    public static String MESSAGE_LIST_URL = url + "api/SysNotice";
    /*首页消息中心未读消息数*/
    public static String UN_READ_MESSAGE_NUM_URL = url + "api/SysNotice?userId=";
    /*找回密码获取验证码*/
    public static String FIND_PASSWORD_GET_CODE_URL = url + "api/Teacher";
    public static String PreviewuRL= "http://uxueja.com/vue/lessonPreview?cId=";

//    ###注册协议-教师端
//    http://www.us2080.com/doc/uteacher_contract.html
//
//            ###游学家QA-教师端
//    http://www.us2080.com/doc/uteacher_faq.html
//
//            ###游学家QA-家长端
//    http://www.us2080.com/doc/uclient_faq.html
//    ###游学家-关于评分和等级 - 教师端
    public static String aboutLevel="http://www.us2080.com/doc/uteacher_level.html";
    public static String dynamicDetailUrl="http://uxueja.com/vue/dynamicDetail/zonezanDetail?uzoneId=";
    public final static String TagUrl="api/Tag";
    public final static String SYSSETTINGURL="api/SysSetting?";
    public final static String LOGINURL="api/TeacherAccount?";
    public final static String GETCERTYINFO="api/Teacher?";
    public final static String WITHDRAW="api/Teacher";
    public final static String TEACHERINFO="api/Teacher";
    /*图文详情处使用的图片上传*/
    public final static String UPLOAD_FILE_URL="api/UploadImg";
    //发布动态
    public final static String PUBLISH_DYNAMIC="api/UZone";
    //老版本文件上传
    public final static String FILE_OLD_UPLOAD = "api/FileUpload";
    //获取动态
    public final static String GET_UZONE = "api/UZone?";
    //动态点赞
    public final static String ADD_DYNAMICZAN = "api/UZoneZan";
    //点赞列表
    public final static String UZONEZAN_LIST = "api/UZoneZan";
    //动态评论列表
    public final static String DYNAMIC_COMMENT_LIST = "api/UZoneComment";
    //发布评论
    public final static String PUBLISH_COMMENT = "api/UZoneComment";
    //更新检查
    public final static String UPDATE_APP_URL = url+"api/VersionInfo?app=1";
    //城市列表
    public final static String GET_CITY_LIST = "api/Province";
    //相册删除接口
    public final static String ALBUM_DELETE = "api/TeacherImage";
    //找回密码部分，验证验证码是否正确
    public final static String CHECK_CODE = "api/VerifyCode";
    //获取要修改课程的配置
    public final static String EDIT_LESSAON_PLAN_URL = "api/Course";
    //获取模板列表接口：
    public final static String GET_TEMPLATE_URL = "api/Template";
    //获取模板详情
    public final static String GET_TEMPLATE_DETAIL_URL = "api/Template";
    //获取教师所有课程名称列表 2017-07-11 for version 1.4.7
    public final static String GET_DYNAMIC_LIST_URL = "api/Course";
    //获取教师所有课程详情课程Id
    public final static String GET_COURSE_DETAIL_URL = "api/Course";
    //教师端：查询客户详情、关注者详情 2017-07-12 for version 1.4.7
    public final static String GET_COMTER_DETAIL_URL = "api/Parent";
    //教师端：删除动态
    public final static String DELETE_UZONE_URL = "api/UZone";
    public final static String UPLOADING_MEDIA = "api/UploadMedia";
    public final static String upload_media = url+"api/UploadMedia";
    //视频上传
    public final static String UPLOAD_MEDIA_IMAGE = "api/Teacher";
    //根据课程Id获取课程设置页信息 2
    public final static String GET_COURSE_SETTING = "api/Course";
    //教师端：课程视频上传
    public final static String UPLOAD_MEDIA_COURSE_SETTING = "api/Course";
    //教师端：获取教师与指定家长的聊天记录分页列表
    public final static String CHAT_MESSAGE_DEATIL= "api/UChat";
    //教师端：查询家长购课详情 2017-08-24 for version 1.5.0 新接口
    public final static String GET_PARENTS_DEATIL= "api/Parent";
    //发送聊天信息
    public final static String CHAT_MESSAGE_URL = "api/UChat";

    //1.60 教师端：新建学习班
    public final static String CREATE_CLASS =url+ "api/CourseFrequency";
    //1.60 教师端：根据课程Id获取课程信息与课程学习班列表信息
    public final static String GET_CLASS_INFO = "api/Course";
    //教师端：根据字段名修改教案设置
    public final static String EDIT_COURSE = "api/Course";
    //教师端：查询某教师某课程的订单列表 2017-09-07 for version 1.6.0
    public final static String QUERY_COURSE_ORDER_LIST = "api/Order";
}
