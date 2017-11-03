package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Tile
 */
public class Tile {

    private static final int PISO_BASE = 3;
    private static final int MAX_TILES = 256;
    private static Tile[] tiles = new Tile[MAX_TILES];
    private static Tile[] aubenor;
    private static Tile[] aris;

    // es el piso de aubenor por defecto si queres llamarlo asi, es gris
    private static int arisBase = PISO_BASE;
    private static int aubenorBase = PISO_BASE;

    public static final int ANCHO = 64;
    public static final int ALTO = 32;

    private BufferedImage textura;
    private final int id;

    private boolean esSolido;

    private int ancho;
    private int alto;

    /**
     * Constructor
     *
     * @param textura
     *            buffer
     * @param id
     *            id textura
     * @param esSolido
     *            es o no solido
     */
    public Tile(final BufferedImage textura, final int id, final boolean esSolido) {
        this.setTextura(textura);
        this.id = id;
        getTiles()[id] = this;
        this.esSolido = esSolido;
    }

    /**
     * Constructor
     *
     * @param textura
     *            buffer
     * @param id
     *            id textura
     * @param esSolido
     *            es o no solido
     * @param ancho
     *            textura
     * @param alto
     *            textura
     */
    public Tile(final BufferedImage textura, final int id, final boolean esSolido, final int ancho, final int alto) {
        this.setTextura(textura);
        this.id = id;
        getTiles()[id] = this;
        this.setAncho(ancho);
        this.setAlto(alto);
        this.esSolido = esSolido;
    }

    /**
     * Actualizar
     */
    public void actualizar() {

    }

    /**
     * Graficar tile
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     */
    public void graficar(final Graphics g, final int x, final int y) {
        g.drawImage(getTextura(), x, y, ANCHO, ALTO, null);
    }

    /**
     * Graficar tile
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param width
     *            ancho
     * @param height
     *            alto
     */
    public void graficar(final Graphics g, final int x, final int y, final int width, final int height) {
        g.drawImage(getTextura(), x, y, width, height, null);
    }

    /**
     * Set solidez
     *
     * @param solidez
     *            boolean
     */
    public void setSolido(final boolean solidez) {
        esSolido = solidez;
    }

    /**
     * Getter de solidez
     *
     * @return boolean solidez
     */
    public boolean esSolido() {
        return esSolido;
    }

    /**
     * Get id de tile
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Get de ancho
     *
     * @return int ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Get de alto
     *
     * @return int alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Get de array de tiles
     *
     * @return Tile[] tiles
     */
    public static Tile[] getTiles() {
        return tiles;
    }

    /**
     * Set de array de tiles
     *
     * @param tiles
     *            Tile[]
     */
    public static void setTiles(final Tile[] tiles) {
        Tile.tiles = tiles;
    }

    /**
     * Aubenor
     *
     * @return Tile[] tiles
     */
    public static Tile[] getAubenor() {
        return aubenor;
    }

    /**
     * Aubenor
     *
     * @param aubenor
     *            Tile[] tiles
     */
    public static void setAubenor(final Tile[] aubenor) {
        Tile.aubenor = aubenor;
    }

    /**
     * Aris
     *
     * @return Tile[] tiles
     */
    public static Tile[] getAris() {
        return aris;
    }

    /**
     * Aris
     *
     * @param aris
     *            Tile[] tiles
     */
    public static void setAris(final Tile[] aris) {
        Tile.aris = aris;
    }

    /**
     * Aris base
     *
     * @return int arisBase
     */
    public static int getArisBase() {
        return arisBase;
    }

    /**
     * Aris base
     *
     * @param arisB
     *            int base
     */
    public static void setArisBase(final int arisB) {
        Tile.arisBase = arisB;
    }

    /**
     * Aubenor base
     *
     * @return int base
     */
    public static int getAubenorBase() {
        return aubenorBase;
    }

    /**
     * Set de base aubenor
     *
     * @param aubenorBase
     *            base
     */
    public static void setAubenorBase(final int aubenorBase) {
        Tile.aubenorBase = aubenorBase;
    }

    /**
     * Textura
     *
     * @return buffer textura
     */
    protected BufferedImage getTextura() {
        return textura;
    }

    /**
     * Textura
     *
     * @param textura
     *            set textura
     */
    protected void setTextura(final BufferedImage textura) {
        this.textura = textura;
    }

    /**
     * Ancho
     *
     * @param ancho
     *            int ancho
     */
    protected void setAncho(final int ancho) {
        this.ancho = ancho;
    }

    /**
     * Alto
     *
     * @param alto
     *            int alto
     */
    protected void setAlto(final int alto) {
        this.alto = alto;
    }

}
