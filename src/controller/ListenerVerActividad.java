package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.ActivityVo;

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
	private ListView<ActivityVo> listView;
	
	@FXML
	private Text duracion;
	
	@FXML
	private Text responsable;
	
	public void initialize() {
		//TODO
	}
	
	public void setActividad(ActivityVo actividad) {
		this.actividad = actividad;
		setFecha();
		setDuracion();
		setResponsable();
		setDescripcion();
		setNombre();
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
}
