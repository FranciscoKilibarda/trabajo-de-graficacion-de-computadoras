public class Estilo {
  public int pintar, noPintar;

  public Estilo(int p, int n) {
    pintar = (p > 0) ? p : 1;
    noPintar = (n > 0) ? n : 1;
  }
}
