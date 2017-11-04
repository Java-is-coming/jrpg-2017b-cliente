package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
import mensajeria.PaqueteAtacarNPC;
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
    private PaqueteAtacarNPC paqueteAtacarNPC;
    private final PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    private boolean miTurno;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;
    private final BufferedImage miniaturaEnemigo;

    private final MenuBatalla menuBatalla;

    /**
     * Constructor estado batalla
     *
     * @param juego
     *            instancia juego
     * @param paqueteBatalla
     *            paquete
     */
    public EstadoBatallaNPC(final Juego juego, final PaqueteBatalla paqueteBatalla) {
        super(juego);
        mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
        miTurno = paqueteBatalla.isMiTurno();

        paquetePersonaje = getJuego().getPersonajesConectados().get(paqueteBatalla.getId());
        paqueteEnemigoNPC = getJuego().getNPCsDisponibles().get(paqueteBatalla.getIdEnemigo());

        crearPersonajes();

        menuBatalla = new MenuBatalla(miTurno, personaje);

        final int frame = 5;
        miniaturaEnemigo = Recursos.personaje.get("Elfo").get(frame)[0];
        miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(frame)[0];

        paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
        paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
        paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigoNPC.getId());
        paqueteFinalizarBatalla.setTipoBatalla(PaqueteBatalla.BATALLAR_NPC);

        // por defecto batalla perdida
        getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(), MenuInfoNPC.MENU_PERDERBATALLA);

        // limpio la accion del mouse
        getJuego().getHandlerMouse().setNuevoClick(false);

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

            if (!personaje.estaVivo()) {
                getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                        MenuInfoPersonaje.MENU_PERDERBATALLA);
                paqueteFinalizarBatalla.setGanadorBatalla(paqueteFinalizarBatalla.getIdEnemigo());
                finalizarBatalla();
                Estado.setEstado(getJuego().getEstadoJuego());
            } else {

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

                        paqueteAtacarNPC = new PaqueteAtacarNPC(paquetePersonaje.getId(), paqueteEnemigoNPC.getId(),
                                personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(),
                                personaje.getDefensa(), personaje.getCasta().getProbabilidadEvitarDanio());

                        enviarAtaque(paqueteAtacarNPC);
                        miTurno = false;
                        menuBatalla.setHabilitado(false);
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

        final int sizeImg = 256;

        final int frame = 3;
        final int yImgPj = 175;
        final Image img = Recursos.personaje.get(paquetePersonaje.getRaza()).get(frame)[0];
        g.drawImage(img, 0, yImgPj, sizeImg, sizeImg, null);

        final int frame7 = 7;
        final Image imgEnemigo = Recursos.personaje.get(paqueteEnemigoNPC.getRaza()).get(frame7)[0];
        final int xImgEnemigo = 550;
        final int yImgEnemigo = 75;

        g.drawImage(imgEnemigo, xImgEnemigo, yImgEnemigo, sizeImg, sizeImg, null);

        mundo.graficarObstaculos(g);
        menuBatalla.graficar(g);

        g.setColor(Color.GREEN);

        final int xEstadoPj = 25;
        final int yEstado = 5;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xEstadoPj, yEstado, personaje, miniaturaPersonaje);
        final int xEstadoEnemigo = 550;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xEstadoEnemigo, yEstado, enemigo, miniaturaEnemigo);
    }

    /**
     * Dibuja personaje
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
     * Envio de paquete de ataque a NPC
     *
     * @param paquete
     *            paquete atacar npc
     */
    public void enviarAtaque(final PaqueteAtacarNPC paquete) {
        try {
            getJuego().getCliente().getSalida().writeObject(gson.toJson(paquete));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
        }
    }

    /**
     * Terminar batalla
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
            // getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigoNPC));

        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
        }
    }

    /**
     * Get paquete personaje
     *
     * @return PaquetePersonaje paquete
     */
    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }

    /**
     * Get paquete enemigo
     *
     * @return PaqueteNPC enemigo npc
     */
    public PaqueteNPC getPaqueteEnemigo() {
        return paqueteEnemigoNPC;
    }

    /**
     * Set mi turno
     *
     * @param b
     *            boolean mi turno
     */
    public void setMiTurno(final boolean b) {
        miTurno = b;
        menuBatalla.setHabilitado(b);
        getJuego().getHandlerMouse().setNuevoClick(false);
    }

    /**
     * Getter de personaje
     *
     * @return Personaje personaje en batalla
     */
    public Personaje getPersonaje() {
        return personaje;
    }

    /**
     * Getter de NPC
     *
     * @return NPC en batalla
     */
    public NonPlayableCharacter getEnemigo() {
        return enemigo;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return false;
    }
}
