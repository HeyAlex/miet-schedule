package heyalex.com.miet_schedule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;

/**
 * Created by alexf on 06.04.2017.
 */

public class BaseWebFragement extends Fragment{

    private WebView webView;

    private ProgressBar progressBar;

    private String url;

    public static Fragment newInstance(String url) {
        BaseWebFragement f = new BaseWebFragement();
        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.orioks_item, container, false);
        webView = (WebView) root.findViewById(R.id.webview);
        progressBar = (ProgressBar) root.findViewById(R.id.webview_progressbar);
        url = getArguments() != null ? getArguments().getString("url") : "";
        configWebView();
        setWebViewProgress();
        webView.loadUrl(url);
        ButterKnife.bind(this, root);
        return root;
    }

    protected void configWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    private void setWebViewProgress() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
    @Override
    public void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
