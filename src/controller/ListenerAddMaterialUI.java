package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.dao.MaterialDao;
import model.dao.RoomDao;
import model.vo.MaterialVo;
import model.vo.RoomVo;

import java.sql.Date;
import java.util.ArrayList;

public class ListenerAddMaterialUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private ChoiceBox  choiceBoxIdSala;
    @FXML
    private TextField textFieldIDMaterial;

    @FXML
    private TextField textFieldAlta;
    @FXML
    private DatePicker datePickerBaja;

    @FXML
    private Button buttonCreateMaterial;
    @FXML
    private Button buttonCancel;

    private int idSala;
    private String nombreMaterial;
    private int idMaterial;
    private Date fechaAlta;
    private java.sql.Date date;

    private Stage primaryStage;

    public void initialize() throws Exception {

        this.rellenaChoiceBox();

        long millis=System.currentTimeMillis();
        this.date = new java.sql.Date(millis);

        this.textFieldAlta.setText(date.toString());
        this.textFieldAlta.setDisable(true);


    }
    public void buttonCreateMaterialMethod() throws Exception {
        if(this.compruebaDatos()) {

            this.idSala = Integer.parseInt(this.choiceBoxIdSala.getValue().toString());
            this.nombreMaterial = this.textFieldNombre.getText();
            this.idMaterial = Integer.parseInt(this.textFieldIDMaterial.getText());

            this.fechaAlta = this.date;


            MaterialDao materialDao = new MaterialDao();
            MaterialVo materialVo = new MaterialVo(this.idMaterial, this.nombreMaterial,this.fechaAlta,null,this.idSala);

            materialDao.anadir(materialVo);
            this.mensajeCreacionExitoso();
            this.closeWindow();
        }
    }

    public void rellenaChoiceBox() throws Exception {

        RoomDao roomDao = new RoomDao();

        ArrayList<Integer> roomArrayList = new ArrayList<>(roomDao.listarID());
        ObservableList<Integer> roomVoList = FXCollections.observableArrayList(roomArrayList);

        this.choiceBoxIdSala.setItems(roomVoList);
        this.choiceBoxIdSala.setValue("Seleccione Sala");

    }
    public void buttonCancelMethod() throws Exception {
        this.closeWindow();
    }

    private Boolean compruebaDatos(){
        /*FALTA COMPROBAR QUE EL ID SOLO SEA INT*/

        if(this.textFieldNombre.getText() == ""|| this.textFieldIDMaterial.getText() == ""|| this.choiceBoxIdSala.getValue() == null
                || this.textFieldAlta.getText() == ""){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Algun campo introducido es incorrecto o esta vacio\n");
            alert.showAndWait();
            return false;
        }
        return true;

    }
    private void mensajeCreacionExitoso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Material "+this.nombreMaterial+ " creado con exito!");
        alert.showAndWait();
    }

    private void closeWindow() {
        this.primaryStage = (Stage) this.textFieldNombre.getScene().getWindow();
        this.primaryStage.close();
    }

}
