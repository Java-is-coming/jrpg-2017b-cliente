package entidades;

/**
 * Clase Nodo de Tiles donde se encuentran todos los tiles del personaje
 */
public class PilaDeTiles {

    private NodoDePila ptrPila;

    /**
     * Constructor de la Clase Nodo de Tiles
     */
    public PilaDeTiles() {
        setPtrPila(null);
    }

    /**
     * Establece el nuevo siguiente
     *
     * @param nodo
     *            nuevo nodo siguiente
     */
    public void push(final NodoDePila nodo) {
        nodo.establecerSiguiente(getPtrPila());
        setPtrPila(nodo);
    }

    /**
     * Pide el tope
     *
     * @return un nodo de pila con el tope de la pila
     */
    public NodoDePila pop() {
        final NodoDePila tope = getPtrPila();
        if (tope == null) {
            return null;
        }
        setPtrPila(getPtrPila().obtenerSiguiente());
        return tope;
    }

    /**
     * Pregunta si esta vacia la pila de tiles
     *
     * @return true or false
     */
    public boolean estaVacia() {
        return getPtrPila() == null;
    }

    /**
     * Getter de pila
     *
     * @return NodoDePila nodo
     */
    NodoDePila getPtrPila() {
        return ptrPila;
    }

    /**
     * Setter en pila
     *
     * @param ptrPila
     *            nodo
     */
    void setPtrPila(final NodoDePila ptrPila) {
        this.ptrPila = ptrPila;
    }

}
