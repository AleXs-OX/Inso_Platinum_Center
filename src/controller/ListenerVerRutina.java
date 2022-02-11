package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.ExercisesRoutinesDao;
import model.vo.ExerciseVo;
import model.vo.RoutineVo;

public class ListenerVerRutina {
	
	private Stage primaryStage;
	
	private RoutineVo rutina;
	
	@FXML
	private ListView<ExerciseVo> listView;
	
	private ObservableList<ExerciseVo> listaEjercicios;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private Label calorias;
	
	@FXML
	private Label nombre;
	
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
	
	public void setRutina(RoutineVo rutina) {
		this.rutina = rutina;
		setDescripcion();
		setNombre();
		setCalorias();
		rellenar();
	}
	
	private void rellenar() {
		ExercisesRoutinesDao dao = new ExercisesRoutinesDao();
		
		try {
			this.listaEjercicios = FXCollections.observableArrayList(dao.listar(rutina));
			this.listView.setItems(listaEjercicios);
			this.listView.setCellFactory(exerciseCell -> new ListenerExerciseCell());
		}catch(Exception ex) {
			error("Se produjo un error al listar: " + ex.getMessage());
		}
	}
	
	private void setDescripcion() {
		this.descripcion.setText(rutina.getDescripcion());
	}
	
	private void setNombre() {
		this.nombre.setText(rutina.getNombreRutina());
	}
	
	private void setCalorias() {
		this.calorias.setText("" + rutina.getCalorias());
	}
	
	private void error(String texto){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Error");
			stage.setResizable(false);
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage); 
			
			ListenerError controller = loader.getController();
			controller.setMensaje(texto);
			stage.showAndWait();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	 }
}
