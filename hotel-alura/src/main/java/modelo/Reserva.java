package modelo;

import java.sql.Date;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private double precio ;
	private String formaPago;
	private String nombreHuesped;
	private Integer idHuesped;
	
	public Reserva(String nombreHuesped, Integer id,Date fechaEntrada, Date fechaSalida, double precio, String formaPago) {
		this.nombreHuesped = nombreHuesped;
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
		this.formaPago = formaPago;
		
	}
	
	public Reserva(Date fechaEntrada, Date fechaSalida, double precio, String formaPago, Integer idHuesped) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
		this.formaPago = formaPago;
		this.idHuesped = idHuesped;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	} 
	
	public String getNombreHuesped() {
		return this.nombreHuesped;
	}

	public Integer getIdHuesped() {
		return this.idHuesped;
	}
	
	
}
