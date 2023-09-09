package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaDao {
	
	private Connection con;

	public ReservaDao(Connection con) {
		this.con = con;
		}
	
	public Integer eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM Reserva WHERE ID = ?",PreparedStatement.RETURN_GENERATED_KEYS);
			
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				
				return statement.getUpdateCount();
				}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Integer modificar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVA SET FECHAENTRADA = ?,"
					+ " FECHASALIDA = ?, FORMAPAGO=? WHERE ID = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			try(statement){
				
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setString(3, reserva.getFormaPago());
				statement.setInt(4, reserva.getId());
				statement.execute();

			
				return statement.getUpdateCount();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscar(String busqueda){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT H.NOMBRE, R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.PRECIO, R.FORMAPAGO FROM RESERVA R INNER JOIN "
					+ "HUESPED H ON R.IDHUESPED = H.ID AND (H.NOMBRE LIKE ? OR R.FECHAENTRADA=? OR R.FECHASALIDA=? OR R.FORMAPAGO =?)");
			try(statement){
				
				statement.setString(1, "%" + busqueda + "%");
				try {
					statement.setDate(2, Date.valueOf(busqueda));
					statement.setDate(3, Date.valueOf(busqueda));
					
				}catch(IllegalArgumentException e) {
					
					statement.setDate(2,null);
					statement.setDate(3,null);
				}
				statement.setString(4, busqueda);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					List<Reserva> resultado = new ArrayList<>();
					
					while(resultSet.next()) {
						Reserva reserva = new Reserva(resultSet.getString(1), resultSet.getInt(2),
								resultSet.getDate(3),
								resultSet.getDate(4), resultSet.getDouble(5),
								resultSet.getString(6));
						
						resultado.add(reserva);
					}
					return resultado;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVA(FECHAENTRADA,FECHASALIDA,PRECIO,FORMAPAGO,IDHUESPED) VALUES("
					+ "?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			
			try(statement){
				
				final ResultSet resultSet = setQuery(reserva, statement).getGeneratedKeys();
				
				try(resultSet){
					
					while(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private PreparedStatement setQuery(Reserva reserva, PreparedStatement statement) {
		try {
			statement.setDate(1, reserva.getFechaEntrada());
			statement.setDate(2, reserva.getFechaSalida());
			statement.setDouble(3, reserva.getPrecio());
			statement.setString(4, reserva.getFormaPago());
			statement.setInt(5, reserva.getIdHuesped());
			
			statement.execute();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return statement;
	}
	
	public List<Reserva> listar(){
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT H.NOMBRE, R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.PRECIO, R.FORMAPAGO FROM RESERVA R INNER JOIN "
					+ "HUESPED H ON R.IDHUESPED = H.ID");
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					List<Reserva> resultado = new ArrayList<>();
					
					while(resultSet.next()) {
						Reserva reserva = new Reserva(resultSet.getString(1), resultSet.getInt(2),
								resultSet.getDate(3),
								resultSet.getDate(4), resultSet.getDouble(5),
								resultSet.getString(6));
						resultado.add(reserva);
					}
					return resultado;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	}
