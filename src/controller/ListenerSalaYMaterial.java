package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.ActivityDao;
import model.dao.MaterialDao;
import model.dao.RoomDao;
import model.vo.ActivityVo;
import model.vo.MaterialVo;
import model.vo.RoomVo;
import model.vo.UsersVo;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Optional;

public class ListenerSalaYMaterial {

    @FXML
    private Button buttonAddSala;
    @FXML
    private Button buttonAddMaterial;

    @FXML
    private Button buttonDeleteSala;
    @FXML
    private Button buttonDeleteMaterial;

    @FXML
    private Button buttonShowMaterial;
    @FXML
    private Button buttonReloadInfo;
    @FXML
    private Button buttonMoreInfo;
    @FXML
    private Button buttonBackMenu;



    @FXML
    private TableView<RoomVo> tableShowSalas;
    @FXML
    private TableView<MaterialVo> tableShowMaterial;


    /*Table Salas columns*/
    @FXML
    private TableColumn<RoomVo, Integer> idSalaColumn;
    @FXML
    private TableColumn<RoomVo, String> nameColumn;
    @FXML
    private TableColumn<RoomVo, Integer> aforoColumn;
    @FXML
    private TableColumn<RoomVo, Time> timeOpenColumn;
    @FXML
    private TableColumn<RoomVo, Time> timeCloseColumn;

    /*Table Material columns*/
    @FXML
    private TableColumn<MaterialVo, Integer> idMaterialColumn;
    @FXML
    private TableColumn<MaterialVo, String> nameMaterialColumn;
    @FXML
    private TableColumn<MaterialVo, Date> timeAltaColumn;
    @FXML
    private TableColumn<MaterialVo, Date> timeBajaColumn;
    @FXML
    private TableColumn<MaterialVo, Integer> idSala;


    private Stage primaryStage;


    public void initialize() throws Exception {

        this.showRooms();
        //this.tableShowMaterial();
    }

    private void showRooms() throws Exception {

        RoomDao roomDao = new RoomDao();
        ArrayList<RoomVo> roomArrayList = new ArrayList<>(roomDao.listar());

        idSalaColumn.setCellValueFactory(new PropertyValueFactory("idSala"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("nombreSala"));
        aforoColumn.setCellValueFactory(new PropertyValueFactory("aforo"));
        timeOpenColumn.setCellValueFactory(new PropertyValueFactory("apertura"));
        timeCloseColumn.setCellValueFactory(new PropertyValueFactory("cierre"));

        ObservableList<RoomVo> roomVoList = FXCollections.observableArrayList(roomArrayList);

        tableShowSalas.setItems(roomVoList);
    }

    private void tableShowMaterial(int idSalaM) throws Exception {

        MaterialDao materialDao = new MaterialDao();

        //ArrayList<MaterialVo> materialArrayList = new ArrayList<>(materialDao.buscar(room.getIdSala()));

        MaterialVo materialVo = materialDao.buscar(idSalaM);

        idMaterialColumn.setCellValueFactory(new PropertyValueFactory("idMaterial"));
        nameMaterialColumn.setCellValueFactory(new PropertyValueFactory("nombreMaterial"));
        timeAltaColumn.setCellValueFactory(new PropertyValueFactory("fechaAlta"));
        timeBajaColumn.setCellValueFactory(new PropertyValueFactory("fechaBaja"));
        idSala.setCellValueFactory(new PropertyValueFactory("idSala"));

        ObservableList<MaterialVo> materialVoList = FXCollections.observableArrayList(materialVo);

        tableShowMaterial.setItems(materialVoList);
    }

    public void addSala() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/addRoom.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 433, 441);
        Stage stage = new Stage();
        stage.setTitle("Add New Room");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteSala() throws Exception {

        if(this.tableShowSalas.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("ELIMINAR");
        }else {
            RoomVo roomVoSelected = this.tableShowSalas.getSelectionModel().getSelectedItem();

            if (areYouSureAlert(roomVoSelected)) {
                RoomDao roomDao = new RoomDao();
                roomDao.eliminar(roomVoSelected);
                this.showRooms();
            }
        }
    }
    public void showMaterial() throws Exception {

        if(this.tableShowSalas.getSelectionModel().getSelectedItem() == null){
            this.errorDeleteAlert("VER MATERIAL");
        }else {
            RoomVo roomVoSelected = this.tableShowSalas.getSelectionModel().getSelectedItem();
            this.tableShowMaterial(roomVoSelected.getIdSala());
        }
    }
    public void addMaterial(){

    }
    public void deleteMaterial(){

    }
    public void reloadInfo() throws Exception {
        this.showRooms();
    }
    public void moreInfo(){

    }
    public void backButtonMethod(){

    }

    private Boolean areYouSureAlert(RoomVo room){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion Eliminar");
        alert.setHeaderText("Eliminar SALA: "+ room.getNombreSala()+".");
        alert.setContentText("Pulse Aceptar para confirmar");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }

    }
    private void errorDeleteAlert(String message){

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Actividad no seleccionada");
        alert2.setHeaderText(null);
        alert2.setContentText("Por favor seleccione una SALA antes de pulsar " + message);
        alert2.showAndWait();
    }

}
