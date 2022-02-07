package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;


public class ListenerUsersUI {

    Boolean filter = true; /* True = Filter Users*/
                           /* False = Filter Employees*/

    @FXML
    private ListView<String> listViewClients;

    @FXML
    private Button buttonAddUser;
    @FXML
    private Button buttonReload;
    @FXML
    private Button backButton;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonFilterBy;


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
    @FXML
    private TableColumn<UsersVo, Integer> rateColumn;

    @FXML
    private Stage primaryStage;


    public void initialize() throws Exception {

        this.showListClients(this.filter);
    }

    public void showListClients(Boolean filter) throws Exception {

        UsersDao usersDao = new UsersDao();
        ArrayList<UsersVo> userArrayList;

        if(filter) {
            userArrayList = new ArrayList<>(usersDao.listarPorTipo(0));
        }
        else {
            userArrayList = new ArrayList<>(usersDao.listarPorTipo(1));
        }

            ObservableList<UsersVo> userList = FXCollections.observableArrayList(userArrayList);

            idUserColumn.setCellValueFactory(new PropertyValueFactory("idUsuario"));
            nameColumn.setCellValueFactory(new PropertyValueFactory("nombreUsuario"));
            allNameColumn.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
            dateColumn.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
            //rateColumn.setCellValueFactory(new PropertyValueFactory("tipoUsuario"));
            if(!userList.isEmpty() && userList.get(0).getTipoDeUsuario() == 0){

                System.out.println("Filtrando busqueda por [Clientes]");

            }else{
                //rateColumn.setText("Empleado");
                System.out.println("Filtrando busqueda por [Empleados]");

            }

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
            fxmlLoader.setLocation(getClass().getResource("/view/addEmployee.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 450, 700);
            Stage stage = new Stage();
            stage.setTitle("Add New Employee");
            stage.setScene(scene);
            stage.show();

    }

    public void buttonInformationMethod() {

        if(this.tableShowClients.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("MAS INFORMACION");
        }else {
            UsersVo usersVoSelected = this.tableShowClients.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion completa");
            alert.setHeaderText(null);
            alert.setContentText("Hola \n"+ "hola");

            alert.showAndWait();

        }
    }

    public void ButtonFilterByMethod() throws Exception {

        this.filter = !this.filter;

        if(this.filter)
            this.buttonFilterBy.setText("Filtrar por: Clientes");
        else
            this.buttonFilterBy.setText("Filtrar por: Empleados");

        this.showListClients(this.filter);
    }

    public void buttonDeleteMethod(ActionEvent event) throws Exception {

        if(this.tableShowClients.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("ELIMINAR");
        }else {
            UsersVo usersVoSelected = this.tableShowClients.getSelectionModel().getSelectedItem();

        }
    }

    public void backButtonMethod() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Administrator.fxml"));

        primaryStage = (Stage) this.buttonAddUser.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        Stage stage = new Stage();
        stage.setTitle("Administrador");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }

    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.buttonAddUser.getScene().getWindow();
        this.primaryStage.close();
        this.showListClients(this.filter);
    }

    public void buttonReloadUsers() throws Exception {
        this.showListClients(this.filter);
    }
   // public Stage getStage(){
       // return this.primaryStage.re;
   // }

    private Boolean areYouSureAlert(UsersVo user){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion Eliminar");
        alert.setHeaderText("Eliminar usuario: "+ user.getNombreCompleto()+".");
        alert.setContentText("Pulse OK para confirmar");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }

    private void errorDeleteAlert(String message){

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Usuario no seleccionado");
        alert2.setHeaderText(null);
        alert2.setContentText("Porfavor seleccione un usuario antes de pulsar " + message);
        alert2.showAndWait();
    }
}


