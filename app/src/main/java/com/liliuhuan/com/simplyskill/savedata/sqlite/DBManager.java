package com.liliuhuan.com.simplyskill.savedata.sqlite;

/**
 * Created by liliuhuan on 2017/9/2.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

/**
 * 数据库管理者 - 提供数据库封装
 *
 */
public class DBManager {

    private static final String TAG = "DatabaseManager";
    // 静态引用
    private volatile static DBManager mInstance;
    // DatabaseHelper
    private DBHelper dbHelper;

    private DBManager(Context context) {
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        DBManager inst = mInstance;
        if (inst == null) {
            synchronized (DBManager.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new DBManager(context);
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 插入数据
     */
    public void insertData(String name) {
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_NAME, name);
        // insert 操作
        db.insert(DBHelper.TABLE_NAME, null, cv);
        //关闭数据库
        db.close();
    }

    /**
     * 未开启事务批量插入
     * @param testCount
     */
    public void insertDatasByNomarl(int testCount){
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(int i =0;i<testCount;i++ ){
            //生成要修改或者插入的键值
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.FIELD_NAME, String.valueOf(i));
            // insert 操作
            db.insert(DBHelper.TABLE_NAME, null, cv);
            Log.e(TAG, "insertDatasByNomarl");
        }
        //关闭数据库
        db.close();
    }

    /**
     * 测试开启事务批量插入
     * @param testCount
     */
    public void insertDatasByTransaction(int testCount){
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();  //手动设置开始事务
        try{
            //批量处理操作
            for(int i =0;i<testCount;i++ ){
                //生成要修改或者插入的键值
                ContentValues cv = new ContentValues();
                cv.put(DBHelper.FIELD_NAME, String.valueOf(i));
                // insert 操作
                db.insert(DBHelper.TABLE_NAME, null, cv);
                Log.e(TAG, "insertDatasByTransaction");
            }
            db.setTransactionSuccessful(); //设置事务处理成功，不设置会自动回滚不提交
        }catch(Exception e){

        }finally{
            db.endTransaction(); //处理完成
            //关闭数据库
            db.close();
        }
    }

    /**
     * 删除数据
     */
    public void deleteData(String name) {
        //生成条件语句
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_NAME).append(" = ").append("'").append(name).append("'");
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // delete 操作
        db.delete(DBHelper.TABLE_NAME, whereBuffer.toString(), null);
        //关闭数据库
        db.close();
    }

    /**
     * 删除所有数据
     */
    public void deleteDatas()
    {
        String sql="delete from "+ DBHelper.TABLE_NAME;
        execSQL(sql);
    }

    /**
     * 更新数据
     */
    public void updateData(String name) {
        //生成条件语句
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_NAME).append(" = ").append("'").append(name).append("'");
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_NAME, name+name);
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // update 操作
        db.update(DBHelper.TABLE_NAME, cv, whereBuffer.toString(), null);
        //关闭数据库
        db.close();
    }

    /**
     * 指定条件查询数据
     */
    public void queryDatas(String name){
        //生成条件语句
        StringBuffer whereBuffer = new StringBuffer();
        whereBuffer.append(DBHelper.FIELD_NAME).append(" = ").append("'").append(name).append("'");
        //指定要查询的是哪几列数据
        String[] columns = {DBHelper.FIELD_NAME};
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        cursor = db.query(DBHelper.TABLE_NAME, columns, whereBuffer.toString(), null, null, null, null);
        while (cursor.moveToNext()) {

            int count = cursor.getColumnCount();
            String columName = cursor.getColumnName(0);
            String  tname = cursor.getString(0);
            Log.e(TAG, "count = " + count + " columName = " + columName + "  name =  " +tname);
        }
        if (cursor != null) {
            cursor.close();
        }
        //关闭数据库
        db.close();
    }

    /**
     * 查询全部数据
     */
    public String queryDatas(){
        //指定要查询的是哪几列数据
        String[] columns = {DBHelper.FIELD_NAME};
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        //查询数据库
        Cursor cursor = null;
        cursor = db.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);//获取数据游标
        while (cursor.moveToNext()) {
            int count = cursor.getColumnCount();
            String columeName = cursor.getColumnName(0);//获取表结构列名
            String  name = cursor.getString(0);//获取表结构列数据
            Log.e(TAG, "count = " + count + " columName = " + columeName + "  name =  " +name);
            stringBuilder.append(" columName = " + columeName + "  name =  " +name+"\n");
        }
        //关闭游标防止内存泄漏
        if (cursor != null) {
            cursor.close();
        }
        //关闭数据库
        db.close();
        if (TextUtils.isEmpty(stringBuilder)) return null;
        return  stringBuilder.toString();
    }

    /**
     * 执行sql语句
     */
    private void execSQL(String sql){
        //获取写数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //直接执行sql语句
        db.execSQL(sql);//或者
        //关闭数据库
        db.close();
    }

}