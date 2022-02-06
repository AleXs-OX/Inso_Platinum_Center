package model;

import model.dao.UsersDao;
import model.vo.UsersVo;

public class loginLogic {
	
	private static loginLogic instancia;
	private int idUsuario;
	
	private loginLogic() {}
	
	public int iniciarSesion(String usuario, String password) throws Exception{
		int tipoUsuario = -1;
		
		if(usuario == null || password == null || usuario == "" || password == "" ) {
			throw new Exception("Alguno de los campos está vacío. Rellénalos.");
		}else {
			try {
				UsersVo vo = new UsersVo();
				UsersDao dao = new UsersDao();
				vo = dao.buscar(usuario, password);
				
				setIdUsuario(vo.getIdUsuario());
				tipoUsuario = vo.getTipoDeUsuario();
			}catch(Exception e) {
				throw new Exception("Usuario y/o contraseña incorrectos. Error: " + e.getMessage());
			}
		}
		
		return tipoUsuario;
	}
	
	public static loginLogic getInstancia() {
		if(instancia == null) {
			instancia = new loginLogic();
		}
		
		return instancia;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void logout() {
		this.idUsuario = -1;
	}
	
	private void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}