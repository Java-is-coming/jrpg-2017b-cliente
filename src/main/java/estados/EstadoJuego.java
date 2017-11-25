package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoNPC;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Estado de juego
 */
public class EstadoJuego extends Estado {

    private final Entidad entidadPersonaje;
    private PaquetePersonaje paquetePersonaje;
    private final Mundo mundo;
    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
    private Map<Integer, PaquetePersonaje> personajesConectados;

    // Agregamos NPCs
    private Map<Integer, PaqueteNPC> npcsDisponibles;
    // private Map<Integer, PaqueteMovimiento> ubicacionesNPCsDisponibles;

    private boolean haySolicitud;
    private int tipoSolicitud;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;

    private MenuInfoPersonaje menuEnemigo;
    private MenuInfoNPC menuEnemigoNPC;

    /**
     * Constructor
     *
     * @param juego
     *            instancia de juego
     */
    public EstadoJuego(final Juego juego) {
        super(juego);
        mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
        paquetePersonaje = juego.getPersonaje();
        final int size = 64;
        final int velAnimacion = 150;
        entidadPersonaje = new Entidad(juego, mundo, size, size, juego.getPersonaje().getNombre(), 0, 0,
                Recursos.personaje.get(juego.getPersonaje().getRaza()), velAnimacion);
        final int frameMin = 5;
        miniaturaPersonaje = Recursos.personaje.get(paquetePersonaje.getRaza()).get(frameMin)[0];

        try {
            // Le envio al servidor que me conecte al mapa y mi posicion
            juego.getPersonaje().setComando(Comando.CONEXION);
            juego.getPersonaje().setEstado(Estado.ESTADO_JUEGO);
            juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
            juego.getCliente().getSalida()
                    .writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor al ingresar al mundo");
        }
    }

    @Override
    public void actualizar() {
        mundo.actualizar();
        entidadPersonaje.actualizar();
    }

