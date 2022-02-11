package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.DietDao;
import model.vo.DietVo;
import model.vo.UsersVo;

public class ListenerBuscarDietas {
	
	private UsersVo usuario;
	
	private ObservableList<DietVo> listaDietas;
	
	@FXML
	private Button cerrar;
	
	@FXML
	private ListView<DietVo> listView;
	
	@FXML
	private TextField buscar;
	
	@FXML
	private Stage primaryStage;
	
	public void initialize() {
		this.buscar.textProperty().addListener((observable, oldValue, newValue) -> {
			buscar(newValue);
		});
		
		this.cerrar.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.cerrar.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public void setUsuario(UsersVo usuario) {
		this.usuario = usuario;
	}
	
	private void buscar(String termino) {
		DietDao dao = new DietDao();
		
		try {
			this.listaDietas = FXCollections.observableArrayList(dao.buscar(termino));
			this.listView.setItems(listaDietas);
			this.listView.setCellFactory(searchCell -> new ListenerDietSearchCell(usuario));
		}catch(Exception ex) {
			error("Se produjo un error inesperado al listar: " + ex.getMessage());
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