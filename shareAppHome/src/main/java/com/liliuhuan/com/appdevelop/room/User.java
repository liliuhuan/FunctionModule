package com.liliuhuan.com.appdevelop.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by liliuhuan on 2017/12/23.
 */
@Entity
public class User {
    @PrimaryKey
    private int uid;
    private String name;
    @ColumnInfo(name = "last_name")
    private String lastName;
    // getters and setters are ignored for brevity but they are required for Room to work.

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}