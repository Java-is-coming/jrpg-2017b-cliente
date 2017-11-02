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

public class MenuStats extends JFrame {

    private final JPanel contentPane;
    private final PaquetePersonaje paquetePersonaje;
    private final double mod = 1.5;

    /**
     * Create the frame.
     */
    public MenuStats(final Cliente cliente) {
        paquetePersonaje = cliente.getPaquetePersonaje();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 100, 346, 321);
        this.setLocationRelativeTo(null);
        this.setTitle("Estadísticas");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
        lblNombre.setBounds(12, 13, 56, 16);
        contentPane.add(lblNombre);

        final JLabel lblCasta = new JLabel("Casta");
        lblCasta.setForeground(Color.WHITE);
        lblCasta.setBounds(12, 42, 56, 16);
        contentPane.add(lblCasta);

        final JLabel lblRaza = new JLabel("Raza");
        lblRaza.setForeground(Color.WHITE);
        lblRaza.setBounds(12, 71, 56, 16);
        contentPane.add(lblRaza);

        final JLabel lblNivel = new JLabel("Nivel");
        lblNivel.setForeground(Color.WHITE);
        lblNivel.setBounds(169, 13, 56, 16);
        contentPane.add(lblNivel);

        final JLabel lblExperiencia = new JLabel("Experiencia");
        lblExperiencia.setForeground(Color.WHITE);
        lblExperiencia.setBounds(169, 42, 72, 16);
        contentPane.add(lblExperiencia);

        final JLabel lblEnergia = new JLabel("Energia");
        lblEnergia.setForeground(Color.WHITE);
        lblEnergia.setBounds(169, 100, 48, 16);
        contentPane.add(lblEnergia);

        final JLabel lblSalud = new JLabel("Salud");
        lblSalud.setForeground(Color.WHITE);
        lblSalud.setBounds(12, 100, 56, 16);
        contentPane.add(lblSalud);

        final JLabel lblFuerza = new JLabel("Fuerza");
        lblFuerza.setForeground(Color.WHITE);
        lblFuerza.setBounds(12, 129, 48, 16);
        contentPane.add(lblFuerza);

        final JLabel lblDestreza = new JLabel("Destreza");
        lblDestreza.setForeground(Color.WHITE);
        lblDestreza.setBounds(12, 158, 56, 16);
        contentPane.add(lblDestreza);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        lblInteligencia.setForeground(Color.WHITE);
        lblInteligencia.setBounds(12, 187, 72, 16);
        contentPane.add(lblInteligencia);

        final JLabel lblAtaque = new JLabel("Ataque");
        lblAtaque.setForeground(Color.WHITE);
        lblAtaque.setBounds(169, 129, 48, 16);
        contentPane.add(lblAtaque);

        final JLabel lblDefensa = new JLabel("Defensa");
        lblDefensa.setForeground(Color.WHITE);
        lblDefensa.setBounds(169, 158, 56, 16);
        contentPane.add(lblDefensa);

        final JLabel lblMagia = new JLabel("Magia");
        lblMagia.setForeground(Color.WHITE);
        lblMagia.setBounds(169, 187, 39, 16);
        contentPane.add(lblMagia);

        final JLabel lblCantidadDeItems = new JLabel("Cantidad de Items");
        lblCantidadDeItems.setForeground(Color.WHITE);
        lblCantidadDeItems.setBounds(12, 216, 110, 16);
        contentPane.add(lblCantidadDeItems);

        final JLabel nmbPj = new JLabel(paquetePersonaje.getNombre());
        nmbPj.setForeground(Color.WHITE);
        nmbPj.setHorizontalAlignment(SwingConstants.RIGHT);
        nmbPj.setBounds(80, 13, 77, 16);
        contentPane.add(nmbPj);

        final JLabel cstPj = new JLabel(paquetePersonaje.getCasta());
        cstPj.setForeground(Color.WHITE);
        cstPj.setHorizontalAlignment(SwingConstants.RIGHT);
        cstPj.setBounds(80, 42, 77, 16);
        contentPane.add(cstPj);

        final JLabel rzPj = new JLabel(paquetePersonaje.getRaza());
        rzPj.setForeground(Color.WHITE);
        rzPj.setHorizontalAlignment(SwingConstants.RIGHT);
        rzPj.setBounds(80, 71, 77, 16);
        contentPane.add(rzPj);

