package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.TariffsVo;

public class TariffsDao extends SQL_Controller_Conexion{
	
	public void anadir(TariffsVo tarifa) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO TARIFAS"
					+ "(idTarifa, nombreTarifa, importe, descripcion) VALUES (?, ?, ?, ?)");
			st.setInt(1, tarifa.getIdTarifa());
			st.setString(2, tarifa.getNombreTarifa());
			st.setFloat(3, tarifa.getImporte());
			st.setString(4, tarifa.getDescripcion());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir una tairfa: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al añadir una tarifa: " + e.getMessage());
			}
		}
	}
	
	public List<TariffsVo> listar() throws Exception{
		ArrayList<TariffsVo> listaTarifas = new ArrayList<TariffsVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM TARIFAS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				TariffsVo tarifa = new TariffsVo();
				tarifa.setIdTarifa(rs.getInt("idTarifa"));
				tarifa.setNombreTarifa(rs.getString("nombreTarifa"));
				tarifa.setDescripcion(rs.getString("descripcion"));
				tarifa.setImporte(rs.getInt("importe"));
				
				listaTarifas.add(tarifa);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los ejercicios: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando los ejercicios: " + e.getMessage());
			}
		}
		
		return listaTarifas;
	}
	
	public List<TariffsVo> buscar(String termino) throws Exception{
		ArrayList<TariffsVo> listaTarifas = new ArrayList<TariffsVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM TARIFAS WHERE nombreTarifa"
					+ " LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				TariffsVo tarifa = new TariffsVo();
				tarifa.setIdTarifa(rs.getInt("idTarifa"));
				tarifa.setNombreTarifa(rs.getString("nombreTarifa"));
				tarifa.setDescripcion(rs.getString("descripcion"));
				tarifa.setImporte(rs.getInt("importe"));
				
				listaTarifas.add(tarifa);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los ejercicios: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando los ejercicios: " + e.getMessage());
			}
		}
		
		return listaTarifas;
	}
}
