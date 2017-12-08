package com.example.liliuhuan.aidlproject.utils;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.liliuhuan.aidlproject.AidlCallback;

/**
 * Created by liliuhuan on 2017/8/12.
 */

public class ProcessingDataUtils {
    public RemoteCallbackList<AidlCallback> mRemoteCallbackList;

    public ProcessingDataUtils() {
        mRemoteCallbackList = new RemoteCallbackList<>();
    }


    public void registerCallback(AidlCallback callBack) {
        mRemoteCallbackList.register(callBack);
    }

    public void unRegisterCallback(AidlCallback callBack) {
        mRemoteCallbackList.unregister(callBack);
    }

    public void startDownloading() {
            //因为没有服务器，这里就简单模拟，开始下载前，下载中，下载完成等数据回传功能

        int size = mRemoteCallbackList.beginBroadcast();
        for(int i = 0; i < size; i++){
            try {
                mRemoteCallbackList.getBroadcastItem(i).onPreparedStart();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mRemoteCallbackList.finishBroadcast();

        long pos = 1 ;
        while ( pos <= 100000 ){
            int size1 = mRemoteCallbackList.beginBroadcast();
            for(int i = 0; i < size1; i++){
                try {
                    mRemoteCallbackList.getBroadcastItem(i).onDownloadProgress(pos,100000);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            mRemoteCallbackList.finishBroadcast();
            pos++;
        }

        int size2 = mRemoteCallbackList.beginBroadcast();
        for(int i = 0; i < size2; i++){
            try {
                mRemoteCallbackList.getBroadcastItem(i).onDownloadCompletion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mRemoteCallbackList.finishBroadcast();

    }

}
