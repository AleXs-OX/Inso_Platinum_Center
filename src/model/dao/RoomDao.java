package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.ActivityVo;
import model.vo.RoomVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends SQL_Controller_Conexion{
    public void anadir(RoomVo sala) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO SALAS"
                    + "(idSala, nombreSala, aforo, apertura, cierre) VALUES (?, ?, ?, ?, ?)");
            st.setInt(1, sala.getIdSala());
            st.setString(2, sala.getNombreSala());
            st.setInt(3, sala.getAforo());
            st.setTime(4, sala.getApertura());
            st.setTime(5, sala.getCierre());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Error anadiendo sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion anadiendo sala: " + e.getMessage());
            }
        }
    }

    public void actualizar(RoomVo sala) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("UPDATE SALAS SET "
                    + "nombreSala=?, aforo=?, apertura=?, cierre=? WHERE idSala=?");

            st.setString(1, sala.getNombreSala());
            st.setInt(2, sala.getAforo());
            st.setTime(3, sala.getApertura());
            st.setTime(4, sala.getCierre());
            st.setInt(5, sala.getIdSala());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Error al actualizar sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion actualizando sala: " + e.getMessage());
            }
        }
    }

    public RoomVo buscar(int idSala) throws Exception {
        RoomVo sala = new RoomVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALAS WHERE "
            		+ "idSala=?");
            st.setInt(1, idSala);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                sala.setIdSala(result.getInt("idSala"));
                sala.setNombreSala(result.getString("nombreSala"));
                sala.setAforo(result.getInt("aforo"));
                sala.setApertura(result.getTime("apertura"));
                sala.setCierre(result.getTime("cierre"));
            }

        }catch (Exception e){
            throw new Exception("Error al buscar sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscar sala: " + e.getMessage());
            }
        }

        return sala;
    }
    
    public List<RoomVo> listar() throws Exception{
        ArrayList<RoomVo> listaSalas = new ArrayList<>();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALAS");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                RoomVo sala = new RoomVo();
                sala.setIdSala(rs.getInt("idSala"));
                sala.setNombreSala(rs.getString("nombreSala"));
                sala.setAforo(rs.getInt("aforo"));
                sala.setApertura(rs.getTime("apertura"));
                sala.setCierre(rs.getTime("cierre"));

                listaSalas.add(sala);
            }
        }catch(Exception e) {
            throw new Exception("Error al listar salas: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion listando salas: " + e.getMessage());
            }
        }

        return listaSalas;
    }

    public List<RoomVo> buscar(String termino) throws Exception{
        ArrayList<RoomVo> listaSalas = new ArrayList<>();
        String criterioBusqueda = '%' + termino + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALAS WHERE "
            		+ "nombreSala LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                RoomVo sala = new RoomVo();
                sala.setIdSala(rs.getInt("idSala"));
                sala.setNombreSala(rs.getString("nombreSala"));
                sala.setAforo(rs.getInt("aforo"));
                sala.setApertura(rs.getTime("apertura"));
                sala.setCierre(rs.getTime("cierre"));

                listaSalas.add(sala);
            }
        }catch(Exception e) {
            throw new Exception("Error al buscar salas: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion buscando salas: " + e.getMessage());
            }
        }

        return listaSalas;
    }
    public void eliminar(RoomVo room) throws Exception{
        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM SALAS WHERE "
                    + "idSala=?");
            st.setInt(1, room.getIdSala());

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
}