package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ListenerClientsUI {

	@FXML
    private Button botonMiPerfil;
	
	@FXML
    private Button botonMisActividades;
	
	@FXML
    private Button botonMisDietas;
	
	@FXML
    private Button botonMisRutinas;
	
	@FXML
    private Stage primaryStage;
	
	public void initialize(){
		this.botonMiPerfil.setOnAction(e -> {
			try {
				//TODO
				System.out.println("Botón mi perfil");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisDietas.setOnAction(e -> {
			try {
				//TODO
				System.out.println("Botón mis dietas");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisActividades.setOnAction(e -> {
			try {
				//TODO
				System.out.println("Botón mis actividades");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisRutinas.setOnAction(e -> {
			try {
				//TODO
				System.out.println("Botón mis rutinas");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
    }
}