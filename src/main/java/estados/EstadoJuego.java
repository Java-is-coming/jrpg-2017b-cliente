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

public class EstadoJuego extends Estado {

    private final Entidad entidadPersonaje;
    private PaquetePersonaje paquetePersonaje;
    private final Mundo mundo;
    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
    private Map<Integer, PaquetePersonaje> personajesConectados;

    // Agregamos NPCs
    private Map<Integer, PaqueteNPC> NPCsDisponibles;
    // private Map<Integer, PaqueteMovimiento> ubicacionesNPCsDisponibles;

    private boolean haySolicitud;
    private int tipoSolicitud;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;

    MenuInfoPersonaje menuEnemigo;
    MenuInfoNPC menuEnemigoNPC;

    public EstadoJuego(final Juego juego) {
        super(juego);
        mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
        paquetePersonaje = juego.getPersonaje();
        entidadPersonaje = new Entidad(juego, mundo, 64, 64, juego.getPersonaje().getNombre(), 0, 0,
                Recursos.personaje.get(juego.getPersonaje().getRaza()), 150);
        miniaturaPersonaje = Recursos.personaje.get(paquetePersonaje.getRaza()).get(5)[0];

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

        // Agregamos el grafico de losw NPC
        graficarNPC(g);

        mundo.graficarObstaculos(g);
        entidadPersonaje.graficarNombre(g);
        g.drawImage(Recursos.marco, 0, 0, getJuego().getAncho(), getJuego().getAlto(), null);
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, 5, 5, paquetePersonaje, miniaturaPersonaje);
        g.drawImage(Recursos.mochila, 738, 545, 59, 52, null);
        g.drawImage(Recursos.menu, 3, 562, 102, 35, null);
        g.drawImage(Recursos.chat, 3, 524, 102, 35, null);
        if (haySolicitud) {
            if (tipoSolicitud == MenuInfoNPC.menuBatallarNPC) {
                menuEnemigoNPC.graficar(g, tipoSolicitud);
            } else {
                menuEnemigo.graficar(g, tipoSolicitud);
            }

        }

    }

    public void graficarPersonajes(final Graphics g) {
        if (getJuego().getPersonajesConectados() != null) {
            personajesConectados = new HashMap(getJuego().getPersonajesConectados());
            ubicacionPersonajes = new HashMap(getJuego().getUbicacionPersonajes());
            final Iterator<Integer> it = personajesConectados.keySet().iterator();
            int key;
            PaqueteMovimiento actual;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
            while (it.hasNext()) {
                key = it.next();
                actual = ubicacionPersonajes.get(key);
                if (actual != null && actual.getIdPersonaje() != getJuego().getPersonaje().getId()
                        && personajesConectados.get(actual.getIdPersonaje()).getEstado() == Estado.ESTADO_JUEGO) {
                    Pantalla.centerString(g,
                            new Rectangle((int) (actual.getPosX() - getJuego().getCamara().getxOffset() + 32),
                                    (int) (actual.getPosY() - getJuego().getCamara().getyOffset() - 20), 0, 10),
                            personajesConectados.get(actual.getIdPersonaje()).getNombre());
                    g.drawImage(
                            Recursos.personaje.get(personajesConectados.get(actual.getIdPersonaje()).getRaza())
                                    .get(actual.getDireccion())[actual.getFrame()],
                            (int) (actual.getPosX() - getJuego().getCamara().getxOffset()),
                            (int) (actual.getPosY() - getJuego().getCamara().getyOffset()), 64, 64, null);
                }
            }
        }
    }

    public void graficarNPC(final Graphics g) {
        if (getJuego().getNPCsDisponibles() != null) {
            NPCsDisponibles = new HashMap(getJuego().getNPCsDisponibles());
            // ubicacionesNPCsDisponibles = new
            // HashMap(juego.getUbicacionesNPCsDisponibles());
            final Iterator<Integer> it = NPCsDisponibles.keySet().iterator();

            int key;
            PaqueteNPC actual;
            g.setColor(Color.RED);
            g.setFont(new Font("Book Antiqua", Font.BOLD, 15));
            while (it.hasNext()) {
                key = it.next();
                actual = NPCsDisponibles.get(key);
                if (actual != null && NPCsDisponibles.get(actual.getId()).getEstado() == Estado.ESTADO_JUEGO) {
                    Pantalla.centerString(g,
                            new Rectangle((int) (actual.getPosX() - getJuego().getCamara().getxOffset() + 32),
                                    (int) (actual.getPosY() - getJuego().getCamara().getyOffset() - 4), 0, 10),
                            NPCsDisponibles.get(actual.getId()).getNombre());
                    // NPCsDisponibles.get(actual.getId()).getNombre() + "X: " +
                    // NPCsDisponibles.get(actual.getId()).getPosX() + "Y: " +
                    // NPCsDisponibles.get(actual.getId()).getPosY());
                    g.drawImage(
                            Recursos.personaje.get(NPCsDisponibles.get(actual.getId()).getRaza())
                                    .get(actual.getDireccion())[actual.getFrame()],
                            (int) (actual.getPosX() - getJuego().getCamara().getxOffset()),
                            (int) (actual.getPosY() - getJuego().getCamara().getyOffset()), 128, 128, null);
                }
            }
        }
    }

    public Entidad getPersonaje() {
        return entidadPersonaje;
    }

    private String getMundo() {
        final int mundo = getJuego().getPersonaje().getMapa();

        if (mundo == 1) {
            return "Aubenor";
        } else if (mundo == 2) {
            return "Aris";
        } else if (mundo == 3) {
            return "Eodrim";
        }

        return null;
    }

    public void setHaySolicitud(final boolean b, final PaquetePersonaje enemigo, final int tipoSolicitud) {
        haySolicitud = b;
        // menu que mostrara al enemigo
        if (tipoSolicitud != MenuInfoNPC.menuBatallarNPC && enemigo != null) {
            menuEnemigo = new MenuInfoPersonaje(300, 50, enemigo);
        }
        this.tipoSolicitud = tipoSolicitud;
    }

    public void setHaySolicitud(final boolean b, final PaqueteNPC enemigo) {
        haySolicitud = b;
        // menu que mostrara al enemigo
        menuEnemigoNPC = new MenuInfoNPC(300, 50, enemigo);
        this.tipoSolicitud = MenuInfoNPC.menuBatallarNPC;
    }

    public boolean getHaySolicitud() {
        return haySolicitud;
    }

    public void actualizarPersonaje() {
        paquetePersonaje = getJuego().getPersonaje();
    }

    public MenuInfoPersonaje getMenuEnemigo() {
        return menuEnemigo;
    }

    public MenuInfoNPC getMenuEnemigoNPC() {
        return menuEnemigoNPC;
    }

    public int getTipoSolicitud() {
        return tipoSolicitud;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return true;
    }

}
