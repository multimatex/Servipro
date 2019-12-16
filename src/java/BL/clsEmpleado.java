package BL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clsEmpleado {
    
    Connection conn = null;
    
    public clsEmpleado(){
        
        clsConexion obclsConexion = new clsConexion();
        conn = obclsConexion.getConexion();
        
    }
    
    public String createEmpleado(Modelos.clsEmpleado obclsEmpleado){
        
        try 
        {
            PreparedStatement ps = conn.prepareStatement("{call spInsertarEmpleado (?,?,?,?,?,?)}");
            ps.setInt(1, obclsEmpleado.getObclsTipoDocumento().getInId());
            ps.setString(2, obclsEmpleado.getStDocumento());
            ps.setString(3, obclsEmpleado.getStNombre());
            ps.setString(4, obclsEmpleado.getStTelefono());
            ps.setInt(5, obclsEmpleado.getObclsCentroCosto().getInId());
            ps.setInt(6, obclsEmpleado.getObclsCargo().getInId());
            
            
            ps.execute();
            return "Se realizo el proceso con exito";
        } 
        catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    public List<Modelos.clsEmpleado> getEmpleado(){
        
        List<Modelos.clsEmpleado> lstclsEmpleado = new ArrayList<Modelos.clsEmpleado>();
        
        try {
            ResultSet rs = null;
            PreparedStatement ps = conn.prepareStatement("{call spConsultarEmpleado}");
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                Modelos.clsEmpleado obclsEmpleado = new Modelos.clsEmpleado();
                obclsEmpleado.setInId(rs.getInt("emplId"));
                
                Modelos.clsTipoDocumento obclsTipoDocumento = new Modelos.clsTipoDocumento();
                obclsTipoDocumento.setInId(rs.getInt("tidoId"));
                obclsTipoDocumento.setStDescripcion(rs.getString("tidoDescripcion"));
                obclsEmpleado.setObclsTipoDocumento(obclsTipoDocumento);
                
                obclsEmpleado.setStDocumento(rs.getString("emplDocumento"));
                obclsEmpleado.setStNombre(rs.getString("emplNombre"));
                obclsEmpleado.setStTelefono(rs.getString("emplTelefono"));
                
                Modelos.clsCentroCosto obclsCentroCosto = new Modelos.clsCentroCosto();
                obclsCentroCosto.setInId(rs.getInt("cecoId"));
                obclsCentroCosto.setStDescripcion(rs.getString("cecoDescripcion"));
                obclsEmpleado.setObclsCentroCosto(obclsCentroCosto);
                
                Modelos.clsCargo obclsCargo = new Modelos.clsCargo();
                obclsCargo.setInId(rs.getInt("cargId"));
                obclsCargo.setStDescripcion(rs.getString("cargDescripcion"));
                obclsEmpleado.setObclsCargo(obclsCargo);
                
                lstclsEmpleado.add(obclsEmpleado);
            }
            
        } 
        catch (Exception ex) {
        }
        return lstclsEmpleado;
        
    }
    
    public String updateEmpleado(Modelos.clsEmpleado obclsEmpleado){
        
        try {
            PreparedStatement ps = conn.prepareStatement("{call spModificarEmpleado(?,?,?,?,?,?,?)}");
            
            ps.setInt(1, obclsEmpleado.getInId());
            ps.setInt(2, obclsEmpleado.getObclsTipoDocumento().getInId());
            ps.setString(3, obclsEmpleado.getStDocumento());
            ps.setString(4, obclsEmpleado.getStNombre());
            ps.setString(5, obclsEmpleado.getStTelefono());
            ps.setInt(6, obclsEmpleado.getObclsCentroCosto().getInId());
            ps.setInt(7, obclsEmpleado.getObclsCargo().getInId());
            
            ps.execute();
            
            return "Se realizo el proceso con exito";
        } 
        catch (Exception ex) 
        {
            return ex.getMessage();
        }
    }
    
    public String deleteEmpleado(Modelos.clsEmpleado obclsEmpleado){
        
        try {
            
            PreparedStatement ps= conn.prepareStatement("{call spEliminarEmpleado(?)}");
            ps.setInt(1, obclsEmpleado.getInId());
            ps.execute();
            
            return "Se realizo el proceso con exito";
        } 
        catch (Exception ex) {
            
            return ex.getMessage();
        }
    }
    
    public List<Modelos.clsEmpleado> getBuscarEmpleado(Modelos.clsBuscar obclsBuscar){
        
        List<Modelos.clsEmpleado> lstclsEmpleado = new ArrayList<Modelos.clsEmpleado>();
        
        try {
            
            ResultSet rs = null;
            PreparedStatement ps = conn.prepareStatement("{call spBuscarEmpleado(?)}");
            ps.setString(1, obclsBuscar.getStBuscar());
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Modelos.clsEmpleado obclsEmpleado = new Modelos.clsEmpleado();
                obclsEmpleado.setInId(rs.getInt("emplId"));
                
                Modelos.clsTipoDocumento obclsTipoDocumento = new Modelos.clsTipoDocumento();
                obclsTipoDocumento.setInId(rs.getInt("tidoId"));
                obclsTipoDocumento.setStDescripcion(rs.getString("tidoDescripcion"));
                obclsEmpleado.setObclsTipoDocumento(obclsTipoDocumento);
                
                obclsEmpleado.setStDocumento(rs.getString("emplDocumento"));
                obclsEmpleado.setStNombre(rs.getString("emplNombre"));
                obclsEmpleado.setStTelefono(rs.getString("emplTelefono"));
                
                Modelos.clsCentroCosto obclsCentroCosto = new Modelos.clsCentroCosto();
                obclsCentroCosto.setInId(rs.getInt("cecoId"));
                obclsCentroCosto.setStDescripcion(rs.getString("cecoDescripcion"));
                obclsEmpleado.setObclsCentroCosto(obclsCentroCosto);
                
                Modelos.clsCargo obclsCargo = new Modelos.clsCargo();
                obclsCargo.setInId(rs.getInt("cargId"));
                obclsCargo.setStDescripcion(rs.getString("cargDescripcion"));
                obclsEmpleado.setObclsCargo(obclsCargo);
                
                lstclsEmpleado.add(obclsEmpleado);
            }
            
            
        } 
        catch (Exception e) {
            
        }
        return lstclsEmpleado;
    }
}