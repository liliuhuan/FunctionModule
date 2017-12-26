package com.liliuhuan.com.simplyskill.rx;

/**
 * Created by 李刘欢
 * 2017/12/26
 * 描述:${}
 */

public abstract class InterOperate implements IOperate {
    abstract void typeCreate();
    abstract void typeThread();
    abstract void typeTrans();
    abstract void typeFilter();
    abstract void typeGroup();
    abstract void typeError();
    abstract void typeAssist();
    abstract void typeCondaction();
}
