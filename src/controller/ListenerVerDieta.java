package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.DietVo;
import model.vo.FoodVo;

public class ListenerVerDieta {
	
	private Stage primaryStage;
	
	private DietVo dieta;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private ListView<FoodVo> listView;
	
	@FXML
	private Label nombre;
	
	@FXML
	private Label calorias;
	
	public void initialize() {
		//TODO
	}
	
	public void setDieta(DietVo dieta) {
		this.dieta = dieta;
		setDescripcion();
		setNombre();
		setCalorias();
	}
	
	private void setDescripcion() {
		this.descripcion.setText(dieta.getDescripcion());
	}
	
	private void setNombre() {
		this.nombre.setText(dieta.getNombreDieta());
	}
	
	private void setCalorias() {
		this.calorias.setText("" + dieta.getCalorias());
	}
}
