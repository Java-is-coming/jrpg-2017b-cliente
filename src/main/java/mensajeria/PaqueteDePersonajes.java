package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Paquete para el manejo de los personajes conectados
 *
 */
public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private Map<Integer, PaquetePersonaje> personajes;

    /**
     * Constructor
     */
    public PaqueteDePersonajes() {

    }

    /**
     * Constructor que recibe un mapa
     *
     * @param personajes
     *            mapa
     */
    public PaqueteDePersonajes(final Map<Integer, PaquetePersonaje> personajes) {
        this.personajes = personajes;
    }

    /**
     * Getter del mapa de personajos
     *
     * @return Map<Integer, PaquetePersonaje> paquete
     */
    public Map<Integer, PaquetePersonaje> getPersonajes() {
        return personajes;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

}
