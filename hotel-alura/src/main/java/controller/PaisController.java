package controller;

import java.util.List;

import dao.PaisDao;
import factory.ConnectionFactory;
import modelo.Pais;

public class PaisController {

	private PaisDao paisDao;
	
	public PaisController() {
		this.paisDao = new PaisDao(new ConnectionFactory().recuperarConexion());
	}
	
	public List<Pais> listar(){
		return paisDao.listar();
	}
}
