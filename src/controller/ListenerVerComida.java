package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.FoodVo;

public class ListenerVerComida {
	private Stage primaryStage;
	
	private FoodVo comida;
	
	@FXML
	private ImageView imagen;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private Label calorias;
	
	@FXML
	private Button cerrar;
	
	@FXML
	private Label nombre;
	
	public void initialize() {
		this.cerrar.setOnAction(e -> {
			try {
				primaryStage = (Stage) this.cerrar.getScene().getWindow();
				primaryStage.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public void setComida(FoodVo comida) {
		this.comida = comida;
		setImagen();
		setDescripcion();
		setCalorias();
		setNombre();
	}
	
	private void setImagen() {
		Image im = new Image(comida.getImage());
		imagen.setImage(im);
	}
	
	private void setDescripcion() {
		this.descripcion.setText(comida.getDescripcion());
	}
	
	private void setCalorias() {
		this.calorias.setText(comida.getCalorias() + "");
	}
	
	private void setNombre() {
		this.nombre.setText(comida.getNombreAlimento());
	}
}
