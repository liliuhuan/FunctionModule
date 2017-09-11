package com.liliuhuan.com.simplyskill.annotation.custome;

/**
 * Created by liliuhuan on 2017/9/11.
 */

public interface IReqApi {
    @ReqType(reqType = ReqType.ReqTypeEnum.POST)
    @ReqUrl(reqUrl = "www.xxx.com/openApi/login")
    String login(@ReqParam("userId") String uerId,@ReqParam("password") String password);
}
