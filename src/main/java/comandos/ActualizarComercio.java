package comandos;

import javax.swing.JOptionPane;

import dominio.Item;
import mensajeria.PaqueteComerciar;

/**
 * Comando para actualizar el comercio
 *
 */
public class ActualizarComercio extends ComandosEscucha {

    @Override
    public void ejecutar() {
        final int sizeMisItems = getJuego().getCliente().getM1().getSizeItems();
        final int sizeADar = getJuego().getCliente().getM1().getDar().size();
        final int maxSize = 9;
        int sizeAObtener;
        int cuentaSize;
        PaqueteComerciar paqueteComerciar;
        paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
        sizeAObtener = paqueteComerciar.getItemsADar().size();
        cuentaSize = sizeMisItems - sizeADar + sizeAObtener;
        if (sizeADar != 0) {
            if (cuentaSize <= maxSize) {
                getJuego().getCliente().getM1().getChckbxListo().setEnabled(true);
                getJuego().getCliente().getM1().getLeyenda().setVisible(false);
            } else if (cuentaSize > maxSize) {
                getJuego().getCliente().getM1().getChckbxListo().setEnabled(false);
                getJuego().getCliente().getM1().getLeyenda().setVisible(true);
            }
        }
        if (sizeAObtener == 0) {
            getJuego().getCliente().getM1().getChckbxListo().setEnabled(false);
            getJuego().getCliente().getM1().getLeyenda().setVisible(true);
        }
        if (getJuego().getCliente().getPaqueteComercio().getListo() == paqueteComerciar.getListo()) {
            // actualizar la lista
            getJuego().getCliente().getM1().getObtener().removeAllElements();
            for (final Item item : paqueteComerciar.getItemsADar()) {
                getJuego().getCliente().getM1().getObtener().addElement(item.getNombre());
            }
            getJuego().getCliente().getPaqueteComercio().setItemsAObtener(paqueteComerciar.getItemsADar());
        } else {
            // se modifico el listo
            // me fijo si puso listo o lo saco
            if (getJuego().getCliente().getPaqueteComercio().getListo() < paqueteComerciar.getListo()) {
                getJuego().getCliente().getPaqueteComercio().aumentarListo();
            } else {
                getJuego().getCliente().getPaqueteComercio().disminuirListo();
            }
            // modifico la cant de listos en el jframe y tambien el lbl
            getJuego().getCliente().getM1().setCantListos(paqueteComerciar.getListo());
            getJuego().getCliente().getM1().getCantListo()
                    .setText(String.valueOf(getJuego().getCliente().getM1().getCantListos()) + "/2");
            if (getJuego().getCliente().getM1().getCantListos() == 2) {
                JOptionPane.showMessageDialog(getJuego().getCliente().getM1(), "Se ha realizado con exito el comercio");
                getJuego().getCliente().getM1().dispose();
            }
        }
    }

}
