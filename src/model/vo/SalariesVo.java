package model.vo;

public class SalariesVo {

	private int idSalario;
	private String nombreSalario;
	private float cantidad;
	private String descripcion;
	
	public SalariesVo() {
		super();
	}

	public int getIdSalario() {
		return idSalario;
	}
	
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
	
	public String getNombreSalario() {
		return nombreSalario;
	}
	
	public void setNombreSalario(String nombreSalario) {
		this.nombreSalario = nombreSalario;
	}
	
	public float getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
