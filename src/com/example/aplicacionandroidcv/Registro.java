package com.example.aplicacionandroidcv;

import com.example.db.Usuario;
import com.example.db.UsuarioDAO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registro extends ActionBarActivity implements OnClickListener {
	
	EditText name, username, pass, pass2;
	Button registro;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        
        name = (EditText) findViewById(R.id.edit_regNombre);
        username = (EditText) findViewById(R.id.edit_regUser);
        pass = (EditText) findViewById(R.id.edit_regPass);
        pass2 = (EditText) findViewById(R.id.edit_regPass2);
        registro = (Button) findViewById(R.id.btn_reg_registro);
        
        registro.setOnClickListener(this);
           
    }

	@Override
	public void onClick(View v) {
		String p1, p2;
		p1 = pass.getText().toString();
		p2 = pass2.getText().toString();
		if(p1.equals(p2)){
			Usuario u = new Usuario();
			u.setNombre(name.getText().toString());
			u.setUsername(username.getText().toString());
			u.setPassword(pass.getText().toString());
			UsuarioDAO dao = new UsuarioDAO(this);
			dao.insertUsuario(u);
			Toast.makeText(this, "Usuario creado! Ya puedes inciar sesión!", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(Registro.this, InicioSesion.class);
            startActivity(intent);
		}
		else{
			Toast.makeText(this, "Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
		}
	}


    
}