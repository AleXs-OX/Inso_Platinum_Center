package model.vo;

public class DietVo {
	
	private int idDieta;
	private int idCreador;
	private String nombreDieta;
	private String descripcion;
	private int calorias;
	
	public DietVo() {
		super();
	}

	public int getIdDieta() {
		return idDieta;
	}
	
	public void setIdDieta(int idDieta) {
		this.idDieta = idDieta;
	}
	
	public int getIdCreador() {
		return idCreador;
	}
	
	public void setIdCreador(int idCreador) {
		this.idCreador = idCreador;
	}
	
	public String getNombreDieta() {
		return nombreDieta;
	}
	
	public void setNombreDieta(String nombreDieta) {
		this.nombreDieta = nombreDieta;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
}
