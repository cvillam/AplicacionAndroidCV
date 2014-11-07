package com.example.aplicacionandroidcv;

import com.example.db.Nota;
import com.example.db.NotaDAO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NotaTexto extends ActionBarActivity implements OnClickListener{
	
	String name, loginu, nombrenota, latitudenviar, longitudenviar;
	TextView nombre, tag, contenido, horayfecha, lat, longitud;
	NotaDAO dao;
	Button vermap, eliminarbtn;
	WebView webmapa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_nt);
		
		nombre = (TextView) findViewById(R.id.txt_detnt_nombre);
		tag = (TextView) findViewById(R.id.txt_detnt_tag);
		contenido = (TextView) findViewById(R.id.txt_detnt_contenido);
		horayfecha = (TextView) findViewById(R.id.txt_detnt_horafecha);
		lat = (TextView) findViewById(R.id.txt_detnt_latitud);
		longitud = (TextView) findViewById(R.id.txt_detnt_longitud);
		vermap = (Button) findViewById(R.id.btn_vermapa);
		eliminarbtn = (Button) findViewById(R.id.btn_eliminarnota);
		webmapa = (WebView) findViewById(R.id.webvmap);
			
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			name = bundle.getString("login");
			loginu = bundle.getString("loginu");
			nombrenota = bundle.getString("nombre_nota"); 
								
		}
		dao = new NotaDAO(this);
		Nota nota = new Nota();
		nota = dao.getNotaTextoByNombreCreador(nombrenota, loginu);
		nombre.setText(nota.getNombre());
		tag.setText(nota.getTag());
		contenido.setText(nota.getContenido());
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
	}

	@Override
	public void onClick(View v) {
		
		String urlmap = "https://www.google.es/maps/@"+latitudenviar+","+
				longitudenviar+",18z";
				   webmapa.loadUrl(urlmap);
		
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
		                		
		                		Intent nnt = new Intent(NotaTexto.this, VerNotas.class);
		                		nnt.putExtra("login", name);
		                		nnt.putExtra("loginu", loginu);
		                       startActivity(nnt);
		                    }
		                });
		AlertDialog alert = builder.create();
		alert.show();
		
		
	}
}
