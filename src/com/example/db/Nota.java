package com.example.db;

public class Nota {
	
	public static final String TABLE_NAME = "Nota";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOMBRE = "nombre";
	public static final String COLUMN_TAG = "tag";
	public static final String COLUMN_TIPO = "tipo";
	public static final String COLUMN_CONTENIDO = "contenido";
	public static final String COLUMN_ARCHIVOSOUND = "archivosound";
	public static final String COLUMN_LATITUD = "latitud";
	public static final String COLUMN_LONGITUD = "longitud";
	public static final String COLUMN_HORAFECHA = "horafecha";
	public static final String COLUMN_CREADOR = "creador";
	
		
	int id;
	String nombre, tag, tipo, contenido, archivosound, horafecha;
	int latitud, longitud;
	String creador;
	
	
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getArchivosound() {
		return archivosound;
	}
	public void setArchivosound(String archivosound) {
		this.archivosound = archivosound;
	}
	public String getHorafecha() {
		return horafecha;
	}
	public void setHorafecha(String horafecha) {
		this.horafecha = horafecha;
	}
		
	public int getLatitud() {
		return latitud;
	}
	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	public Nota(String nombre, String tag, String tipo, String contenido,
			String archivosound, String horafecha, int latitud, int longitud,
			String creador) {
		
		this.nombre = nombre;
		this.tag = tag;
		this.tipo = tipo;
		this.contenido = contenido;
		this.archivosound = archivosound;
		this.horafecha = horafecha;
		this.latitud = latitud;
		this.longitud = longitud;
		this.creador = creador;
	}
	public Nota() {
		
	}
	
	
	
}
