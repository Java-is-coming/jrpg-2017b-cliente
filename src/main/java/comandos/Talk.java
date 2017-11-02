package comandos;

import chat.MiChat;
import chat.VentanaContactos;
import juego.Pantalla;
import mensajeria.PaqueteMensaje;

/**
 * Hablar por chat
 *
 */
public class Talk extends ComandosEscucha {

    @Override
    public void ejecutar() {
        MiChat chat = null;
        String destino;
        getJuego().getCliente().setPaqueteMensaje(gson.fromJson(cadenaLeida, PaqueteMensaje.class));
        if (!(getJuego().getCliente().getPaqueteMensaje().getUserReceptor() == null)) {
            final String userEmisor = getJuego().getCliente().getPaqueteMensaje().getUserEmisor();
            if (!(getJuego().getChatsActivos().containsKey(userEmisor))) {
                chat = new MiChat(getJuego());

                chat.setTitle(getJuego().getCliente().getPaqueteMensaje().getUserEmisor());
                chat.setVisible(true);

                getJuego().getChatsActivos().put(getJuego().getCliente().getPaqueteMensaje().getUserEmisor(), chat);
            }
            destino = getJuego().getCliente().getPaqueteMensaje().getUserEmisor();
        } else {
            // ALL
            if (!getJuego().getChatsActivos().containsKey("Sala")) {
                chat = new MiChat(getJuego());

                chat.setTitle("Sala");
                chat.setVisible(true);

                getJuego().getChatsActivos().put("Sala", chat);
                if (Pantalla.getVentContac() != null) {
                    VentanaContactos.getBotonMc().setEnabled(false);
                }
            }
            destino = "Sala";
        }

        String mensaje = getJuego().getCliente().getPaqueteMensaje().getUserEmisor();
        mensaje += ": ";
        mensaje += getJuego().getCliente().getPaqueteMensaje().getMensaje() + "\n";
        getJuego().getChatsActivos().get(destino).getChat().append(mensaje);

        getJuego().getChatsActivos().get(destino).getTexto().grabFocus();
    }
}
