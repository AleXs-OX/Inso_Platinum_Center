package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ListenerError {
	
	@FXML
	private Stage primaryStage;
	
	@FXML
	private Label mensaje;
	
	@FXML
	private Button aceptar;
	
	public void initialize() {
		this.aceptar.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.aceptar.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public void setMensaje(String cadena) {
		this.mensaje.setText(cadena);
	}
}
