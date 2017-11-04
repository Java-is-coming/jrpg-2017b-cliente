package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Cliente;
import mensajeria.Comando;
import mensajeria.PaquetePersonaje;

/**
 * Menu creacion personaje
 */
public class MenuCreacionPj extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JTextField nombre;
    private final JLabel destreza;
    private final JLabel fuerza;
    private final JLabel inteligencia;
    private final JLabel salud;
    private final JLabel energia;

    private final JComboBox<String> cbxCasta;
    private final JComboBox<String> cbxRaza;

    /**
     * Constructor menu
     *
     * @param cliente
     *            instancia cliente
     * @param personaje
     *            personaje a crear
     * @param gson
     *            paquete
     */
    public MenuCreacionPj(final Cliente cliente, final PaquetePersonaje personaje, final Gson gson) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        final String[] vecSalud = {"55", "50", "60"};
        final String[] vecEnergia = {"55", "60", "50"};
        final String[] vecFuerza = {"15", "10", "10"};
        final String[] vecDestreza = {"10", "10", "15"};
        final String[] vecInteligencia = {"10", "15", "10"};

        // En caso de cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                personaje.setNombre(nombre.getText());
                if (nombre.getText().equals("")) {
                    personaje.setNombre("nameless");
                }
                personaje.setRaza((String) cbxRaza.getSelectedItem());
                personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
                personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
                personaje.setCasta((String) cbxCasta.getSelectedItem());
                personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
                personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
                personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
                synchronized (cliente) {
                    cliente.notify();
                }
                dispose();
            }
        });

        setTitle("WOME - Crear personaje");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        final int bounds = 100;
        final int width450 = 450;
        final int height300 = 300;
        setBounds(bounds, bounds, width450, height300);
        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        final JLayeredPane layeredPane = new JLayeredPane();
        final int widthPane = 444;
        final int heightPane = 271;
        layeredPane.setBounds(0, 0, widthPane, heightPane);
        contentPane.add(layeredPane);

        final JLabel lblFuerza = new JLabel("Fuerza");
        final int xLblFza = 33;
        final int widthLblFza = 46;
        final int heightLblFza = 14;
        lblFuerza.setBounds(xLblFza, bounds, widthLblFza, heightLblFza);
        layeredPane.add(lblFuerza, new Integer(1));
        lblFuerza.setForeground(Color.WHITE);
        final int fontSize = 13;
        lblFuerza.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        fuerza = new JLabel("15");
        final int xFza = 110;
        final int yFza = 102;
        final int heightFza = 22;
        final int widthFza = 14;
        fuerza.setBounds(xFza, yFza, heightFza, widthFza);
        layeredPane.add(fuerza, new Integer(1));
        fuerza.setForeground(Color.GREEN);

        final JLabel lblDestreza = new JLabel("Destreza");
        final int xLblDestreza = 33;
        final int yLblDestreza = 126;
        final int widthLblDestreza = 60;
        final int heightLblDestreza = 14;
        lblDestreza.setBounds(xLblDestreza, yLblDestreza, widthLblDestreza, heightLblDestreza);
        layeredPane.add(lblDestreza, new Integer(1));
        lblDestreza.setForeground(Color.WHITE);
        lblDestreza.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        destreza = new JLabel("10");
        final int xDestreza = 110;
        final int yDestreza = 127;
        final int widthDestreza = 22;
        final int heightDestreza = 14;
        destreza.setBounds(xDestreza, yDestreza, widthDestreza, heightDestreza);
        layeredPane.add(destreza, new Integer(1));
        destreza.setForeground(Color.GREEN);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        final int xLblInt = 33;
        final int yLblInt = 151;
        final int widthLblInt = 66;
        final int heightLblInt = 22;
        lblInteligencia.setBounds(xLblInt, yLblInt, widthLblInt, heightLblInt);
        layeredPane.add(lblInteligencia, new Integer(1));
        lblInteligencia.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        lblInteligencia.setForeground(Color.WHITE);

        inteligencia = new JLabel("10");
        final int xInt = 110;
        final int yInt = 156;
        final int widthInt = 22;
        final int heightInt = 14;
        inteligencia.setBounds(xInt, yInt, widthInt, heightInt);
        layeredPane.add(inteligencia, new Integer(1));
        inteligencia.setForeground(Color.GREEN);

        final JLabel lblSalud = new JLabel("Salud");
        final int xLblSalud = 33;
        final int yLblSalud = 183;
        final int widthLblSalud = 46;
        final int heightLblSalud = 14;
        lblSalud.setBounds(xLblSalud, yLblSalud, widthLblSalud, heightLblSalud);
        layeredPane.add(lblSalud, new Integer(1));
        lblSalud.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        lblSalud.setForeground(Color.WHITE);

        salud = new JLabel("55");
        final int xSalud = 110;
        final int ySalud = 183;
        final int widthSalud = 22;
        final int heightSalud = 14;
        salud.setBounds(xSalud, ySalud, widthSalud, heightSalud);
        layeredPane.add(salud, new Integer(1));
        salud.setForeground(Color.GREEN);

        final JLabel lblEnergia = new JLabel("Energia");
        final int xLblEng = 33;
        final int yLblEng = 204;
        final int widthLblEng = 46;
        final int heightLblEng = 20;
        lblEnergia.setBounds(xLblEng, yLblEng, widthLblEng, heightLblEng);
        layeredPane.add(lblEnergia, new Integer(1));
        lblEnergia.setForeground(Color.WHITE);
        lblEnergia.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        energia = new JLabel("55");
        final int xEng = 110;
        final int yEng = 208;
        final int widthEng = 22;
        final int heightEng = 14;
        energia.setBounds(xEng, yEng, widthEng, heightEng);
        layeredPane.add(energia, new Integer(1));
        energia.setForeground(Color.GREEN);

        final JLabel lblNombre = new JLabel("Nombre");
        final int xLblNombre = 207;
        final int yLblNombre = 125;
        final int widthLblNombre = 60;
        final int heightLblNombre = 14;
        lblNombre.setBounds(xLblNombre, yLblNombre, widthLblNombre, heightLblNombre);
        layeredPane.add(lblNombre, new Integer(1));
        lblNombre.setForeground(Color.WHITE);
        final int fontSize1 = 15;
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, fontSize1));

        nombre = new JTextField();
        nombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                crearPj(cliente, personaje, gson, vecSalud, vecEnergia, vecFuerza, vecDestreza, vecInteligencia);
            }
        });
        final int xNombre = 277;
        final int yNombre = 122;
        final int widthNombre = 122;
        final int heightNombre = 20;
        nombre.setBounds(xNombre, yNombre, widthNombre, heightNombre);
        layeredPane.add(nombre, new Integer(1));
        final int columns = 10;
        nombre.setColumns(columns);

        final JLabel lblAceptar = new JLabel("Aceptar");
        final int xLblAceptar = 280;
        final int yLblAceptar = 173;
        final int widthLblAceptar = 50;
        final int heightLblAceptar = 24;
        lblAceptar.setBounds(xLblAceptar, yLblAceptar, widthLblAceptar, heightLblAceptar);
        layeredPane.add(lblAceptar, new Integer(2));
        lblAceptar.setForeground(Color.WHITE);
        lblAceptar.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        // En caso de apretar el boton aceptar
        final JButton btnAceptar = new JButton("Aceptar");
        final int xBtnAceptar = 230;
        final int yBtnAceptar = 174;
        final int widthBtnAceptar = 153;
        final int heightBtnAceptar = 23;
        btnAceptar.setBounds(xBtnAceptar, yBtnAceptar, widthBtnAceptar, heightBtnAceptar);
        layeredPane.add(btnAceptar, new Integer(1));
        btnAceptar.setFocusable(false);
        btnAceptar.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/BotonMenu.png")));

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                crearPj(cliente, personaje, gson, vecSalud, vecEnergia, vecFuerza, vecDestreza, vecInteligencia);

            }

        });

        final JLabel lblRaza = new JLabel("Raza");
        final int xLblRaza = 33;
        final int yLblRaza = 23;
        final int widthLblRaza = 46;
        final int heightLblRaza = 14;
        lblRaza.setBounds(xLblRaza, yLblRaza, widthLblRaza, heightLblRaza);
        layeredPane.add(lblRaza, new Integer(1));
        lblRaza.setForeground(Color.WHITE);
        lblRaza.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        final JLabel lblCasta = new JLabel("Casta");
        final int xLblCasta = 161;
        final int yLblCasta = 23;
        final int widthLblCasata = 46;
        final int heightLblCasta = 14;
        lblCasta.setBounds(xLblCasta, yLblCasta, widthLblCasata, heightLblCasta);
        layeredPane.add(lblCasta, new Integer(1));
        lblCasta.setForeground(Color.WHITE);
        lblCasta.setFont(new Font("Tahoma", Font.PLAIN, fontSize));

        cbxCasta = new JComboBox<>();
        final int xCombo = 161;
        final int yCombo = 48;
        final int widthCombo = 76;
        final int heightCombo = 20;
        cbxCasta.setBounds(xCombo, yCombo, widthCombo, heightCombo);
        layeredPane.add(cbxCasta, new Integer(1));
        cbxCasta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                fuerza.setText(vecFuerza[cbxCasta.getSelectedIndex()]);
                destreza.setText(vecDestreza[cbxCasta.getSelectedIndex()]);
                inteligencia.setText(vecInteligencia[cbxCasta.getSelectedIndex()]);
            }
        });
        cbxCasta.addItem("Guerrero");
        cbxCasta.addItem("Hechicero");
        cbxCasta.addItem("Asesino");

        cbxRaza = new JComboBox<>();
        final int xCmbRaza = 32;
        final int yCmbRaza = 48;
        final int widthCmbRaza = 76;
        final int heightCmbRaza = 20;
        cbxRaza.setBounds(xCmbRaza, yCmbRaza, widthCmbRaza, heightCmbRaza);
        layeredPane.add(cbxRaza, new Integer(1));
        cbxRaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                salud.setText(vecSalud[cbxRaza.getSelectedIndex()]);
                energia.setText(vecEnergia[cbxRaza.getSelectedIndex()]);
            }
        });
        cbxRaza.addItem("Humano");
        cbxRaza.addItem("Elfo");
        cbxRaza.addItem("Orco");

        final JLabel lblBackground = new JLabel("");
        final int widthBkg = 444;
        final int heightBkg = 271;
        lblBackground.setBounds(0, 0, widthBkg, heightBkg);
        layeredPane.add(lblBackground, new Integer(0));
        lblBackground.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/menuBackground.jpg")));
    }

    /**
     * Crea personaje
     *
     * @param cliente
     *            instancia
     * @param personaje
     *            a crear
     * @param gson
     *            info
     * @param vecSalud
     *            salud
     * @param vecEnergia
     *            energia
     * @param vecFuerza
     *            fuerza
     * @param vecDestreza
     *            destreza
     * @param vecInteligencia
     *            inteligencia
     */
    protected void crearPj(final Cliente cliente, final PaquetePersonaje personaje, final Gson gson,
            final String[] vecSalud, final String[] vecEnergia, final String[] vecFuerza, final String[] vecDestreza,
            final String[] vecInteligencia) {

        personaje.setNombre(nombre.getText());
        if (nombre.getText().equals("")) {
            personaje.setNombre("nameless");
        }
        personaje.setRaza((String) cbxRaza.getSelectedItem());
        personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
        personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
        personaje.setCasta((String) cbxCasta.getSelectedItem());
        personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
        personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
        personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
        try {

            // Le envio los datos al servidor
            cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
            cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
            dispose();
        } catch (JsonSyntaxException | IOException esd) {
            JOptionPane.showMessageDialog(null, "Error al crear personaje");

        }
    }

}
