package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.FoodVo;

public class FoodDao extends SQL_Controller_Conexion{

	public void anadir(FoodVo comida) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO ALIMENTOS"
					+ "(idAlimento, nombreAlimento, descripcion, calorias, image) VALUES (?, ?, ?, ?, ?)");
			st.setInt(1, comida.getIdAlimento());
			st.setString(2, comida.getNombreAlimento());
			st.setString(3, comida.getDescripcion());
			st.setInt(4, comida.getCalorias());
			st.setString(5, comida.getImage());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al añadir comida: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo comida: " + e.getMessage());
			}
		}
	}
	
	public List<FoodVo> listar() throws Exception{
		ArrayList<FoodVo> listaComidas = new ArrayList<FoodVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ALIMENTOS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				FoodVo comida = new FoodVo();
				comida.setIdAlimento(rs.getInt("idAlimento"));
				comida.setNombreAlimento(rs.getString("nombreAlimento"));
				comida.setDescripcion(rs.getString("descripcion"));
				comida.setCalorias(rs.getInt("calorias"));
				comida.setImage(rs.getString("image"));
				
				listaComidas.add(comida);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los alimentos: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando alimentos: " + e.getMessage());
			}
		}
		
		return listaComidas;
	}
	
	public List<FoodVo> buscar(String termino) throws Exception{
		ArrayList<FoodVo> listaComidas = new ArrayList<FoodVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ALIMENTOS WHERE nombreAlimento "
					+ "LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				FoodVo comida = new FoodVo();
				comida.setIdAlimento(rs.getInt("idAlimento"));
				comida.setNombreAlimento(rs.getString("nombreAlimento"));
				comida.setDescripcion(rs.getString("descripcion"));
				comida.setCalorias(rs.getInt("calorias"));
				comida.setImage(rs.getString("image"));
				
				listaComidas.add(comida);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar alimentos: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando alimentos: " + e.getMessage());
			}
		}
		
		return listaComidas;
	}
	
	public FoodVo buscar(int idAlimento) throws Exception{
		FoodVo comida = new FoodVo();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ALIMENTOS "
					+ "WHERE idAlimento=?");
			st.setInt(1, idAlimento);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				comida.setIdAlimento(rs.getInt("idAlimento"));
				comida.setNombreAlimento(rs.getString("nombreAlimento"));
				comida.setDescripcion(rs.getString("descripcion"));
				comida.setCalorias(rs.getInt("calorias"));
				comida.setImage(rs.getString("image"));
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar alimentos: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando alimentos: " + e.getMessage());
			}
		}
		
		return comida;
	}
}