        final JLabel saludPj = new JLabel(String.valueOf(paquetePersonaje.getSaludTope()));
        saludPj.setForeground(Color.WHITE);
        saludPj.setHorizontalAlignment(SwingConstants.RIGHT);
        saludPj.setBounds(80, 100, 77, 16);
        contentPane.add(saludPj);

        final JLabel fzaPj = new JLabel(String.valueOf(paquetePersonaje.getFuerza()));
        fzaPj.setForeground(Color.WHITE);
        fzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        fzaPj.setBounds(80, 129, 77, 16);
        contentPane.add(fzaPj);

        final JLabel dstzaPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
        dstzaPj.setForeground(Color.WHITE);
        dstzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        dstzaPj.setBounds(80, 158, 77, 16);
        contentPane.add(dstzaPj);

        final JLabel intPj = new JLabel(String.valueOf(paquetePersonaje.getInteligencia()));
        intPj.setForeground(Color.WHITE);
        intPj.setHorizontalAlignment(SwingConstants.RIGHT);
        intPj.setBounds(80, 187, 77, 16);
        contentPane.add(intPj);

        final JLabel cantItem = new JLabel(String.valueOf(paquetePersonaje.getCantItems()));
        cantItem.setForeground(Color.WHITE);
        cantItem.setHorizontalAlignment(SwingConstants.RIGHT);
        cantItem.setBounds(118, 216, 39, 16);
        contentPane.add(cantItem);

        final JLabel lvPj = new JLabel(String.valueOf(paquetePersonaje.getNivel()));
        lvPj.setForeground(Color.WHITE);
        lvPj.setHorizontalAlignment(SwingConstants.RIGHT);
        lvPj.setBounds(251, 13, 77, 16);
        contentPane.add(lvPj);

        final JLabel xpPj = new JLabel(String.valueOf(paquetePersonaje.getExperiencia()));
        xpPj.setForeground(Color.WHITE);
        xpPj.setHorizontalAlignment(SwingConstants.RIGHT);
        xpPj.setBounds(251, 42, 77, 16);
        contentPane.add(xpPj);

        final JLabel energiaPj = new JLabel(String.valueOf(paquetePersonaje.getEnergiaTope()));
        energiaPj.setForeground(Color.WHITE);
        energiaPj.setHorizontalAlignment(SwingConstants.RIGHT);
        energiaPj.setBounds(251, 100, 77, 16);
        contentPane.add(energiaPj);

        final int ataquePj = calcularAtaque(paquetePersonaje.getFuerza());
        final JLabel ataPj = new JLabel(String.valueOf(ataquePj));
        ataPj.setForeground(Color.WHITE);
        ataPj.setHorizontalAlignment(SwingConstants.RIGHT);
        ataPj.setBounds(251, 129, 77, 16);
        contentPane.add(ataPj);

        final JLabel defPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
        defPj.setForeground(Color.WHITE);
        defPj.setHorizontalAlignment(SwingConstants.RIGHT);
        defPj.setBounds(251, 158, 77, 16);
        contentPane.add(defPj);

        final int intePj = calcularMagia(paquetePersonaje.getInteligencia());
        final JLabel magicPj = new JLabel(String.valueOf(intePj));
        magicPj.setForeground(Color.WHITE);
        magicPj.setHorizontalAlignment(SwingConstants.RIGHT);
        magicPj.setBounds(251, 187, 77, 16);
        contentPane.add(magicPj);

        final JButton btnVolver = new JButton("Volver");
        btnVolver.setIcon(new ImageIcon("recursos//volver.png"));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pantalla.setMenuStats(null);
                dispose();
            }
        });
        btnVolver.setBounds(128, 245, 97, 25);
        contentPane.add(btnVolver);
        final JLabel background = new JLabel(
                new ImageIcon(imagenFondo.getScaledInstance(400, 350, Image.SCALE_DEFAULT)));
        background.setBounds(-12, -11, 363, 312);
        contentPane.add(background);
    }

    private int calcularMagia(final int inteligencia) {
        return (int) (inteligencia * mod);
    }

    private int calcularAtaque(final int fuerza) {
        return (int) (fuerza * mod);
    }
}
