package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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

        /*EJEMPLO temporal de prueba*/
        listViewClients.getItems().add("Manolo");
        listViewClients.getItems().add("Pepe");
        listViewClients.getItems().add("Dani");
        listViewClients.getItems().add("Maria");
        listViewClients.getItems().add("Lucia");
        listViewClients.getItems().add("Perro");
        listViewClients.getItems().add("Gato");
    }

    public void addClientButton(ActionEvent event) throws Exception {

        /*Carga la pagina encargada de anadir el usuario*/

        Parent newRoot = FXMLLoader.load(getClass().getResource("/view/AddClient.fxml"));
        primaryStage = (Stage) this.buttonAddUser.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);



    }
}


