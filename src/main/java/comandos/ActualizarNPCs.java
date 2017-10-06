package comandos;

import mensajeria.PaqueteDeNPCs;

public class ActualizarNPCs extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteDeNPCs pdm = (PaqueteDeNPCs) gson.fromJson(cadenaLeida,PaqueteDeNPCs.class);
		juego.setNPCsDisponibles(pdm.getNPCs());
	}

}
