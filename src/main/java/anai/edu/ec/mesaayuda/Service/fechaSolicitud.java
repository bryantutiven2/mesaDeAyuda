
package anai.edu.ec.mesaayuda.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bryan
 */
public class fechaSolicitud {
    
    public static Date obtenerFechaInicio(){
        Date fecha = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = dateFormat.parse(fecha);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
