package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {
	
	SQLiteDatabase db;
	
	public UsuarioDAO(Context context){
		DataBaseHelper helper = new DataBaseHelper(context);
		db = helper.getWritableDatabase();
		
	}
	public void insertUsuario(Usuario usuario){
		ContentValues cV = new ContentValues();
		cV.put(Usuario.COLUMN_NOMBRE, usuario.getNombre());
		cV.put(Usuario.COLUMN_USERNAME, usuario.getUsername());
		cV.put(Usuario.COLUMN_PASSWORD, usuario.getPassword());
		db.insert(Usuario.TABLE_NAME, null, cV);
	}
	
	public Usuario getUsuario(String username){
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+Usuario.TABLE_NAME+
				" WHERE "+Usuario.COLUMN_USERNAME+" = '"+username+"'", null);
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToNext();
				Usuario user = new Usuario();
				user.setId(cursor.getInt(0));
				user.setNombre(cursor.getString(1));
				user.setUsername(cursor.getString(2));
				user.setPassword(cursor.getString(3));
				
				return user;	
			}
			
		else {
			Usuario uerror = new Usuario();
			return uerror;
			
		}
					
	}
public Usuario getUsuarioByNombre(String nombre){
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+Usuario.TABLE_NAME+
				" WHERE "+Usuario.COLUMN_NOMBRE+" = '"+nombre+"'", null);
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToNext();
				Usuario user = new Usuario();
				user.setId(cursor.getInt(0));
				user.setNombre(cursor.getString(1));
				user.setUsername(cursor.getString(2));
				user.setPassword(cursor.getString(3));
				
				return user;	
			}
			
		else {
			Usuario uerror = new Usuario();
			return uerror;
			
		}
					
	}
//	public void updateUsuario(Usuario user){
//		
//		String[] args = new String[1];
//		args[0] = user.getNombre();
//		ContentValues cV = new ContentValues();
//		cV.put(Usuario.COLUMN_NOMBRE, user.getNombre());
//		cV.put(Usuario.COLUMN_USERNAME, user.getUsername());
//		cV.put(Usuario.COLUMN_PASSWORD, user.getPassword());
//		db.update(Usuario.TABLE_NAME, cV, "nombre = ? ", args);
//	}
//public void updateUsuario(Usuario user){
//		
////		String[] args = new String[1];
////		args[0] = user.getNombre();
//		ContentValues cV = new ContentValues();
//		cV.put(Usuario.COLUMN_NOMBRE, user.getNombre());
//		cV.put(Usuario.COLUMN_USERNAME, user.getUsername());
//		cV.put(Usuario.COLUMN_PASSWORD, user.getPassword());
//		db.update(Usuario.TABLE_NAME, cV, "_id="+user.getId(), null);
//	}

public void updateUsuario(String nombre, String username,String password, String name, String user1){
		
	db.execSQL("UPDATE usuario SET nombre='"+nombre+"',username='"+username
			+"',password='"+password+"' WHERE (nombre='"+name+"' AND username='"+user1+"')");
	}
}
