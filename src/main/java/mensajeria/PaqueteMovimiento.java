package mensajeria;

import java.io.Serializable;

/**
 * Paquete para enviar un movimiento
 *
 */
public class PaqueteMovimiento extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private float posX;
    private float posY;
    private int direccion;
    private int frame;

    /**
     * Constructor
     */
    public PaqueteMovimiento() {
        setComando(Comando.MOVIMIENTO);
    }

    /**
     * Constructor con personaje
     *
     * @param idPersonaje
     *            int
     */
    public PaqueteMovimiento(final int idPersonaje) {
        id = idPersonaje;
        setComando(Comando.MOVIMIENTO);
    }

    /**
     * Constructor con personaje y posicion
     *
     * @param idPersonaje
     *            int personaje
     * @param posX
     *            int posicion en X
     * @param posY
     *            int posicion en Y
     */
    public PaqueteMovimiento(final int idPersonaje, final float posX, final float posY) {
        this.id = idPersonaje;
        this.posX = posX;
        this.posY = posY;
        setComando(Comando.MOVIMIENTO);
    }

    /**
     * Getter de id personaje
     *
     * @return int id personaje
     */
    public int getIdPersonaje() {
        return id;
    }

    /**
     * Setter de id personaje
     *
     * @param idPersonaje
     *            int id personaje
     */
    public void setIdPersonaje(final int idPersonaje) {
        this.id = idPersonaje;
    }

    /**
     * Getter de pos en X
     *
     * @return float pos en X
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Setter de pos en x
     *
     * @param posX
     *            float
     */
    public void setPosX(final float posX) {
        this.posX = posX;
    }

    /**
     * Getter de pos en Y
     *
     * @return float pos Y
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Setter de pos en y
     *
     * @param posY
     *            int pos
     */
    public void setPosY(final float posY) {
        this.posY = posY;
    }

    /**
     * Getter de direccion
     *
     * @return int direccion
     */
    public int getDireccion() {
        return direccion;
    }

    /**
     * Setter de direccion
     *
     * @param direccion
     *            int
     */
    public void setDireccion(final int direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter de num de frame
     *
     * @return int frame
     */
    public int getFrame() {
        return frame;
    }

    /**
     * Setter de frame
     *
     * @param frame
     *            int
     */
    public void setFrame(final int frame) {
        this.frame = frame;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }
}
