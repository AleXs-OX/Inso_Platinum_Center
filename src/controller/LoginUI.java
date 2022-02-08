package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.UsersDao;
import model.vo.UsersVo;

public class LoginUI {

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField textFieldPass;

    @FXML
    private TextField textFiledUser;

    private Stage primaryStage;
    //FXMLLoader newRoot;

    //@FXML
    //void buttonLoginPressed(ActionEvent event) {

    //}

    public void initialize(){
        this.buttonLogin.setOnAction(e -> {
            try {
                this.entrar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void entrar() throws Exception {	
    	if(textFiledUser.getText() == null || textFieldPass.getText() == null || textFiledUser.getText() == "" || textFieldPass.getText() == "" ) {
			error("Alguno de los campos está vacío. Rellénalos.");
		}else {
			try {
				UsersDao dao = new UsersDao();
				UsersVo vo = dao.buscar(textFiledUser.getText(), textFieldPass.getText());
				
				if(vo.getTipoDeUsuario() == 2 && vo.getNombreUsuario() != null){
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Administrator.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Platinum Center - Panel de administración");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					
					ListenerProfileAdmin controller = loader.getController();
					controller.setTextoUsuario(vo.getNombreUsuario());
					stage.show();
					
					primaryStage = (Stage) this.buttonLogin.getScene().getWindow();
					primaryStage.close();
				}else if(vo.getTipoDeUsuario() == 1 && vo.getNombreUsuario() != null){
					//TODO
					System.out.println("Por implementar: empleado");
				}else if(vo.getTipoDeUsuario() == 0 && vo.getNombreUsuario() != null){
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Client.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Platinum Center - Home");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					
					ListenerClientsUI controller = loader.getController();
					controller.setTextoUsuario(vo.getNombreUsuario());
					stage.show();
					
					primaryStage = (Stage) this.buttonLogin.getScene().getWindow();
					primaryStage.close();
				}else {
					error("Usuario y/o contraseña incorrectos. Revisa los campos.");
				}
				
			}catch(Exception e) {
				error(e.getMessage());
			}	
		}
    }
    
    private void error(String texto) {
    	Alert alert = new Alert(Alert.AlertType.WARNING);
    	
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
}