package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ChargesVo;

public class ChargesDao extends SQL_Controller_Conexion{
	
	public void anadir(ChargesVo cobro) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO COBROS"
					+ "(idCliente, fechaCobro, idTarifa) VALUES (?, ?, ?)");
			st.setInt(1, cobro.getIdCliente());
			st.setDate(2, cobro.getFechaCobro());
			st.setInt(3, cobro.getIdTarifa());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error añadiendo cobro: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo cobro: " + e.getMessage());
			}
		}
	}
	
	public List<ChargesVo> listar() throws Exception{
		ArrayList<ChargesVo> listaCobros = new ArrayList<ChargesVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM COBROS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ChargesVo cobro = new ChargesVo();
				cobro.setIdCliente(rs.getInt("idCliente"));
				cobro.setIdTarifa(rs.getInt("idTarifa"));
				cobro.setFechaCobro(rs.getDate("fechaCobro"));
				
				listaCobros.add(cobro);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los cobros: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando los cobros: " + e.getMessage());
			}
		}
		
		return listaCobros;
	}
	
	public List<ChargesVo> buscar(String fechaInicio, String fechaFin) throws Exception{
		ArrayList<ChargesVo> listaCobros = new ArrayList<ChargesVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM COBROS WHERE "
					+ "fechaCobro BETWEEN ? AND ?");
			st.setString(1, fechaInicio);
			st.setString(2, fechaFin);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ChargesVo cobro = new ChargesVo();
				cobro.setIdCliente(rs.getInt("idCliente"));
				cobro.setIdTarifa(rs.getInt("idTarifa"));
				cobro.setFechaCobro(rs.getDate("fechaCobro"));
				
				listaCobros.add(cobro);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar cobros: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando cobros: " + e.getMessage());
			}
		}
		
		return listaCobros;
	}
}