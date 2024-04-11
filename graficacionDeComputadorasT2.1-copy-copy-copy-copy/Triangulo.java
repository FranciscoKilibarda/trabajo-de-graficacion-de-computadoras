 import java.util.Comparator;
 import java.util.Arrays;

public class Triangulo extends Grafico {
  private Punto p1, p2, p3;
  private XColor bordeColor;
  private XColor rellenoColor;
  
  public Triangulo(Punto p1, Punto p2, Punto p3, Panel canvas) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    this.canvas = canvas;
    this.bordeColor = new XColor(255, 255, 255); // Color blanco por defecto
    this.rellenoColor = new XColor(255, 255, 255); // Color blanco por defecto
  }

  @Override
  public void dibujarContorno(int grosor, Estilo estilo, XColor color, int delay) {
    Linea l1 = new Linea(p1, p2, canvas),
          l2 = new Linea(p2, p3, canvas),
          l3 = new Linea(p3, p1, canvas);

    l1.dibujarContorno(grosor, estilo, color, delay);
    l2.dibujarContorno(grosor, estilo, color, delay);
    l3.dibujarContorno(grosor, estilo, color, delay);
  }

  @Override
  public void dibujarRelleno(XColor color, int delay) {
     // Obtener las coordenadas y ordenarlas de menor a mayor
    Punto[] puntos = {p1, p2, p3};
    Arrays.sort(puntos, Comparator.comparingInt(p -> p.y));

    // Coordenadas y del triángulo
    int minY = puntos[0].y;
    int midY = puntos[1].y;
    int maxY = puntos[2].y;

    // Coordenadas x de los puntos que forman las líneas
    double x1 = puntos[0].x;
    double x2 = puntos[0].x;
    double x3 = puntos[1].x;

    // Pendientes de las líneas del triángulo
    double m1 = (puntos[1].x - puntos[0].x) / (double)(puntos[1].y - puntos[0].y);
    double m2 = (puntos[2].x - puntos[0].x) / (double)(puntos[2].y - puntos[0].y);
    double m3 = (puntos[2].x - puntos[1].x) / (double)(puntos[2].y - puntos[1].y);

    // Iterar desde el punto más bajo hasta el punto medio del triángulo
    for (int y = minY; y < midY; y++) {
        int startX = (int)Math.ceil(x1 + (y - minY) * m1);
        int endX = (int)Math.ceil(x2 + (y - minY) * m2);
        dibujarLineaHorizontal(startX, endX, y, color, delay);
    }

    // Iterar desde el punto medio hasta el punto más alto del triángulo
    for (int y = midY; y <= maxY; y++) {
        int startX = (int)Math.ceil(x1 + (y - minY) * m1);
        int endX = (int)Math.ceil(x3 + (y - minY) * m3);
        dibujarLineaHorizontal(startX, endX, y, color, delay);
    }
    }
  
    private void dibujarLineaHorizontal(int startX, int endX, int y, XColor color, int delay) {
    for (int x = startX; x <= endX; x++) {
        canvas.drawPixel(x, y, color.getValor());
    }

    try {
        Thread.sleep(delay);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    
 @Override // En la clase Triangulo
public void setBordeColor(XColor color) {
    this.bordeColor = color;
}

 @Override
public void setRellenoColor(XColor color) {
    this.rellenoColor = color;
}
}

        
