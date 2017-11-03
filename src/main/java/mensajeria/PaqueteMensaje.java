package mensajeria;

import java.io.Serializable;

/**
 * Paquete de mensaje
 *
 */
public class PaqueteMensaje extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private String userEmisor;
    private String userReceptor;
    private String msj;

    /**
     * Constructor
     */
    public PaqueteMensaje() {
    }

    @Override
    public String getMensaje() {
        return msj;
    }

    @Override
    public void setMensaje(final String mensaje) {
        this.msj = mensaje;
    }

    /**
     * Getter de user emisor
     *
     * @return String usuario
     */
    public String getUserEmisor() {
        return userEmisor;
    }

    /**
     * Setter de user emisor
     *
     * @param idEmisor
     *            user
     */
    public void setUserEmisor(final String idEmisor) {
        this.userEmisor = idEmisor;
    }

    /**
     * Getter de user receptors
     *
     * @return String user
     */
    public String getUserReceptor() {
        return userReceptor;
    }

    /**
     * Setter de user receptor
     *
     * @param idReceptor
     *            user
     */
    public void setUserReceptor(final String idReceptor) {
        this.userReceptor = idReceptor;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }
}
