package comandos;

import mensajeria.Comando;

/**
 * Ejecuta el comando de salir.
 *
 */
public class SalirSet extends ComandosCliente {

    @Override
    public void ejecutar() {
        getCliente().getPaqueteUsuario().setIp(getCliente().getMiIp());
        getCliente().getPaqueteUsuario().setComando(Comando.SALIR);
    }

}
