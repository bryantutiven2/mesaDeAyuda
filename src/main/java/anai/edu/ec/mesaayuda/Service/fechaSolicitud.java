
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
            System.out.println(fecha);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return fecha;
    }
}
