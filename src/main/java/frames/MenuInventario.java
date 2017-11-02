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

public class MenuInventario extends JFrame {
    private final JButton cancelar = new JButton("Exit");

    public MenuInventario(final Cliente cliente) {
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        this.setBounds(600, 600, 600, 600);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setLocation(900, 140);
        this.setResizable(false);
        this.setVisible(true);
    }
}
