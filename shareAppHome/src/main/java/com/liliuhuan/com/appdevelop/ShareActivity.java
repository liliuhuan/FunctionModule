package com.liliuhuan.com.appdevelop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * author:llh
 * date:
 * tag:
 * 外部启动方式
 *     Intent sendIntent = new Intent();
 sendIntent.setAction(Intent.ACTION_SEND);
 sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
 sendIntent.setType("text/plain");
 startActivity(sendIntent);

 //二进制文件
 Intent shareIntent = new Intent();
 shareIntent.setAction(Intent.ACTION_SEND);
 shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
 shareIntent.setType("image/jpeg");
 startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));

 //多个文件内容
 ArrayList<Uri> imageUris = new ArrayList<Uri>();
 imageUris.add(imageUri1); // Add your image URIs here
 imageUris.add(imageUri2);

 Intent shareIntent = new Intent();
 shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
 shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
 shareIntent.setType("image/*");
 startActivity(Intent.createChooser(shareIntent, "Share images to.."));

 */
public class ShareActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initData() {
        //init  data
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }

    }

    @Override
    protected void initView() {
        //bind view
        findViewById(R.id.btn_send).setOnClickListener(this);
        TextView tvShow = findViewById(R.id.tv_show);
    }

    @Override
    protected void requestNet() {
        //http

    }

    @Override
    public void onClick(View v) {
        // Create intent to deliver some kind of result data
        Intent result = new Intent("com.liliuhuan.com.otherappdevelop.SHARE", Uri.parse("content://result_uri"));
        result.putExtra("name","hello world result");
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            TextView viewById = findViewById(R.id.tv_show);
            viewById.append(sharedText);
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }
}


