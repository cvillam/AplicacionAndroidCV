package com.example.aplicacionandroidcv;

import java.text.DateFormat;
import java.util.Date;

import com.example.db.Nota;
import com.example.db.NotaDAO;
import com.example.networking.HttpAsyncTask;
import com.example.networking.HttpAsyncTask.HttpAsyncInterface;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IngNotaTexto extends ActionBarActivity implements OnClickListener, OnItemSelectedListener, HttpAsyncInterface{

		//EditText tag;
		EditText nombre, content;
		Spinner tag;
		Button crear;
		LocationManager locationManager;
		String provider;
		String name, loginu;
		String tagenviar;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_nota_texto);
			
			nombre = (EditText) findViewById(R.id.edit_ingnt_nombre);
			//tag = (EditText) findViewById(R.id.edit_ingnt_tag);
			tag = (Spinner) findViewById(R.id.spinner_ingnt_tag);
			content = (EditText) findViewById(R.id.edit_ingnt_contenido);
			crear = (Button) findViewById(R.id.btn_ingnt_crearnotatexto);
			Bundle bundle = getIntent().getExtras();
			if(bundle != null) {
				name = bundle.getString("login");
				loginu = bundle.getString("loginu");
				
							
			}
			
			tag.setOnItemSelectedListener(this);
			
			crear.setOnClickListener(this);
			
		}

		@Override
		public void onClick(View v) {
			NotaDAO dao = new NotaDAO(this);
			if((nombre.getText().toString().length()*content.getText().length())==0){
				Toast.makeText(this, "Debe llenar todos los campos!", Toast.LENGTH_LONG).show();
			}
			else{
				 if(dao.nombreNotaYaExiste(nombre.getText().toString(), loginu)==false){
						float lat, lng;
						Nota nota = new Nota();
						nota.setNombre(nombre.getText().toString());
						//nota.setTag(tag.getText().toString());
						nota.setTag(tagenviar);
						nota.setContenido(content.getText().toString());
						locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
					    Criteria criteria = new Criteria();
					    provider = locationManager.getBestProvider(criteria, false);
					    Location location = locationManager.getLastKnownLocation(provider);
				    
					    if (location != null) {
					    	lat = (float) (location.getLatitude());
						    lng = (float) (location.getLongitude());
						    nota.setLatitud(lat);
						    nota.setLongitud(lng);
					      
					    } else {
					    	lat = 0;
						    lng = 0;
					    	nota.setLatitud(0);
						    nota.setLongitud(0);
					    }
					    nota.setCreador(loginu);
					    String horafecha = DateFormat.getDateTimeInstance().format(new Date());
					    nota.setHorafecha(horafecha);
					    
					    dao.insertNotaTexto(nota);
					    //parte web
					    HttpAsyncTask task= new HttpAsyncTask(this
								,"accion=ingresar&nombre="+nombre.getText().toString()+
								"&tag="+tagenviar+
								"&tipo=texto"+
								"&contenido="+content.getText().toString()+
								"&archivosound=noaplica"+
								"&horafecha="+horafecha+
								"&latitud="+lat+
								"&longitud="+lng+
								"&creador="+loginu			
													
					    		);
						task.execute("http://192.168.10.102:8080/NotappBackEnd/NotaServlet");
						
					    Toast.makeText(this, "Se ha creado la nota!", Toast.LENGTH_LONG).show();
					    Intent intent = new Intent(IngNotaTexto.this, MenuPpal.class);
						intent.putExtra("login", name);
						intent.putExtra("loginu", loginu);
						startActivity(intent);
					}else{
						Toast.makeText(this, "Ya existe una nota con el nombre dado, por favor cambielo!", Toast.LENGTH_LONG).show();
					}
			}
			
			
		}

		

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			tagenviar = tag.getSelectedItem().toString();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		public void setResponse(String rta) {
			
		}
		
		 
}
