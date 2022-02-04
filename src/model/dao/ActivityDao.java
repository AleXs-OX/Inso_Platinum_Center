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
                    + "(nombreActividad, fecha, idEmpleado, idSala, descripcion, duracion) VALUES (?, ?, ?, ?, ?, ?)");
            st.setString(1, actividad.getNombreActividad());
            st.setDate(2, actividad.getFecha());
            st.setInt(3, actividad.getIdEmpleado());
            st.setInt(4, actividad.getIdSala());
            st.setString(5, actividad.getDescripcion());
            st.setTime(6, actividad.getDuracion());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Anadiendo sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion anadiendo actividad: " + e.getMessage());
            }
        }
    }

    public void actualizar(ActivityVo actividad) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("UPDATE ACTIVIDADES SET "
                    + "nombreActividad=?, fecha=?, idSala=?, descripcion=?, duracion=?");

            st.setString(1, actividad.getNombreActividad());
            st.setDate(2, actividad.getFecha());
            st.setInt(3, actividad.getIdSala());
            st.setString(5, actividad.getDescripcion());
            st.setTime(6, actividad.getDuracion());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Actualizar Actividad: " + e.getMessage());
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
                actividad.setFecha(rs.getDate("fecha"));
                actividad.setIdEmpleado(rs.getInt("idEmpleado"));
                actividad.setIdSala(rs.getInt("idSala"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setDuracion(rs.getTime("duracion"));

                listaActividades.add(actividad);
            }
        }catch(Exception e) {
            throw new Exception("Listar Actividades: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion listando actividades: " + e.getMessage());
            }
        }

        return listaActividades;
    }
}
