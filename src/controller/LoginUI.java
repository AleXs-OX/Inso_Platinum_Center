package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    //@FXML
    //void buttonLoginPressed(ActionEvent event) {

    //}

    public void initialize(){
        buttonLogin.setOnAction(e -> {
            try {
                this.entrar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Faltaria cambiar la excepcion a un try catch en el metodo entrar, no en el metodo initialize
    public void entrar() throws IOException {
        FXMLLoader newRoot;

        System.out.println("Prueba de boton");
        String adm = "Admin";
        String empleado = "Empleado";
        String cliente = "Cliente";

        System.out.println("Usuario: "+ textFiledUser.getText().toString());
        System.out.println("Contraseña: "+ textFieldPass.getText().toString());

        if(adm.equals(textFiledUser.getText().toString())){

            newRoot = new FXMLLoader(getClass().getResource("/view/Administrator.fxml"));
            Parent root = (Parent)newRoot.load();
            AdministratorUI admin = newRoot.<AdministratorUI>getController();
            primaryStage.getScene().setRoot(root);

        }else if(empleado.equals(textFiledUser.getText().toString())){

        }else if(cliente.equals(textFiledUser.getText().toString())){

        }
    }


}
