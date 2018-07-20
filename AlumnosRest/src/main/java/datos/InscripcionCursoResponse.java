
package datos;

import entidades.InscripcionCurso;
import java.util.Date;

/**
 *
 * @author FrancoSili
 */
public class InscripcionCursoResponse implements Payload{
    private Integer idInscripcionCurso;
    private Date fechaInscripcion;
    private CursoResponse curso;

    public InscripcionCursoResponse(InscripcionCurso c) {
        this.idInscripcionCurso = c.getIdInscripcionCurso();
        this.fechaInscripcion = c.getFechaInscripcion();
        this.curso = new CursoResponse(c.getCurso());
    }

    public Integer getIdInscripcionCurso() {
        return idInscripcionCurso;
    }

    public void setIdInscripcionCurso(Integer idInscripcionCurso) {
        this.idInscripcionCurso = idInscripcionCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public CursoResponse getCurso() {
        return curso;
    }

    public void setCurso(CursoResponse curso) {
        this.curso = curso;
    }
    
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
