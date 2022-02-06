package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ExerciseVo;

public class ExerciseDao extends SQL_Controller_Conexion{
	
	public void anadir(ExerciseVo ejercicio) throws Exception{
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO EJERCICIOS"
					+ "(idEjercicio, nombreEjercicio, descripcion, calorias, image) VALUES (?, ?, ?, ?, ?)");
			
			st.setInt(1, ejercicio.getIdEjercicio());
			st.setString(2, ejercicio.getNombreEjercicio());
			st.setString(3, ejercicio.getDescripcion());
			st.setInt(4, ejercicio.getCalorias());
			st.setString(5, ejercicio.getImage());	
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al insertar añadir ejercicio: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo un ejercicio: " + e.getMessage());
			}
		}
	}
	
	public List<ExerciseVo> listar() throws Exception{
		ArrayList<ExerciseVo> listaEjercicios = new ArrayList<ExerciseVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM EJERCICIOS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ExerciseVo ejercicio = new ExerciseVo();
				ejercicio.setIdEjercicio(rs.getInt("idEjercicio"));
				ejercicio.setNombreEjercicio(rs.getString("nombreEjercicio"));
				ejercicio.setDescripcion(rs.getString("descripcion"));
				ejercicio.setCalorias(rs.getInt("calorias"));
				ejercicio.setImage(rs.getString("image"));
				
				listaEjercicios.add(ejercicio);
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
		
		return listaEjercicios;
	}
	
	public List<ExerciseVo> buscar(String termino) throws Exception{
		ArrayList<ExerciseVo> listaEjercicios = new ArrayList<ExerciseVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM EJERCICIOS WHERE nombreEjercicio "
					+ "LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ExerciseVo ejercicio = new ExerciseVo();
				ejercicio.setIdEjercicio(rs.getInt("idEjercicio"));
				ejercicio.setNombreEjercicio(rs.getString("nombreEjercicio"));
				ejercicio.setDescripcion(rs.getString("descripcion"));
				ejercicio.setCalorias(rs.getInt("calorias"));
				ejercicio.setImage(rs.getString("image"));
				
				listaEjercicios.add(ejercicio);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar ejercicios: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando ejercicios: " + e.getMessage());
			}
		}
		
		return listaEjercicios;
	}
	
	public ExerciseVo buscar(int idEjercicio) throws Exception{
		ExerciseVo ejercicio = new ExerciseVo();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM EJERCICIOS "
					+ "WHERE idEjercicio=?");
			st.setInt(1, idEjercicio);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				ejercicio.setIdEjercicio(rs.getInt("idEjercicio"));
				ejercicio.setNombreEjercicio(rs.getString("nombreEjercicio"));
				ejercicio.setDescripcion(rs.getString("descripcion"));
				ejercicio.setCalorias(rs.getInt("calorias"));
				ejercicio.setImage(rs.getString("image"));
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar ejercicios: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando ejercicios: " + e.getMessage());
			}
		}
		
		return ejercicio;
	}
}