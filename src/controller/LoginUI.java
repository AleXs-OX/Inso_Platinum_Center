package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginUI {

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField textFieldPass;

    @FXML
    private TextField textFiledUser;

    //@FXML
    //void buttonLoginPressed(ActionEvent event) {

    //}

    public void initialize(){
        buttonLogin.setOnAction(e -> this.entrar());
    }

    public void entrar(){
        System.out.println("Prueba de boton");

        System.out.println("Usuario: "+ textFiledUser.getText().toString());
        System.out.println("Contraseña: "+ textFieldPass.getText().toString());
    }

}
