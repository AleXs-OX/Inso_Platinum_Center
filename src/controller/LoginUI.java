package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginUI {

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textFiledPass;

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
    }

}
