package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.RoutineDao;
import model.vo.RoutineVo;

public class ListenerAddRoutineUI {

    @FXML
    private TextField textFieldIDRutina;
    @FXML
    private TextField textFieldIDCreador;
    @FXML
    private TextField textFieldCalorias;
    @FXML
    private TextField textFieldNombreRutina;
    @FXML
    private TextField textFieldDescripcion;

    /* Buttons */
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;

    private Stage primaryStage;

    /* Variables */
    private int idRutina;
    private int idCreador;
    private String nombreRutina;
    private String descripcion;
    private int calorias;

    public void buttonCreateRoutineMethod(ActionEvent event) throws Exception {

        if (this.compruebaDatos()) {

            this.idRutina = Integer.parseInt(this.textFieldIDRutina.getText());
            this.idCreador = Integer.parseInt(this.textFieldIDCreador.getText());
            this.nombreRutina = this.textFieldNombreRutina.getText();
            this.descripcion = this.textFieldDescripcion.getText();
            this.calorias = Integer.parseInt(this.textFieldCalorias.getText());

            RoutineDao rutinaDao = new RoutineDao();
            RoutineVo rutinaVo = new RoutineVo(idRutina, idCreador, nombreRutina, descripcion, calorias);

            this.resetEspaciosBlanco();
            this.mensajeCreacionExitoso();

            this.closeWindow();
        }

    }

    private Boolean compruebaDatos() {

        if (this.textFieldIDRutina.getText() == "" || !isInt(this.textFieldIDRutina.getText()) || this.textFieldIDCreador.getText() == "" ||
        !isInt(this.textFieldIDCreador.getText()) || this.textFieldCalorias.getText() == "" || !isInt(this.textFieldCalorias.getText()) ||
        this.textFieldNombreRutina.getText() == "" || this.textFieldDescripcion.getText() == ""){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Algun campo introducido es incorrecto o esta vacio\n");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }

    private void mensajeCreacionExitoso() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Rutina " + this.nombreRutina + " creada con exito!");
        alert.showAndWait();
    }

    private void resetEspaciosBlanco() {
        this.textFieldIDRutina.setText("");
        this.textFieldIDCreador.setText("");
        this.textFieldNombreRutina.setText("");
        this.textFieldDescripcion.setText("");
        this.textFieldCalorias.setText("");

    }

    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.textFieldNombreRutina.getScene().getWindow();
        this.primaryStage.close();

    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
