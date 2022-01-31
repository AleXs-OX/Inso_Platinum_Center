package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ExerciseVo;
import model.vo.RoutineVo;

public class ExercisesRoutinesDao extends SQL_Controller_Conexion{
	
	public void anadir(RoutineVo rutina, ExerciseVo ejercicio) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO EJERCICIOSRUTINAS"
					+ "(idRutina, idEjercicio) VALUES (?, ?)");
			st.setInt(1, rutina.getIdRutina());
			st.setInt(2, ejercicio.getIdEjercicio());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir ejercicio a rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
				this.actualizar(rutina);
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al añadir ejercicio a rutina: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(RoutineVo rutina, ExerciseVo ejercicio) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM EJERCICIOSRUTINAS WHERE "
					+ "idRutina=? AND idEjercicio=?");
			st.setInt(1, rutina.getIdRutina());
			st.setInt(2, ejercicio.getIdEjercicio());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar ejercicio de rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
				this.actualizar(rutina);
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al eliminar ejercicio de rutina: " + e.getMessage());
			}
		}
	}
	
	public List<ExerciseVo> listar(RoutineVo rutina) throws Exception{
		ArrayList<ExerciseVo> listaEjercicios = new ArrayList<ExerciseVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idEjercicio FROM EJERCICIOSRUTINAS WHERE "
					+ "idRutina=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT * FROM EJERCICIOS WHERE "
					+ "idEjercicio=?");
			
			st1.setInt(1, rutina.getIdRutina());
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idEjercicio"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				ExerciseVo ejercicio = new ExerciseVo();
				ejercicio.setIdEjercicio(rs2.getInt("idEjercicio"));
				ejercicio.setNombreEjercicio(rs2.getString("nombreEjercicio"));
				ejercicio.setDescripcion(rs2.getString("descripcion"));
				ejercicio.setCalorias(rs2.getInt("calorias"));
				ejercicio.setImage(rs2.getString("image"));
				
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
	
	public void actualizar(RoutineVo rutina) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idEjercicio FROM EJERCICIOSRUTINAS WHERE "
					+ "idRutina=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT calorias FROM EJERCICIOS WHERE "
					+ "idEjercicio=?");
			
			st1.setInt(1, rutina.getIdRutina());
			ResultSet rs1 = st1.executeQuery();
			
			int calorias = 0;
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idEjercicio"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				calorias += rs2.getInt("calorias");
			}
			
			rutina.setCalorias(calorias);
			RoutineDao dao = new RoutineDao();
			dao.actualizar(rutina);
		}catch(Exception e) {
			throw new Exception("Error al actualizar la rutina: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al actualizar la rutina: " + e.getMessage());
			}
		}
	}
}