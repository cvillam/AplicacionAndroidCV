package com.example.aplicacionandroidcv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class InicioSesion extends ActionBarActivity {
	
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        
        Button boton_registro = (Button) findViewById(R.id.btn_registro);
        Button boton_inicio_sesion = (Button) findViewById(R.id.btn_inicio_sesion);
        
        boton_registro.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
              Intent intent = new Intent(InicioSesion.this, Registro.class);
              startActivity(intent);
           }
        });
           
    }


    
}
