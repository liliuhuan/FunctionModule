package com.liliuhuan.teststringformate.reflact;

/**
 * Created by liqin on 2017/12/29.
 */

public class Student {
    private int age;
    private String name;
    private String address;
    private static String sTest;

    public Student(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
        sTest = "测试静态";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getsTest() {
        return sTest;
    }

    public static void setsTest(String sTest) {
        Student.sTest = sTest;
    }
}
