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
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatallaNPC extends Estado {

    private final Mundo mundo;
    private Personaje personaje;
    private NonPlayableCharacter enemigo;
    private int[] posMouse;
    private final PaquetePersonaje paquetePersonaje;
    private final PaqueteNPC paqueteEnemigoNPC;
    private PaqueteAtacar paqueteAtacar;
    private final PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    private boolean miTurno;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;
    private final BufferedImage miniaturaEnemigo;

    private final MenuBatalla menuBatalla;

    public EstadoBatallaNPC(Juego juego, PaqueteBatalla paqueteBatalla) {
        super(juego);
        mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
        miTurno = paqueteBatalla.isMiTurno();

        paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
        paqueteEnemigoNPC = juego.getNPCsDisponibles().get(paqueteBatalla.getIdEnemigo());

        crearPersonajes();

        menuBatalla = new MenuBatalla(miTurno, personaje);

        miniaturaEnemigo = Recursos.personaje.get("Elfo").get(5)[0];
        miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];

        paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
        paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
        paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigoNPC.getId());
        paqueteFinalizarBatalla.setTipoBatalla(PaqueteBatalla.batallarNPC);

        // por defecto batalla perdida
        juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoNPC.menuPerderBatalla);

        // limpio la accion del mouse
        juego.getHandlerMouse().setNuevoClick(false);

    }

    @Override
    public void actualizar() {

        getJuego().getCamara().setxOffset(-350);
        getJuego().getCamara().setyOffset(150);

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

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta1(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta2(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
                        if (personaje.puedeAtacar()) {
                            seRealizoAccion = true;
                            personaje.habilidadCasta3(enemigo);
                        }
                        haySpellSeleccionada = true;
                    }

                    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
                        seRealizoAccion = true;
                        personaje.serEnergizado(10);
                        haySpellSeleccionada = true;
                    }
                }

                if (haySpellSeleccionada && seRealizoAccion) {
                    if (!enemigo.estaVivo()) {
                        getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                MenuInfoPersonaje.menuGanarBatalla);

                        if (personaje.ganarExperiencia(enemigo.otorgarExp())) {
                            getJuego().getPersonaje().setNivel(personaje.getNivel());
                            getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                    MenuInfoPersonaje.menuSubirNivel);
                            Pantalla.setMenuAsignar(null);
                        }

                        paqueteFinalizarBatalla.setGanadorBatalla(getJuego().getPersonaje().getId());
                        finalizarBatalla();
                        Estado.setEstado(getJuego().getEstadoJuego());

                    } else {
                        enemigo.atacar(personaje);

                        if (!personaje.estaVivo()) {
                            getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
                                    MenuInfoPersonaje.menuPerderBatalla);
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
    public void graficar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getJuego().getAncho(), getJuego().getAlto());
        mundo.graficar(g);

        g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
        g.drawImage(Recursos.personaje.get(paqueteEnemigoNPC.getRaza()).get(7)[0], 550, 75, 256, 256, null);

        mundo.graficarObstaculos(g);
        menuBatalla.graficar(g);

        g.setColor(Color.GREEN);

        EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
        EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);
    }

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

    public PaquetePersonaje getPaquetePersonaje() {
        return paquetePersonaje;
    }

    public PaqueteNPC getPaqueteEnemigo() {
        return paqueteEnemigoNPC;
    }

    public void setMiTurno(boolean b) {
        miTurno = b;
        menuBatalla.setHabilitado(b);
        getJuego().getHandlerMouse().setNuevoClick(false);
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public NonPlayableCharacter getEnemigo() {
        return enemigo;
    }

    @Override
    public boolean esEstadoDeJuego() {
        return false;
    }
}
