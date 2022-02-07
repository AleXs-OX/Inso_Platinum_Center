package model.vo;

import java.sql.Date;

public class UsersVo {


	private int idUsuario;
	private String nombreCompleto;
	private String nombreUsuario;
	private String contrasena;
	private Date fechaNacimiento;
	private Date fechaContratacion;
	private String CIF;
	private String email;
	private int telefono;
	private String IBAN;
	private String direccion;
	private int tipoDeUsuario;
	private int idSalario;
	private int idTarifa;


	/*Constructor principal*/
	public UsersVo(int idUsuario, String nombreCompleto, String nombreUsuario, String contrasena, Date fechaNacimiento, Date fechaContratacion,
				   int tipoDeUsuario, String cif, String email, int telefono, String iban, String direccion, int idSalario, int idTarifa) {

		super();

		this.idUsuario = idUsuario;
		this.nombreCompleto = nombreCompleto;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaContratacion = fechaContratacion;
		this.tipoDeUsuario = tipoDeUsuario;
		this.CIF = cif;
		this.email = email;
		this.telefono = telefono;
		this.IBAN = iban;
		this.direccion = direccion;
		this.idSalario = idSalario;
		this.idTarifa = idTarifa;

	}

	public static enum TipoDeUsuario {
		CLIENTE, EMPLEADO, ADMINISTRADOR
	};
	/*Constructor vacio*/
	public UsersVo(){
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

	public Date getFechaContratacion(){
		return fechaContratacion;
	}

	public void setFechaContratacion (Date fechaContratacion){
		this.fechaContratacion = fechaContratacion;
	}

	public void setFechaContratacion(String fechaContratacion){
		this.fechaContratacion = Date.valueOf(fechaContratacion);
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
	
	public String getCIF() {
		return CIF;
	}
	
	public void setCIF(String cIF) {
		this.CIF = cIF;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(String iBAN) {
		this.IBAN = iBAN;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getIdSalario() {
		return idSalario;
	}
	
	public void setIdSalario(int idSalario) {
		this.idSalario = idSalario;
	}
	
	public int getIdTarifa() {
		return idTarifa;
	}
	
	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}
	
}
