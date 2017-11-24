package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import estados.Estado;

/**
 * Paquete que contiene la información de un NPC
 */
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

	// Re generacion
	private int secsToRespawn;
	private Date deathTime;

	/**
	 * Constructor
	 *
	 * @throws IOException
	 *             exception
	 */
	public PaqueteNPC() throws IOException {
		estado = Estado.ESTADO_OFFLINE;
	}

	public PaqueteNPC(int id) {
		super();
		this.id = id;
	}

	public PaqueteNPC(int id, int idMapa, int estado, String nombre, int nivel, int dificultad, float posX, float posY,
			int direccion, int frame, int secsToRespawn) {
		super();
		this.id = id;
		this.idMapa = idMapa;
		this.estado = estado;
		this.nombre = nombre;
		this.nivel = nivel;
		this.dificultad = dificultad;
		this.posX = posX;
		this.posY = posY;
		this.direccion = direccion;
		this.frame = frame;
		this.setSecsToRespawn(secsToRespawn);
	}

	/**
	 * Devuelve el estado
	 *
	 * @return int estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Devuelve el nivel
	 *
	 * @return int nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setea el nivel
	 *
	 * @param nivel
	 *            int
	 */
	public void setNivel(final int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Setea el estado
	 *
	 * @param estado
	 *            int
	 */
	public void setEstado(final int estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve el nombre del NPC
	 *
	 * @return nombre string
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre del NPC
	 *
	 * @param nombre
	 *            del npc
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el ID del mapa
	 *
	 * @return int idMapa
	 */
	public int getMapa() {
		return idMapa;
	}

	/**
	 * Devuelve la raza
	 *
	 * @return String raza
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Setea el mapa
	 *
	 * @param mapa
	 *            int
	 */
	public void setMapa(final int mapa) {
		idMapa = mapa;
	}

	/**
	 * Devuelve el id
	 *
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id
	 *            int
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Devuelve el tope de salud
	 *
	 * @return int salud tope
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Setea el tope de salud
	 *
	 * @param saludTope
	 *            int
	 */
	public void setSaludTope(final int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Devuelve la fuerza
	 *
	 * @return int fuerza
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Setea la fuerza
	 *
	 * @param fuerza
	 *            int
	 */
	public void setFuerza(final int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Devuelve la dificultad del NPC
	 *
	 * @return int dificultad
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * Setea la dificultad
	 *
	 * @param dificultad
	 *            int
	 */
	public void setDificultad(final int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Posicion X
	 *
	 * @return float pos x
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Set de posicion X
	 *
	 * @param posX
	 *            float
	 */
	public void setPosX(final float posX) {
		this.posX = posX;
	}

	/**
	 * Posicion Y
	 *
	 * @return float pos y
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Set de pos y
	 *
	 * @param posY
	 *            float
	 */
	public void setPosY(final float posY) {
		this.posY = posY;
	}

	/**
	 * Direccion
	 *
	 * @return int dir
	 */
	public int getDireccion() {
		return direccion;
	}

	/**
	 * Setea la direccion
	 *
	 * @param direccion
	 *            int
	 */
	public void setDireccion(final int direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el frame
	 *
	 * @return int frame
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * Setea el frame
	 *
	 * @param frame
	 *            int
	 */
	public void setFrame(final int frame) {
		this.frame = frame;
	}

	public int getSecsToRespawn() {
		return secsToRespawn;
	}

	public void setSecsToRespawn(int secsToRespawn) {
		this.secsToRespawn = secsToRespawn;
	}

	public Date getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(Date deathTime) {
		this.deathTime = deathTime;
	}

	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
