public class Linea extends Primitiva {
  private Punto p1, p2;

  public Linea(Punto p1, Punto p2, Panel canvas) {
    this.p1 = p1;
    this.p2 = p2;
    this.canvas = canvas;
  }

  // BRESENHAM
  @Override
  public void dibujarContorno(int grosor, Estilo estilo, XColor color, int delay) {
    int dx = this.p2.x - this.p1.x,
        dy = this.p2.y - this.p1.y;

    int IncYi;
    if (dy >= 0) {
      IncYi = 1;
    } else {
      dy = -dy;
      IncYi = -1;
    }

    int IncXi;
    if (dx >= 0) {
      IncXi = 1;
    } else {
      dx = -dx;
      IncXi = -1;
    }

    int IncXr, IncYr;
    if (dx >= dy) {
      IncYr = 0;
      IncXr = IncXi;
    } else {
      IncXr = 0;
      IncYr = IncYi;

      int tmp = dx;
      dx = dy; dy = tmp;
    }

    int x, y, avR, av, avI;
    x = this.p1.x; y = this.p1.y;
    avR = dy << 1;
    av = avR - dx;
    avI = av - dx;

    int pintar = 0, noPintar = 0, cnt = 1;
    if (estilo != null) {
      pintar = estilo.pintar;
      noPintar = estilo.noPintar;
    }

    boolean f = true;
    while (true) {
      if (estilo != null) {
        if (cnt == 1) {
          pintar--;

          if (pintar == 0) {
            pintar = estilo.pintar;
            cnt = 0;
            f = false;
          }
        } else {
          noPintar--;

          if (noPintar == 0) {
            noPintar = estilo.noPintar;
            cnt = 1;
            f = true;
          }
        }
      } else
        f = true;


      if (f)
        for (int i = 0; i < grosor; i++)
          for (int j = 0; j < grosor; j++)
            canvas.drawPixel(x + i, y + j, color.getValor());

      if (av >= 0) {
        x += IncXi;
        y += IncYi;
        av += avI;
      } else {
        x += IncXr;
        y += IncYr;
        av += avR;
      }

      if (x == this.p2.x && y == this.p2.y)
        break;

      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
