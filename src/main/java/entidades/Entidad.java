package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import chat.VentanaContactos;
import estados.Estado;
import frames.MenuEscape;
import frames.MenuInventario;
import interfaz.MenuInfoNPC;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNPC;
import mundo.Grafo;
import mundo.Mundo;
import mundo.Nodo;
import recursos.Recursos;

/**
 * Clase Entidad
 */
public class Entidad {
    private Juego juego;

    // Tamaño de la entidad
    private int ancho;
    private int alto;

    // Posiciones
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float xFinal;
    private float yFinal;
    private final int xOffset;
    private final int yOffset;
    private int drawX;
    private int drawY;
    private int[] posMouseRecorrido;
    private int[] posMouse;

    // Movimiento Actual
    private static final int HORIZONTAL_DER = 4;
    private static final int HORIZONTAL_IZQ = 0;
    private static final int VERTICAL_SUP = 2;
    private static final int VERTICAL_INF = 6;
    private static final int DIAGONAL_INF_IZQ = 7;
    private static final int DIAGONAL_INF_DER = 5;
    private static final int DIAGONAL_SUP_DER = 3;
    private static final int DIAGONAL_SUP_IZQ = 1;
    private int movimientoHacia = VERTICAL_INF;
    private boolean enMovimiento;

    // Animaciones
    private final Animacion moverIzq;
    private final Animacion moverArribaIzq;
    private final Animacion moverArriba;
    private final Animacion moverArribaDer;
    private final Animacion moverDer;
    private final Animacion moverAbajoDer;
    private final Animacion moverAbajo;
    private final Animacion moverAbajoIzq;

    private final Gson gson = new Gson();
    private int intervaloEnvio = 0;

    // pila de movimiento
    private PilaDeTiles pilaMovimiento;
    private int[] tileActual;
    private int[] tileFinal;
    private int[] tileMoverme;

    private final Mundo mundo;
    private final String nombre;
    private int[] tilePersonajes;
    private int idEnemigo;

    // Ubicacion para abrir comerciar.
    private float xComercio;
    private float yComercio;
    private float[] comercio;

    /**
     * Constructor de la clase Entidad
     *
     * @param juego
     *            juego con el que se instancia Entidad
     * @param mundo
     *            mundo con el que se instancia Entidad
     * @param ancho
     *            ancho
     * @param alto
     *            alto
     * @param nombre
     *            nombre del personaje
     * @param spawnX
     *            tile X donde spawnea
     * @param spawnY
     *            tile Y donde spawnea
     * @param animaciones
     *            animaciones del personaje
     * @param velAnimacion
     *            velocidad de animacion del personaje
     */
    public Entidad(final Juego juego, final Mundo mundo, final int ancho, final int alto, final String nombre,
            final float spawnX, final float spawnY, final LinkedList<BufferedImage[]> animaciones,
            final int velAnimacion) {
        this.setJuego(juego);
        this.ancho = ancho;
        this.alto = alto;
        this.nombre = nombre;
        this.mundo = mundo;
        xOffset = ancho / 2;
        yOffset = alto / 2;
        final int size2 = 64;
        x = (int) (spawnX / size2) * size2;
        final int size1 = 32;
        y = (int) (spawnY / size1) * size1;

        moverIzq = new Animacion(velAnimacion, animaciones.get(HORIZONTAL_IZQ));
        moverArribaIzq = new Animacion(velAnimacion, animaciones.get(DIAGONAL_SUP_IZQ));
        moverArriba = new Animacion(velAnimacion, animaciones.get(VERTICAL_SUP));
        moverArribaDer = new Animacion(velAnimacion, animaciones.get(DIAGONAL_SUP_DER));
        moverDer = new Animacion(velAnimacion, animaciones.get(HORIZONTAL_DER));
        moverAbajoDer = new Animacion(velAnimacion, animaciones.get(DIAGONAL_INF_DER));
        moverAbajo = new Animacion(velAnimacion, animaciones.get(VERTICAL_INF));
        moverAbajoIzq = new Animacion(velAnimacion, animaciones.get(DIAGONAL_INF_IZQ));

        // Informo mi posicion actual
        juego.getUbicacionPersonaje().setPosX(x);
        juego.getUbicacionPersonaje().setPosY(y);
        juego.getUbicacionPersonaje().setDireccion(getDireccion());
        juego.getUbicacionPersonaje().setFrame(getFrame());
    }

