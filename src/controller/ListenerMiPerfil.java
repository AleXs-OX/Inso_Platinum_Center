package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

public class ListenerMiPerfil {
	 @FXML
	 private Label nombreCompleto;
	 
	 @FXML
	 private Label nombreUsuario;
	 
	 @FXML
	 private Label contrasena;
	 
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
	 
	 @FXML
	 private Button editarContrasena;
	 
	 @FXML
	 private Button editarEmail;
	 
	 @FXML
	 private Button editarTelefono;
	 
	 @FXML
	 private Button editarIBAN;
	 
	 @FXML
	 private Button editarDireccion;
	 
	 @FXML
	 private Button atras;
	 
	 @FXML
	 private Stage primaryStage;
	 
	 private UsersVo usuario;
	 
	 public void initialize() {
		this.editarContrasena.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.editarContrasena.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changePassword.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Cambiar contraseña");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage);
				
				ListenerCambiarContrasena controller = loader.getController();
				stage.showAndWait();
				
				if(controller.getValorPrimero().compareTo(controller.getValorSegundo()) == 0 && controller.getValorPrimero().length() > 0) {
					this.usuario.setContrasena(controller.getValorPrimero());
					actualizar();
					cargarModificacion();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.editarEmail.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.editarEmail.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changeValue.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Cambiar email");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage);
				
				ListenerCambiarValor controller = loader.getController();
				controller.setTitulo("email");
				controller.setTituloValorActual("Email");
				controller.setValorActual(usuario.getEmail());
				stage.showAndWait();
				
				if(controller.getNuevoValor().length() > 6 && controller.getNuevoValor().contains("@") && controller.getNuevoValor().contains(".")) {
					this.usuario.setEmail(controller.getNuevoValor());
					actualizar();
					cargarModificacion();
				}else {
					error("El email introducido no es válido");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.editarTelefono.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.editarTelefono.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changeValue.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Cambiar teléfono");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage);
				
				ListenerCambiarValor controller = loader.getController();
				controller.setTitulo("teléfono");
				controller.setTituloValorActual("Teléfono");
				controller.setValorActual("" + usuario.getTelefono());
				stage.showAndWait();
				
				try {
					if(controller.getNuevoValor().length() == 9) {
						this.usuario.setTelefono(Integer.parseInt(controller.getNuevoValor()));
						actualizar();
						cargarModificacion();
					}else {
						error("El número no tiene 9 dígitos");
					}
				}catch(Exception ex) {
					error("El número introducido no es válido");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.editarIBAN.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.editarEmail.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changeValue.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Cambiar IBAN");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage);
				
				ListenerCambiarValor controller = loader.getController();
				controller.setTitulo("IBAN");
				controller.setTituloValorActual("IBAN");
				controller.setValorActual(usuario.getIBAN());
				stage.showAndWait();
				
				if(controller.getNuevoValor().length() == 24 && controller.getNuevoValor().contains("ES")) {
					this.usuario.setIBAN(controller.getNuevoValor());
					actualizar();
					cargarModificacion();
				}else {
					error("El IBAN introducido no es válido");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.editarDireccion.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.editarEmail.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changeValue.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Cambiar dirección");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage);
				
				ListenerCambiarValor controller = loader.getController();
				controller.setTitulo("dirección");
				controller.setTituloValorActual("Dirección");
				controller.setValorActual(usuario.getDireccion());
				stage.showAndWait();
				
				if(controller.getNuevoValor().length() >= 20) {
					this.usuario.setDireccion(controller.getNuevoValor());
					actualizar();
					cargarModificacion();
				}else {
					error("La dirección introducida no es válida.\nFormato: Calle, número, CP, localidad");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.atras.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Client.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Platinum Center - Home");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				
				ListenerClientsUI controller = loader.getController();
				controller.setUsuario(usuario);
				stage.show();
				
				primaryStage = (Stage) this.atras.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	 }
	 
	 public void setUsuario(UsersVo usuario) {
		 this.usuario = usuario;
		 actualizar();
	 }
	 
	 private void actualizar() {
		 setNombreUsuario();
		 setNombreCompleto();
		 setContrasena();
		 setFechaNacimiento();
		 setFechaAlta();
		 setCIF();
		 setEmail();
		 setTelefono();
		 setIBAN();
		 setDireccion();
	 }
	 
	 private void cargarModificacion() {
		 UsersDao dao = new UsersDao();
		 
		 try {
			 dao.actualizar(usuario);
		 }catch(Exception e) {
			 error("Se produjo un error inesperado. Inténtalo de nuevo");
		 }
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
	 
	 private void setContrasena() {
		 StringBuffer cadena = new StringBuffer();
		 
		 for(int i=0; i<usuario.getContrasena().length(); i++) {
			 cadena.append("*");
		 }
		 
		 this.contrasena.setText(cadena.toString());
	 }
	 
	 private void error(String texto){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Error");
			stage.setResizable(false);
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage); 
			
			ListenerError controller = loader.getController();
			controller.setMensaje(texto);
			stage.showAndWait();	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	 }
}