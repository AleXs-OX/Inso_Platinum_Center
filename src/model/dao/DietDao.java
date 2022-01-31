package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.DietVo;

public class DietDao extends SQL_Controller_Conexion{
	
	public void anadir(DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO DIETAS"
					+ "(idDieta, idCreador, nombreDieta, descripcion) VALUES (?, ?, ?, ?)");
			st.setInt(1, dieta.getIdDieta());
			st.setInt(2, dieta.getIdCreador());
			st.setString(3, dieta.getNombreDieta());
			st.setString(4, dieta.getDescripcion());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo dieta: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM DIETAS WHERE "
					+ "idDieta=?");
			st.setInt(1, dieta.getIdDieta());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión eliminando dieta: " + e.getMessage());
			}
		}
	}
	
	public List<DietVo> listar() throws Exception{
		ArrayList<DietVo> listaDietas = new ArrayList<DietVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM DIETAS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				DietVo dieta = new DietVo();
				dieta.setIdDieta(rs.getInt("idDieta"));
				dieta.setIdCreador(rs.getInt("idCreador"));
				dieta.setNombreDieta(rs.getString("nombreDieta"));
				dieta.setDescripcion(rs.getString("descripcion"));
				dieta.setCalorias(rs.getInt("calorias"));
				
				listaDietas.add(dieta);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar las dietas: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando dietas: " + e.getMessage());
			}
		}
		
		return listaDietas;
	}
	
	public List<DietVo> buscar(String termino) throws Exception{
		ArrayList<DietVo> listaDietas = new ArrayList<DietVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM DIETAS WHERE nombreDieta "
					+ "LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				DietVo dietas = new DietVo();
				dietas.setIdDieta(rs.getInt("idDieta"));
				dietas.setIdCreador(rs.getInt("idCreador"));
				dietas.setNombreDieta(rs.getString("nombreDieta"));
				dietas.setDescripcion(rs.getString("descripcion"));
				dietas.setCalorias(rs.getInt("calorias"));
				
				listaDietas.add(dietas);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar dietas: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando dietas: " + e.getMessage());
			}
		}
		
		return listaDietas;
	}
	
	public void actualizar(DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("UPDATE DIETAS SET "
					+ "calorias=?, nombreDieta=?, descripcion=? WHERE idDieta=?");
			st.setInt(1, dieta.getCalorias());
			st.setString(2, dieta.getNombreDieta());
			st.setString(3, dieta.getDescripcion());
			st.setInt(4, dieta.getIdDieta());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al actualizar dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión actualizando dieta: " + e.getMessage());
			}
		}
	}
}