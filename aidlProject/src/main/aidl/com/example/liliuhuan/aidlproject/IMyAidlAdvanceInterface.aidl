// IMyAidlAdvanceInterface.aidl
package com.example.liliuhuan.aidlproject;
import com.example.liliuhuan.aidlproject.AidlCallback;
// Declare any non-default types here with import statements

interface IMyAidlAdvanceInterface {
    void startDownloading();
    void registerListener(AidlCallback callBack);
    void unRegisterListener(AidlCallback callBack);
}
