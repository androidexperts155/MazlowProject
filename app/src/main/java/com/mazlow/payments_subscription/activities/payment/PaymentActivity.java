package com.mazlow.payments_subscription.activities.payment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Mazlow.R;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.M;
import com.mazlow.payments_subscription.activities.thanksfor_patience.ThanksForPatience;
import com.mazlow.ui.users.topupsuccess.TopUpSuccessActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rl_toolbar;
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.webview)
    WebView webView;

    String url,referenceCode,from;
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        setListner();
        getValueFromintent();
        wenview();
        init();
    }

    private void init() {
        if(from.equals("topup")) {
            rl_toolbar.setVisibility(View.VISIBLE);
            txt_title.setText(getResources().getString(R.string.payment));
        }
    }

    private void wenview() {
        dialog = M.showloader(PaymentActivity.this, "", false, false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        dialog.show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                handleUrl(url);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
        });
        webView.loadUrl(url);
    }

    private void handleUrl(String url) {

        if (url.contains("getpfsPaySuccess") || url.contains("pfsPayUnsuccess"))
        {
            if(url.contains("getpfsPaySuccess") && referenceCode != "");
            {
                if(from.equals("topup")){
                    Intent intent= new Intent(this, TopUpSuccessActivity.class);
                    intent.putExtra("from","topup");
                    intent.putExtra("data",getIntent().getBundleExtra("data"));
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent= new Intent(this, ThanksForPatience.class);
                    startActivity(intent);
                    finish();
                }

            }
        }
    }

    private void getValueFromintent() {
        url=getIntent().getStringExtra("url");
        referenceCode=getIntent().getStringExtra("referenceCode");
        from=getIntent().getStringExtra("from");
    }



    private void setListner() {
        img_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.img_back:
                finish();
                break;
        }
    }
}
