package model.vo;

import java.sql.Date;

/*
 * Representacion de la tabla "Cobros", la cual abarca las cuotas pagadas por los clientes.
 */
public class ChargesVo {
	
	private int idCliente;
	private Date fechaCobro;
	private int idTarifa;
	
	public ChargesVo() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	
	public int getIdTarifa() {
		return idTarifa;
	}
	
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
}
