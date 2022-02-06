package model.dao;

import model.connection.SQL_Controller_Conexion;
import model.vo.EmployeeVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends SQL_Controller_Conexion{

    public List<EmployeeVo> listar() throws Exception{
        ArrayList<EmployeeVo> listaEmpleados = new ArrayList<EmployeeVo>();
        
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM EMPLEADOS");
            ResultSet result = st.executeQuery();

            while (result.next()){
                EmployeeVo empleado = new EmployeeVo();
                empleado.setIdEmpleado(result.getInt("idEmpleado"));
                empleado.setFechaContratacion(result.getDate("fechaContratacion"));
                empleado.setFechaDespido(result.getDate("fechaDespido"));
                empleado.setIdSalario(result.getInt("idSalario"));

                listaEmpleados.add(empleado);
            }
        }
        catch(Exception e){
            throw new Exception("Error listando empleados: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listando empleados: " + e.getMessage());
            }
        }
        
        return listaEmpleados;
    }

    public EmployeeVo buscar(int idEmpleado) throws Exception {
        EmployeeVo empleado = new EmployeeVo();

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM EMPLEADOS "
            		+ "WHERE idEmpleado=?");
            st.setInt(1, idEmpleado);
            ResultSet result = st.executeQuery();

            while(result.next()) {
                empleado.setIdEmpleado(result.getInt("idEmpleado"));
                empleado.setFechaContratacion(result.getDate("fechaContratacion"));
                empleado.setFechaDespido(result.getDate("fechaDespido"));
                empleado.setIdSalario(result.getInt("idSalario"));
            }
        }catch (Exception e){
            throw new Exception("Error al buscar empleado: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            }catch(Exception e) {
                throw new Exception("Error al cerrar la conexion buscando empleado: " + e.getMessage());
            }
        }

        return empleado;
    }

    public void anadir(EmployeeVo empleado) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO empleados"
            		+ "(idEmpleado, fechaContratacion, idSalario) VALUES (?,?,?)");
            st.setInt(1, empleado.getIdEmpleado());
            st.setDate(2, empleado.getFechaContratacion());
            st.setInt(3, empleado.getIdSalario());
            
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Error registrando empleado: " + e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrando empleado: " + e.getMessage());
            }
        }
    }
    
    public void actualizarSalario(EmployeeVo empleado) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE EMPLEADOS SET "
    				+ "idSalario=? WHERE idEmpleado=?");
    		st.setInt(1, empleado.getIdSalario());
    		st.setInt(2, empleado.getIdEmpleado());    		
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error al actualizar salario empleado: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando salario empleado: " + e.getMessage());
    		}
    	}
    }
    
    public void actualizarDespido(EmployeeVo empleado) throws Exception{
    	try {
    		this.openConnection();
    		
    		PreparedStatement st = this.getConnection().prepareStatement("UPDATE EMPLEADOS SET "
    				+ "fechaDespido=? WHERE idEmpleado=?");
    		st.setDate(1, empleado.getFechaDespido());
    		st.setInt(2, empleado.getIdEmpleado());    		
    		
    		st.executeUpdate();
    	}catch(Exception e) {
    		throw new Exception("Error al actualizar despido empleado: " + e.getMessage());
    	}finally {
    		try {
    			this.closeConnection();
    		}catch(Exception e) {
    			throw new Exception("Error al cerrar la conexion actualizando despido empleado: " + e.getMessage());
    		}
    	}
    }
}