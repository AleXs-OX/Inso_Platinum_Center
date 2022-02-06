package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.MaterialVo;

public class MaterialDao extends SQL_Controller_Conexion{
	
	public void anadir(MaterialVo material) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO MATERIALES"
					+ "(idMaterial, nombreMaterial, fechaAlta, idSala) VALUES (?, ?, ?, ?)");
			st.setInt(1, material.getIdMaterial());
			st.setString(2, material.getNombreMaterial());
			st.setDate(3, material.getFechaAlta());
			st.setInt(4, material.getIdSala());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error añadiendo material: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e){
				throw new Exception("Error al cerrar la conexión añadiendo material: " + e.getMessage());
			}
		}
	}
	
	public void actualizarSala(MaterialVo material) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("UPDATE MATERIALES SET "
					+ "idSala=? WHERE idMaterial=?");
			st.setInt(1, material.getIdSala());
			st.setInt(2, material.getIdMaterial());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error actualizando sala material: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión actualizando sala material: " + e.getMessage());
			}
		}
	}
	
	public void actualizarBaja(MaterialVo material) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("UPDATE MATERIALES SET "
					+ "fechaBaja=? WHERE idMaterial=?");
			st.setDate(1, material.getFechaBaja());
			st.setInt(2, material.getIdMaterial());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error actualizando baja material: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión actualizando baja material: " + e.getMessage());
			}
		}
	}
	
	public List<MaterialVo> listar() throws Exception{
		ArrayList<MaterialVo> listaMateriales = new ArrayList<MaterialVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM MATERIALES");
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
			throw new Exception("Error listando materiales: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e){
				throw new Exception("Error al cerrar la conexión listando materiales: " + e.getMessage());
			}
		}
		
		return listaMateriales;
	}
	
	public List<MaterialVo> buscar(String termino) throws Exception{
		ArrayList<MaterialVo> listaMateriales = new ArrayList<MaterialVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM MATERIALES "
					+ "WHERE nombreMaterial LIKE ?");
			st.setString(1, criterioBusqueda);
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
			throw new Exception("Errro buscando materiales: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e){
				throw new Exception("Error al cerrar la conexión buscando materiales: " + e.getMessage());
			}
		}
		
		return listaMateriales;
	}
	
	public MaterialVo buscar(int idMaterial) throws Exception{
		MaterialVo material = new MaterialVo();
		
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM MATERIALES "
					+ "WHERE idMaterial=?");
			st.setInt(1, idMaterial);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				material.setIdMaterial(rs.getInt("idMaterial"));
				material.setNombreMaterial(rs.getString("nombreMaterial"));
				material.setFechaAlta(rs.getDate("fechaAlta"));
				material.setFechaBaja(rs.getDate("fechaBaja"));
				material.setIdSala(rs.getInt("idSala"));
			}
		}catch(Exception e) {
			throw new Exception("Error buscando materiales: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e){
				throw new Exception("Error al cerrar la conexión buscando materiales: " + e.getMessage());
			}
		}
		
		return material;
	}
}