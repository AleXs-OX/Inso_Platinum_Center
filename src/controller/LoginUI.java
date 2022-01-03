package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginUI {

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField textFieldPass;

    @FXML
    private TextField textFiledUser;

    private Stage primaryStage;
    //FXMLLoader newRoot;

    //@FXML
    //void buttonLoginPressed(ActionEvent event) {

    //}

    public void initialize(){
            this.buttonLogin.setOnAction(e -> {
                try {
                    this.entrar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }

    //Faltaria cambiar la excepcion a un try catch en el metodo entrar, no en el metodo initialize
    public void entrar() throws IOException {

        System.out.println("Prueba de boton");
        String adm = "Admin";
        String empleado = "Empleado";
        String cliente = "Cliente";

        System.out.println("Usuario: "+ textFiledUser.getText());
        System.out.println("Contraseña: "+ textFieldPass.getText());

        if(adm.equals(textFiledUser.getText())){

            Parent newRoot = FXMLLoader.load(getClass().getResource("/view/Administrator.fxml"));
            primaryStage = (Stage) this.buttonLogin.getScene().getWindow();

            primaryStage.getScene().setRoot(newRoot);
            //AdministratorUI admin = newRoot.<AdministratorUI>getController();
            //stage.setScene(scene);
            //stage.show();

        }else if(empleado.equals(textFiledUser.getText())){

        }else if(cliente.equals(textFiledUser.getText())){

        }
    }


}
