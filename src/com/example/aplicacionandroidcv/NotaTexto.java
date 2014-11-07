package com.example.aplicacionandroidcv;

import com.example.db.Nota;
import com.example.db.NotaDAO;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class NotaTexto extends ActionBarActivity{
	
	String name, loginu, nombrenota;
	TextView nombre, tag, contenido, horayfecha, lat, longitud;
	NotaDAO dao;
	
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
		latitud = Integer.toString(nota.getLatitud());
		lng = Integer.toString(nota.getLongitud());
		lat.setText(latitud);
		longitud.setText(lng);
		
		
	}
}
