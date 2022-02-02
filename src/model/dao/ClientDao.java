package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.ClientVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends SQL_Controller_Conexion {

    public List<ClientVo> listar() throws Exception{

        ArrayList<ClientVo> listaClientes = new ArrayList<ClientVo>();
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * from clientes");
            ResultSet result = st.executeQuery();

            while (result.next()){
                ClientVo client = new ClientVo();
                client.setIdCliente(result.getInt("idCliente"));
                client.setFechaAlta(result.getDate("fechaAlta"));
                client.setFechaBaja(result.getDate("fechaBaja"));
                client.setTarifaContratada(result.getInt("tarifaContratada"));

                listaClientes.add(client);
            }
        }
        catch(Exception e){
            throw new Exception("Listar Clientes "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listar clientes: " + e.getMessage());
            }
        }
        return listaClientes;
    }

    public void eliminar(ClientVo cliente) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM clientes WHERE idCliente=?");
            st.setInt(1, cliente.getIdCliente());
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Eliminar Cliente "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion eliminar cliente: " + e.getMessage());
            }
        }
    }

    public ClientVo buscar(int idCliente) throws Exception {

        ClientVo client = new ClientVo();
        String criterioBusqueda = '%' + Integer.toString(idCliente) + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM clientes WHERE idCliente "
                    + "LIKE ?");
            st.setString(1, criterioBusqueda);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                client.setIdCliente(result.getInt("idCliente"));
                client.setFechaAlta(result.getDate("fechaAlta"));
                client.setFechaBaja(result.getDate("fechaBaja"));
                client.setTarifaContratada(result.getInt("tarifaContratada"));
            }

        }catch (Exception e){
            throw new Exception("Error al buscar cliente: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscar cliente: " + e.getMessage());
            }
        }

        return client;
    }

    public void registrar(ClientVo cliente) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO clientes (idCliente, fechaAlta, fechaBaja, tarifaContratada)" +
                    "VALUES (?,?,?,?)");
            st.setInt(1, cliente.getIdCliente());
            st.setDate(2, cliente.getFechaAlta());
            st.setDate(3, cliente.getFechaBaja());
            st.setInt(4, cliente.getTarifaContratada());
        }
        catch(Exception e){
            throw new Exception("Registrar Cliente "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrar cliente: " + e.getMessage());
            }
        }
    }
}
