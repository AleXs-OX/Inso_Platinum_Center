package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

public class ListenerEmployeesUI {

    @FXML
    private Button botonMisRutinas;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonMisDietas;

    @FXML
    private Button botonMisActividades;

    @FXML
    private Stage primaryStage;

    @FXML
    private Label textoUsuario;

    private UsersVo empleado;

    public void initialize(){

        this.botonClientes.setOnAction(e -> {
            //Todo
        });

        this.botonMisDietas.setOnAction(e -> {
            //Todo
        });

        this.botonMisActividades.setOnAction(e -> {
            //Todo
        });

        this.botonMisDietas.setOnAction(e -> {
            //Todo
        });

    }
}
