package model.dao;

import model.connection.SQL_Controller_Conexion;
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
            throw new Exception("Anadiendo sala: " + e.getMessage());
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
                    + "nombreSala=?, aforo=?, apertura=?, cierre=?");

            st.setString(1, sala.getNombreSala());
            st.setInt(2, sala.getAforo());
            st.setTime(3, sala.getApertura());
            st.setTime(5, sala.getCierre());

            st.executeUpdate();
        }catch(Exception e) {
            throw new Exception("Actualizar Sala: " + e.getMessage());
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
        String criterioBusqueda = '%' + Integer.toString(idSala) + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM SALAS WHERE idSala "
                    + "LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                sala.setIdSala(result.getInt("idSala"));
                sala.setNombreSala(result.getString("nombreSala"));
                sala.setAforo(result.getInt("aforo"));
                sala.setApertura(result.getTime("apertura"));
                sala.setCierre(result.getTime("cierre"));
            }

        }catch (Exception e){
            throw new Exception("Buscar Sala: " + e.getMessage());
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
            throw new Exception("Listar Sala: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e){
                throw new Exception("Error al cerrar la conexion listando salas: " + e.getMessage());
            }
        }

        return listaSalas;
    }
}
