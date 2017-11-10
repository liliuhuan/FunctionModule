package com.liliuhuan.com.simplyskill.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.liliuhuan.com.simplyskill.ISalary;
import com.liliuhuan.com.simplyskill.entity.Person;
import com.liliuhuan.com.simplyskill.entity.Salary;

import java.util.HashMap;
import java.util.Map;

public class AidlMutilService extends Service {
    SalaryBinder binder = new SalaryBinder();
    private static Map<Person,Salary> ss = new HashMap<Person, Salary>();
    //初始化Map集合,这里在静态代码块中进行初始化,当然你可也以在构造方法中完成初始化
    static
    {
        ss.put(new Person(1, "Jay"), new Salary("码农", 2000));
        ss.put(new Person(2, "GEM"), new Salary("歌手", 20000));
        ss.put(new Person(3, "XM"), new Salary("学生", 20));
        ss.put(new Person(4, "MrWang"), new Salary("老师", 2000));
    }

    public AidlMutilService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return binder;
    }
    //同样是继承Stub,即同时实现ISalary接口和IBinder接口
    public class SalaryBinder extends ISalary.Stub {
        @Override
        public Person get(int num) throws RemoteException {
            return new Person(1, "Jay");
        }

        @Override
        public Salary getPerson(Person ower) throws RemoteException {
            Salary salary = null;
            int id = ower.id;
            switch (id){
                case 1:
                    salary = new Salary("码农", 2000);
                    break;
                case 2:
                    salary = new Salary("歌手", 20000);
                    break;
            }
            return salary;
        }
    }
}
