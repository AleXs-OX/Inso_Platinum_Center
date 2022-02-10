package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
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
			
			setText(null);
			setGraphic(box);
		}
	}
}