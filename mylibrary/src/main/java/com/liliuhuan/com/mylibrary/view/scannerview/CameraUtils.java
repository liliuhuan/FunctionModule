package com.liliuhuan.com.mylibrary.view.scannerview;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import java.util.List;

public class CameraUtils {
    public CameraUtils() {
    }

    public static Camera getCameraInstance() {
        return getCameraInstance(-1);
    }

    public static Camera getCameraInstance(int cameraId) {
        Camera c = null;

        try {
            if (cameraId == -1) {
                c = Camera.open();
            } else {
                c = Camera.open(cameraId);
            }
        } catch (Exception var3) {
            ;
        }

        return c;
    }

    public static boolean isFlashSupported(Camera camera) {
        if (camera != null) {
            Parameters parameters = camera.getParameters();
            if (parameters.getFlashMode() == null) {
                return false;
            } else {
                List supportedFlashModes = parameters.getSupportedFlashModes();
                return supportedFlashModes != null && !supportedFlashModes.isEmpty() && (supportedFlashModes.size() != 1 || !((String) supportedFlashModes.get(0)).equals("off"));
            }
        } else {
            return false;
        }
    }
}
