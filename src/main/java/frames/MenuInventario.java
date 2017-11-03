package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import cliente.Cliente;
import inventario.Inventario;
import juego.Pantalla;
import mensajeria.Comando;

/**
 * Menu que muestra el inventario
 */
public class MenuInventario extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JButton cancelar = new JButton("Exit");

    /**
     * Constructor del menu
     *
     * @param cliente
     *            cliente
     */
    public MenuInventario(final Cliente cliente) {
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final Gson gson = new Gson();
                    cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARINVENTARIO);
                    cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar inventario");
                }
                Pantalla.setMenuInventario(null);
                dispose();
            }
        });
        this.setTitle("Inventario");
        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
            this.setLayout(new BorderLayout());
            this.add(new Inventario(cliente.getPaquetePersonaje()));
            this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Falló al iniciar el inventario");

        }
        final int bounds = 600;
        this.setBounds(bounds, bounds, bounds, bounds);
        this.pack();
        this.setLocationRelativeTo(null);
        final int locationX = 900;
        final int locationY = 140;
        this.setLocation(locationX, locationY);
        this.setResizable(false);
        this.setVisible(true);
    }
}
