package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Clase utilitaria
 *
 */
public final class Utilitarias {

    /**
     * Constructor oculto
     */
    private Utilitarias() {

    }

    /**
     * Archivo a string
     *
     * @param path
     *            ruta al archivo
     * @return String devuelve el contenido en string
     */
    public static String archivoAString(final String path) {
        final StringBuilder builder = new StringBuilder();

        try {
            final BufferedReader br = new BufferedReader(new FileReader(path));
            String linea;

            while ((linea = br.readLine()) != null) {
                builder.append(linea + System.lineSeparator());
            }

            br.close();
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo al intentar cargar el mapa " + path);
        }

        return builder.toString();
    }

    /**
     * Parseo a entero de un string
     *
     * @param numero
     *            string a convertir
     * @return int entero convertido
     */
    public static int parseInt(final String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (final NumberFormatException e) {

            return 0;
        }
    }

}
