
import java.awt.Color;
// FALTA DEFINIR ESTA CLASE
public class XColor {
  public int a, b, c;

  public static final XColor WHITE = new XColor(255, 255, 255);
  
  public XColor(int r, int g, int b) {
    this.a = r;
    this.b = g;
    this.c = b;
  }

  public int getValor() {
    return new Color(a, b, c).getRGB();
  }
}

