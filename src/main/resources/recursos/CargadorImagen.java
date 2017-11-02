package recursos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Utilitaria para cargar imagen
 *
 */
public final class CargadorImagen {

    /**
     * Constructor oculto
     */
    private CargadorImagen() {

    }

    /**
     * Carga imagen en buffer
     *
     * @param path
     *            ruta a la imagen
     * @return BufferedImage buffer
     */
    public static BufferedImage cargarImagen(final String path) {
        try {
            return ImageIO.read(CargadorImagen.class.getResource(path));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + path);
        }

        return null;
    }
}
