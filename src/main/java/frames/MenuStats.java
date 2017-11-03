package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;

/**
 * Menu estadisticas
 */
public class MenuStats extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final PaquetePersonaje paquetePersonaje;
    private final double mod = 1.5;

    /**
     * Constructor
     *
     * @param cliente
     *            cliente
     */
    public MenuStats(final Cliente cliente) {
        paquetePersonaje = cliente.getPaquetePersonaje();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        final int coords = 100;
        final int width = 346;
        final int height = 321;
        this.setBounds(coords, coords, width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("Estadísticas");

        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                Pantalla.setMenuStats(null);
                dispose();
            }
        });

        BufferedImage imagenFondo = null;
        try {
            imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

        }

        final JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setForeground(Color.WHITE);
        final int boundX = 12;
        final int boundY = 13;
        final int width1 = 56;
        final int height1 = 16;
        lblNombre.setBounds(boundX, boundY, width1, height1);
        contentPane.add(lblNombre);

        final JLabel lblCasta = new JLabel("Casta");
        lblCasta.setForeground(Color.WHITE);
        final int boundY1 = 42;
        lblCasta.setBounds(boundX, boundY1, width1, height1);
        contentPane.add(lblCasta);

        final JLabel lblRaza = new JLabel("Raza");
        lblRaza.setForeground(Color.WHITE);
        final int boundY2 = 71;
        lblRaza.setBounds(boundX, boundY2, width1, height1);
        contentPane.add(lblRaza);

        final JLabel lblNivel = new JLabel("Nivel");
        lblNivel.setForeground(Color.WHITE);
        final int boundX1 = 169;
        lblNivel.setBounds(boundX1, boundY, width1, height1);
        contentPane.add(lblNivel);

        final JLabel lblExperiencia = new JLabel("Experiencia");
        lblExperiencia.setForeground(Color.WHITE);
        final int width2 = 72;
        lblExperiencia.setBounds(boundX1, boundY1, width2, height1);
        contentPane.add(lblExperiencia);

        final JLabel lblEnergia = new JLabel("Energia");
        lblEnergia.setForeground(Color.WHITE);
        final int width3 = 48;
        lblEnergia.setBounds(boundX1, coords, width3, height1);
        contentPane.add(lblEnergia);

        final JLabel lblSalud = new JLabel("Salud");
        lblSalud.setForeground(Color.WHITE);
        lblSalud.setBounds(boundX, coords, width1, height1);
        contentPane.add(lblSalud);

        final JLabel lblFuerza = new JLabel("Fuerza");
        lblFuerza.setForeground(Color.WHITE);
        final int boundY3 = 129;
        lblFuerza.setBounds(boundX, boundY3, width3, height1);
        contentPane.add(lblFuerza);

        final JLabel lblDestreza = new JLabel("Destreza");
        lblDestreza.setForeground(Color.WHITE);
        final int boundY4 = 158;
        lblDestreza.setBounds(boundX, boundY4, width1, height1);
        contentPane.add(lblDestreza);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        lblInteligencia.setForeground(Color.WHITE);
        final int boundY5 = 187;
        lblInteligencia.setBounds(boundX, boundY5, width2, height1);
        contentPane.add(lblInteligencia);

        final JLabel lblAtaque = new JLabel("Ataque");
        lblAtaque.setForeground(Color.WHITE);
        lblAtaque.setBounds(boundX1, boundY3, width3, height1);
        contentPane.add(lblAtaque);

        final JLabel lblDefensa = new JLabel("Defensa");
        lblDefensa.setForeground(Color.WHITE);
        lblDefensa.setBounds(boundX1, boundY4, width1, height1);
        contentPane.add(lblDefensa);

        final JLabel lblMagia = new JLabel("Magia");
        lblMagia.setForeground(Color.WHITE);
        final int width4 = 39;
        lblMagia.setBounds(boundX1, boundY5, width4, height1);
        contentPane.add(lblMagia);

        final JLabel lblCantidadDeItems = new JLabel("Cantidad de Items");
        lblCantidadDeItems.setForeground(Color.WHITE);
        final int boundY6 = 216;
        final int width5 = 110;
        lblCantidadDeItems.setBounds(boundX, boundY6, width5, height1);
        contentPane.add(lblCantidadDeItems);

        final JLabel nmbPj = new JLabel(paquetePersonaje.getNombre());
        nmbPj.setForeground(Color.WHITE);
        nmbPj.setHorizontalAlignment(SwingConstants.RIGHT);
        final int boundX2 = 80;
        final int width6 = 77;
        nmbPj.setBounds(boundX2, boundY, width6, height1);
        contentPane.add(nmbPj);

        final JLabel cstPj = new JLabel(paquetePersonaje.getCasta());
        cstPj.setForeground(Color.WHITE);
        cstPj.setHorizontalAlignment(SwingConstants.RIGHT);
        cstPj.setBounds(boundX2, boundY1, width6, height1);
        contentPane.add(cstPj);

        final JLabel rzPj = new JLabel(paquetePersonaje.getRaza());
        rzPj.setForeground(Color.WHITE);
        rzPj.setHorizontalAlignment(SwingConstants.RIGHT);
        rzPj.setBounds(boundX2, boundY2, width6, height1);
        contentPane.add(rzPj);

        final JLabel saludPj = new JLabel(String.valueOf(paquetePersonaje.getSaludTope()));
        saludPj.setForeground(Color.WHITE);
        saludPj.setHorizontalAlignment(SwingConstants.RIGHT);
        saludPj.setBounds(boundX2, coords, width6, height1);
        contentPane.add(saludPj);

        final JLabel fzaPj = new JLabel(String.valueOf(paquetePersonaje.getFuerza()));
        fzaPj.setForeground(Color.WHITE);
        fzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        fzaPj.setBounds(boundX2, boundY3, width6, height1);
        contentPane.add(fzaPj);

        final JLabel dstzaPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
        dstzaPj.setForeground(Color.WHITE);
        dstzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        dstzaPj.setBounds(boundX2, boundY4, width6, height1);
        contentPane.add(dstzaPj);

        final JLabel intPj = new JLabel(String.valueOf(paquetePersonaje.getInteligencia()));
        intPj.setForeground(Color.WHITE);
        intPj.setHorizontalAlignment(SwingConstants.RIGHT);
        intPj.setBounds(boundX2, boundY5, width6, height1);
        contentPane.add(intPj);

        final JLabel cantItem = new JLabel(String.valueOf(paquetePersonaje.getCantItems()));
        cantItem.setForeground(Color.WHITE);
        cantItem.setHorizontalAlignment(SwingConstants.RIGHT);
        final int boundX3 = 118;
        cantItem.setBounds(boundX3, boundY6, width4, height1);
        contentPane.add(cantItem);

        final JLabel lvPj = new JLabel(String.valueOf(paquetePersonaje.getNivel()));
        lvPj.setForeground(Color.WHITE);
        lvPj.setHorizontalAlignment(SwingConstants.RIGHT);
        final int boundX4 = 251;
        lvPj.setBounds(boundX4, boundY, width6, height1);
        contentPane.add(lvPj);

        final JLabel xpPj = new JLabel(String.valueOf(paquetePersonaje.getExperiencia()));
        xpPj.setForeground(Color.WHITE);
        xpPj.setHorizontalAlignment(SwingConstants.RIGHT);
        xpPj.setBounds(boundX4, boundY1, width6, height1);
        contentPane.add(xpPj);

        final JLabel energiaPj = new JLabel(String.valueOf(paquetePersonaje.getEnergiaTope()));
        energiaPj.setForeground(Color.WHITE);
        energiaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        energiaPj.setBounds(boundX4, coords, width6, height1);
        contentPane.add(energiaPj);

        final int ataquePj = calcularAtaque(paquetePersonaje.getFuerza());
        final JLabel ataPj = new JLabel(String.valueOf(ataquePj));
        ataPj.setForeground(Color.WHITE);
        ataPj.setHorizontalAlignment(SwingConstants.RIGHT);
        ataPj.setBounds(boundX4, boundY3, width6, height1);
        contentPane.add(ataPj);

        final JLabel defPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
        defPj.setForeground(Color.WHITE);
        defPj.setHorizontalAlignment(SwingConstants.RIGHT);
        defPj.setBounds(boundX4, boundY4, width6, height1);
        contentPane.add(defPj);

        final int intePj = calcularMagia(paquetePersonaje.getInteligencia());
        final JLabel magicPj = new JLabel(String.valueOf(intePj));
        magicPj.setForeground(Color.WHITE);
        magicPj.setHorizontalAlignment(SwingConstants.RIGHT);
        magicPj.setBounds(boundX4, boundY5, width6, height1);
        contentPane.add(magicPj);

        final JButton btnVolver = new JButton("Volver");
        btnVolver.setIcon(new ImageIcon("recursos//volver.png"));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Pantalla.setMenuStats(null);
                dispose();
            }
        });

        final int boundX5 = 128;
        final int boundY7 = 245;
        final int widthVolver = 97;
        final int heightVolver = 25;
        btnVolver.setBounds(boundX5, boundY7, widthVolver, heightVolver);
        contentPane.add(btnVolver);
        final int widthIcon = 400;
        final int heightIcon = 350;
        final JLabel background = new JLabel(
                new ImageIcon(imagenFondo.getScaledInstance(widthIcon, heightIcon, Image.SCALE_DEFAULT)));
        final int boundY8 = 11;
        final int widthBkg = 363;
        final int heightBkg = 312;
        background.setBounds(-boundX, -boundY8, widthBkg, heightBkg);
        contentPane.add(background);
    }

    /**
     * Calcular magia a partir de la inteligencia
     *
     * @param inteligencia
     *            int
     * @return int magia
     */
    private int calcularMagia(final int inteligencia) {
        return (int) (inteligencia * mod);
    }

    /**
     * Calcular ataque a partir de la fuerza
     *
     * @param fuerza
     *            int
     * @return int ataque
     */
    private int calcularAtaque(final int fuerza) {
        return (int) (fuerza * mod);
    }
}
