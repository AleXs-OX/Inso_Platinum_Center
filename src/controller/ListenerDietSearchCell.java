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
import model.dao.DietsClientsDao;
import model.vo.DietVo;
import model.vo.UsersVo;

public class ListenerDietSearchCell extends ListCell<DietVo>{
	
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
	
	public ListenerDietSearchCell(UsersVo usuario) {
		this.usuario = usuario;
	}
	
	@Override
	protected void updateItem(DietVo dieta, boolean empty) {
		super.updateItem(dieta, empty);
		
		if(dieta != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellSearch.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			if(yaAnadido(dieta)) {
				setText(null);
				setGraphic(null);
			}else {
				anadir.setOnAction(e -> {
					try {
						primaryStage = (Stage) this.anadir.getScene().getWindow();
						anadir(dieta);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				});
				
				nombre.setText(dieta.getNombreDieta());
				setText(null);
				setGraphic(box);
			}
		}
	}
	
	private boolean yaAnadido(DietVo dieta) {
		int idDieta = dieta.getIdDieta();
		boolean yaAnadido = false;
		
		DietsClientsDao dao = new DietsClientsDao();
		try {
			ArrayList<DietVo> lista = (ArrayList<DietVo>) dao.listar(usuario);
			
			for(int i=0; i<lista.size(); i++) {
				DietVo otraDieta = lista.get(i);
				
				if(otraDieta.getIdDieta() == idDieta) {
					yaAnadido = true;
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return yaAnadido;
	}
	
	private void anadir(DietVo dieta) {
		DietsClientsDao dao = new DietsClientsDao();
		
		try {
			dao.anadir(dieta, usuario);
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