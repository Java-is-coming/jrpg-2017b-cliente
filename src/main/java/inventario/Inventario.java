package inventario;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import dominio.Item;
import mensajeria.PaquetePersonaje;

/**
 * Clase para el manejo de inventario
 */
public class Inventario extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int CANTCOLUMNAS = 3;
    private static final int CANTFILAS = 3;
    private final ArrayList<Item> items;

    /**
     * Constructor de inventario
     *
     * @param paquetePersonaje
     *            paquete
     * @throws IOException
     *             excepcion
     */
    public Inventario(final PaquetePersonaje paquetePersonaje) throws IOException {
        setLayout(new GridBagLayout());
        items = new ArrayList<Item>(paquetePersonaje.getItems());
        final GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < CANTFILAS; row++) {
            for (int col = 0; col < CANTCOLUMNAS; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                Celda cellPane;
                if (!items.isEmpty()) {
                    cellPane = new Celda(items.get(0), paquetePersonaje);
                    items.remove(0);
                } else {
                    cellPane = new Celda();
                }

                Border border = null;
                if (row < CANTFILAS - 1) {
                    if (col < CANTCOLUMNAS - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.DARK_GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.DARK_GRAY);
                    }
                } else {
                    if (col < CANTCOLUMNAS - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.DARK_GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
                    }
                }
                cellPane.setBorder(border);
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;

                add(cellPane, gbc);
            }
        }
    }
}
