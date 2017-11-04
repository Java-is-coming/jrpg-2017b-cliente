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

/**
 * Menu de comercio
 */
public class MenuComerciar extends JFrame {

    private static final long serialVersionUID = 1L;
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
     * Construye el menu
     *
     * @param cliente
     *            instancia de cliente
     */
    public MenuComerciar(final Cliente cliente) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        final int bounds = 100;
        final int widthVentana = 610;
        final int heightVentana = 363;
        this.setBounds(bounds, bounds, widthVentana, heightVentana);
        this.setLocationRelativeTo(null);
        this.setTitle("Comercio");

        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
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
            public void actionPerformed(final ActionEvent e) {
                cliente.setM1(null);
                dispose();
            }
        });
        final int xBtnCancel = 276;
        final int yBtnCancel = 245;
        final int widthBtnCancel = 97;
        final int heightBtnCancel = 25;
        btnCancelar.setBounds(xBtnCancel, yBtnCancel, widthBtnCancel, heightBtnCancel);
        contentPane.add(btnCancelar);

        final JList<String> listMisItems = new JList<String>();
        final int xListItems = 12;
        final int yListItems = 42;
        final int widthListItems = 157;

        final int heightListItems = 162;
        listMisItems.setBounds(xListItems, yListItems, widthListItems, heightListItems);
        contentPane.add(listMisItems);

        final JList<String> listADar = new JList<String>();
        final int xListAdar = 244;
        listADar.setBounds(xListAdar, yListItems, widthListItems, heightListItems);
        contentPane.add(listADar);

        final JList<String> listAObtener = new JList<String>();
        final int xListAObtener = 428;
        listAObtener.setBounds(xListAObtener, yListItems, widthListItems, heightListItems);
        contentPane.add(listAObtener);

        final JLabel lblMisItems = new JLabel("Mis Items");
        lblMisItems.setForeground(Color.WHITE);
        lblMisItems.setHorizontalAlignment(SwingConstants.CENTER);
        final int yMisItems = 13;
        final int heightMisItems = 16;
        lblMisItems.setBounds(xListItems, yMisItems, widthListItems, heightMisItems);
        contentPane.add(lblMisItems);

        final JLabel lblItemsAIntercambiar = new JLabel("Items a Dar");
        lblItemsAIntercambiar.setForeground(Color.WHITE);
        lblItemsAIntercambiar.setHorizontalAlignment(SwingConstants.CENTER);
        final int yLblItemsADar = 13;
        lblItemsAIntercambiar.setBounds(xListAdar, yLblItemsADar, widthListItems, heightMisItems);
        contentPane.add(lblItemsAIntercambiar);

        final JLabel lblItemsAObtener = new JLabel("Items a Obtener");
        lblItemsAObtener.setForeground(Color.WHITE);
        lblItemsAObtener.setHorizontalAlignment(SwingConstants.CENTER);
        final int xLblItemsAObtener = 428;
        final int yLblItemsAObtener = 13;
        lblItemsAObtener.setBounds(xLblItemsAObtener, yLblItemsAObtener, widthListItems, heightMisItems);
        contentPane.add(lblItemsAObtener);

        final JLabel lblSalud = new JLabel("Salud");
        lblSalud.setForeground(Color.WHITE);
        final int yLblSalud = 217;
        final int widthLblSalud = 56;
        lblSalud.setBounds(xListItems, yLblSalud, widthLblSalud, heightMisItems);
        contentPane.add(lblSalud);

        final JLabel lblEnerga = new JLabel("Energía");
        lblEnerga.setForeground(Color.WHITE);
        final int yLblEnergia = 240;
        final int widthLblEnergia = 56;
        lblEnerga.setBounds(xListItems, yLblEnergia, widthLblEnergia, heightMisItems);
        contentPane.add(lblEnerga);

        final JLabel lblFuerza = new JLabel("Fuerza");
        lblFuerza.setForeground(Color.WHITE);
        final int xLblFuerza = 113;
        final int yLblFuerza = 217;
        final int widthLblFuerza = 56;
        lblFuerza.setBounds(xLblFuerza, yLblFuerza, widthLblFuerza, heightMisItems);
        contentPane.add(lblFuerza);

        final JLabel lblDestreza = new JLabel("Destreza");
        lblDestreza.setForeground(Color.WHITE);
        final int xLblDestreza = 113;
        final int yLblDestreza = 240;
        final int widthLblDestreza = 56;
        lblDestreza.setBounds(xLblDestreza, yLblDestreza, widthLblDestreza, heightMisItems);
        contentPane.add(lblDestreza);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        lblInteligencia.setForeground(Color.WHITE);
        final int yLblInteligencia = 263;
        final int widthLblInteligencia = 71;
        lblInteligencia.setBounds(xListItems, yLblInteligencia, widthLblInteligencia, heightMisItems);
        contentPane.add(lblInteligencia);

        final JLabel lblSaludEnemy = new JLabel("Salud");
        lblSaludEnemy.setForeground(Color.WHITE);
        final int xLblSaludEnemy = 387;
        final int yLblSaludEnemy = 217;
        final int widthLblSaludEnemy = 56;
        lblSaludEnemy.setBounds(xLblSaludEnemy, yLblSaludEnemy, widthLblSaludEnemy, heightMisItems);
        contentPane.add(lblSaludEnemy);

        final JLabel lblEnergiaEnemy = new JLabel("Energía");
        lblEnergiaEnemy.setForeground(Color.WHITE);
        final int xLblEnergiaEnemy = 387;
        final int yLblEnergiaEnemy = 240;
        final int widthLblEnergiaEnemy = 56;
        lblEnergiaEnemy.setBounds(xLblEnergiaEnemy, yLblEnergiaEnemy, widthLblEnergiaEnemy, heightMisItems);
        contentPane.add(lblEnergiaEnemy);

        final JLabel lblFzaEnemy = new JLabel("Fuerza");
        lblFzaEnemy.setForeground(Color.WHITE);
        final int xLblFzaEnemy = 497;
        final int yLblFzaEnemy = 217;
        final int widthLblFzaEnemy = 56;
        lblFzaEnemy.setBounds(xLblFzaEnemy, yLblFzaEnemy, widthLblFzaEnemy, heightMisItems);
        contentPane.add(lblFzaEnemy);

        final JLabel lblDesEnemy = new JLabel("Destreza");
        lblDesEnemy.setForeground(Color.WHITE);
        final int xLblDesEnemy = 497;
        final int yLblDesEnemy = 240;
        final int widthLblDesEnemy = 56;
        lblDesEnemy.setBounds(xLblDesEnemy, yLblDesEnemy, widthLblDesEnemy, heightMisItems);
        contentPane.add(lblDesEnemy);

        final JLabel lblIntEnemy = new JLabel("Inteligencia");
        lblIntEnemy.setForeground(Color.WHITE);
        final int xLblIntEnemy = 387;
        final int yLblIntEnemy = 263;
        final int widthLblIntEnemy = 71;
        lblIntEnemy.setBounds(xLblIntEnemy, yLblIntEnemy, widthLblIntEnemy, heightMisItems);
        contentPane.add(lblIntEnemy);

        final JLabel lblListo = new JLabel("Listo");
        lblListo.setForeground(Color.WHITE);
        final int yLblListo = 279;
        final int widthLblListo = 56;
        lblListo.setBounds(xBtnCancel, yLblListo, widthLblListo, heightMisItems);
        contentPane.add(lblListo);

        final JLabel bonusSalud = new JLabel("");
        bonusSalud.setForeground(Color.WHITE);
        bonusSalud.setHorizontalAlignment(SwingConstants.RIGHT);
        final int xBonusSalud = 51;
        final int yBonusSalud = 217;
        final int widthBonusSalud = 56;
        bonusSalud.setBounds(xBonusSalud, yBonusSalud, widthBonusSalud, heightMisItems);
        contentPane.add(bonusSalud);

        final JLabel bonusEnergia = new JLabel("");
        bonusEnergia.setForeground(Color.WHITE);
        bonusEnergia.setHorizontalAlignment(SwingConstants.RIGHT);
        final int xBonusEne = 51;
        final int yBonusEne = 240;
        final int widthBonusEne = 56;
        bonusEnergia.setBounds(xBonusEne, yBonusEne, widthBonusEne, heightMisItems);
        contentPane.add(bonusEnergia);

        final JLabel bonusFuerza = new JLabel("");
        bonusFuerza.setForeground(Color.WHITE);
        bonusFuerza.setHorizontalAlignment(SwingConstants.RIGHT);
        final int xBonusFza = 176;
        final int yBonusFza = 217;
        final int widthBonusFza = 56;
        bonusFuerza.setBounds(xBonusFza, yBonusFza, widthBonusFza, heightMisItems);
        contentPane.add(bonusFuerza);

        final JLabel bonusDes = new JLabel("");
        bonusDes.setForeground(Color.WHITE);
        bonusDes.setHorizontalAlignment(SwingConstants.RIGHT);
        final int xBonusDes = 176;
        final int yBonusDes = 240;
        final int widthBonusDes = 56;
        bonusDes.setBounds(xBonusDes, yBonusDes, widthBonusDes, heightMisItems);
        contentPane.add(bonusDes);

        final JLabel bonusInt = new JLabel("");
        bonusInt.setForeground(Color.WHITE);
        bonusInt.setHorizontalAlignment(SwingConstants.RIGHT);
        final int xBonusInt = 51;
        final int yBonusInt = 263;
        final int widthBonusInt = 56;
        bonusInt.setBounds(xBonusInt, yBonusInt, widthBonusInt, heightMisItems);
        contentPane.add(bonusInt);

        final JLabel saludEnemy = new JLabel("");
        saludEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        saludEnemy.setForeground(Color.WHITE);
        final int xSaludEnemy = 428;
        final int ySaludEnemy = 217;
        final int widthSaludEnemy = 56;
        saludEnemy.setBounds(xSaludEnemy, ySaludEnemy, widthSaludEnemy, heightMisItems);
        contentPane.add(saludEnemy);

        final JLabel energyEnemy = new JLabel("");
        energyEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        energyEnemy.setForeground(Color.WHITE);
        final int xEnergyEnemy = 428;
        final int yEnergyEnemy = 240;
        final int widthEnergyEnemy = 56;
        energyEnemy.setBounds(xEnergyEnemy, yEnergyEnemy, widthEnergyEnemy, heightMisItems);
        contentPane.add(energyEnemy);

        final JLabel fzaEnemy = new JLabel("");
        fzaEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        fzaEnemy.setForeground(Color.WHITE);
        final int xFzaEnemy = 536;
        final int yFzaEnemy = 217;
        final int widthFzaEnemy = 56;
        fzaEnemy.setBounds(xFzaEnemy, yFzaEnemy, widthFzaEnemy, heightMisItems);
        contentPane.add(fzaEnemy);

        final JLabel desEnemy = new JLabel("");
        desEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        desEnemy.setForeground(Color.WHITE);
        final int xDesEnemy = 536;
        final int yDesEnemy = 240;
        final int widthDesEnemy = 56;
        desEnemy.setBounds(xDesEnemy, yDesEnemy, widthDesEnemy, heightMisItems);
        contentPane.add(desEnemy);

        final JLabel intEnemy = new JLabel("");
        intEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
        intEnemy.setForeground(Color.WHITE);
        final int xIntEnemy = 428;
        final int yIntEnemy = 263;
        final int widthIntEnemy = 56;
        intEnemy.setBounds(xIntEnemy, yIntEnemy, widthIntEnemy, heightMisItems);
        contentPane.add(intEnemy);

        chckbxListo = new JCheckBox("Listo");
        chckbxListo.setForeground(Color.WHITE);
        chckbxListo.setBackground(Color.BLACK);
        // Arranca deshabilitada
        chckbxListo.setEnabled(false);

        leyenda = new JLabel("Recuerda que la máxima cantidad de items es 9");
        leyenda.setForeground(Color.WHITE);
        final int yMaxItems = 299;
        final int widthMaxItems = 282;
        leyenda.setBounds(xListItems, yMaxItems, widthMaxItems, heightMisItems);
        contentPane.add(leyenda);
        leyenda.setVisible(false);

        final JButton btnAgregar = new JButton("-->");
        btnAgregar.setIcon(new ImageIcon("recursos//flechaDer.png"));
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                if (listMisItems.getSelectedValue() != null) {
                    dar.addElement(listMisItems.getSelectedValue());
                    if (obtener.size() != 0) {
                        final int maxItems = 9;
                        if (sizeItems - dar.size() + obtener.size() <= maxItems) {
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
        final int xBtnAdd = 181;
        final int yBtnAdd = 93;
        final int widthBtnAdd = 51;
        final int heightBtnAdd = 25;
        btnAgregar.setBounds(xBtnAdd, yBtnAdd, widthBtnAdd, heightBtnAdd);
        contentPane.add(btnAgregar);

        final JButton btnSacar = new JButton("<--");
        btnSacar.setIcon(new ImageIcon("recursos//flechaIzq.png"));
        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
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
                    final int maxItems = 9;
                    if (sizeItems - dar.size() + obtener.size() > maxItems) {
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
        final int xBtnSacar = 181;
        final int yBtnSacar = 131;
        final int widthBtnSacar = 51;
        final int heightBtnSacar = 25;
        btnSacar.setBounds(xBtnSacar, yBtnSacar, widthBtnSacar, heightBtnSacar);
        contentPane.add(btnSacar);

        // List Listener para cargar stats del item mio clickeado
        listMisItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
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
            public void mouseClicked(final MouseEvent arg0) {
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
        final int xCantListo = 317;
        final int yCantListo = 278;
        final int widthCantListo = 56;
        cantListo.setBounds(xCantListo, yCantListo, widthCantListo, heightMisItems);
        contentPane.add(cantListo);

        chckbxListo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent arg0) {
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
        final int xChkListo = 289;
        final int yChkListo = 213;
        final int widthChkListo = 71;
        final int heightChkListo = 25;
        chckbxListo.setBounds(xChkListo, yChkListo, widthChkListo, heightChkListo);
        contentPane.add(chckbxListo);

        final int scaleX = 610;
        final int scaleY = 416;
        final JLabel background = new JLabel(
                new ImageIcon(imagenFondo.getScaledInstance(scaleX, scaleY, Image.SCALE_DEFAULT)));
        final int widthBkg = 628;
        final int heightBkg = 336;
        background.setBounds(-xListItems, 0, widthBkg, heightBkg);
        contentPane.add(background);
    }

    /**
     * Getter cant listos
     *
     * @return cantListos int
     */
    public int getCantListos() {
        return cantListos;
    }

    /**
     * Set de cant listos
     *
     * @param cantListos
     *            int
     */
    public void setCantListos(final int cantListos) {
        this.cantListos = cantListos;
    }

    /**
     * Get cant listo
     *
     * @return cant listo int
     */
    public JLabel getCantListo() {
        return cantListo;
    }

    /**
     * Set obtener
     *
     * @param obtener
     *            items a obtener
     */
    public void setObtener(final DefaultListModel<String> obtener) {
        this.obtener = obtener;
    }

    /**
     * Get obtener
     *
     * @return obtener items
     */
    public DefaultListModel<String> getObtener() {
        return obtener;
    }

    /**
     * Get Dar
     *
     * @return dar model
     */
    public DefaultListModel<String> getDar() {
        return dar;
    }

    /**
     * Items size
     *
     * @return size items
     */
    public int getSizeItems() {
        return sizeItems;
    }

    /**
     * Get de checkbox listo
     *
     * @return checkbox listo
     */
    public JCheckBox getChckbxListo() {
        return chckbxListo;
    }

    /**
     * Get leyenda
     *
     * @return leyenda label
     */
    public JLabel getLeyenda() {
        return leyenda;
    }
}
