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

public class EdicionCuenta extends ActionBarActivity implements OnClickListener{
	
	EditText edname, edusername, edpass1, edpass2;
	Button btnEdit;
	String name, loginu;
	UsuarioDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_edicion);
		 edname = (EditText) findViewById(R.id.edit_cuenta_name);
		 edusername = (EditText) findViewById(R.id.edit_cuenta_username);
		 edpass1 = (EditText) findViewById(R.id.edit_cuenta_password);
		 edpass2 = (EditText) findViewById(R.id.edit_cuenta_password2);
		 btnEdit = (Button) findViewById(R.id.btn_edicion_edicion);
		 dao = new UsuarioDAO(this);
		 Bundle bundle = getIntent().getExtras();
			if(bundle != null) {
				name = bundle.getString("login");
				loginu = bundle.getString("loginu");
				//Usuario u =	dao.getUsuarioByNombre(name);
				Usuario u =	dao.getUsuario(loginu);
				edname.setText(u.getNombre());
				edusername.setText(u.getUsername());
				edpass1.setText(u.getPassword());
				edpass2.setText(u.getPassword());
							
			}
			else{
				edname.setText("Error");
				edusername.setText("Error");
				edpass1.setText("Error");
				edpass2.setText("Error");
			}
		 btnEdit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		String n, user, p1, p2;
		p1 = edpass1.getText().toString();
		p2 = edpass2.getText().toString();
		String n1=edname.getText().toString();
		String u1 = edusername.getText().toString();
		if(p1.equals(p2)){
			Usuario usuario = new Usuario();
			usuario.setNombre(edname.getText().toString());
			usuario.setUsername(edusername.getText().toString());
			usuario.setPassword(edpass1.getText().toString());
			dao.updateUsuario(n1,u1,p1,name,loginu);
			Toast.makeText(this, "Usuario editado!", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(EdicionCuenta.this, MenuPpal.class);
			intent.putExtra("login", usuario.getNombre());
            startActivity(intent);
		}
		else{
			Toast.makeText(this, "Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
		}
		
	}
}
