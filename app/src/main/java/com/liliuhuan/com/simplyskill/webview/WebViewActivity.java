package com.liliuhuan.com.simplyskill.webview;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liliuhuan.com.simplyskill.BaseActivity;
import com.liliuhuan.com.simplyskill.R;
import com.liliuhuan.com.simplyskill.utils.IntentUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.webview)
    WebView webview;

    /**
     * 加载本地资源
     * webView.loadUrl("file:///android_asset/example.html");
     * <p>
     * 加载网络资源
     * webView.loadUrl("www.xxx.com/index.html");
     * <p>
     * 添加请求头信息
     * Map<String,String> map=new HashMap<String,String>();
     * map.put("User-Agent","Android");
     * webView.loadUrl("www.xxx.com/index.html",map);
     * <p>
     * 也可以加载html片段
     * String data = " Html 数据";
     * webView.loadData(data, "text/html", "utf-8");
     * <p>
     * 实测会发现loadData会导致中文乱码，所以一般情况使用如下代码
     * String data = " Html 数据";
     * webView.loadDataWithBaseURL(null,data, "text/html", "utf-8",null);
     * <p>
     * 比如项目总js触发一个native函数关闭Activity
     * <p>
     * 设置支持JavaScript
     * WebSettings webSettings = webView.getSettings();
     * webSettings.setJavaScriptEnabled(true);//设置支持javascript
     * webView.addJavascriptInterface(new JavaScriptInterface(), "xueleapp");
     * <p>
     * JavaScriptInterface 接口定义
     * public class JavaScriptInterface {
     *
     * @return
     * @android.webkit.JavascriptInterface public void doTrainFinish() {
     * finish();
     * }
     * }
     * <p>
     * webView.setWebViewClient(new WebViewClient() {
     * public boolean shouldOverrideUrlLoading(WebView view, String url) {
     * view.loadUrl(url);
     * return true;
     * }
     * });
     * 除此之外WebViewClient更多的处理网页的地址的解析和渲染，例如
     * <p>
     * onLoadResource//加载资源时响应
     * 　　onPageStart//在加载页面时响应
     * 　　onPageFinish//在加载页面结束时响应
     * 　　onReceiveError//在加载出错时响应
     * 　　onReceivedHttpAuthRequest//获取返回信息授权请求
     * <p>
     * <p>
     * 比如加载进度获取title
     * webView.setWebChromeClient(new WebChromeClient() {
     * @Override public void onProgressChanged(WebView view, int newProgress) {
     * if (newProgress == 100) {
     * //网页加载完成
     * } else {
     * //网页加载中
     * }
     * }
     * });
     * 除了上面检测进度之外还有
     * 　　onCloseWindow//关闭WebView
     * 　　onCreateWindow() //触发创建一个新的窗口
     * 　　onJsAlert //触发弹出一个对话框
     * 　　onJsPrompt //触发弹出一个提示
     * 　　onJsConfirm//触发弹出确认提示
     * 　　onProgressChanged //加载进度
     * 　　onReceivedIcon //获取网页icon
     * 　　onReceivedTitle//获取网页title
     * <p>
     * 7.）设置网页栈返回
     * <p>
     * webview会默认把浏览过去的网页进行压栈存储，所以我们有时需要实现回退到上一目录
     * public boolean onKeyDown(int keyCode, KeyEvent event) {
     * if (keyCode == KeyEvent.KEYCODE_BACK) {
     * if (webView.canGoBack()) {
     * webView.goBack();//返回上一浏览页面
     * return true;
     * } else {
     * finish();//关闭Activity
     * }
     * }
     * return super.onKeyDown(keyCode, event);
     * }
     * <p>
     * WebView 缓存控制
     * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
     * LOAD_DEFAULT: 根据cache-control决定是否从网络上取数据。
     * LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
     * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
     * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
     * WebSettings webSettings = webView.getSettings();
     * webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
     * <p>
     * WebView屏幕自适应
     * WebSettings webSettings = webView.getSettings();
     * webSettings.setUseWideViewPort(true);
     * webSettings.setLoadWithOverviewMode(true);
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/wx.html");
        webview.addJavascriptInterface(this, "wx");//js调用android

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//android 调用js
                // 无参数调用
                webview.loadUrl("javascript:actionFromNative()");
                // 传递参数调用
                webview.loadUrl("javascript:actionFromNativeWithParam(" + "'come from java'" + ")");
            }
        });
    }

    @JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "js调用了Native函数", Toast.LENGTH_SHORT).show();
                String text = textView.getText() + "\njs调用了Native函数";
                textView.setText(text);
            }
        });
    }

    @JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();
                String text = textView.getText() + "\njs调用了Native函数传递参数：" + str;
                textView.setText(text);
            }
        });

    }


    @OnClick(R.id.btn2)
    public void onViewClicked() {
        IntentUtil.startActivity(this,WebViewJavascriptBridgeActivity.class);
    }
}
