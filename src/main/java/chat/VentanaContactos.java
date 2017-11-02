package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import juego.Juego;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;

/**
 * Clase para mostrar la ventana contactos
 *
 */
public class VentanaContactos extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final DefaultListModel<String> modelo = new DefaultListModel<String>();
    private static JList<String> list = new JList<String>();
    private static JButton botonMc;
    private final JLabel background;

    /**
     * Create the frame.
     *
     * @param juego
     *            juego
     */
    public VentanaContactos(final Juego juego) {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final int x = 100, y = 100, width = 327, height = 273, border = 5;
        setBounds(x, y, width, height);

        setLocationRelativeTo(null);
        setTitle("Usuarios");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JScrollPane scrollPane = new JScrollPane();
        final int x1 = 10, y1 = 11, width1 = 299, height1 = 188;
        scrollPane.setBounds(x1, y1, width1, height1);
        contentPane.add(scrollPane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent arg0) {
                Pantalla.setVentContac(null);
                dispose();
            }
        });

        botonMc = new JButton("Multichat");
        botonMc.setIcon(new ImageIcon("recursos//multichatButton.png"));
        botonMc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (modelo.size() != 0) {
                    if (!juego.getChatsActivos().containsKey("Sala")) {
                        final MiChat chat = new MiChat(juego);
                        juego.getChatsActivos().put("Sala", chat);
                        chat.setTitle("Sala");
                        chat.setVisible(true);
                        botonMc.setEnabled(false);
                    }
                }
            }
        });
        final int x2 = 119, y2 = 208, width2 = 89, height2 = 23;
        botonMc.setBounds(x2, y2, width2, height2);
        contentPane.add(botonMc);

        // Cargo la lista de contactos
        actualizarLista(juego);
        // Pregunto si la ventana sala esta abierta y cancelo el boton multichat
        if (juego.getChatsActivos().containsKey("Sala")) {
            botonMc.setEnabled(false);
        } else {
            botonMc.setEnabled(true);
        }

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                if (arg0.getClickCount() == 2) {
                    if (list.getSelectedValue() != null) {
                        if (!juego.getChatsActivos().containsKey(list.getSelectedValue())) {
                            if (juego.getCliente() != null) {
                                final MiChat chat = new MiChat(juego);
                                juego.getChatsActivos().put(list.getSelectedValue(), chat);
                                chat.setTitle(list.getSelectedValue());
                                chat.setVisible(true);
                            }
                        }
                    }
                }
            }
        });

        list.setModel(modelo);
        scrollPane.setViewportView(list);

        background = new JLabel(new ImageIcon("recursos//background.jpg"));
        final int x3 = -16, y3 = 0, width3 = 352, height3 = 254;
        background.setBounds(x3, y3, width3, height3);
        contentPane.add(background);
    }

    /**
     * Actualiza lista de contactos
     *
     * @param juego
     *            juego
     */
    private void actualizarLista(final Juego juego) {
        if (juego.getCliente() != null) {
            synchronized (juego.getCliente()) {
                modelo.removeAllElements();
                if (juego.getPersonajesConectados() != null) {
                    for (final Map.Entry<Integer, PaquetePersonaje> personaje : juego.getPersonajesConectados()
                            .entrySet()) {
                        modelo.addElement(personaje.getValue().getNombre());
                    }
                    modelo.removeElement(juego.getPersonaje().getNombre());
                    list.setModel(modelo);
                }
            }
        }
    }

    /**
     * Devuelve la lista
     *
     * @return JList<String> lista
     */
    public static JList<String> getList() {
        return list;
    }

    /**
     * Devuelve el boton
     *
     * @return JButton boton
     */
    public static JButton getBotonMc() {
        return botonMc;
    }
}
