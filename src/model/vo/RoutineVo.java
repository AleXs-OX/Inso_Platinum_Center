package model.vo;

public class RoutineVo {

	private int idRutina;
	private int idCreador;
	private String nombreRutina;
	private String descripcion;
	private int calorias;

	public RoutineVo(int idRutina, int idCreador, String nombreRutina, String descripcion, int calorias){
		this.idRutina = idRutina;
		this.idCreador = idCreador;
		this.nombreRutina = nombreRutina;
		this.descripcion = descripcion;
		this.calorias = calorias;
	}

	public RoutineVo() {
		super();
	}
	
	public int getIdRutina() {
		return idRutina;
	}
	
	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}
	
	public int getIdCreador() {
		return idCreador;
	}
	
	public void setIdCreador(int idCreador) {
		this.idCreador = idCreador;
	}
	
	public String getNombreRutina() {
		return nombreRutina;
	}
	
	public void setNombreRutina(String nombreRutina) {
		this.nombreRutina = nombreRutina;
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
