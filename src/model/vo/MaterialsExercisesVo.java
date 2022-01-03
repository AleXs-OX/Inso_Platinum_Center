package model.vo;

/*
 * Representacion de la tabla MaterialesEjercicios de la BBDD
 */
public class MaterialsExercisesVo {
	
	private int idMaterial;
	private int idEjercicio;
	
	public MaterialsExercisesVo() {
		super();
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}
	
}
