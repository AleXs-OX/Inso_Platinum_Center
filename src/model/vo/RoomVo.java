package model.vo;

import java.sql.Time;

public class RoomVo {

	private int idSala;
    private String nombreSala;
    private int aforo;
    private Time apertura;
    private Time cierre;
    
    public RoomVo(int idSala, String nombreSala, int aforo, Time apertura, Time cierre) {
		super();
		this.idSala = idSala;
		this.nombreSala = nombreSala;
		this.aforo = aforo;
		this.apertura = apertura;
		this.cierre = cierre;
	}
    
	public int getIdSala() {
		return idSala;
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public String getNombreSala() {
		return nombreSala;
	}
	
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	
	public int getAforo() {
		return aforo;
	}
	
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	
	public Time getApertura() {
		return apertura;
	}
	
	public void setApertura(String apertura) {
		this.apertura = Time.valueOf(apertura);
	}
	
	public Time getCierre() {
		return cierre;
	}
	
	public void setCierre(String cierre) {
		this.cierre = Time.valueOf(cierre);
	}
}
