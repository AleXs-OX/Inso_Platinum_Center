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
import model.dao.FoodsDietsDao;
import model.vo.DietVo;
import model.vo.FoodVo;

public class ListenerVerDieta {
	
	private Stage primaryStage;
	
	private DietVo dieta;
	
	@FXML
	private Text descripcion;
	
	@FXML
	private ListView<FoodVo> listView;
	
	private ObservableList<FoodVo> listaComidas;
	
	@FXML
	private Label nombre;
	
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
	
	public void setDieta(DietVo dieta) {
		this.dieta = dieta;
		setDescripcion();
		setNombre();
		setCalorias();
		rellenar();
	}
	
	private void rellenar() {
		FoodsDietsDao dao = new FoodsDietsDao();
		
		try {
			this.listaComidas = FXCollections.observableArrayList(dao.listar(dieta));
			this.listView.setItems(listaComidas);
			this.listView.setCellFactory(foodCell -> new ListenerFoodCell());
		}catch(Exception ex) {
			error("Se produjo un error al listar: " + ex.getMessage());
		}
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
