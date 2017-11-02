package comandos;

import mensajeria.PaqueteDeNPCs;

/**
 * Actualiza la lista de NPC's en el mapa
 *
 */
public class ActualizarNPCs extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final PaqueteDeNPCs pdm = gson.fromJson(cadenaLeida, PaqueteDeNPCs.class);
        getJuego().setNPCsDisponibles(pdm.getNPCs());
    }

}
