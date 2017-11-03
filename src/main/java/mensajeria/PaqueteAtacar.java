package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Paquete para el envio de un ataque
 *
 */
public class PaqueteAtacar extends Paquete implements Serializable, Cloneable {

    private int id;
    private int idEnemigo;
    private int nuevaSaludPersonaje;
    private int nuevaEnergiaPersonaje;
    private int nuevaSaludEnemigo;
    private int nuevaEnergiaEnemigo;
    private final HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
    private final HashMap<String, Number> mapEnemigo = new HashMap<String, Number>();

    /**
     * Constructor
     *
     * @param id
     *            id
     * @param idEnemigo
     *            enemigo
     * @param nuevaSalud
     *            salud
     * @param nuevaEnergia
     *            energia
     * @param nuevaSaludEnemigo
     *            salud enemigo
     * @param nuevaEnergiaEnemigo
     *            energia enemigo
     * @param nuevaDefensa
     *            defensa
     * @param nuevaDefensaEnemigo
     *            defensa enemigo
     * @param probEvitarDano
     *            prob evitar daño
     * @param probEvitarDanoEnemgio
     *            prov evitar daño del enemigo
     */
    public PaqueteAtacar(final int id, final int idEnemigo, final int nuevaSalud, final int nuevaEnergia,
            final int nuevaSaludEnemigo, final int nuevaEnergiaEnemigo, final int nuevaDefensa,
            final int nuevaDefensaEnemigo, final double probEvitarDano, final double probEvitarDanoEnemgio) {
        setComando(Comando.ATACAR);
        this.id = id;
        this.idEnemigo = idEnemigo;
        this.nuevaSaludPersonaje = nuevaSalud;
        this.nuevaEnergiaPersonaje = nuevaEnergia;
        this.nuevaSaludEnemigo = nuevaSaludEnemigo;
        this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
        mapPersonaje.put("salud", nuevaSalud);
        mapPersonaje.put("energia", nuevaEnergia);
        mapPersonaje.put("defensa", nuevaDefensa);
        mapPersonaje.put("probEvitarDanio", probEvitarDano);
        mapEnemigo.put("salud", nuevaSaludEnemigo);
        mapEnemigo.put("energia", nuevaEnergiaEnemigo);
        mapEnemigo.put("defensa", nuevaDefensaEnemigo);
        mapEnemigo.put("probEvitarDanio", probEvitarDanoEnemgio);
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
     * Getter de id enemigo
     *
     * @return int idenemigo
     */
    public int getIdEnemigo() {
        return idEnemigo;
    }

    /**
     * Setter id enemigo
     *
     * @param idEnemigo
     *            int
     */
    public void setIdEnemigo(final int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    /**
     * Getter nueva salud personaje
     *
     * @return int salud
     */
    public int getNuevaSaludPersonaje() {
        return nuevaSaludPersonaje;
    }

    /**
     * Setter nueva salud personaje
     *
     * @param nuevaSaludPersonaje
     *            salud
     */
    public void setNuevaSaludPersonaje(final int nuevaSaludPersonaje) {
        this.nuevaSaludPersonaje = nuevaSaludPersonaje;
    }

    /**
     * Getter nueva energia del personaje
     *
     * @return int nueva energia
     */
    public int getNuevaEnergiaPersonaje() {
        return nuevaEnergiaPersonaje;
    }

    /**
     * Setter nueva energia del personaje
     *
     * @param nuevaEnergiaPersonaje
     *            int energia
     */
    public void setNuevaEnergiaPersonaje(final int nuevaEnergiaPersonaje) {
        this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
    }

    /**
     * Getter de nueva salud del enemigo
     *
     * @return int nueva salud enemigo
     */
    public int getNuevaSaludEnemigo() {
        return nuevaSaludEnemigo;
    }

    /**
     * Setter nueva salud enemigo
     *
     * @param nuevaSaludEnemigo
     *            salud int
     */
    public void setNuevaSaludEnemigo(final int nuevaSaludEnemigo) {
        this.nuevaSaludEnemigo = nuevaSaludEnemigo;
    }

    /**
     * Getter nueva energia enemigo
     *
     * @return int energia
     */
    public int getNuevaEnergiaEnemigo() {
        return nuevaEnergiaEnemigo;
    }

    /**
     * Setter nueva energia enemigo
     *
     * @param nuevaEnergiaEnemigo
     *            int energia
     */
    public void setNuevaEnergiaEnemigo(final int nuevaEnergiaEnemigo) {
        this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
    }

    /**
     * Getter mapa del personaje
     *
     * @return HashMap<String, Number> mapa
     */
    public HashMap<String, Number> getMapPersonaje() {
        return mapPersonaje;
    }

    /**
     * Getter mapa del enemigo
     *
     * @return HashMap<String, Number> mapa
     */
    public HashMap<String, Number> getMapEnemigo() {
        return mapEnemigo;
    }

}
