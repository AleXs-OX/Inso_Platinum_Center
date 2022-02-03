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

public class ListenerAddClientUI {

    @FXML
    private TextField textFieldNombre;
    private TextField textFieldApellidos;
    private DatePicker textFieldFecha;
    private TextField textField_ID_Cliente;
    private TextField textFieldTarifa;
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
         String nombreCompleto = nombreUsuario.concat(" ").concat(nombreUsuario) ;
         String contrasena = textFieldPass.getText();
         //Date fechaNacimiento = textFieldFecha.to;
         int tipoDeUsuario;


        /* 1º Crear objeto usersDAO*/
        UsersDao userDao = new UsersDao();
        UsersVo userVo = new UsersVo();

        userDao.registrar(userVo);



        /* 2º Alli se conecta a la base de tados con extends de conectionDB*/
        /* 3º Se usa de modelo DAO su verion en VO*/
    }
    public void compruebaDatos(){

        /*Falta añadir ventana emergente*/

    }

}
