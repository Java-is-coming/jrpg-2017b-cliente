package comandos;

import estados.Estado;

/**
 * Finaliza la batalla con un usuario o NPC
 *
 */
public class FinalizarBatalla extends ComandosEscucha {

    @Override
    public void ejecutar() {
        // final PaqueteFinalizarBatalla paqueteFinalizarBatalla =
        // gson.fromJson(cadenaLeida,
        // PaqueteFinalizarBatalla.class);
        getJuego().getPersonaje().setEstado(Estado.ESTADO_JUEGO);
        Estado.setEstado(getJuego().getEstadoJuego());

    }

}
