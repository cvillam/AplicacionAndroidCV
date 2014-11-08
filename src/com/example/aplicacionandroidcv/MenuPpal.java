package com.example.aplicacionandroidcv;

import java.text.DateFormat;
import java.util.Date;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuPpal extends ActionBarActivity {

	TextView txt, login;
	Button newnotatxt, newnotav, vnotas, edit, close;
	String name, loginu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menuppal);
		txt = (TextView) findViewById(R.id.fecha);
		login = (TextView) findViewById(R.id.mppal_logueado);
		newnotatxt = (Button) findViewById(R.id.btn_ing_nota_txt);
		newnotav = (Button) findViewById(R.id.btn_ing_nota_voz);
		vnotas = (Button) findViewById(R.id.btn_ver_notas);
		edit = (Button) findViewById(R.id.btn_editar_cuenta);
		close = (Button) findViewById(R.id.btn_close);
	
		String horafecha = DateFormat.getDateTimeInstance().format(new Date());
		txt.setText(horafecha);
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			name = bundle.getString("login");
			loginu = bundle.getString("loginu");
			login.setText("Logueado como: "+name);
						
		}
		else{
			login.setText("Error en inicio de sesión");
		}
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirEdicion(v);
				
			}
		});
		newnotatxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirNuevaNotaTexto(v);
				
			}
		});
		vnotas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirVerNotas(v);
				
			}
		});
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent edicion = new Intent(MenuPpal.this, InicioSesion.class);
				startActivity(edicion);
				
				
			}
		});
		newnotav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirNuevaNotaVoz(v);
				
			}
		});
	}
	public void abrirEdicion(View v){
		Intent edicion = new Intent(MenuPpal.this, EdicionCuenta.class);
		edicion.putExtra("login", name);
		edicion.putExtra("loginu", loginu);
       startActivity(edicion);
	}
	public void abrirNuevaNotaTexto(View v){
		Intent nnt = new Intent(MenuPpal.this, IngNotaTexto.class);
		nnt.putExtra("login", name);
		nnt.putExtra("loginu", loginu);
       startActivity(nnt);
	}
	public void abrirVerNotas(View v){
		Intent nnt = new Intent(MenuPpal.this, VerNotas.class);
		nnt.putExtra("login", name);
		nnt.putExtra("loginu", loginu);
       startActivity(nnt);
	}
	public void abrirNuevaNotaVoz(View v){
		Intent nnt = new Intent(MenuPpal.this, IngNotaVoz.class);
		nnt.putExtra("login", name);
		nnt.putExtra("loginu", loginu);
       startActivity(nnt);
	}
	@Override
	public void onBackPressed() {
	}
}
