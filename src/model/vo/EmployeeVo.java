package model.vo;

import java.sql.Date;

public class EmployeeVo {
	
	private int idEmpleado;
	private Date fechaContratacion;
	private Date fechaDespido;
	private int idSalario;
	
	public EmployeeVo() {
		super();
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	
	public void setFechaContratacion(String fechaContratacion) {
		this.fechaContratacion = Date.valueOf(fechaContratacion);
	}
	
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public Date getFechaDespido() {
		return fechaDespido;
	}
	
	public void setFechaDespido(String fechaDespido) {
		this.fechaDespido = Date.valueOf(fechaDespido);
	}
	
	public void setFechaDespido(Date fechaDespido) {
		this.fechaDespido = fechaDespido;
	}
	
	public int getIdSalario() {
		return idSalario;
	}
	
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
}
