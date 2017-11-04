package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Menu del juego
 */
public class MenuJugar extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;

    /**
     * Constructor del menu
     *
     * @param cliente
     *            cliente
     */
    public MenuJugar(final Cliente cliente) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    final MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
                    menuInicioSesion.setVisible(true);
                    dispose();
                }
            }
        });
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
        final int boundsVentana = 100;
        final int widthVentana = 450;
        final int heightVentana = 300;
        setBounds(boundsVentana, boundsVentana, widthVentana, heightVentana);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        contentPane.add(layeredPane);

        // Boton Registrarse
        final JLabel lblRegistrarse = new JLabel("Registrarse");
        final int xRegistrarse = 181;
        final int yRegistrarse = 162;
        final int widthRegistrarse = 82;
        final int heightRegistrarse = 23;
        lblRegistrarse.setBounds(xRegistrarse, yRegistrarse, widthRegistrarse, heightRegistrarse);
        layeredPane.add(lblRegistrarse, new Integer(2));
        lblRegistrarse.setForeground(Color.WHITE);
        lblRegistrarse.setEnabled(true);
        final int fontSize = 15;
        lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        lblRegistrarse.setBackground(Color.WHITE);

        // Boton Iniciar sesion
        final JLabel lblIniciarSesion = new JLabel("Iniciar Sesion");
        final int xIniciar = 175;
        final int yIniciar = 91;
        final int widthIniciar = 91;
        final int heightIniciar = 23;
        lblIniciarSesion.setBounds(xIniciar, yIniciar, widthIniciar, heightIniciar);
        layeredPane.add(lblIniciarSesion, new Integer(2));
        lblIniciarSesion.setForeground(Color.WHITE);
        lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        final JButton btnRegistrar = new JButton("Registrarse");
        final int xBtnReg = 121;
        final int yBtnReg = 162;
        final int widthBtnReg = 191;
        final int heightBtnReg = 23;
        btnRegistrar.setBounds(xBtnReg, yBtnReg, widthBtnReg, heightBtnReg);
        layeredPane.add(btnRegistrar, new Integer(1));
        btnRegistrar.setFocusable(false);
        btnRegistrar.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final MenuRegistro menuRegistro = new MenuRegistro(cliente);
                menuRegistro.setVisible(true);
                dispose();
            }
        });

        final JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        final int xBtnIniciar = 121;
        final int yBtnIniciar = 92;
        final int widthBtnIniciar = 191;
        final int heightBtnIniciar = 23;
        btnIniciarSesion.setBounds(xBtnIniciar, yBtnIniciar, widthBtnIniciar, heightBtnIniciar);
        layeredPane.add(btnIniciarSesion, new Integer(1));
        btnIniciarSesion.setFocusable(false);
        btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
                menuInicioSesion.setVisible(true);
                dispose();
            }
        });

        final JLabel lblBackground = new JLabel("");
        final int widthLbl = 444;
        final int heightLbl = 271;
        lblBackground.setBounds(0, 0, widthLbl, heightLbl);
        lblBackground.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/menuBackground.jpg")));
        layeredPane.add(lblBackground, new Integer(0));
    }
}
