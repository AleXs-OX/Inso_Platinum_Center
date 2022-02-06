package model.vo;

import java.sql.Time;
import java.sql.Timestamp;

public class ActivityVo {
	
	private String nombreActividad;
	private Timestamp fecha;
	private int idEmpleado;
	private int idSala;
	private int idRutina;
	private String descripcion;
	private Time duracion;
	
	public ActivityVo() {
		super();
	}
	
	public String getNombreActividad() {
		return nombreActividad;
	}
	
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	
	public Timestamp getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = Timestamp.valueOf(fecha);
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public int getIdSala() {
		return idSala;
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Time getDuracion() {
		return duracion;
	}
	
	public void setDuracion(String duracion) {
		this.duracion = Time.valueOf(duracion);
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}
	
}
