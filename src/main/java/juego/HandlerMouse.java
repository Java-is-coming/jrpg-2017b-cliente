package juego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Maneja los eventos del mouse
 *
 */
public class HandlerMouse implements MouseListener {

    private final int[] posMouse;
    private final int[] posMouseRecorrido;
    private boolean nuevoRecorrido;
    private boolean nuevoClick;

    /**
     * Constructor
     */
    public HandlerMouse() {
        posMouse = new int[2];
        posMouseRecorrido = new int[2];
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            posMouse[0] = e.getX();
            posMouse[1] = e.getY();
            nuevoClick = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            posMouseRecorrido[0] = e.getX();
            posMouseRecorrido[1] = e.getY();
            nuevoRecorrido = true;
        }
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) {

    }

    @Override
    public void mouseExited(final MouseEvent arg0) {

    }

    @Override
    public void mousePressed(final MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(final MouseEvent arg0) {

    }

    /**
     * Devuelve la posición del mouse
     *
     * @return int posición
     */
    public int[] getPosMouse() {
        return posMouse;
    }

    /**
     * Devuelve la posición recorrido del mouse
     *
     * @return int posición
     */
    public int[] getPosMouseRecorrido() {
        return posMouseRecorrido;
    }

    /**
     * Devuelve si es nuevo recorrido del mouse
     *
     * @return bool posición
     */
    public boolean getNuevoRecorrido() {
        return nuevoRecorrido;
    }

    /**
     * Setea si es nuevo recorrido del mouse
     *
     * @param b
     *            bool
     */
    public void setNuevoRecorrido(final boolean b) {
        nuevoRecorrido = b;
    }

    /**
     * Devuelve si hubo un nuevo click
     *
     * @return bool
     */
    public boolean getNuevoClick() {
        return nuevoClick;
    }

    /**
     * Setea el nuevo click
     *
     * @param b
     *            bool
     */
    public void setNuevoClick(final boolean b) {
        nuevoClick = b;
    }
}
