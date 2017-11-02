package comandos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dominio.Casta;
import dominio.Item;
import dominio.Personaje;
import mensajeria.Comando;
import mensajeria.PaqueteComerciar;
import mensajeria.PaquetePersonaje;

/**
 * Trueque entre personajes
 *
 */
public class Trueque extends ComandosEscucha {

    @Override
    public void ejecutar() {
        PaqueteComerciar paqueteComerciar;
        PaquetePersonaje paquetePersonaje;
        paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
        Personaje pj = null;

        getJuego().getCliente().getPaquetePersonaje().removerBonus();

        final String nombre = getJuego().getCliente().getPaquetePersonaje().getNombre();
        final int salud = getJuego().getCliente().getPaquetePersonaje().getSaludTope();
        final int energia = getJuego().getCliente().getPaquetePersonaje().getEnergiaTope();
        final int fuerza = getJuego().getCliente().getPaquetePersonaje().getFuerza();
        final int destreza = getJuego().getCliente().getPaquetePersonaje().getDestreza();
        final int inteligencia = getJuego().getCliente().getPaquetePersonaje().getInteligencia();
        final int experiencia = getJuego().getCliente().getPaquetePersonaje().getExperiencia();
        final int nivel = getJuego().getCliente().getPaquetePersonaje().getNivel();
        final int id = getJuego().getCliente().getPaquetePersonaje().getId();

        Casta casta = null;

        try {
            casta = (Casta) Class.forName("dominio" + "." + getJuego().getCliente().getPaquetePersonaje().getCasta())
                    .newInstance();
            pj = (Personaje) Class.forName("dominio" + "." + getJuego().getCliente().getPaquetePersonaje().getRaza())
                    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
                            Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)
                    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la batalla");
        }
        // Si soy yo mismo, tengo que cambiar los items a darme, y despues trueque
        if (id == paqueteComerciar.getId()) {
            paqueteComerciar.getItemsADar().removeAll(paqueteComerciar.getItemsADar());
            final ArrayList<Item> items = getJuego().getPersonajesConectados().get(paqueteComerciar.getIdEnemigo())
                    .getItems();
            boolean loop = true;
            int i = 0;
            while (getJuego().getCliente().getM1().getObtener().size() > 0) {
                while (loop) {
                    if (items.get(i).getNombre().equals(getJuego().getCliente().getM1().getObtener().get(0))) {
                        paqueteComerciar.getItemsADar().add(items.get(i));
                        getJuego().getCliente().getM1().getObtener().remove(0);
                        loop = false;
                    }
                    i++;
                }
                loop = true;
                i = 0;
            }
            pj.trueque(getJuego().getCliente().getPaquetePersonaje().getItems(), paqueteComerciar.getItemsADar(),
                    getJuego().getCliente().getM1().getDar());
        } else {
            // sino soy yo esta todo ok y trueque
            pj.trueque(getJuego().getCliente().getPaquetePersonaje().getItems(), paqueteComerciar.getItemsADar(),
                    getJuego().getCliente().getM1().getDar());
        }

        getJuego().getCliente().getPaquetePersonaje().actualizarTrueque(pj.getItems());
        paquetePersonaje = getJuego().getCliente().getPaquetePersonaje();
        paquetePersonaje.setComando(Comando.ACTUALIZARTRUEQUE);
        getJuego().getCliente().setM1(null);

        try {
            getJuego().getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar trueque");

        }

    }

}
