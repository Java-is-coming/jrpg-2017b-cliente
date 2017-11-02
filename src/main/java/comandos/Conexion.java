package comandos;

import java.util.Map;

import javax.swing.DefaultListModel;

import chat.VentanaContactos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

/**
 * Comandos base de conexión
 *
 */
public class Conexion extends ComandosEscucha {

    @Override
    /**
     * Ejecución del comando Conexión
     */
    public void ejecutar() {
        final PaqueteDePersonajes pdp = gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
        getJuego().setPersonajesConectados(pdp.getPersonajes());
        actualizarLista(pdp);
    }

    /**
     * Actualiza la lista de personajes conectados
     *
     * @param pdp
     *            paquete de personajes conectados
     */
    private void actualizarLista(final PaqueteDePersonajes pdp) {
        final DefaultListModel<String> modelo = new DefaultListModel<String>();
        VentanaContactos.getList().removeAll();
        for (final Map.Entry<Integer, PaquetePersonaje> personaje : pdp.getPersonajes().entrySet()) {
            modelo.addElement(personaje.getValue().getNombre());
        }
        modelo.removeElement(getJuego().getPersonaje().getNombre());
        VentanaContactos.getList().setModel(modelo);
    }
}
