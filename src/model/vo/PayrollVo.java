package model.vo;

import java.sql.Date;

/*
 * Representación de la tabla "Cobros" de la BBDD
 */
public class PayrollVo {
	
	private int idEmpleado;
	private Date fechaCobro;
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
	
	public Date getFechaCobro() {
		return fechaCobro;
	}
	
	public void setFechaCobro(Date fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = Date.valueOf(fechaCobro);
	}
	
	public int getIdSalario() {
		return idSalario;
	}
	
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
}
