package com.example.db;

public class Usuario {

	public static final String TABLE_NAME = "usuario";
	public static final String COLUMN_NOMBRE = "nombre";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_ID = "id";
	
	
	int id;
	String nombre, username, password;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Usuario(String nombre, String username, String password) {
		
		this.nombre = nombre;
		this.username = username;
		this.password = password;
	}
	public Usuario() {
		
	}
	
	
	
	
}
