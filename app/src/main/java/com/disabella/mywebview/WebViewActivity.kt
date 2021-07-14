package com.disabella.mywebview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class WebViewActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        myWebView = findViewById<WebView>(R.id.webView).apply {
            settings.javaScriptEnabled = true
            webViewClient = MyWebViewClient()
            webChromeClient = MyChromeWebViewClient()
            loadUrl("https://developer.android.com/training/basics/network-ops/connecting")
        }

        val button_back = findViewById<Button>(R.id.button_back)
        val button_next = findViewById<Button>(R.id.button_next)
        button_back.setOnClickListener {
            if (myWebView.canGoBack()) {
                myWebView.goBack()
            }
        }
        button_next.setOnClickListener {
            if (myWebView.canGoForward()) {
                myWebView.goForward()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }


        return super.onKeyDown(keyCode, event)
    }
}

private class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return false
    }
}

private class MyChromeWebViewClient : WebChromeClient() {
    //  handle Javascript dialogs, favicons, titles, and the progress
}