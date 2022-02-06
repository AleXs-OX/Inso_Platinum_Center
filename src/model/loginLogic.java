package model;

import model.dao.UsersDao;
import model.vo.UsersVo;

public class loginLogic {
	
	public loginLogic() {
		super();
	}
	
	public UsersVo iniciarSesion(String usuario, String password) throws Exception{
		UsersVo vo = new UsersVo();
		
		if(usuario == null || password == null || usuario == "" || password == "" ) {
			throw new Exception("Alguno de los campos est� vac�o. Rell�nalos.");
		}else {
			try {
				UsersDao dao = new UsersDao();
				vo = dao.buscar(usuario, password);
			}catch(Exception e) {
				throw new Exception("Usuario y/o contrase�a incorrectos. Error: " + e.getMessage());
			}
		}
		
		return vo;
	}
}