import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Panel canvas;
    private JButton btnColorBorde;
    private JButton btnColorRelleno;
    private JButton btnGenerarTriangulo;
    private JButton btnGenerarCircunferencia;
    private XColor colorBordeSeleccionado; // Color seleccionado para el borde
    private XColor colorRellenoSeleccionado; // Color seleccionado para el relleno
    private Grafico figuraSeleccionada; // Mantenemos una referencia a la última figura generada
    private JButton btnLimpiar;
    
    public Main() {
        super("Main");
        canvas = new Panel(300, 150, 3);

        btnColorBorde = new JButton("Color Borde");
        btnColorRelleno = new JButton("Color Relleno");
        btnGenerarTriangulo = new JButton("Generar Triángulo");
        btnGenerarCircunferencia = new JButton("Generar Circunferencia");

        btnGenerarTriangulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                figuraSeleccionada = new Triangulo(
                        new Punto(100, 20), new Punto(30, 100), new Punto(280, 20), canvas);
                aplicarColores(figuraSeleccionada);
            }
        });

        btnGenerarCircunferencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                figuraSeleccionada = new Circunferencia(new Punto(150, 75), 50, canvas, 1);
                aplicarColores(figuraSeleccionada);
            }
        });

        btnColorBorde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorBordeSeleccionado = elegirColor();
            }
        });

        btnColorRelleno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorRellenoSeleccionado = elegirColor();
            }
        });
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.limpiarLienzo();
            }
        });
        
        // Creamos un panel para organizar los botones en una cuadrícula de 2x2
         JPanel panelBotones = new JPanel(new GridLayout(2, 2));
        panelBotones.add(btnGenerarTriangulo);
        panelBotones.add(btnGenerarCircunferencia);
        panelBotones.add(btnColorBorde);
        panelBotones.add(btnColorRelleno);
        panelBotones.add(btnLimpiar);

        // Usamos un BorderLayout en el contenedor principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private XColor elegirColor() {
        java.awt.Color awtColor = JColorChooser.showDialog(this, "Seleccionar Color", java.awt.Color.WHITE);
        if (awtColor != null) {
            return new XColor(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
        } else {
            return new XColor(255, 255, 255);
        }
    }

    private void aplicarColores(Grafico figura) {
        if (colorBordeSeleccionado != null) {
            figura.setBordeColor(colorBordeSeleccionado);
        }
        if (colorRellenoSeleccionado != null) {
            figura.setRellenoColor(colorRellenoSeleccionado);
        }
        
        // Dibujar la figura con los colores seleccionados
        if (figura != null) {
            figura.dibujarContorno(1, new Estilo(4, 4), colorBordeSeleccionado, 200);
            figura.dibujarRelleno(colorRellenoSeleccionado, 200);
        }
    }

    public static void main(String[] args) {
        Main root = new Main();
    }
}
