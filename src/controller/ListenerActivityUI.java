package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.ActivityDao;
import model.dao.UsersDao;
import model.vo.ActivityVo;
import model.vo.UsersVo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

public class ListenerActivityUI {

    @FXML
    private TableView<ActivityVo> tablaActividades;
    @FXML
    private TableColumn<UsersVo, String> tableColumnActividad;
    @FXML
    private TableColumn<UsersVo, Timestamp> tableColumnFecha;
    @FXML
    private TableColumn<UsersVo, Integer> tableColumnEmpleado;
    @FXML
    private TableColumn<UsersVo, Integer> tableColumnSala;
    @FXML
    private TableColumn<UsersVo, Integer> tableColumnRutina;

    private Stage primaryStage;

    /*Buttons*/

    @FXML
    private Button buttonAddActivity;
    @FXML
    private Button buttonDeleteActivity;
    @FXML
    private Button buttonBackMenu;
    @FXML
    private Button buttonMoreInfo;
    @FXML
    private Button buttonReloadActivities;


    public void initialize() throws Exception {

        System.out.println("Activando tabla");
        this.rellenaTabla();
    }


    public void rellenaTabla() throws Exception {

        ActivityDao activityDao = new ActivityDao();
        ArrayList<ActivityVo> userArrayList = new ArrayList<>(activityDao.listar());

        tableColumnActividad.setCellValueFactory(new PropertyValueFactory("nombreActividad"));
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        tableColumnEmpleado.setCellValueFactory(new PropertyValueFactory("idEmpleado"));
        tableColumnSala.setCellValueFactory(new PropertyValueFactory("idSala"));
        tableColumnRutina.setCellValueFactory(new PropertyValueFactory("idRutina"));

        ObservableList<ActivityVo> activityVoList = FXCollections.observableArrayList(userArrayList);

        tablaActividades.setItems(activityVoList);

    }

    public void addActivity() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/addActivity.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 450, 600);
        Stage stage = new Stage();
        stage.setTitle("Add New Room");
        stage.setScene(scene);
        stage.show();
    }
    public void deleteActivity() throws Exception {

        if(this.tablaActividades.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("ELIMINAR");
        }else {
            ActivityVo activityVoSelected = this.tablaActividades.getSelectionModel().getSelectedItem();

            if (areYouSureAlert(activityVoSelected)) {
                ActivityDao activityDao = new ActivityDao();
                activityDao.eliminar(activityVoSelected);
                this.rellenaTabla();
            }
        }
    }

    public void moreInfo(){

        if(this.tablaActividades.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("MAS INFORMACION");

        }else {
            ActivityVo activityVoSelected = this.tablaActividades.getSelectionModel().getSelectedItem();
            String tipo;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion completa");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de la actividad: "+activityVoSelected.getNombreActividad()+ "\n"+ "ID del Empleado: "+activityVoSelected.getIdEmpleado()+"\n"
                    +"Fecha de creacion "+activityVoSelected.getFecha()+"\n"
                    + "Duracion: "+activityVoSelected.getDuracion()+" hora(s)\n" +
                    "Descripcion: "+activityVoSelected.getDescripcion()+"\n"
                    +"IdSala donde se imparte: "+activityVoSelected.getIdSala()+"\n"
                    +"IdRutina que se aplica: "+activityVoSelected.getIdRutina()+"\n");

            alert.showAndWait();

        }
    }
    public void reloadActivities() throws Exception {
        this.rellenaTabla();
    }

    public void backButtonMethod() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Administrator.fxml"));

        primaryStage = (Stage) this.buttonBackMenu.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 695, 462);
        Stage stage = new Stage();
        stage.setTitle("Administrador");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }


    private Boolean areYouSureAlert(ActivityVo activity){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion Eliminar");
        alert.setHeaderText("Eliminar ACTIVIDAD: "+ activity.getNombreActividad()+".");
        alert.setContentText("Pulse Aceptar para confirmar");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }
    private void errorDeleteAlert(String message){

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Actividad no seleccionada");
        alert2.setHeaderText(null);
        alert2.setContentText("Por favor seleccione una ACTIVIDAD antes de pulsar " + message);
        alert2.showAndWait();
    }


}
