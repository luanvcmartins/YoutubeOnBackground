package io.github.luanvcmartins.youtube;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

public class CWebView extends WebView {
    private CWebViewEvents onCWebViewEvents;

    public CWebView(Context context) {
        super(context);
        super.onWindowVisibilityChanged(View.VISIBLE);
    }

    public CWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Required, otherwise the view won't know if it should do work or not
        super.onWindowVisibilityChanged(View.VISIBLE);
    }

    public CWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.onWindowVisibilityChanged(View.VISIBLE);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        // Don't notify shit. That all it takes.
    }

    public void setOnCWebViewEvents(CWebViewEvents onCWebViewEvents) {
        this.onCWebViewEvents = onCWebViewEvents;
    }

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onCWebViewEvents != null) onCWebViewEvents.pageScroll(t);
    }


    interface CWebViewEvents {
        void pageScroll(int height);
    }
}
