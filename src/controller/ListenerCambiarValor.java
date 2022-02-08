package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListenerCambiarValor {
	
	@FXML
	private Label titulo;
	
	@FXML
	private Label tituloValorActual;
	
	@FXML
	private Label valorActual;
	
	@FXML
	private Label tituloNuevoValor;
	
	@FXML
	private TextField valorNuevo;
	
	@FXML
	private Button aceptar;
	
	@FXML
	private Stage primaryStage;
	
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
	
	public void setTitulo(String cadena) {
		this.titulo.setText("Cambiar " + cadena);
	}
	
	public void setTituloValorActual(String cadena) {
		this.tituloValorActual.setText(cadena + " actual");
	}
	
	public void setValorActual(String cadena) {
		this.valorActual.setText(cadena);
	}
	
	public String getNuevoValor() {
		return valorNuevo.getText();
	}
}
