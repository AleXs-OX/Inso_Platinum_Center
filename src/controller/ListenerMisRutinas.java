package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.RoutinesClientsDao;
import model.vo.RoutineVo;
import model.vo.UsersVo;

public class ListenerMisRutinas {
	
	private UsersVo usuario;
	
	@FXML
	private ListView<RoutineVo> listView;
	
	private ObservableList<RoutineVo> listaRutinas;
	
	@FXML
	private Button buscar;
	
	@FXML
	private Button atras;
	
	@FXML
	private Stage primaryStage;
	
	public void initialize() {
		this.buscar.setOnAction(e-> {
			try {
				primaryStage = (Stage) this.buscar.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buscarRutina.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Buscar rutina");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage); 
				
				ListenerBuscarRutinas controller = loader.getController();
				controller.setUsuario(usuario);
				stage.showAndWait();
				
				rellenar();
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
		rellenar();
	}
	
	private void rellenar() {
		RoutinesClientsDao dao = new RoutinesClientsDao();
		
		try {
			this.listaRutinas = FXCollections.observableArrayList(dao.listar(this.usuario));
			this.listView.setItems(listaRutinas);
			this.listView.setCellFactory(routineCell -> new ListenerRoutineCell());
		}catch(Exception e) {
			error("Se produjo un error inesperado al listar: " + e.getMessage());
		}
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
