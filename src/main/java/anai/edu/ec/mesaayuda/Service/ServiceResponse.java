
package anai.edu.ec.mesaayuda.Service;

/**
 *
 * @author bryan
 */
public class ServiceResponse<T> {
    private String status;
    private T data;

    public ServiceResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ServiceResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
}
