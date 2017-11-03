package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Clase utilizada para la definición de los paquetes de comunicación
 *
 */
public class Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    public static final String MSJ_EXITO = "1";
    public static final String MSJ_FRACASO = "0";

    private String mensaje;
    private String ip;
    private int comando;

    /**
     * Constructor default
     */
    public Paquete() {

    }

    /**
     * Constructor parametrizado
     *
     * @param mensaje
     *            mensaje del paquete
     * @param nick
     *            del usuario
     * @param ip
     *            del usuario
     * @param comando
     *            comando a enviar
     */
    public Paquete(final String mensaje, final String nick, final String ip, final int comando) {
        this.mensaje = mensaje;
        this.ip = ip;
        this.comando = comando;
    }

    /**
     * Constructor parametrizado
     *
     * @param mensaje
     *            a enviar
     * @param comando
     *            comando a enviar
     */
    public Paquete(final String mensaje, final int comando) {
        this.mensaje = mensaje;
        this.comando = comando;
    }

    /**
     * Constructor parametrizado
     *
     * @param comando
     *            comando a enviar
     */
    public Paquete(final int comando) {
        this.comando = comando;
    }

    /**
     * Setter de mensaje
     *
     * @param mensaje
     *            String
     */
    public void setMensaje(final String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Setter de ip
     *
     * @param ip
     *            direccion
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * Setter de comando
     *
     * @param comando
     *            a enviar
     */
    public void setComando(final int comando) {
        this.comando = comando;
    }

    /**
     * Getter de mensaje
     *
     * @return String mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Getter de ip
     *
     * @return String direccion
     */
    public String getIp() {
        return ip;
    }

    /**
     * Getter de comando
     *
     * @return int comando
     */
    public int getComando() {
        return comando;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (final CloneNotSupportedException ex) {
            JOptionPane.showMessageDialog(null, "Error al clonar");

        }
        return obj;
    }

    /**
     * Devuelve el objeto
     *
     * @param nombrePaquete
     *            paquete
     * @return Comando comando
     */
    public Comando getObjeto(final String nombrePaquete) {
        try {
            Comando c;
            c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
            return c;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }

    }

    /**
     * Devuelve el objeto
     *
     * @param nombrePaquete
     *            paquete
     * @param accion
     *            accion
     * @return Comando objeto
     */
    public static Comando getObjetoSet(final String nombrePaquete, final int accion) {
        try {
            Comando c;
            c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
            return c;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }

    }

}
