package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.vo.ExerciseVo;

public class ListenerVerEjercicio {
	
	private Stage primaryStage;
	
	private ExerciseVo ejercicio;
	
	@FXML
	private ImageView imagen;
	
	@FXML
	private Label nombre;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private Label calorias;
	
	@FXML
	private Button cerrar;
	
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
	
	public void setEjercicio(ExerciseVo ejercicio) {
		this.ejercicio = ejercicio;
		setImagen();
		setNombre();
		setDescripcion();
		setCalorias();
	}
	
	private void setImagen() {
		Image im = new Image(ejercicio.getImage());
		imagen.setImage(im);
	}
	
	private void setNombre() {
		this.nombre.setText(ejercicio.getNombreEjercicio());
	}
	
	private void setDescripcion() {
		this.descripcion.setText(ejercicio.getDescripcion());
	}
	
	private void setCalorias() {
		this.calorias.setText(ejercicio.getCalorias() + "");
	}
}
