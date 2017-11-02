package comandos;

import mensajeria.PaqueteAtacar;

/**
 * Comando para enviar un ataque en una batalla
 *
 *
 */
public class Atacar extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final PaqueteAtacar paqueteAtacar = gson.fromJson(cadenaLeida, PaqueteAtacar.class);
        getJuego().getEstadoBatalla().getEnemigo().actualizarAtributos(paqueteAtacar.getMapPersonaje());
        getJuego().getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
        getJuego().getEstadoBatalla().setMiTurno(true);

    }

}
