package comandos;

import estados.Estado;
import estados.EstadoBatalla;
import estados.EstadoBatallaNPC;
import mensajeria.PaqueteBatalla;

/**
 * Comando para iniciar batalla con un contrincante ya sea NPC o personaje
 *
 */
public class Batalla extends ComandosEscucha {

    @Override
    public void ejecutar() {

        final PaqueteBatalla paqueteBatalla = gson.fromJson(cadenaLeida, PaqueteBatalla.class);
        getJuego().getPersonaje().setEstado(Estado.ESTADO_BATALLA);
        Estado.setEstado(null);

        if (paqueteBatalla.getTipoBatalla() == PaqueteBatalla.BATALLAR_PERSONAJE) {
            getJuego().setEstadoBatalla(new EstadoBatalla(getJuego(), paqueteBatalla));
            Estado.setEstado(getJuego().getEstadoBatalla());
        } else {
            getJuego().setEstadoBatalla(new EstadoBatallaNPC(getJuego(), paqueteBatalla));
            Estado.setEstado(getJuego().getEstadoBatallaNPC());
        }

    }

}
