package comandos;

import mensajeria.PaqueteAtacarNPC;

public class AtacarNPC extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteAtacarNPC paqueteAtacarNPC = gson.fromJson(cadenaLeida, PaqueteAtacarNPC.class);
		getJuego().getEstadoBatallaNPC().getPersonaje().actualizarAtributos(paqueteAtacarNPC.getMapPersonaje());
		getJuego().getEstadoBatallaNPC().setMiTurno(true);

	}

}
