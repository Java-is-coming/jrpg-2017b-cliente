package mensajeria;

import com.google.gson.Gson;

/**
 * Clase abstracta con la definicion de todos los comandos
 */
public abstract class Comando {
	// Nombre del paquete donde se encuentran las clases con las responsabilidades
	public static final String NOMBREPAQUETE = "comandos";
	public static final String[] CLASSNAMES = { "Conexion", "CrearPersonaje", "Desconectar", "InicioSesion",
			"MostrarMapas", "Movimiento", "Registro", "Salir", "Batalla", "Atacar", "FinalizarBatalla",
			"ActualizarPersonaje", "ActualizarPersonajeLvl", "ActualizarInventario", "Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk", "ActualizarNPCs", "AtacarNPC" };
	public static final String[] CLASSNAMESBIS = { "Conexion", "CrearPersonaje", "Desconectar", "InicioSesionSet",
			"MostrarMapas", "Movimiento", "RegistroSet", "SalirSet", "Batalla", "Atacar", "FinalizarBatalla",
			"ActualizarPersonaje", "ActualizarPersonajeLvl", "ActualizarInventario", "Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk", "ActualizarNPCs", "AtacarNPC" };

	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	public static final int BATALLA = 8;
	public static final int ATACAR = 9;
	public static final int FINALIZARBATALLA = 10;
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int ACTUALIZARPERSONAJELV = 12;
	public static final int ACTUALIZARINVENTARIO = 13;
	public static final int COMERCIO = 14;
	public static final int ACTUALIZARCOMERCIO = 15;
	public static final int TRUEQUE = 16;
	public static final int ACTUALIZARTRUEQUE = 17;
	public static final int TALK = 18;
	public static final int ACTUALIZARNPCS = 19;
	public static final int ATACARNPC = 20;

	protected final Gson gson = new Gson();
	protected String cadenaLeida;

	/**
	 * Set de cadena leida
	 *
	 * @param cadena
	 *            string
	 */
	public void setCadena(final String cadena) {
		this.cadenaLeida = cadena;
	}

	/**
	 * Ejecucion de los comandos
	 */
	public abstract void ejecutar();
}
