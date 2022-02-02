package model.vo;

import java.sql.Date;

/*
 * Representacion de la tabla "Pagos" de la BBDD
 */
public class PayrollVo {
	
	private int idEmpleado;
	private Date fechaPago;
	private int idSalario;
		
	public PayrollVo() {
		super();
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}
	
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public void setFechaPago(String fechaPago) {
		this.fechaPago = Date.valueOf(fechaPago);
	}
	
	public int getIdSalario() {
		return idSalario;
	}
	
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
}
