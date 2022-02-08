package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.vo.UsersVo;

public class ListenerMiPerfil {
	 @FXML
	 private Label nombreCompleto;
	 
	 @FXML
	 private Label nombreUsuario;
	 
	 @FXML
	 private Label fechaNacimiento;
	 
	 @FXML
	 private Label fechaAlta;
	 
	 @FXML
	 private Label CIF;
	 
	 @FXML
	 private Label email;
	 
	 @FXML
	 private Label telefono;
	 
	 @FXML
	 private Label IBAN;
	 
	 @FXML
	 private Label direccion;
	 
	 private UsersVo usuario;
	 
	 public void initialize() {
		 //TODO
	 }
	 
	 public void setUsuario(UsersVo usuario) {
		 this.usuario = usuario;
		 setNombreUsuario();
		 setNombreCompleto();
		 setFechaNacimiento();
		 setFechaAlta();
		 setCIF();
		 setEmail();
		 setTelefono();
		 setIBAN();
		 setDireccion();
	 }
	 
	 private void setNombreUsuario() {
		 this.nombreUsuario.setText(usuario.getNombreUsuario());
	 }
	 
	 private void setNombreCompleto() {
		 this.nombreCompleto.setText(usuario.getNombreCompleto());
	 }
	 
	 private void setFechaNacimiento() {
		 this.fechaNacimiento.setText(usuario.getFechaNacimiento().toString());
	 }
	 
	 private void setFechaAlta() {
		 this.fechaAlta.setText(usuario.getFechaContratacion().toString());
	 }
	 
	 private void setCIF() {
		 this.CIF.setText(usuario.getCIF());
	 }
	 
	 private void setEmail() {
		 this.email.setText(usuario.getEmail());
	 }
	 
	 private void setTelefono() {
		 this.telefono.setText(usuario.getTelefono() + "");
	 }
	 
	 private void setIBAN() {
		 this.IBAN.setText(usuario.getIBAN());
	 }
	 
	 private void setDireccion() {
		 this.direccion.setText(usuario.getDireccion());
	 }
}
