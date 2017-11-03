package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dominio.NonPlayableCharacter;
import dominio.Personaje;
import mensajeria.PaqueteNPC;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Grafica el estado del personaje
 */
public final class EstadoDePersonaje {

    private static final int ANCHOBARRA = 122;
    private static final int ALTOSALUD = 14;
    private static final int ALTOENERGIA = 14;
    private static final int ALTOEXPERIENCIA = 6;
    private static final int ALTOMINIATURA = 64;
    private static final int ANCHOMINIATURA = 64;

    /**
     * Constructor privado
     */
    private EstadoDePersonaje() {

    }

    /**
     * Dibujar el estado
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param personaje
     *            Personaje
     * @param miniaturaPersonaje
     *            imagen miniatura
     */
    public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y, final Personaje personaje,
            final BufferedImage miniaturaPersonaje) {

        int drawBarra = 0;

        g.drawImage(Recursos.estadoPersonaje, x, y, null);

        final int offsetX = 10;
        final int offsetY = 9;
        g.drawImage(miniaturaPersonaje, x + offsetX, y + offsetY, ANCHOMINIATURA, ALTOMINIATURA, null);

        if (personaje.getSalud() == personaje.getSaludTope()) {
            drawBarra = ANCHOBARRA;
        } else {
            drawBarra = (personaje.getSalud() * ANCHOBARRA) / personaje.getSaludTope();
        }

        g.setColor(Color.WHITE);
        final int fontSize = 10;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        final int offsetX1 = 80;
        final int offsetY1 = 26;
        g.drawImage(Recursos.barraSalud, x + offsetX1, y + offsetY1, drawBarra, ALTOSALUD, null);
        final int offsetX2 = 132;
        final int offsetY2 = 37;
        g.drawString(String.valueOf(personaje.getSalud()) + " / " + String.valueOf(personaje.getSaludTope()),
                x + offsetX2, y + offsetY2);

        if (personaje.getEnergia() == personaje.getEnergiaTope()) {
            drawBarra = ANCHOBARRA;
        } else {
            drawBarra = (personaje.getEnergia() * ANCHOBARRA) / personaje.getEnergiaTope();
        }

        final int offsetX3 = 80;
        final int offsetY3 = 42;
        g.drawImage(Recursos.barraEnergia, x + offsetX3, y + offsetY3, drawBarra, ALTOENERGIA, null);
        final int offsetX4 = 132;
        final int offsetY4 = 52;
        g.drawString(String.valueOf(personaje.getEnergia()) + " / " + String.valueOf(personaje.getEnergiaTope()),
                x + offsetX4, y + offsetY4);

        if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
            drawBarra = ANCHOBARRA;
        } else {
            drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
                    / Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
        }

        final int fontSize1 = 8;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize1));
        final int offsetX5 = 77;
        final int offsetY5 = 65;
        g.drawImage(Recursos.barraExperiencia, x + offsetX5, y + offsetY5, drawBarra, ALTOEXPERIENCIA, null);
        final int offsetX6 = 132;
        final int offsetY6 = 70;
        g.drawString(
                String.valueOf(personaje.getExperiencia()) + " / "
                        + String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
                x + offsetX6, y + offsetY6);
        final int fontSize2 = 10;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize2));
        g.setColor(Color.GREEN);
        final int offsetX7 = 59;
        final int offsetY7 = 70;
        g.drawString(String.valueOf(personaje.getNivel()), x + offsetX7, y + offsetY7);
    }

    /**
     * Dibujar el estado
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param personaje
     *            a dibujar
     * @param miniaturaPersonaje
     *            miniatura del personaje
     */
    public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y,
            final PaquetePersonaje personaje, final BufferedImage miniaturaPersonaje) {

        int drawBarra = 0;

        g.drawImage(Recursos.estadoPersonaje, x, y, null);

        final int offsetX = 10;
        final int offsetY = 9;
        g.drawImage(miniaturaPersonaje, x + offsetX, y + offsetY, ANCHOMINIATURA, ALTOMINIATURA, null);

        g.setColor(Color.WHITE);
        final int fontSize = 10;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        final int offsetX1 = 80;
        final int offsetY1 = 26;
        g.drawImage(Recursos.barraSalud, x + offsetX1, y + offsetY1, ANCHOBARRA, ALTOSALUD, null);
        final int offsetX3 = 132;
        final int offsetY3 = 37;
        g.drawString(String.valueOf(personaje.getSaludTope()) + " / " + String.valueOf(personaje.getSaludTope()),
                x + offsetX3, y + offsetY3);

        final int offsetX2 = 80;
        final int offsetY2 = 42;
        g.drawImage(Recursos.barraEnergia, x + offsetX2, y + offsetY2, ANCHOBARRA, ALTOENERGIA, null);
        final int offsetY4 = 52;
        g.drawString(String.valueOf(personaje.getEnergiaTope()) + " / " + String.valueOf(personaje.getEnergiaTope()),
                x + offsetX3, y + offsetY4);

        if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
            drawBarra = ANCHOBARRA;
        } else {
            drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
                    / Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
        }

        final int fontSize1 = 8;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize1));
        final int offsetX5 = 77;
        final int offsetY5 = 65;
        g.drawImage(Recursos.barraExperiencia, x + offsetX5, y + offsetY5, drawBarra, ALTOEXPERIENCIA, null);
        final int offsetY6 = 70;
        g.drawString(
                String.valueOf(personaje.getExperiencia()) + " / "
                        + String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
                x + offsetX3, y + offsetY6);
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        g.setColor(Color.GREEN);
        final int offsetX6 = 59;
        g.drawString(String.valueOf(personaje.getNivel()), x + offsetX6, y + offsetY6);
    }

    /**
     * Dibuja un NPC
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param npc
     *            npc a dibujar
     * @param miniaturaPersonaje
     *            miniatura personaje
     */
    public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y,
            final NonPlayableCharacter npc, final BufferedImage miniaturaPersonaje) {

        int drawBarra = 0;

        g.drawImage(Recursos.estadoPersonaje, x, y, null);

        final int offsetX = 10;
        final int offsetY = 9;
        g.drawImage(miniaturaPersonaje, x + offsetX, y + offsetY, ANCHOMINIATURA, ALTOMINIATURA, null);

        if (npc.getSalud() == npc.getSaludTope()) {
            drawBarra = ANCHOBARRA;
        } else {
            drawBarra = (npc.getSalud() * ANCHOBARRA) / npc.getSaludTope();
        }

        g.setColor(Color.WHITE);
        final int fontSize = 10;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        final int offsetX1 = 80;
        final int offsetY1 = 26;
        g.drawImage(Recursos.barraSalud, x + offsetX1, y + offsetY1, drawBarra, ALTOSALUD, null);
        final int offsetX2 = 132;
        final int offsetY2 = 37;
        g.drawString(String.valueOf(npc.getSalud()) + " / " + String.valueOf(npc.getSaludTope()), x + offsetX2,
                y + offsetY2);
        /*
         * if(personaje.getEnergia() == personaje.getEnergiaTope()) { drawBarra =
         * ANCHOBARRA; } else { drawBarra = (personaje.getEnergia() * ANCHOBARRA) /
         * personaje.getEnergiaTope(); }
         *
         * g.drawImage(Recursos.barraEnergia, x + 80, y + 42, drawBarra, ALTOENERGIA,
         * null); g.drawString(String.valueOf(personaje.getEnergia()) + " / " +
         * String.valueOf(personaje.getEnergiaTope()), x + 132, y + 52);
         *
         * if(personaje.getExperiencia() ==
         * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) { drawBarra =
         * ANCHOBARRA; } else { drawBarra = (personaje.getExperiencia() * ANCHOBARRA) /
         * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]; } g.setFont(new
         * Font("Tahoma", Font.PLAIN, 8)); g.drawImage(Recursos.barraExperiencia, x +
         * 77, y + 65, drawBarra, ALTOEXPERIENCIA, null);
         * g.drawString(String.valueOf(personaje.getExperiencia()) + " / " +
         * String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]), x +
         * 132, y + 70); g.setFont(new Font("Tahoma", Font.PLAIN, 10));
         * g.setColor(Color.GREEN); g.drawString(String.valueOf(personaje.getNivel()), x
         * + 59, y + 70);
         */
    }

    /**
     * Dibujar estado
     *
     * @param g
     *            graphics
     * @param x
     *            pos en X
     * @param y
     *            pos en Y
     * @param npc
     *            paquete NPC
     * @param miniaturaPersonaje
     *            miniatura
     */
    public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y, final PaqueteNPC npc,
            final BufferedImage miniaturaPersonaje) {

        g.drawImage(Recursos.estadoPersonaje, x, y, null);

        final int offsetX = 10;
        final int offsetY = 9;
        g.drawImage(miniaturaPersonaje, x + offsetX, y + offsetY, ANCHOMINIATURA, ALTOMINIATURA, null);

        g.setColor(Color.WHITE);
        final int fontSize = 10;
        g.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
        final int offsetX1 = 80;
        final int offsetY1 = 26;
        g.drawImage(Recursos.barraSalud, x + offsetX1, y + offsetY1, ANCHOBARRA, ALTOSALUD, null);
        final int offsetX2 = 132;
        final int offsetY2 = 37;
        g.drawString(String.valueOf(npc.getSaludTope()) + " / " + String.valueOf(npc.getSaludTope()), x + offsetX2,
                y + offsetY2);

        /*
         * g.drawImage(Recursos.barraEnergia, x + 80, y + 42, ANCHOBARRA, ALTOENERGIA,
         * null); g.drawString(String.valueOf(npc.getEnergiaTope()) + " / " +
         * String.valueOf(npc.getEnergiaTope()), x + 132, y + 52);
         *
         * if(personaje.getExperiencia() ==
         * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) { drawBarra =
         * ANCHOBARRA; } else { drawBarra = (personaje.getExperiencia() * ANCHOBARRA) /
         * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]; }
         *
         * g.setFont(new Font("Tahoma", Font.PLAIN, 8));
         * g.drawImage(Recursos.barraExperiencia, x + 77, y + 65, drawBarra,
         * ALTOEXPERIENCIA, null);
         * g.drawString(String.valueOf(personaje.getExperiencia()) + " / " +
         * String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]), x +
         * 132, y + 70); g.setFont(new Font("Tahoma", Font.PLAIN, 10));
         * g.setColor(Color.GREEN); g.drawString(String.valueOf(personaje.getNivel()), x
         * + 59, y + 70);
         */
    }
}
