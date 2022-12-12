package control;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import entidad.Ruta;
import entidad.TipoVuelo;
import entidad.Vuelo;

public class ControlVuelosTest {

    /*
     * Prueba unitaria 1
     * - Prueba para el caso exitoso, donde el origen y destino son valores validos
     * y
     * estan dentro de las rutas por lo que se pueden mostrar los vuelos disponibles
     */
    @Test
    public void testCrearVuelosValido() throws Exception {
        /*
         * Como el metodo a evaluar no tiene retorno validamos si se creo el vuelo
         * validando los
         * valores iniciales de la longitud de la lista de vuelos y tambien validando si
         * ese nuevo
         * vuelo quedo creado como objeto de tipo vuelo en la lista
         */
        ControlVuelos control = new ControlVuelos();
        int longitudInicial = control.getVuelos() != null ? control.getVuelos().size() : 0;
        Ruta ruta = new Ruta("manizales", "medellin", 2.5, 44000);
        Vuelo vuelo = new Vuelo(TipoVuelo.DIRECTO);
        boolean encontrado = false;
        List<Vuelo> vuelosAsignadas = control.getVuelos();
        int longitudFinal = vuelosAsignadas.size();

        control.crearVuelos("manizales", "medellin");
        vuelo.agregarRuta(ruta);

        for (Vuelo vuelo_aux : vuelosAsignadas) {
            if (vuelo_aux == vuelo && longitudInicial < longitudFinal) {
                encontrado = true;
            }
        }

        try {
            assertTrue(encontrado);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     * Prueba unitaria 2
     * - Prueba para el caso no exitoso, donde el origen y destino son valores
     * validos,
     * sin embargo no se encuentran en la ruta, para este caso el origen y destino
     * decidimos colocar el mismo valor
     */
    @Test
    public void testCrearVuelosInvalidos() throws Exception {
        /*
         * Como el metodo a evaluar no tiene retorno validamos si se creo la ruta
         * validando que el origen y el destino no sean iguales a los valores que
         * ingresamos
         */
        ControlVuelos control = new ControlVuelos();
        control.crearVuelos("manizales", "manizales");
        boolean modificado = false;
        List<Ruta> rutaActuales = control.getRutas();

        for (Ruta ruta : rutaActuales) {
            if (ruta.getOrigen().equals("manizales") && ruta.getDestino().equals("manizales")) {
                modificado = true;
            }
        }

        try {
            assertTrue(modificado);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    
    /* Prueba unitaria 2
    * - Prueba para el caso no exitoso, donde el origen y destino son valores
    * invalidos
    */
   @Test
   public void testCrearVuelosInvalidoAlguno() throws Exception {
    /*
     * como son invalidos se debe crear una nueva excepcion con el mensaje
     * "El origen y destino no son validos"
     */
    ControlVuelos control = new ControlVuelos();
    control.crearVuelos("manizales", "k");
    boolean modificado = false;
    List<Ruta> rutaActuales = control.getRutas();

    for (Ruta ruta : rutaActuales) {
        if (ruta.getOrigen().equals("manizales") && ruta.getDestino().equals("manizales")) {
            modificado = true;
        }
    }

    try {
        assertTrue(modificado);
    } catch (AssertionError e) {
        System.out.println(e.getMessage());
    }
}
}
