package controller;

import java.sql.Date;
import java.time.MonthDay;
import java.util.List;

import dao.ReservaDao;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	
	private ReservaDao reservaDao;
	
	public ReservaController() {
		var factory = new ConnectionFactory().recuperarConexion();
		this.reservaDao = new ReservaDao(factory);
	}
	
	public void guardar(Reserva reserva) {
		reservaDao.guardar(reserva);
	}
	
	public List<Reserva> buscar(String busqueda) {
		return reservaDao.buscar(busqueda);
	}
	
	public Integer modificar(Reserva reserva, Integer id) {
		reserva.setId(id);
		return reservaDao.modificar(reserva);
	}
	
	public Integer eliminar(Integer id) {
		return reservaDao.eliminar(id);
	}
	

	public List<Reserva> listar() {
		return reservaDao.listar();
	}
}
