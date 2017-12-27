package com.liliuhuan.com.appdevelop.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by liliuhuan on 2017/12/23.
 */

// File: AppDatabase.java
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}