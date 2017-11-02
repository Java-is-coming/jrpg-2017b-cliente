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

public class MenuJugar extends JFrame {

    private final JPanel contentPane;

    public MenuJugar(final Cliente cliente) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
            public void windowClosing(WindowEvent e) {
                synchronized (cliente) {
                    cliente.setAccion(Comando.SALIR);
                    cliente.notify();
                }
                dispose();
            }
        });

        // Propiedades de la ventana
        setTitle("WOME - World Of the Middle Earth");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 444, 271);
        contentPane.add(layeredPane);

        // Boton Registrarse
        final JLabel lblRegistrarse = new JLabel("Registrarse");
        lblRegistrarse.setBounds(181, 162, 82, 23);
        layeredPane.add(lblRegistrarse, new Integer(2));
        lblRegistrarse.setForeground(Color.WHITE);
        lblRegistrarse.setEnabled(true);
        lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRegistrarse.setBackground(Color.WHITE);

        // Boton Iniciar sesion
        final JLabel lblIniciarSesion = new JLabel("Iniciar Sesion");
        lblIniciarSesion.setBounds(175, 91, 91, 23);
        layeredPane.add(lblIniciarSesion, new Integer(2));
        lblIniciarSesion.setForeground(Color.WHITE);
        lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));

        final JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(121, 162, 191, 23);
        layeredPane.add(btnRegistrar, new Integer(1));
        btnRegistrar.setFocusable(false);
        btnRegistrar.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final MenuRegistro menuRegistro = new MenuRegistro(cliente);
                menuRegistro.setVisible(true);
                dispose();
            }
        });

        final JButton btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.setBounds(121, 92, 191, 23);
        layeredPane.add(btnIniciarSesion, new Integer(1));
        btnIniciarSesion.setFocusable(false);
        btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
                menuInicioSesion.setVisible(true);
                dispose();
            }
        });

        final JLabel lblBackground = new JLabel("");
        lblBackground.setBounds(0, 0, 444, 271);
        lblBackground.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/menuBackground.jpg")));
        layeredPane.add(lblBackground, new Integer(0));
    }
}
