package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Item;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Celda
 */
public class Celda extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage item;
    private PaquetePersonaje paquetePersonaje;
    private final JLabel label;
    private Item it;

    private static final int WIDTH_HEIGHT = 49;

    /**
     * Constructor
     *
     * @param item
     *            item a mostrar
     * @param paquetePersonaje
     *            personaje
     * @throws IOException
     *             error
     */
    public Celda(final Item item, final PaquetePersonaje paquetePersonaje) throws IOException {
        this.item = item.getBufferedFoto();
        it = item;
        this.paquetePersonaje = paquetePersonaje;
        final ImageIcon icon = new ImageIcon(
                this.item.getScaledInstance(WIDTH_HEIGHT, WIDTH_HEIGHT, Image.SCALE_DEFAULT));
        label = new JLabel(icon);
        actionListenersYLabel(item);
    }

    /**
     * Constructor base
     */
    public Celda() {
        label = new JLabel(
                new ImageIcon(Recursos.noItem.getScaledInstance(WIDTH_HEIGHT, WIDTH_HEIGHT, Image.SCALE_DEFAULT)));
        add(label);
    }

    /**
     * Muestra información del item
     *
     * @param itemInfo
     *            item
     */
    private void actionListenersYLabel(final Item itemInfo) {
        final StringBuilder s = new StringBuilder();

        s.append("<html>" + itemInfo.getNombre() + "<br>");

        if (itemInfo.getBonusSalud() != 0) {
            s.append("+" + itemInfo.getBonusSalud() + " Salud " + "<br>");
        }
        if (itemInfo.getBonusEnergia() != 0) {
            s.append("+" + itemInfo.getBonusEnergia() + " Energia " + "<br>");
        }
        if (itemInfo.getBonusFuerza() != 0) {
            s.append("+" + itemInfo.getBonusFuerza() + " Fuerza " + "<br>");
        }
        if (itemInfo.getBonusDestreza() != 0) {
            s.append("+" + itemInfo.getBonusDestreza() + " Destreza " + "<br>");
        }
        if (itemInfo.getBonusInteligencia() != 0) {
            s.append("+" + itemInfo.getBonusInteligencia() + " Inteligencia");
        }
        s.append("</html>");
        label.setToolTipText(s.toString());

        label.addMouseListener(getMouseListener());

        addMouseListener(getMouseListener());

        add(label);
        this.validate();
        this.repaint();

    }

    /**
     * Limpia el label
     */
    protected void resetLabel() {
        label.setIcon(
                new ImageIcon(Recursos.noItem.getScaledInstance(WIDTH_HEIGHT, WIDTH_HEIGHT, Image.SCALE_DEFAULT)));
        label.setToolTipText(null);
        paquetePersonaje.removerItem(it);
        label.removeMouseListener(getMouseListener());
        removeMouseListener(getMouseListener());
    }

    @Override
    public Dimension getPreferredSize() {
        final int dimension = 60;
        return new Dimension(dimension, dimension);
    }

    /**
     * Getter del label
     *
     * @return JLabel label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Getter de mouselistener
     *
     * @return mouseListener mouse
     */
    MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     * Setter de mouselistener
     *
     * @param mouseListener
     *            mouselistener
     */
    void setMouseListener(final MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(final MouseEvent e) {
            final Object[] options = {"Tirar", "Cancelar"};
            if (e.getClickCount() == 2) {
                final int answer = JOptionPane.showOptionDialog(getParent(), "¿Qué desea hacer?",
                        "Item: " + it.getNombre(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                        options, options[1]);
                // Tirar
                if (answer == 0) {
                    paquetePersonaje.sacarBonus(it.getBonusSalud(), it.getBonusEnergia(), it.getBonusFuerza(),
                            it.getBonusDestreza(), it.getBonusInteligencia());
                    resetLabel();
                }
            }
        }
    };
}
