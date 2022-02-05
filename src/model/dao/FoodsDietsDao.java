package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.DietVo;
import model.vo.FoodVo;

public class FoodsDietsDao extends SQL_Controller_Conexion{

	public void anadir(FoodVo comida, DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO ALIMENTOSDIETAS"
					+ "(idDieta, idAlimento) VALUES (?, ?)");
			st.setInt(1, dieta.getIdDieta());
			st.setInt(2, comida.getIdAlimento());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al anadir una comida a dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
				this.actualizar(dieta);
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion al anadir una comida a dieta: " + e.getMessage());
			}
		}
	}
	
	public void eliminar(FoodVo comida, DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM ALIMENTOSDIETAS WHERE"
					+ "idDieta=? AND idAlimento=?");
			st.setInt(1, dieta.getIdDieta());
			st.setInt(2, comida.getIdAlimento());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error al eliminar una comida de dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
				this.actualizar(dieta);
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion eliminando una comida de dieta: " + e.getMessage());
			}
		}
	}
	
	public void actualizar(DietVo dieta) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idAlimento FROM ALIMENTOSDIETAS WHERE "
					+ "idDieta=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT calorias FROM ALIMENTOS WHERE "
					+ "idAlimento=?");
			
			st1.setInt(1, dieta.getIdDieta());
			ResultSet rs1 = st1.executeQuery();
			
			int calorias = 0;
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idAlimento"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				calorias += rs2.getInt("calorias");
			}
			
			dieta.setCalorias(calorias);
			DietDao dao = new DietDao();
			dao.actualizar(dieta);
		}catch(Exception e) {
			throw new Exception("Error al actulizar dieta: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion actualizando dieta: " + e.getMessage());
			}
		}
	}
	
	public List<FoodVo> listar(DietVo dieta) throws Exception{
		ArrayList<FoodVo> listaComidas = new ArrayList<FoodVo>();
		
		try {
			this.openConnection();
			
			PreparedStatement st1 = this.getConnection().prepareStatement("SELECT idAlimento FROM ALIMENTOSDIETAS WHERE "
					+ "idDieta=?");
			PreparedStatement st2 = this.getConnection().prepareStatement("SELECT * FROM ALIMENTOS WHERE "
					+ "idAlimento=?");
			
			st1.setInt(1, dieta.getIdDieta());
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				st2.setInt(1, rs1.getInt("idAlimento"));
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				
				FoodVo alimento = new FoodVo();
				alimento.setIdAlimento(rs2.getInt("idAlimento"));
				alimento.setNombreAlimento(rs2.getString("nombreAlimento"));
				alimento.setDescripcion(rs2.getString("descripcion"));
				alimento.setCalorias(rs2.getInt("calorias"));
				alimento.setImage(rs2.getString("image"));
				
				listaComidas.add(alimento);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar las comidas: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexion listando las comidas: " + e.getMessage());
			}
		}
		
		return listaComidas;
	}
}