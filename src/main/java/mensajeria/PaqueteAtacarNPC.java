package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

public class PaqueteAtacarNPC extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idNPCEnemigo;
	private int nuevaSaludPersonaje;
	private int nuevaEnergiaPersonaje;
	private int nuevaSaludEnemigo;
	private HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
	private HashMap<String, Number> mapNPCEnemigo = new HashMap<String, Number>();

	public PaqueteAtacarNPC(int id, int idNPCEnemigo, int nuevaSalud, int nuevaEnergia, int nuevaSaludEnemigo,
			int nuevaDefensa, double probEvitarDano) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdNPCEnemigo() {
		return idNPCEnemigo;
	}

	public void setIdNPCEnemigo(int idNPCEnemigo) {
		this.idNPCEnemigo = idNPCEnemigo;
	}

	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	public void setNuevaSaludPersonaje(int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	public void setNuevaEnergiaPersonaje(int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	public void setNuevaSaludEnemigo(int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	public HashMap<String, Number> getMapPersonaje() {
		return mapPersonaje;
	}

	public HashMap<String, Number> getMapNPCEnemigo() {
		return mapNPCEnemigo;
	}

}
