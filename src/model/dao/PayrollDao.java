package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.connection.SQL_Controller_Conexion;
import model.vo.PayrollVo;

public class PayrollDao extends SQL_Controller_Conexion{
	
	public void anadir(PayrollVo pago) throws Exception{
		try {
			this.openConnection();
			
			PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO PAGOS"
					+ "(idEmpleado, fechaPago, idSalario) VALUES (?, ?, ?)");
			st.setInt(1, pago.getIdEmpleado());
			st.setDate(2, pago.getFechaPago());
			st.setInt(3, pago.getIdSalario());
			
			st.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Error añadiendo pago: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión añadiendo pago: " + e.getMessage());
			}
		}
	}
	
	public List<PayrollVo> listar() throws Exception{
		ArrayList<PayrollVo> listaPagos = new ArrayList<PayrollVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM PAGOS");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				PayrollVo pago = new PayrollVo();
				pago.setIdEmpleado(rs.getInt("idEmpleado"));
				pago.setIdSalario(rs.getInt("idSalario"));
				pago.setFechaPago(rs.getDate("fechaPago"));
				
				listaPagos.add(pago);
			}
		}catch(Exception e) {
			throw new Exception("Error al listar los pagos: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión listando los pagos: " + e.getMessage());
			}
		}
		
		return listaPagos;
	}
	
	public List<PayrollVo> buscar(String fechaInicio, String fechaFin) throws Exception{
		ArrayList<PayrollVo> listaPagos = new ArrayList<PayrollVo>();
		
		try {
			this.openConnection();
			PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM PAGOS WHERE "
					+ "fechaPago BETWEEN ? AND ?");
			st.setString(1, fechaInicio);
			st.setString(2, fechaFin);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				PayrollVo pago = new PayrollVo();
				pago.setIdEmpleado(rs.getInt("idEmpleado"));
				pago.setIdSalario(rs.getInt("idSalario"));
				pago.setFechaPago(rs.getDate("fechaPago"));
				
				listaPagos.add(pago);
			}
		}catch(Exception e) {
			throw new Exception("Error al buscar pago: " + e.getMessage());
		}finally {
			try {
				this.closeConnection();
			}catch(Exception e) {
				throw new Exception("Error al cerrar la conexión al buscar pago: " + e.getMessage());
			}
		}
		
		return listaPagos;
	}
}
