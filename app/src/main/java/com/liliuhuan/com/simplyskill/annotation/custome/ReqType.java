package com.liliuhuan.com.simplyskill.annotation.custome;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liliuhuan on 2017/9/11.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqType {
    /**
     * 请求方式枚举
     *
     */
    enum ReqTypeEnum{POST,GET,DETELE,PUT}
    /**
     * 请求方式
     * @return
     */
    ReqTypeEnum reqType() default ReqTypeEnum.POST;
}
