package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
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
			
			setText(null);
			setGraphic(box);
		}
	}
}