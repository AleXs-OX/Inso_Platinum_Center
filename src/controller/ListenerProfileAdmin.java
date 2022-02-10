package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.vo.UsersVo;

import java.io.IOException;

public class ListenerProfileAdmin {

    @FXML
    private Button buttonShowClients;
    @FXML
    private Button buttonActividades;
    @FXML
    private Button buttonSalasYMaterial;
    @FXML
    private Button buttonMaterial;

    private Button buttonEditUser;
    private Button buttonDeleteUser;
    
    @FXML
    private Label textoUsuario;

    @FXML
    private Stage primaryStage;
    
    private UsersVo usuario;

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

        this.buttonSalasYMaterial.setOnAction(e -> {
            try {
                this.showRoomsYMaterialFXML();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        this.buttonMaterial.setOnAction(e -> {
            try {
                this.showRoomsYMaterialFXML();
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

        Scene scene = new Scene(fxmlLoader.load(), 805, 560);
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
        stage.setTitle("Show Room & Material");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }

    public void showRoomsYMaterialFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showSalasYMaterial.fxml"));

        primaryStage = (Stage) this.buttonActividades.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 987, 736);
        Stage stage = new Stage();
        stage.setTitle("Show Activities");
        stage.setScene(scene);
        stage.show();

        primaryStage.close();
    }
    
    public void setUsuario(UsersVo usuario) {
    	this.usuario = usuario;
    	setTextoUsuario();
    }
    
    private void setTextoUsuario() {
    	this.textoUsuario.setText("¡Bienvenido, " + this.usuario.getNombreUsuario() + "!");
    }
}
