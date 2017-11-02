package estados;

import java.awt.Graphics;

import juego.Juego;

/**
 * Clase abstracta para indicar el estado actual del personaje
 *
 */
public abstract class Estado {

    private static Estado estadoActual = null;

    // Tipo de estados
    public static final int ESTADO_OFFLINE = 0;
    public static final int ESTADO_JUEGO = 1;
    public static final int ESTADO_BATALLA = 2;

    private Juego juego;

    /**
     * Constructor
     *
     * @param juego
     *            juego
     */
    public Estado(final Juego juego) {
        this.setJuego(juego);
    }

    /**
     * Actualizar estado
     */
    public abstract void actualizar();

    /**
     * Graficar estado
     *
     * @param g
     *            graphics
     */
    public abstract void graficar(Graphics g);

    /**
     * Set de estado
     *
     * @param estado
     *            estado
     */
    public static void setEstado(final Estado estado) {
        estadoActual = estado;
    }

    /**
     * Get estado actual
     *
     * @return Estado estadoActual
     */
    public static Estado getEstado() {
        return estadoActual;
    }

    /**
     * Es estado juego
     *
     * @return boolean
     */
    public abstract boolean esEstadoDeJuego();

    /**
     * Getter de juego
     *
     * @return the juego
     */
    protected Juego getJuego() {
        return juego;
    }

    /**
     * Setter de juego
     *
     * @param juego
     *            juego
     */
    protected void setJuego(final Juego juego) {
        this.juego = juego;
    }
}
