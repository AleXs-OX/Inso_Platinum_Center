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

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM USUARIOS");
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
            throw new Exception("Error listando usuarios: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listando usuarios: " + e.getMessage());
            }
        }
        
        return listaUsuarios;
    }

    public UsersVo buscar(int idUser) throws Exception {
        UsersVo user = new UsersVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM USUARIOS "
            		+ "WHERE idUsuario=?");
            st.setInt(1, idUser);
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
                throw new Exception("Error al cerrar la conexion buscando usuario: " + e.getMessage());
            }
        }

        return user;
    }
    
    public List<UsersVo> buscar(String termino) throws Exception {
    	ArrayList<UsersVo> listaUsuarios = new ArrayList<UsersVo>();
        String criterioBusqueda = '%' + termino + '%';
        
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM USUARIOS "
            		+ "WHERE nombreCompleto LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet result = st.executeQuery();

            while(result.next()) {
            	UsersVo user = new UsersVo();
                user.setIdUsuario(result.getInt("idUsuario"));
                user.setNombreCompleto(result.getString("nombreCompleto"));
                user.setNombreUsuario(result.getString("nombreUsuario"));
                user.setContrasena(result.getString("contrasena"));
                user.setFechaNacimiento(result.getDate("fechaNacimiento"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                
                listaUsuarios.add(user);
            }

        }catch (Exception e){
            throw new Exception("Error al buscar usuario: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscando usuario: " + e.getMessage());
            }
        }

        return listaUsuarios;
    }

    //TODO PLANTEARSE SI SE PUEDEN ELIMINAR USUARIOS (fechaBaja, fechaDespido, etc)
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

    public void anadir(UsersVo user) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO usuarios (idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaNacimiento, tipoUsuario)" +
                    "VALUES (?,?,?,?,?,?)");

            st.setInt(1, user.getIdUsuario());
            st.setString(2, user.getNombreCompleto());
            st.setString(3, user.getNombreUsuario());
            st.setString(4, user.getContrasena());
            st.setDate(5, user.getFechaNacimiento());
            st.setInt(6, user.getTipoDeUsuario());

            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Error al registrar usuario: [Codigo de error -> " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrando usuario: [Codigo de error -> " + e.getMessage());
            }
        }
    }
    
    public void actualizar(UsersVo usuario) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE USUARIOS SET "
    				+ "contrasena=? WHERE idUsuario=?");
    		st.setString(1, usuario.getContrasena());
    		st.setInt(2, usuario.getIdUsuario());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error al actualizar usuario: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando usuario: " + e.getMessage());
    		}
    	}
    }
}