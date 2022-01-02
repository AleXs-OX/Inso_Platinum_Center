package model.vo;

import java.sql.Date;

public class ClientVo {
	
	private int idCliente;
	private Date fechaAlta;
	private Date fechaBaja;
	private int tarifaContratada;
	
	public ClientVo() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = Date.valueOf(fechaAlta);
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}
	
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = Date.valueOf(fechaBaja);
	}
	
	public int getTarifaContratada() {
		return tarifaContratada;
	}
	
	public void setTarifaContratada(int tarifaContratada) {
		this.tarifaContratada = tarifaContratada;
	}
}
