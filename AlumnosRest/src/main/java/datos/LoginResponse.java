package datos;

import entidades.Acceso;
import entidades.Usuario;

/*
 *
 * @author FrancoSili
 */

public class LoginResponse implements Payload{
    private UsuarioResponse cuenta;
    private AccesoResponse acceso;
    
    public LoginResponse(Usuario usuario, Acceso acceso) {
        this.cuenta = new UsuarioResponse(usuario);
        this.acceso = new AccesoResponse(acceso);
    }
    
    ////////////////////////////////////////////////////////////
    /////////             GETTERS AND SETTERS          /////////
    ////////////////////////////////////////////////////////////

    public UsuarioResponse getCuenta() {
        return cuenta;
    }

    public void setCuenta(UsuarioResponse cuenta) {
        this.cuenta = cuenta;
    }

    public AccesoResponse getAcceso() {
        return acceso;
    }

    public void setAcceso(AccesoResponse acceso) {
        this.acceso = acceso;
    }
     
    ////////////////////////////////////////////////////////////
    /////////                 OVERRIDE                 /////////
    ////////////////////////////////////////////////////////////
    
    @Override
    public String getClassName() {
        return LoginResponse.class.getSimpleName();
    }
}
