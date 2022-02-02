package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

import java.io.IOException;


public class ListenerClientsUI {

    @FXML
    private ListView<String> listViewClients;

    @FXML
    private Button buttonAddUser;

    @FXML
    private Stage primaryStage;


    public void initialize(){

    this.showListClients();

    }

    public void showListClients(){

        /*EJEMPLO temporal*/
        listViewClients.getItems().add("Manolo");
        listViewClients.getItems().add("Pepe");
        listViewClients.getItems().add("Dani");
        listViewClients.getItems().add("Maria");
        listViewClients.getItems().add("Lucia");
        listViewClients.getItems().add("Perro");
        listViewClients.getItems().add("Gato");
    }

    public void addClient(ActionEvent event) throws Exception {

        /*Carga la pagina encargada de anadir el usuario*/

        Parent newRoot = FXMLLoader.load(getClass().getResource("/view/AddClient.fxml"));
        primaryStage = (Stage) this.buttonAddUser.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);

        /* 1º Crear objeto usersDAO*/
        UsersDao userDao = new UsersDao();
        UsersVo userVo = new UsersVo();

        userDao.registrar(userVo);



        /* 2º Alli se conecta a la base de tados con extends de conectionDB*/
        /* 3º Se usa de modelo DAO su verion en VO*/

    }
}


