package juego;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import chat.MiChat;
import cliente.Cliente;
import cliente.EscuchaMensajes;
import dominio.Personaje;
import estados.Estado;
import estados.EstadoBatalla;
import estados.EstadoBatallaNPC;
import estados.EstadoJuego;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;

/**
 * Welcome to WoME
 *
 */
public class Juego implements Runnable {

    private Pantalla pantalla;
    private final String nombre;
    private final int ancho;
    private final int alto;

    private Thread hilo;
    private boolean corriendo;

    private BufferStrategy bs; // Estrategia para graficar mediante buffers (Primero se "grafica" en el/los
                               // buffer/s y finalmente en el canvas)
    private Graphics g;

    // Estados
    private Estado estadoJuego;
    private Estado estadoBatalla;

    // HandlerMouse
    private final HandlerMouse handlerMouse;

    // Camara
    private Camara camara;

    // Conexion
    private final Cliente cliente;
    private final EscuchaMensajes escuchaMensajes;
    private PaquetePersonaje paquetePersonaje;
    private final PaqueteMovimiento ubicacionPersonaje;
    private Map<Integer, PaquetePersonaje> personajesConectados;
    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;

    // Agregamos NPCs
    private Map<Integer, PaqueteNPC> npcsDisponibles;
    // private Map<Integer, PaqueteMovimiento> ubicacionesNPCsDisponibles;

    private final Map<String, MiChat> chatsActivos = new HashMap<>();

    private final CargarRecursos cargarRecursos;

    /**
     * Constructor del juego
     *
     * @param nombre
     *            de la ventana
     * @param ancho
     *            de la ventana
     * @param alto
     *            de la ventana
     * @param cliente
     *            clase cliente
     * @param pp
     *            paquete personaje
     */
    public Juego(final String nombre, final int ancho, final int alto, final Cliente cliente,
            final PaquetePersonaje pp) {
        this.nombre = nombre;
        this.alto = alto;
        this.ancho = ancho;
        this.cliente = cliente;
        this.paquetePersonaje = pp;

        // Inicializo la ubicacion del personaje
        ubicacionPersonaje = new PaqueteMovimiento();
        ubicacionPersonaje.setIdPersonaje(paquetePersonaje.getId());
        ubicacionPersonaje.setFrame(0);
        final int direccion = 6;
        ubicacionPersonaje.setDireccion(direccion);

        // Creo el escucha de mensajes
        escuchaMensajes = new EscuchaMensajes(this);
        escuchaMensajes.start();

        handlerMouse = new HandlerMouse();

        iniciar();

        cargarRecursos = new CargarRecursos(cliente);
        cargarRecursos.start();

    }

    /**
     * Carga lo necesario para iniciar el juego
     */
    public void iniciar() {
        pantalla = new Pantalla(nombre, ancho, alto, cliente);

        pantalla.getCanvas().addMouseListener(handlerMouse);

        camara = new Camara(this, 0, 0);

        Personaje.cargarTablaNivel();
    }

    /**
     * Actualiza los objetos y sus posiciones
     */
    private void actualizar() {

        if (Estado.getEstado() != null) {
            Estado.getEstado().actualizar();
        }
    }

    /**
     * Grafica los objetos y sus posiciones
     */
    private void graficar() {
        bs = pantalla.getCanvas().getBufferStrategy();
        if (bs == null) { // Seteo una estrategia para el canvas en caso de que no tenga una
            final int buffer = 3;
            pantalla.getCanvas().createBufferStrategy(buffer);
            return;
        }

        g = bs.getDrawGraphics(); // Permite graficar el buffer mediante g

        g.clearRect(0, 0, ancho, alto); // Limpiamos la pantalla

        // Graficado de imagenes
        final int style = 1;
        final int size = 15;
        g.setFont(new Font("Book Antiqua", style, size));

        if (Estado.getEstado() != null) {
            Estado.getEstado().graficar(g);
        }

        // Fin de graficado de imagenes

        bs.show(); // Hace visible el próximo buffer disponible
        g.dispose();
    }

    @Override
    /**
     * Hilo principal del juego
     */
    public void run() {

        final int fps = 60; // Cantidad de actualizaciones por segundo que se desean
        final double tiempoLimite = 1000000000;
        final double tiempoPorActualizacion = tiempoLimite / fps; // Cantidad de nanosegundos en FPS deseados
        double delta = 0;
        long ahora;
        long ultimoTiempo = System.nanoTime();
        long timer = 0; // Timer para mostrar fps cada un segundo
        int actualizaciones = 0; // Cantidad de actualizaciones que se realizan realmente

        while (corriendo) {
            ahora = System.nanoTime();
            delta += (ahora - ultimoTiempo) / tiempoPorActualizacion; // Calculo para determinar cuando realizar la
                                                                      // actualizacion y el graficado
            timer += ahora - ultimoTiempo; // Sumo el tiempo transcurrido hasta que se acumule 1 segundo y mostrar los
                                           // FPS
            ultimoTiempo = ahora; // Para las proximas corridas del bucle

            if (delta >= 1) {
                actualizar();
                graficar();
                actualizaciones++;
                delta--;
            }

            if (timer >= tiempoLimite) { // Si paso 1 segundo muestro los FPS
                pantalla.getFrame().setTitle(nombre + " | " + "FPS: " + actualizaciones);
                actualizaciones = 0;
                timer = 0;
            }
        }

        stop();
    }

