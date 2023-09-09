package controller;

import java.util.List;

import dao.HuespedDao;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {

	private HuespedDao huespedDao;
	
	public HuespedController() {
		var factory = new ConnectionFactory().recuperarConexion();
		this.huespedDao = new HuespedDao(factory);
	}
	
	public Integer guardar(Huesped huesped) {
		return huespedDao.guardar(huesped);
	}
	
	public List<Huesped> buscar(String busqueda){
		return huespedDao.buscar(busqueda);
	}
	
	public Integer eliminar(Integer id) {
		return huespedDao.eliminar(id);
	}
	
	public Integer modificar(Huesped huesped) {
		return huespedDao.modificar(huesped);
	}

	public List<Huesped> listar() {
		return huespedDao.listar();
	}
}
