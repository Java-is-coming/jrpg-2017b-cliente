package frames;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Menu de carga
 */
public class MenuCarga extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JLabel barraCargando;

    /**
     * Constructor del menu de carga
     *
     * @param cliente
     *            instancia cliente
     */
    public MenuCarga(final Cliente cliente) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // En caso de cerrar la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                synchronized (cliente) {
                    cliente.setAccion(Comando.SALIR);
                    cliente.notify();
                }
                dispose();
            }
        });

        // Propiedades de la ventana
        setTitle("WOME - World Of the Middle Earth");
        final int bounds = 100;
        final int widthVentana = 450;
        final int heightVentana = 300;
        setBounds(bounds, bounds, widthVentana, heightVentana);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        final int borders = 5;
        contentPane.setBorder(new EmptyBorder(borders, borders, borders, borders));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        barraCargando = new JLabel("");
        barraCargando.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/Barra.png")));
        final int xBarra = 52;
        final int yBarra = 160;
        final int heightBarra = 27;
        barraCargando.setBounds(xBarra, yBarra, 0, heightBarra);
        contentPane.add(barraCargando);

        final JLabel lblBarraCarga = new JLabel("");
        lblBarraCarga.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/BarraCarga.png")));
        final int xLblBarra = 47;
        final int yLblBarra = 154;
        final int widthLblBarra = 355;
        final int heightLblBarra = 40;
        lblBarraCarga.setBounds(xLblBarra, yLblBarra, widthLblBarra, heightLblBarra);
        contentPane.add(lblBarraCarga);

        final JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
        final int xLblLogo = 109;
        final int yLblLogo = 39;
        final int widthLblLogo = 216;
        final int heightLblLogo = 90;
        lblLogo.setBounds(xLblLogo, yLblLogo, widthLblLogo, heightLblLogo);
        contentPane.add(lblLogo);

        final JLabel lblBackground = new JLabel("");
        final int widthLblBkg = 444;
        final int heightLblBkg = 271;
        lblBackground.setBounds(0, 0, widthLblBkg, heightLblBkg);
        contentPane.add(lblBackground);
        lblBackground.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/menuBackground.jpg")));
    }

    /**
     * Set de barra cargando
     *
     * @param ancho
     *            barra
     */
    public void setBarraCargando(final int ancho) {
        final int size = 27;
        barraCargando.setSize(ancho, size);
    }
}
