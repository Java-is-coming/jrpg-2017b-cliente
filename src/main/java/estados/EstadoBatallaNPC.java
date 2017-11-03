package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Casta;
import dominio.NonPlayableCharacter;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoNPC;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Batallando con un NPC
 */
public class EstadoBatallaNPC extends Estado {

    private final Mundo mundo;
    private Personaje personaje;
    private NonPlayableCharacter enemigo;
    private int[] posMouse;
    private final PaquetePersonaje paquetePersonaje;
    private final PaqueteNPC paqueteEnemigoNPC;
    private final PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    private boolean miTurno;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;
    private final BufferedImage miniaturaEnemigo;

    private final MenuBatalla menuBatalla;

    /**
     * Constructor de estado
     *
     * @param juego
     *            instancia del juego
     * @param paqueteBatalla
     *            paquete de batalla
     */
    public EstadoBatallaNPC(final Juego juego, final PaqueteBatalla paqueteBatalla) {
        super(juego);
        mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
        miTurno = paqueteBatalla.isMiTurno();

        paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
        paqueteEnemigoNPC = juego.getNPCsDisponibles().get(paqueteBatalla.getIdEnemigo());

        crearPersonajes();

        menuBatalla = new MenuBatalla(miTurno, personaje);

        final int frame = 5;
        miniaturaEnemigo = Recursos.personaje.get(personaje.getNombreRaza()).get(frame)[0];
        miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(frame)[0];

        paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
        paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
        paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigoNPC.getId());
        paqueteFinalizarBatalla.setTipoBatalla(PaqueteBatalla.BATALLAR_NPC);

