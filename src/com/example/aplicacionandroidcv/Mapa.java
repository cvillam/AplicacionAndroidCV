package com.example.aplicacionandroidcv;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class Mapa extends ActionBarActivity{
	
	WebView wvmapa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		wvmapa = (WebView) findViewById(R.id.wvmapa);
		Bundle bundle=getIntent().getExtras();
		String urlmap = "https://www.google.es/maps/@"+bundle.getString("latitud")+","+
		bundle.getString("longitud")+",19z";
		   wvmapa.loadUrl(urlmap);
	}
}
