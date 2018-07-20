package datos;

import entidades.Alumno;
import entidades.Curso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author FrancoSili
 */
public class CursoResponse implements Payload {
    private Integer idCurso;
    private String nombre;
    private String descripcion;
    private int cupoMaxio;
    private Date fecha;
    private CarreraResponse carrera;
    private DocenteResponse docente;
    private List<AlumnoResponse> alumnos;

    public CursoResponse(Curso c) {
        this.idCurso = c.getIdCurso();
        this.nombre = c.getNombre();
        this.descripcion = c.getDescripcion();
        this.cupoMaxio = c.getCupoMaxio();
        this.fecha = c.getFecha();
        this.carrera = new CarreraResponse(c.getCarrera());
        this.docente = new DocenteResponse(c.getDocente());
        this.alumnos = new ArrayList<>();
    }
    
    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
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

    public int getCupoMaxio() {
        return cupoMaxio;
    }

    public void setCupoMaxio(int cupoMaxio) {
        this.cupoMaxio = cupoMaxio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CarreraResponse getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraResponse carrera) {
        this.carrera = carrera;
    }

    public DocenteResponse getDocente() {
        return docente;
    }

    public void setDocente(DocenteResponse docente) {
        this.docente = docente;
    }

    public List<AlumnoResponse> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnoResponse> alumnos) {
        this.alumnos = alumnos;
    }
    
    public void agregarAlumno(Alumno alumno) {
            AlumnoResponse alumn = new AlumnoResponse(alumno);
            this.alumnos.add(alumn);
    }
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
