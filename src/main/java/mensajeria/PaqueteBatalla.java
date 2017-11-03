package mensajeria;

import java.io.Serializable;

/**
 * Paquete para la batalla PvP o PvE
 *
 */
public class PaqueteBatalla extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idEnemigo;
    private boolean miTurno;
    private int tipoBatalla;

    public static final int BATALLAR_PERSONAJE = 1;
    public static final int BATALLAR_NPC = 2;

    /**
     * Constructor batalla personaje
     */
    public PaqueteBatalla() {
        setComando(Comando.BATALLA);
        this.tipoBatalla = BATALLAR_PERSONAJE;
    }

    /**
     * Constructor con tipo de batalla personaje o NPC
     *
     * @param tipoBatalla
     *            personaje o NPc
     */
    public PaqueteBatalla(final int tipoBatalla) {
        setComando(Comando.BATALLA);
        this.tipoBatalla = tipoBatalla;
    }

    /**
     * Getter ID
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de id
     *
     * @param id
     *            int
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter id enemigo
     *
     * @return int id enemigo
     */
    public int getIdEnemigo() {
        return idEnemigo;
    }

    /**
     * Setter de id enemigo
     *
     * @param idEnemigo
     *            int id
     */
    public void setIdEnemigo(final int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    /**
     * Getter tipo de batalla
     *
     * @return int tipo batalla
     */
    public int getTipoBatalla() {
        return tipoBatalla;
    }

    /**
     * Setter tipo de batalla
     *
     * @param tBatalla
     *            int
     */
    public void setTipoBatlla(final int tBatalla) {
        this.tipoBatalla = tBatalla;
    }

    /**
     * Getter de turno
     *
     * @return boolean es mi turno
     */
    public boolean isMiTurno() {
        return miTurno;
    }

    /**
     * Setter de turno
     *
     * @param miTurno
     *            boolean
     */
    public void setMiTurno(final boolean miTurno) {
        this.miTurno = miTurno;
    }
}
