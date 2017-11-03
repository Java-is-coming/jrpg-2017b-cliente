package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

/**
 * Menu de batalla PvP o PvE
 */
public class MenuBatalla {

    private static final int OFFSETX3 = 394;
    private static final int OFFSETX2 = 221;
    private static final int OFFSETY2 = 146;
    private static final int OFFSETY = 72;
    private static final int OFFSETX = 48;
    private static final int X = 100;
    private static final int Y = 380;
    private static final int ANCHO_BOTON = 40;
    private static final int[][] BOTONES = {{X + OFFSETX, Y + OFFSETY}, {X + OFFSETX, Y + OFFSETY2},
            {X + OFFSETX2, Y + OFFSETY}, {X + OFFSETX2, Y + OFFSETY2}, {X + OFFSETX3, Y + OFFSETY},
            {X + OFFSETX3, Y + OFFSETY2}};
    private boolean habilitado;
    private final Personaje personaje;

    /**
     * Constructor del menu
     *
     * @param habilitado
     *            boolean
     * @param personaje
     *            personaje a mostrar
     */
    public MenuBatalla(final boolean habilitado, final Personaje personaje) {
        this.habilitado = habilitado;
        this.personaje = personaje;
    }

    /**
     * Grafica el menu
     *
     * @param g
     *            graphics
     */
    public void graficar(final Graphics g) {

        if (habilitado) {
            g.drawImage(Recursos.menuBatalla, X, Y, null);
        } else {
            g.drawImage(Recursos.menuBatallaDeshabilitado, X, Y, null);
        }

        // Dibujo los boones
        g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[0]), BOTONES[0][0], BOTONES[0][1],
                ANCHO_BOTON, ANCHO_BOTON, null);
        g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[1]), BOTONES[1][0], BOTONES[1][1],
                ANCHO_BOTON, ANCHO_BOTON, null);
        g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[0]), BOTONES[2][0], BOTONES[2][1],
                ANCHO_BOTON, ANCHO_BOTON, null);
        final int btnHab3 = 3;
        g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[1]), BOTONES[btnHab3][0],
                BOTONES[btnHab3][1], ANCHO_BOTON, ANCHO_BOTON, null);
        final int btnHab4 = 4;
        g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[2]), BOTONES[btnHab4][0],
                BOTONES[btnHab4][1], ANCHO_BOTON, ANCHO_BOTON, null);
        final int btnHab5 = 5;
        g.drawImage(Recursos.habilidades.get("Ser Energizado"), BOTONES[btnHab5][0], BOTONES[btnHab5][1], ANCHO_BOTON,
                ANCHO_BOTON, null);

        // Dibujo las leyendas
        final int fontStyle = 1;
        final int fontSize = 14;
        final int offsetX = 95;
        final int offsetY = 94;
        final int offsetY2 = 168;
        final int offsetX2 = 268;
        final int offsetX3 = 442;
        g.setFont(new Font("Book Antiqua", fontStyle, fontSize));

        g.drawString(personaje.getHabilidadesRaza()[0], X + offsetX, Y + offsetY);

        g.drawString(personaje.getHabilidadesRaza()[1], X + offsetX, Y + offsetY2);

        g.drawString(personaje.getHabilidadesCasta()[0], X + offsetX2, Y + offsetY);
        g.drawString(personaje.getHabilidadesCasta()[1], X + offsetX2, Y + offsetY2);

        g.drawString(personaje.getHabilidadesCasta()[2], X + offsetX3, Y + offsetY);
        g.drawString("Ser energizado", X + offsetX3, Y + offsetY2);

        // Dibujo el turno de quien es
        g.setColor(Color.WHITE);
        final int height = 20;
        if (habilitado) {
            Pantalla.centerString(g, new Rectangle(X, Y + btnHab5, Recursos.menuBatalla.getWidth(), height),
                    "Mi Turno");
        } else {
            Pantalla.centerString(g, new Rectangle(X, Y + btnHab5, Recursos.menuBatalla.getWidth(), height),
                    "Turno Rival");
        }

    }

    /**
     * Getter del boton clickeado
     *
     * @param mouseX
     *            pos en X
     * @param mouseY
     *            pos en Y
     * @return int boton
     */
    public int getBotonClickeado(final int mouseX, final int mouseY) {
        if (!habilitado) {
            return 0;
        }
        for (int i = 0; i < BOTONES.length; i++) {
            if (mouseX >= BOTONES[i][0] && mouseX <= BOTONES[i][0] + ANCHO_BOTON && mouseY >= BOTONES[i][1]
                    && mouseY <= BOTONES[i][1] + ANCHO_BOTON) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Click en menu
     *
     * @param mouseX
     *            pos en X
     * @param mouseY
     *            pos en Y
     * @return boolean click
     */
    public boolean clickEnMenu(final int mouseX, final int mouseY) {
        if (mouseX >= X && mouseX <= X + Recursos.menuBatalla.getWidth() && mouseY >= Y
                && mouseY <= Y + Recursos.menuBatalla.getHeight()) {
            return habilitado;
        }
        return false;
    }

    /**
     * Setter de habilitado
     *
     * @param b
     *            boolean
     */
    public void setHabilitado(final boolean b) {
        habilitado = b;
    }
}
