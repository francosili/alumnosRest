package datos;

import entidades.InscripcionCarrera;
import java.util.Date;

/**
 *
 * @author FrancoSili
 */
public class InscripcionCarreraResponse implements Payload{
    private Integer idInscripcionCarrera;
    private Date fechaInscripcion;
    private CarreraResponse carrera;

    public InscripcionCarreraResponse(InscripcionCarrera s) {
        this.idInscripcionCarrera = s.getIdInscripcionCarrera();
        this.fechaInscripcion = s.getFechaInscripcion();
        this.carrera = new CarreraResponse(s.getCarrera());
    }

    public Integer getIdInscripcionCarrera() {
        return idInscripcionCarrera;
    }

    public void setIdInscripcionCarrera(Integer idInscripcionCarrera) {
        this.idInscripcionCarrera = idInscripcionCarrera;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public CarreraResponse getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraResponse carrera) {
        this.carrera = carrera;
    }

    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
