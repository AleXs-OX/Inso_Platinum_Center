package model.vo;

/*
 * Representación de la tabla AlimentosDietas de la BBDD
 */
public class FoodsDietsVo {
	
	private int idDieta;
	private int idAlimento;
	
	public FoodsDietsVo() {
		super();
	}

	public int getIdDieta() {
		return idDieta;
	}
	
	public void setIdDieta(int idDieta) {
		this.idDieta = idDieta;
	}
	
	public int getIdAlimento() {
		return idAlimento;
	}
	
	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}
	
}
