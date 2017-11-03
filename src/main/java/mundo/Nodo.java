package mundo;

/**
 * Nodo componente de grafo
 */
public class Nodo {

    private final int x;
    private final int y;
    private final int indice;
    private int cantidadDeAdyacentes;
    private final Nodo[] nodosAdyacentes;

    /**
     * Construye un nodo
     *
     * @param indice
     *            numero
     * @param x
     *            posicion x
     * @param y
     *            posicion y
     */
    public Nodo(final int indice, final int x, final int y) {
        this.x = x;
        this.y = y;
        this.indice = indice;
        cantidadDeAdyacentes = 0;
        final int max = 8;
        nodosAdyacentes = new Nodo[max];
    }

    /**
     * Retorna posicion X
     *
     * @return int X
     */
    public int obtenerX() {
        return x;
    }

    /**
     * Retorna posicion Y
     *
     * @return int Y
     */
    public int obtenerY() {
        return y;
    }

    /**
     * Retorna el indice
     *
     * @return int indice
     */
    public int obtenerIndice() {
        return indice;
    }

    /**
     * Retorna los nodos adyacentes
     *
     * @return Nodo[] nodosAdyacentes
     */
    public Nodo[] obtenerNodosAdyacentes() {
        return nodosAdyacentes;
    }

    /**
     * Agrega un nodo adyacente
     *
     * @param nodo
     *            nodo adyacente
     */
    public void agregarAdyacente(final Nodo nodo) {
        nodosAdyacentes[cantidadDeAdyacentes++] = nodo;
    }

    /**
     * Obtiene la cantidad de adyacentes
     *
     * @return int cantidad
     */
    public int obtenerCantidadDeAdyacentes() {
        return cantidadDeAdyacentes;
    }
}
