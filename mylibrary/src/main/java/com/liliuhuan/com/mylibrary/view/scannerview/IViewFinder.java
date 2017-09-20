package com.liliuhuan.com.mylibrary.view.scannerview;

import android.graphics.Rect;

public interface IViewFinder {
    void setupViewFinder();

    Rect getFramingRect();

    int getWidth();

    int getHeight();
}
