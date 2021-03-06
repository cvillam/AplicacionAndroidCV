package com.example.aplicacionandroidcv;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.db.Nota;
import com.example.db.NotaDAO;

public class VerNotas extends ActionBarActivity implements  OnItemSelectedListener{
	
	ListView listView;
	NotaDAO dao;
	String name, loginu, tagspinner;
	Spinner tagbuscar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_notas);
		listView = (ListView) findViewById(R.id.listnotas);
		tagbuscar = (Spinner) findViewById(R.id.spinner_vernotas_tag);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			name = bundle.getString("login");
			loginu = bundle.getString("loginu");
								
		}
		llenarListaNombresNotas();
		listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
             String  itemValue    = (String) listView.getItemAtPosition(position);
           	 if(dao.esNotaTexto(itemValue, loginu)==true){
           		Intent intent = new Intent(VerNotas.this, NotaTexto.class);
              	 intent.putExtra("login", name);
              	 intent.putExtra("loginu", loginu);
              	 intent.putExtra("nombre_nota", itemValue);
              	 
              	 startActivity(intent); 
           	 }
           	 else{
           		Intent intent = new Intent(VerNotas.this, NotaVoz.class);
             	 intent.putExtra("login", name);
             	 intent.putExtra("loginu", loginu);
             	 intent.putExtra("nombre_nota", itemValue);
             	 
             	 startActivity(intent); 
           	 }
             
          }
        });
		tagbuscar.setOnItemSelectedListener(this);
		
		
	}
	public void llenarListaNombresNotas(){
		dao = new NotaDAO(this);
		List<Nota> notas = dao.getAllNotasByCreador(loginu);
		 String[] nombresnotas = new String[notas.size()];
		 for(int i =0;i< notas.size();i++ ){
			 
			 nombresnotas[i] = notas.get(i).getNombre();
		 }
		 
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, nombresnotas);
		 listView.setAdapter(adapter);
		 adapter.notifyDataSetChanged();
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		tagspinner = tagbuscar.getSelectedItem().toString();
		if(tagbuscar.getSelectedItem().toString().equals("Todas las notas")){
			llenarListaNombresNotas();
		}
		else{
			List<Nota> notas = dao.getAllNotasByCreadoryTag(loginu, tagspinner);
			 String[] nombresnotas = new String[notas.size()];
			 for(int i =0;i< notas.size();i++ ){
				 
				 nombresnotas[i] = notas.get(i).getNombre();
			 }
			 
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		              android.R.layout.simple_list_item_1, android.R.id.text1, nombresnotas);
			 listView.setAdapter(adapter);
			 adapter.notifyDataSetChanged();
		}
		
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBackPressed() {
		Intent nnt = new Intent(VerNotas.this, MenuPpal.class);
		nnt.putExtra("login", name);
		nnt.putExtra("loginu", loginu);
       startActivity(nnt);
	}
		
	

}
