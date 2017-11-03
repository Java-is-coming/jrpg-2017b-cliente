package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Casta;
import dominio.NonPlayableCharacter;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoNPC;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteAtacarNPC;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatallaNPC extends Estado {

	private Mundo mundo;
	private Personaje personaje;
	private NonPlayableCharacter enemigo;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private PaqueteNPC paqueteEnemigoNPC;
	private PaqueteAtacarNPC paqueteAtacarNPC;
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	private boolean miTurno;

	private boolean haySpellSeleccionada;
	private boolean seRealizoAccion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;

	private MenuBatalla menuBatalla;

	public EstadoBatallaNPC(Juego juego, PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();

		paquetePersonaje = getJuego().getPersonajesConectados().get(paqueteBatalla.getId());
		paqueteEnemigoNPC = getJuego().getNPCsDisponibles().get(paqueteBatalla.getIdEnemigo());

		crearPersonajes();

		menuBatalla = new MenuBatalla(miTurno, personaje);

		miniaturaEnemigo = Recursos.personaje.get("Elfo").get(5)[0];
		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];

		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
		paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigoNPC.getId());
		paqueteFinalizarBatalla.setTipoBatalla(PaqueteBatalla.BATALLAR_NPC);

		// por defecto batalla perdida
		getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(), MenuInfoNPC.MENU_PERDERBATALLA);

		// limpio la accion del mouse
		getJuego().getHandlerMouse().setNuevoClick(false);

	}

	@Override
	public void actualizar() {

		getJuego().getCamara().setxOffset(-350);
		getJuego().getCamara().setyOffset(150);

		seRealizoAccion = false;
		haySpellSeleccionada = false;

		if (miTurno) {

			if (!personaje.estaVivo()) {
				getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
						MenuInfoPersonaje.MENU_PERDERBATALLA);
				paqueteFinalizarBatalla.setGanadorBatalla(paqueteFinalizarBatalla.getIdEnemigo());
				finalizarBatalla();
				Estado.setEstado(getJuego().getEstadoJuego());
			} else {

				if (getJuego().getHandlerMouse().getNuevoClick()) {
					posMouse = getJuego().getHandlerMouse().getPosMouse();

					if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
							if (personaje.puedeAtacar()) {
								seRealizoAccion = true;
								personaje.habilidadRaza1(enemigo);
							}
							haySpellSeleccionada = true;
						}

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
							if (personaje.puedeAtacar()) {
								seRealizoAccion = true;
								personaje.habilidadRaza2(enemigo);
							}
							haySpellSeleccionada = true;
						}

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
							if (personaje.puedeAtacar()) {
								seRealizoAccion = true;
								personaje.habilidadCasta1(enemigo);
							}
							haySpellSeleccionada = true;
						}

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
							if (personaje.puedeAtacar()) {
								seRealizoAccion = true;
								personaje.habilidadCasta2(enemigo);
							}
							haySpellSeleccionada = true;
						}

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
							if (personaje.puedeAtacar()) {
								seRealizoAccion = true;
								personaje.habilidadCasta3(enemigo);
							}
							haySpellSeleccionada = true;
						}

						if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
							seRealizoAccion = true;
							personaje.serEnergizado(10);
							haySpellSeleccionada = true;
						}
					}

					if (haySpellSeleccionada && seRealizoAccion) {
						if (!enemigo.estaVivo()) {
							getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
									MenuInfoPersonaje.MENU_GANARBATALLA);

							if (personaje.ganarExperiencia(enemigo.otorgarExp())) {
								getJuego().getPersonaje().setNivel(personaje.getNivel());
								getJuego().getEstadoJuego().setHaySolicitud(true, getJuego().getPersonaje(),
										MenuInfoPersonaje.MENU_SUBIRNIVEL);
								Pantalla.setMenuAsignar(null);
							}

							paqueteFinalizarBatalla.setGanadorBatalla(getJuego().getPersonaje().getId());
							finalizarBatalla();
							Estado.setEstado(getJuego().getEstadoJuego());

						} else {

							paqueteAtacarNPC = new PaqueteAtacarNPC(paquetePersonaje.getId(), paqueteEnemigoNPC.getId(),
									personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(),
									personaje.getDefensa(), personaje.getCasta().getProbabilidadEvitarDaño());

							enviarAtaque(paqueteAtacarNPC);
							miTurno = false;
							menuBatalla.setHabilitado(false);
						}
					} else if (haySpellSeleccionada && !seRealizoAccion) {
						JOptionPane.showMessageDialog(null,
								"No posees la energía suficiente para realizar esta habilidad.");
					}

					getJuego().getHandlerMouse().setNuevoClick(false);
				}
			}
		}
	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getJuego().getAncho(), getJuego().getAlto());
		mundo.graficar(g);

		g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		g.drawImage(Recursos.personaje.get(paqueteEnemigoNPC.getRaza()).get(7)[0], 550, 75, 256, 256, null);

		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);

		g.setColor(Color.GREEN);

		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);
	}

	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();

		Casta casta = null;
		try {
			casta = (Casta) Class.forName("dominio" + "." + paquetePersonaje.getCasta()).newInstance();
			personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza())
					.getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
							Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)
					.newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}

		nombre = paqueteEnemigoNPC.getNombre();
		salud = paqueteEnemigoNPC.getSaludTope();
		fuerza = paqueteEnemigoNPC.getFuerza();
		nivel = paqueteEnemigoNPC.getNivel();
		id = paqueteEnemigoNPC.getId();
		int dificultad = paqueteEnemigoNPC.getDificultad();

		enemigo = new NonPlayableCharacter(nombre, nivel, dificultad);
	}

	public void enviarAtaque(PaqueteAtacarNPC paqueteAtacarNPC) {
		try {
			getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteAtacarNPC));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
		}
	}

	private void finalizarBatalla() {
		try {
			getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));

			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			paquetePersonaje.removerBonus();

			// paqueteEnemigoNPC.setSaludTope(enemigo.getSalud());
			// paqueteEnemigoNPC.setFuerza(enemigo.getFuerza());

			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			// paqueteEnemigoNPC.setComando(Comando.ACTUALIZARPERSONAJE);

			getJuego().getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			// getJuego().getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigoNPC));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
		}
	}

	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	public PaqueteNPC getPaqueteEnemigo() {
		return paqueteEnemigoNPC;
	}

	public void setMiTurno(boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		getJuego().getHandlerMouse().setNuevoClick(false);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public NonPlayableCharacter getEnemigo() {
		return enemigo;
	}

	@Override
	public boolean esEstadoDeJuego() {
		return false;
	}
}
