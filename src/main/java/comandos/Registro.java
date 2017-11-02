package comandos;

import javax.swing.JOptionPane;

import frames.MenuCreacionPj;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

/**
 * Registro de usuario
 *
 */
public class Registro extends ComandosCliente {

    @Override
    public void ejecutar() {
        synchronized (this) {

            final Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
            if (paquete.getMensaje().equals(Paquete.MSJ_EXITO)) {

                // Abro el menu para la creaci�n del personaje
                final PaquetePersonaje pPersonaje = getCliente().getPaquetePersonaje();
                final MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(getCliente(), pPersonaje, gson);
                menuCreacionPJ.setVisible(true);

                // Espero a que el usuario cree el personaje

                // Recibo el paquete personaje con los datos (la id incluida)

                // Indico que el usuario ya inicio sesion

            } else {
                if (paquete.getMensaje().equals(Paquete.MSJ_FRACASO)) {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar.");
                }
                // El usuario no pudo iniciar sesión
                getCliente().getPaqueteUsuario().setInicioSesion(false);
            }

        }
    }

}
