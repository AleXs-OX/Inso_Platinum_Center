package model.vo;

public class ExerciseVo {
	
	private int idEjercicio;
	private String nombreEjercicio;
	private String descripcion;
	private int calorias;
	private String image;
	
	public ExerciseVo() {
		super();
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}
	
	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}
	
	public String getNombreEjercicio() {
		return nombreEjercicio;
	}
	
	public void setNombreEjercicio(String nombreEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
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
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
}
