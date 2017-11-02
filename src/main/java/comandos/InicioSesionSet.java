package comandos;

import mensajeria.Comando;

/**
 * Ejecuta el inicio de sesion
 *
 */
public class InicioSesionSet extends ComandosCliente {

    @Override
    public void ejecutar() {
        getCliente().getPaqueteUsuario().setComando(Comando.INICIOSESION);
    }

}
