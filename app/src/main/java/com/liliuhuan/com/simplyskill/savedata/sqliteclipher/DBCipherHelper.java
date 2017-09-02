package com.liliuhuan.com.simplyskill.savedata.sqliteclipher;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 具体实现传统的SQLiteOpenHelper都是完全相同的，
 不同的地方在获取数据库句柄的地方需要传入一个password，这个password就是用于加密的秘钥

 //获取写数据库
 SQLiteDatabase db = dbHelper.getWritableDatabase(DBCipherHelper.DB_PWD);
 //获取可读数据库
 SQLiteDatabase db = dbHelper.getReadableDatabase(DBCipherHelper.DB_PWD);
 */

public class DBCipherHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "test_cipher_db";//数据库名字
    public static final String DB_PWD="whoislcj";//数据库密码
    public static String TABLE_NAME = "person";// 表名
    public static String FIELD_ID = "id";// 列名
    public static String  FIELD_NAME= "name";// 列名
    private static final int DB_VERSION = 1;   // 数据库版本

    public DBCipherHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //不可忽略的 进行so库加载
        SQLiteDatabase.loadLibs(context);
    }

    public DBCipherHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        createTable(db);
    }

    private void createTable(SQLiteDatabase db){
        String  sql = "CREATE TABLE " + TABLE_NAME + "(" + FIELD_ID + " integer primary key autoincrement , " + FIELD_NAME + " text not null);";
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG, "onCreate " + TABLE_NAME + "Error" + e.toString());
            return;
        }
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
