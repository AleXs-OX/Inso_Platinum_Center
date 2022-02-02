package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.UsersVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends SQL_Controller_Conexion {

    public List<UsersVo> listar() throws Exception{

        ArrayList<UsersVo> listaUsuarios = new ArrayList<UsersVo>();
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * from usuarios");
            ResultSet result = st.executeQuery();

            while (result.next()){
                UsersVo user = new UsersVo();
                user.setIdUsuario(result.getInt("idUsuario"));
                user.setNombreCompleto(result.getString("nombreCompleto"));
                user.setNombreUsuario(result.getString("nombreUsuario"));
                user.setContrasena(result.getString("contrasena"));
                user.setFechaNacimiento(result.getDate("fechaNacimiento"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                listaUsuarios.add(user);
            }
        }
        catch(Exception e){
            throw new Exception("Listar Usuarios "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listar usuarios: " + e.getMessage());
            }
        }
        return listaUsuarios;
    }

    public UsersVo buscar(int idUser) throws Exception {

        UsersVo user = new UsersVo();
        String criterioBusqueda = '%' + Integer.toString(idUser) + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM usuarios WHERE idUsuario "
                    + "LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                user.setIdUsuario(result.getInt("idUsuario"));
                user.setNombreCompleto(result.getString("nombreCompleto"));
                user.setNombreUsuario(result.getString("nombreUsuario"));
                user.setContrasena(result.getString("contrasena"));
                user.setFechaNacimiento(result.getDate("fechaNacimiento"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
            }

        }catch (Exception e){
            throw new Exception("Error al buscar usuario: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscar usuario: " + e.getMessage());
            }
        }

        return user;
    }

    public void eliminar(UsersVo user) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM usuarios WHERE IdUsuario=?");
            st.setInt(1, user.getIdUsuario());
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Eliminar Usuario "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion eliminar usuario: " + e.getMessage());
            }
        }
    }

    public void registrar(UsersVo user) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO usuarios (idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaNacimiento, tipoDeUsuario)" +
                    "VALUES (?,?,?,?,?,?)");

            st.setInt(1, user.getIdUsuario());
            st.setString(2, user.getNombreCompleto());
            st.setString(3, user.getNombreUsuario());
            st.setString(3, user.getContrasena());
            st.setDate(4, user.getFechaNacimiento());
            st.setInt(5, user.getTipoDeUsuario());
        }
        catch(Exception e){
            throw new Exception("Registrar Usuario "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrar usuario: " + e.getMessage());
            }
        }
    }
}
