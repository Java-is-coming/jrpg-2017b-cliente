package mensajeria;

import java.io.Serializable;

/**
 * Paquete que contiene la informacion del usuario
 */
public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int idPj;
    private String username;
    private String password;
    private boolean inicioSesion;

    /**
     * Constructor por defecto
     */
    public PaqueteUsuario() {

    }

    /**
     * Constructor
     *
     * @param pj
     *            id
     * @param user
     *            usuario
     * @param pw
     *            contraseña
     */
    public PaqueteUsuario(final int pj, final String user, final String pw) {
        idPj = pj;
        username = user;
        password = pw;
        inicioSesion = false;
    }

    /**
     * Devuelve el ID del personaje
     *
     * @return int id
     */
    public int getIdPj() {
        return idPj;
    }

    /**
     * Setea el id del personaje
     *
     * @param idPj
     *            id
     */
    public void setIdPj(final int idPj) {
        this.idPj = idPj;
    }

    /**
     * Getter de nombre de usuario
     *
     * @return String nombre
     */
    public String getUsername() {
        return username;
    }

    /**
     * Seta el nombre de usuario
     *
     * @param username
     *            string
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Devuelve la password
     *
     * @return String pwd
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setea la passsword
     *
     * @param password
     *            string
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Determina si es un inicio de sesion
     *
     * @return boolean inicio
     */
    public boolean isInicioSesion() {
        return inicioSesion;
    }

    /**
     * Setea si es un inicio de sesion
     *
     * @param inicioSesion
     *            boolean
     */
    public void setInicioSesion(final boolean inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

}