    /**
     * Actualiza el personaje
     */
    public void actualizar() {

        if (enMovimiento) {
            moverIzq.actualizar();
            moverArribaIzq.actualizar();
            moverArriba.actualizar();
            moverArribaDer.actualizar();
            moverDer.actualizar();
            moverAbajoDer.actualizar();
            moverAbajo.actualizar();
            moverAbajoIzq.actualizar();
        } else {
            moverIzq.reset();
            moverArribaIzq.reset();
            moverArriba.reset();
            moverArribaDer.reset();
            moverDer.reset();
            moverAbajoDer.reset();
            moverAbajo.reset();
            moverAbajoIzq.reset();
        }

        getEntrada();
        mover();

        getJuego().getCamara().centrar(this);
    }

    /**
     * Devuelve la entrada
     */
    public void getEntrada() {
        posMouseRecorrido = getJuego().getHandlerMouse().getPosMouseRecorrido();
        posMouse = getJuego().getHandlerMouse().getPosMouse();
        final int posLimit = 738;
        final int posLimit1 = 797;
        final int posLimit2 = 545;
        final int posLimit3 = 597;
        if (getJuego().getHandlerMouse().getNuevoClick() && posMouse[0] >= posLimit && posMouse[0] <= posLimit1
                && posMouse[1] >= posLimit2 && posMouse[1] <= posLimit3) {
            if (Pantalla.getMenuInventario() == null) {
                Pantalla.setMenuInventario(new MenuInventario(getJuego().getCliente()));
                Pantalla.getMenuInventario().setVisible(true);
            }
            getJuego().getHandlerMouse().setNuevoClick(false);
        }
        final int posLimit4 = 105;
        final int posLimit5 = 562;
        final int posLimit8 = 3;
        if (getJuego().getHandlerMouse().getNuevoClick() && posMouse[0] >= posLimit8 && posMouse[0] <= posLimit4
                && posMouse[1] >= posLimit5 && posMouse[1] <= posLimit3) {
            if (Pantalla.getMenuEscp() == null) {
                Pantalla.setMenuEscp(new MenuEscape(getJuego().getCliente()));
                Pantalla.getMenuEscp().setVisible(true);
            }
            getJuego().getHandlerMouse().setNuevoClick(false);
        }
        final int posLimit6 = 524;
        final int posLimit7 = 559;
        if (getJuego().getHandlerMouse().getNuevoClick() && posMouse[0] >= posLimit8 && posMouse[0] <= posLimit4
                && posMouse[1] >= posLimit6 && posMouse[1] <= posLimit7) {
            if (Pantalla.getVentContac() == null) {
                Pantalla.setVentContac(new VentanaContactos(getJuego()));
                Pantalla.getVentContac().setVisible(true);
            }
            getJuego().getHandlerMouse().setNuevoClick(false);
        }
        // Tomo el click izquierdo
        if (getJuego().getHandlerMouse().getNuevoClick()) {

            final int comercioLimit = 44;
            final int comercioLimit1 = 71;
            final int comercioLimit2 = 29;
            if (getJuego().getEstadoJuego().getHaySolicitud()) {

                if (getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoNPC.MENU_BATALLARNPC) {
                    if (getJuego().getEstadoJuego().getMenuEnemigoNPC().clickEnMenu(posMouse[0], posMouse[1])) {
                        if (getJuego().getEstadoJuego().getMenuEnemigoNPC().clickEnBoton(posMouse[0], posMouse[1])) {

                            // juego.getEstadoJuego().setHaySolicitud(false, null,
                            // MenuInfoNPC.menuBatallarNPC);
                            final PaqueteBatalla pBatalla = new PaqueteBatalla(PaqueteBatalla.BATALLAR_NPC);

                            pBatalla.setId(getJuego().getPersonaje().getId());
                            pBatalla.setIdEnemigo(idEnemigo);

                            getJuego().getEstadoJuego().setHaySolicitud(false, null);

                            try {
                                getJuego().getCliente().getSalida().writeObject(gson.toJson(pBatalla));
                            } catch (final IOException e) {
                                JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
                            }

                        } else if (getJuego().getEstadoJuego().getMenuEnemigoNPC().clickEnCerrar(posMouse[0],
                                posMouse[1])) {
                            getJuego().getEstadoJuego().setHaySolicitud(false, null, MenuInfoNPC.MENU_BATALLARNPC);
                        }

                    }
                } else if (getJuego().getEstadoJuego().getMenuEnemigo().clickEnMenu(posMouse[0], posMouse[1])) {
                    if (getJuego().getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])) {

                        // Pregunto si menuBatallar o menuComerciar o menu menuBatallarNPC, sino no me
                        // interesa hacer esto
                        if (getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENU_BATALLAR
                                || getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENU_COMERCIAR) {

                            // Guardo las poss con el que quiero comerciar
                            xComercio = getJuego().getUbicacionPersonajes().get(idEnemigo).getPosX();
                            yComercio = getJuego().getUbicacionPersonajes().get(idEnemigo).getPosY();
                            comercio = Mundo.isoA2D(xComercio, yComercio);
                        }
                        // pregunto si el menu emergente es de tipo batalla
                        if (getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENU_BATALLAR) {
                            // ME FIJO SI CON EL QUE QUIERO BATALLAR ESTA EN LA ZONA DE COMERCIO
                            if (!((int) comercio[0] >= comercioLimit && (int) comercio[0] <= comercioLimit1
                                    && (int) comercio[1] >= 0 && (int) comercio[1] <= comercioLimit2)) {

                                getJuego().getEstadoJuego().setHaySolicitud(false, null,
                                        MenuInfoPersonaje.MENU_BATALLAR);
                                final PaqueteBatalla pBatalla = new PaqueteBatalla(PaqueteBatalla.BATALLAR_PERSONAJE);

                                pBatalla.setId(getJuego().getPersonaje().getId());
                                pBatalla.setIdEnemigo(idEnemigo);

                                getJuego().getEstadoJuego().setHaySolicitud(false, null,
                                        MenuInfoPersonaje.MENU_BATALLAR);

                                try {
                                    getJuego().getCliente().getSalida().writeObject(gson.toJson(pBatalla));
                                } catch (final IOException e) {
                                    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "El otro usuario se encuentra dentro de la zona de comercio");
                            }
                        } else if (getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENU_COMERCIAR) {
                            // PREGUNTO SI EL MENU EMERGENTE ES DE TIPO COMERCIO
                            if (getJuego().getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENU_COMERCIAR) {
                                if ((int) comercio[0] >= comercioLimit && (int) comercio[0] <= comercioLimit1
                                        && (int) comercio[1] >= 0 && (int) comercio[1] <= comercioLimit2) {
                                    if (getJuego().getCliente().getM1() == null) {
                                        getJuego().getCliente().setPaqueteComercio(new PaqueteComerciar());
                                        getJuego().getCliente().getPaqueteComercio()
                                                .setId(getJuego().getPersonaje().getId());
                                        getJuego().getCliente().getPaqueteComercio().setIdEnemigo(idEnemigo);

                                        try {
                                            getJuego().getCliente().getSalida().writeObject(
                                                    gson.toJson(getJuego().getCliente().getPaqueteComercio()));
                                        } catch (final IOException e) {
                                            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Ya te encuentras comerciando!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "El otro usuario no se encuentra dentro de la zona de comercio");
                                }
                            }
                        }
                        getJuego().getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENU_BATALLAR);

                    } else if (getJuego().getEstadoJuego().getMenuEnemigo().clickEnCerrar(posMouse[0], posMouse[1])) {
                        getJuego().getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENU_BATALLAR);
                    }
                } else {
                    getJuego().getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENU_BATALLAR);
                }
            } else {

                boolean esPersonaje = false;

                Iterator<Integer> it = getJuego().getUbicacionPersonajes().keySet().iterator();
                int key;
                int[] movermeTile = Mundo.mouseATile(posMouse[0] + getJuego().getCamara().getxOffset() - xOffset,
                        posMouse[1] + getJuego().getCamara().getyOffset() - yOffset);
                PaqueteMovimiento actual;

                while (it.hasNext()) {
                    key = it.next();
                    actual = getJuego().getUbicacionPersonajes().get(key);
                    tilePersonajes = Mundo.mouseATile(actual.getPosX(), actual.getPosY());
                    if (actual != null && actual.getIdPersonaje() != getJuego().getPersonaje().getId()
                            && getJuego().getPersonajesConectados().get(actual.getIdPersonaje()) != null
                            && getJuego().getPersonajesConectados().get(actual.getIdPersonaje())
                                    .getEstado() == Estado.ESTADO_JUEGO) {

                        if (movermeTile[0] == tilePersonajes[0] && movermeTile[1] == tilePersonajes[1]) {
                            idEnemigo = actual.getIdPersonaje();
                            final float[] xy = Mundo.isoA2D(x, y);
                            // ESTA ESTE PARA NO MOVERME HASTA EL LUGAR.
                            if (xy[0] >= comercioLimit && xy[0] <= comercioLimit1 && xy[1] >= 0
                                    && xy[1] <= comercioLimit2) {
                                // SI ESTOY DENTRO DE LA ZONA DE COMERCIO SETEO QUE SE ABRA EL MENU
                                // DE COMERCIO
                                getJuego().getEstadoJuego().setHaySolicitud(true,
                                        getJuego().getPersonajesConectados().get(idEnemigo),
                                        MenuInfoPersonaje.MENU_COMERCIAR);

                            } else {
                                // SI ESTOY DENTRO DE LA ZONA DE BATALLA SETEO QUE SE ABRA EL MENU
                                // DE BATALLA
                                getJuego().getEstadoJuego().setHaySolicitud(true,
                                        getJuego().getPersonajesConectados().get(idEnemigo),
                                        MenuInfoPersonaje.MENU_BATALLAR);
                            }
                            esPersonaje = true;
                            getJuego().getHandlerMouse().setNuevoClick(false);
                        }
                    }
                }

                if (!esPersonaje) {
                    it = getJuego().getNPCsDisponibles().keySet().iterator();
                    key = 0;
                    movermeTile = Mundo.mouseATile(posMouse[0] + getJuego().getCamara().getxOffset() - xOffset,
                            posMouse[1] + getJuego().getCamara().getyOffset() - yOffset);

                    PaqueteNPC actualNPC;

                    while (it.hasNext()) {
                        key = it.next();
                        actualNPC = getJuego().getNPCsDisponibles().get(key);
                        tilePersonajes = Mundo.mouseATile(actualNPC.getPosX(), actualNPC.getPosY());
                        if (actualNPC != null && getJuego().getNPCsDisponibles().get(actualNPC.getId()) != null
                                && getJuego().getNPCsDisponibles().get(actualNPC.getId())
                                        .getEstado() == Estado.ESTADO_JUEGO) {

                            if (Math.abs(movermeTile[0] - tilePersonajes[0]) <= 2
                                    && Math.abs(movermeTile[1] - tilePersonajes[1]) <= 2) {
                                idEnemigo = actualNPC.getId();

                                getJuego().getEstadoJuego().setHaySolicitud(true,
                                        getJuego().getNPCsDisponibles().get(idEnemigo));

                                getJuego().getHandlerMouse().setNuevoClick(false);
                            }
                        }
                    }
                }
            }
        }

        if (getJuego().getHandlerMouse().getNuevoRecorrido() && !getJuego().getEstadoJuego().getHaySolicitud()) {

            tileMoverme = Mundo.mouseATile(posMouseRecorrido[0] + getJuego().getCamara().getxOffset() - xOffset,
                    posMouseRecorrido[1] + getJuego().getCamara().getyOffset() - yOffset);

            getJuego().getHandlerMouse().setNuevoRecorrido(false);

            pilaMovimiento = null;

            getJuego().getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENU_BATALLAR);
        }

        if (!enMovimiento && tileMoverme != null) {

            enMovimiento = false;

            tileActual = Mundo.mouseATile(x, y);

            if (tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mundo.obtenerAncho()
                    || tileMoverme[1] >= mundo.obtenerAlto()) {
                enMovimiento = false;
                getJuego().getHandlerMouse().setNuevoRecorrido(false);
                pilaMovimiento = null;
                tileMoverme = null;
                return;
            }

            if (tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1]
                    || (mundo.getTile(tileMoverme[0], tileMoverme[1]).esSolido()
                            && !getJuego().getPersonaje().getModoNoClip())) {
                tileMoverme = null;
                enMovimiento = false;
                getJuego().getHandlerMouse().setNuevoRecorrido(false);
                pilaMovimiento = null;
                return;
            }

            if (pilaMovimiento == null) {
                pilaMovimiento = caminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
            }
            // Me muevo al primero de la pila
            final NodoDePila nodoActualTile = pilaMovimiento.pop();

            if (nodoActualTile == null) {
                enMovimiento = false;
                getJuego().getHandlerMouse().setNuevoRecorrido(false);
                pilaMovimiento = null;
                tileMoverme = null;
                return;
            }

            tileFinal = new int[2];
            tileFinal[0] = nodoActualTile.obtenerX();
            tileFinal[1] = nodoActualTile.obtenerY();

            xFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[0];
            yFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[1];

            if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1) {
                movimientoHacia = VERTICAL_SUP;
            }
            if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1) {
                movimientoHacia = VERTICAL_INF;
            }
            if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1) {
                movimientoHacia = HORIZONTAL_IZQ;
            }
            if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1) {
                movimientoHacia = HORIZONTAL_DER;
            }
            if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1]) {
                movimientoHacia = DIAGONAL_SUP_IZQ;
            }
            if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1]) {
                movimientoHacia = DIAGONAL_INF_DER;
            }
            if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1) {
                movimientoHacia = DIAGONAL_SUP_DER;
            }
            if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1) {
                movimientoHacia = DIAGONAL_INF_IZQ;
            }
            enMovimiento = true;
        }
    }

    /**
     * Mueve el personaje
     */
    public void mover() {

        dx = 0;
        dy = 0;

        final double paso = 1;

        final int size = 32;
        if (enMovimiento && !(x == xFinal && y == yFinal - size)) {
            if (movimientoHacia == VERTICAL_SUP) {
                dy -= paso;
            } else if (movimientoHacia == VERTICAL_INF) {
                dy += paso;
            } else if (movimientoHacia == HORIZONTAL_DER) {
                dx += paso;
            } else if (movimientoHacia == HORIZONTAL_IZQ) {
                dx -= paso;
            } else if (movimientoHacia == DIAGONAL_INF_DER) {
                dx += paso;
                dy += paso / 2;
            } else if (movimientoHacia == DIAGONAL_INF_IZQ) {
                dx -= paso;
                dy += paso / 2;
            } else if (movimientoHacia == DIAGONAL_SUP_DER) {
                dx += paso;
                dy -= paso / 2;
            } else if (movimientoHacia == DIAGONAL_SUP_IZQ) {
                dx -= paso;
                dy -= paso / 2;
            }

            x += dx;
            y += dy;

            // Le envio la posicion
            if (intervaloEnvio == 2) {
                enviarPosicion();
                intervaloEnvio = 0;
            }
            intervaloEnvio++;

            if (x == xFinal && y == yFinal - size) {
                enMovimiento = false;
            }
        }
    }

    /**
     * Grafica el frame del personaje
     *
     * @param g
     *            graphics
     */
    public void graficar(final Graphics g) {
        drawX = (int) (x - getJuego().getCamara().getxOffset());
        drawY = (int) (y - getJuego().getCamara().getyOffset());
        final int offsetY = 4;
        g.drawImage(getFrameAnimacionActual(), drawX, drawY + offsetY, ancho, alto, null);
    }

    /**
     * Grafica el nombre
     *
     * @param g
     *            graphics
     */
    public void graficarNombre(final Graphics g) {
        g.setColor(Color.WHITE);
        final int fontSize = 15;
        g.setFont(new Font("Book Antiqua", Font.BOLD, fontSize));
        final int offsetx = 32;
        final int offsetY = 20;
        final int height = 10;
        Pantalla.centerString(g, new java.awt.Rectangle(drawX + offsetx, drawY - offsetY, 0, height),
                nombre + " X: " + getX() + " Y: " + getY());
    }

    /**
     * Obtiene el frameActual del personaje
     *
     * @return BufferedImage image
     */
    private BufferedImage getFrameAnimacionActual() {
        if (movimientoHacia == HORIZONTAL_IZQ) {
            return moverIzq.getFrameActual();
        } else if (movimientoHacia == HORIZONTAL_DER) {
            return moverDer.getFrameActual();
        } else if (movimientoHacia == VERTICAL_SUP) {
            return moverArriba.getFrameActual();
        } else if (movimientoHacia == VERTICAL_INF) {
            return moverAbajo.getFrameActual();
        } else if (movimientoHacia == DIAGONAL_INF_IZQ) {
            return moverAbajoIzq.getFrameActual();
        } else if (movimientoHacia == DIAGONAL_INF_DER) {
            return moverAbajoDer.getFrameActual();
        } else if (movimientoHacia == DIAGONAL_SUP_IZQ) {
            return moverArribaIzq.getFrameActual();
        } else if (movimientoHacia == DIAGONAL_SUP_DER) {
            return moverArribaDer.getFrameActual();
        }

        final int frame = 6;
        return Recursos.orco.get(frame)[0];
    }

    /**
     * Pide la direccion donde va
     *
     * @return devuelve el movimiento hacia donde va
     */
    private int getDireccion() {
        return movimientoHacia;
    }

    /**
     * Obtiene el frame donde esta el personaje
     *
     * @return int frame
     */
    private int getFrame() {
        if (movimientoHacia == HORIZONTAL_IZQ) {
            return moverIzq.getFrame();
        } else if (movimientoHacia == HORIZONTAL_DER) {
            return moverDer.getFrame();
        } else if (movimientoHacia == VERTICAL_SUP) {
            return moverArriba.getFrame();
        } else if (movimientoHacia == VERTICAL_INF) {
            return moverAbajo.getFrame();
        } else if (movimientoHacia == DIAGONAL_INF_IZQ) {
            return moverAbajoIzq.getFrame();
        } else if (movimientoHacia == DIAGONAL_INF_DER) {
            return moverAbajoDer.getFrame();
        } else if (movimientoHacia == DIAGONAL_SUP_IZQ) {
            return moverArribaIzq.getFrame();
        } else if (movimientoHacia == DIAGONAL_SUP_DER) {
            return moverArribaDer.getFrame();
        }

        return 0;
    }

    /**
     * Envia la posicion del personaje
     */
    private void enviarPosicion() {
        getJuego().getUbicacionPersonaje().setPosX(x);
        getJuego().getUbicacionPersonaje().setPosY(y);
        getJuego().getUbicacionPersonaje().setDireccion(getDireccion());
        getJuego().getUbicacionPersonaje().setFrame(getFrame());
        try {
            getJuego().getCliente().getSalida()
                    .writeObject(gson.toJson(getJuego().getUbicacionPersonaje(), PaqueteMovimiento.class));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
        }
    }

    /**
     * Busca el camino más corto a recorrer para llegar a una posición
     *
     * @param xInicial
     *            ubicacion en X inicial
     * @param yInicial
     *            ubicacion en Y inicial
     * @param xFin
     *            ubicacion en X final
     * @param yFin
     *            ubicacion en Y final
     * @return la pila de tiles a recorrer
     */
    private PilaDeTiles caminoMasCorto(final int xInicial, final int yInicial, final int xFin, final int yFin) {
        Grafo grafoLibres;

        if (!getJuego().getPersonaje().getModoNoClip()) {
            grafoLibres = mundo.obtenerGrafoDeTilesNoSolidos();
        } else {
            grafoLibres = mundo.obtenerGrafoDeTodosTiles();
        }

        // Transformo las coordenadas iniciales y finales en indices
        final int nodoInicial = (yInicial - grafoLibres.obtenerNodos()[0].obtenerY())
                * (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicial
                - grafoLibres.obtenerNodos()[0].obtenerX();

        int nodoFinal = (yFin - grafoLibres.obtenerNodos()[0].obtenerY())
                * (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFin
                - grafoLibres.obtenerNodos()[0].obtenerX();

        // Hago todo
        final double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
        final int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
        final boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
        int cantSolucion = 0;
        // Lleno la matriz de costos de numeros grandes
        for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
            vecCostos[i] = Double.MAX_VALUE;
        }
        // Adyacentes al nodo inicial
        conjSolucion[nodoInicial] = true;
        cantSolucion++;
        vecCostos[nodoInicial] = 0;
        Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
        final double indice = 1.5;
        for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
            if (estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial],
                    grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
                vecCostos[adyacentes[i].obtenerIndice()] = indice;
            } else {
                vecCostos[adyacentes[i].obtenerIndice()] = 1;
            }
            vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
        }
        // Aplico Dijkstra
        while (cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()) {
            // Elijo W perteneciente al conjunto restante tal que el costo de W
            // sea minimo
            double minimo = Double.MAX_VALUE;
            int indiceMinimo = 0;
            for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
                if (!conjSolucion[i] && vecCostos[i] < minimo) {
                    minimo = vecCostos[i];
                    indiceMinimo = i;
                }
            }
            // Pongo a W en el conj solucion
            conjSolucion[indiceMinimo] = true;
            cantSolucion++;
            // Por cada nodo I adyacente a W del conj restante
            // Le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
            adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
            for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
                double valorASumar = 1;
                if (estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo],
                        grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
                    valorASumar = indice;
                }
                if (vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]) {
                    vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
                    vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
                }
            }
        }
        // Creo el vector de nodos hasta donde quiere llegar
        final PilaDeTiles camino = new PilaDeTiles();
        while (nodoFinal != nodoInicial) {
            camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(),
                    grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
            nodoFinal = vecPredecesores[nodoFinal];
        }

        return camino;
    }

    /**
     * Pregunta si los personajes estan en diagonal
     *
     * @param nodoUno
     *            personaje 1
     * @param nodoDos
     *            personaje 2
     * @return true or false
     */
    private boolean estanEnDiagonal(final Nodo nodoUno, final Nodo nodoDos) {

        return !(nodoUno.obtenerX() == nodoDos.obtenerX() || nodoUno.obtenerY() == nodoDos.obtenerY());

    }

    /**
     * Pide el valor de X
     *
     * @return devuelve la ubicacion en X
     */
    public float getX() {
        return x;
    }

    /**
     * Setea el valor de X
     *
     * @param x
     *            valor nuevo de la ubicacion en X
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Pide el valor de Y
     *
     * @return devuelve la ubicacion en Y
     */
    public float getY() {
        return y;
    }

    /**
     * Setea el valor de Y
     *
     * @param y
     *            valor nuevo de la ubicacion en Y
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * Pide el ancho
     *
     * @return devuelve el ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Setea el ancho
     *
     * @param ancho
     *            nuevo ancho a setear
     */
    public void setAncho(final int ancho) {
        this.ancho = ancho;
    }

    /**
     * Pide el alto
     *
     * @return devuelve el alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Setea el alto
     *
     * @param alto
     *            nuevo alto a setear
     */
    public void setAlto(final int alto) {
        this.alto = alto;
    }

    /**
     * Pide el offset de X
     *
     * @return devuelve el offset de X
     */
    public int getxOffset() {
        return xOffset;
    }

    /**
     * Pide el offset de Y
     *
     * @return devuelve el offset de Y
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * @return the juego
     */
    Juego getJuego() {
        return juego;
    }

    /**
     * @param juego
     *            the juego to set
     */
    void setJuego(final Juego juego) {
        this.juego = juego;
    }
}
