package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

import java.io.IOException;

public class ListenerAddClientUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textFieldIdEmpleado;
    @FXML
    private TextField textFieldPass;
    @FXML
    private DatePicker datePicker;

    /*Buttons*/
    @FXML
    private Button buttonCreateEmployee;
    @FXML
    private Button buttonCancel;
    private Stage primaryStage;

    /*Variables*/
    private int idUsuario;
    private String nombreUsuario;
    private String nombreCompleto;
    private String contrasena;
    private java.sql.Date fechaContratacion;


    // METODO COMENTADO PORQUE NO COMPILABA Y NO DEJABA EJECTURAR EL PROGRAMA
    /*
    public void buttonCreateEmployeeMethod(ActionEvent event) throws Exception {

        if(this.compruebaDatos()) {

            this.idUsuario = Integer.parseInt(textField_ID_Cliente.getText());
            this.nombreUsuario = textFieldNombre.getText();
            this.nombreCompleto = nombreUsuario.concat(" ").concat(textFieldApellidos.getText());
            this.contrasena = textFieldPass.getText();
            this.fechaContratacion = java.sql.Date.valueOf(datePicker.getValue());

            //- - Tipos de usuario -
            //1 - Administrador
            // 2 - Empleado
            //3 - Cliente


            int tipoDeUsuario = 2; // Tipo usuario cliente.

            //Crea objeto userDao


            UsersDao userDao = new UsersDao();
            UsersVo userVo = new UsersVo(idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaContratacion, tipoDeUsuario);
            //Registra el usuarioVo creado en userDao para introducirlo en la base de datos
            userDao.anadir(userVo);

            this.resetEspaciosBlanco();
            this.mensajeCreacionExitoso();

            //Recarga la tabla de nuevo para que el usuario creado sea visible
            //Usa extends de ListenerClientsUI

            this.closeWindow();
        }

    }
    */

    public void buttonCancelMethod(ActionEvent event) throws Exception {

        this.closeWindow();
    }

   /* private Boolean compruebaDatos(){
        /*FALTA COMPROBAR QUE EL ID SOLO SEA INT*/
    /*
        if(textField_ID_Cliente.getText() == null|| this.textFieldNombre.getText() == null|| this.textFieldApellidos.getText() == null
                || this.textFieldPass.getText() == null || this.choiceBoxTarifas.getValue() == null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Algun campo introducido es incorrecto o esta vacio\n");
            alert.showAndWait();
            return false;

        }else{

            if(datePicker.getValue() == null){
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Fecha incorrecta");
                alert2.setHeaderText(null);
                alert2.setContentText("La fecha introducida es incorrecta");
                alert2.setContentText("Porfavor pulse "+"enter"+ "cuando termine de escribirla o seleccione el selector de fecha");
                alert2.showAndWait();
                return false;
            }
        }
        return true;

    }*/

    private void mensajeCreacionExitoso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Usuario "+this.nombreCompleto+ " creado con exito!");
        alert.showAndWait();
    }

    private void resetEspaciosBlanco(){
        this.datePicker.setValue(null);
        this.textFieldNombre.setText("");
        this.textFieldApellidos.setText("");
        this.textFieldIdEmpleado.setText("");
        this.textFieldPass.setText("");

        // this.choiceBoxTarifas.setValue("Seleccionar");

    }

    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.textFieldNombre.getScene().getWindow();
        this.primaryStage.close();

    }
}
