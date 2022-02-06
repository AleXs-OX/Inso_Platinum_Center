package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.AdministratorVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao extends SQL_Controller_Conexion{

    public List<AdministratorVo> listar() throws Exception{
        ArrayList<AdministratorVo> listaAdmins = new ArrayList<AdministratorVo>();
        
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ADMINS");
            ResultSet result = st.executeQuery();

            while (result.next()){
                AdministratorVo admin = new AdministratorVo();
                admin.setIdAdmin(result.getInt("idAdmin"));
                admin.setIdSalario(result.getInt("idSalario"));

                listaAdmins.add(admin);
            }
        }
        catch(Exception e){
            throw new Exception("Error listando admins: "+ e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listando admins: " + e.getMessage());
            }
        }
        
        return listaAdmins;
    }

    public AdministratorVo buscar(int idAdmin) throws Exception {
        AdministratorVo admin = new AdministratorVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM admins WHERE idAdmin "
                    + "LIKE ?");
            st.setInt(1, idAdmin);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                admin.setIdAdmin(result.getInt("idAdmin"));
                admin.setIdSalario(result.getInt("idSalario"));
            }

        }catch (Exception e){
            throw new Exception("Error al buscar admin: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscando admin: " + e.getMessage());
            }
        }

        return admin;
    }
}
