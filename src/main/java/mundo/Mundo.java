package mundo;

import java.awt.Graphics;

import estados.Estado;
import juego.Juego;

/**
 * The World
 */
public class Mundo {
	private final Juego juego;
	private int ancho;
	private int alto;
	private int xOffset;
	private int yOffset;

	private float[] iso = new float[2];
	private int[][] tiles;
	private int[][] tilesInv;

	private int xMinimo;
	private int xMaximo;
	private int yMinimo;
	private int yMaximo;

	private static final int AUBENOR = 1;
	private static final int ARIS = 2;
	private static final int EODRIM = 3;

	private Grafo grafoDeTilesNoSolidos;
	private Grafo grafoDeTodosTiles;

	/**
	 * Devuelve el grafo con todos los tiles incluyendo los solidos.
	 *
	 * @return Grafo grafoDeTodosTiles
	 */
	public Grafo obtenerGrafoDeTodosTiles() {
		return grafoDeTodosTiles;
	}

	/**
	 * Construy el mundo
	 *
	 * @param juego
	 *            instancia de juego
	 * @param pathMap
	 *            mapa a cargar
	 * @param pathObstac
	 *            obstaculos del mapa
	 */
	public Mundo(final Juego juego, final String pathMap, final String pathObstac) {
		this.juego = juego;
		cargarMundo(pathMap, pathObstac);
		mundoAGrafo();
	}

	/**
	 * Actualizar
	 */
	public void actualizar() {

	}

