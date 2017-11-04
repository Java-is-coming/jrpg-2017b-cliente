package recursos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;

import frames.MenuCarga;
import frames.MenuMapas;
import mundo.Tile;

/**
 * Recursos generales del juego
 */
public final class Recursos {

    private static final int ELEMENTOS = 65;
    private static final int ANCHOBARRA = 345;

    private static final int ANCHO = 256; // Ancho del frame a obtener
    private static final int ALTO = 256; // Alto del frame a obtener

    private static final int CANT_FRAMES = 4;
    private static final int MULT_3 = 3;
    private static final int MULT_5 = 5;
    private static final int MULT_6 = 6;
    private static final int MULT_7 = 7;
    private static final int SIZE = 64;

    // Inicio Personajes
    // Hash de imagenes para los personajes (humano, ogro, elfo)
    public static Map<String, LinkedList<BufferedImage[]>> personaje = new HashMap<>();

    private static SpriteSheet spriteNPC;
    public static LinkedList<BufferedImage[]> npc = new LinkedList<>();
    private static BufferedImage[] npcIzq;
    private static BufferedImage[] npcArribaIzq;
    private static BufferedImage[] npcArriba;
    private static BufferedImage[] npcArribaDer;
    private static BufferedImage[] npcDer;
    private static BufferedImage[] npcAbajoDer;
    private static BufferedImage[] npcAbajo;
    private static BufferedImage[] npcAbajoIzq;

    private static SpriteSheet spriteHumano;
    public static LinkedList<BufferedImage[]> humano = new LinkedList<>();
    private static BufferedImage[] humanoIzq;
    private static BufferedImage[] humanoArribaIzq;
    private static BufferedImage[] humanoArriba;
    private static BufferedImage[] humanoArribaDer;
    private static BufferedImage[] humanoDer;
    private static BufferedImage[] humanoAbajoDer;
    private static BufferedImage[] humanoAbajo;
    private static BufferedImage[] humanoAbajoIzq;

    private static SpriteSheet spriteOgro;
    public static LinkedList<BufferedImage[]> orco = new LinkedList<>();
    private static BufferedImage[] orcoIzq;
    private static BufferedImage[] orcoArribaIzq;
    private static BufferedImage[] orcoArriba;
    private static BufferedImage[] orcoArribaDer;
    private static BufferedImage[] orcoDer;
    private static BufferedImage[] orcoAbajoDer;
    private static BufferedImage[] orcoAbajo;
    private static BufferedImage[] orcoAbajoIzq;

    private static SpriteSheet spriteElfo;
    public static LinkedList<BufferedImage[]> elfo = new LinkedList<>();
    private static BufferedImage[] elfoIzq;
    private static BufferedImage[] elfoArribaIzq;
    private static BufferedImage[] elfoArriba;
    private static BufferedImage[] elfoArribaDer;
    private static BufferedImage[] elfoDer;
    private static BufferedImage[] elfoAbajoDer;
    private static BufferedImage[] elfoAbajo;
    private static BufferedImage[] elfoAbajoIzq;
    // Fin Personajes

    // Entorno
    private static SpriteSheet trees;
    public static BufferedImage cesped;
    public static BufferedImage roca;
    public static BufferedImage background;
    public static BufferedImage marco;
    public static BufferedImage botonMenu;
    public static BufferedImage menuEnemigo;
    public static BufferedImage greenTree;
    public static BufferedImage nievePiso1;
    public static BufferedImage iceBlock;
    // Fin Entorno

    // Batalla
    public static BufferedImage barraSpells;
    public static BufferedImage estadoPersonaje;
    public static BufferedImage barraSalud;
    public static BufferedImage barraEnergia;
    public static BufferedImage barraExperiencia;
    public static BufferedImage menuBatalla;
    public static BufferedImage menuBatallaDeshabilitado;
    public static BufferedImage noItem;
    public static BufferedImage mochila;
    public static BufferedImage menu;
    public static BufferedImage chat;
    public static Map<String, BufferedImage> habilidades = new HashMap<>();
    // Fin Batalla

    /**
     * Constructor privado
     */
    private Recursos() {

    }

