package model.vo;

import java.sql.Date;

public class MaterialVo {
	
	private int idMaterial;
	private String nombreMaterial;
	private Date fechaAlta;
	private Date fechaBaja;
	private int idSala;

	public MaterialVo(int idMaterial,String nombreMaterial,Date fechaAlta,Date fechaBaja,int idSala){

		this.idMaterial = idMaterial;
		this.nombreMaterial = nombreMaterial;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.idSala = idSala;
	}
	public MaterialVo() {
		super();
	}

	public int getIdMaterial() {
		return idMaterial;
	}
	
	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public String getNombreMaterial() {
		return nombreMaterial;
	}
	
	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = Date.valueOf(fechaAlta);
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
	
	public int getIdSala() {
		return idSala;
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
}
