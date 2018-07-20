package datos;

import entidades.Alumno;
import entidades.InscripcionCarrera;
import entidades.InscripcionCurso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author FrancoSili
 */
public class AlumnoResponse implements Payload {
    private int idAlumno;
    private String legajo;
    private PersonaResponse persona;
    private List<InscripcionCarreraResponse> carreras;
    private List<InscripcionCursoResponse> cursos;

    public AlumnoResponse(Alumno a) {
        this.idAlumno = a.getIdAlumno();
        this.legajo = a.getLegajo();
        this.persona = new PersonaResponse(a.getPersona());
        this.carreras = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }
    
   
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public PersonaResponse getPersona() {
        return persona;
    }

    public void setPersona(PersonaResponse persona) {
        this.persona = persona;
    }

    public List<InscripcionCarreraResponse> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<InscripcionCarreraResponse> carreras) {
        this.carreras = carreras;
    }

    public List<InscripcionCursoResponse> getCursos() {
        return cursos;
    }

    public void setCursos(List<InscripcionCursoResponse> cursos) {
        this.cursos = cursos;
    }
 
    
    public void agregarInscripcionesCarreras(Collection<InscripcionCarrera> lista) {
        for(InscripcionCarrera s : lista) {
            InscripcionCarreraResponse p = new InscripcionCarreraResponse(s);
            this.carreras.add(p);
        }
    }
    
    public void agregarInscripcionesCursos(Collection<InscripcionCurso> lista) {
        for(InscripcionCurso s : lista) {
            InscripcionCursoResponse p = new InscripcionCursoResponse(s);
            this.cursos.add(p);
        }
    }

    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
