package comandos;

import javax.swing.JOptionPane;

import mensajeria.PaquetePersonaje;

/**
 * Comando para crear personaje
 *
 */
public class CrearPersonaje extends ComandosCliente {

    @Override
    public void ejecutar() {
        JOptionPane.showMessageDialog(null, "Registro exitoso.");
        getCliente().setPaquetePersonaje(gson.fromJson(cadenaLeida, PaquetePersonaje.class));
        getCliente().getPaqueteUsuario().setInicioSesion(true);

    }

}
