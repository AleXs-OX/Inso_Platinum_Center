package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.dao.ActivityDao;
import model.vo.ActivityVo;
import model.vo.UsersVo;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;

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


}
