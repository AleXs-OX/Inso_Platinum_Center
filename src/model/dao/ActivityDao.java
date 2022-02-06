package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.ActivityVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao extends SQL_Controller_Conexion{

    public void anadir(ActivityVo actividad) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO ACTIVIDADES"
                    + "(nombreActividad, fecha, idEmpleado, idSala, descripcion, duracion, idRutina) VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, actividad.getNombreActividad());
            st.setTimestamp(2, actividad.getFecha());
            st.setInt(3, actividad.getIdEmpleado());
            st.setInt(4, actividad.getIdSala());
            st.setString(5, actividad.getDescripcion());
            st.setTime(6, actividad.getDuracion());
            st.setInt(7,actividad.getIdRutina());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Error anadiendo sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion anadiendo actividad: " + e.getMessage());
            }
        }
    }
    
    public void eliminar(ActivityVo actividad) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM ACTIVIDADES WHERE "
    				+ "nombreActividad=?");
    		st.setString(1, actividad.getNombreActividad());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error eliminando actividad: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexión eliminando actividad: " + e.getMessage());
    		}
    	}
    }

    public void actualizar(ActivityVo actividad) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("UPDATE ACTIVIDADES SET "
                    + "fecha=?, idSala=?, descripcion=?, duracion=?, idRutina=? WHERE nombreActividad=?");

            st.setTimestamp(1, actividad.getFecha());
            st.setInt(2, actividad.getIdSala());
            st.setString(3, actividad.getDescripcion());
            st.setTime(4, actividad.getDuracion());
            st.setInt(5, actividad.getIdRutina());
            st.setString(6, actividad.getNombreActividad());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Error actualizando actividad: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion actualizando actividad: " + e.getMessage());
            }
        }
    }

    public List<ActivityVo> listar() throws Exception{
        ArrayList<ActivityVo> listaActividades = new ArrayList<>();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ACTIVIDADES");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                ActivityVo actividad = new ActivityVo();
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setFecha(rs.getTimestamp("fecha"));
                actividad.setIdEmpleado(rs.getInt("idEmpleado"));
                actividad.setIdSala(rs.getInt("idSala"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setDuracion(rs.getTime("duracion"));
                actividad.setIdRutina(rs.getInt("idRutina"));

                listaActividades.add(actividad);
            }
        }catch(Exception e) {
            throw new Exception("Error listando actividades: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion listando actividades: " + e.getMessage());
            }
        }

        return listaActividades;
    }
    
    public List<ActivityVo> buscar(String termino) throws Exception{
        ArrayList<ActivityVo> listaActividades = new ArrayList<>();
        String criterioBusqueda = '%' + termino + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM ACTIVIDADES WHERE "
            		+ "nombreActividad LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                ActivityVo actividad = new ActivityVo();
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setFecha(rs.getTimestamp("fecha"));
                actividad.setIdEmpleado(rs.getInt("idEmpleado"));
                actividad.setIdSala(rs.getInt("idSala"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setDuracion(rs.getTime("duracion"));
                actividad.setIdRutina(rs.getInt("idRutina"));

                listaActividades.add(actividad);
            }
        }catch(Exception e) {
            throw new Exception("Error buscando actividades: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion buscando actividades: " + e.getMessage());
            }
        }

        return listaActividades;
    }
}