package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListenerCambiarContrasena {

	@FXML
	private Button aceptar;
	
	@FXML
	private PasswordField valorPrimero;
	
	@FXML
	private PasswordField valorSegundo;
	
	@FXML
	private Stage primaryStage;
	
	public void initialize() {
		this.aceptar.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.aceptar.getScene().getWindow();
				
				if(getValorPrimero().compareTo(getValorSegundo()) == 0) {
					primaryStage.close();					
				}else {
					error("Los valores introducidos no coinciden");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public String getValorPrimero() {
		return valorPrimero.getText();
	}
	
	public String getValorSegundo() {
		return valorSegundo.getText();
	}
	
	private void error(String texto) {
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
