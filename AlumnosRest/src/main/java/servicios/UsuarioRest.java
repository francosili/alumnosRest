package servicios;

import datos.AppCodigo;
import datos.LoginResponse;
import datos.ServicioResponse;
import entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistencias.AccesoFacade;
import persistencias.UsuarioFacade;

/**
 *
 * @author FrancoSili
 */

@Stateless
@Path("usuarios")
public class UsuarioRest {
    @Inject UsuarioFacade usuarioFacade;
    @Inject AccesoFacade accesoFacade;
    
    @POST
    @Path("/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response auth(@PathParam("usuario") String cuenta, @HeaderParam("clave") String clave, @Context HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ServicioResponse respuesta = new ServicioResponse();
        
        //Valido los datos de ingreso
        if (cuenta == null || clave == null || cuenta.trim().isEmpty() || clave.trim().isEmpty()) {
            respuesta.setControl(AppCodigo.ERROR, "Error en los parametros");
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
        }
        
        //Busco el usuario en la base de datos        
        Usuario usuario = usuarioFacade.getByCuenta(cuenta);
                
        //Si no se encuentra, devuelvo error
        if (usuario == null) {
            respuesta.setControl(AppCodigo.ERROR, "Usuario o contraseña invalida");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta.toJson()).build();
        }
       
        // Si la clave es correcta
        if (usuario.getClave().equals(clave)) {
            // Si está todo ok, genero token y completo el response
            entidades.Acceso acceso = accesoFacade.pedirToken(usuario);
                       
            // Genero la respuesta
            LoginResponse lr = new LoginResponse(usuario, acceso);
            respuesta.setDatos(lr);
            respuesta.setControl(AppCodigo.OK, "");
        } else {
            //Usuario existe pero está mal la clave, se devuelve mismo error que si no existiera
            respuesta.setControl(AppCodigo.ERROR, "Usuario o contraseña invalida");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta.toJson()).build();
        }
        return Response.ok(respuesta.toJson(), MediaType.APPLICATION_JSON).build();
    }
}