	/**
	 * Graficador
	 *
	 * @param g
	 *            graphics
	 */
	public void graficar(final Graphics g) {
		xOffset = juego.getEstadoJuego().getPersonaje().getxOffset();
		yOffset = juego.getEstadoJuego().getPersonaje().getYOffset();

		final int offset = 30;
		xMinimo = (int) (juego.getCamara().getxOffset() - xOffset - offset);
		xMaximo = xMinimo + juego.getAncho() + xOffset + offset;
		final int offset1 = 60;
		yMinimo = (int) juego.getCamara().getyOffset() + yOffset - offset1;
		yMaximo = yMinimo + juego.getAlto() + yOffset + offset1;

		// Grafico el el tile base
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = dosDaIso(j, i);
				if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)) {
					final int map = juego.getPersonaje().getMapa();
					final int cameraOffset = 32;
					final int width = 64;
					if (map == AUBENOR) {
						Tile.getAubenor()[Tile.getAubenorBase()].graficar(g,
								(int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - cameraOffset), width, width);
					} else if (map == ARIS) {
						Tile.getAris()[Tile.getArisBase()].graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - cameraOffset), width, width);
					} else {

						if (map == EODRIM) {
							Tile.getAubenor()[Tile.getAubenorBase()].graficar(g,
									(int) (iso[0] - juego.getCamara().getxOffset()),
									(int) (iso[1] - juego.getCamara().getyOffset() - cameraOffset), width, width);
						}
					}
					if (!getTile(j, i).esSolido()) {
						getTile(j, i).graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
								(int) (iso[1] - juego.getCamara().getyOffset() - cameraOffset), width, width);
					}
				}
			}
		}
	}

	/**
	 * Grafica obstaculos
	 *
	 * @param g
	 *            graphics
	 */
	public void graficarObstaculos(final Graphics g) {
		Tile obst;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = dosDaIso(j, i);
				// Grafico al personaje
				if (Estado.getEstado() == juego.getEstadoJuego()) {
					if (Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
							juego.getUbicacionPersonaje().getPosY())[0] == j
							&& Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
									juego.getUbicacionPersonaje().getPosY())[1] == i) {
						juego.getEstadoJuego().getPersonaje().graficar(g);
					}
				}

				// Grafico los obstaculos
				if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)
						&& getTile(j, i).esSolido()) {
					obst = getTile(j, i);
					obst.graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
							(int) (iso[1] - juego.getCamara().getyOffset() - obst.getAlto() / 2), obst.getAncho(),
							obst.getAlto());
				}
			}
		}
	}

	/**
	 * Get tile
	 *
	 * @param x
	 *            pos X
	 * @param y
	 *            pos Y
	 * @return Tile
	 */
	public Tile getTile(final int x, final int y) {
		final Tile t = Tile.getTiles()[tiles[x][y]];
		if (t == null) {
			final int map = juego.getPersonaje().getMapa();
			if (map == AUBENOR) {
				return Tile.getAubenor()[Tile.getAubenorBase()];
			} else if (map == ARIS) {
				return Tile.getAris()[Tile.getArisBase()];
			} else if (map == EODRIM) {
				return Tile.getAubenor()[Tile.getAubenorBase()];
			}
		}
		return t;
	}

	/**
	 * Carga el mundo
	 *
	 * @param pathMapa
	 *            ruta al mapa
	 * @param pathObstaculos
	 *            ruta obstaculos
	 */
	private void cargarMundo(final String pathMapa, final String pathObstaculos) {
		final String archivo = Utilitarias.archivoAString(pathMapa);
		final String[] tokens = archivo.split("\\s+");
		ancho = Utilitarias.parseInt(tokens[0]);
		alto = Utilitarias.parseInt(tokens[1]);
		Utilitarias.parseInt(tokens[2]);
		final int utilitarias = 3;
		Utilitarias.parseInt(tokens[utilitarias]);

		tiles = new int[ancho][alto];
		tilesInv = new int[alto][ancho];

		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {

				final int offset = 4;
				tiles[x][y] = Utilitarias.parseInt(tokens[(x + y * ancho + offset)]);
				tilesInv[y][x] = tiles[x][y];
			}
		}

	}

	/**
	 * Convierte mundo a grafos
	 */
	private void mundoAGrafo() {
		// Creo una matriz de nodos
		final Nodo[][] nodos = new Nodo[ancho][alto];
		final Nodo[][] nodosNoClip = new Nodo[ancho][alto];
		grafoDeTodosTiles = new Grafo(ancho * alto);
		int indice = 0;
		// Lleno la matriz con los nodos
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				nodos[y][x] = new Nodo(indice, x, y);
				nodosNoClip[y][x] = new Nodo(indice, x, y);
				indice++;
			}
		}
		// Variables finales
		final int xFinal = ancho;
		final int yFinal = alto;

		// Uno cada nodo con sus adyacentes
		for (int x = 0; x < yFinal; x++) {
			for (int y = 0; y < xFinal; y++) {

				// if (!Tile.getTiles()[tilesInv[x][y]].esSolido()) {
				// Si no es la ultima fila y el tile de abajo es no solido,
				// lo uno
				if (y < yFinal - 1) {

					if (!Tile.getTiles()[tilesInv[x][y]].esSolido()
							&& !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
						nodos[x][y].agregarAdyacente(nodos[x][y + 1]);
						nodos[x][y + 1].agregarAdyacente(nodos[x][y]);
					}

					nodosNoClip[x][y].agregarAdyacente(nodosNoClip[x][y + 1]);
					nodosNoClip[x][y + 1].agregarAdyacente(nodosNoClip[x][y]);
				}
				// Si no es la ultima columna
				if (x < xFinal - 1) {
					// Si el de arriba a la derecha no es un tile solido
					// Y ademas el de arriba ni el de la derecha lo son, lo
					// uno
					// Tiene que ser a partir de la segunda fila
					if (y > 0) {
						if (!Tile.getTiles()[tilesInv[x][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y - 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y - 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y - 1]);
							nodos[x + 1][y - 1].agregarAdyacente(nodos[x][y]);
						}

						nodosNoClip[x][y].agregarAdyacente(nodosNoClip[x + 1][y - 1]);
						nodosNoClip[x + 1][y - 1].agregarAdyacente(nodosNoClip[x][y]);
					}
					// Si el de la derecha no es un tile solido lo uno
					if (!Tile.getTiles()[tilesInv[x][y]].esSolido()
							&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()) {
						nodos[x][y].agregarAdyacente(nodos[x + 1][y]);
						nodos[x + 1][y].agregarAdyacente(nodos[x][y]);
					}

					nodosNoClip[x][y].agregarAdyacente(nodosNoClip[x + 1][y]);
					nodosNoClip[x + 1][y].agregarAdyacente(nodosNoClip[x][y]);

					// Si el de abajo a la derecha no es un tile solido
					// Y ademas el de abajo ni el de la derecha lo son, lo
					// uno
					// Debe ser antes de la ultima fila
					if (y < yFinal - 1) {
						if (!Tile.getTiles()[tilesInv[x][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y + 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y + 1]);
							nodos[x + 1][y + 1].agregarAdyacente(nodos[x][y]);
						}

						nodosNoClip[x][y].agregarAdyacente(nodosNoClip[x + 1][y + 1]);
						nodosNoClip[x + 1][y + 1].agregarAdyacente(nodosNoClip[x][y]);

					}
				}
				// }

			}
		}
		// Creo un grafo para almacenar solo los tiles no solidos
		grafoDeTilesNoSolidos = new Grafo(ancho * alto);
		indice = 0;
		// Paso la matriz a un array
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				grafoDeTilesNoSolidos.agregarNodo(nodos[i][j]);
				grafoDeTodosTiles.agregarNodo(nodosNoClip[i][j]);
			}
		}
	}

	/**
	 * Grafo de tiles no solidos
	 *
	 * @return Grafo grafo
	 */
	public Grafo obtenerGrafoDeTilesNoSolidos() {
		return grafoDeTilesNoSolidos;
	}

	/**
	 * Ancho
	 *
	 * @return int ancho del mundo
	 */
	public int obtenerAncho() {
		return ancho;
	}

	/**
	 * Alto
	 *
	 * @return int alto del mundo
	 */
	public int obtenerAlto() {
		return alto;
	}

	/**
	 * ISO a 2D
	 *
	 * @param x
	 *            pos X
	 * @param y
	 *            pos Y
	 * @return float[] 2D
	 */
	public static float[] isoA2D(final float x, final float y) {
		final float[] dosD = new float[2];

		dosD[0] = (x / (Tile.ANCHO / 2) + y / (Tile.ALTO / 2)) / 2;
		dosD[1] = (y / (Tile.ALTO / 2) - (x / (Tile.ANCHO / 2))) / 2;

		return dosD;
	}

	/**
	 * 2D a ISO
	 *
	 * @param x
	 *            pos X
	 * @param y
	 *            pos Y
	 * @return float[] ISO
	 */
	public static float[] dosDaIso(final float x, final float y) {
		final float[] iso = new float[2];

		iso[0] = (x - y) * (Tile.ANCHO / 2);
		iso[1] = (x + y) * (Tile.ALTO / 2);

		return iso;
	}

	/**
	 * Mouse a tile
	 *
	 * @param x
	 *            pos X
	 * @param y
	 *            pos Y
	 * @return int[]
	 */
	public static int[] mouseATile(final float x, final float y) {
		final int[] tile = new int[2];

		tile[0] = (int) Math.floor((y / Tile.ALTO) + (x / Tile.ANCHO)) + 1;
		tile[1] = (int) Math.floor((-x / Tile.ANCHO) + (y / Tile.ALTO)) + 1;

		return tile;
	}
}
