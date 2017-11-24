package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Estado batalla de un personaje
 */
public class EstadoBatalla extends Estado {

    private final Mundo mundo;
    private Personaje personaje;
    private Personaje enemigo;
    private int[] posMouse;
    private final PaquetePersonaje paquetePersonaje;
    private final PaquetePersonaje paqueteEnemigo;
    private PaqueteAtacar paqueteAtacar;
    private final PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    private boolean miTurno;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;
    private final BufferedImage miniaturaEnemigo;

    private final MenuBatalla menuBatalla;

    /**
     * Constructor
     *
     * @param juego
     *            instancia de juego
     * @param paqueteBatalla
     *            paquete de batalla
     */
    public EstadoBatalla(final Juego juego, final PaqueteBatalla paqueteBatalla) {
        super(juego);
        mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
        miTurno = paqueteBatalla.isMiTurno();

        paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
        paqueteEnemigo = juego.getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());

        crearPersonajes();

        menuBatalla = new MenuBatalla(miTurno, personaje);

        final int frame = 5;
        miniaturaEnemigo = Recursos.personaje.get(enemigo.getNombreRaza()).get(frame)[0];
        miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(frame)[0];

        paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
        paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
        paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());
        paqueteFinalizarBatalla.setTipoBatalla(PaqueteBatalla.BATALLAR_PERSONAJE);

        // por defecto batalla perdida
        juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENU_PERDERBATALLA);

        // limpio la accion del mouse
        juego.getHandlerMouse().setNuevoClick(false);

    }

    @Override
    public void actualizar() {

        final int xOffset = 350;
        getJuego().getCamara().setxOffset(-xOffset);
        final int yOffset = 150;
        getJuego().getCamara().setyOffset(yOffset);

        seRealizoAccion = false;
        haySpellSeleccionada = false;

        if (miTurno) {

            boolean esInvulnerable = false;

            esInvulnerable = !paquetePersonaje.getModoDios() && paqueteEnemigo.getModoDios();

            if (getJuego().getHandlerMouse().getNuevoClick()) {
                posMouse = getJuego().getHandlerMouse().getPosMouse();

                if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            if (!esInvulnerable) {
                                personaje.habilidadRaza1(enemigo);
                            }
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton2 = 2;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton2) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            if (!esInvulnerable) {
                                personaje.habilidadRaza2(enemigo);
                            }
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton3 = 3;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton3) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            if (!esInvulnerable) {
                                personaje.habilidadCasta1(enemigo);
                            }
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton4 = 4;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton4) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            if (!esInvulnerable) {
                                personaje.habilidadCasta2(enemigo);
                            }
                        }
                        haySpellSeleccionada = true;
                    }

                    final int boton5 = 5;
                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == boton5) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            if (!esInvulnerable) {
                                personaje.habilidadCasta3(enemigo);
                            }
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
                        final int multiplicador = 40;
                        if (personaje.ganarExperiencia(enemigo.getNivel() * multiplicador)) {
                            getJuego().getPersonaje().setNivel(personaje.getNivel());
                            getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                    MenuInfoPersonaje.MENU_SUBIRNIVEL);
                            Pantalla.setMenuAsignar(null);
                        }
                        paqueteFinalizarBatalla.setGanadorBatalla(getJuego().getPersonaje().getId());
                        finalizarBatalla();
                        Estado.setEstado(getJuego().getEstadoJuego());

                    } else {
                        paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), paqueteEnemigo.getId(),
                                personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), enemigo.getEnergia(),
                                personaje.getDefensa(), enemigo.getDefensa(),
                                personaje.getCasta().getProbabilidadEvitarDanio(),
                                enemigo.getCasta().getProbabilidadEvitarDanio());
                        enviarAtaque(paqueteAtacar);
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

        final int size = 256;
        final int yPersonaje = 175;
        final int frame3 = 3;
        g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(frame3)[0], 0, yPersonaje, size, size, null);
        final int yPersonaje1 = 75;
        final int xPersonaje = 550;
        final int frame7 = 7;
        g.drawImage(Recursos.personaje.get(paqueteEnemigo.getRaza()).get(frame7)[0], xPersonaje, yPersonaje1, size,
                size, null);

        mundo.graficarObstaculos(g);
        menuBatalla.graficar(g);

        g.setColor(Color.GREEN);

        final int xPersonaje2 = 25;
        final int yMiniaturas = 5;
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xPersonaje2, yMiniaturas, personaje, miniaturaPersonaje);
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, xPersonaje, yMiniaturas, enemigo, miniaturaEnemigo);

    }

    /**
     * Crea los personajes
     */
    private void crearPersonajes() {
        String nombre = paquetePersonaje.getNombre();
        int salud = paquetePersonaje.getSaludTope();
        int energia = paquetePersonaje.getEnergiaTope();
        int fuerza = paquetePersonaje.getFuerza();
        int destreza = paquetePersonaje.getDestreza();
        int inteligencia = paquetePersonaje.getInteligencia();
        int experiencia = paquetePersonaje.getExperiencia();
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

        nombre = paqueteEnemigo.getNombre();
        salud = paqueteEnemigo.getSaludTope();
        energia = paqueteEnemigo.getEnergiaTope();
        fuerza = paqueteEnemigo.getFuerza();
        destreza = paqueteEnemigo.getDestreza();
        inteligencia = paqueteEnemigo.getInteligencia();
        experiencia = paqueteEnemigo.getExperiencia();
        nivel = paqueteEnemigo.getNivel();
        id = paqueteEnemigo.getId();

        casta = null;
        if (paqueteEnemigo.getCasta().equals("Guerrero")) {
            casta = new Guerrero();
        } else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
            casta = new Hechicero();
        } else if (paqueteEnemigo.getCasta().equals("Asesino")) {
            casta = new Asesino();
        }

        if (paqueteEnemigo.getRaza().equals("Humano")) {
            enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
        } else if (paqueteEnemigo.getRaza().equals("Orco")) {
            enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
        } else if (paqueteEnemigo.getRaza().equals("Elfo")) {
            enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
        }
    }

    /**
     * Enviar ataque
     *
     * @param pAtacar
     *            paquete
     */
    public void enviarAtaque(final PaqueteAtacar pAtacar) {
        try {
            getJuego().getCliente().getSalida().writeObject(gson.toJson(pAtacar));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
        }
    }

    /**
     * Finaliza la batalla
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

            paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
            paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
            paqueteEnemigo.setNivel(enemigo.getNivel());
            paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
            paqueteEnemigo.setDestreza(enemigo.getDestreza());
            paqueteEnemigo.setFuerza(enemigo.getFuerza());
            paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
            paqueteEnemigo.removerBonus();

            paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
            paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);

            getJuego().getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
            getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));

        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
        }
    }

    /**
     * Get paquete personaje
     *
     * @return paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }

    /**
     * Get paquete enemigo
     *
     * @return paquete enemigo
     */
    public PaquetePersonaje getPaqueteEnemigo() {
        return paqueteEnemigo;
    }

    /**
     * Set de turno personaje
     *
     * @param b
     *            turno
     */
    public void setMiTurno(final boolean b) {
        miTurno = b;
        menuBatalla.setHabilitado(b);
        getJuego().getHandlerMouse().setNuevoClick(false);
    }

    /**
     * Getter de personaje
     *
     * @return Personaje pj
     */
    public Personaje getPersonaje() {
        return personaje;
    }

    /**
     * Getter de enemigo
     *
     * @return personaje enemigo
     */
    public Personaje getEnemigo() {
        return enemigo;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return false;
    }
}
