package com.example.liliuhuan.aidlproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.liliuhuan.aidlproject.IMyAidlInterface;

import java.util.Random;

public class MyAidlService extends Service {
    private IBinder iBinder ;
    public MyAidlService() {
        iBinder = new RandomBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return iBinder;
    }

    private class RandomBinder extends IMyAidlInterface.Stub {
        @Override
        public String randomNumber() throws RemoteException {
            return startRandomNumber();
        }
    }

    private String startRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100));
    }
}
