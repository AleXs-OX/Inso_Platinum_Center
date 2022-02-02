package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class ListenerShowClientsUI {

    @FXML
    private ListView<String> listViewClients;


    public void initialize(){

    this.showListClients();

    }

    public void showListClients(){

        listViewClients.getItems().add("Manolo");
        listViewClients.getItems().add("Pepe");
        listViewClients.getItems().add("Dani");
        listViewClients.getItems().add("Maria");
        listViewClients.getItems().add("Lucia");
        listViewClients.getItems().add("Perro");
        listViewClients.getItems().add("Gato");

    }
}


