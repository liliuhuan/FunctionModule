package com.liliuhuan.com.mylibrary.view.scannerview;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtils {
    public DisplayUtils() {
    }

    public static Point getScreenResolution(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        Point screenResolution = new Point();
        if(VERSION.SDK_INT >= 13) {
            display.getSize(screenResolution);
        } else {
            screenResolution.set(display.getWidth(), display.getHeight());
        }

        return screenResolution;
    }

    public static int getScreenOrientation(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        boolean orientation = false;
        byte orientation1;
        if(display.getWidth() == display.getHeight()) {
            orientation1 = 3;
        } else if(display.getWidth() < display.getHeight()) {
            orientation1 = 1;
        } else {
            orientation1 = 2;
        }

        return orientation1;
    }
}
