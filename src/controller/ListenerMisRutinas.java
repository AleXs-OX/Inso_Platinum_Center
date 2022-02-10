package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.vo.UsersVo;

public class ListenerMisRutinas {
	
	private UsersVo usuario;
	
	@FXML
	private Button buscar;
	
	@FXML
	private Button atras;
	
	@FXML
	private Stage primaryStage;
	
	public void initialize() {
		this.buscar.setOnAction(e-> {
			try {
				//TODO
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.atras.setOnAction(e-> {
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
		//TODO
	}
}
