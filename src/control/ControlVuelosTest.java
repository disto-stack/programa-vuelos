package control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entidad.Ruta;
import entidad.TipoVuelo;
import entidad.Vuelo;

public class ControlVuelosTest {

    @Test
    public void testCrearVuelosDirectosValido() {
        ControlVuelos control = new ControlVuelos();

        List<Ruta> rutas = new ArrayList<Ruta>();

        rutas.add(new Ruta("manizales", "medellin", 1, 90000));
        control.setRutas(rutas);

        control.crearVuelos("manizales", "medellin");
       
        assertEquals( 1, control.getVuelos().size());
    }

    @Test
    public void testCrearVuelosConEscalaValido() {
        ControlVuelos control = new ControlVuelos();

        List<Ruta> rutas = new ArrayList<Ruta>();

        rutas.add(new Ruta("manizales", "cartagena", 1, 90000));
        rutas.add(new Ruta("manizales", "medellin", 1, 90000));
        rutas.add(new Ruta("medellin", "cartagena", 1, 90000));
        control.setRutas(rutas);

        control.crearVuelos("manizales", "cartagena");
       
        assertEquals( 2, control.getVuelos().size());
    }
    
    @Test
    public void testCrearVuelosConOrigenInvalido() {
        ControlVuelos control = new ControlVuelos();

        List<Ruta> rutas = new ArrayList<Ruta>();

        rutas.add(new Ruta("manizales", "cartagena", 1, 90000));
        control.setRutas(rutas);

        control.crearVuelos("pereira", "cartagena");
        
        assertEquals( 0, control.getVuelos().size());
    }

    @Test
    public void testCrearVuelosConDestinoInvalido() {
        ControlVuelos control = new ControlVuelos();

        List<Ruta> rutas = new ArrayList<Ruta>();

        rutas.add(new Ruta("manizales", "cartagena", 1, 90000));
        rutas.add(new Ruta("manizales", "medellin", 1, 90000));
        rutas.add(new Ruta("medellin", "cartagena", 1, 90000));
        control.setRutas(rutas);

        control.crearVuelos("manizales", "cali");
        
        assertEquals( 0, control.getVuelos().size());
    }
}
