package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ListenerProfileAdmin {

    @FXML
    private Button buttonShowClients;
    private Stage primaryStage;


    public void initialize(){

        this.buttonShowClients.setOnAction(e -> {
            try {
                this.showAdminProfileFXML();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showAdminProfileFXML() throws IOException{

        Parent newRoot = FXMLLoader.load(getClass().getResource("/view/showClients.fxml"));
        primaryStage = (Stage) this.buttonShowClients.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);

        System.out.println("PRUEBAAAAAAAAAAAaa");

    }
}
