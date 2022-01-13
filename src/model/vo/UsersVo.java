package model.vo;

import java.sql.Date;

public class UsersVo {
	public static enum TipoDeUsuario {CLIENTE, EMPLEADO, ADMINISTRADOR};

	private int idUsuario;
	private String nombreCompleto;
	private String nombreUsuario;
	private String contrasena;
	private Date fechaNacimiento;
	private int tipoDeUsuario;
	
	public UsersVo() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = Date.valueOf(fechaNacimiento);
	}
	
	public int getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipo) {
		this.tipoDeUsuario = tipo.ordinal();
	}

	public void setTipoDeUsuario(int tipo) {
		this.tipoDeUsuario = tipo;
	}
	
}
