package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.UsersVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends SQL_Controller_Conexion {

    public UsersDao() {
        new SQL_Controller_Conexion();
    }

    public List<UsersVo> listar() throws Exception{
        ArrayList<UsersVo> listaUsuarios = new ArrayList<UsersVo>();
        try{
            PreparedStatement statement = this.getConnection().prepareStatement("SELECT * from usuarios");
            ResultSet result = statement.executeQuery();

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
        }
        return listaUsuarios;
    }

    public void eliminar(UsersVo user) throws Exception{
        try{
            PreparedStatement statement = this.getConnection().prepareStatement("DELETE FROM usuarios WHERE IdUsuario=?");
            statement.setInt(1, user.getIdUsuario());
            statement.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Eliminar Usuarios "+e.getMessage());
        }
    }

    public void registrar(UsersVo user) throws Exception{
        try{
            PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO usuarios (idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaNacimiento, tipoDeUsuario)" +
                    "VALUES (?,?,?,?,?,?)");
            statement.setInt(1, user.getIdUsuario());
            statement.setString(2, user.getNombreCompleto());
            statement.setString(3, user.getNombreUsuario());
            statement.setString(3, user.getContrasena());
            statement.setDate(4, user.getFechaNacimiento());
            statement.setInt(5, user.getTipoDeUsuario());
        }
        catch(Exception e){
            throw new Exception("Eliminar Usuarios "+e.getMessage());
        }
    }
}
