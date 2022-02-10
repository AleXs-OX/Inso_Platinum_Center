package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    	primaryStage = (Stage) this.buttonLogin.getScene().getWindow();
    	
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
					controller.setUsuario(vo);
					stage.show();
					
					primaryStage.close();
				}else if(vo.getTipoDeUsuario() == 1 && vo.getNombreUsuario() != null){

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Employee.fxml"));

					Stage stage = new Stage();
					stage.setTitle("Platinum Center - Panel de Empleado");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));

					ListenerEmployeesUI controller = loader.getController();
					controller.setUsuario(vo);
					stage.show();

					primaryStage.close();

				}else if(vo.getTipoDeUsuario() == 0 && vo.getNombreUsuario() != null){
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Client.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Platinum Center - Home");
					stage.setResizable(false);
					stage.setScene(new Scene(loader.load()));
					
					ListenerClientsUI controller = loader.getController();
					controller.setUsuario(vo);
					stage.show();
					
					primaryStage.close();
				}else {
					error("Usuario y/o contraseña incorrectos");
				}
				
			}catch(Exception e) {
				error(e.getMessage());
			}	
		}
    }
    
    private void error(String texto) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Error");
			stage.setResizable(false);
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage); 
			
			ListenerError controller = loader.getController();
			controller.setMensaje(texto);
			stage.showAndWait();	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
    }
}