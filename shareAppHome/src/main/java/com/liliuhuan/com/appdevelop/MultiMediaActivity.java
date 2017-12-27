package com.liliuhuan.com.appdevelop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author:llh
 * date:
 * tag:
 */
public class MultiMediaActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageView,mImageViewthumbnail ;
    private VideoView mVideoView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_multi_media;
    }

    @Override
    protected void initData() {
        //init  data
    }
    @Override
    protected void initView() {
        //bind view
        findViewById(R.id.photo).setOnClickListener(this);
        findViewById(R.id.photo_thumbnail).setOnClickListener(this);
        findViewById(R.id.record).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);

        mImageView = findViewById(R.id.image);
        mImageViewthumbnail = findViewById(R.id.image_thumbnail);
        mVideoView = findViewById(R.id.video_view);

    }

    @Override
    protected void requestNet() {
        //http
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.photo_thumbnail:dispatchTakePictureIntent();break;
            case R.id.photo:dispatchFullTakePictureIntent();break;
            case R.id.record:dispatchTakeVideoIntent();break;
            case R.id.video:startAc(PermissionActivity.class);break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");//缩略图
            mImageViewthumbnail.setImageBitmap(imageBitmap);
        }
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
           galleryAddPic();
           setPic();
        }

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            mVideoView.setVideoURI(videoUri);
        }
    }


    static final int REQUEST_VIDEO_CAPTURE = 3;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    /**photo*/
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {//缩略图
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    static final int REQUEST_TAKE_PHOTO = 2;
    private void dispatchFullTakePictureIntent() {//全尺寸
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            // Continue only if the File was successfully created

            if (photoFile != null) {
                Uri photoURI = null;
                if (Build.VERSION.SDK_INT >= 24) {
                    photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.liliuhuan.com.appdevelop.fileprovider", photoFile);
                } else {
                    photoURI = Uri.fromFile(photoFile);
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//私有的外部不能访问，相册中不会显示
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //添加到相册中显示
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            contentUri = FileProvider.getUriForFile(getApplicationContext(), "com.liliuhuan.com.appdevelop.fileprovider", f);
        } else {
            contentUri = Uri.fromFile(f);
        }
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }
}


