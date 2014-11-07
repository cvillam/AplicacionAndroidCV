package com.example.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NotaDAO {

	SQLiteDatabase db;
	
	public NotaDAO(Context context){
		DataBaseHelper helper = new DataBaseHelper(context);
		db = helper.getWritableDatabase();
		
	}
	public void insertNotaTexto(Nota nota){
		ContentValues cV = new ContentValues();
		cV.put(Nota.COLUMN_NOMBRE, nota.getNombre());
		cV.put(Nota.COLUMN_TAG, nota.getTag());
		cV.put(Nota.COLUMN_TIPO, "texto");
		cV.put(Nota.COLUMN_CONTENIDO, nota.getContenido());
		cV.put(Nota.COLUMN_ARCHIVOSOUND, "noaplica");
		cV.put(Nota.COLUMN_LATITUD, nota.getLatitud());
		cV.put(Nota.COLUMN_LONGITUD, nota.getLongitud());
		cV.put(Nota.COLUMN_HORAFECHA, nota.getHorafecha());
		cV.put(Nota.COLUMN_CREADOR, nota.getCreador());
		db.insert(Nota.TABLE_NAME, null, cV);
	}
	public void insertNotaVoz(Nota nota){
		ContentValues cV = new ContentValues();
		cV.put(Nota.COLUMN_NOMBRE, nota.getNombre());
		cV.put(Nota.COLUMN_TAG, nota.getTag());
		cV.put(Nota.COLUMN_TIPO, "voz");
		cV.put(Nota.COLUMN_CONTENIDO, "noaplica");
		cV.put(Nota.COLUMN_ARCHIVOSOUND, nota.getArchivosound());
		cV.put(Nota.COLUMN_LATITUD, nota.getLatitud());
		cV.put(Nota.COLUMN_LONGITUD, nota.getLongitud());
		cV.put(Nota.COLUMN_HORAFECHA, nota.getHorafecha());
		cV.put(Nota.COLUMN_CREADOR, nota.getCreador());
		db.insert(Nota.TABLE_NAME, null, cV);
	}
	
	public Nota getNotaTextoByNombreCreador(String nombre, String creador){
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+Nota.TABLE_NAME+
				" WHERE ("+Nota.COLUMN_NOMBRE+" = '"+nombre+"' AND creador = '"+creador+"')", null);
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToNext();
				Nota nota = new Nota();
				nota.setId(cursor.getInt(0));
				nota.setNombre(cursor.getString(1));
				nota.setTag(cursor.getString(2));
				nota.setContenido(cursor.getString(4));
				nota.setLatitud(cursor.getFloat(7));
				nota.setLongitud(cursor.getFloat(8));
				nota.setHorafecha(cursor.getString(6));
				nota.setCreador(cursor.getString(9));
				
				return nota;	
			}
		else {
			Nota nerror = new Nota();
			return nerror;
			
		}
					
	}
	public Nota getNotaVozByNombreCreador(String nombre, String creador){
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+Nota.TABLE_NAME+
				" WHERE ("+Nota.COLUMN_NOMBRE+" = '"+nombre+"' AND creador = '"+creador+"')", null);
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToNext();
				Nota nota = new Nota();
				nota.setId(cursor.getInt(0));
				nota.setNombre(cursor.getString(1));
				nota.setTag(cursor.getString(2));
				nota.setArchivosound(cursor.getString(5));
				nota.setLatitud(cursor.getFloat(6));
				nota.setLongitud(cursor.getFloat(7));
				nota.setHorafecha(cursor.getString(8));
				nota.setCreador(cursor.getString(9));
				
				return nota;	
			}
		else {
			Nota nerror = new Nota();
			return nerror;
			
		}
					
	}
	public List<Nota> getAllNotasByCreador(String creador){
		List<Nota> data = new ArrayList<Nota>();
		Cursor cursor = db.rawQuery("SELECT * FROM "+Nota.TABLE_NAME+" WHERE creador = '"+creador+"'", null);
		if(cursor.getCount()>0){
			for(int i = 0; i<cursor.getCount();i++){
				cursor.moveToPosition(i);
				Nota nota = new Nota();
				nota.setId(cursor.getInt(0));
				nota.setNombre(cursor.getString(1));
				nota.setTag(cursor.getString(2));
				nota.setTipo(cursor.getString(3));
				nota.setContenido(cursor.getString(4));
				nota.setArchivosound(cursor.getString(5));
				nota.setLatitud(cursor.getFloat(6));
				nota.setLongitud(cursor.getFloat(7));
				nota.setHorafecha(cursor.getString(8));
				nota.setCreador(cursor.getString(9));
				
				data.add(nota);
				}
			}
		
			return data;	
	}
	public boolean esNotaTexto(String nombre, String creador){
		
		Cursor cursor = db.rawQuery("SELECT tipo FROM "+Nota.TABLE_NAME+
				" WHERE ("+Nota.COLUMN_NOMBRE+" = '"+nombre+"' AND creador = '"+creador+"')", null);
		if(cursor!=null && cursor.getCount()>0){
			cursor.moveToNext();
				String texto="texto";
				String base = cursor.getString(0);
				if(texto.equals(base)){
					return true;
				}else{
					return false;
				}
					
			}
		else {
			
			return false;
			
		}
					
	}
	public List<Nota> getAllNotasByCreadoryTag(String creador, String tag){
		List<Nota> data = new ArrayList<Nota>();
		Cursor cursor = db.rawQuery("SELECT * FROM "+Nota.TABLE_NAME+" WHERE (creador = '"+creador
				+"' AND tag ='"+tag+"')", null);
		if(cursor.getCount()>0){
			for(int i = 0; i<cursor.getCount();i++){
				cursor.moveToPosition(i);
				Nota nota = new Nota();
				nota.setId(cursor.getInt(0));
				nota.setNombre(cursor.getString(1));
				nota.setTag(cursor.getString(2));
				nota.setTipo(cursor.getString(3));
				nota.setContenido(cursor.getString(4));
				nota.setArchivosound(cursor.getString(5));
				nota.setLatitud(cursor.getFloat(6));
				nota.setLongitud(cursor.getFloat(7));
				nota.setHorafecha(cursor.getString(8));
				nota.setCreador(cursor.getString(9));
				
				data.add(nota);
				}
			}
		
			return data;	
	}
	public void deleteNota(Nota nota){
		
		String[] args = new String[2];
		args[0] = ""+nota.getNombre();
		args[1]=""+nota.getCreador();
		//args[1] = "Tierra";
		//db.delete(Planeta.TABLE_NAME, "_id=? AND nombre like '?'", )
		//db.delete(Planeta.TABLE_NAME, "_id=1, null) cuANDO SE COLOCAN LOS PARAMETROS EN LA SENTENCIA
		db.delete(Nota.TABLE_NAME, "nombre = ? AND creador = ?", args);
	}
	
}
