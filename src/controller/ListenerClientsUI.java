package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.vo.UsersVo;

import java.io.IOException;


public class ListenerClientsUI {

    @FXML
    private ListView<String> listViewClients;

    @FXML
    private Button buttonAddUser;

    @FXML
    private TableColumn tableShowClients;

    @FXML
    private Stage primaryStage;



    public void initialize(){

        this.showListClients();

    }

    public void showListClients(){

       //tableShowClients.
        UsersVo usersVo = new UsersVo();

    }

    public void addClientButton(ActionEvent event) throws Exception {

        /*Carga la pagina encargada de anadir el usuario*/

        //Parent newRoot = FXMLLoader.load(getClass().getResource("/view/AddClient.fxml"));
        //primaryStage = (Stage) this.buttonAddUser.getScene().getWindow();
        //primaryStage.getScene().setRoot(newRoot);

        //FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setLocation(getClass().getResource("/view/AddClient.fxml"));
        //DialogPane addClientPane = fxmlLoader.load();


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/AddClient.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 500, 350);
            Stage stage = new Stage();
            stage.setTitle("Add New Client");
            stage.setScene(scene);
            stage.show();

    }
}


