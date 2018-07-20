package datos;

import entidades.TipoDocumento;

/**
 *
 * @author FrancoSili
 */
public class TipoDocumentoResponse implements Payload{
    private Integer idTipoDocumento;
    private String descripcion;

    public TipoDocumentoResponse(TipoDocumento d) {
        this.idTipoDocumento = d.getIdTipoDocumento();
        this.descripcion = d.getDescripcion();
    }
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
