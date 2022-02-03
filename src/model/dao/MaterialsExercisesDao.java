package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ExerciseVo;
import model.vo.MaterialVo;

public class MaterialsExercisesDao extends SQL_Controller_Conexion{
	public void añadir(MaterialVo material, ExerciseVo ejercicio) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO MATERIALESEJERCICIOS"
					+ "(idMaterial, idEjercicio) VALUES (?, ?)");
			st.setInt(1, material.getIdMaterial());
			st.setInt(2, ejercicio.getIdEjercicio());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir un material a ejercicio: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch (Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo un material a ejercicio: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(MaterialVo material, ExerciseVo ejercicio) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM MATERIALESEJERCICIOS WHERE "
					+ "idMaterial=? AND idEjercicio=?");
			st.setInt(1, material.getIdMaterial());
			st.setInt(2, ejercicio.getIdEjercicio());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar un material de ejercicio: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error cerrando la conexión al eliminar un material de ejercicio: " + e.getMessage());
			}
		}
	}
	
	public List<MaterialVo> listar(ExerciseVo ejercicio) throws Exception{
		ArrayList<MaterialVo> listaMateriales = new ArrayList<MaterialVo>();
		
		try {
			this.closeConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM MATERIALESEJERCICIOS "
					+ "WHERE idEjercicio=?");
			st.setInt(1, ejercicio.getIdEjercicio());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				MaterialVo material = new MaterialVo();
				material.setIdMaterial(rs.getInt("idMaterial"));
				material.setNombreMaterial(rs.getString("nombreMaterial"));
				material.setFechaAlta(rs.getDate("fechaAlta"));
				material.setFechaBaja(rs.getDate("fechaBaja"));
				material.setIdSala(rs.getInt("idSala"));
				
				listaMateriales.add(material);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar materiales de un ejercicio: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error cerrando conexión al listar materiales de un ejercicio: " + e.getMessage());
			}
		}
		
		return listaMateriales;
	}
}