package com.example.aplicacionandroidcv;

import java.text.DateFormat;
import java.util.Date;

import com.example.db.Nota;
import com.example.db.NotaDAO;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngNotaTexto extends ActionBarActivity implements OnClickListener{

		EditText nombre, tag, content;
		Button crear;
		LocationManager locationManager;
		String provider;
		String name, loginu;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_nota_texto);
			
			nombre = (EditText) findViewById(R.id.edit_ingnt_nombre);
			tag = (EditText) findViewById(R.id.edit_ingnt_tag);
			content = (EditText) findViewById(R.id.edit_ingnt_contenido);
			crear = (Button) findViewById(R.id.btn_ingnt_crearnotatexto);
			Bundle bundle = getIntent().getExtras();
			if(bundle != null) {
				name = bundle.getString("login");
				loginu = bundle.getString("loginu");
				
							
			}
			
			
			
			crear.setOnClickListener(this);
			
		}

		@Override
		public void onClick(View v) {
			int lat, lng;
			Nota nota = new Nota();
			nota.setNombre(nombre.getText().toString());
			nota.setTag(tag.getText().toString());
			nota.setContenido(content.getText().toString());
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		    Criteria criteria = new Criteria();
		    provider = locationManager.getBestProvider(criteria, false);
		    Location location = locationManager.getLastKnownLocation(provider);
	    
		    if (location != null) {
		    	lat = (int) (location.getLatitude());
			    lng = (int) (location.getLongitude());
			    nota.setLatitud(lat);
			    nota.setLongitud(lng);
		      
		    } else {
		    	nota.setLatitud(0);
			    nota.setLongitud(0);
		    }
		    nota.setCreador(loginu);
		    String horafecha = DateFormat.getDateTimeInstance().format(new Date());
		    nota.setHorafecha(horafecha);
		    NotaDAO dao = new NotaDAO(this);
		    dao.insertNotaTexto(nota);
		    Toast.makeText(this, "Se ha creado la nota!", Toast.LENGTH_LONG).show();
		    Intent intent = new Intent(IngNotaTexto.this, MenuPpal.class);
			intent.putExtra("login", name);
			intent.putExtra("loginu", loginu);
			startActivity(intent);
		}
		
		 
}
