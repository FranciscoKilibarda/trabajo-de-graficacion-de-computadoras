abstract public class Primitiva {
  protected Panel canvas;

  public void dibujarContorno() {
    dibujarContorno(1, null, new XColor(255, 255, 255), 0);
  }

  abstract public void dibujarContorno(int grosor, Estilo estilo, XColor color, int delay);
}