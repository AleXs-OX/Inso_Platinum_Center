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
                user.setFechaContratacion(result.getDate("fechaAlta"));
                user.setCIF(result.getString("CIF"));
                user.setEmail(result.getString("email"));
                user.setTelefono(result.getInt("telefono"));
                user.setIBAN(result.getString("IBAN"));
                user.setDireccion(result.getString("direccion"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                user.setIdSalario(result.getInt("idSalario"));
                user.setIdTarifa(result.getInt("idTarifa"));
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
    
    public List<UsersVo> listarPorTipo(int tipoUsuario) throws Exception{
        ArrayList<UsersVo> listaUsuarios = new ArrayList<UsersVo>();
        
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM USUARIOS"
            		+ "WHERE tipoUsuario=?");
            st.setInt(1, tipoUsuario);
            ResultSet result = st.executeQuery();

            while (result.next()){
                UsersVo user = new UsersVo();
                user.setIdUsuario(result.getInt("idUsuario"));
                user.setNombreCompleto(result.getString("nombreCompleto"));
                user.setNombreUsuario(result.getString("nombreUsuario"));
                user.setContrasena(result.getString("contrasena"));
                user.setFechaNacimiento(result.getDate("fechaNacimiento"));
                user.setFechaContratacion(result.getDate("fechaAlta"));
                user.setCIF(result.getString("CIF"));
                user.setEmail(result.getString("email"));
                user.setTelefono(result.getInt("telefono"));
                user.setIBAN(result.getString("IBAN"));
                user.setDireccion(result.getString("direccion"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                user.setIdSalario(result.getInt("idSalario"));
                user.setIdTarifa(result.getInt("idTarifa"));
                listaUsuarios.add(user);
            }
        }
        catch(Exception e){
            throw new Exception("Error listando usuarios por tipo: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listando usuarios por tipo: " + e.getMessage());
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
                user.setFechaContratacion(result.getDate("fechaAlta"));
                user.setCIF(result.getString("CIF"));
                user.setEmail(result.getString("email"));
                user.setTelefono(result.getInt("telefono"));
                user.setIBAN(result.getString("IBAN"));
                user.setDireccion(result.getString("direccion"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                user.setIdSalario(result.getInt("idSalario"));
                user.setIdTarifa(result.getInt("idTarifa"));
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
    
    public UsersVo buscar(String usuario, String pass) throws Exception {
        UsersVo user = new UsersVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM USUARIOS "
            		+ "WHERE nombreUsuario=? AND contrasena=?");
            st.setString(1, usuario);
            st.setString(2, pass);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                user.setIdUsuario(result.getInt("idUsuario"));
                user.setNombreCompleto(result.getString("nombreCompleto"));
                user.setNombreUsuario(result.getString("nombreUsuario"));
                user.setContrasena(result.getString("contrasena"));
                user.setFechaNacimiento(result.getDate("fechaNacimiento"));
                user.setFechaContratacion(result.getDate("fechaAlta"));
                user.setCIF(result.getString("CIF"));
                user.setEmail(result.getString("email"));
                user.setTelefono(result.getInt("telefono"));
                user.setIBAN(result.getString("IBAN"));
                user.setDireccion(result.getString("direccion"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                user.setIdSalario(result.getInt("idSalario"));
                user.setIdTarifa(result.getInt("idTarifa"));
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
                user.setFechaContratacion(result.getDate("fechaAlta"));
                user.setCIF(result.getString("CIF"));
                user.setEmail(result.getString("email"));
                user.setTelefono(result.getInt("telefono"));
                user.setIBAN(result.getString("IBAN"));
                user.setDireccion(result.getString("direccion"));
                user.setTipoDeUsuario(result.getInt("tipoUsuario"));
                user.setIdSalario(result.getInt("idSalario"));
                user.setIdTarifa(result.getInt("idTarifa"));
                
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

    public void eliminar(UsersVo user) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM USUARIOS WHERE idUsuario=?");
            st.setInt(1, user.getIdUsuario());
            
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Error eliminando usuarios: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion eliminando usuario: " + e.getMessage());
            }
        }
    }

    public void anadir(UsersVo user) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO usuarios "
            		+ "(idUsuario, nombreCompleto, nombreUsuario, contrasena, fechaNacimiento, fechaAlta, CIF, email, telefono, IBAN, direccion, tipoDeUsuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setInt(1, user.getIdUsuario());
            st.setString(2, user.getNombreCompleto());
            st.setString(3, user.getNombreUsuario());
            st.setString(4, user.getContrasena());
            st.setDate(5, user.getFechaNacimiento());
            st.setDate(6, user.getFechaContratacion());
            st.setString(7, user.getCIF());
            st.setString(8, user.getEmail());
            st.setInt(9, user.getTelefono());
            st.setString(10, user.getIBAN());
            st.setString(11, user.getDireccion());
            st.setInt(12, user.getTipoDeUsuario());

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
    				+ "contrasena=?, email=?, telefono=?, IBAN=?, direccion=? WHERE idUsuario=?");
    		st.setString(1, usuario.getContrasena());
    		st.setString(2, usuario.getEmail());
    		st.setInt(3, usuario.getTelefono());
    		st.setString(4, usuario.getIBAN());
    		st.setString(5, usuario.getDireccion());
    		st.setInt(6, usuario.getIdUsuario());
    		
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
    
    public void actualizarSalario(UsersVo usuario) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE USUARIOS SET "
    				+ "idSalario=? WHERE idUsuario=?");
    		st.setInt(1, usuario.getIdSalario());
    		st.setInt(2, usuario.getIdUsuario());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error al actualizar salario de usuario: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando salario de usuario: " + e.getMessage());
    		}
    	}
    }
    
    public void actualizarTarifa(UsersVo usuario) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE USUARIOS SET "
    				+ "idTarifa=? WHERE idUsuario=?");
    		st.setInt(1, usuario.getIdTarifa());
    		st.setInt(2, usuario.getIdUsuario());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error al actualizar tarifa de usuario: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando tarifa de usuario: " + e.getMessage());
    		}
    	}
    }
}