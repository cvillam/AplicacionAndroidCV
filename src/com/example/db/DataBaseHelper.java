package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

	public static final String DB_NAME = "AppCVDatabase";
	public static final int DB_VERSION = 2;
	
	public DataBaseHelper(Context context) {
		
		super(context, DB_NAME,null, DB_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE "+Usuario.TABLE_NAME
				+"("
				+"_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+Usuario.COLUMN_NOMBRE +" VARCHAR,"
				+Usuario.COLUMN_USERNAME+" VARCHAR,"
				+Usuario.COLUMN_PASSWORD+" VARCHAR"
				+")";
		db.execSQL(sql);
		
		ContentValues cV = new ContentValues();
		cV.put(Usuario.COLUMN_NOMBRE, "admin");
		cV.put(Usuario.COLUMN_USERNAME, "admin");
		cV.put(Usuario.COLUMN_PASSWORD, "123");
		db.insert(Usuario.TABLE_NAME, null, cV);
		
		String sql2 = "CREATE TABLE "+Nota.TABLE_NAME
				+"("
				+"_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+Nota.COLUMN_NOMBRE +" VARCHAR,"
				+Nota.COLUMN_TAG+" VARCHAR,"
				+Nota.COLUMN_TIPO+" VARCHAR,"
				+Nota.COLUMN_CONTENIDO+" VARCHAR,"
				+Nota.COLUMN_ARCHIVOSOUND+" VARCHAR,"
				+Nota.COLUMN_HORAFECHA+" VARCHAR,"
				+Nota.COLUMN_LATITUD+" FLOAT,"
				+Nota.COLUMN_LONGITUD+" FLOAT,"
				+Nota.COLUMN_CREADOR+" VARCHAR"
				+")";
		db.execSQL(sql2);
		
		ContentValues cV2 = new ContentValues();
		cV2.put(Nota.COLUMN_NOMBRE, "Nota inicial");
		cV2.put(Nota.COLUMN_TAG, "mensaje");
		cV2.put(Nota.COLUMN_TIPO, "texto");
		cV2.put(Nota.COLUMN_CONTENIDO, "Bienvenido a Notapp");
		cV2.put(Nota.COLUMN_ARCHIVOSOUND, "no");
		cV2.put(Nota.COLUMN_HORAFECHA, "texto");
		cV2.put(Nota.COLUMN_LATITUD, 5);
		cV2.put(Nota.COLUMN_LONGITUD, -70);
		cV2.put(Nota.COLUMN_CREADOR, "admin");
		db.insert(Nota.TABLE_NAME, null, cV2);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE "+Usuario.TABLE_NAME);
		onCreate(db);
		
	}

}

