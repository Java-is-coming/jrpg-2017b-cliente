package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Paquete para el manejo de movimientos
 *
 */
public class PaqueteDeMovimientos extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private Map<Integer, PaqueteMovimiento> personajes;

    /**
     * Constructor
     */
    public PaqueteDeMovimientos() {

    }

    /**
     * Constructor que recibe paquetes de movimientos de personajes
     *
     * @param personajes
     *            paquetes
     */
    public PaqueteDeMovimientos(final Map<Integer, PaqueteMovimiento> personajes) {
        this.personajes = personajes;
    }

    /**
     * Getter de movimientos de personajes
     *
     * @return Map<Integer, PaqueteMovimiento> personajes
     */
    public Map<Integer, PaqueteMovimiento> getPersonajes() {
        return personajes;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

}
