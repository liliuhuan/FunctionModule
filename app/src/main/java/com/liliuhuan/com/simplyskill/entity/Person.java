package com.liliuhuan.com.simplyskill.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * =======================================
 * Author :李刘欢
 * DATA : 2017-11-10
 * DES : ${}
 * =======================================
 */

public class Person implements Parcelable {
    public int id;
    public String name ;

    public Person(int id, String name) {
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

    protected Person(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "id=="+id+"name===="+name;
    }
}
