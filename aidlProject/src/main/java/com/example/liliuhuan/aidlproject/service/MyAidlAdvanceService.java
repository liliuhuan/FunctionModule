package com.example.liliuhuan.aidlproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.liliuhuan.aidlproject.AidlCallback;
import com.example.liliuhuan.aidlproject.IMyAidlAdvanceInterface;
import com.example.liliuhuan.aidlproject.utils.ProcessingDataUtils;

import java.lang.ref.WeakReference;

public class MyAidlAdvanceService extends Service {

    private  ProcessingDataUtils processingDataUtils;
    private IBinder iBinder ;
    public MyAidlAdvanceService() {
        iBinder = new AdvanceBinder(this);
        processingDataUtils = new ProcessingDataUtils();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private class AdvanceBinder extends IMyAidlAdvanceInterface.Stub {
        private final WeakReference<MyAidlAdvanceService> mService;
        private AdvanceBinder( MyAidlAdvanceService service) {
            mService = new WeakReference<>(service);
        }

        @Override
        public void startDownloading() throws RemoteException {
            mService.get().startDownLoading();
        }

        @Override
        public void registerListener(AidlCallback callBack) throws RemoteException {
            mService.get().registerCallbacks(callBack);
        }

        @Override
        public void unRegisterListener(AidlCallback callBack) throws RemoteException {
            mService.get().unRegisterCallbacks(callBack);
        }
    }

    private void startDownLoading() {
        processingDataUtils.startDownloading();
    }

    private void registerCallbacks(AidlCallback callBack) {
        processingDataUtils.registerCallback(callBack);
    }

    private void unRegisterCallbacks(AidlCallback callBack) {
        processingDataUtils.unRegisterCallback(callBack);
    }

}
