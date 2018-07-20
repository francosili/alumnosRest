package servicios;

import com.google.gson.JsonObject;
import datos.AppCodigo;
import datos.ServicioResponse;
import entidades.Acceso;
import entidades.Alumno;
import entidades.Persona;
import entidades.TipoDocumento;
import entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistencias.AccesoFacade;
import persistencias.AlumnoFacade;
import persistencias.PersonaFacade;
import persistencias.TipoDocumentoFacade;
import persistencias.UsuarioFacade;
import utils.Utils;

/**
 *
 * @author FrancoSili
 */

@Stateless
@Path("alumno")
public class AlumnoRest {
    @Inject UsuarioFacade usuarioFacade;
    @Inject AccesoFacade accesoFacade;
    @Inject TipoDocumentoFacade tipoDocumentoFacade;
    @Inject AlumnoFacade alumnoFacade;
    @Inject PersonaFacade personaFacade;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setAlumno(  
        @HeaderParam ("token") String token,
        @Context HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ServicioResponse respuesta = new ServicioResponse();
        try {
            // Obtengo el body de la request
            JsonObject jsonBody = Utils.getJsonObjectFromRequest(request);
            
            // Obtengo los atributos del body
            String legajo = (String) Utils.getKeyFromJsonObject("legajo", jsonBody, "String");
            String documento = (String) Utils.getKeyFromJsonObject("documento", jsonBody, "String");
            String nombre = (String) Utils.getKeyFromJsonObject("nombre", jsonBody, "String");
            String apellido = (String) Utils.getKeyFromJsonObject("apellido", jsonBody, "String");
            String direccion = (String) Utils.getKeyFromJsonObject("direccion", jsonBody, "String");
            Date fechaNacimiento = (Date) Utils.getKeyFromJsonObject("fechaNacimiento", jsonBody, "Date");
            Integer tipoDoc = (Integer) Utils.getKeyFromJsonObject("tipoDoc", jsonBody, "Integer");
            
            //valido que token no sea null
            if(token == null || token.trim().isEmpty()) {
                respuesta.setControl(AppCodigo.ERROR, "Error, token vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //Busco el token
            Acceso userToken = accesoFacade.findByToken(token);

            //valido que Acceso no sea null
            if(userToken == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, Acceso nulo");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //Busco el usuario
            Usuario user = usuarioFacade.getByToken(userToken);

            //valido que el Usuario no sea null
            if(user == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, Usuario nulo");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //valido vencimiento token
            if(!accesoFacade.validarToken(userToken, user)) {
                respuesta.setControl(AppCodigo.ERROR, "Credenciales incorrectas");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta.toJson()).build();
            }

            //Me fijo que  que los campos no sean nulos
            if(legajo == null || documento == null || nombre == null || apellido == null || fechaNacimiento == null || tipoDoc == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, algun campo vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            
            //busco el tipo de documento
            TipoDocumento tipo = tipoDocumentoFacade.find(tipoDoc);
            
            //Me fijo que tipo de documento no sea nulo
            if(tipo == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, no existe ese tipo de documento");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            
            boolean transaccion;
            boolean transaccion2;
            Persona persona = new Persona();
            Alumno alumno = new Alumno();
            persona.setApellido(apellido);
            persona.setDireccion(direccion);
            persona.setDocumento(documento);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setNombre(nombre);
            persona.setTipoDoc(tipo);
            alumno.setPersona(persona);
            alumno.setLegajo(legajo);            
            transaccion = personaFacade.setPersonaNuevo(persona);
            transaccion2 = alumnoFacade.setAlumnoNuevo(alumno);
            if(!transaccion) {
                respuesta.setControl(AppCodigo.ERROR, "No se pudo dar de alta la Persona");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            if(!transaccion2) {
                respuesta.setControl(AppCodigo.ERROR, "No se pudo dar de alta el Alumno");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            respuesta.setControl(AppCodigo.OK, "Alumno creado con exito");
            return Response.status(Response.Status.CREATED).entity(respuesta.toJson()).build();
        } catch (Exception ex) { 
            respuesta.setControl(AppCodigo.ERROR, ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
        }
    } 
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAlumno(  
        @HeaderParam ("token") String token,
        @Context HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ServicioResponse respuesta = new ServicioResponse();
        try {
            // Obtengo el body de la request
            JsonObject jsonBody = Utils.getJsonObjectFromRequest(request);
            
            // Obtengo los atributos del body
            Integer idAlumno = (Integer) Utils.getKeyFromJsonObject("tipoDoc", jsonBody, "Integer");
            String legajo = (String) Utils.getKeyFromJsonObject("legajo", jsonBody, "String");
            String documento = (String) Utils.getKeyFromJsonObject("documento", jsonBody, "String");
            String nombre = (String) Utils.getKeyFromJsonObject("nombre", jsonBody, "String");
            String apellido = (String) Utils.getKeyFromJsonObject("apellido", jsonBody, "String");
            String direccion = (String) Utils.getKeyFromJsonObject("direccion", jsonBody, "String");
            Date fechaNacimiento = (Date) Utils.getKeyFromJsonObject("fechaNacimiento", jsonBody, "Date");
            Integer tipoDoc = (Integer) Utils.getKeyFromJsonObject("tipoDoc", jsonBody, "Integer");
            
            //valido que token no sea null
            if(token == null || token.trim().isEmpty()) {
                respuesta.setControl(AppCodigo.ERROR, "Error, token vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //Busco el token
            Acceso userToken = accesoFacade.findByToken(token);

            //valido que Acceso no sea null
            if(userToken == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, Acceso nulo");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //Busco el usuario
            Usuario user = usuarioFacade.getByToken(userToken);

            //valido que el Usuario no sea null
            if(user == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, Usuario nulo");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }

            //valido vencimiento token
            if(!accesoFacade.validarToken(userToken, user)) {
                respuesta.setControl(AppCodigo.ERROR, "Credenciales incorrectas");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta.toJson()).build();
            }

            //Me fijo que  que los campos no sean nulos
            if(idAlumno == null || legajo == null || documento == null || nombre == null || apellido == null || fechaNacimiento == null || tipoDoc == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, algun campo vacio");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            
            //busco el tipo de documento
            TipoDocumento tipo = tipoDocumentoFacade.find(tipoDoc);
            
            //Me fijo que tipo de documento no sea nulo
            if(tipo == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, no existe ese tipo de documento");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            
            Alumno alumno = alumnoFacade.find(idAlumno);
            if(alumno == null) {
                respuesta.setControl(AppCodigo.ERROR, "Error, no existe ese alumno");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            
            boolean transaccion;
            boolean transaccion2;
            Persona persona = alumno.getPersona();
            persona.setApellido(apellido);
            persona.setDireccion(direccion);
            persona.setDocumento(documento);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setNombre(nombre);
            persona.setTipoDoc(tipo);
            alumno.setPersona(persona);
            alumno.setLegajo(legajo);            
            transaccion = personaFacade.editPersona(persona);
            transaccion2 = alumnoFacade.editAlumno(alumno);
            if(!transaccion) {
                respuesta.setControl(AppCodigo.ERROR, "No se pudo editar la Persona");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            if(!transaccion2) {
                respuesta.setControl(AppCodigo.ERROR, "No se pudo editar el Alumno");
                return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
            }
            respuesta.setControl(AppCodigo.OK, "Alumno editado con exito");
            return Response.status(Response.Status.CREATED).entity(respuesta.toJson()).build();
        } catch (Exception ex) { 
            respuesta.setControl(AppCodigo.ERROR, ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta.toJson()).build();
        }
    } 
}
