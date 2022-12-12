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


  /**
   * Lee desde un lector de archivos, las rutas almacenadas
   */
  public void cargarRutas() {
    this.vuelos = new ArrayList<Vuelo>();
    
    LectorArchivo<Ruta> archivo = new LectorArchivoJSON("src/archivo/datos");
    this.rutas = archivo.cargarArchivo();
  }

  /**
   * Crea los objetos de vuelos posibles (Directos o en escala) con las rutas cargadas
   * previamente en el control.
   * @param origen de los vuelos posibles.
   * @param destino de los vuelos posibles.
   */
  public void crearVuelos(String origen, String destino) {
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
          }
        }
      }
    }
  }

  /**
   * Método para mostrar todos los vuelos posibles dado un origen y un destino.
   * @param origen de los vuelos posibles que se desean mostrar.
   * @param destino de los vuelos posibles que se desean mostrar.
   * @return cadena de texto con los vuelos posibles.
   */
  public String mostrarVuelosDisponibles(String origen, String destino) {
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
}
