package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.vo.UsersVo;

import java.io.IOException;

public class ListenerEmployeesUI {

    @FXML
    private Button botonClientes;
    @FXML
    private Button botonMisActividades;
    @FXML
    private Button botonMisRutinas;
    @FXML
    private Button botonMisDietas;


    @FXML
    private Label textoUsuario;

    @FXML
    private Stage primaryStage;

    private UsersVo usuario;

    public void initialize(){

        this.botonClientes.setOnAction(e -> {
            try {
                this.showAdminProfileFXML();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        this.botonMisActividades.setOnAction(e -> {
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
        primaryStage = (Stage) this.botonClientes.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 805, 560);
        Stage stage = new Stage();
        stage.setTitle("Show Users");
        stage.setScene(scene);

        ListenerUsersUI controller = fxmlLoader.getController();
        controller.changeToEmployee(this.usuario);

        stage.show();

        primaryStage.close();

    }

    public void showActivitiesWindowFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/showActivity.fxml"));

        primaryStage = (Stage) this.botonMisActividades.getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
        Stage stage = new Stage();
        stage.setTitle("Show Room & Material");
        stage.setScene(scene);

        ListenerActivityUI controller = fxmlLoader.getController();
        controller.changeToEmployee(this.usuario);

        stage.show();

        primaryStage.close();
    }

    public void showMyDietsWindowFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/myDiets.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Platinum Center - Mis dietas");
        stage.setResizable(false);
        stage.setScene(new Scene(fxmlLoader.load()));

        ListenerMisDietas controller = fxmlLoader.getController();
        controller.setUsuario(usuario);

        stage.show();

        primaryStage = (Stage) this.botonMisDietas.getScene().getWindow();
        primaryStage.close();

    }

    public void showMyRoutinesWindowFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/myRoutines.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Platinum Center - Mis rutinas");
        stage.setResizable(false);
        stage.setScene(new Scene(fxmlLoader.load()));

        ListenerMisRutinas controller = fxmlLoader.getController();
        controller.setUsuario(usuario);
        stage.show();

        primaryStage = (Stage) this.botonMisRutinas.getScene().getWindow();
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
