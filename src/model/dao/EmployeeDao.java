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

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * from empleados");
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
            throw new Exception("Listar Empleados "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion listar empleados: " + e.getMessage());
            }
        }
        return listaEmpleados;
    }

    public EmployeeVo buscar(int idEmpleado) throws Exception {

        EmployeeVo empleado = new EmployeeVo();
        String criterioBusqueda = '%' + Integer.toString(idEmpleado) + '%';

        try {
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("SELECT * FROM empleados WHERE idEmpleado "
                    + "LIKE ?");
            st.setString(1, criterioBusqueda);
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
                throw new Exception("Error al cerrar la conexion buscar empleado: " + e.getMessage());
            }
        }

        return empleado;
    }

    public void eliminar(EmployeeVo empleado) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("DELETE FROM empleados WHERE idEmpleado=?");
            st.setInt(1, empleado.getIdEmpleado());
            st.executeUpdate();
        }
        catch(Exception e){
            throw new Exception("Eliminar Empleado "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion eliminar empleado: " + e.getMessage());
            }
        }
    }

    public void registrar(EmployeeVo empleado) throws Exception{
        try{
            this.openConnection();

            PreparedStatement st = this.getConnection().prepareStatement("INSERT INTO empleados (idEmpleado, fechaContratacion, fechaDespido, idSalario)" +
                    "VALUES (?,?,?,?)");
            st.setInt(1, empleado.getIdEmpleado());
            st.setDate(2, empleado.getFechaContratacion());
            st.setDate(3, empleado.getFechaDespido());
            st.setInt(4, empleado.getIdSalario());
        }
        catch(Exception e){
            throw new Exception("Registrar Empleado "+e.getMessage());
        }finally {
            try {
                this.closeConnection();
            } catch (Exception e) {
                throw new Exception("Error al cerrar la conexion registrar empleado: " + e.getMessage());
            }
        }
    }
}
