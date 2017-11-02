package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Inicia la ejecucion del comando desconectar
 *
 */
public class Salir extends ComandosCliente {

    @Override
    public void ejecutar() {
        try {
            getCliente().getPaqueteUsuario().setInicioSesion(false);
            getCliente().getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
            getCliente().getSocket().close();
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Error al salir");

        }

    }

}
