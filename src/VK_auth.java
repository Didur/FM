package com.example.vk;

import org.json.JSONArray;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.v4.app.NavUtils;

public class VK_auth extends Activity {

	WebView authWebView;
	
    @SuppressLint({ "ParserError", "ParserError" }) @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authWebView = new WebView(this);
        
        authWebView.getSettings().setJavaScriptEnabled(true);
        authWebView.getSettings().setPluginsEnabled(true);
        authWebView.getSettings().setSupportZoom(false);  
        authWebView.setInitialScale(0);
        
        authWebView.setWebViewClient(new WebViewClient() {
        	@Override
            public boolean shouldOverrideUrlLoading (WebView view, String url) {             
               super.shouldOverrideUrlLoading (view, url);   
               if (url.contains("access_token")) {
                 Uri x = Uri.parse(url);           
                 Log.d("fgdfdsf", x.getFragment())       ; 
                }
			   return false;
            }     	
        });
        
        authWebView.loadUrl("https://oauth.vk.com/authorize?client_id=2967122&scope=messages,friends,video,offline&redirect_uri=http://oauth.vk.com/blank.html&display=mobile&response_type=token") ;
        
        setContentView(R.layout.activity_vk_auth);
        setContentView(authWebView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vk_auth, menu);
        return true;
    }

    
}
