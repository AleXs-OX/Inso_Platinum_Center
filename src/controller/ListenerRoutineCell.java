package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.vo.RoutineVo;

public class ListenerRoutineCell extends ListCell<RoutineVo>{
	
	@FXML
	private FXMLLoader loader;
	
	@FXML
	private HBox box;
	
	@FXML
	private Button nombre;
	
	@FXML
	private Label calorias;
	
	private Stage primaryStage;
	
	@Override
	protected void updateItem(RoutineVo rutina, boolean empty) {
		super.updateItem(rutina, empty);
		
		if(rutina != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellRoutine.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			
			calorias.setText(rutina.getCalorias() + "");
			nombre.setText(rutina.getNombreRutina());
			
			nombre.setOnAction(e -> {
				try {
					primaryStage = (Stage) this.nombre.getScene().getWindow();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/infoRoutine.fxml"));
					Stage stage = new Stage();
					stage.setTitle(rutina.getNombreRutina());
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(primaryStage); 
					
					ListenerVerRutina controller = loader.getController();
					controller.setRutina(rutina);
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