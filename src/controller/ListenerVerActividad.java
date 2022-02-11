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
import model.dao.RoutineDao;
import model.dao.UsersDao;
import model.vo.ActivityVo;
import model.vo.ExerciseVo;

public class ListenerVerActividad {

	private Stage primaryStage;
	
	private ActivityVo actividad;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private Text fecha;
	
	@FXML
	private Label nombre;
	
	@FXML
	private Button cerrar;
	
	@FXML
	private ListView<ExerciseVo> listView;
	
	private ObservableList<ExerciseVo> listaEjercicios;
	
	@FXML
	private Text duracion;
	
	@FXML
	private Text responsable;
	
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
	
	public void setActividad(ActivityVo actividad) {
		this.actividad = actividad;
		setFecha();
		setDuracion();
		setResponsable();
		setDescripcion();
		setNombre();
		rellenar();
	}
	
	private void rellenar() {
		ExercisesRoutinesDao dao = new ExercisesRoutinesDao();
		RoutineDao daoRutina = new RoutineDao();
		int idRutina = actividad.getIdRutina();

		try {
			this.listaEjercicios = FXCollections.observableArrayList(dao.listar(daoRutina.buscar(idRutina)));
			this.listView.setItems(listaEjercicios);
			this.listView.setCellFactory(exerciseCell -> new ListenerExerciseCell());
		}catch(Exception ex) {
			error("Se produjo un error al listar: " + ex.getMessage());
		}
	}
	
	private void setNombre() {
		this.nombre.setText(actividad.getNombreActividad());
	}
	
	private void setFecha() {
		this.fecha.setText(actividad.getFecha().toString());
	}
	
	private void setDuracion() {
		this.duracion.setText(actividad.getDuracion().toString());
	}
	
	private void setResponsable() {
		int idEmpleado = this.actividad.getIdEmpleado();
		UsersDao dao = new UsersDao();
		
		try {
			this.responsable.setText(dao.buscar(idEmpleado).getNombreCompleto());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void setDescripcion() {
		this.descripcion.setText(actividad.getDescripcion());
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
