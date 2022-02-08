package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ListenerActivityUI {

    @FXML
    private VBox itemsVbox = null;


    public void initializable(){

        System.out.println("Activando tabla");
        this.rellaVbox();
    }


    public void rellaVbox(){

    Node[] nodes = new Node[10];

    for(int i = 0; i < nodes.length; i++){

       try{
            nodes[i] = FXMLLoader.load(getClass().getResource("view/itemDashBoard.fxml"));
            itemsVbox.getChildren().add(nodes[i]);

       }catch (IOException e){
           e.printStackTrace();
       }
    }

    }
}
