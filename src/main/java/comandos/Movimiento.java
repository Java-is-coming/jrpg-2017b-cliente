package comandos;

import mensajeria.PaqueteDeMovimientos;

/**
 * Movimiento del personaje en el mapa
 *
 *
 */
public class Movimiento extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final PaqueteDeMovimientos pdm = gson.fromJson(cadenaLeida, PaqueteDeMovimientos.class);
        getJuego().setUbicacionPersonajes(pdm.getPersonajes());

    }

}
