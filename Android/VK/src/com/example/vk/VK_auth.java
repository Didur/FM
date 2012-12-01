package com.example.vk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.UriMatcher;
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
            	   String access_token = extractPattern(url, "access_token=(.*?)&");
				   String user_id = extractPattern(url, "user_id=(\\d*)");                         
                   Prefs.SetId(user_id);
                   Prefs.SetToken(access_token);
                   Log.d("ID", Prefs.GetId())  ; 
                   Log.d("TOKEN", Prefs.GetToken())  ; 
                }
			   return false;
            }

			private String extractPattern(String str, String pattern) {
				Pattern p = Pattern.compile(pattern);
	            Matcher m = p.matcher(str);
	            if (!m.find())
	                return null;
	            return m.toMatchResult().group(1);
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
