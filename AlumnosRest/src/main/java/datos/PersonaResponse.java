package datos;

import entidades.Persona;
import java.util.Date;

/**
 *
 * @author FrancoSili
 */
public class PersonaResponse implements Payload{
    private Integer idPersona;
    private String documento;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String direccion;
    private TipoDocumentoResponse tipoDoc;

    public PersonaResponse(Persona p) {
        this.idPersona = p.getIdPersona();
        this.documento = p.getDocumento();
        this.nombre = p.getNombre();
        this.apellido = p.getApellido();
        this.fechaNacimiento = p.getFechaNacimiento();
        this.direccion = p.getDireccion();
        this.tipoDoc = new TipoDocumentoResponse(p.getTipoDoc());
    }
    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoDocumentoResponse getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDocumentoResponse tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    
    
    
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
