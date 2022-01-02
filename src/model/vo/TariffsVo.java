package model.vo;

public class TariffsVo {

	private int idTarifa;
	private String nombreTarifa;
	private float importe;
	private String descripcion;
	
	public TariffsVo() {
		super();
	}

	public int getIdTarifa() {
		return idTarifa;
	}
	
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
	
	public String getNombreTarifa() {
		return nombreTarifa;
	}
	
	public void setNombreTarifa(String nombreTarifa) {
		this.nombreTarifa = nombreTarifa;
	}
	
	public float getImporte() {
		return importe;
	}
	
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
