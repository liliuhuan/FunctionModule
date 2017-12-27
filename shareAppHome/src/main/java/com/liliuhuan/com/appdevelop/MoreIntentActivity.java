package com.liliuhuan.com.appdevelop;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * author:llh
 * date:
 * tag:
 */
public class MoreIntentActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        //init  data
    }

    @Override
    protected void initView() {
        //bind view
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void requestNet() {
        //http
    }

    @Override
    public void onClick(View v) {
        Uri webpage = Uri.parse("http://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);


        // Always use string resources for UI text.
        // This says something like "Share this photo with"
        String title = getResources().getString(R.string.chooser_title);
        // Create intent to show chooser
        Intent chooser = Intent.createChooser(webIntent, title);

        // Verify the intent will resolve to at least one activity
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

}


