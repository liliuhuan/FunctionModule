package com.liliuhuan.com.mylibrary.view.scannerview;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import java.util.Iterator;
import java.util.List;

public class CameraPreview extends SurfaceView implements Callback {
    private static final String TAG = "CameraPreview";
    private Camera mCamera;
    private Handler mAutoFocusHandler;
    private boolean mPreviewing = true;
    private boolean mAutoFocus = true;
    private boolean mSurfaceCreated = false;
    private PreviewCallback mPreviewCallback;
    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if(CameraPreview.this.mCamera != null && CameraPreview.this.mPreviewing && CameraPreview.this.mAutoFocus && CameraPreview.this.mSurfaceCreated) {
                CameraPreview.this.safeAutoFocus();
            }

        }
    };
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            CameraPreview.this.scheduleAutoFocus();
        }
    };

    public CameraPreview(Context context, Camera camera, PreviewCallback previewCallback) {
        super(context);
        this.init(camera, previewCallback);
    }

    public CameraPreview(Context context, AttributeSet attrs, Camera camera, PreviewCallback previewCallback) {
        super(context, attrs);
        this.init(camera, previewCallback);
    }

    public void init(Camera camera, PreviewCallback previewCallback) {
        this.setCamera(camera, previewCallback);
        this.mAutoFocusHandler = new Handler();
        this.getHolder().addCallback(this);
        this.getHolder().setType(3);
    }

    public void setCamera(Camera camera, PreviewCallback previewCallback) {
        this.mCamera = camera;
        this.mPreviewCallback = previewCallback;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = true;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if(surfaceHolder.getSurface() != null) {
            this.stopCameraPreview();
            this.showCameraPreview();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = false;
        this.stopCameraPreview();
    }

    public void showCameraPreview() {
        if(this.mCamera != null) {
            try {
                this.getHolder().addCallback(this);
                this.mPreviewing = true;
                this.setupCameraParameters();
                this.mCamera.setPreviewDisplay(this.getHolder());
                this.mCamera.setDisplayOrientation(this.getDisplayOrientation());
                this.mCamera.setOneShotPreviewCallback(this.mPreviewCallback);
                this.mCamera.startPreview();
                if(this.mAutoFocus) {
                    if(this.mSurfaceCreated) {
                        this.safeAutoFocus();
                    } else {
                        this.scheduleAutoFocus();
                    }
                }
            } catch (Exception var2) {
                Log.e("CameraPreview", var2.toString(), var2);
            }
        }

    }

    public void safeAutoFocus() {
        try {
            this.mCamera.autoFocus(this.autoFocusCB);
        } catch (RuntimeException var2) {
            this.scheduleAutoFocus();
        }

    }

    public void stopCameraPreview() {
        if(this.mCamera != null) {
            try {
                this.mPreviewing = false;
                this.getHolder().removeCallback(this);
                this.mCamera.cancelAutoFocus();
                this.mCamera.setOneShotPreviewCallback((PreviewCallback)null);
                this.mCamera.stopPreview();
            } catch (Exception var2) {
                Log.e("CameraPreview", var2.toString(), var2);
            }
        }

    }

    public void setupCameraParameters() {
//        Size optimalSize = this.getOptimalPreviewSize();
//        Parameters parameters = this.mCamera.getParameters();
//        parameters.setPreviewSize(optimalSize.width, optimalSize.height);
//        this.mCamera.setParameters(parameters);
//        this.adjustViewSize(optimalSize);
    }

    private void adjustViewSize(Size cameraSize) {
        Point previewSize = this.convertSizeToLandscapeOrientation(new Point(this.getWidth(), this.getHeight()));
        float cameraRatio = (float)cameraSize.width / (float)cameraSize.height;
        float screenRatio = (float)previewSize.x / (float)previewSize.y;
        if(screenRatio > cameraRatio) {
            this.setViewSize((int)((float)previewSize.y * cameraRatio), previewSize.y);
        } else {
            this.setViewSize(previewSize.x, (int)((float)previewSize.x / cameraRatio));
        }

    }

    private Point convertSizeToLandscapeOrientation(Point size) {
        return this.getDisplayOrientation() % 180 == 0?size:new Point(size.y, size.x);
    }

    private void setViewSize(int width, int height) {
        LayoutParams layoutParams = this.getLayoutParams();
        if(this.getDisplayOrientation() % 180 == 0) {
            layoutParams.width = width;
            layoutParams.height = height;
        } else {
            layoutParams.width = height;
            layoutParams.height = width;
        }

        this.setLayoutParams(layoutParams);
    }

    public int getDisplayOrientation() {
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(0, info);
        WindowManager wm = (WindowManager)this.getContext().getSystemService("window");
        Display display = wm.getDefaultDisplay();
        int rotation = display.getRotation();
        short degrees = 0;
        switch(rotation) {
            case 0:
                degrees = 0;
                break;
            case 1:
                degrees = 90;
                break;
            case 2:
                degrees = 180;
                break;
            case 3:
                degrees = 270;
        }

        int result;
        if(info.facing == 1) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }

    private Size getOptimalPreviewSize() {
        if(this.mCamera == null) {
            return null;
        } else {
            List sizes = this.mCamera.getParameters().getSupportedPreviewSizes();
            int w = this.getWidth();
            int h = this.getHeight();
            if(DisplayUtils.getScreenOrientation(this.getContext()) == 1) {
                int ASPECT_TOLERANCE = h;
                h = w;
                w = ASPECT_TOLERANCE;
            }

            double ASPECT_TOLERANCE1 = 0.1D;
            double targetRatio = (double)w / (double)h;
            if(sizes == null) {
                return null;
            } else {
                Size optimalSize = null;
                double minDiff = 1.7976931348623157E308D;
                int targetHeight = h;
                Iterator i$ = sizes.iterator();

                Size size;
                while(i$.hasNext()) {
                    size = (Size)i$.next();
                    double ratio = (double)size.width / (double)size.height;
                    if(Math.abs(ratio - targetRatio) <= 0.1D && (double) Math.abs(size.height - targetHeight) < minDiff) {
                        optimalSize = size;
                        minDiff = (double) Math.abs(size.height - targetHeight);
                    }
                }

                if(optimalSize == null) {
                    minDiff = 1.7976931348623157E308D;
                    i$ = sizes.iterator();

                    while(i$.hasNext()) {
                        size = (Size)i$.next();
                        if((double) Math.abs(size.height - targetHeight) < minDiff) {
                            optimalSize = size;
                            minDiff = (double) Math.abs(size.height - targetHeight);
                        }
                    }
                }

                return optimalSize;
            }
        }
    }

    public void setAutoFocus(boolean state) {
        if(this.mCamera != null && this.mPreviewing) {
            if(state == this.mAutoFocus) {
                return;
            }

            this.mAutoFocus = state;
            if(this.mAutoFocus) {
                if(this.mSurfaceCreated) {
                    Log.v("CameraPreview", "Starting autofocus");
                    this.safeAutoFocus();
                } else {
                    this.scheduleAutoFocus();
                }
            } else {
                Log.v("CameraPreview", "Cancelling autofocus");
                this.mCamera.cancelAutoFocus();
            }
        }

    }

    private void scheduleAutoFocus() {
        this.mAutoFocusHandler.postDelayed(this.doAutoFocus, 1000L);
    }
}

