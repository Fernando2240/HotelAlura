package modelo;

import java.sql.Date;

public class Huesped {

	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String pais;
	private String telefono;
	
	
	
	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String pais, String telefono) {
		this.id = id;
		this.nombre = nombre.toUpperCase();
		this.apellido = apellido.toUpperCase();
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
		this.telefono = telefono;
				
	}
	
	public Huesped(String nombre, String apellido,Date date, String pais, String telefono) {
		this.nombre = nombre.toUpperCase();
		this.apellido = apellido.toUpperCase();
		this.fechaNacimiento = date;
		this.pais = pais;
		this.telefono = telefono;
				
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPais() {
		return pais;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id: "+this.id+" \nNombre: "+this.nombre+"";
	}
}
