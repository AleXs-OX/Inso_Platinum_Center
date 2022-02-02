package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.RoutineVo;

public class RoutineDao extends SQL_Controller_Conexion{
	
	public void anadir(RoutineVo rutina) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO RUTINAS"
					+ "(idRutina, idCreador, nombreRutina, descripcion) VALUES (?, ?, ?, ?)");
			st.setInt(1, rutina.getIdRutina());
			st.setInt(2, rutina.getIdCreador());
			st.setString(3, rutina.getNombreRutina());
			st.setString(4, rutina.getDescripcion());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al crear rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión creando una rutina: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(RoutineVo rutina) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM RUTINAS WHERE "
					+ "idRutina=?");
			st.setInt(1, rutina.getIdRutina());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar la rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al eliminar la rutina: " + e.getMessage());
			}
		}
	}
	
	public List<RoutineVo> listar() throws Exception{
		ArrayList<RoutineVo> listaRutinas = new ArrayList<RoutineVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM RUTINAS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				RoutineVo rutina = new RoutineVo();
				rutina.setIdRutina(rs.getInt("idRutina"));
				rutina.setIdCreador(rs.getInt("idCreador"));
				rutina.setNombreRutina(rs.getString("nombreRutina"));
				rutina.setDescripcion(rs.getString("descripcion"));
				rutina.setCalorias(rs.getInt("calorias"));
				
				listaRutinas.add(rutina);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar las rutinas: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando las rutinas: " + e.getMessage());
			}
		}
		
		return listaRutinas;
	}
	
	public List<RoutineVo> buscar(String termino) throws Exception{
		ArrayList<RoutineVo> listaRutinas = new ArrayList<RoutineVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM RUTINAS WHERE nombreRutina "
					+ "LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				RoutineVo rutina = new RoutineVo();
				rutina.setIdRutina(rs.getInt("idRutina"));
				rutina.setIdCreador(rs.getInt("idCreador"));
				rutina.setNombreRutina(rs.getString("nombreRutina"));
				rutina.setDescripcion(rs.getString("descripcion"));
				rutina.setCalorias(rs.getInt("calorias"));
				
				listaRutinas.add(rutina);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar rutinas: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando rutinas: " + e.getMessage());
			}
		}
		
		return listaRutinas;
	}
	
	public void actualizar(RoutineVo rutina) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("UPDATE RUTINAS SET "
					+ "calorias=?, nombreRutina=?, descripcion=? WHERE idRutina=?");
			st.setInt(1, rutina.getCalorias());
			st.setString(2, rutina.getNombreRutina());
			st.setString(3, rutina.getDescripcion());
			st.setInt(4, rutina.getIdRutina());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al actualizar rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al actualizar rutina: " + e.getMessage());
			}
		}
	}
}