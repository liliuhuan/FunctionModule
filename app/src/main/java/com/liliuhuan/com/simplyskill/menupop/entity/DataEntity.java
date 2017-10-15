package com.liliuhuan.com.simplyskill.menupop.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liliuhuan on 2017/10/14.
 */

public class DataEntity implements Parcelable {
    public int id;
    public String name ;

    public DataEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    protected DataEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
        @Override
        public DataEntity createFromParcel(Parcel source) {
            return new DataEntity(source);
        }

        @Override
        public DataEntity[] newArray(int size) {
            return new DataEntity[size];
        }
    };
}
