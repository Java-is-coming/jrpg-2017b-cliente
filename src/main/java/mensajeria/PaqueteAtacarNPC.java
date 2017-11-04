package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Atacar NPC. Se utiliza en la batalla vs un NPC
 */
public class PaqueteAtacarNPC extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idNPCEnemigo;
    private int nuevaSaludPersonaje;
    private int nuevaEnergiaPersonaje;
    private int nuevaSaludEnemigo;
    private final HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
    private final HashMap<String, Number> mapNPCEnemigo = new HashMap<String, Number>();

    /**
     * Constructor paquete
     *
     * @param id
     *            paquete
     * @param idNPCEnemigo
     *            id npc
     * @param nuevaSalud
     *            salud
     * @param nuevaEnergia
     *            energia
     * @param nuevaSaludEnemigo
     *            salud enemigo
     * @param nuevaDefensa
     *            defensa
     * @param probEvitarDano
     *            probabilidad evitar daño
     */
    public PaqueteAtacarNPC(final int id, final int idNPCEnemigo, final int nuevaSalud, final int nuevaEnergia,
            final int nuevaSaludEnemigo, final int nuevaDefensa, final double probEvitarDano) {
        setComando(Comando.ATACARNPC);
        this.id = id;
        this.idNPCEnemigo = idNPCEnemigo;
        this.nuevaSaludPersonaje = nuevaSalud;
        this.nuevaEnergiaPersonaje = nuevaEnergia;
        this.nuevaSaludEnemigo = nuevaSaludEnemigo;
        mapPersonaje.put("salud", nuevaSalud);
        mapPersonaje.put("energia", nuevaEnergia);
        mapPersonaje.put("defensa", nuevaDefensa);
        mapPersonaje.put("probEvitarDanio", probEvitarDano);
        mapNPCEnemigo.put("salud", nuevaSaludEnemigo);
    }

    /**
     * Get de id
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
     * Get id npc enemigo
     *
     * @return int id npc enemigo
     */
    public int getIdNPCEnemigo() {
        return idNPCEnemigo;
    }

    /**
     * Setter de id npc enemigo
     *
     * @param idNPCEnemigo
     *            int
     */
    public void setIdNPCEnemigo(final int idNPCEnemigo) {
        this.idNPCEnemigo = idNPCEnemigo;
    }

    /**
     * Nueva salud personaje
     *
     * @return int nueva salud
     */
    public int getNuevaSaludPersonaje() {
        return nuevaSaludPersonaje;
    }

    /**
     * Set nueva salud personaje
     *
     * @param nuevaSaludPersonaje
     *            salud
     */
    public void setNuevaSaludPersonaje(final int nuevaSaludPersonaje) {
        this.nuevaSaludPersonaje = nuevaSaludPersonaje;
    }

    /**
     * Get nueva energia
     *
     * @return nueva energia personaje
     */
    public int getNuevaEnergiaPersonaje() {
        return nuevaEnergiaPersonaje;
    }

    /**
     * Set nueva energia
     *
     * @param nuevaEnergiaPersonaje
     *            nueva energia
     */
    public void setNuevaEnergiaPersonaje(final int nuevaEnergiaPersonaje) {
        this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
    }

    /**
     * Get Nueva salud enemigo
     *
     * @return int nueva salud
     */
    public int getNuevaSaludEnemigo() {
        return nuevaSaludEnemigo;
    }

    /**
     * Set nueva salud enemigo
     *
     * @param nuevaSaludEnemigo
     *            nueva salud
     */
    public void setNuevaSaludEnemigo(final int nuevaSaludEnemigo) {
        this.nuevaSaludEnemigo = nuevaSaludEnemigo;
    }

    /**
     * Map de pesonaje
     *
     * @return HashMap<String, Number> map
     */
    public HashMap<String, Number> getMapPersonaje() {
        return mapPersonaje;
    }

    /**
     * Set de map de personaje
     *
     * @return HashMap<String, Number> map
     */
    public HashMap<String, Number> getMapNPCEnemigo() {
        return mapNPCEnemigo;
    }

}
