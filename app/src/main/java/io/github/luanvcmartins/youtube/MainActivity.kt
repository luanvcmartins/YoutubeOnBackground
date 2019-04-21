@file:Suppress("OverridingDeprecatedMember", "DEPRECATION")

package io.github.luanvcmartins.youtube

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.webkit.WebResourceRequest
import java.io.InputStream
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val adRegex = Regex("(doubleclick)|(pagead)|(googlesyndication)|(get_midroll_info)|(/ads)")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    @SuppressLint("SetJavaScriptEnabled", "RestrictedApi")
    fun init() {
        web.settings.javaScriptEnabled = true
        web.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
                if (url?.contains(adRegex)!!) {
                    return WebResourceResponse("", "UTF-8", null)
                }

                return  super.shouldInterceptRequest(view, url)
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                val url = request?.url!!.toString()

                if (url.contains(adRegex)) {
                    return WebResourceResponse("", "UTF-8", null)
                }

                return  super.shouldInterceptRequest(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (!url?.startsWith("http")!!) return true
                if (!url.contains("youtube.com")) {
                    openBrowser(url)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        web.setOnCWebViewEvents {
            if (it > 500) btnScrollUp.visibility = View.VISIBLE
            else btnScrollUp.visibility = View.GONE
        }

        btnScrollUp.setOnClickListener {
            web.scrollTo(0, 0)
        }

        web.loadUrl("https://www.youtube.com")
    }

    fun openBrowser(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (ignored: Exception) {
            Toast.makeText(this@MainActivity, "Failed to handle link", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (web.canGoBack()) web.goBack()
        else super.onBackPressed()
    }
}
