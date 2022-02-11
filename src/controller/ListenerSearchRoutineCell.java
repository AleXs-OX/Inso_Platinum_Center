package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.RoutinesClientsDao;
import model.vo.RoutineVo;
import model.vo.UsersVo;

public class ListenerSearchRoutineCell extends ListCell<RoutineVo>{
	
	private UsersVo usuario;
	
	@FXML
	private FXMLLoader loader;
	
	@FXML
	private AnchorPane box;
	
	@FXML
	private Button nombre;
	
	@FXML
	private Button anadir;
	
	private Stage primaryStage;
	
	public ListenerSearchRoutineCell(UsersVo usuario) {
		this.usuario = usuario;
	}
	
	@Override
	protected void updateItem(RoutineVo rutina, boolean empty) {
		super.updateItem(rutina, empty);
		
		if(rutina != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellSearch.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			if(yaAnadido(rutina)) {
				setText(null);
				setGraphic(null);
			}else {
				anadir.setOnAction(e -> {
					try {
						primaryStage = (Stage) this.anadir.getScene().getWindow();
						anadir(rutina);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				});
				
				nombre.setText(rutina.getNombreRutina());
				setText(null);
				setGraphic(box);
			}
		}
	}
	
	private boolean yaAnadido(RoutineVo rutina) {
		int idRutina = rutina.getIdRutina();
		boolean yaAnadido = false;
		
		RoutinesClientsDao dao = new RoutinesClientsDao();
		try {
			ArrayList<RoutineVo> lista = (ArrayList<RoutineVo>) dao.listar(usuario);
			
			for(int i=0; i<lista.size(); i++) {
				RoutineVo otraRutina = lista.get(i);
			
				if(otraRutina.getIdRutina() == idRutina) {
					yaAnadido = true;
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return yaAnadido;
	}
	
	private void anadir(RoutineVo rutina) {
		RoutinesClientsDao dao = new RoutinesClientsDao();
		
		try {
			dao.anadir(rutina, usuario);
			primaryStage.close();
		}catch(Exception ex) {
			error("Se produjo un error al añadir la rutina al perfil");
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