    /**
     * Inicia el juego
     */
    public synchronized void start() {
        if (corriendo) {
            return;
        }

        estadoJuego = new EstadoJuego(this);
        Estado.setEstado(estadoJuego);
        pantalla.mostrar();
        corriendo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    /**
     * Detiene el juego
     */
    public synchronized void stop() {
        if (!corriendo) {
            return;
        }
        try {
            corriendo = false;
            hilo.join();
        } catch (final InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Fallo al intentar detener el juego.");
        }
    }

    /**
     * Getter del ancho
     *
     * @return int ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Getter del alto
     *
     * @return int alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Getter de handler de mouse
     *
     * @return HandlerMouse handler
     */
    public HandlerMouse getHandlerMouse() {
        return handlerMouse;
    }

    /**
     * Getter de camara
     *
     * @return Camara camara
     */
    public Camara getCamara() {
        return camara;
    }

    /**
     * Getter de estado juegi
     *
     * @return EstadoJuego estado
     */
    public EstadoJuego getEstadoJuego() {
        return (EstadoJuego) estadoJuego;
    }

    /**
     * Getter estado batalla
     *
     * @return EstadoBatalla estado
     */
    public EstadoBatalla getEstadoBatalla() {
        return (EstadoBatalla) estadoBatalla;
    }

    /**
     * Getter estado batalla npc
     *
     * @return EstadoBatallaNPC estado
     */
    public EstadoBatallaNPC getEstadoBatallaNPC() {
        return (EstadoBatallaNPC) estadoBatalla;
    }

    /**
     * Setter estado batalla
     *
     * @param estadoBatalla
     *            estado
     */
    public void setEstadoBatalla(final EstadoBatalla estadoBatalla) {
        this.estadoBatalla = estadoBatalla;
    }

    /**
     * Setter estado batalla
     *
     * @param estadoBatalla
     *            estadoBatallaNPC
     */
    public void setEstadoBatalla(final EstadoBatallaNPC estadoBatalla) {
        this.estadoBatalla = estadoBatalla;
    }

    /**
     * Getter de cliente
     *
     * @return Cliente cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Getter escucha mensajes
     *
     * @return EscuchaMensajes escucha
     */
    public EscuchaMensajes getEscuchaMensajes() {
        return escuchaMensajes;
    }

    /**
     * Getter de paquete personaje
     *
     * @return PaquetePersonaje personaje
     */
    public PaquetePersonaje getPersonaje() {
        return paquetePersonaje;
    }

    /**
     * Getter de ubicacion de personaje
     *
     * @return PaqueteMovimiento paquete
     */
    public PaqueteMovimiento getUbicacionPersonaje() {
        return ubicacionPersonaje;
    }

    /**
     * Setter de personaje
     *
     * @param pPersonaje
     *            paquete
     */
    public void setPersonaje(final PaquetePersonaje pPersonaje) {
        this.paquetePersonaje = pPersonaje;
    }

    /**
     * Actualiza el personaje
     */
    public void actualizarPersonaje() {
        paquetePersonaje = (PaquetePersonaje) (personajesConectados.get(paquetePersonaje.getId()).clone());
    }

    /**
     * Getter de personajes conectados
     *
     * @return Map<Integer, PaquetePersonaje> mapa
     */
    public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
        return personajesConectados;
    }

    /**
     * Setter de personajes conectados
     *
     * @param map
     *            personajes
     */
    public void setPersonajesConectados(final Map<Integer, PaquetePersonaje> map) {
        this.personajesConectados = map;
    }

    /**
     * Getter de ubicacion de personajes
     *
     * @return Map<Integer, PaqueteMovimiento> mapa
     */
    public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
        return ubicacionPersonajes;
    }

    /**
     * Setter de ubicacion de personajes
     *
     * @param ubicacionPersonajes
     *            Map
     */
    public void setUbicacionPersonajes(final Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
        this.ubicacionPersonajes = ubicacionPersonajes;
    }

    /**
     * Chats activos
     *
     * @return Map<String, MiChat> map
     */
    public Map<String, MiChat> getChatsActivos() {
        return chatsActivos;
    }

    /**
     * Getter de NPCs disponibles
     *
     * @return Map<Integer, PaqueteNPC> map
     */
    public Map<Integer, PaqueteNPC> getNPCsDisponibles() {
        return npcsDisponibles;
    }

    /**
     * Setter de NPCs disponibles
     *
     * @param nPCsDisponibles
     *            map
     */
    public void setNPCsDisponibles(final Map<Integer, PaqueteNPC> nPCsDisponibles) {
        npcsDisponibles = nPCsDisponibles;
    }
}
