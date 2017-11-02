package comandos;

import juego.Juego;
import mensajeria.Comando;

/**
 * Clase abstracta para definir los comandos
 *
 */
public abstract class ComandosEscucha extends Comando {
    private Juego juego;

    /**
     * Seter de Juego
     *
     * @param juego
     *            juego
     */
    public void setJuego(final Juego juego) {
        this.juego = juego;
    }

    /**
     * Getter de juego
     *
     * @return Juego juego
     */
    public Juego getJuego() {
        return juego;
    }

}
