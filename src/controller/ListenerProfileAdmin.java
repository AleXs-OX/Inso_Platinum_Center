package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ListenerProfileAdmin {

    @FXML
    private Button buttonShowClients;
    @FXML
    private Button buttonShowEmployees;

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

        //Parent newRoot = FXMLLoader.load(getClass().getResource("/view/showUsers.fxml"));
        //primaryStage.getScene().setRoot(newRoot);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showUsers.fxml"));

        primaryStage = (Stage) this.buttonShowClients.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        Stage stage = new Stage();
        stage.setTitle("Show Users");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();

    }

    public void showEmployeesWindow() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showEmployees.fxml"));

        primaryStage = (Stage) this.buttonShowEmployees.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        Stage stage = new Stage();
        stage.setTitle("Show Employees");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }

}
