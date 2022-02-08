package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.ActivityDao;
import model.dao.MaterialDao;
import model.dao.RoomDao;
import model.vo.ActivityVo;
import model.vo.MaterialVo;
import model.vo.RoomVo;
import model.vo.UsersVo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ListenerSalaYMaterial {

    @FXML
    private Button buttonAddSala;
    @FXML
    private Button buttonAddMaterial;

    @FXML
    private Button buttonReload;
    @FXML
    private Button backButton;

    @FXML
    private Button buttonDeleteSala;
    @FXML
    private Button buttonDeleteMaterial;



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
        this.tableShowMaterial();
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

    private void tableShowMaterial() throws Exception {

        MaterialDao materialDao = new MaterialDao();
        ArrayList<MaterialVo> materialArrayList = new ArrayList<>(materialDao.listar());

        idMaterialColumn.setCellValueFactory(new PropertyValueFactory("idMaterial"));
        nameMaterialColumn.setCellValueFactory(new PropertyValueFactory("nombreMaterial"));
        timeAltaColumn.setCellValueFactory(new PropertyValueFactory("fechaAlta"));
        timeBajaColumn.setCellValueFactory(new PropertyValueFactory("fechaBaja"));
        idSala.setCellValueFactory(new PropertyValueFactory("idSala"));

        ObservableList<MaterialVo> materialVoList = FXCollections.observableArrayList(materialArrayList);

        tableShowMaterial.setItems(materialVoList);
    }

}
