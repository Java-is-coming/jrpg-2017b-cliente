<<<<<<< HEAD
package mensajeria;

import java.io.Serializable;
import java.util.Map;

public class PaqueteDeNPCs extends Paquete implements Serializable, Cloneable {

	private Map<Integer, PaqueteNPC> npcs;

	public PaqueteDeNPCs() {

	}

	public PaqueteDeNPCs(Map<Integer, PaqueteNPC> npcs) {
		this.npcs = npcs;
	}

	public Map<Integer, PaqueteNPC> getNPCs() {
		return npcs;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
=======
package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Paquete para el manejo de NPCs
 *
 */
public class PaqueteDeNPCs extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private Map<Integer, PaqueteNPC> npcs;

    /**
     * Constructor
     */
    public PaqueteDeNPCs() {

    }

    /**
     * Constructor con mapa de NPCs
     *
     * @param npcs
     *            Mapa
     */
    public PaqueteDeNPCs(final Map<Integer, PaqueteNPC> npcs) {
        this.npcs = npcs;
    }

    /**
     * Getter del mapa de NPCs
     *
     * @return Map<Integer, PaqueteNPC> mapa
     */
    public Map<Integer, PaqueteNPC> getNPCs() {
        return npcs;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

}
>>>>>>> origin/master
