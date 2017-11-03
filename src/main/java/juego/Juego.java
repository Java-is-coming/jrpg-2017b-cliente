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

    public void iniciar() { // Carga lo necesario para iniciar el juego
        pantalla = new Pantalla(nombre, ancho, alto, cliente);

        pantalla.getCanvas().addMouseListener(handlerMouse);

        camara = new Camara(this, 0, 0);

        Personaje.cargarTablaNivel();
    }

    private void actualizar() { // Actualiza los objetos y sus posiciones

        if (Estado.getEstado() != null) {
            Estado.getEstado().actualizar();
        }
    }

    private void graficar() { // Grafica los objetos y sus posiciones
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
    public void run() { // Hilo principal del juego

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

    public synchronized void start() { // Inicia el juego
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

    public synchronized void stop() { // Detiene el juego
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

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public HandlerMouse getHandlerMouse() {
        return handlerMouse;
    }

    public Camara getCamara() {
        return camara;
    }

    public EstadoJuego getEstadoJuego() {
        return (EstadoJuego) estadoJuego;
    }

    public EstadoBatalla getEstadoBatalla() {
        return (EstadoBatalla) estadoBatalla;
    }

    public EstadoBatallaNPC getEstadoBatallaNPC() {
        return (EstadoBatallaNPC) estadoBatalla;
    }

    public void setEstadoBatalla(final EstadoBatalla estadoBatalla) {
        this.estadoBatalla = estadoBatalla;
    }

    public void setEstadoBatalla(final EstadoBatallaNPC estadoBatalla) {
        this.estadoBatalla = estadoBatalla;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EscuchaMensajes getEscuchaMensajes() {
        return escuchaMensajes;
    }

    public PaquetePersonaje getPersonaje() {
        return paquetePersonaje;
    }

    public PaqueteMovimiento getUbicacionPersonaje() {
        return ubicacionPersonaje;
    }

    public void setPersonaje(final PaquetePersonaje pPersonaje) {
        this.paquetePersonaje = pPersonaje;
    }

    public void actualizarPersonaje() {
        paquetePersonaje = (PaquetePersonaje) (personajesConectados.get(paquetePersonaje.getId()).clone());
    }

    public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
        return personajesConectados;
    }

    public void setPersonajesConectados(final Map<Integer, PaquetePersonaje> map) {
        this.personajesConectados = map;
    }

    public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
        return ubicacionPersonajes;
    }

    public void setUbicacionPersonajes(final Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
        this.ubicacionPersonajes = ubicacionPersonajes;
    }

    public Map<String, MiChat> getChatsActivos() {
        return chatsActivos;
    }

    public Map<Integer, PaqueteNPC> getNPCsDisponibles() {
        return npcsDisponibles;
    }

    public void setNPCsDisponibles(final Map<Integer, PaqueteNPC> nPCsDisponibles) {
        npcsDisponibles = nPCsDisponibles;
    }
}
