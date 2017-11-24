package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;

/**
 * Paquete con toda la informacion del personaje
 */
public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int idInventario;
    private int idMochila;
    private int idMapa;
    private int estado;
    private String casta;
    private String nombre;
    private String raza;
    private int saludTope;
    private int energiaTope;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int nivel = 1;
    private int experiencia;
    private int idAlianza = -1;

    private boolean modoDios = false;
    private boolean modoNoClip = false;
    private int modoFuerza = 0;
    private boolean modoInvisible = false;

    /**
     *
     * @return
     */
    public boolean getModoDios() {
        return modoDios;
    }

    public void setModoDios(final boolean modoDios) {
        this.modoDios = modoDios;
    }

    public boolean getModoNoClip() {
        return modoNoClip;
    }

    public void setModoNoClip(final boolean modoNoClip) {
        this.modoNoClip = modoNoClip;
    }

    public int getModoFuerza() {
        return modoFuerza;
    }

    public void setModoFuerza(final int modoFuerza) {
        this.modoFuerza = modoFuerza;
    }

    public boolean getModoInvisible() {
        return modoInvisible;
    }

    public void setModoInvisible(final boolean modoInvisible) {
        this.modoInvisible = modoInvisible;
    }

    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Constructor
     *
     * @throws IOException
     *             error
     */
    public PaquetePersonaje() throws IOException {
        estado = Estado.ESTADO_OFFLINE;
    }

    /**
     * Get de estado
     *
     * @return int estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Set de estado
     *
     * @param estado
     *            int
     */
    public void setEstado(final int estado) {
        this.estado = estado;
    }

    /**
     * Get de mapa
     *
     * @return mapa int
     */
    public int getMapa() {
        return idMapa;
    }

    /**
     * Setter de mapa
     *
     * @param mapa
     *            int
     */
    public void setMapa(final int mapa) {
        idMapa = mapa;
    }

    /**
     * Get de nivel
     *
     * @return int nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Set nivel
     *
     * @param nivel
     *            int
     */
    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    /**
     * Get experiencia
     *
     * @return int exp
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * Set experiencia
     *
     * @param experiencia
     *            int
     */
    public void setExperiencia(final int experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * Get de id
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
     * Get de casta
     *
     * @return string Casta
     */
    public String getCasta() {
        return casta;
    }

    /**
     * Set de casta
     *
     * @param casta
     *            string
     */
    public void setCasta(final String casta) {
        this.casta = casta;
    }

    /**
     * Getter de nombre
     *
     * @return string nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre
     *
     * @param nombre
     *            string
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get raza
     *
     * @return string raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Set de raza
     *
     * @param raza
     *            string
     */
    public void setRaza(final String raza) {
        this.raza = raza;
    }

    /**
     * Get salud tope
     *
     * @return int salud tope
     */
    public int getSaludTope() {
        return saludTope;
    }

    /**
     * Set salud tope
     *
     * @param saludTope
     *            int
     */

    public void setSaludTope(final int saludTope) {
        this.saludTope = saludTope;
    }

    /**
     * Get energia tope
     *
     * @return energia tope
     */
    public int getEnergiaTope() {
        return energiaTope;
    }

    /**
     * Set energia tope
     *
     * @param energiaTope
     *            energia
     */
    public void setEnergiaTope(final int energiaTope) {
        this.energiaTope = energiaTope;
    }

    /**
     * Get de fuerza
     *
     * @return fuerza int
     */
    public int getFuerza() {

        int fuerzaCalc = fuerza;

        for (int i = 0; i < Math.abs(modoFuerza); i++) {
            if (modoFuerza > 0) {
                fuerzaCalc *= 2;
            } else if (modoFuerza < 0) {
                fuerzaCalc /= 2;
            }
        }

        return fuerzaCalc;
    }

    /**
     * Set fuerza
     *
     * @param fuerza
     *            int
     */
    public void setFuerza(final int fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * Get destreza
     *
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Set destreza
     *
     * @param destreza
     *            int
     */
    public void setDestreza(final int destreza) {
        this.destreza = destreza;
    }

    /**
     * Get inteligencia
     *
     * @return int inteligencia
     */
    public int getInteligencia() {
        return inteligencia;
    }

    /**
     * Set inteligencia
     *
     * @param inteligencia
     *            int
     */
    public void setInteligencia(final int inteligencia) {
        this.inteligencia = inteligencia;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

    /**
     * Agregar item
     *
     * @param i
     *            item
     */
    public final void anadirItem(final Item i) {
        items.add(i);
    }

    /**
     * Remover item
     *
     * @param i
     *            item
     */
    public final void removerItem(final Item i) {
        items.remove(i);
    }

    /**
     * Get de items
     *
     * @return ArrayList<Item> items
     */
    public ArrayList<Item> getItems() {
        return new ArrayList<Item>(items);
    }

    /**
     * Set de items
     *
     * @param items
     *            ArrayList<Item>
     */
    public final void setItems(final ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Get de item ID
     *
     * @param index
     *            indice
     * @return id item
     */
    public final int getItemID(final int index) {
        return items.get(index).getIdItem();
    }

    /**
     * Agregar item
     *
     * @param idItem
     *            id
     * @param nombreItem
     *            item
     * @param wearLocation
     *            parte
     * @param bonusSalud
     *            bonus salud
     * @param bonusEnergia
     *            bonus energia
     * @param bonusAtaque
     *            bonus ataque
     * @param bonusDefensa
     *            bonus def
     * @param bonusMagia
     *            bonus magia
     * @param foto
     *            foto item
     * @param fotoEquipado
     *            foto equipado
     */
    public final void anadirItem(final int idItem, final String nombreItem, final int wearLocation,
            final int bonusSalud, final int bonusEnergia, final int bonusAtaque, final int bonusDefensa,
            final int bonusMagia, final String foto, final String fotoEquipado) {
        try {
            items.add(new Item(idItem, nombreItem, wearLocation, bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa,
                    bonusMagia, foto, fotoEquipado));
            useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Falló al añadir item");

        }
    }

    /**
     * Remover bonus
     */
    public final void removerBonus() {
        // Intente usar un iterator y por alguna razón no andaba..
        int i = 0;
        while (i < items.size()) {
            sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    /**
     * Sacar bonus
     *
     * @param bonusSalud
     *            hp
     * @param bonusEnergia
     *            energia
     * @param bonusAtaque
     *            atk
     * @param bonusDefensa
     *            def
     * @param bonusMagia
     *            magia
     */
    public final void sacarBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque,
            final int bonusDefensa, final int bonusMagia) {
        saludTope -= bonusSalud;
        energiaTope -= bonusEnergia;
        fuerza -= bonusAtaque;
        destreza -= bonusDefensa;
        inteligencia -= bonusMagia;
    }

    /**
     * Poner bonus
     */
    public final void ponerBonus() {
        // Intente usar un iterator y por alguna razón no andaba..
        int i = 0;
        while (i < items.size()) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    /**
     * Usar bonus
     *
     * @param bonusSalud
     *            hp
     * @param bonusEnergia
     *            eng
     * @param bonusAtaque
     *            atk
     * @param bonusDefensa
     *            def
     * @param bonusMagia
     *            mg
     */
    public void useBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque, final int bonusDefensa,
            final int bonusMagia) {
        saludTope += bonusSalud;
        energiaTope += bonusEnergia;
        fuerza += bonusAtaque;
        destreza += bonusDefensa;
        inteligencia += bonusMagia;
    }

    /**
     * Cant items
     *
     * @return int cantidad
     */
    public int getCantItems() {
        return items.size();
    }

    /**
     * Agregar item
     *
     * @param idItem
     *            id del item
     */
    public void anadirItem(final int idItem) {
        try {
            items.add(new Item(idItem, null, 0, 0, 0, 0, 0, 0, null, null));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Falló al añadir item");
        }

    }

    /**
     * Iterador
     *
     * @return Iterador de item
     */
    public Iterator<Item> getIterator() {
        // TODO Auto-generated method stub
        return items.iterator();
    }

    /**
     * Remueve el ultimo item
     */
    public void removerUltimoItem() {
        items.remove(items.size() - 1);

    }

    /**
     * Nuevo item
     *
     * @return boolean resultado
     */
    public boolean nuevoItem() {
        return items.get(items.size() - 1).getNombre() == null;
    }

    /**
     * Bonus
     *
     * @param cantItems
     *            items
     */
    public void ponerBonus(final int cantItems) {
        int i = 0;
        while (i < cantItems) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    /**
     * Sacar ultimo item
     */
    public void sacarUltimoItem() {
        final int i = items.size() - 1;
        if (i >= 0) {
            sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
        }
    }

    /**
     * Poner ultimo item
     */
    public void ponerUltimoItem() {
        final int i = items.size() - 1;
        if (i >= 0) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
        }
    }

    /**
     * Eliminar items
     */
    public void eliminarItems() {
        items.removeAll(items);
    }

    /**
     * Actualizar trueque
     *
     * @param itemsTrueque
     *            array
     */
    public void actualizarTrueque(final ArrayList<Item> itemsTrueque) {
        this.items.removeAll(this.items);
        for (final Item item : itemsTrueque) {
            this.items.add(item);
        }
    }

    /**
     * Get de id alianza
     *
     * @return int id alianza
     */
    public int getIdAlianza() {
        return idAlianza;
    }

    /**
     * Set id alianza
     *
     * @param idAlianza
     *            int
     */
    public void setIdAlianza(final int idAlianza) {
        this.idAlianza = idAlianza;
    }

    /**
     * Get id inventario
     *
     * @return int id inventario
     */
    public int getIdInventario() {
        return idInventario;
    }

    /**
     * Set id inventario
     *
     * @param idInventario
     *            int
     */
    public void setIdInventario(final int idInventario) {
        this.idInventario = idInventario;
    }

    /**
     * Get id mochila
     *
     * @return int mochila
     */
    public int getIdMochila() {
        return idMochila;
    }

    /**
     * Setter id mochila
     *
     * @param idMochila
     *            int
     */
    public void setIdMochila(final int idMochila) {
        this.idMochila = idMochila;
    }
}
