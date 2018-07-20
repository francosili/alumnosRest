
package servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Franco Sili
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(servicios.AlumnoRest.class);
        resources.add(servicios.CursoRest.class);
        resources.add(servicios.EstadoAcademicoRest.class);
        resources.add(servicios.InscripcionRest.class);
        resources.add(servicios.UsuarioRest.class);
        resources.add(utils.CrossRules.class);
    }
    
}
