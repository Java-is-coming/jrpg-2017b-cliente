package frames;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Menu escape
 *
 * @author julia
 *
 */
public class MenuEscape extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final Gson gson = new Gson();

    /**
     * Crea el menu
     *
     * @param instancia
     *            de cliente
     */
    public MenuEscape(final Cliente cliente) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        final int bounds = 100;
        final int widthVentana = 180;
        final int heightVentana = 270;
        this.setBounds(bounds, bounds, widthVentana, heightVentana);
        this.setLocationRelativeTo(null);

        contentPane = new JPanel();
        final int borders = 5;
        contentPane.setBorder(new EmptyBorder(borders, borders, borders, borders));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JButton verStats = new JButton("Estadísticas");
        verStats.setIcon(new ImageIcon("recursos//stats.png"));
        verStats.setToolTipText("Presiona S para ver estadísticas");
        final int xStats = 29;
        final int yStats = 13;
        final int widthStats = 125;
        final int heightStats = 25;
        verStats.setBounds(xStats, yStats, widthStats, heightStats);
        verStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
                Pantalla.setMenuEscp(null);
                if (Pantalla.getMenuStats() == null) {
                    Pantalla.setMenuStats(new MenuStats(cliente));
                    Pantalla.getMenuStats().setVisible(true);
                }
            }
        });
        contentPane.add(verStats);

        final JButton asignarSkills = new JButton("Asignar Skills");
        asignarSkills.setIcon(new ImageIcon("recursos//asignar skills.png"));
        asignarSkills.setToolTipText("Presiona A para asignar skills");
        final int xAsignar = 29;
        final int yAsignar = 66;
        final int widthAsignar = 125;
        final int heightAsignar = 25;
        asignarSkills.setBounds(xAsignar, yAsignar, widthAsignar, heightAsignar);
        asignarSkills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
                Pantalla.setMenuEscp(null);
                if (Pantalla.getMenuAsignar() == null) {
                    Pantalla.setMenuAsignar(new MenuAsignarSkills(cliente));
                    Pantalla.getMenuAsignar().setVisible(true);
                }
            }
        });
        contentPane.add(asignarSkills);

        final JButton inventario = new JButton("Inventario");
        inventario.setIcon(new ImageIcon("recursos//inventario.png"));
        inventario.setToolTipText("Presiona I para abrir inventario");
        final int yInventario = 121;
        inventario.setBounds(xAsignar, yInventario, widthAsignar, heightAsignar);
        inventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
                Pantalla.setMenuEscp(null);
                if (Estado.getEstado().esEstadoDeJuego()) {
                    if (Pantalla.getMenuInventario() == null) {
                        Pantalla.setMenuInventario(new MenuInventario(cliente));
                        Pantalla.getMenuInventario().setVisible(true);
                    }
                }
            }
        });
        contentPane.add(inventario);

        final JButton desconectarse = new JButton("Desconectarse");
        final int xDesconectar = 29;
        final int yDesconectar = 175;
        desconectarse.setBounds(xDesconectar, yDesconectar, widthAsignar, heightAsignar);
        desconectarse.setIcon(new ImageIcon("recursos//desconectarse.png"));
        desconectarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final Paquete p = new Paquete();
                    p.setComando(Comando.DESCONECTAR);
                    p.setIp(cliente.getMiIp());
                    cliente.getSalida().writeObject(gson.toJson(p));
                    cliente.getEntrada().close();
                    cliente.getSalida().close();
                    cliente.getSocket().close();
                    System.exit(0);
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(null, "Error al desconectar");

                }
            }
        });
        contentPane.add(desconectarse);

        final JButton volver = new JButton("Volver");
        volver.setIcon(new ImageIcon("recursos//volver.png"));
        final int xVolver = 29;
        final int yVolver = 227;
        volver.setBounds(xVolver, yVolver, widthAsignar, heightAsignar);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                Pantalla.setMenuEscp(null);
                dispose();
            }
        });
        contentPane.add(volver);

        BufferedImage imagenFondo = null;
        try {
            imagenFondo = ImageIO.read(new File("recursos//fondo2.png"));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

        }
        final int widthIcon = 200;
        final int heightIcon = 350;
        final JLabel background = new JLabel(
                new ImageIcon(imagenFondo.getScaledInstance(widthIcon, heightIcon, Image.SCALE_DEFAULT)));
        final int widthBkg = 186;
        final int heightBkg = 273;
        background.setBounds(0, 0, widthBkg, heightBkg);
        contentPane.add(background);
    }
}
