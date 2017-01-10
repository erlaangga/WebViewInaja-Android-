package com.example.android.webviewinaja;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {

    static boolean udah = false;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.v("MainActiv", "udah "+udah);
        if (udah==false) {
            final ImageView imageView = (ImageView) findViewById(R.id.activity_splash_screen);
            imageView.setVisibility(ImageView.VISIBLE);
            Thread thread = new Thread() {
                public void run() {
                    try {
                        for (long i = 0xFFFFFFFF; i >= 0x96FFFFFF; i -= (16777216/64)) {
                            imageView.setBackgroundColor(((int) i));
                        }
                    sleep(1000);
////                    }
//                    sleep(100);
//                    imageView.setBackgroundColor(0x46FFFFFF);
//                        sleep(1000);
                        imageView.setVisibility(ImageView.GONE);
                    } catch (Exception e) {

                    }
                }
            };

            //entah kenapa pake Thread ngga kejalannin semua skripnya
            thread.start();
            udah = true;
        }
        webView = (WebView) findViewById(R.id.webku);
//        webView.loadUrl("http://pemula.linux.or.id");
        webView.loadUrl("file:///android_asset/pemula-offline/index.html");
        //setting agar javascript yang ada disitus work di webview
        WebSettings webSettings = webView.getSettings();
        Toast.makeText(this, "Tampilan: " + webSettings.getTextZoom(), Toast.LENGTH_SHORT);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(

        ));

        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //method goback(),untuk kembali ke halaman sebelumnya
            return true;
        }
        // Jika tidak ada halaman yang pernah dibuka
        // maka akan keluar dari activity (tutup aplikasi)
        return super.onKeyDown(keyCode, event);
    }
}
