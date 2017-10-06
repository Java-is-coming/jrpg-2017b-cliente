package mensajeria;

import java.io.Serializable;

public class PaqueteBatalla extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private boolean miTurno;
	private int tipoBatalla; 
	
	public static final int batallarPersonaje = 1;
	public static final int batallarNPC = 2;

	
	public PaqueteBatalla(){		
		setComando(Comando.BATALLA);
		this.tipoBatalla = batallarPersonaje;
	}
	
	public PaqueteBatalla(int tipoBatalla){		
		setComando(Comando.BATALLA);
		this.tipoBatalla = tipoBatalla;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdEnemigo() {
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo){
		this.idEnemigo = idEnemigo;
	}
	
	public int getTipoBatalla() {
		return tipoBatalla;
	}

	public void setTipoBatlla(int tipoBatalla){
		this.tipoBatalla = tipoBatalla;
	}

	public boolean isMiTurno() {
		return miTurno;
	}

	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}
}
