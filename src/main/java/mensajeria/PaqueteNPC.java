package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import estados.Estado;

public class PaqueteNPC extends Paquete implements Serializable, Cloneable {
	//https://cdn-images-1.medium.com/max/455/1*snTXFElFuQLSFDnvZKJ6IA.png
	
	private int id;
	private int idMapa;
	private int estado;
	private String nombre;
	private String raza = "Elfo";
	private int saludTope;
	private int fuerza;
	private int nivel = 1;
	
	//Posicion
	private float posX;
	private float posY;
	private int direccion;
	private int frame;
	
	public PaqueteNPC() throws IOException {
		estado = Estado.estadoOffline;
	}

 public int getEstado() {
  return estado;
 }

 public void setEstado(int estado) {
  this.estado = estado;
 }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMapa(){
		return idMapa;
	}
	public String getRaza() {
		return raza;
	}

	public void setMapa(int mapa){
		idMapa = mapa;
	}

	public int getId() {
		return id;
	}

 public void setExperiencia(int experiencia) {
 }

	public int getSaludTope() {
		return saludTope;
	}

 public float getPosY() {
  return posY;
 }

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	
	//Posicion
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}	
}