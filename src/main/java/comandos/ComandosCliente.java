package comandos;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Clase abstracta para definir los comandos cliente
 *
 */
public abstract class ComandosCliente extends Comando {
    private Cliente cliente;

    /**
     * Setter de cliente
     *
     * @param cliente
     *            cliente
     */
    public void setCliente(final Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Getter de cliente
     *
     * @return Cliente cliente
     */
    protected Cliente getCliente() {
        return cliente;
    }

}
