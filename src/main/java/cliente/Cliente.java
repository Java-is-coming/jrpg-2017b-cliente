package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosCliente;
import frames.MenuCarga;
import frames.MenuComerciar;
import frames.MenuJugar;
import frames.MenuMapas;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMensaje;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;
import mundo.Utilitarias;

/**
 * La clase Cliente tiene como función ejecutar el cliente.
 */
public class Cliente extends Thread {

	private Socket cliente;
	private String miIp;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;

	// Objeto gson
	private final Gson gson = new Gson();

	// Paquete usuario y paquete personaje
	private PaqueteUsuario paqueteUsuario;
	private PaquetePersonaje paquetePersonaje;
	private PaqueteComerciar paqueteComercio;
	private PaqueteMensaje paqueteMensaje = new PaqueteMensaje();

	// Acciones que realiza el usuario
	private int accion;

	// MENU COMERCIAR
	private MenuComerciar m1;

	// Ip y puerto
	private String ip;
	private int puerto;

	// Resolución
	private final int anchoPantalla = 800;
	private final int altoPantalla = 600;

	/**
	 * Pide la accion
	 *
	 * @return Devuelve la accion
	 */
	public int getAccion() {
		return accion;
	}

	/**
	 * Setea la accion
	 *
	 * @param accion
	 *            accion a setear
	 */
	public void setAccion(final int accion) {
		this.accion = accion;
	}

	private Juego wome;
	private MenuCarga menuCarga;

