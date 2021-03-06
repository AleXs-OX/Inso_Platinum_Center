package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.vo.UsersVo;

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
	
	@FXML
    private Label textoUsuario;
	
	private UsersVo usuario;
	
	public void initialize(){
		this.botonMiPerfil.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profile.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Platinum Center - Mi perfil");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				
				ListenerMiPerfil controller = loader.getController();
				controller.setUsuario(usuario);
				stage.show();
				
				primaryStage = (Stage) this.botonMiPerfil.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisDietas.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/myDiets.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Platinum Center - Mis dietas");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				
				ListenerMisDietas controller = loader.getController();
				controller.setUsuario(usuario);
				stage.show();
				
				primaryStage = (Stage) this.botonMisDietas.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisActividades.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/myActivities.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Platinum Center - Mis actividades");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				
				ListenerMisActividades controller = loader.getController();
				controller.setUsuario(usuario);
				stage.show();
				
				primaryStage = (Stage) this.botonMisActividades.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.botonMisRutinas.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/myRoutines.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Platinum Center - Mis rutinas");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				
				ListenerMisRutinas controller = loader.getController();
				controller.setUsuario(usuario);
				stage.show();
				
				primaryStage = (Stage) this.botonMisRutinas.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
    }
	
	public void setUsuario(UsersVo usuario) {
		this.usuario = usuario;
		setTextoUsuario();
	}
	
	private void setTextoUsuario() {
		this.textoUsuario.setText("?Bienvenido, " + this.usuario.getNombreUsuario() + "!");
	}
}