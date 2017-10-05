package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;

public class PaqueteNPC extends Paquete implements Serializable, Cloneable {
	//https://cdn-images-1.medium.com/max/455/1*snTXFElFuQLSFDnvZKJ6IA.png
	
	private int id;
	private int idMapa;
	private int estado;
	private String nombre;
	private int saludTope;
	private int fuerza;
	private int nivel = 1;
	
	public PaqueteNPC() throws IOException {
		estado = Estado.estadoOffline;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getMapa(){
		return idMapa;
	}

	public void setMapa(int mapa){
		idMapa = mapa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return 0;
	}

	public void setExperiencia(int experiencia) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCasta() {
		return "";
	}


	public void setCasta(String casta) {		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return "";
	}

	public void setRaza(String raza) {
	}

	public int getSaludTope() {
		return saludTope;
	}

	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	public int getEnergiaTope() {
		return 0;
	}

	public void setEnergiaTope(int energiaTope) {
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getDestreza() {
		return 0;
	}

	public void setDestreza(int destreza) {
	}

	public int getInteligencia() {
		return 0;
	}

	public void setInteligencia(int inteligencia) {
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}	
}
