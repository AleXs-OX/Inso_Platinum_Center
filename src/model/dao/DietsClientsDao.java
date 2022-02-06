package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.UsersVo;
import model.vo.DietVo;

public class DietsClientsDao extends SQL_Controller_Conexion {
	
	public void anadir(DietVo dieta, UsersVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO DIETASCLIENTES"
					+ "(idCliente, idDieta) VALUES (?, ?)");
			st.setInt(1, cliente.getIdUsuario());
			st.setInt(2, dieta.getIdDieta());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir dieta a cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion añadiendo dieta a cliente: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(DietVo dieta, UsersVo cliente) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM DIETASCLIENTES "
					+ "WHERE idDieta=? AND idCliente=?");
			st.setInt(1, dieta.getIdDieta());
			st.setInt(2, cliente.getIdUsuario());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar dieta de cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion eliminando dieta de cliente: " + e.getMessage());
			}
		}
	}
	
	public List<DietVo> listar(UsersVo cliente) throws Exception{
		ArrayList<DietVo> listaDietas = new ArrayList<DietVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idDieta FROM DIETASCLIENTES "
					+ "WHERE idCliente=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT * FROM DIETAS "
					+ "WHERE idDieta=?");
			
			st1.setInt(1, cliente.getIdUsuario());
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idDieta"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				DietVo dieta = new DietVo();
				dieta.setIdDieta(rs2.getInt("idDieta"));
				dieta.setIdCreador(rs2.getInt("idCreador"));
				dieta.setNombreDieta(rs2.getString("nombreDieta"));
				dieta.setDescripcion(rs2.getString("descripcion"));
				dieta.setCalorias(rs2.getInt("calorias"));
				
				listaDietas.add(dieta);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar las dietas del cliente: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion listando dietas de cliente: " + e.getMessage());
			}
		}
		
		return listaDietas;
	}
}