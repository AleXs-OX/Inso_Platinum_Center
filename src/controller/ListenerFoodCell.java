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
import model.vo.FoodVo;

public class ListenerFoodCell extends ListCell<FoodVo>{
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
	protected void updateItem(FoodVo comida, boolean empty) {
		super.updateItem(comida, empty);
		
		if(comida != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellDiet.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			
			calorias.setText(comida.getCalorias() + "");
			nombre.setText(comida.getNombreAlimento());
			
			nombre.setOnAction(e -> {
				try {
					primaryStage = (Stage) this.nombre.getScene().getWindow();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/infoFood.fxml"));
					Stage stage = new Stage();
					stage.setTitle(comida.getNombreAlimento());
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(primaryStage); 
					
					ListenerVerComida controller = loader.getController();
					controller.setComida(comida);
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
