package entidad;

/*
 * Ruta
 */
public class Ruta {
  private String origen;
  private String destino;
  private double duracion;
  private double precio;

  public Ruta(String origen, String destino, double duracion, double precio) {
    this.origen = origen;
    this.destino = destino;
    this.duracion = duracion;
    this.precio = precio;
  }

  public String getOrigen() {
    return origen;
  }

  public String getDestino() {
    return destino;
  }

  public double getDuracion() {
    return duracion;
  }

  public double getPrecio() {
    return precio;
  }
}
