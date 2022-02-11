package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.vo.ExerciseVo;

public class ListenerExerciseCell extends ListCell<ExerciseVo>{
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
	protected void updateItem(ExerciseVo ejercicio, boolean empty) {
		super.updateItem(ejercicio, empty);
		
		if(ejercicio != null) {
			if(loader == null) {
				loader = new FXMLLoader(getClass().getResource("/view/cellRoutine.fxml"));
				loader.setController(this);
				
				try {
					loader.load();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
			
			calorias.setText(ejercicio.getCalorias() + "");
			nombre.setText(ejercicio.getNombreEjercicio());
			
			nombre.setOnAction(e -> {
				try {
					//TODO
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			});
			
			setText(null);
			setGraphic(box);
		}
	}
}
