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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Menu de mapas
 */
public class MenuMapas extends JFrame {

    private static final long serialVersionUID = 1L;
    private static int numberMap = 0;
    private final JPanel contentPane;

    /**
     * Constructor menu
     *
     * @param cliente
     *            cliente
     */
    public MenuMapas(final Cliente cliente) {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    synchronized (cliente) {
                        cliente.getPaquetePersonaje().setMapa(1);
                        setNumberMap(1);
                        cliente.notify();
                    }
                    dispose();
                }
            }
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));
        setTitle("Elegir Mapa");
        final int xVentana = 100;
        final int yVentana = 100;
        final int widthVentana = 450;
        final int heightVentana = 300;
        setBounds(xVentana, yVentana, widthVentana, heightVentana);
        // En caso de cerrar
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
        // Panel
        setTitle("WOME - Elegir Mapa");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(xVentana, yVentana, widthVentana, heightVentana);
        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        contentPane.add(layeredPane);
        // Mapa Aris
        final JLabel lblAris = new JLabel("Aris");
        final int xAris = 204;
        final int yAris = 129;
        final int widthAris = 32;
        final int heightAris = 23;
        lblAris.setBounds(xAris, yAris, widthAris, heightAris);
        layeredPane.add(lblAris, new Integer(2));
        lblAris.setForeground(Color.WHITE);
        final int fontSize = 15;
        lblAris.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        // Mapa Aubenor
        final JLabel lblAubenor = new JLabel("Aubenor");
        final int xAubenor = 191;
        final int yAubenor = 72;
        final int widthAubenor = 66;
        lblAubenor.setBounds(xAubenor, yAubenor, widthAubenor, heightAris);
        layeredPane.add(lblAubenor, new Integer(2));
        lblAubenor.setForeground(Color.WHITE);
        lblAubenor.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        // Mapa Eodrim
        final JLabel lblEodrim = new JLabel("Eodrim");
        final int xEodrim = 198;
        final int yEodrim = 192;
        final int widthEodrim = 53;
        lblEodrim.setBounds(xEodrim, yEodrim, widthEodrim, heightAris);
        layeredPane.add(lblEodrim, new Integer(2));
        lblEodrim.setForeground(Color.WHITE);
        lblEodrim.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        final JButton btnAubenor = new JButton("");
        final int xBtnAubenor = 148;
        final int yBtnAubenor = 72;
        final int widthBtnAubenor = 143;
        btnAubenor.setBounds(xBtnAubenor, yBtnAubenor, widthBtnAubenor, heightAris);
        layeredPane.add(btnAubenor, new Integer(1));
        btnAubenor.setFocusable(false);
        btnAubenor.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));
        final JButton btnEodrim = new JButton("");
        final int xBtnEodrim = 148;
        final int yBtnEodrim = 192;
        final int widthBtnEodrim = 143;
        btnEodrim.setBounds(xBtnEodrim, yBtnEodrim, widthBtnEodrim, heightAris);
        layeredPane.add(btnEodrim, new Integer(1));
        btnEodrim.setFocusable(false);
        btnEodrim.setEnabled(false);
        btnEodrim.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));
        btnEodrim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                synchronized (cliente) {
                    final int mapaEodrim = 3;
                    cliente.getPaquetePersonaje().setMapa(mapaEodrim);
                    cliente.notify();
                }
                dispose();
            }
        });
        btnEodrim.setEnabled(false);
        final JButton btnAris = new JButton("");
        final int xBtnAris = 148;
        final int yBtnAris = 130;
        final int widthBtnAris = 143;
        btnAris.setBounds(xBtnAris, yBtnAris, widthBtnAris, heightAris);
        layeredPane.add(btnAris, new Integer(1));
        btnAris.setFocusable(false);
        btnAris.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));
        btnAris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                synchronized (cliente) {
                    cliente.getPaquetePersonaje().setMapa(2);
                    setNumberMap(2);
                    cliente.notify();
                }
                dispose();
            }
        });
        btnAris.setEnabled(true);

        final JLabel lblBackground = new JLabel("");
        final int widthLblBkg = 444;
        lblBackground.setBounds(0, 0, widthLblBkg, heightPane);
        layeredPane.add(lblBackground, new Integer(0));
        lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
        btnAubenor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                synchronized (cliente) {
                    cliente.getPaquetePersonaje().setMapa(1);
                    setNumberMap(1);
                    cliente.notify();
                }
                dispose();
            }
        });
    }

    /**
     * Getter num map
     *
     * @return numero de mapa
     */
    public static int getNumberMap() {
        return numberMap;
    }

    /**
     * Setter numero de mapa
     *
     * @param numberMap
     *            numero de mapa
     */
    public static void setNumberMap(final int numberMap) {
        MenuMapas.numberMap = numberMap;
    }
}
