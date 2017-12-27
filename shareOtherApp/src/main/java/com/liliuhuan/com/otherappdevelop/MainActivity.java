package com.liliuhuan.com.otherappdevelop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivityForResult(sendIntent,101);

//         //二进制文件
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
//        shareIntent.setType("image/jpeg");
//        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
//
//        //多个文件内容
//        ArrayList<Uri> imageUris = new ArrayList<Uri>();
//        imageUris.add(imageUri1); // Add your image URIs here
//        imageUris.add(imageUri2);
//
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
//        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
//        shareIntent.setType("image/*");
//        startActivity(Intent.createChooser(shareIntent, "Share images to.."));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK){
            String extras = data.getStringExtra("name");
            TextView tv =findViewById(R.id.result);
            tv.append(":"+extras);
        }
    }
}
