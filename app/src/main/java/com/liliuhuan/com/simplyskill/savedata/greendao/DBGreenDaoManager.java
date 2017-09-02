package com.liliuhuan.com.simplyskill.savedata.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/***
 *
 DBGreenDaoManager dbManager = DBGreenDaoManager.getInstance(this);
 for (int i = 0; i < 5; i++) {
 User user = new User();
 user.setId(i);
 user.setAge(i * 3);
 user.setName("第" + i + "人");
 dbManager.insertUser(user);
 }
 List<User> userList = dbManager.queryUserList();
 for (User user : userList) {
 Log.e("TAG", "queryUserList--before-->" + user.getId() + "--" + user.getName() +"--"+user.getAge());
 if (user.getId() == 0) {
 dbManager.deleteUser(user);
 }
 if (user.getId() == 3) {
 user.setAge(10);
 dbManager.updateUser(user);
 }
 }
 userList = dbManager.queryUserList();
 for (User user : userList) {
 Log.e("TAG", "queryUserList--after--->" + user.getId() + "---" + user.getName()+"--"+user.getAge());
 }
 */

public class DBGreenDaoManager {
    private final static String dbName = "test_db";
    private static DBGreenDaoManager mInstance;
    private DaoMaster.OpenHelper openHelper;

    private Context context;

    public DBGreenDaoManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBGreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBGreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DBGreenDaoManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();//不加密
        // Database db1 = openHelper.getEncryptedWritableDb(DBCipherHelper.DB_PWD);//加密
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);

    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }
    /**
     * 查询用户列表
     */
    public List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        List<User> list = qb.list();
        return list;
    }
}
