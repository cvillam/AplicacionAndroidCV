package com.example.aplicacionandroidcv;

import java.io.IOException;
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
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IngNotaVoz extends ActionBarActivity implements OnItemSelectedListener, HttpAsyncInterface{
	
	EditText nombre;
	Spinner tag;
	String name, loginu;
	MediaRecorder myRecorder;
	Button start, stop, crearnotavoz;
	String outputFile = null;
	String pathcompleto;
	LocationManager locationManager;
	String provider;
	String tagenviar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota_voz);
		nombre = (EditText) findViewById(R.id.edit_ingnv_nombre);
		tag = (Spinner) findViewById(R.id.spinner_ingnv_tag);
		start = (Button) findViewById(R.id.btn_ingnv_startRecord);
		stop = (Button) findViewById(R.id.btn_ingnv_stopRecord);
		crearnotavoz = (Button) findViewById(R.id.btn_ingnt_crearnotavoz);
		start.setEnabled(true);
		stop.setEnabled(false);
		crearnotavoz.setEnabled(false);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			name = bundle.getString("login");
			loginu = bundle.getString("loginu");
								
		}
		
		outputFile = "/sonidos/";
		pathcompleto = Environment.getExternalStorageDirectory().
	    		  getAbsolutePath() + "/sonidos/";

	      myRecorder = new MediaRecorder();
	      myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	      myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	      myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
	      
	      
	      start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				iniciarGrabacion(v);
				
			}
		});
	      stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pararGrabacion(v);
				
			}
		});
	      crearnotavoz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				crearNotaVoz(v);
				
			}
		});
	      tag.setOnItemSelectedListener(this);
	}
	public void iniciarGrabacion(View v){
		start.setEnabled(false);
		if(nombre.getText().toString().isEmpty()){
			Toast.makeText(this, "Primero debe ingresar el nombre de la nota!", Toast.LENGTH_LONG).show();
		}
		else{
			NotaDAO daoprueba = new NotaDAO(this);
			if(daoprueba.nombreNotaYaExiste(nombre.getText().toString(), loginu)==true){
				Toast.makeText(this, "El nombre de la nota ya existe, debe cambiarlo!", Toast.LENGTH_LONG).show();
			}
			else{
			pathcompleto = pathcompleto+nombre.getText().toString()+".3gpp";
			outputFile = outputFile+nombre.getText().toString()+".3gpp";
			myRecorder.setOutputFile(pathcompleto);
			 try {
				myRecorder.prepare();
				myRecorder.start();
				Toast.makeText(this, "Ha iniciado la grabación", Toast.LENGTH_LONG).show();
				stop.setEnabled(true);
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
	public void pararGrabacion(View v){
		 myRecorder.stop();
	      myRecorder.release();
	      myRecorder  = null;
	      Toast.makeText(this, "Grabación finalizada", Toast.LENGTH_LONG).show();
	      stop.setEnabled(false);
	      crearnotavoz.setEnabled(true);
	      ;
	}
	public void crearNotaVoz(View v){
		float lat, lng;
		Nota nota = new Nota();
		nota.setNombre(nombre.getText().toString());
		//nota.setTag(tag.getText().toString());
		nota.setTag(tagenviar);
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
	    nota.setArchivosound(outputFile);
	    NotaDAO dao = new NotaDAO(this);
	    dao.insertNotaVoz(nota);
	    //parte web
	    HttpAsyncTask task= new HttpAsyncTask(this
				,"accion=ingresar&nombre="+nombre.getText().toString()+
				"&tag="+tagenviar+
				"&tipo=voz"+
				"&contenido=noaplica"+
				"&archivosound="+outputFile+
				"&horafecha="+horafecha+
				"&latitud="+lat+
				"&longitud="+lng+
				"&creador="+loginu			
									
	    		);
		task.execute("http://192.168.10.102:8080/NotappBackEnd/NotaServlet");
	    
	    Toast.makeText(this, "Se ha creado la nota!", Toast.LENGTH_LONG).show();
	    Intent intent = new Intent(IngNotaVoz.this, MenuPpal.class);
		intent.putExtra("login", name);
		intent.putExtra("loginu", loginu);
		startActivity(intent);
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		tagenviar=tag.getSelectedItem().toString();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	public void setResponse(String rta) {
		
	}
}
