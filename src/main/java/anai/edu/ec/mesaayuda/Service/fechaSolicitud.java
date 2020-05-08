
package anai.edu.ec.mesaayuda.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bryan
 */
public class fechaSolicitud {
    
    public static Date obtenerFecha(){
        Date fecha = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = new Date();
            fecha = dateFormat.parse(dateFormat.format(date));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return fecha;
    }
    
    public static Date convertirFecha(String fecha){
        Date date = null;
        try{
            SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            Date date1 = parseFormat.parse(fecha);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = dateFormat.parse(dateFormat.format(date1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
    
    public static Date convertirFechaSimple(String fecha){
        Date date = null;
        try{
            SimpleDateFormat parseFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = parseFormat.parse(fecha);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(dateFormat.format(date1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