	/**
	 * Constructor del Cliente
	 */
	public Cliente() {

		puerto = Utilitarias.parseInt(Utilitarias.archivoLineaAString("puerto.txt"));

		ip = JOptionPane.showInputDialog("Ingrese IP del servidor: (default localhost)");
		if (ip == null) {
			ip = "localhost";
		}
		try {
			cliente = new Socket(ip, puerto);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null,
					"Fallo al iniciar la aplicación. " + "Revise la conexión con el servidor.");
			System.exit(1);
		}
	}

	/**
	 * Construye al objeto cliente conectado con parametros
	 *
	 * @param ip
	 *            direccion ip
	 * @param puerto
	 *            puerto utilizado
	 */
	public Cliente(final String ip, final int puerto) {
		try {
			cliente = new Socket(ip, puerto);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (final IOException e) {
			JOptionPane.showMessageDialog(null,
					"Fallo al iniciar la aplicación. " + "Revise la conexión con el servidor.");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				ComandosCliente comand;
				// Creo el paquete que le voy a enviar al servidor
				paqueteUsuario = new PaqueteUsuario();
				MenuJugar menuJugar = null;
				while (!paqueteUsuario.isInicioSesion()) {

					// Muestro el menú principal
					if (menuJugar == null) {
						menuJugar = new MenuJugar(this);
						menuJugar.setVisible(true);

						// Creo los paquetes que le voy a enviar al servidor
						paqueteUsuario = new PaqueteUsuario();
						paquetePersonaje = new PaquetePersonaje();

						// Espero a que el usuario seleccione alguna accion
						wait();

						comand = (ComandosCliente) Paquete.getObjetoSet(Comando.NOMBREPAQUETE, getAccion());
						comand.setCadena(null);
						comand.setCliente(this);
						comand.ejecutar();

						// Le envio el paquete al servidor
						salida.writeObject(gson.toJson(paqueteUsuario));
					}
					// Recibo el paquete desde el servidor
					final String cadenaLeida = (String) entrada.readObject();
					final Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);

					comand = (ComandosCliente) paquete.getObjeto(Comando.NOMBREPAQUETE);
					comand.setCadena(cadenaLeida);
					comand.setCliente(this);
					comand.ejecutar();
				}

				// Creo un paquete con el comando mostrar mapas
				paquetePersonaje.setComando(Comando.MOSTRARMAPAS);

				// Abro el menu de eleccion del mapa
				final MenuMapas menuElegirMapa = new MenuMapas(this);
				menuElegirMapa.setVisible(true);

				// Espero a que el usuario elija el mapa
				wait();

				// Si clickeo en la Cruz al Seleccionar mapas
				if (paquetePersonaje.getMapa() == 0) {
					paquetePersonaje.setComando(Comando.DESCONECTAR);
					salida.writeObject(gson.toJson(paquetePersonaje));
				} else {
					// Establezco el mapa en el paquete personaje
					paquetePersonaje.setIp(miIp);

					// Le envio el paquete con el mapa seleccionado
					salida.writeObject(gson.toJson(paquetePersonaje));

					// Instancio el juego y cargo los recursos
					wome = new Juego("World Of the Middle Earth", anchoPantalla, altoPantalla, this, paquetePersonaje);

					// Muestro el menu de carga
					menuCarga = new MenuCarga(this);
					menuCarga.setVisible(true);

					// Espero que se carguen todos los recursos
					wait();

					// Inicio el juego
					wome.start();

					// Finalizo el menu de carga
					menuCarga.dispose();
				}
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor durante el inicio de sesión.");
				System.exit(1);
			}
		}

	}

	/**
	 * Pide el cliente
	 *
	 * @return Socket Devuelve el cliente
	 */
	public Socket getSocket() {
		return cliente;
	}

	/**
	 * Set de cliente
	 *
	 * @param socketCliente
	 *            socket
	 */
	public void setSocket(final Socket socketCliente) {
		this.cliente = socketCliente;
	}

	/**
	 * Pide la ip
	 *
	 * @return Devuelve la ip
	 */
	public String getMiIp() {
		return miIp;
	}

	/**
	 * Setea la ip
	 *
	 * @param miIp
	 *            ip a setear
	 */
	public void setMiIp(final String miIp) {
		this.miIp = miIp;
	}

	/**
	 * Pide la entrada
	 *
	 * @return Devuelve la entrada
	 */
	public ObjectInputStream getEntrada() {
		return entrada;
	}

	/**
	 * Setea la entrada
	 *
	 * @param entrada
	 *            entrada a setear
	 */
	public void setEntrada(final ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	/**
	 * Pide la salida
	 *
	 * @return Devuelve la salida
	 */
	public ObjectOutputStream getSalida() {
		return salida;
	}

	/**
	 * Setea la salida
	 *
	 * @param salida
	 *            salida a setear
	 */
	public void setSalida(final ObjectOutputStream salida) {
		this.salida = salida;
	}

	/**
	 * Pide el paquete usuario
	 *
	 * @return Devuelve el paquete usuario
	 */
	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}

	/**
	 * Pide el paquete personaje
	 *
	 * @return Devuelve el paquete personaje
	 */
	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	/**
	 * Pide el juego
	 *
	 * @return Devuelve el juego
	 */
	public Juego getJuego() {
		return wome;
	}

	/**
	 * Pide el menu de carga
	 *
	 * @return Devuelve el menu de carga
	 */
	public MenuCarga getMenuCarga() {
		return menuCarga;
	}

	/**
	 * Actualiza los items del personaje
	 *
	 * @param paqueteActualizado
	 *            paquete
	 */
	public void actualizarItems(final PaquetePersonaje paqueteActualizado) {
		if (paquetePersonaje.getCantItems() != 0
				&& paquetePersonaje.getCantItems() != paqueteActualizado.getCantItems()) {
			paquetePersonaje.anadirItem(paqueteActualizado.getItems().get(paqueteActualizado.getItems().size() - 1));
		}
	}

	/**
	 * Devuelve la ip
	 *
	 * @return ip direccion ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Actualizar paquete pesonaje
	 *
	 * @param pP
	 *            paquete
	 */
	public void actualizarPersonaje(final PaquetePersonaje pP) {
		paquetePersonaje = pP;
	}

	/**
	 * Instancia actual del juego
	 *
	 * @return Juego juego
	 */
	public Juego getWome() {
		return wome;
	}

	/**
	 * Setea juego
	 *
	 * @param wome
	 *            juego
	 */
	public void setWome(final Juego wome) {
		this.wome = wome;
	}

	/**
	 * Devuelve el puerto
	 *
	 * @return int puerto
	 */
	public int getPuerto() {
		return puerto;
	}

	/**
	 * Paquete usuario
	 *
	 * @param paqueteUsuario
	 *            paquete
	 */
	public void setPaqueteUsuario(final PaqueteUsuario paqueteUsuario) {
		this.paqueteUsuario = paqueteUsuario;
	}

	/**
	 * Paquete personaje
	 *
	 * @param paquetePersonaje
	 *            paquete
	 */
	public void setPaquetePersonaje(final PaquetePersonaje paquetePersonaje) {
		this.paquetePersonaje = paquetePersonaje;
	}

	/**
	 * Direccion ip
	 *
	 * @param ip
	 *            direccion
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * Menu carga
	 *
	 * @param menuCarga
	 *            menu
	 */
	public void setMenuCarga(final MenuCarga menuCarga) {
		this.menuCarga = menuCarga;
	}

	/**
	 * Menu comerciar
	 *
	 * @return MenuComerciar
	 */
	public MenuComerciar getM1() {
		return m1;
	}

	/**
	 * Set menu comerciar
	 *
	 * @param m1
	 *            menu
	 */
	public void setM1(final MenuComerciar m1) {
		this.m1 = m1;
	}

	/**
	 * Paquete comerciar
	 *
	 * @return PaqueteComerciar paquete
	 */
	public PaqueteComerciar getPaqueteComercio() {
		return paqueteComercio;
	}

	/**
	 * Set paquete comercio
	 *
	 * @param paqueteComercio
	 *            paquete
	 */
	public void setPaqueteComercio(final PaqueteComerciar paqueteComercio) {
		this.paqueteComercio = paqueteComercio;
	}

	/**
	 * Paquete mensaje
	 *
	 * @return PaqueteMensaje
	 */
	public PaqueteMensaje getPaqueteMensaje() {
		return paqueteMensaje;
	}

	/**
	 * Paquete mensaje
	 *
	 * @param paqueteMensaje
	 *            paquete
	 */
	public void setPaqueteMensaje(final PaqueteMensaje paqueteMensaje) {
		this.paqueteMensaje = paqueteMensaje;
	}
}