    /**
     * Se cargan todos los recursos del juego una sola vez al inicio
     *
     * @param menuCarga
     *            menu
     * @throws IOException
     *             error
     */
    public static void cargar(final MenuCarga menuCarga) throws IOException {

        int elementosCargados = 0;

        // Items
        noItem = ImageIO.read(new File("recursos//noItem.png"));
        mochila = ImageIO.read(new File("recursos//mochila.png"));
        menu = ImageIO.read(new File("recursos//menu.png"));
        chat = ImageIO.read(new File("recursos//chat.png"));

        // Inicio humano
        spriteHumano = new SpriteSheet(CargadorImagen.cargarImagen("/Humano.png"));

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        humanoIzq = new BufferedImage[CANT_FRAMES];
        humanoArribaIzq = new BufferedImage[CANT_FRAMES];
        humanoArriba = new BufferedImage[CANT_FRAMES];
        humanoArribaDer = new BufferedImage[CANT_FRAMES];
        humanoDer = new BufferedImage[CANT_FRAMES];
        humanoAbajoDer = new BufferedImage[CANT_FRAMES];
        humanoAbajo = new BufferedImage[CANT_FRAMES];
        humanoAbajoIzq = new BufferedImage[CANT_FRAMES];

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoIzq[i] = spriteHumano.getTile(ANCHO * i, 0, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoArribaIzq[i] = spriteHumano.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoArriba[i] = spriteHumano.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoArribaDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * MULT_3, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * CANT_FRAMES, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoAbajoDer[i] = spriteHumano.getTile(ANCHO * i, ALTO * MULT_5, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoAbajo[i] = spriteHumano.getTile(ANCHO * i, ALTO * MULT_6, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            humanoAbajoIzq[i] = spriteHumano.getTile(ANCHO * i, ALTO * MULT_7, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        humano.add(humanoIzq);
        humano.add(humanoArribaIzq);
        humano.add(humanoArriba);
        humano.add(humanoArribaDer);
        humano.add(humanoDer);
        humano.add(humanoAbajoDer);
        humano.add(humanoAbajo);
        humano.add(humanoAbajoIzq);
        // Fin humano

        // Inicio NPC
        spriteNPC = new SpriteSheet(CargadorImagen.cargarImagen("/NPC.png"));

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        npcIzq = new BufferedImage[CANT_FRAMES];
        npcArribaIzq = new BufferedImage[CANT_FRAMES];
        npcArriba = new BufferedImage[CANT_FRAMES];
        npcArribaDer = new BufferedImage[CANT_FRAMES];
        npcDer = new BufferedImage[CANT_FRAMES];
        npcAbajoDer = new BufferedImage[CANT_FRAMES];
        npcAbajo = new BufferedImage[CANT_FRAMES];
        npcAbajoIzq = new BufferedImage[CANT_FRAMES];

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcIzq[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), 0, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcArribaIzq[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcArriba[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * 2, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcArribaDer[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * MULT_3, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcDer[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * CANT_FRAMES, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcAbajoDer[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * MULT_5, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcAbajo[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * MULT_6, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            npcAbajoIzq[i] = spriteNPC.getTile(ANCHO * (i + CANT_FRAMES), ALTO * MULT_7, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        npc.add(npcIzq);
        npc.add(npcArribaIzq);
        npc.add(npcArriba);
        npc.add(npcArribaDer);
        npc.add(npcDer);
        npc.add(npcAbajoDer);
        npc.add(npcAbajo);
        npc.add(npcAbajoIzq);
        // Fin NPC

        // Inicio Ogro
        spriteOgro = new SpriteSheet(CargadorImagen.cargarImagen("/Ogro.png"));

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        orcoIzq = new BufferedImage[CANT_FRAMES];
        orcoArribaIzq = new BufferedImage[CANT_FRAMES];
        orcoArriba = new BufferedImage[CANT_FRAMES];
        orcoArribaDer = new BufferedImage[CANT_FRAMES];
        orcoDer = new BufferedImage[CANT_FRAMES];
        orcoAbajoDer = new BufferedImage[CANT_FRAMES];
        orcoAbajo = new BufferedImage[CANT_FRAMES];
        orcoAbajoIzq = new BufferedImage[CANT_FRAMES];

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoIzq[i] = spriteOgro.getTile(ANCHO * i, 0, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoArribaIzq[i] = spriteOgro.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoArriba[i] = spriteOgro.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoArribaDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * MULT_3, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * CANT_FRAMES, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoAbajoDer[i] = spriteOgro.getTile(ANCHO * i, ALTO * MULT_5, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoAbajo[i] = spriteOgro.getTile(ANCHO * i, ALTO * MULT_6, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            orcoAbajoIzq[i] = spriteOgro.getTile(ANCHO * i, ALTO * MULT_7, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        orco.add(orcoIzq);
        orco.add(orcoArribaIzq);
        orco.add(orcoArriba);
        orco.add(orcoArribaDer);
        orco.add(orcoDer);
        orco.add(orcoAbajoDer);
        orco.add(orcoAbajo);
        orco.add(orcoAbajoIzq);

        // Fin Ogro

        // Inicio Elfo
        spriteElfo = new SpriteSheet(CargadorImagen.cargarImagen("/elfo2.png"));

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        elfoIzq = new BufferedImage[CANT_FRAMES];
        elfoArribaIzq = new BufferedImage[CANT_FRAMES];
        elfoArriba = new BufferedImage[CANT_FRAMES];
        elfoArribaDer = new BufferedImage[CANT_FRAMES];
        elfoDer = new BufferedImage[CANT_FRAMES];
        elfoAbajoDer = new BufferedImage[CANT_FRAMES];
        elfoAbajo = new BufferedImage[CANT_FRAMES];
        elfoAbajoIzq = new BufferedImage[CANT_FRAMES];

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoIzq[i] = spriteElfo.getTile(ANCHO * i, 0, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoArribaIzq[i] = spriteElfo.getTile(ANCHO * i, ALTO, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoArriba[i] = spriteElfo.getTile(ANCHO * i, ALTO * 2, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoArribaDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * MULT_3, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * CANT_FRAMES, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoAbajoDer[i] = spriteElfo.getTile(ANCHO * i, ALTO * MULT_5, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoAbajo[i] = spriteElfo.getTile(ANCHO * i, ALTO * MULT_6, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        for (int i = 0; i < CANT_FRAMES; i++) {
            elfoAbajoIzq[i] = spriteElfo.getTile(ANCHO * i, ALTO * MULT_7, ANCHO, ALTO);
        }

        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        elfo.add(elfoIzq);
        elfo.add(elfoArribaIzq);
        elfo.add(elfoArriba);
        elfo.add(elfoArribaDer);
        elfo.add(elfoDer);
        elfo.add(elfoAbajoDer);
        elfo.add(elfoAbajo);
        elfo.add(elfoAbajoIzq);

        // Fin Elfo

        // Agrego los pj al hash
        personaje.put("Humano", humano);
        personaje.put("Orco", orco);
        personaje.put("Elfo", elfo);
        personaje.put("NPC", npc);

        // Inicio Entorno
        cesped = CargadorImagen.cargarImagen("/Cesped.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        roca = CargadorImagen.cargarImagen("/rock.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        background = CargadorImagen.cargarImagen("/background.jpg");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        marco = CargadorImagen.cargarImagen("/marco.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        botonMenu = CargadorImagen.cargarImagen("/botonMenu.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        menuEnemigo = CargadorImagen.cargarImagen("/MenuEnemigo.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        trees = new SpriteSheet(CargadorImagen.cargarImagen("/trees.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        final int ancho2 = 42;
        final int alto2 = 50;
        greenTree = trees.getTile(0, 0, ancho2, alto2);
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        nievePiso1 = CargadorImagen.cargarImagen("/nieve piso.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        iceBlock = CargadorImagen.cargarImagen("/nieve cubo.png");

        // Mapa
        final int cantTiles = 81;
        final int cant = 10;
        final int cantY = 8;
        if (MenuMapas.getNumberMap() == 1) {
            final SpriteSheet mapaAubenor = new SpriteSheet(CargadorImagen.cargarImagen("/Aubenor.png"));
            Tile.setAubenor(new Tile[cantTiles]);
            final boolean[][] solidezAubenor = {{true, true, false, true, false, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {true, false, false, false, false, false, false, false, true, true},
                    {false, false, false, false, false, false, false, false, true, true},
                    {false, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true}};
            for (int y = 0; y < cantY; y++) {
                for (int x = 0; x < cant; x++) {
                    Tile.getAubenor()[y * cant + x + 1] = new Tile(mapaAubenor.getTile(x * SIZE, y * SIZE, SIZE, SIZE),
                            y * cant + x + 1, solidezAubenor[y][x], SIZE, SIZE);
                }
            }
        } else {
            final SpriteSheet mapaAris = new SpriteSheet(CargadorImagen.cargarImagen("/Aris.png"));
            Tile.setAris(new Tile[cantTiles]);
            final boolean[][] solidezAris = {{true, false, false, false, false, false, false, true, true, true},
                    {false, false, false, false, false, false, false, false, true, true},
                    {false, false, false, false, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {false, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true}};
            for (int y = 0; y < cantY; y++) {
                for (int x = 0; x < cant; x++) {
                    Tile.getAris()[y * cant + x + 1] = new Tile(mapaAris.getTile(x * SIZE, y * SIZE, SIZE, SIZE),
                            y * cant + x + 1, solidezAris[y][x], SIZE, SIZE);
                }
            }
        }

        // Fin Entorno

        // Inicio Batalla
        barraSpells = CargadorImagen.cargarImagen("/BarraSpells.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        estadoPersonaje = CargadorImagen.cargarImagen("/EstadoPersonaje.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        barraSalud = CargadorImagen.cargarImagen("/BarraDeSalud.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        barraEnergia = CargadorImagen.cargarImagen("/BarraDeEnergia.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        barraExperiencia = CargadorImagen.cargarImagen("/BarraDeExperiencia.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Golpe Level", CargadorImagen.cargarImagen("/Golpe Level.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Ataque Bosque", CargadorImagen.cargarImagen("/Ataque Bosque.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Golpe Defensa", CargadorImagen.cargarImagen("/Golpe Defensa.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Mordisco de Vida", CargadorImagen.cargarImagen("/Mordisco de Vida.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Incentivar", CargadorImagen.cargarImagen("/Incentivar.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Golpe Fatal", CargadorImagen.cargarImagen("/Golpe Fatal.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Ataque Doble", CargadorImagen.cargarImagen("/Ataque Doble.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Aumentar Defensa", CargadorImagen.cargarImagen("/Aumentar Defensa.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Ignorar Defensa", CargadorImagen.cargarImagen("/Ignorar Defensa.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Bola de Fuego", CargadorImagen.cargarImagen("/Bola de Fuego.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Curar Aliado", CargadorImagen.cargarImagen("/Curar Aliado.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Robar Energia y Salud", CargadorImagen.cargarImagen("/Robar Energia y Salud.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Golpe Critico", CargadorImagen.cargarImagen("/Golpe Critico.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Aumentar Evasion", CargadorImagen.cargarImagen("/Aumentar Evasion.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Robar", CargadorImagen.cargarImagen("/Robar.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        habilidades.put("Ser Energizado", CargadorImagen.cargarImagen("/Ser Energizado.png"));
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        menuBatalla = CargadorImagen.cargarImagen("/MenuBatalla.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);

        menuBatallaDeshabilitado = CargadorImagen.cargarImagen("/MenuBatallaDeshabilitado.png");
        actualizarBarraDeCarga(++elementosCargados, menuCarga);
        // Fin Batalla
    }

    /**
     * Actualiza barra de carga
     *
     * @param elementosCargados
     *            elementos ya cargados
     * @param menuCarga
     *            menu a actualizar
     */
    private static void actualizarBarraDeCarga(final int elementosCargados, final MenuCarga menuCarga) {
        menuCarga.setBarraCargando(elementosCargados * ANCHOBARRA / ELEMENTOS);
    }
}
