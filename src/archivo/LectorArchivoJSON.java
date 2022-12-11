package archivo;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import entidad.Ruta;

public class LectorArchivoJSON implements LectorArchivo<Ruta> {
  private String nombreArchivo;

  public LectorArchivoJSON(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo + ".json";
  }

  @Override
  public List<Ruta> cargarArchivo() {
    List<Ruta> rutas = new ArrayList<Ruta>();
    JSONParser jsonParser = new JSONParser();

    try {
      FileReader reader =  new FileReader(this.nombreArchivo);
      Object objetoRuta = jsonParser.parse(reader);
      JSONArray listaDeRutas = (JSONArray) objetoRuta;

      for (Object ruta : listaDeRutas) {
        JSONObject JSONRuta = (JSONObject) ruta;
        String origen = (String) JSONRuta.get("origen");
        String destino = (String) JSONRuta.get("destino");
        double precio = (double) JSONRuta.get("precio");
        double duracion = (double) JSONRuta.get("duracion");

        rutas.add(new Ruta(origen, destino, precio, duracion));
      }
      
      return rutas;

    } catch (Exception e) {
      return null;
    }
  }
  
}
