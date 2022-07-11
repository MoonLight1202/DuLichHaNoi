package com.example.hanoitourist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebViewWiki extends AppCompatActivity {

    private TextView txtback;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_wiki );
        webView = (WebView) findViewById(R.id.webViewTinTuc);
        txtback = (TextView) findViewById(R.id.txtBack);
        Intent intent = getIntent();

        // lấy link để hiển thị dưới dạng webView
        String link = intent.getStringExtra("linkBai");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}