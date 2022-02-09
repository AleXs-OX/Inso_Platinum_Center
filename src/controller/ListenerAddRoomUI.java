package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.RoomDao;
import model.vo.RoomVo;

import java.sql.Time;
import java.util.LinkedList;

public class ListenerAddRoomUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldIdSala;
    @FXML
    private TextField textFieldAforo;

    @FXML
    private ChoiceBox choiceBoxApertura;
    @FXML
    private ChoiceBox choiceBoxCierre;

    @FXML
    private Button buttonCreateRoom;
    @FXML
    private Button buttonCancel;

    private int idSala;
    private String nombreSala;
    private int aforo;
    private Time horaApertura;
    private Time horaCierre;

    private Stage primaryStage;

    public void initialize(){
        this.rellenaChoiceBox();
    }

    public void rellenaChoiceBox(){

        ObservableList<String> numListApertura = FXCollections.observableArrayList("10", "11","12","13","14","15","16","17","18","19");
        ObservableList<String> numListCierre = FXCollections.observableArrayList("11","12","13","14","15","16","17","18","19","20");

        this.choiceBoxApertura.setItems(numListApertura);
        this.choiceBoxCierre.setItems(numListCierre);

        this.choiceBoxApertura.setValue("Seleccione Hora");
        this.choiceBoxCierre.setValue("Seleccione Hora");

    }

    public void buttonCreateRoomMethod() throws Exception {

        if(this.compruebaDatos()) {

            this.idSala = Integer.parseInt(this.textFieldIdSala.getText());
            this.nombreSala = this.textFieldNombre.getText();
            this.aforo = Integer.parseInt(this.textFieldAforo.getText());

            String horaFinalApertura = this.choiceBoxApertura.getValue().toString().concat(":00:00");
            String horaFinalCierre = this.choiceBoxCierre.getValue().toString().concat(":00:00");

            this.horaApertura = Time.valueOf(horaFinalApertura);
            this.horaCierre = Time.valueOf(horaFinalCierre);

            RoomDao roomDao = new RoomDao();
            RoomVo roomVo = new RoomVo(this.idSala, this.nombreSala, this.aforo, this.horaApertura, this.horaCierre);

            roomDao.anadir(roomVo);
            this.mensajeCreacionExitoso();
            this.closeWindow();
        }
    }

    public void buttonCancelMethod() throws Exception {
        this.closeWindow();
    }

    private void mensajeCreacionExitoso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Sala "+this.nombreSala+ " creada con exito!");
        alert.showAndWait();
    }

    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.textFieldNombre.getScene().getWindow();
        this.primaryStage.close();

    }

    private Boolean compruebaDatos(){
        /*FALTA COMPROBAR QUE EL ID SOLO SEA INT*/

        if(this.textFieldNombre.getText() == ""|| this.textFieldAforo.getText() == ""|| this.textFieldIdSala.getText() == ""
                || this.choiceBoxCierre.getValue() == null || this.choiceBoxApertura.getValue() == null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Algun campo introducido es incorrecto o esta vacio\n");
            alert.showAndWait();
            return false;
        }
        return true;

    }

}
