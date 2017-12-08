package com.example.liliuhuan.aidlproject;

interface AidlCallback {
    void onPreparedStart();
    void onDownloadProgress(long progress, long total);
    void onDownloadCompletion();
}
