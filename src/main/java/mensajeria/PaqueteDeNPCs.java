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