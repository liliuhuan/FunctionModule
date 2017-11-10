package com.liliuhuan.com.simplyskill.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class IPCService extends Service {
    public static final String DESCRIPTOR = "IPCService";
    private final String[] names = {"B神","艹神","基神","J神","翔神"};
    private MyBinder mBinder = new MyBinder();
    public IPCService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return mBinder;
    }

    private class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code){
                case 0x001: {
                    data.enforceInterface(DESCRIPTOR);
                    int num = data.readInt();
                    reply.writeNoException();
                    reply.writeString(names[num]);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
