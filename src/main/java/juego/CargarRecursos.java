package juego;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import cliente.Cliente;
import mensajeria.Comando;
import recursos.Recursos;

/**
 * Thread de carga de los recursos del juego
 *
 */
public class CargarRecursos extends Thread {

    private final Cliente cliente;

    /**
     * Construye el thread
     *
     * @param cliente
     *            cliente
     */
    public CargarRecursos(final Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        synchronized (cliente) {
            try {
                Recursos.cargar(cliente.getMenuCarga());
            } catch (final FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Falló al cargar los recursos");

            } catch (final NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Falló al cargar los recursos");
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(null, "Falló al cargar los recursos");
            }

            cliente.setAccion(Comando.SALIR);
            cliente.notify();
        }
    }

}
