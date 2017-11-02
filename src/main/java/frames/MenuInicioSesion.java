package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

public class MenuInicioSesion extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JPasswordField passwordField;

    public MenuInicioSesion(final Cliente cliente) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (cliente) {
                    cliente.setAccion(Comando.SALIR);
                    cliente.notify();
                }
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }
        });

        setTitle("WOME - Iniciar Sesion");
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

        final JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setBounds(111, 118, 68, 21);
        layeredPane.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setForeground(Color.WHITE);

        final JLabel lblNewLabel = new JLabel("Usuario");
        lblNewLabel.setBounds(111, 66, 55, 23);
        layeredPane.add(lblNewLabel, new Integer(2));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        final JLabel lblIngresar = new JLabel("Ingresar");
        lblIngresar.setBounds(193, 183, 68, 23);
        layeredPane.add(lblIngresar, new Integer(2));
        lblIngresar.setForeground(Color.WHITE);
        lblIngresar.setFont(new Font("Tahoma", Font.PLAIN, 15));

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                logIn(cliente);
            }
        });
        textField.setBounds(198, 69, 118, 20);
        layeredPane.add(textField, new Integer(1));
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn(cliente);
            }
        });
        passwordField.setBounds(198, 119, 118, 20);
        layeredPane.add(passwordField, new Integer(1));
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 11));

        final JButton btnConectar = new JButton("");
        btnConectar.setBounds(141, 182, 153, 23);
        layeredPane.add(btnConectar, new Integer(1));
        btnConectar.setFocusable(false);
        btnConectar.setIcon(new ImageIcon(MenuInicioSesion.class.getResource("/frames/BotonMenu.png")));
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn(cliente);

            }
        });

        final JLabel labelBackground = new JLabel("");
        labelBackground.setBounds(0, 0, 444, 271);
        labelBackground.setIcon(new ImageIcon(MenuInicioSesion.class.getResource("/frames/menuBackground.jpg")));
        layeredPane.add(labelBackground, new Integer(0));
    }

    private void logIn(final Cliente cliente) {
        synchronized (cliente) {
            cliente.setAccion(Comando.INICIOSESION);
            cliente.getPaqueteUsuario().setUsername(textField.getText());
            cliente.getPaqueteUsuario().setPassword(String.valueOf(passwordField.getPassword()));
            cliente.notify();
            dispose();
        }
    }
}
