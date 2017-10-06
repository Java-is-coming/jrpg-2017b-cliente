package mensajeria;

import java.io.Serializable;

public class PaqueteFinalizarBatalla extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int ganadorBatalla;
	
	private int tipoBatalla; 
	
	public static final int batallarPersonaje = 1;
	public static final int batallarNPC = 2;
	
	public PaqueteFinalizarBatalla(){
		setComando(Comando.FINALIZARBATALLA);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTipoBatalla() {
		return tipoBatalla;
	}

	public void setTipoBatalla(int tipoBatalla) {
		this.tipoBatalla = tipoBatalla;
	}	

	public int getIdEnemigo() {
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	public int getGanadorBatalla() {
		return ganadorBatalla;
	}

	public void setGanadorBatalla(int ganadorBatalla) {
		this.ganadorBatalla = ganadorBatalla;
	}
}
