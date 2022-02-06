package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.ClientVo;
import model.vo.RoutineVo;

public class RoutinesClientsDao extends SQL_Controller_Conexion {
	
	public void anadir(RoutineVo rutina, ClientVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO RUTINASCLIENTES"
					+ "(idRutina, idCliente) VALUES (?,?)");
			st.setInt(1, rutina.getIdRutina());
			st.setInt(2, cliente.getIdCliente());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir rutina a cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion añadiendo rutina a cliente: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(RoutineVo rutina, ClientVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM RUTINASCLIENTES WHERE "
					+ "idRutina=? AND idCliente=?");
			st.setInt(1, rutina.getIdRutina());
			st.setInt(2, cliente.getIdCliente());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar rutina de cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion eliminando rutina de cliente: " + e.getMessage());
			}
		}
	}
	
	public List<RoutineVo> listar(ClientVo cliente) throws Exception{
		ArrayList<RoutineVo> listaRutinas = new ArrayList<RoutineVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idRutina FROM RUTINASCLIENTES "
					+ "WHERE idCliente=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT * FROM RUTINAS "
					+ "WHERE idRutina=?");
			
			st1.setInt(1, cliente.getIdCliente());
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idRutina"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				RoutineVo rutina = new RoutineVo();
				rutina.setIdRutina(rs2.getInt("idRutina"));
				rutina.setIdCreador(rs2.getInt("idCreador"));
				rutina.setNombreRutina(rs2.getString("nombreRutina"));
				rutina.setDescripcion(rs2.getString("descripcion"));
				rutina.setCalorias(rs2.getInt("calorias"));
				
				listaRutinas.add(rutina);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar rutinas de usuario: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion listando rutinas de usuario: " + e.getMessage());
			}
		}
		
		return listaRutinas;
	}
}