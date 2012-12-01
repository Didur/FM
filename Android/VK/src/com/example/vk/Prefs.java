package com.example.vk;

import android.R.string;

public class Prefs {

	 static String token;
	 static String id;
	
	static void  SetToken(String str){
		token=str;
	}
	
	static void  SetId(String str){
		id=str;
	}
	
	static String GetToken() {
		return token;
	}
	
	static String GetId() {
		return id;
	}
	
}
