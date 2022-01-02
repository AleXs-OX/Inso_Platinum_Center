package model.vo;

import java.sql.Date;
import java.sql.Time;

public class ActivityVo {
	
	private String nombreActividad;
	private Date fecha;
	private int idEmpleado;
	private int idSala;
	private String descripcion;
	private Time duracion;
	
	public ActivityVo(String nombreActividad, Date fecha, int idEmpleado, int idSala, String descripcion, Time duracion) {
		super();
		this.nombreActividad = nombreActividad;
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
		this.idSala = idSala;
		this.descripcion = descripcion;
		this.duracion = duracion;
	}
	
	public String getNombreActividad() {
		return nombreActividad;
	}
	
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = Date.valueOf(fecha);
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

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}
	
}
