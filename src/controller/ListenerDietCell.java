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
import model.vo.DietVo;

public class ListenerDietCell extends ListCell<DietVo>{
	
	@FXML
	private Button nombre;
	
	@FXML
	private Label calorias;
	
	@FXML
	private HBox box;
	
	@FXML
	private FXMLLoader loader;
	
	private Stage primaryStage;
	
	@Override
	protected void updateItem(DietVo dieta, boolean empty) {
		super.updateItem(dieta, empty);
		
		if(dieta != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellDiet.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			
			calorias.setText(dieta.getCalorias() + "");
			nombre.setText(dieta.getNombreDieta());
			nombre.setOnAction(e -> {
				try {
					primaryStage = (Stage) this.nombre.getScene().getWindow();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/infoDiet.fxml"));
					Stage stage = new Stage();
					stage.setTitle(dieta.getNombreDieta());
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(primaryStage); 
					
					ListenerVerDieta controller = loader.getController();
					controller.setDieta(dieta);
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