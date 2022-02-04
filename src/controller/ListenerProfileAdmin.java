package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ListenerProfileAdmin {

    @FXML
    private Button buttonShowClients;
    private Button buttonAddUser;
    private Button buttonEditUser;
    private Button buttonDeleteUser;

    @FXML
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

        //Parent newRoot = FXMLLoader.load(getClass().getResource("/view/showClients.fxml"));
        //primaryStage.getScene().setRoot(newRoot);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showClients.fxml"));

        primaryStage = (Stage) this.buttonShowClients.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = new Stage();
        stage.setTitle("Show Clients");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();





        //System.out.println("PRUEBAAAAAAAAAAAaa");

    }

}
