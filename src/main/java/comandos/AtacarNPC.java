package comandos;

import mensajeria.PaqueteAtacarNPC;

/**
 * Ataque a un NPC
 */
public class AtacarNPC extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final PaqueteAtacarNPC paqueteAtacarNPC = gson.fromJson(cadenaLeida, PaqueteAtacarNPC.class);
        getJuego().getEstadoBatallaNPC().getPersonaje().actualizarAtributos(paqueteAtacarNPC.getMapPersonaje());
        getJuego().getEstadoBatallaNPC().setMiTurno(true);

    }

}
