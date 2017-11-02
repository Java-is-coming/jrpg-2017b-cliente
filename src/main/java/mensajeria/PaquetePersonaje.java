package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private int id;
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
    private ArrayList<Item> items = new ArrayList<Item>();

    public PaquetePersonaje() throws IOException {
        estado = Estado.ESTADO_OFFLINE;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(final int estado) {
        this.estado = estado;
    }

    public int getMapa() {
        return idMapa;
    }

    public void setMapa(final int mapa) {
        idMapa = mapa;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(final int experiencia) {
        this.experiencia = experiencia;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCasta() {
        return casta;
    }

    public void setCasta(final String casta) {
        this.casta = casta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(final String raza) {
        this.raza = raza;
    }

    public int getSaludTope() {
        return saludTope;
    }

    public void setSaludTope(final int saludTope) {
        this.saludTope = saludTope;
    }

    public int getEnergiaTope() {
        return energiaTope;
    }

    public void setEnergiaTope(final int energiaTope) {
        this.energiaTope = energiaTope;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(final int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(final int destreza) {
        this.destreza = destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(final int inteligencia) {
        this.inteligencia = inteligencia;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }

    public final void anadirItem(final Item i) {
        items.add(i);
    }

    public final void removerItem(final Item i) {
        items.remove(i);
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<Item>(items);
    }

    public final void setItems(final ArrayList<Item> items) {
        this.items = items;
    }

    public final int getItemID(final int index) {
        return items.get(index).getIdItem();
    }

    public final void anadirItem(final int idItem, final String nombre, final int wearLocation, final int bonusSalud,
            final int bonusEnergia, final int bonusAtaque, final int bonusDefensa, final int bonusMagia,
            final String foto, final String fotoEquipado) {
        try {
            items.add(new Item(idItem, nombre, wearLocation, bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa,
                    bonusMagia, foto, fotoEquipado));
            useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Falló al añadir item");

        }
    }

    public final void removerBonus() {
        // Intente usar un iterator y por alguna razón no andaba..
        int i = 0;
        while (i < items.size()) {
            sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    public final void sacarBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque,
            final int bonusDefensa, final int bonusMagia) {
        saludTope -= bonusSalud;
        energiaTope -= bonusEnergia;
        fuerza -= bonusAtaque;
        destreza -= bonusDefensa;
        inteligencia -= bonusMagia;
    }

    public final void ponerBonus() {
        // Intente usar un iterator y por alguna razón no andaba..
        int i = 0;
        while (i < items.size()) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    public void useBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque, final int bonusDefensa,
            final int bonusMagia) {
        saludTope += bonusSalud;
        energiaTope += bonusEnergia;
        fuerza += bonusAtaque;
        destreza += bonusDefensa;
        inteligencia += bonusMagia;
    }

    public int getCantItems() {
        return items.size();
    }

    public void anadirItem(final int idItem) {
        try {
            items.add(new Item(idItem, null, 0, 0, 0, 0, 0, 0, null, null));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Falló al añadir item");
        }

    }

    public Iterator<Item> getIterator() {
        // TODO Auto-generated method stub
        return items.iterator();
    }

    public void removerUltimoItem() {
        items.remove(items.size() - 1);

    }

    public boolean nuevoItem() {
        return items.get(items.size() - 1).getNombre() == null;
    }

    public void ponerBonus(final int cantItems) {
        int i = 0;
        while (i < cantItems) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
            i++;
        }
    }

    public void sacarUltimoItem() {
        final int i = items.size() - 1;
        if (i >= 0) {
            sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
        }
    }

    public void ponerUltimoItem() {
        final int i = items.size() - 1;
        if (i >= 0) {
            useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
                    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
        }
    }

    public void eliminarItems() {
        items.removeAll(items);
    }

    public void actualizarTrueque(final ArrayList<Item> items) {
        this.items.removeAll(this.items);
        for (final Item item : items) {
            this.items.add(item);
        }
    }
}