        // por defecto batalla perdida
        juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoNPC.MENU_PERDERBATALLA);

        // limpio la accion del mouse
        juego.getHandlerMouse().setNuevoClick(false);

    }

    @Override
    public void actualizar() {

        final int xOffset = 350;
        final int yOffset = 150;
        getJuego().getCamara().setxOffset(-xOffset);
        getJuego().getCamara().setyOffset(yOffset);

        seRealizoAccion = false;
        haySpellSeleccionada = false;

        if (miTurno) {

            if (getJuego().getHandlerMouse().getNuevoClick()) {
                posMouse = getJuego().getHandlerMouse().getPosMouse();

                if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadRaza1(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadRaza2(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton3 = 3;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton3) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta1(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton4 = 4;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton4) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta2(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton5 = 5;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton5) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta3(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton6 = 6;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton6) {
                        seRealizoAccion = true;
                        final int energizado = 10;
                        personaje.serEnergizado(energizado);
                        haySpellSeleccionada = true;
                    }
                }

                if (haySpellSeleccionada && seRealizoAccion) {
                    if (!enemigo.estaVivo()) {
                        getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                MenuInfoPersonaje.MENU_GANARBATALLA);

                        if (personaje.ganarExperiencia(enemigo.otorgarExp())) {
                            getJuego().getPersonaje().setNivel(personaje.getNivel());
                            getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                    MenuInfoPersonaje.MENU_SUBIRNIVEL);
                            Pantalla.setMenuAsignar(null);
                        }

                        paqueteFinalizarBatalla.setGanadorBatalla(getJuego().getPersonaje().getId());
                        finalizarBatalla();
                        Estado.setEstado(getJuego().getEstadoJuego());

                    } else {
                        enemigo.atacar(personaje);

                        if (!personaje.estaVivo()) {
                            getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                    MenuInfoPersonaje.MENU_PERDERBATALLA);
                            paqueteFinalizarBatalla.setGanadorBatalla(paqueteFinalizarBatalla.getIdEnemigo());
                            finalizarBatalla();
                            Estado.setEstado(getJuego().getEstadoJuego());
                        }
                        // juego.getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
                        setMiTurno(true);
                    }
                } else if (haySpellSeleccionada && !seRealizoAccion) {
                    JOptionPane.showMessageDialog(null,
                            "No posees la energía suficiente para realizar esta habilidad.");
                }

                getJuego().getHandlerMouse().setNuevoClick(false);
            }
        }

    }

    @Override
    public void graficar(final Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getJuego().getAncho(), getJuego().getAlto());
        mundo.graficar(g);

        final int frame3 = 3;
        final int yPersonaje = 175;
        final int size = 256;
        g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(frame3)[0], 0, yPersonaje, size, size, null);
        final int frame7 = 7;
        final int xEnemigo = 550;
        final int yEnemigo = 75;
        g.drawImage(Recursos.personaje.get(paqueteEnemigoNPC.getRaza()).get(frame7)[0], xEnemigo, yEnemigo, size, size,
                null);

        mundo.graficarObstaculos(g);
        menuBatalla.graficar(g);

        g.setColor(Color.GREEN);

        final int xEstado = 25;
        final int yEstado = 5;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xEstado, yEstado, personaje, miniaturaPersonaje);
        final int xEstadoEnemigo = 550;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xEstadoEnemigo, yEstado, enemigo, miniaturaEnemigo);
    }

    /**
     * Crea los personajes
     */
    private void crearPersonajes() {
        String nombre = paquetePersonaje.getNombre();
        int salud = paquetePersonaje.getSaludTope();
        final int energia = paquetePersonaje.getEnergiaTope();
        int fuerza = paquetePersonaje.getFuerza();
        final int destreza = paquetePersonaje.getDestreza();
        final int inteligencia = paquetePersonaje.getInteligencia();
        final int experiencia = paquetePersonaje.getExperiencia();
        int nivel = paquetePersonaje.getNivel();
        int id = paquetePersonaje.getId();

        Casta casta = null;
        try {
            casta = (Casta) Class.forName("dominio" + "." + paquetePersonaje.getCasta()).newInstance();
            personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza())
                    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
                            Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)
                    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la batalla");
        }

        nombre = paqueteEnemigoNPC.getNombre();
        salud = paqueteEnemigoNPC.getSaludTope();
        fuerza = paqueteEnemigoNPC.getFuerza();
        nivel = paqueteEnemigoNPC.getNivel();
        id = paqueteEnemigoNPC.getId();
        final int dificultad = paqueteEnemigoNPC.getDificultad();

        enemigo = new NonPlayableCharacter(nombre, nivel, dificultad);
    }

    /**
     * Termina la batalla
     */
    private void finalizarBatalla() {
        try {
            getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));

            paquetePersonaje.setSaludTope(personaje.getSaludTope());
            paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
            paquetePersonaje.setNivel(personaje.getNivel());
            paquetePersonaje.setExperiencia(personaje.getExperiencia());
            paquetePersonaje.setDestreza(personaje.getDestreza());
            paquetePersonaje.setFuerza(personaje.getFuerza());
            paquetePersonaje.setInteligencia(personaje.getInteligencia());
            paquetePersonaje.removerBonus();

            // paqueteEnemigoNPC.setSaludTope(enemigo.getSalud());
            // paqueteEnemigoNPC.setFuerza(enemigo.getFuerza());

            paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
            // paqueteEnemigoNPC.setComando(Comando.ACTUALIZARPERSONAJE);

            getJuego().getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
            // juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigoNPC));

        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
        }
    }

    /**
     * Paquete personaje
     *
     * @return paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }

    /**
     * Paquete NPC
     *
     * @return enemigo NPC
     */
    public PaqueteNPC getPaqueteEnemigo() {
        return paqueteEnemigoNPC;
    }

    /**
     * Set mi turno
     *
     * @param b
     *            mi turno
     */
    public void setMiTurno(final boolean b) {
        miTurno = b;
        menuBatalla.setHabilitado(b);
        getJuego().getHandlerMouse().setNuevoClick(false);
    }

    /**
     * Get personaje
     *
     * @return el personaje
     */
    public Personaje getPersonaje() {
        return personaje;
    }

    /**
     * Enemigo
     *
     * @return npc enemigo
     */
    public NonPlayableCharacter getEnemigo() {
        return enemigo;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return false;
    }
}
