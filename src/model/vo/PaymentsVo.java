package model.vo;

import java.sql.Date;

/*
 * Representacion de la tabla "Pagos", la cual abarca las cuotas pagadas por los clientes.
 */
public class PaymentsVo {
	
	private int idCliente;
	private Date fechaPago;
	private int idTarifa;
	
	public PaymentsVo() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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
	
	public int getIdTarifa() {
		return idTarifa;
	}
	
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
}
