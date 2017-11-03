package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Menu con la información del personaje
 *
 */
public class MenuInfoPersonaje {

    private static final int ANCHO_PERSONAJE = 128;
    private static final BufferedImage MENU = Recursos.menuEnemigo;
    public static final int MENU_BATALLAR = 0;
    public static final int MENU_INFORMACION = 1;
    public static final int MENU_SUBIRNIVEL = 2;
    public static final int MENU_GANARBATALLA = 3;
    public static final int MENU_PERDERBATALLA = 4;
    public static final int MENU_GANARITEM = 5;
    public static final int MENU_COMERCIAR = 6;
    private static final String[] LEYENDABOTON = {"Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar",
            "Comerciar"};

    private final int x;
    private final int y;
    private final PaquetePersonaje personaje;

    /**
     * Constructor
     *
     * @param x
     *            posicion x
     * @param y
     *            posicion y
     * @param personaje
     *            paquete personaje
     */
    public MenuInfoPersonaje(final int x, final int y, final PaquetePersonaje personaje) {
        this.x = x;
        this.y = y;
        this.personaje = personaje;
    }

    /**
     * Graficar menu
     *
     * @param g
     *            graphics
     * @param tipoMenu
     *            int
     */
    public void graficar(final Graphics g, final int tipoMenu) {
        if (tipoMenu >= LEYENDABOTON.length) {
            return;
        }

        // dibujo el menu
        g.drawImage(MENU, x, y, null);

        // dibujo el personaje
        final int recurso = 6;
        final java.awt.Image img = Recursos.personaje.get(personaje.getRaza()).get(recurso)[0];
        final int offsetY = 70;
        final int widthHeight = 128;
        g.drawImage(img, x + MENU.getWidth() / 2 - ANCHO_PERSONAJE / 2, y + offsetY, widthHeight, widthHeight, null);

        // muestro el nombre
        g.setColor(Color.WHITE);
        final int fontStyle = 1;
        final int fontSize = 20;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));
        final int offsetY2 = 15;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), personaje.getNombre());

        // Grafico la leyenda segun el tipo de menu
        switch (tipoMenu) {
        case MENU_BATALLAR:
            graficarMenuInformacion(g);
            break;
        case MENU_INFORMACION:
            graficarMenuInformacion(g);
            break;
        case MENU_SUBIRNIVEL:
            graficarMenuSubirNivel(g);
            break;
        case MENU_GANARBATALLA:
            graficarMenuGanarBatalla(g);
            break;
        case MENU_PERDERBATALLA:
            graficarMenuPerderBatalla(g);
            break;
        case MENU_GANARITEM:
            graficarMenuItem(g);
            break;
        case MENU_COMERCIAR:
            graficarMenuComerciar(g);
            break;
        default:
            break;
        }

        // muestro los botones
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));
        final int offsetX3 = 50;
        final int offsetY3 = 380;
        final int width = 200;
        final int height = 25;
        g.drawImage(Recursos.botonMenu, x + offsetX3, y + offsetY3, width, height, null);
        g.setColor(Color.WHITE);
        Pantalla.centerString(g, new Rectangle(x + offsetX3, y + offsetY3, width, height), LEYENDABOTON[tipoMenu]);
    }

    /**
     * Grafica el menu de batalla perdida
     *
     * @param g
     *            graphics
     */
    private void graficarMenuPerderBatalla(final Graphics g) {

        // Informo que perdio la batalla
        g.setColor(Color.BLACK);
        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), "¡Has sido derrotado!");

        final int fontStyle = 1;
        final int fontSize = 20;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));

        final int offsetY2 = 250;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), "¡No te rindas! Sigue luchando");
        final int offsetY3 = 270;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY3, MENU.getWidth(), 0), "contra los demás personajes");
        final int offsetY4 = 290;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY4, MENU.getWidth(), 0), "para aumentar tu nivel y");
        final int offsetY5 = 310;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY5, MENU.getWidth(), 0), "mejorar tus atributos.");
    }

    /**
     * Grafica el menu de batalla ganada
     *
     * @param g
     *            graphics
     */
    private void graficarMenuGanarBatalla(final Graphics g) {

        // Informo que gano la batalla
        g.setColor(Color.BLACK);
        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), "¡Has derrotado");
        final int offsetY2 = 230;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), "a tu enemigo!");

        final int fontStyle = 0;
        final int fontSize = 14;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));

        final int offsetY3 = 270;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY3, MENU.getWidth(), 0), "¡Felicitaciones! Has derrotado");
        final int offsetY4 = 290;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY4, MENU.getWidth(), 0), "a tu oponente, sigue así");
        final int offsetY5 = 310;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY5, MENU.getWidth(), 0), "para lograr subir de nivel");
        final int offsetY6 = 330;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY6, MENU.getWidth(), 0), "y mejorar tus atributos.");

    }

    /**
     * Grafica el menu de nuevo nivel
     *
     * @param g
     *            graphics
     */
    private void graficarMenuSubirNivel(final Graphics g) {

        // Informo que subio de nivel
        g.setColor(Color.BLACK);
        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), "¡Has subido de nivel!");

        final int fontStyle = 0;
        final int fontSize = 18;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));

        final int offsetY2 = 240;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), "¡Felicitaciones!");

        final int offsetY3 = 270;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY3, MENU.getWidth(), 0), "Nuevo Nivel");

        final int fontStyle2 = 1;
        final int fontSize2 = 62;
        g.setFont(new Font("Book Antiqua", fontStyle2, fontSize2));

        final int offsetY4 = 325;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY4, MENU.getWidth(), 0),
                String.valueOf(personaje.getNivel()));

    }

    /**
     * Grafica el menu informacion
     *
     * @param g
     *            graphics
     */
    public void graficarMenuInformacion(final Graphics g) {

        // muestro los nombres de los atributos
        g.setColor(Color.BLACK);
        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), personaje.getRaza());

        final int offsetX1 = 30;
        final int offsetY1 = 260;
        g.drawString("Casta: ", x + offsetX1, y + offsetY1);
        g.drawString("Nivel: ", x + offsetX1, y + offsetY1 + offsetX1);
        g.drawString("Experiencia: ", x + offsetX1, y + offsetY1 + offsetX1 + offsetX1);

        // muestro los atributos
        final int fontStyle = 0;
        final int fontSize = 20;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));

        final int offsetX2 = 100;
        final int offsetY2 = 260;
        g.drawString(personaje.getCasta(), x + offsetX2, y + offsetY2);
        final int offsetY3 = 290;
        g.drawString(personaje.getNivel() + " ", x + offsetX2, y + offsetY3);
        final int offsetX3 = 150;
        final int offsetY4 = 320;
        g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
                x + offsetX3, y + offsetY4);

    }

    /**
     * Grafica el menu de un item
     *
     * @param g
     *            graphics
     */
    private void graficarMenuItem(final Graphics g) {

        // Informo que subio de nivel
        g.setColor(Color.BLACK);
        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), "¡Aca iria algo!");

        final int fontStyle = 0;
        final int fontSize = 18;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));
        final int offsetY2 = 240;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY2, MENU.getWidth(), 0), "¡Aca otra cosa!");
        final int offsetY3 = 270;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY3, MENU.getWidth(), 0), "Nuevo Nivel");
        final int fontStyle1 = 1;
        final int fontSize1 = 62;
        g.setFont(new Font("Book Antiqua", fontStyle1, fontSize1));
        final int offsetY4 = 325;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY4, MENU.getWidth(), 0),
                String.valueOf(personaje.getNivel()));

    }

    /**
     * Grafica el menu comerciar
     *
     * @param g
     *            graphics
     */
    private void graficarMenuComerciar(final Graphics g) {

        // muestro los nombres de los atributos
        g.setColor(Color.BLACK);

        final int offsetY = 200;
        Pantalla.centerString(g, new Rectangle(x, y + offsetY, MENU.getWidth(), 0), personaje.getRaza());
        final int offsetX = 30;
        final int offsetY2 = 260;
        g.drawString("Casta: ", x + offsetX, y + offsetY2);
        final int offsetY3 = 290;
        g.drawString("Nivel: ", x + offsetX, y + offsetY3);
        final int offsetY4 = 320;
        g.drawString("Experiencia: ", x + offsetX, y + offsetY4);

        // muestro los atributos
        final int fontSize = 20;
        g.setFont(new Font("Book Antiqua", 0, fontSize));
        final int offsetX1 = 100;
        g.drawString(personaje.getCasta(), x + offsetX1, y + offsetY);
        g.drawString(personaje.getNivel() + " ", x + offsetX1, y + offsetY3);
        final int offsetX2 = 150;
        g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
                x + offsetX2, y + offsetY4);

    }

    /**
     * Click en boton en pantalla
     *
     * @param mouseX
     *            pos X del mouse
     * @param mouseY
     *            pos Y del mouse
     * @return boolean click
     */
    public boolean clickEnBoton(final int mouseX, final int mouseY) {
        final int offsetX = 50;
        final int offsetX1 = 250;
        final int offsetY = 380;
        final int offsetY1 = 405;
        if (mouseX >= x + offsetX && mouseX <= x + offsetX1 && mouseY >= y + offsetY && mouseY <= y + offsetY1) {
            return true;
        }
        return false;
    }

    /**
     * Click en cerrar
     *
     * @param mouseX
     *            pos X del mouse
     * @param mouseY
     *            pos Y del mouse
     * @return boolean click
     */
    public boolean clickEnCerrar(final int mouseX, final int mouseY) {
        final int offsetX = 24;
        final int offsetX1 = 4;
        final int offsetY = 12;
        final int offsetY1 = 36;
        if (mouseX >= x + MENU.getWidth() - offsetX && mouseX <= x + MENU.getWidth() + offsetX1 && mouseY >= y + offsetY
                && mouseY <= y + offsetY1) {
            return true;
        }
        return false;
    }

    /**
     * Click en menu
     *
     * @param mouseX
     *            pos X del mouse
     * @param mouseY
     *            pos Y del mouse
     * @return boolean click
     */
    public boolean clickEnMenu(final int mouseX, final int mouseY) {
        if (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight()) {
            return true;
        }
        return false;
    }
}
