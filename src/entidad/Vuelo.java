package entidad;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {

  private List<Ruta> rutas;
  private TipoVuelo tipo;

  public Vuelo(TipoVuelo tipo) {
    this.tipo = tipo;
    this.rutas = new ArrayList<Ruta>();
  }

  private double calcularPrecio() {
    double precioTotal = 0;
    for (Ruta ruta : rutas) {
      precioTotal += ruta.getPrecio();
    }

    return precioTotal;
  }

  private double calcularDuracion() {
    double duracionTotal = 0;
    for (Ruta ruta : rutas) {
      duracionTotal += ruta.getDuracion();
    }

    return duracionTotal;
  }

  public Ruta buscarRuta(String origen, String destino) {
    for (Ruta ruta : rutas) {
      if (ruta.getOrigen() == origen && ruta.getDestino() == destino) {
        return ruta;
      }
    }
    return null;
  }

  public void agregarRuta(Ruta ruta) {
    this.rutas.add(ruta);
  }

  @Override
  public String toString() {
    return String.format("Tipo: %s, Precio: %s, Duracion: %s", this.tipo.toString(), this.calcularPrecio(), this.calcularDuracion());
  }
}