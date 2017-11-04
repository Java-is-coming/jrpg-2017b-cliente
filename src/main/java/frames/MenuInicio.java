package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

/**
 * Menu inicio
 */
public class MenuInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;

    /**
     * Constructor
     */
    public MenuInicio() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final Cliente cliente = new Cliente();
                    cliente.start();
                    dispose();
                }
            }
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Propiedades de la ventana
        setTitle("WOME - World Of the Middle Earth");
        final int bounds = 100;
        final int widthVentana = 450;
        final int heightVentana = 300;
        setBounds(bounds, bounds, widthVentana, heightVentana);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
        final int xLblLogo = 109;
        final int yLblLogo = 39;
        final int widthLblLogo = 216;
        final int heightLblLogo = 90;
        lblLogo.setBounds(xLblLogo, yLblLogo, widthLblLogo, heightLblLogo);
        contentPane.add(lblLogo);

        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        contentPane.add(layeredPane);

        // Boton Jugar
        final JLabel lblRegistrarse = new JLabel("Jugar");
        final int xJugar = 205;
        final int yJugar = 162;
        final int widthJugar = 82;
        final int heightJugar = 23;
        lblRegistrarse.setBounds(xJugar, yJugar, widthJugar, heightJugar);
        layeredPane.add(lblRegistrarse, new Integer(2));
        lblRegistrarse.setForeground(Color.WHITE);
        lblRegistrarse.setEnabled(true);
        final int fontSize = 15;
        lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        lblRegistrarse.setBackground(Color.WHITE);

        // Boton Salir
        final JLabel lblIniciarSesion = new JLabel("Salir");
        final int xSalir = 210;
        final int ySalir = 202;
        final int widthSalir = 91;
        final int heightSalir = 23;
        lblIniciarSesion.setBounds(xSalir, ySalir, widthSalir, heightSalir);
        layeredPane.add(lblIniciarSesion, new Integer(2));
        lblIniciarSesion.setForeground(Color.WHITE);
        lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        final JButton btnRegistrar = new JButton("Jugar");
        final int xBtnJugar = 127;
        final int yBtnJugar = 162;
        final int widthBtnJugar = 191;
        final int heightBtnJugar = 23;
        btnRegistrar.setBounds(xBtnJugar, yBtnJugar, widthBtnJugar, heightBtnJugar);
        layeredPane.add(btnRegistrar, new Integer(1));
        btnRegistrar.setFocusable(false);
        btnRegistrar.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Cliente cliente = new Cliente();
                cliente.start();
                dispose();
            }
        });

        final JButton btnIniciarSesion = new JButton("Salir");
        final int xBtnSalir = 127;
        final int yBtnSalir = 202;
        final int widthBtnSalir = 191;
        final int heightBtnSalir = 23;
        btnIniciarSesion.setBounds(xBtnSalir, yBtnSalir, widthBtnSalir, heightBtnSalir);
        layeredPane.add(btnIniciarSesion, new Integer(1));
        btnIniciarSesion.setFocusable(false);
        btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        final JLabel lblBackground = new JLabel("");

        final int widthBkg = 444;
        final int heightBkg = 271;
        lblBackground.setBounds(0, 0, widthBkg, heightBkg);
        lblBackground.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/menuBackground.jpg")));
        layeredPane.add(lblBackground, new Integer(0));
    }

    /**
     * Main menu inicio
     *
     * @param args
     *            argumentos main
     */
    public static void main(final String[] args) {
        new MenuInicio().setVisible(true);
    }

}
