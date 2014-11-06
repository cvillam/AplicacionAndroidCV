package com.example.aplicacionandroidcv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.Usuario;
import com.example.db.UsuarioDAO;


public class InicioSesion extends ActionBarActivity implements OnClickListener {
	
	 
	 Button boton_registro;
	 Button boton_inicio_sesion;
	 EditText username, pass;
	 UsuarioDAO dao;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        
        boton_registro = (Button) findViewById(R.id.btn_registro);
        boton_inicio_sesion = (Button) findViewById(R.id.btn_inicio_sesion);
        username = (EditText) findViewById(R.id.edit_iniUsuario);
        pass = (EditText) findViewById(R.id.edit_iniContrasena);
        dao = new  UsuarioDAO(this);
        
        boton_registro.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
              Intent intent = new Intent(InicioSesion.this, Registro.class);
              startActivity(intent);
           }
        });
        
        boton_inicio_sesion.setOnClickListener(this);
           
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String u, p;
		u = username.getText().toString();
		p = pass.getText().toString();
		Usuario usuario = dao.getUsuario(u);
		String p2 = usuario.getPassword();
		String nameu = usuario.getNombre();
		if(p.equals(p2)){
			Toast.makeText(this, "Correcto! Bienvenido "+nameu+"!", Toast.LENGTH_SHORT).show();
			Intent mppal = new Intent(InicioSesion.this, MenuPpal.class);
			mppal.putExtra("login", usuario.getNombre());
			mppal.putExtra("loginu", usuario.getUsername());
            startActivity(mppal);
		}
		else{
			Toast.makeText(this, "Error en usuario o contraseña! Intenta nuevamente", Toast.LENGTH_LONG).show();
		}
		
	}


    
}
