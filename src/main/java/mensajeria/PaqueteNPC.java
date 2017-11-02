package mensajeria;

import java.io.IOException;
import java.io.Serializable;

import estados.Estado;

public class PaqueteNPC extends Paquete implements Serializable, Cloneable {
    // https://cdn-images-1.medium.com/max/455/1*snTXFElFuQLSFDnvZKJ6IA.png

    private static final long serialVersionUID = 1L;
    private int id;
    private int idMapa;
    private int estado;
    private String nombre;
    private final String raza = "NPC";
    private int saludTope;
    private int fuerza;

    private int nivel = 1;
    private int dificultad;

    // Posicion
    private float posX;
    private float posY;
    private int direccion;
    private int frame;

    public PaqueteNPC() throws IOException {
        estado = Estado.ESTADO_OFFLINE;
    }

    public int getEstado() {
        return estado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    public void setEstado(final int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public int getMapa() {
        return idMapa;
    }

    public String getRaza() {
        return raza;
    }

    public void setMapa(final int mapa) {
        idMapa = mapa;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getSaludTope() {
        return saludTope;
    }

    public void setSaludTope(final int saludTope) {
        this.saludTope = saludTope;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(final int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(final int dificultad) {
        this.dificultad = dificultad;
    }

    // Posicion
    public float getPosX() {
        return posX;
    }

    public void setPosX(final float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(final float posY) {
        this.posY = posY;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(final int direccion) {
        this.direccion = direccion;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(final int frame) {
        this.frame = frame;
    }

    @Override
    public Object clone() {
        Object obj = null;
        obj = super.clone();
        return obj;
    }
}
