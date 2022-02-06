package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ActivityVo;
import model.vo.UsersVo;

public class ActivitiesClientsDao extends SQL_Controller_Conexion {

	public void anadir(ActivityVo actividad, UsersVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO ACTIVIDADESCLIENTES"
					+ "(idCliente, nombreActividad, fecha) VALUES (?, ?, ?)");
			st.setInt(1, cliente.getIdUsuario());
			st.setString(2, actividad.getNombreActividad());
			st.setTimestamp(3, actividad.getFecha());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir actividad a cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar conexion añadiendo actividad a cliente: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(ActivityVo actividad, UsersVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM ACTIVIDADESCLIENTES "
					+ "WHERE idCliente=? AND nombreActividad=? AND fecha=?");
			st.setInt(1, cliente.getIdUsuario());
			st.setString(2, actividad.getNombreActividad());
			st.setTimestamp(3, actividad.getFecha());
			
		}catch(Exception e) {
			throw new Exception("Error al eliminar actividad de cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion eliminando actividad de cliente: " + e.getMessage());
			}
		}
	}
	
	public List<ActivityVo> listar(UsersVo cliente) throws Exception{
		ArrayList<ActivityVo> listaActividades = new ArrayList<ActivityVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT * FROM ACTIVIDADESCLIENTES "
					+ "WHERE idCliente=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT * FROM ACTIVIDADES "
					+ "WHERE nombreActividad=? AND fecha=?");
			
			st1.setInt(1, cliente.getIdUsuario());
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				st2.setString(1, rs1.getString("nombreActividad"));
				st2.setTimestamp(2, rs1.getTimestamp("fecha"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				ActivityVo actividad = new ActivityVo();
				actividad.setNombreActividad(rs2.getString("nombreActividad"));
				actividad.setFecha(rs2.getTimestamp("fecha"));
				actividad.setIdEmpleado(rs2.getInt("idEmpleado"));
				actividad.setIdSala(rs2.getInt("idSala"));
				actividad.setIdRutina(rs2.getInt("idRutina"));
				actividad.setDescripcion(rs2.getString("descripcion"));
				actividad.setDuracion(rs2.getTime("duracion"));
				
				listaActividades.add(actividad);
			}
		}catch(Exception e) {
			throw new Exception("Error listando actividades del cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion listando actividades de cliente: " + e.getMessage());
			}
		}
		
		return listaActividades;
	}
}