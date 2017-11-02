package entidades;

/**
 * Clase Nodo de Pila
 */
public class NodoDePila {

    private final int x;
    private final int y;
    private NodoDePila ptrSiguiente;

    /**
     * Constructor de la clase Nodo de Pila
     *
     * @param x
     *            valor de x donde esta el personaje
     * @param y
     *            valor de y donde esta el personaje
     */
    public NodoDePila(final int x, final int y) {
        this.x = x;
        this.y = y;
        ptrSiguiente = null;
    }

    /**
     * Pide el siguiente
     *
     * @return devuelve un nodo de pila con el siguiente
     */
    public NodoDePila obtenerSiguiente() {
        return ptrSiguiente;
    }

    /**
     * Setea el siguiente
     *
     * @param nodo
     *            nuevo nodo a setear
     */
    public void establecerSiguiente(final NodoDePila nodo) {
        ptrSiguiente = nodo;
    }

    /**
     * Pide el valor de X
     *
     * @return devuelve el valor de X
     */
    public int obtenerX() {
        return x;
    }

    /**
     * Pide el valor de Y
     *
     * @return devuelve el valor de Y
     */
    public int obtenerY() {
        return y;
    }

}
