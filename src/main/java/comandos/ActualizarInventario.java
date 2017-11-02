package comandos;

import mensajeria.PaquetePersonaje;

/**
 * Comando para actualizar el inventario de un personaje
 *
 */
public class ActualizarInventario extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final PaquetePersonaje paquetePersonaje = gson.fromJson(cadenaLeida, PaquetePersonaje.class);
        getJuego().getPersonajesConectados().remove(paquetePersonaje.getId());
        getJuego().getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
        if (getJuego().getPersonaje().getId() == paquetePersonaje.getId()) {
            getJuego().actualizarPersonaje();
            getJuego().getEstadoJuego().actualizarPersonaje();
            getJuego().getCliente().actualizarItems(paquetePersonaje);
            final int idPersonaje = paquetePersonaje.getId();
            final PaquetePersonaje pPersonaje = getJuego().getPersonajesConectados().get(idPersonaje);
            getJuego().getCliente().actualizarPersonaje(pPersonaje);
        }

    }

}
