package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ListenerProfileAdmin {

    @FXML
    private Button buttonShowClients;
    @FXML
    private Button buttonActividades;

    private Button buttonAddUser;
    private Button buttonEditUser;
    private Button buttonDeleteUser;
    
    @FXML
    private Label textoUsuario;

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

        this.buttonActividades.setOnAction(e -> {
            try {
                this.showActivitiesWindowFXML();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showAdminProfileFXML() throws IOException{

        //Parent newRoot = FXMLLoader.load(getClass().getResource("/view/showUsers.fxml"));
        //primaryStage.getScene().setRoot(newRoot);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showUsers.fxml"));

        primaryStage = (Stage) this.buttonShowClients.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Stage stage = new Stage();
        stage.setTitle("Show Users");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();

    }

    public void showActivitiesWindowFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showActivity.fxml"));

        primaryStage = (Stage) this.buttonActividades.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
        Stage stage = new Stage();
        stage.setTitle("Show Activities");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }
    
    public void setTextoUsuario(String username) {
    	this.textoUsuario.setText("¡Bienvenido, " + username + "!");
    }
}
