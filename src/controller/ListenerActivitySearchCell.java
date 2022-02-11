package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.ActivitiesClientsDao;
import model.vo.ActivityVo;
import model.vo.UsersVo;

public class ListenerActivitySearchCell extends ListCell<ActivityVo>{

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
	
	public ListenerActivitySearchCell(UsersVo usuario) {
		this.usuario = usuario;
	}
	
	@Override
	protected void updateItem(ActivityVo actividad, boolean empty) {
		super.updateItem(actividad, empty);
		
		if(actividad != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellSearch.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			if(yaAnadido(actividad)) {
				setText(null);
				setGraphic(null);
			}else {
				anadir.setOnAction(e -> {
					try {
						primaryStage = (Stage) this.anadir.getScene().getWindow();
						anadir(actividad);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				});
				
				nombre.setText(actividad.getNombreActividad());
				nombre.setOnAction(e -> {
					try {
						primaryStage = (Stage) this.nombre.getScene().getWindow();

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/infoActivity.fxml"));
						Stage stage = new Stage();
						stage.setTitle(actividad.getNombreActividad());
						stage.setResizable(false);
						stage.setScene(new Scene(loader.load()));
						stage.initModality(Modality.WINDOW_MODAL);
						stage.initOwner(primaryStage); 
						
						ListenerVerActividad controller = loader.getController();
						controller.setActividad(actividad);
						stage.showAndWait();
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				});
				
				setText(null);
				setGraphic(box);
			}
		}
	}
	
	private boolean yaAnadido(ActivityVo actividad) {
		String nombreActividad = actividad.getNombreActividad();
		Date fecha = actividad.getFecha();
		Date hoy = new Date();
		boolean yaAnadido = false;
		
		
		if(fecha.before(hoy)) {
			yaAnadido = true;
		}else {
			ActivitiesClientsDao dao = new ActivitiesClientsDao();
			try {
				ArrayList<ActivityVo> lista = (ArrayList<ActivityVo>) dao.listar(usuario);
				
				for(int i=0; i<lista.size(); i++) {
					ActivityVo otraActividad = lista.get(i);
					
					if(fecha.compareTo(otraActividad.getFecha()) == 0 && nombreActividad.compareTo(otraActividad.getNombreActividad()) == 0) {
						yaAnadido = true;
						break;
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return yaAnadido;
	}
	
	private void anadir(ActivityVo actividad) {
		ActivitiesClientsDao dao = new ActivitiesClientsDao();
		
		try {
			dao.anadir(actividad, usuario);
			primaryStage.close();
		}catch(Exception ex) {
			error("Se produjo un error al añadir la actividad al perfil");
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