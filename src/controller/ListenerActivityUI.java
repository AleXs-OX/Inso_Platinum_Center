package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;

public class ListenerActivityUI {

    @FXML
    private VBox itemsVbox = null;
    @FXML
    private HBox itemHbox;


    public void initialize(){

        System.out.println("Activando tabla");
        this.rellenaVbox();
    }


    public void rellenaVbox(){

        Node[] nodes = new Node[10];

        for (int i = 0; i < nodes.length; i++) {
            try {

                nodes[i] = FXMLLoader.load(getClass().getResource("/view/itemDashBoard.fxml"));
                itemsVbox.getChildren().add(nodes[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void rellenaNodes(){



    }
}
