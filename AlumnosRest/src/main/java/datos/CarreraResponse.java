package datos;

import entidades.Carrera;
import java.util.Date;

/**
 *
 * @author FrancoSili
 */
public class CarreraResponse implements Payload{
    private Integer idCarrera;
    private String nombre;
    private String descripcion;
    private Date fechaDesde;
    private Date fechaHasta;

    public CarreraResponse(Carrera c) {
        this.idCarrera = c.getIdCarrera();
        this.nombre = c.getNombre();
        this.descripcion = c.getDescripcion();
        this.fechaDesde = c.getFechaDesde();
        this.fechaHasta = c.getFechaHasta();
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
