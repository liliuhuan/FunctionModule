package com.liliuhuan.teststringformate.reflact;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liqin on 2017/12/29.
 */

public class StudentClient {

    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("ClassLoader.Student");
        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, String.class, String.class);
        constructor.setAccessible(true);

        Object student = constructor.newInstance(27, "小文", "望京soho");
        System.out.print(student);

        Field mAge = clazz.getDeclaredField("age");
        mAge.setAccessible(true);
        int age = (int)mAge.get(student);
        System.out.print("age="+age);

        Method getAge = clazz.getDeclaredMethod("getAge");
        getAge.setAccessible(true);
        int invoke = (int) getAge.invoke(student);
        System.out.print("age="+invoke);

        Method getTestMethod=clazz.getDeclaredMethod("getTest");
        getTestMethod.setAccessible(true);
        String result= (String) getTestMethod.invoke(null);
        System.out.println("调用静态方法:"+result);

    }
}
