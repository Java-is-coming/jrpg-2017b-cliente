package mundo;

/**
 * Grafo
 */
public class Grafo {

    private int cantidadDeNodos;
    private final int cantidadDeNodosTotal;
    private final Nodo[] nodos;

    /**
     * Constructor
     *
     * @param cantidadDeNodosTotal
     *            cant de nodos total
     */
    public Grafo(final int cantidadDeNodosTotal) {
        cantidadDeNodos = 0;
        nodos = new Nodo[cantidadDeNodosTotal];
        this.cantidadDeNodosTotal = cantidadDeNodosTotal;
    }

    /**
     * Agrega un nodo al grafo
     *
     * @param nodo
     *            a agregar
     */
    public void agregarNodo(final Nodo nodo) {
        nodos[cantidadDeNodos++] = nodo;
    }

    /**
     * Agregar nodo adyacente a otro
     *
     * @param nodoUno
     *            primer nodo
     * @param nodoDos
     *            segundo nodo
     */
    public void agregarAdyacentes(final Nodo nodoUno, final Nodo nodoDos) {
        nodoUno.agregarAdyacente(nodoDos);
    }

    /**
     * Devuelve los nodos del grafo
     *
     * @return Nodo[] nodos
     */
    public Nodo[] obtenerNodos() {
        return nodos;
    }

    /**
     * Devuelve la cantidad de nodos
     *
     * @return int cantidad
     */
    public int obtenerCantidadDeNodos() {
        return cantidadDeNodos;
    }

    /**
     * Cantidad de nodos total
     *
     * @return int total
     */
    public int obtenerCantidadDeNodosTotal() {
        return cantidadDeNodosTotal;
    }

}
