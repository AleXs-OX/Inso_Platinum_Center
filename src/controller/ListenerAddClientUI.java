package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.dao.UsersDao;
import model.vo.UsersVo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static java.sql.Date.valueOf;

public class ListenerAddClientUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textField_ID_Cliente;
    @FXML
    private TextField textFieldPass;
    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> choiceBoxTarifas;

    /*Variables*/
    private int idUsuario;
    private String nombreUsuario;
    private String nombreCompleto;
    private String contrasena;
    private java.sql.Date fechaNacimiento;

    public void initialize(){

        this.anadeTarifas();
        datePicker.setEditable(false);

    }

    public void anadeTarifas(){
        ObservableList<String> lista_Tarifas = FXCollections.observableArrayList("Tarifa 1", "Tarifa 2", "Tarifa 3");
        choiceBoxTarifas.setItems(lista_Tarifas);

    }

    public void buttonCreateClient(ActionEvent event) throws Exception {

        this.compruebaDatos();

        this.idUsuario = Integer.parseInt(textField_ID_Cliente.getText());
        this.nombreUsuario = textFieldNombre.getText();
        this.nombreCompleto = nombreUsuario.concat(" ").concat(textFieldApellidos.getText()) ;
        this.contrasena = textFieldPass.getText();
        this.fechaNacimiento = java.sql.Date.valueOf(datePicker.getValue());

                                    /* - - Tipos de usuario - -*/
                                    /* 1 - Administrador */
                                    /* 2 - Empleado */
                                    /* 3 - Cliente*/
                                    /**/

        int tipoDeUsuario = 3; // Tipo usuario cliente.

        /*Crea objeto userDao*/
        UsersDao userDao = new UsersDao();
        UsersVo userVo = new UsersVo(idUsuario,nombreCompleto,nombreUsuario,contrasena,fechaNacimiento,tipoDeUsuario);
        /*Registra el usuarioVo creado en userDao para introducirlo en la base de datos*/
        userDao.registrar(userVo);

    }
    public void compruebaDatos(){

        /*Falta añadir ventana emergente*/
        if(textField_ID_Cliente.getText() == null|| this.nombreUsuario == null|| this.nombreCompleto == null|| this.contrasena == null|| datePicker.getValue() == null){

        }

    }

}
