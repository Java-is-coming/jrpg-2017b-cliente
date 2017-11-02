package comandos;

import mensajeria.Comando;

/**
 * Hace efectivo el registro de un usuario
 *
 */
public class RegistroSet extends ComandosCliente {

    @Override
    public void ejecutar() {
        getCliente().getPaqueteUsuario().setComando(Comando.REGISTRO);

    }

}
