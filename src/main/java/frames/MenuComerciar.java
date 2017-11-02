package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import mensajeria.Comando;

public class MenuComerciar extends JFrame {

    private final JPanel contentPane;
    private final DefaultListModel<String> misItems = new DefaultListModel<String>();
    private final DefaultListModel<String> dar = new DefaultListModel<String>();
    private DefaultListModel<String> obtener = new DefaultListModel<String>();
    private int cantListos = 0;
    private final JLabel cantListo;
    private Item item1;
    private int count = 0;
    private final Gson gson = new Gson();
    private final int sizeItems;
    private final JCheckBox chckbxListo;
    private final JLabel leyenda;

    /**
     * Create the frame.
     */
    public MenuComerciar(final Cliente cliente) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(100, 100, 610, 363);
        this.setLocationRelativeTo(null);
        this.setTitle("Comercio");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cliente.setM1(null);
                dispose();
            }
        });

        BufferedImage imagenFondo = null;
        try {
            imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

        }

        final JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setIcon(new ImageIcon("recursos//volver.png"));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.setM1(null);
                dispose();
            }
        });
        btnCancelar.setBounds(276, 245, 97, 25);
        contentPane.add(btnCancelar);

        final JList<String> listMisItems = new JList<String>();
        listMisItems.setBounds(12, 42, 157, 162);
        contentPane.add(listMisItems);

        final JList<String> listADar = new JList<String>();
        listADar.setBounds(244, 42, 157, 162);
        contentPane.add(listADar);

        final JList<String> listAObtener = new JList<String>();
        listAObtener.setBounds(428, 42, 157, 162);
        contentPane.add(listAObtener);

        final JLabel lblMisItems = new JLabel("Mis Items");
        lblMisItems.setForeground(Color.WHITE);
        lblMisItems.setHorizontalAlignment(SwingConstants.CENTER);
        lblMisItems.setBounds(12, 13, 157, 16);
        contentPane.add(lblMisItems);

        final JLabel lblItemsAIntercambiar = new JLabel("Items a Dar");
        lblItemsAIntercambiar.setForeground(Color.WHITE);
        lblItemsAIntercambiar.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemsAIntercambiar.setBounds(244, 13, 157, 16);
        contentPane.add(lblItemsAIntercambiar);

        final JLabel lblItemsAObtener = new JLabel("Items a Obtener");
        lblItemsAObtener.setForeground(Color.WHITE);
        lblItemsAObtener.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemsAObtener.setBounds(428, 13, 157, 16);
        contentPane.add(lblItemsAObtener);

        final JLabel lblSalud = new JLabel("Salud");
        lblSalud.setForeground(Color.WHITE);
        lblSalud.setBounds(12, 217, 56, 16);
        contentPane.add(lblSalud);

        final JLabel lblEnerga = new JLabel("Energía");
        lblEnerga.setForeground(Color.WHITE);
        lblEnerga.setBounds(12, 240, 56, 16);
        contentPane.add(lblEnerga);

        final JLabel lblFuerza = new JLabel("Fuerza");
        lblFuerza.setForeground(Color.WHITE);
        lblFuerza.setBounds(113, 217, 56, 16);
        contentPane.add(lblFuerza);

        final JLabel lblDestreza = new JLabel("Destreza");
        lblDestreza.setForeground(Color.WHITE);
        lblDestreza.setBounds(113, 240, 56, 16);
        contentPane.add(lblDestreza);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        lblInteligencia.setForeground(Color.WHITE);
        lblInteligencia.setBounds(12, 263, 71, 16);
        contentPane.add(lblInteligencia);

        final JLabel lblSaludEnemy = new JLabel("Salud");
        lblSaludEnemy.setForeground(Color.WHITE);
        lblSaludEnemy.setBounds(387, 217, 56, 16);
        contentPane.add(lblSaludEnemy);

        final JLabel lblEnergiaEnemy = new JLabel("Energía");
        lblEnergiaEnemy.setForeground(Color.WHITE);
        lblEnergiaEnemy.setBounds(387, 240, 56, 16);
        contentPane.add(lblEnergiaEnemy);

        final JLabel lblFzaEnemy = new JLabel("Fuerza");
        lblFzaEnemy.setForeground(Color.WHITE);
        lblFzaEnemy.setBounds(497, 217, 56, 16);
        contentPane.add(lblFzaEnemy);

        final JLabel lblDesEnemy = new JLabel("Destreza");
        lblDesEnemy.setForeground(Color.WHITE);
        lblDesEnemy.setBounds(497, 240, 56, 16);
        contentPane.add(lblDesEnemy);

        final JLabel lblIntEnemy = new JLabel("Inteligencia");
        lblIntEnemy.setForeground(Color.WHITE);
        lblIntEnemy.setBounds(387, 263, 71, 16);
        contentPane.add(lblIntEnemy);

        final JLabel lblListo = new JLabel("Listo");
        lblListo.setForeground(Color.WHITE);
        lblListo.setBounds(276, 279, 56, 16);
        contentPane.add(lblListo);

        final JLabel bonusSalud = new JLabel("");
        bonusSalud.setForeground(Color.WHITE);
        bonusSalud.setHorizontalAlignment(SwingConstants.RIGHT);
        bonusSalud.setBounds(51, 217, 56, 16);
        contentPane.add(bonusSalud);

        final JLabel bonusEnergia = new JLabel("");
        bonusEnergia.setForeground(Color.WHITE);
        bonusEnergia.setHorizontalAlignment(SwingConstants.RIGHT);
        bonusEnergia.setBounds(51, 240, 56, 16);
        contentPane.add(bonusEnergia);

        final JLabel bonusFuerza = new JLabel("");
        bonusFuerza.setForeground(Color.WHITE);
        bonusFuerza.setHorizontalAlignment(SwingConstants.RIGHT);
        bonusFuerza.setBounds(176, 217, 56, 16);
        contentPane.add(bonusFuerza);

        final JLabel bonusDes = new JLabel("");
        bonusDes.setForeground(Color.WHITE);
        bonusDes.setHorizontalAlignment(SwingConstants.RIGHT);
        bonusDes.setBounds(176, 240, 56, 16);
        contentPane.add(bonusDes);

        final JLabel bonusInt = new JLabel("");
        bonusInt.setForeground(Color.WHITE);
        bonusInt.setHorizontalAlignment(SwingConstants.RIGHT);
        bonusInt.setBounds(51, 263, 56, 16);
        contentPane.add(bonusInt);

        final JLabel saludEnemy = new JLabel("");
        saludEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        saludEnemy.setForeground(Color.WHITE);
        saludEnemy.setBounds(428, 217, 56, 16);
        contentPane.add(saludEnemy);

        final JLabel energyEnemy = new JLabel("");
        energyEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        energyEnemy.setForeground(Color.WHITE);
        energyEnemy.setBounds(428, 240, 56, 16);
        contentPane.add(energyEnemy);

        final JLabel fzaEnemy = new JLabel("");
        fzaEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        fzaEnemy.setForeground(Color.WHITE);
        fzaEnemy.setBounds(536, 217, 56, 16);
        contentPane.add(fzaEnemy);

        final JLabel desEnemy = new JLabel("");
        desEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        desEnemy.setForeground(Color.WHITE);
        desEnemy.setBounds(536, 240, 56, 16);
        contentPane.add(desEnemy);

        final JLabel intEnemy = new JLabel("");
        intEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        intEnemy.setForeground(Color.WHITE);
        intEnemy.setBounds(428, 263, 56, 16);
        contentPane.add(intEnemy);

        chckbxListo = new JCheckBox("Listo");
        chckbxListo.setForeground(Color.WHITE);
        chckbxListo.setBackground(Color.BLACK);
        // Arranca deshabilitada
        chckbxListo.setEnabled(false);

        leyenda = new JLabel("Recuerda que la máxima cantidad de items es 9");
        leyenda.setForeground(Color.WHITE);
        leyenda.setBounds(12, 299, 282, 16);
        contentPane.add(leyenda);
        leyenda.setVisible(false);

        final JButton btnAgregar = new JButton("-->");
        btnAgregar.setIcon(new ImageIcon("recursos//flechaDer.png"));
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (listMisItems.getSelectedValue() != null) {
                    dar.addElement(listMisItems.getSelectedValue());
                    if (obtener.size() != 0) {
                        if (sizeItems - dar.size() + obtener.size() <= 9) {
                            chckbxListo.setEnabled(true);
                            leyenda.setVisible(false);
                        }
                    }
                    // Pongo el primer item y pregunto si es igual al seleccionado
                    // Entonces mientras que sean distinto lo busca
                    // Cuando sea igual sale del while y lo agrega en la lista
                    item1 = cliente.getPaquetePersonaje().getItems().get(count);
                    while (!item1.getNombre().equals(listMisItems.getSelectedValue())) {
                        count++;
                        item1 = cliente.getPaquetePersonaje().getItems().get(count);
                    }
                    count = 0;
                    cliente.getPaqueteComercio().getItemsADar().add(item1);
                    misItems.removeElement(listMisItems.getSelectedValue());
                    cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
                    try {
                        cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                    }
                    if (misItems.size() == 0) {
                        bonusSalud.setText("");
                        bonusEnergia.setText("");
                        bonusFuerza.setText("");
                        bonusDes.setText("");
                        bonusInt.setText("");
                    }
                }
            }
        });
        btnAgregar.setBounds(181, 93, 51, 25);
        contentPane.add(btnAgregar);

        final JButton btnSacar = new JButton("<--");
        btnSacar.setIcon(new ImageIcon("recursos//flechaIzq.png"));
        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (listADar.getSelectedValue() != null) {
                    misItems.addElement(listADar.getSelectedValue());
                    for (final Item item : cliente.getPaquetePersonaje().getItems()) {
                        if (item.getNombre().equals(listADar.getSelectedValue())) {
                            cliente.getPaqueteComercio().getItemsADar().remove(item);
                        }
                    }
                    dar.removeElement(listADar.getSelectedValue());
                    // Si saque el item y la lista no tiene nada deshabilito el check
                    if (dar.size() == 0) {
                        chckbxListo.setEnabled(false);
                    }
                    // Si los items en total es mayor a 9 no puedo comerciar
                    if (sizeItems - dar.size() + obtener.size() > 9) {
                        chckbxListo.setEnabled(false);
                        leyenda.setVisible(true);
                    }
                    cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
                    try {
                        cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                    }
                    // Cuando paso un item de ofertar a no ofertado muestro el que movi
                    final int i = misItems.size();
                    if (i >= 1) {
                        for (final Item item : cliente.getPaquetePersonaje().getItems()) {
                            if (misItems.getElementAt(i - 1).equals(item.getNombre())) {
                                bonusSalud.setText("+ " + item.getBonusSalud());
                                bonusEnergia.setText("+ " + item.getBonusEnergia());
                                bonusFuerza.setText("+ " + item.getBonusFuerza());
                                bonusDes.setText("+ " + item.getBonusDestreza());
                                bonusInt.setText("+ " + item.getBonusInteligencia());
                            }
                        }
                    }
                }
            }
        });
        btnSacar.setBounds(181, 131, 51, 25);
        contentPane.add(btnSacar);

        // List Listener para cargar stats del item mio clickeado
        listMisItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (arg0.getClickCount() == 1) {
                    if (listMisItems.getSelectedValue() != null) {
                        for (final Item item : cliente.getPaquetePersonaje().getItems()) {
                            if (listMisItems.getSelectedValue().equals(item.getNombre())) {
                                bonusSalud.setText("+ " + item.getBonusSalud());
                                bonusEnergia.setText("+ " + item.getBonusEnergia());
                                bonusFuerza.setText("+ " + item.getBonusFuerza());
                                bonusDes.setText("+ " + item.getBonusDestreza());
                                bonusInt.setText("+ " + item.getBonusInteligencia());
                            }
                        }
                    }
                }
            }
        });

        // List Listener para cargar stats del item del enemigo clickeado
        listAObtener.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (arg0.getClickCount() == 1) {
                    if (obtener.size() != 0) {
                        // cambiar la variable del for each a la lista que va a venir del otro pj
                        for (final Item item : cliente.getPaqueteComercio().getItemsAObtener()) {
                            if (listAObtener.getSelectedValue().equals(item.getNombre())) {
                                saludEnemy.setText("+ " + item.getBonusSalud());
                                energyEnemy.setText("+ " + item.getBonusEnergia());
                                fzaEnemy.setText("+ " + item.getBonusFuerza());
                                desEnemy.setText("+ " + item.getBonusDestreza());
                                intEnemy.setText("+ " + item.getBonusInteligencia());
                            }
                        }
                    }
                }
            }
        });

        // CARGO MIS ITEMS
        for (final Item item : cliente.getPaquetePersonaje().getItems()) {
            misItems.addElement(item.getNombre());
        }

        // Seteo la cantidad de mis items en mi mochila
        sizeItems = misItems.size();

        // Seteo de JList
        listMisItems.setModel(misItems);
        listADar.setModel(dar);
        listAObtener.setModel(obtener);

        cantListo = new JLabel("0/2");
        cantListo.setHorizontalAlignment(SwingConstants.RIGHT);
        cantListo.setForeground(Color.WHITE);
        cantListo.setBounds(317, 278, 56, 16);
        contentPane.add(cantListo);

        chckbxListo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (chckbxListo.isSelected()) {
                    // Si ya la persona con la que voy a comerciar esta en LISTO
                    if (cantListos == 1) {
                        cantListos++;
                        // Primero actualizo el label de cant Listos
                        cantListo.setText(cantListos + "/2");
                        // Le envio al otro que toque listo y esta 2/2 listo para trueque
                        cliente.getPaqueteComercio().aumentarListo();
                        cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
                        try {
                            cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                        } catch (final IOException e) {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                        }
                        ////////
                        // Ahora le digo que haga el trueque
                        cliente.getPaqueteComercio().setComando(Comando.TRUEQUE);
                        // Le informo al otro que vamos a hacer el trueque
                        try {
                            cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                        } catch (final IOException e) {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                        }
                        JOptionPane.showMessageDialog(cliente.getM1(), "Se ha realizado con exito el comercio");
                        dispose();
                    } else {
                        // Si todavía LISTO = 0, le informo al otro
                        cantListos++;
                        // Deshabilito los botones para que no pueda agregar nada
                        btnAgregar.setEnabled(false);
                        btnSacar.setEnabled(false);
                        cliente.getPaqueteComercio().aumentarListo();
                        cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
                        // Tambien le tiene que avisar el LISTO al otro jugador
                        try {
                            cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                        } catch (final IOException e) {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                        }
                        cantListo.setText(cantListos + "/2");
                    }
                } else {
                    // Si habia clickeado LISTO, pero lo desclickie entonces le digo
                    // que disminuya en el otro cliente
                    if (cantListos != 2) {
                        // Si no tenia nada en la lista no tengo que disminuir la cant
                        // de listos
                        cantListos--;
                        cliente.getPaqueteComercio().disminuirListo();
                        btnAgregar.setEnabled(true);
                        btnSacar.setEnabled(true);
                        cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
                        // Tambien le tiene que avisar el NO LISTO al otro jugador
                        try {
                            cliente.getSalida().writeObject(gson.toJson(cliente.getPaqueteComercio()));
                        } catch (final IOException e) {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
                        }
                        cantListo.setText(cantListos + "/2");
                    }
                }
            }
        });
        chckbxListo.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxListo.setBounds(289, 213, 71, 25);
        contentPane.add(chckbxListo);

        final JLabel background = new JLabel(
                new ImageIcon(imagenFondo.getScaledInstance(610, 416, Image.SCALE_DEFAULT)));
        background.setBounds(-12, 0, 628, 336);
        contentPane.add(background);
    }

    public int getCantListos() {
        return cantListos;
    }

    public void setCantListos(int cantListos) {
        this.cantListos = cantListos;
    }

    public JLabel getCantListo() {
        return cantListo;
    }

    public void setObtener(DefaultListModel<String> obtener) {
        this.obtener = obtener;
    }

    public DefaultListModel<String> getObtener() {
        return obtener;
    }

    public DefaultListModel<String> getDar() {
        return dar;
    }

    public int getSizeItems() {
        return sizeItems;
    }

    public JCheckBox getChckbxListo() {
        return chckbxListo;
    }

    public JLabel getLeyenda() {
        return leyenda;
    }
}
