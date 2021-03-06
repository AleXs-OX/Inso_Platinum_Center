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
import model.dao.DietsClientsDao;
import model.vo.DietVo;
import model.vo.UsersVo;

public class ListenerMisDietas {
	
	private UsersVo usuario;
	
	private ObservableList<DietVo> listaDietas;
	
	@FXML
	private ListView<DietVo> listView;
	
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
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/buscarDieta.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Buscar dieta");
				stage.setResizable(false);
				stage.setScene(new Scene(loader.load()));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(primaryStage); 
				
				ListenerBuscarDietas controller = loader.getController();
				controller.setUsuario(usuario);
				stage.showAndWait();
				
				rellenar();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		this.atras.setOnAction(e-> {
			try {
				if(this.usuario.getTipoDeUsuario() == 0) {
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

				}else{

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Employee.fxml"));

					primaryStage = (Stage) this.atras.getScene().getWindow();

					Stage stage = new Stage();
					stage.setTitle("Platinum Center - Panel de Empleado");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));

					ListenerEmployeesUI controller = loader.getController();
					controller.setUsuario(usuario);
					stage.show();

					primaryStage.close();
				}
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
		DietsClientsDao dao = new DietsClientsDao();
		
		try {
			this.listaDietas = FXCollections.observableArrayList(dao.listar(this.usuario));	
			this.listView.setItems(listaDietas);
			this.listView.setCellFactory(dietCell -> new ListenerDietCell());
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