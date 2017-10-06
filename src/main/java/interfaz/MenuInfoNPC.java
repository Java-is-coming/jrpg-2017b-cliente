package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import juego.Pantalla;
import mensajeria.PaqueteNPC;
import recursos.Recursos;

public class MenuInfoNPC {

	private static final int anchoPersonaje = 128;
	private static final BufferedImage menu = Recursos.menuEnemigo;
	public static final int menuBatallarNPC = 7;
	public static final int menuPerderBatalla = 8;
	private static final String [] leyendaBoton = {"Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar", "Comerciar"};

	private int x;
	private int y;
	private PaqueteNPC npc;

	public MenuInfoNPC(int x, int y, PaqueteNPC npc){
		this.x = x;
		this.y = y;
		this.npc = npc;
	}

	public void graficar(Graphics g, int tipoMenu){

		// dibujo el menu
		g.drawImage(menu, x, y, null);

		// dibujo el personaje
		g.drawImage(Recursos.personaje.get(npc.getRaza()).get(6)[0], x + menu.getWidth() / 2  - anchoPersonaje / 2, y + 70, 128, 128, null);

		// muestro el nombre
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", 1, 20));
		Pantalla.centerString(g, new Rectangle(x, y + 15, menu.getWidth(), 0), npc.getNombre());

		// Grafico la leyenda segun el tipo de menu
		switch(tipoMenu){
			case menuBatallarNPC:
				graficarMenuInformacion(g);
				break;
		}


		// muestro los botones
		g.setFont(new Font("Book Antiqua", 1, 20));
		g.drawImage(Recursos.botonMenu, x + 50, y + 380, 200, 25, null);
		g.setColor(Color.WHITE);
		Pantalla.centerString(g, new Rectangle(x + 50, y + 380, 200, 25), leyendaBoton[0]);
	}


	public void graficarMenuInformacion(Graphics g){

		// muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), npc.getRaza());
		g.setColor(Color.RED);
		Pantalla.centerString(g, new Rectangle(x, y + 230, menu.getWidth(), 0), "< NPC >");
		g.setColor(Color.BLACK);
		g.drawString("Nivel: ", x + 30, y + 260);
		g.drawString("Fuerza: ", x + 30, y + 290);
		g.drawString("Salud: ", x + 30, y + 320);
		g.drawString("Dificultad: ", x + 30, y + 350);

		// muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, 20));
		g.drawString(npc.getNivel() + " ", x + 100, y + 260);
		g.drawString(npc.getFuerza() + " ", x + 100, y + 290);
		g.drawString(npc.getSaludTope() + " ", x + 100, y + 320);
		g.drawString(npc.getDificultad() + " ", x + 140, y + 350);

	}

	public boolean clickEnBoton(int mouseX, int mouseY){
		if(mouseX >= x + 50 && mouseX <= x + 250 && mouseY >= y + 380 && mouseY <= y + 405)
			return true;
		return false;
	}

	public boolean clickEnCerrar(int mouseX, int mouseY){
		if(mouseX >= x + menu.getWidth() - 24 && mouseX <= x + menu.getWidth() + 4 && mouseY >= y + 12 && mouseY <= y + 36)
			return true;
		return false;
	}

	public boolean clickEnMenu(int mouseX, int mouseY){
		if(mouseX >= x && mouseX <= x + menu.getWidth() && mouseY >= y  && mouseY <= y + menu.getHeight())
			return true;
		return false;
	}
}
