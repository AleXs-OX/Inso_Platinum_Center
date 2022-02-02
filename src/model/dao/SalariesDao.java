package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.SalariesVo;

public class SalariesDao extends SQL_Controller_Conexion{

	public void anadir(SalariesVo salario) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO SALARIOS"
					+ "(idSalario, nombreSalario, cantidad, descripcion) VALUES (?, ?, ?, ?)");
			st.setInt(1, salario.getIdSalario());
			st.setString(2, salario.getNombreSalario());
			st.setFloat(3, salario.getCantidad());
			st.setString(4, salario.getDescripcion());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error añadiendo salario: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo salario: " + e.getMessage());
			}
		}
	}
	
	public List<SalariesVo> listar() throws Exception{
		ArrayList<SalariesVo> listaSalarios = new ArrayList<SalariesVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALARIOS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				SalariesVo salario = new SalariesVo();
				salario.setIdSalario(rs.getInt("idSalario"));
				salario.setNombreSalario(rs.getString("nombreSalario"));
				salario.setDescripcion(rs.getString("descripcion"));
				salario.setCantidad(rs.getInt("cantidad"));
				
				listaSalarios.add(salario);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los salarios: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando los salarios: " + e.getMessage());
			}
		}
		
		return listaSalarios;
	}
	
	public List<SalariesVo> buscar(String termino) throws Exception{
		ArrayList<SalariesVo> listaSalarios = new ArrayList<SalariesVo>();
		String criterioBusqueda = '%' + termino + '%';
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALARIOS WHERE nombreSalario"
					+ " LIKE ?");
			st.setString(1, criterioBusqueda);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				SalariesVo salario = new SalariesVo();
				salario.setIdSalario(rs.getInt("idSalario"));
				salario.setNombreSalario(rs.getString("nombreSalario"));
				salario.setDescripcion(rs.getString("descripcion"));
				salario.setCantidad(rs.getInt("cantidad"));
				
				listaSalarios.add(salario);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar salario: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión buscando salarios: " + e.getMessage());
			}
		}
		
		return listaSalarios;
	}
}