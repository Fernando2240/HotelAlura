package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pais;

public class PaisDao {

	private Connection con;
	
	public PaisDao(Connection con) {
		this.con = con;
	}
	
	public List<Pais> listar(){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT NOMBRE FROM PAIS");
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					List<Pais> resultado = new ArrayList<>();
					
					while(resultSet.next()) {
						resultado.add(new Pais(resultSet.getString("NOMBRE")));
					}
					
					return resultado;
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
