package com.example.aplicacionandroidcv;

import java.io.File;
import java.io.IOException;

import com.example.db.Nota;
import com.example.db.NotaDAO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NotaVoz extends ActionBarActivity implements OnClickListener{
	
	String name, loginu, nombrenota, latitudenviar, longitudenviar;
	TextView nombre, tag, horayfecha, lat, longitud;
	NotaDAO dao;
	WebView webmapanv;
	Button play, stop, vermap, eliminarbtn;
	MediaPlayer myPlayer;
	String path;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_nv);
		nombre = (TextView) findViewById(R.id.txt_detnv_nombre);
		tag = (TextView) findViewById(R.id.txt_detnv_tag);
		horayfecha = (TextView) findViewById(R.id.txt_detnv_horafecha);
		lat = (TextView) findViewById(R.id.txt_detnv_latitud);
		longitud = (TextView) findViewById(R.id.txt_detnv_longitud);
		play = (Button) findViewById(R.id.btn_nv_reproducir);
		stop = (Button) findViewById(R.id.btn_nv_pararReprod);
		vermap = (Button) findViewById(R.id.btn_nv_vermapa);
		eliminarbtn = (Button) findViewById(R.id.btn_nv_eliminarnota);
		webmapanv = (WebView) findViewById(R.id.webvmapNotaVoz);
		stop.setEnabled(false);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			name = bundle.getString("login");
			loginu = bundle.getString("loginu");
			nombrenota = bundle.getString("nombre_nota"); 
								
		}
		dao = new NotaDAO(this);
		Nota nota = new Nota();
		nota = dao.getNotaVozByNombreCreador(nombrenota, loginu);
		nombre.setText(nota.getNombre());
		path = Environment.getExternalStorageDirectory().
	    		  getAbsolutePath() +nota.getArchivosound();
		tag.setText(nota.getTag());
		horayfecha.setText(nota.getHorafecha());
		String latitud, lng;
		latitud = String.valueOf(nota.getLatitud());
		lng = String.valueOf(nota.getLongitud());
		latitudenviar = latitud;
		longitudenviar = lng;
		lat.setText(latitud);
		longitud.setText(lng);
		
		vermap.setOnClickListener(this);
		eliminarbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				eliminarNota();
			}
		});
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playSound(v);
				
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopPlay(v);
			}
		});
	}
	@Override
	public void onClick(View v) {
		
		String urlmap = "https://www.google.es/maps/@"+latitudenviar+","+
				longitudenviar+",18z";
				   webmapanv.loadUrl(urlmap);
		
	}
	public void eliminarNota(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("¿Desea eliminar la nota?")
		        .setTitle("Advertencia")
		        .setCancelable(false)
		        .setNegativeButton("Cancelar",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                        dialog.cancel();
		                    }
		                })
		        .setPositiveButton("Aceptar",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                    	Nota notaborrar = new Nota();
		                		notaborrar.setNombre(nombrenota);
		                		notaborrar.setCreador(loginu);
		                		dao.deleteNota(notaborrar);
		                		 
		                		File file = new File(path);
		                		boolean deleted = file.delete();
		                		Intent nnt = new Intent(NotaVoz.this, VerNotas.class);
		                		nnt.putExtra("login", name);
		                		nnt.putExtra("loginu", loginu);
		                       startActivity(nnt);
		                    }
		                });
		AlertDialog alert = builder.create();
		alert.show();
		
		
	}
	public void playSound(View v){
		play.setEnabled(false);
		stop.setEnabled(true);
		myPlayer = new MediaPlayer();
		   try {
			myPlayer.setDataSource(path);
			myPlayer.prepare();
			   myPlayer.start();
				Toast.makeText(this, "Inicia reproducción", Toast.LENGTH_LONG).show();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
	public void stopPlay(View view) {
		   try {
		       if (myPlayer != null) {
		    	   myPlayer.stop();
		           myPlayer.release();
		           myPlayer = null;
		           play.setEnabled(true);
		           stop.setEnabled(false);
		           
		           
		           Toast.makeText(getApplicationContext(), "Parando reproducción", 
						   Toast.LENGTH_SHORT).show();
		       }
		   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
}
