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
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Menu para el registro
 */
public class MenuRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsuario;
    private JPasswordField pwPassword;

    /**
     * Constructor
     *
     * @param cliente
     *            cliente
     */
    public MenuRegistro(final Cliente cliente) {
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
                dispose();
            }
        });

        setTitle("WOME - Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        final int boundsVentana = 100;
        final int widthVentana = 450;
        final int heightVentana = 300;
        setBounds(boundsVentana, boundsVentana, widthVentana, heightVentana);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        getContentPane().add(layeredPane);

        final JLabel lblUsuario = new JLabel("Usuario");
        final int xUsuario = 113;
        final int yUsuario = 70;
        final int widthUsuario = 57;
        final int heightUsuario = 19;
        lblUsuario.setBounds(xUsuario, yUsuario, widthUsuario, heightUsuario);
        layeredPane.add(lblUsuario, new Integer(1));
        lblUsuario.setForeground(Color.WHITE);
        final int fontSizeUsuario = 15;
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, fontSizeUsuario));

        final JLabel lblPassword = new JLabel("Password");
        final int xPwd = 113;
        final int yPwd = 121;
        final int widthPwd = 65;
        final int heightPwd = 17;
        lblPassword.setBounds(xPwd, yPwd, widthPwd, heightPwd);
        layeredPane.add(lblPassword, new Integer(1));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, fontSizeUsuario));

        final JLabel lblRegistrarse = new JLabel("Registrarse");
        final int xRegistrarse = 186;
        final int yRegistrarse = 182;
        final int widthRegistrarse = 82;
        final int heightRegistrarse = 23;
        lblRegistrarse.setBounds(xRegistrarse, yRegistrarse, widthRegistrarse, heightRegistrarse);
        layeredPane.add(lblRegistrarse, new Integer(2));
        lblRegistrarse.setForeground(Color.WHITE);
        lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, fontSizeUsuario));

        final JButton btnRegistrarse = new JButton("");
        final int xBtn = 143;
        final int yBtn = 182;
        final int widthBtn = 153;
        final int heightBtn = 23;
        btnRegistrarse.setBounds(xBtn, yBtn, widthBtn, heightBtn);
        layeredPane.add(btnRegistrarse, new Integer(1));
        btnRegistrarse.setFocusable(false);
        btnRegistrarse.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/BotonMenu.png")));

        pwPassword = new JPasswordField();
        pwPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                logIn(cliente);
                dispose();
            }
        });
        final int xBtnPwd = 199;
        final int yBtnPwd = 120;
        final int widthBtnPwd = 118;
        final int heightBtnPwd = 20;
        pwPassword.setBounds(xBtnPwd, yBtnPwd, widthBtnPwd, heightBtnPwd);
        layeredPane.add(pwPassword, new Integer(1));

        txtUsuario = new JTextField();
        txtUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                logIn(cliente);
                dispose();
            }
        });
        final int xTxUsr = 199;
        final int yTxtUsr = 69;
        final int widthTxtUsr = 118;
        final int heightTxtUsr = 20;
        txtUsuario.setBounds(xTxUsr, yTxtUsr, widthTxtUsr, heightTxtUsr);
        layeredPane.add(txtUsuario, new Integer(1));
        final int columns = 10;
        txtUsuario.setColumns(columns);

        final JLabel labelBackground = new JLabel("");
        final int widthLblBkg = 444;
        final int heightLblBkg = 271;
        labelBackground.setBounds(0, 0, widthLblBkg, heightLblBkg);
        layeredPane.add(labelBackground, new Integer(0));
        labelBackground.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                logIn(cliente);
                dispose();
            }
        });
    }

    /**
     * Getter txt usuario
     *
     * @return JTextField txt
     */
    public JTextField gettxtUsuario() {
        return txtUsuario;
    }

    /**
     * Setter txt usuario
     *
     * @param txtUser
     *            txt
     */
    public void settxtUsuario(final JTextField txtUser) {
        this.txtUsuario = txtUser;
    }

    /**
     * Getter password field
     *
     * @return JPasswordField password
     */
    public JPasswordField getPasswordField() {
        return pwPassword;
    }

    /**
     * Setter password field
     *
     * @param pwPasswd
     *            field
     */
    public void setPasswordField(final JPasswordField pwPasswd) {
        this.pwPassword = pwPasswd;
    }

    /**
     * Login
     *
     * @param cliente
     *            login
     */
    private void logIn(final Cliente cliente) {
        synchronized (cliente) {
            cliente.getPaqueteUsuario().setUsername(txtUsuario.getText());
            cliente.getPaqueteUsuario().setPassword(String.valueOf(pwPassword.getPassword()));
            cliente.setAccion(Comando.REGISTRO);
            cliente.notify();
        }
    }
}
