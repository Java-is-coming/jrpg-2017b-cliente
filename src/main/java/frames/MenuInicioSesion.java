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

/**
 * Menu de inicio de sesion
 */
public class MenuInicioSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JTextField textField;
    private final JPasswordField passwordField;

    /**
     * Constructor del menu
     *
     * @param cliente
     *            cliente
     */
    public MenuInicioSesion(final Cliente cliente) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                synchronized (cliente) {
                    cliente.setAccion(Comando.SALIR);
                    cliente.notify();
                }
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }
        });

        setTitle("WOME - Iniciar Sesion");
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

        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        contentPane.add(layeredPane);

        final JLabel lblPassword = new JLabel("Password");
        final int xLblPwd = 111;
        final int yLblPwd = 118;
        final int widthLblPwd = 68;
        final int heightLblPwd = 21;
        lblPassword.setBounds(xLblPwd, yLblPwd, widthLblPwd, heightLblPwd);
        layeredPane.add(lblPassword);
        final int fontSize = 15;
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        lblPassword.setForeground(Color.WHITE);

        final JLabel lblNewLabel = new JLabel("Usuario");
        final int yLblUsr = 66;
        final int widthLblUsr = 55;
        final int heightLblUsr = 23;
        lblNewLabel.setBounds(xLblPwd, yLblUsr, widthLblUsr, heightLblUsr);
        layeredPane.add(lblNewLabel, new Integer(2));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        final JLabel lblIngresar = new JLabel("Ingresar");
        final int xLblIngresar = 193;
        final int yLblIngresar = 183;
        final int widthLblIngresar = 68;
        final int heightLblIngresar = 23;
        lblIngresar.setBounds(xLblIngresar, yLblIngresar, widthLblIngresar, heightLblIngresar);
        layeredPane.add(lblIngresar, new Integer(2));
        lblIngresar.setForeground(Color.WHITE);
        lblIngresar.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                logIn(cliente);
            }
        });
        final int xTxtField = 198;
        final int yTxtField = 69;
        final int widthTxtField = 118;
        final int heightTxtField = 20;
        textField.setBounds(xTxtField, yTxtField, widthTxtField, heightTxtField);
        layeredPane.add(textField, new Integer(1));
        final int columns = 10;
        textField.setColumns(columns);

        passwordField = new JPasswordField();
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                logIn(cliente);
            }
        });
        final int xPwdField = 198;
        final int yPwdField = 119;
        final int widthPwdField = 118;
        final int heightPwdField = 20;
        passwordField.setBounds(xPwdField, yPwdField, widthPwdField, heightPwdField);
        layeredPane.add(passwordField, new Integer(1));
        final int fontSizePwd = 11;
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, fontSizePwd));

        final JButton btnConectar = new JButton("");
        final int xBtnConectar = 141;
        final int yBtnConectar = 182;
        final int widthBtnConectar = 153;
        final int heightBtnConectar = 23;
        btnConectar.setBounds(xBtnConectar, yBtnConectar, widthBtnConectar, heightBtnConectar);
        layeredPane.add(btnConectar, new Integer(1));
        btnConectar.setFocusable(false);
        btnConectar.setIcon(new ImageIcon(MenuInicioSesion.class.getResource("/frames/BotonMenu.png")));
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                logIn(cliente);

            }
        });

        final JLabel labelBackground = new JLabel("");
        final int heightLblBkg = 271;
        labelBackground.setBounds(0, 0, widthPane, heightLblBkg);
        labelBackground.setIcon(new ImageIcon(MenuInicioSesion.class.getResource("/frames/menuBackground.jpg")));
        layeredPane.add(labelBackground, new Integer(0));
    }

    /**
     * Login
     *
     * @param cliente
     *            cliente
     */
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
