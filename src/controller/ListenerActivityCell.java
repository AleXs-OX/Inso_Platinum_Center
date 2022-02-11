package controller;

import java.io.IOException;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.vo.ActivityVo;

public class ListenerActivityCell extends ListCell<ActivityVo> {
	
	@FXML
	private HBox box;
	
	@FXML
	private Label fecha;
	
	@FXML
	private Button nombre;
	
	@FXML
	private FXMLLoader loader;
	
	private Stage primaryStage;
	
	@Override
	protected void updateItem(ActivityVo actividad, boolean empty) {
		super.updateItem(actividad, empty);
		
		if(actividad != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellActivity.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
				
			Date todayDate = new Date();
				
			if(todayDate.before(actividad.getFecha())) {
				nombre.setText(actividad.getNombreActividad());
				fecha.setText(actividad.getFecha().toString());
				
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
			}else {
				setText(null);
				setGraphic(null);
			}
		}
	}
}
