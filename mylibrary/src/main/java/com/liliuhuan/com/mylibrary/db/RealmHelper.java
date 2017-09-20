package com.liliuhuan.com.mylibrary.db;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Description: RealmHelper
 * Creator: yxc
 * date: 2016/9/21 17:46
 */

public class RealmHelper {

    public static final String DB_NAME = "teacherDb";
    private Realm mRealm;
    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null)
                    instance = new RealmHelper();
            }
        }
        return instance;
    }


    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed())
            mRealm = Realm.getDefaultInstance();
        return mRealm;
    }


    //--------------------------------------------------标签相关----------------------------------------------------

    /**
     * 增加标签
     *
     * @param bean
     * @param maxSize 保存最大数量
     */
    public void insertTag(TagsBean bean, int maxSize) {
        if (maxSize != 0) {
            RealmResults<TagsBean> results = getRealm().where(TagsBean.class).findAllSorted("time", Sort.DESCENDING);
            if (results.size() >= maxSize) {
                for (int i = maxSize - 1; i < results.size(); i++) {
                    deleteTag(results.get(i).id);
                }
            }
        }
        getRealm().beginTransaction();
        getRealm().copyToRealm(bean);
        getRealm().commitTransaction();
    }


    /**
     * 删除 记录
     *
     * @param
     */
    public void deleteTag(String name) {
        TagsBean data = getRealm().where(TagsBean.class).equalTo("name", name).findFirst();
        getRealm().beginTransaction();
        data.deleteFromRealm();
        getRealm().commitTransaction();
    }

    /**
     * 查询 记录
     *
     * @param name
     * @return
     */
    public boolean queryTagname(String name) {
        RealmResults<TagsBean> results = getRealm().where(TagsBean.class).findAll();
        for (TagsBean item : results) {
            if (item.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 查询 记录
     *
     * @param name
     * @return
     */
    public TagsBean queryTagByname(String name) {
        RealmResults<TagsBean> results = getRealm().where(TagsBean.class).findAll();
        for (TagsBean item : results) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }
    /**
     * 修改
     */
    public void updateTag(String id, String favarote) {
        getRealm().beginTransaction();
       // getRealm().where(TagsBean.class).equalTo("id", id).findFirst().setFavorite(Boolean.valueOf(favarote));
        getRealm().commitTransaction();
    }
    public List<TagsBean> getTagsList() {
        //使用findAllSort ,先findAll再result.sort排序
        RealmResults<TagsBean> results = getRealm().where(TagsBean.class).findAllSorted("time", Sort.ASCENDING);
        return getRealm().copyFromRealm(results);
    }

    /**
     * 清空历史
     */
    public void deleteAllRecord() {
        getRealm().beginTransaction();
        getRealm().delete(TagsBean.class);
        getRealm().commitTransaction();
    }
}
