package com.liliuhuan.com.simplyskill.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.liliuhuan.com.simplyskill.IPerson;

public class AidlSimpleService extends Service {
    PersonQueryBinder mBinder  = new PersonQueryBinder();
    public AidlSimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final class PersonQueryBinder extends IPerson.Stub {

        @Override
        public String getName(int num) throws RemoteException {
            return query(num);
        }
    }

    private String[] names = {"B神","艹神","基神","J神","翔神"};

    private String query(int num)
    {
        if(num > 0 && num < 6){
            return names[num - 1];
        }
        return null;
    }
}
