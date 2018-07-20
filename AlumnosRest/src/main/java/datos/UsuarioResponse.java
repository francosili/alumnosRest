package datos;

import entidades.Carrera;
import entidades.Usuario;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author FrancoSili
 */
public class UsuarioResponse {
    private Integer idUsuario;
    private String nombreUsuario;
    private String password;
    private String nombrePersona;
    private String apellidoPersona;
    private String mail;
    private String direccion;

    public UsuarioResponse(Usuario u) {
        this.idUsuario = u.getIdUsuario();
        this.nombreUsuario = u.getNombreUsuario();
        this.password = u.getClave();
        this.nombrePersona = u.getPersona().getNombre();
        this.apellidoPersona = u.getPersona().getNombre();
        this.direccion = u.getPersona().getDireccion();
    }
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   
    
}
