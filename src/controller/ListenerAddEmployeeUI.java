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
import java.sql.Date;


public class ListenerAddEmployeeUI extends ListenerClientsUI{

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textField_ID_Cliente;
    @FXML
    private TextField textFieldPass;
    @FXML
    private DatePicker datePicker;
    @FXML
    private DatePicker datePickerContrata;
    @FXML
    private TextField textFieldCIF;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldIban;
    @FXML
    private TextField textFieldSalario;


    /*Buttons*/
    @FXML
    private Button buttonCreateClient;
    @FXML
    private Button buttonCancel;

    private Stage primaryStage;

    @FXML
    private ChoiceBox<String> choiceBoxTarifas;

    /*Variables*/
    private int idUsuario;
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasena;
    private Date fechaNacimiento;
    private Date fechaContratacion;
    private String cif;
    private String email;
    private int telefono;
    private String iban;
    private String direccion;
    private int tipoDeUsuario;
    private int idSalario;
    private int idTarifa;

    public void initialize(){

        this.anadeTarifas();
        datePicker.setEditable(false);
    }

    public void anadeTarifas(){
        ObservableList<String> lista_Tarifas = FXCollections.observableArrayList("Tarifa 1", "Tarifa 2", "Tarifa 3");
        choiceBoxTarifas.setItems(lista_Tarifas);
        this.choiceBoxTarifas.setValue("Seleccionar");

    }
    public void buttonCreateClientMethod(ActionEvent event) throws Exception {

        if(this.compruebaDatos()) {

            this.idUsuario = Integer.parseInt(textField_ID_Cliente.getText());
            this.nombreUsuario = textFieldNombre.getText();
            this.nombreCompleto = nombreUsuario.concat(" ").concat(textFieldApellidos.getText());
            this.contrasena = textFieldPass.getText();
            this.fechaNacimiento = java.sql.Date.valueOf(datePicker.getValue());
            this.fechaContratacion = java.sql.Date.valueOf(datePickerContrata.getValue());
            //this.tipoDeUsuario = tipoDeUsuario;
            this.cif = textFieldCIF.getText();
            this.email = textFieldEmail.getText();
            this.telefono = Integer.parseInt(textFieldTelefono.getText());
            this.iban = textFieldIban.getText();
            this.direccion = textFieldDireccion.getText();
            this.idSalario = Integer.parseInt(textFieldSalario.getText());
            this.idTarifa = Integer.parseInt(choiceBoxTarifas.getValue());

            /* - - Tipos de usuario - -*/
            /* 1 - Administrador */
            /* 2 - Empleado */
            /* 3 - Cliente*/
            /**/

            int tipoDeUsuario = 3; // Tipo usuario cliente.

            /*Crea objeto userDao*/
            UsersDao userDao = new UsersDao();
            UsersVo userVo = new UsersVo(idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaNacimiento,fechaContratacion,
                    tipoDeUsuario,cif,email,telefono,iban,direccion,idSalario,idTarifa);

            /*Registra el usuarioVo creado en userDao para introducirlo en la base de datos*/
            userDao.anadir(userVo);

            this.resetEspaciosBlanco();
            this.mensajeCreacionExitoso();

            /*Recarga la tabla de nuevo para que el usuario creado sea visible*/
            /*Usa extends de ListenerClientsUI*/

            this.closeWindow();
        }

    }
    public void buttonCancelMethod(ActionEvent event) throws Exception {

        this.closeWindow();
    }

    private Boolean compruebaDatos(){
        /*FALTA COMPROBAR QUE EL ID SOLO SEA INT*/

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

    }

    private void mensajeCreacionExitoso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Usuario "+this.nombreCompleto+ " creado con exito!");
        alert.showAndWait();
    }
    private void resetEspaciosBlanco(){
        this.textField_ID_Cliente.setText("");
        this.textFieldNombre.setText("");
        this.textFieldApellidos.setText("");
        this.textFieldPass.setText("");
        this.datePicker.setValue(null);
        this.choiceBoxTarifas.setValue("Seleccionar");

    }
    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.textFieldNombre.getScene().getWindow();
        this.primaryStage.close();

    }

}
