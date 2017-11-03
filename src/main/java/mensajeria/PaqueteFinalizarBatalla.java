package mensajeria;

import java.io.Serializable;

/**
 * Paquete utilizado para finalizar una batalla PvP o PvE
 *
 */
public class PaqueteFinalizarBatalla extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idEnemigo;
    private int ganadorBatalla;

    private int tipoBatalla;

    public static final int BATALLAR_PERSONAJE = 1;
    public static final int BATALLAR_NPC = 2;

    /**
     * Constructor del paquete
     */
    public PaqueteFinalizarBatalla() {
        setComando(Comando.FINALIZARBATALLA);
    }

    /**
     * Getter de id
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
     * Getter tipo batalla
     *
     * @return int tipobatalla
     */
    public int getTipoBatalla() {
        return tipoBatalla;
    }

    /**
     * Setter de tipo batalla
     *
     * @param tipoBatalla
     *            int
     */
    public void setTipoBatalla(final int tipoBatalla) {
        this.tipoBatalla = tipoBatalla;
    }

    /**
     * Getter de id enemigo
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
     *            int enemigo
     */
    public void setIdEnemigo(final int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    /**
     * Getter de ganador de batalla
     *
     * @return int ganador
     */
    public int getGanadorBatalla() {
        return ganadorBatalla;
    }

    /**
     * Setter de ganador de batalla
     *
     * @param ganadorBatalla
     *            int ganador
     */
    public void setGanadorBatalla(final int ganadorBatalla) {
        this.ganadorBatalla = ganadorBatalla;
    }
}
