package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

import java.sql.Date;
import java.util.ArrayList;


public class ListenerClientsUI {

    @FXML
    private ListView<String> listViewClients;

    @FXML
    private Button buttonAddUser;

    @FXML
    private Button buttonReload;

    @FXML
    private TableView<UsersVo> tableShowClients;

    /*Table columns*/
    @FXML
    private TableColumn<UsersVo, Integer> idUserColumn;
    @FXML
    private TableColumn<UsersVo, String> nameColumn;
    @FXML
    private TableColumn<UsersVo, String> allNameColumn;
    @FXML
    private TableColumn<UsersVo, Date> dateColumn;
    //@FXML
    //private TableColumn<UsersVo, Integer> rateColumn;

    @FXML
    private Stage primaryStage;


    public void initialize() throws Exception {

        this.showListClients();
    }

    public void showListClients() throws Exception {

        idUserColumn.setCellValueFactory(new PropertyValueFactory("idUsuario"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
        allNameColumn.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));

        UsersDao usersDao = new UsersDao();
        ArrayList<UsersVo> userArrayList;
        userArrayList = usersDao.listar();

        ObservableList<UsersVo> userList = FXCollections.observableArrayList(userArrayList);

        tableShowClients.setItems(userList);

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

    public void buttonReloadUsers() throws Exception {
        this.showListClients();
    }
}


