package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedDao {

	private Connection con;
	
	public HuespedDao(Connection con) {
		this.con = con;
	}
	
	public Integer eliminar(Integer id){
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPED WHERE ID = ?",PreparedStatement.RETURN_GENERATED_KEYS);
			
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				
				return statement.getUpdateCount();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Integer modificar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPED SET NOMBRE=? ,"
					+ "APELLIDO=? , FECHANACIMIENTO=?, PAIS=?, TELEFONO=? WHERE ID=?");
			
			try(statement){
				
				return setQuery(huesped, statement).getUpdateCount();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Integer guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPED(NOMBRE,APELLIDO,"
					+ "FECHANACIMIENTO,PAIS,TELEFONO) VALUES(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			try(statement){
				
				final ResultSet resultSet = setQuery(huesped, statement).getGeneratedKeys();
				
				try(resultSet){
					
					while(resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
					}
					
					return huesped.getId();
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Huesped> buscar(String busqueda){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,NOMBRE,APELLIDO,FECHANACIMIENTO,"
					+ "PAIS,TELEFONO FROM HUESPED WHERE (NOMBRE LIKE ? OR APELLIDO LIKE ? OR FECHANACIMIENTO=? "
					+ "OR TELEFONO LIKE ? OR PAIS LIKE ?)");
			try(statement){
				statement.setString(1,"%" + busqueda + "%");
				statement.setString(2, "%" + busqueda + "%");
				try {
					statement.setDate(3,Date.valueOf(busqueda));
				}catch(IllegalArgumentException e) {
					statement.setDate(3,null);
				}
				statement.setString(4,"%" + busqueda + "%");
				statement.setString(5,"%" + busqueda + "%");
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					List<Huesped> resultado = new ArrayList<>();
					while(resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
								resultSet.getString("PAIS"), resultSet.getString("TELEFONO")));
					}
					
					return resultado;
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private PreparedStatement setQuery(Huesped huesped,PreparedStatement statement) {
		try {
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, huesped.getFechaNacimiento());
			statement.setString(4, huesped.getPais());
			statement.setString(5, huesped.getTelefono());
			
			try {
				statement.setInt(6, huesped.getId());
			}catch(NullPointerException e) {
				
			}
			System.out.println(huesped);
			statement.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return statement;
	}
	
	public List<Huesped> listar(){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID,NOMBRE,APELLIDO,FECHANACIMIENTO,"
					+ "PAIS,TELEFONO FROM HUESPED");
			try(statement){
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					List<Huesped> resultado = new ArrayList<>();
					while(resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
								resultSet.getString("PAIS"), resultSet.getString("TELEFONO")));
					}
					
					return resultado;
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
