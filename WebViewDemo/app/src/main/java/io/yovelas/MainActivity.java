package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);

        // inner Content
        webView.loadData("<h1>Welcome to the web page!</h1><a href=\"https://sldl.yunfens.com/yf.apk\">download</a>","text/html","UTF-8");

        // external Content
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.baidu.com/");
    }
}