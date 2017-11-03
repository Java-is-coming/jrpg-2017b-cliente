package recursos;

import java.awt.image.BufferedImage;

/**
 * SpriteSheet para los graficos
 */
public class SpriteSheet {

    private final BufferedImage sprite;

    /**
     * Constructor del sprite
     *
     * @param sprite
     *            imagen
     */
    public SpriteSheet(final BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Getter del tile
     *
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param ancho
     *            de la imagen
     * @param alto
     *            de la imagen
     * @return BufferedImage imagen en buffer
     */
    public BufferedImage getTile(final int x, final int y, final int ancho, final int alto) {
        return sprite.getSubimage(x, y, ancho, alto);
    }
}
