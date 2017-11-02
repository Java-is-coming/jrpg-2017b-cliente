package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;

public class PaqueteComerciar extends Paquete implements Serializable, Cloneable {

    private int id;
    private int idEnemigo;
    private int listo = 0;
    private ArrayList<Item> itemsADar = new ArrayList<Item>();
    private ArrayList<Item> itemsAObtener = new ArrayList<Item>();
    private boolean solicitudDeComercio;

    public PaqueteComerciar() {
        setComando(Comando.COMERCIO);
        solicitudDeComercio = true;
    }

    public boolean isSolicitudDeComercio() {
        return solicitudDeComercio;
    }

    public void setSolicitudDeComercio(boolean solicitudDeComercio) {
        this.solicitudDeComercio = solicitudDeComercio;
    }

    public ArrayList<Item> getItemsADar() {
        return itemsADar;
    }

    public void setItemsADar(ArrayList<Item> itemsADar) {
        this.itemsADar = itemsADar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnemigo() {
        return idEnemigo;
    }

    public void setIdEnemigo(int idEnemigo) {
        this.idEnemigo = idEnemigo;
    }

    public int getListo() {
        return listo;
    }

    public void aumentarListo() {
        this.listo++;
    }

    public void disminuirListo() {
        this.listo--;
    }

    public ArrayList<Item> getItemsAObtener() {
        return itemsAObtener;
    }

    public void setItemsAObtener(ArrayList<Item> itemsAObtener) {
        this.itemsAObtener = itemsAObtener;
    }
}
