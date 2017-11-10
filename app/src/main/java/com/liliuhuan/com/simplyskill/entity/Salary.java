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

public class Salary implements Parcelable {
    public String workName;
    public int salary;

    public Salary(String workName, int salary) {
        this.workName = workName;
        this.salary = salary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.workName);
        dest.writeInt(this.salary);
    }

    public Salary() {
    }

    protected Salary(Parcel in) {
        this.workName = in.readString();
        this.salary = in.readInt();
    }

    public static final Creator<Salary> CREATOR = new Creator<Salary>() {
        @Override
        public Salary createFromParcel(Parcel source) {
            return new Salary(source);
        }

        @Override
        public Salary[] newArray(int size) {
            return new Salary[size];
        }
    };

    @Override
    public String toString() {
        return "名字："+workName+"===薪水："+salary;
    }
}
