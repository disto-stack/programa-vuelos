package control;

import java.util.ArrayList;
import java.util.List;

import archivo.LectorArchivo;
import archivo.LectorArchivoJSON;
import entidad.Ruta;
import entidad.TipoVuelo;
import entidad.Vuelo;

public class ControlVuelos {
  private List<Ruta> rutas;
  private List<Vuelo> vuelos;

  public ControlVuelos() {
    this.rutas = new ArrayList<Ruta>();
    this.vuelos = new ArrayList<Vuelo>();
  }

  public void cargarRutas() {
    LectorArchivo<Ruta> archivo = new LectorArchivoJSON("\\src\\archivo\\datos.json");
    this.rutas = archivo.cargarArchivo();
  }

  /**
   * 
   * @param origen
   * @param destino
   * @throws Exception
   */
  public void crearVuelos(String origen, String destino) throws Exception {
    if (!(origen == destino)) {
      for (int i = 0; i < this.rutas.size(); i++) {
        Ruta ruta = this.rutas.get(i);

        if (ruta.getOrigen().toUpperCase().equals(origen.toUpperCase())
            && ruta.getDestino().toUpperCase().equals(destino.toUpperCase())) {

          Vuelo vuelo = new Vuelo(TipoVuelo.DIRECTO);
          vuelo.agregarRuta(ruta);

          this.vuelos.add(vuelo);
          continue;
        } else if (ruta.getOrigen().toUpperCase().equals(origen.toUpperCase())) {
          for (int j = 0; j < rutas.size(); j++) {
            Ruta rutaEscala = this.rutas.get(j);
            if (rutaEscala.getOrigen().toUpperCase().equals(ruta.getDestino().toUpperCase()) &&
                rutaEscala.getDestino().toUpperCase().equals(destino.toUpperCase())) {

              Vuelo vuelo = new Vuelo(TipoVuelo.ESCALA);
              vuelo.agregarRuta(ruta);
              vuelo.agregarRuta(rutaEscala);

              this.vuelos.add(vuelo);
              continue;
            }
          }
        }
      }
    } else {
      throw new Exception("El origen y destino no son validos");
    }
  }

  public String mostrarVuelosDisponibles(String origen, String destino) throws Exception {
    this.cargarRutas();

    if (this.rutas.size() <= 0) {
      return null;
    }

    this.crearVuelos(origen, destino);

    if (this.vuelos.size() <= 0) {
      return "No hay vuelos posibles para las ciudades solicitadas";
    }

    String string = "Vuelos posibles\n";

    for (Vuelo vuelo : vuelos) {
      string = string + vuelo.toString() + "\n";
    }

    return string;
  }

  public List<Ruta> getRutas() {
    return rutas;
  }

  public List<Vuelo> getVuelos() {
    return vuelos;
  }
}
