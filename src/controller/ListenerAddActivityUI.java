package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.ActivityDao;
import model.dao.UsersDao;
import model.vo.ActivityVo;
import model.vo.UsersVo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ListenerAddActivityUI {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textField_ID_Empleado;
    @FXML
    private TextField textFieldSala;
    @FXML
    private TextField textFieldIdRutina;
    @FXML
    private TextField textFieldDuracion;
    @FXML
    private TextField textFieldDescripcion;

    @FXML
    private TextField textFieldFechaCreacion;

    /*Buttons*/
    @FXML
    private Button buttonCreateActivity;
    @FXML
    private Button buttonCancel;

    private Stage primaryStage;

    /*Variables*/
    private String activity_name;
    private String descripcion;
    private Timestamp fecha;
    private Time duracion;

    private int idEmpleado;
    private int idSala;
    private int idRutina;

    Timestamp timestamp;


    public void initialize(){

        this.timestamp = new Timestamp(System.currentTimeMillis());
        textFieldFechaCreacion.setText(this.timestamp.toString());

    }

    public void buttonCreateActivityMethod() throws Exception {
        /*Comprobar que la hora sea como maximo del 0 a 9*/
        String horaDefault = ":00:00";
        String horaFinal = "0";
        horaFinal = horaFinal.concat(textFieldDuracion.getText().concat(horaDefault));

        if(this.compruebaDatos()) {

        this.activity_name = this.textFieldNombre.getText();
        this.descripcion = this.textFieldDescripcion.getText();
        this.fecha = this.timestamp;
        this.duracion = Time.valueOf(horaFinal);
        this.idEmpleado = Integer.parseInt(textField_ID_Empleado.getText());
        this.idSala = Integer.parseInt(textFieldSala.getText());
        this.idRutina = Integer.parseInt(textFieldIdRutina.getText());

        /* - - Tipos de usuario - -*/
        /* 2 - Administrador */
        /* 1 - Empleado */
        /* 0 - Cliente*/
        /**/

        ActivityDao activityDao = new ActivityDao();
        ActivityVo activityVo = new ActivityVo(activity_name,fecha,idEmpleado,idSala,idRutina,descripcion,duracion);

        activityDao.anadir(activityVo);


        /*Registra el usuarioVo creado en userDao para introducirlo en la base de datos*/

       // this.resetEspaciosBlanco();
        this.mensajeCreacionExitoso();

        /*Recarga la tabla de nuevo para que el usuario creado sea visible*/
        this.closeWindow();

        }
    }

    public void buttonCancelMethod(ActionEvent event) throws Exception {

        this.closeWindow();
    }

    private void closeWindow() throws Exception {
        this.primaryStage = (Stage) this.textFieldNombre.getScene().getWindow();
        this.primaryStage.close();
    }

    private void mensajeCreacionExitoso(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Actividad "+this.activity_name+ " creada con exito!");
        alert.showAndWait();

    }
    private Boolean compruebaDatos(){
        /*FALTA COMPROBAR QUE EL ID SOLO SEA INT*/

        if(this.textFieldNombre.getText() == ""|| this.textFieldSala.getText() == ""|| this.textFieldIdRutina.getText() == ""
                || this.textFieldDescripcion.getText() == ""|| this.textFieldDuracion.getText() == "" || this.textField_ID_Empleado.getText() == ""
                || this.textFieldFechaCreacion.getText() == ""){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Algun campo introducido es incorrecto o esta vacio\n");
            alert.showAndWait();
            return false;

        }else{

            if(Integer.parseInt(this.textFieldDuracion.getText()) > 9 || Integer.parseInt(this.textFieldDuracion.getText()) == 0){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos incorrectos");
                alert.setHeaderText(null);
                alert.setContentText("La duracion de la actividad debe ser entre 1 hora y 9 horas.\n");
                alert.showAndWait();
                return false;
            }
        }
        return true;

    }

}
