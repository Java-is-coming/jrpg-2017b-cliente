package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;

/**
 * Paquete para establecer comercio
 *
 */
public class PaqueteComerciar extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idEnemigo;
    private int listo = 0;
    private ArrayList<Item> itemsADar = new ArrayList<Item>();
    private ArrayList<Item> itemsAObtener = new ArrayList<Item>();
    private boolean solicitudDeComercio;

    /**
     * Constructor paquete
     */
    public PaqueteComerciar() {
        setComando(Comando.COMERCIO);
        solicitudDeComercio = true;
    }

    /**
     * Retorna true si es una solicitud de comercio
     *
     * @return boolean solicitud de comercio
     */
    public boolean isSolicitudDeComercio() {
        return solicitudDeComercio;
    }

    /**
     * Setter de solicitud de comercio
     *
     * @param solicitudDeComercio
     *            bool
     */
    public void setSolicitudDeComercio(final boolean solicitudDeComercio) {
        this.solicitudDeComercio = solicitudDeComercio;
    }

    /**
     * Getter de items a comerciar
     *
     * @return ArrayList<Item> items
     */
    public ArrayList<Item> getItemsADar() {
        return itemsADar;
    }

    /**
     * Setter de items a comerciar
     *
     * @param itemsADar
     *            ArrayList<Item> items
     */
    public void setItemsADar(final ArrayList<Item> itemsADar) {
        this.itemsADar = itemsADar;
    }

    /**
     * Getter de id
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de id
     *
     * @param id
     *            int
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter id "enemigo"
     *
     * @return int id
     */
    public int getIdEnemigo() {
        return idEnemigo;
    }

    /**
     * Setter id "enemigo"
     *
     * @param idEnemigo
     *            int id
     */
    public void setIdEnemigo(final int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    /**
     * Getter de listo
     *
     * @return int listo
     */
    public int getListo() {
        return listo;
    }

    /**
     * Aumenta listo en 1
     */
    public void aumentarListo() {
        this.listo++;
    }

    /**
     * Disminuye listo en 1
     */
    public void disminuirListo() {
        this.listo--;
    }

    /**
     * Getter de items a obtener
     *
     * @return ArrayList<Item> items
     */
    public ArrayList<Item> getItemsAObtener() {
        return itemsAObtener;
    }

    /**
     * Setter de items a obtener
     *
     * @param itemsAObtener
     *            ArrayList<Item> items
     */
    public void setItemsAObtener(final ArrayList<Item> itemsAObtener) {
        this.itemsAObtener = itemsAObtener;
    }
}
