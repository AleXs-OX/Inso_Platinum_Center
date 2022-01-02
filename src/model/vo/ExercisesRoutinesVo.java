package model.vo;

/*
 * Representación de la tabla EjerciciosRutinas de la BBDD
 */
public class ExercisesRoutinesVo {
	
	private int idRutina;
	private int idEjercicio;
	
	public ExercisesRoutinesVo() {
		super();
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public int getIdEjercicio() {
		return idEjercicio;
	}

	public void setIdEjercicio(int idEjercicio) {
		this.idEjercicio = idEjercicio;
	}
	
}
