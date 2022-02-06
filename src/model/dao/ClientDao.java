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

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM CLIENTES");
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
            throw new Exception("Error listando clientes " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listando clientes: " + e.getMessage());
            }
        }
        
        return listaClientes;
    }

    public ClientVo buscar(int idCliente) throws Exception {
        ClientVo client = new ClientVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM CLIENTES"
            		+ " WHERE idCliente=?");
            st.setInt(1, idCliente);
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
                throw new Exception("Error al cerrar la conexion buscando cliente: " + e.getMessage());
            }
        }

        return client;
    }

    public void anadir(ClientVo cliente) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO CLIENTES"
            		+ "(idCliente, fechaAlta, tarifaContratada) VALUES (?,?,?)");
            st.setInt(1, cliente.getIdCliente());
            st.setDate(2, cliente.getFechaAlta());
            st.setInt(3, cliente.getTarifaContratada());
            
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Error registrando cliente: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrando cliente: " + e.getMessage());
            }
        }
    }
    
    public void actualizarBaja(ClientVo cliente) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE CLIENTES SET "
    				+ "fechaBaja=? WHERE idCliente=?");
    		st.setDate(1, cliente.getFechaBaja());
    		st.setInt(2, cliente.getIdCliente());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error actualizando baja cliente: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando baja cliente: " + e.getMessage());
    		}
    	}
    }
    
    public void actualizarTarifa(ClientVo cliente) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE CLIENTES SET "
    				+ "tarifaContratado=? WHERE idCliente=?");
    		st.setInt(1, cliente.getTarifaContratada());
    		st.setInt(2, cliente.getIdCliente());
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error actualizando tarifa cliente: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando tarifa cliente: " + e.getMessage());
    		}
    	}
    }
}