package com.boxfox.android.myrelationshipsapplication.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.graphics.drawable.ColorDrawable
import android.webkit.JavascriptInterface
import com.boxfox.android.myrelationshipsapplication.R
import android.webkit.WebView
import android.webkit.WebChromeClient


class AddressSearchDialog(context: Context?) : Dialog(context) {
    lateinit var selectedAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        setContentView(R.layout.dialog_address_search)

        val webView = findViewById(R.id.webview_address_search) as WebView
        webView.getSettings().setJavaScriptEnabled(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.addJavascriptInterface(object {
            @JavascriptInterface
            fun setAddress(arg1: String, arg2: String, arg3: String) {
                val address = String.format("(%s) %s %s", arg1, arg2, arg3)
                selectedAddress = address
                dismiss()
            }
        }, "TestApp")
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("http://codeman77.ivyro.net/getAddress.php")
    }

    fun getAddress(): String {
        return selectedAddress
    }
}