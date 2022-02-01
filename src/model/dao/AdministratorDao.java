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

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * from admins");
            ResultSet result = st.executeQuery();

            while (result.next()){
                AdministratorVo admin = new AdministratorVo();
                admin.setIdAdmin(result.getInt("idAdmin"));
                admin.setIdSalario(result.getInt("idSalario"));

                listaAdmins.add(admin);
            }
        }
        catch(Exception e){
            throw new Exception("Listar Admins "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listar admins: " + e.getMessage());
            }
        }
        return listaAdmins;
    }

    public AdministratorVo buscar(int idAdmin) throws Exception {

        AdministratorVo admin = new AdministratorVo();
        String criterioBusqueda = '%' + Integer.toString(idAdmin) + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM admins WHERE idAdmin "
                    + "LIKE ?");
            st.setString(1, criterioBusqueda);
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
                throw new Exception("Error al cerrar la conexion buscar admin: " + e.getMessage());
            }
        }

        return admin;
    }

    public void eliminar(AdministratorVo admin) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM admins WHERE idAdmin=?");
            st.setInt(1, admin.getIdAdmin());
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Eliminar Admin "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion eliminar admin: " + e.getMessage());
            }
        }
    }

    public void registrar(AdministratorVo admin) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO admins (idAdmin, idSalario)" +
                    "VALUES (?,?)");
            st.setInt(1, admin.getIdAdmin());
            st.setInt(2, admin.getIdSalario());
        }
        catch(Exception e){
            throw new Exception("Registrar Admin "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrar admin: " + e.getMessage());
            }
        }
    }
}
