package juego;

import entidades.Entidad;

/**
 * Camara del juego
 *
 */
public class Camara {

    private final Juego juego;
    private float yOffset;
    private float xOffset;

    /**
     * Constructor
     *
     * @param juego
     *            the game
     * @param xOffset
     *            desplazamiento en X
     * @param yOffset
     *            desplazamiento en Y
     */
    public Camara(final Juego juego, final float xOffset, final float yOffset) {
        this.juego = juego;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Centrar camara
     *
     * @param e
     *            entidad
     */
    public void centrar(final Entidad e) {
        xOffset = e.getX() - juego.getAncho() / 2 + e.getAncho() / 2;
        yOffset = e.getY() - juego.getAlto() / 2 + e.getAlto() / 2;
    }

    /**
     * Mover camara
     *
     * @param dx
     *            distancia en x
     * @param dy
     *            distancia en y
     */
    public void mover(final float dx, final float dy) {
        xOffset += dx;
        yOffset += dy;
    }

    /**
     * Getter de offset Y
     *
     * @return float offset Y
     */
    public float getyOffset() {
        return yOffset;
    }

    /**
     * Setter de offset Y
     *
     * @param yOffset
     *            offset
     */
    public void setyOffset(final float yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * Getter de offset X
     *
     * @return float offset X
     */
    public float getxOffset() {
        return xOffset;
    }

    /**
     * Setter de offset X
     *
     * @param xOffset
     *            offset
     */
    public void setxOffset(final float xOffset) {
        this.xOffset = xOffset;
    }
}
