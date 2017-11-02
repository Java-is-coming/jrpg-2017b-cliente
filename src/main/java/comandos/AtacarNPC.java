package comandos;

import mensajeria.PaqueteAtacarNPC;

public class AtacarNPC extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteAtacarNPC paqueteAtacarNPC = gson.fromJson(cadenaLeida, PaqueteAtacarNPC.class);
		juego.getEstadoBatallaNPC().getPersonaje().actualizarAtributos(paqueteAtacarNPC.getMapPersonaje());
		juego.getEstadoBatallaNPC().setMiTurno(true);

	}

}
