package com.liliuhuan.com.mylibrary.db;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by 李刘欢
 * 2017/4/10
 * 描述:${}
 */

public class TagsBean extends RealmObject implements Serializable {
    public String id;
    public String name;
    public String time;
}