    @Override
    public void graficar(final Graphics g) {
        g.drawImage(Recursos.background, 0, 0, getJuego().getAncho(), getJuego().getAlto(), null);
        mundo.graficar(g);
        // entidadPersonaje.graficar(g);
        graficarPersonajes(g);

        // Agregamos el grafico de los NPC
        graficarNPC(g);

        mundo.graficarObstaculos(g);
        entidadPersonaje.graficarNombre(g);
        g.drawImage(Recursos.marco, 0, 0, getJuego().getAncho(), getJuego().getAlto(), null);
        final int estado = 5;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, estado, estado, paquetePersonaje, miniaturaPersonaje);
        final int xMochila = 738;
        final int yMochila = 545;
        final int widthMochila = 59;
        final int heightMochila = 52;
        g.drawImage(Recursos.mochila, xMochila, yMochila, widthMochila, heightMochila, null);
        final int xRecursos = 3;
        final int yMenu = 562;
        final int widthMenu = 102;
        final int heightMenu = 35;
        g.drawImage(Recursos.menu, xRecursos, yMenu, widthMenu, heightMenu, null);
        final int yChat = 524;
        final int widthChat = 102;
        final int heightChat = 35;
        g.drawImage(Recursos.chat, xRecursos, yChat, widthChat, heightChat, null);
        if (haySolicitud) {
            if (tipoSolicitud == MenuInfoNPC.MENU_BATALLARNPC) {
                getMenuEnemigoNPC().graficar(g, tipoSolicitud);
            } else {
                try {
                    getMenuEnemigo().graficar(g, tipoSolicitud);
                } catch (final Exception e) {
                    return;
                }
            }
        }
    }

    /**
     * Grafica personajes
     *
     * @param g
     *            graphics
     */
    public void graficarPersonajes(final Graphics g) {
        if (getJuego().getPersonajesConectados() != null) {
            personajesConectados = new HashMap<Integer, PaquetePersonaje>(getJuego().getPersonajesConectados());
            ubicacionPersonajes = new HashMap<Integer, PaqueteMovimiento>(getJuego().getUbicacionPersonajes());
            final Iterator<Integer> it = personajesConectados.keySet().iterator();
            int key;
            PaqueteMovimiento actual;
            g.setColor(Color.WHITE);
            final int fontSize = 15;
            g.setFont(new Font("Book Antiqua", Font.PLAIN, fontSize));
            while (it.hasNext()) {
                key = it.next();
                actual = ubicacionPersonajes.get(key);

                boolean mostrar = true;

                if (!getJuego().getPersonaje().getModoInvisible()
                        && personajesConectados.get(actual.getIdPersonaje()).getModoInvisible()) {
                    mostrar = false;
                }

                if (actual != null && actual.getIdPersonaje() != getJuego().getPersonaje().getId()
                        && personajesConectados.get(actual.getIdPersonaje()).getEstado() == Estado.ESTADO_JUEGO
                        && mostrar) {
                    final int offset = 32;
                    final int offsetY = 20;
                    final int height = 10;
                    Pantalla.centerString(g,
                            new Rectangle((int) (actual.getPosX() - getJuego().getCamara().getxOffset() + offset),
                                    (int) (actual.getPosY() - getJuego().getCamara().getyOffset() - offsetY), 0,
                                    height),
                            personajesConectados.get(actual.getIdPersonaje()).getNombre());
                    final int size = 64;
                    g.drawImage(
                            Recursos.personaje.get(personajesConectados.get(actual.getIdPersonaje()).getRaza())
                                    .get(actual.getDireccion())[actual.getFrame()],
                            (int) (actual.getPosX() - getJuego().getCamara().getxOffset()),
                            (int) (actual.getPosY() - getJuego().getCamara().getyOffset()), size, size, null);
                }
            }
        }
    }

    /**
     * Grafica NPCs
     *
     * @param g
     *            graphics
     */
    public void graficarNPC(final Graphics g) {
        if (getJuego().getNPCsDisponibles() != null) {
            npcsDisponibles = new HashMap<Integer, PaqueteNPC>(getJuego().getNPCsDisponibles());
            // ubicacionesNPCsDisponibles = new
            // HashMap(juego.getUbicacionesNPCsDisponibles());
            final Iterator<Integer> it = npcsDisponibles.keySet().iterator();

            int key;
            PaqueteNPC actual;
            g.setColor(Color.RED);
            final int fontSize = 15;
            g.setFont(new Font("Book Antiqua", Font.BOLD, fontSize));
            while (it.hasNext()) {
                key = it.next();
                actual = npcsDisponibles.get(key);
                if (actual != null && npcsDisponibles.get(actual.getId()).getEstado() == Estado.ESTADO_JUEGO) {
                    final int offset = 32;
                    final int size = 10;
                    final int offsetY = 4;
                    Pantalla.centerString(g,
                            new Rectangle((int) (actual.getPosX() - getJuego().getCamara().getxOffset() + offset),
                                    (int) (actual.getPosY() - getJuego().getCamara().getyOffset() - offsetY), 0, size),
                            npcsDisponibles.get(actual.getId()).getNombre());
                    // NPCsDisponibles.get(actual.getId()).getNombre() + "X: " +
                    // NPCsDisponibles.get(actual.getId()).getPosX() + "Y: " +
                    // NPCsDisponibles.get(actual.getId()).getPosY());
                    final int size2 = 128;
                    g.drawImage(
                            Recursos.personaje.get(npcsDisponibles.get(actual.getId()).getRaza())
                                    .get(actual.getDireccion())[actual.getFrame()],
                            (int) (actual.getPosX() - getJuego().getCamara().getxOffset()),
                            (int) (actual.getPosY() - getJuego().getCamara().getyOffset()), size2, size2, null);
                }
            }
        }
    }

    /**
     * Get personaje
     *
     * @return Entidad personaje
     */
    public Entidad getPersonaje() {
        return entidadPersonaje;
    }

    /**
     * Get de mundo
     *
     * @return String Mundo
     */
    private String getMundo() {
        final int mundoActual = getJuego().getPersonaje().getMapa() - 1;

        final String[] mapas = {"Aubenor", "Aris", "Eodrim"};

        return mapas[mundoActual];
        // if (mundoActual == 1) {
        // return "Aubenor";
        // } else {
        // final int aris = 2;
        // if (mundoActual == aris) {
        // return "Aris";
        // } else {
        // final int eodrim = 3;
        // if (mundoActual == eodrim) {
        // return "Eodrim";
        // }
        // }
        // }
        //
        // return null;
    }

    /**
     * Solicitud de batalla
     *
     * @param b
     *            boolean
     * @param enemigo
     *            a solicitar
     * @param tipoSol
     *            tipo batalla
     */
    public void setHaySolicitud(final boolean b, final PaquetePersonaje enemigo, final int tipoSol) {
        haySolicitud = b;
        // menu que mostrara al enemigo
        if (tipoSolicitud != MenuInfoNPC.MENU_BATALLARNPC && enemigo != null) {
            final int x = 300;
            final int y = 50;
            setMenuEnemigo(new MenuInfoPersonaje(x, y, enemigo));
        }
        this.tipoSolicitud = tipoSol;
    }

    /**
     * Solicitud de batalla
     *
     * @param b
     *            boolean
     * @param enemigo
     *            enemigo
     */
    public void setHaySolicitud(final boolean b, final PaqueteNPC enemigo) {
        haySolicitud = b;
        // menu que mostrara al enemigo
        final int x = 300;
        final int y = 50;
        setMenuEnemigoNPC(new MenuInfoNPC(x, y, enemigo));
        this.tipoSolicitud = MenuInfoNPC.MENU_BATALLARNPC;
    }

    /**
     * Get solicitud
     *
     * @return boolean hay solicitud
     */
    public boolean getHaySolicitud() {
        return haySolicitud;
    }

    /**
     * Actualizar personaje
     */
    public void actualizarPersonaje() {
        paquetePersonaje = getJuego().getPersonaje();
    }

    /**
     * Muestra info del enemigo
     *
     * @return MenuInfoPersonaje menu
     */
    public MenuInfoPersonaje getMenuEnemigo() {
        return menuEnemigo;
    }

    /**
     * Muestra info del enemigo
     *
     * @return MenuInfoNPC menu
     */
    public MenuInfoNPC getMenuEnemigoNPC() {
        return menuEnemigoNPC;
    }

    /**
     * Tipo solicitud
     *
     * @return int tipo solicitud
     */
    public int getTipoSolicitud() {
        return tipoSolicitud;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return true;
    }

    /**
     * Set menu enemigo
     *
     * @param menuEnemigo
     *            menu enemigo
     */
    void setMenuEnemigo(final MenuInfoPersonaje menuEnemigo) {
        this.menuEnemigo = menuEnemigo;
    }

    /**
     * Set menu enemigo npc
     *
     * @param menuEnemigoNPC
     *            menu enemig npc
     */
    void setMenuEnemigoNPC(final MenuInfoNPC menuEnemigoNPC) {
        this.menuEnemigoNPC = menuEnemigoNPC;
    }

}
