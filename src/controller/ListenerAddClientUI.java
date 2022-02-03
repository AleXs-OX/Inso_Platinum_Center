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

public class ListenerAddClientUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private DatePicker textFieldFecha;
    @FXML
    private TextField textField_ID_Cliente;
    @FXML
    private TextField textFieldPass;

    @FXML
    private ChoiceBox<String> choiceBoxTarifas;

    public void initialize(){

        this.anadeTarifas();

    }

    public void anadeTarifas(){
        ObservableList<String> lista_Tarifas = FXCollections.observableArrayList("Tarifa 1", "Tarifa 2", "Tarifa 3");
        choiceBoxTarifas.setItems(lista_Tarifas);

    }

    public void buttonCreateClient(ActionEvent event) throws Exception {

        this.compruebaDatos();

        //Transformacion a int
         int idUsuario = Integer.parseInt(textField_ID_Cliente.getText());
         String nombreUsuario = textFieldNombre.getText();
         String nombreCompleto = nombreUsuario.concat(" ").concat(textFieldApellidos.getText()) ;
         String contrasena = textFieldPass.getText();
         //Date fechaNacimiento = parse(textFieldFecha.toString());

        /* - - Transformacion DatePicker en String y despues en Date - - */
        //String date = textFieldFecha.toString();

        /* - - Crea objeto para transformarlo en DateJavaUtil - - */
        //SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        /* - - Transformacion DateJavaUtil en DateJavaSQL - - */
        //java.sql.Date fechaNacimiento = new java.sql.Date (formatter1.parse(date).getTime());

        java.sql.Date fechaNacimiento = new java.sql.Date(1999/10/16);


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

    }

}
