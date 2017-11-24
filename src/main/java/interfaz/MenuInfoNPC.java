package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import juego.Pantalla;
import mensajeria.PaqueteNPC;
import recursos.Recursos;

/**
 * Muestra el menu de info de un NPC
 *
 */
public class MenuInfoNPC {

	private static final int ANCHO_PERSONAJE = 128;
	private static final BufferedImage MENU = Recursos.menuEnemigo;
	public static final int MENU_BATALLARNPC = 7;
	public static final int MENU_PERDERBATALLA = 8;
	private static final String[] LEYENDABOTON = { "Batallar" };

	private final int x;
	private final int y;
	private final PaqueteNPC npc;

	/**
	 * Construye el menu de info de un NPC
	 *
	 * @param x
	 *            pos X
	 * @param y
	 *            pos Y
	 * @param npc
	 *            NPC a mostrar
	 */
	public MenuInfoNPC(final int x, final int y, final PaqueteNPC npc) {
		this.x = x;
		this.y = y;
		this.npc = npc;
	}

	/**
	 * Grafica el menu
	 *
	 * @param g
	 *            graphics
	 * @param tipoMenu
	 *            tipo
	 */
	public void graficar(final Graphics g, final int tipoMenu) {
		// dibujo el menu
		g.drawImage(MENU, x, y, null);

		// dibujo el personaje
		final int recurso = 6;
		final java.awt.Image img = Recursos.personaje.get(npc.getRaza()).get(recurso)[0];
		final int offsetY = 70;
		final int widthHeight = 128;
		final int xCalc = x + MENU.getWidth() / 2 - ANCHO_PERSONAJE / 2;
		g.drawImage(img, xCalc, y + offsetY, widthHeight, widthHeight, null);

		g.setColor(Color.WHITE);
		final int fontStyle = 1;
		final int fontSize = 20;
		g.setFont(new Font("Book Antiqua", fontStyle, fontSize));
		final int offsetY2 = 15;
		Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), npc.getNombre());

		// Grafico la leyenda
		graficarMenuInformacion(g);

		// muestro los botones
		g.setFont(new Font("Book Antiqua", fontStyle, fontSize));
		final int offsetX3 = 50;
		final int offsetY3 = 380;
		final int width = 200;
		final int height = 25;
		g.drawImage(Recursos.botonMenu, x + offsetX3, y + offsetY3, width, height, null);
		g.setColor(Color.WHITE);
		// Pantalla.centerString(g, new Rectangle(x + offsetX3, y + offsetY3, width,
		// height), LEYENDABOTON[tipoMenu]);

		Pantalla.centerString(g, new Rectangle(x + offsetX3, y + offsetY3, width, height), LEYENDABOTON[0]);
	}

	/**
	 * Grafica la info del NPC
	 *
	 * @param g
	 *            graphics
	 */
	public void graficarMenuInformacion(final Graphics g) {

		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		final int offsetY = 200;
		Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), npc.getNombre());
		g.setColor(Color.RED);
		final int offsetY2 = 230;
		Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), "< NPC >");
		g.setColor(Color.BLACK);
		final int offsetX = 30;
		final int offsetY3 = 260;
		g.drawString("Nivel: ", x + offsetX, y + offsetY3);
		final int offsetY4 = 290;
		g.drawString("Fuerza: ", x + offsetX, y + offsetY4);
		final int offsetY5 = 320;
		g.drawString("Salud: ", x + offsetX, y + offsetY5);
		final int offsetY6 = 350;
		g.drawString("Dificultad: ", x + offsetX, y + offsetY6);

		// muestro los atributos
		final int fontSize = 20;
		g.setFont(new Font("Book Antiqua", 0, fontSize));
		final int offsetX1 = 100;
		g.drawString(npc.getNivel() + " ", x + offsetX1, y + offsetY3);
		g.drawString(npc.getFuerza() + " ", x + offsetX1, y + offsetY4);
		g.drawString(npc.getSaludTope() + " ", x + offsetX1, y + offsetY5);
		final int offsetX2 = 140;
		g.drawString(npc.getDificultad() + " ", x + offsetX2, y + offsetY6);

	}

	/**
	 * Click en algun boton del menu
	 *
	 * @param mouseX
	 *            pos X
	 * @param mouseY
	 *            pos Y
	 * @return boolean click
	 */
	public boolean clickEnBoton(final int mouseX, final int mouseY) {
		final int offsetX = 50;
		final int offsetX1 = 250;
		final int offsetY = 380;
		final int offsetY1 = 405;
		if (mouseX >= x + offsetX && mouseX <= x + offsetX1) {
			if (mouseY >= y + offsetY && mouseY <= y + offsetY1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Click en cerrar en el menu
	 *
	 * @param mouseX
	 *            pos X
	 * @param mouseY
	 *            pos Y
	 * @return boolean click
	 */
	public boolean clickEnCerrar(final int mouseX, final int mouseY) {
		final int offsetX = 24;
		final int offsetX1 = 4;
		final int offsetY = 12;
		final int offsetY1 = 36;
		if (mouseX >= x + MENU.getWidth() - offsetX && mouseX <= x + MENU.getWidth() + offsetX1) {
			if (mouseY >= y + offsetY && mouseY <= y + offsetY1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Click en menu
	 *
	 * @param mouseX
	 *            pos X
	 * @param mouseY
	 *            pos Y
	 * @return boolean click
	 */
	public boolean clickEnMenu(final int mouseX, final int mouseY) {
		if (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight()) {
			return true;
		}
		return false;
	}
}
