package com.boxfox.android.myrelationshipsapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient







class AddressSearchActivity : AppCompatActivity() {

    private var daum_webView: WebView? = null
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_search)
        // WebView 초기화
        init_webView()
        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = Handler()
    }

    fun init_webView() {
        daum_webView = findViewById(R.id.webview_address_search) as WebView
        daum_webView!!.settings.javaScriptEnabled = true
        daum_webView!!.settings.javaScriptCanOpenWindowsAutomatically = true
        daum_webView!!.addJavascriptInterface(AndroidBridge(), "TestApp")
        daum_webView!!.webChromeClient = WebChromeClient()
        daum_webView!!.loadUrl("http://192.168.25.60:80/daum_address.php")

    }


    private inner class AndroidBridge {
        @JavascriptInterface
        fun setAddress(arg1: String, arg2: String, arg3: String) {
            handler!!.post(Runnable {
                val result = (String.format("(%s) %s %s", arg1, arg2, arg3))
                init_webView()
            })

        }
    }
}
