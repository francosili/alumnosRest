package datos;

import entidades.Docente;

/**
 *
 * @author FrancoSili
 */

public class DocenteResponse implements Payload {
    private Integer idDocente;
    private String legajo;
    private PersonaResponse persona;

    public DocenteResponse(Docente d) {
        this.idDocente = d.getIdDocente();
        this.legajo = d.getLegajo();
        this.persona = new PersonaResponse(d.getPersona());
    }
    
    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
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
    
    
    
    @Override
    public String getClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
