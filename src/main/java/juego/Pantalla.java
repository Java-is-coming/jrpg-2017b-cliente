package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import chat.VentanaContactos;
import cliente.Cliente;
import estados.Estado;
import frames.MenuAsignarSkills;
import frames.MenuEscape;
import frames.MenuInventario;
import frames.MenuJugar;
import frames.MenuStats;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Clase pantalla para mostrar
 *
 */
public class Pantalla {

    private final JFrame pantalla;
    private final Canvas canvas;

    // Menus
    private static MenuInventario menuInventario;
    private static MenuAsignarSkills menuAsignar;
    private static MenuStats menuStats;
    private static MenuEscape menuEscp;
    private static VentanaContactos ventContac;

    private final Gson gson = new Gson();

    /**
     * Constructor de pantalla
     *
     * @param nombre
     *            del formulario
     * @param ancho
     *            de la pantalla
     * @param alto
     *            de la pantalla
     * @param cliente
     *            cliente
     */
    public Pantalla(final String nombre, final int ancho, final int alto, final Cliente cliente) {
        pantalla = new JFrame(nombre);
        pantalla.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
        pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
                "custom cursor"));

        pantalla.setSize(ancho, alto);
        pantalla.setResizable(false);
        pantalla.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pantalla.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                try {
                    final Paquete p = new Paquete();
                    p.setComando(Comando.DESCONECTAR);
                    p.setIp(cliente.getMiIp());
                    cliente.getSalida().writeObject(gson.toJson(p));
                    cliente.getEntrada().close();
                    cliente.getSalida().close();
                    cliente.getSocket().close();
                    System.exit(0);
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
                    System.exit(1);
                }
            }
        });
        pantalla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    if (Estado.getEstado().esEstadoDeJuego()) {
                        if (getMenuInventario() == null) {
                            setMenuInventario(new MenuInventario(cliente));
                            getMenuInventario().setVisible(true);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    if (Estado.getEstado().esEstadoDeJuego()) {
                        if (getMenuAsignar() == null) {
                            setMenuAsignar(new MenuAsignarSkills(cliente));
                            getMenuAsignar().setVisible(true);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (Estado.getEstado().esEstadoDeJuego()) {
                        if (getMenuStats() == null) {
                            setMenuStats(new MenuStats(cliente));
                            getMenuStats().setVisible(true);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (Estado.getEstado().esEstadoDeJuego()) {
                        if (getMenuEscp() == null) {
                            setMenuEscp(new MenuEscape(cliente));
                            getMenuEscp().setVisible(true);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    // if(Estado.getEstado().esEstadoDeJuego()) {
                    if (getVentContac() == null) {
                        setVentContac(new VentanaContactos(cliente.getJuego()));
                        getVentContac().setVisible(true);
                    }
                    // }
                }
            }
        });

        pantalla.setLocationRelativeTo(null);
        pantalla.setVisible(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(ancho, alto));
        canvas.setMaximumSize(new Dimension(ancho, alto));
        canvas.setMinimumSize(new Dimension(ancho, alto));
        canvas.setFocusable(false);

        pantalla.add(canvas);
        pantalla.pack();
    }

    /**
     * Devuelve el canvas
     *
     * @return Canvas canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Devuelve la pantalla
     *
     * @return JFrame pantalla
     */
    public JFrame getFrame() {
        return pantalla;
    }

    /**
     * Muestra la pantalla
     */
    public void mostrar() {
        pantalla.setVisible(true);
    }

    /**
     * Centra el string en el rectangulo
     *
     * @param g
     *            graphic
     * @param r
     *            rectangle
     * @param s
     *            string
     */
    public static void centerString(final Graphics g, final Rectangle r, final String s) {
        final FontRenderContext frc = new FontRenderContext(null, true, true);

        final Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
        final int rWidth = (int) Math.round(r2D.getWidth());
        final int rHeight = (int) Math.round(r2D.getHeight());
        final int rX = (int) Math.round(r2D.getX());
        final int rY = (int) Math.round(r2D.getY());

        final int a = (r.width / 2) - (rWidth / 2) - rX;
        final int b = (r.height / 2) - (rHeight / 2) - rY;

        g.drawString(s, r.x + a, r.y + b);
    }

    /**
     * @return the menuInventario
     */
    public static MenuInventario getMenuInventario() {
        return menuInventario;
    }

    /**
     * @param menuInventario
     *            the menuInventario to set
     */
    public static void setMenuInventario(final MenuInventario menuInventario) {
        Pantalla.menuInventario = menuInventario;
    }

    /**
     * @return the menuAsignar
     */
    public static MenuAsignarSkills getMenuAsignar() {
        return menuAsignar;
    }

    /**
     * @param menuAsignar
     *            the menuAsignar to set
     */
    public static void setMenuAsignar(final MenuAsignarSkills menuAsignar) {
        Pantalla.menuAsignar = menuAsignar;
    }

    /**
     * @return the menuStats
     */
    public static MenuStats getMenuStats() {
        return menuStats;
    }

    /**
     * @param menuStats
     *            the menuStats to set
     */
    public static void setMenuStats(final MenuStats menuStats) {
        Pantalla.menuStats = menuStats;
    }

    /**
     * @return the menuEscp
     */
    public static MenuEscape getMenuEscp() {
        return menuEscp;
    }

    /**
     * @param menuEscp
     *            the menuEscp to set
     */
    public static void setMenuEscp(final MenuEscape menuEscp) {
        Pantalla.menuEscp = menuEscp;
    }

    /**
     * @return the ventContac
     */
    public static VentanaContactos getVentContac() {
        return ventContac;
    }

    /**
     * @param ventContac
     *            the ventContac to set
     */
    public static void setVentContac(final VentanaContactos ventContac) {
        Pantalla.ventContac = ventContac;
    }
}
