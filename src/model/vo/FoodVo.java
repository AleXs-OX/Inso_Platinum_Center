package model.vo;

public class FoodVo {
	
	private int idAlimento;
	private String nombreAlimento;
	private String descripcion;
	private int calorias;
	private String image;
	
	public FoodVo() {
		super();
	}

	public int getIdAlimento() {
		return idAlimento;
	}
	
	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}
	
	public String getNombreAlimento() {
		return nombreAlimento;
	}
	
	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = nombreAlimento;
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
