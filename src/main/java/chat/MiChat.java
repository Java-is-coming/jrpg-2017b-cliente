package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.google.gson.Gson;

import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;

/**
 * Clase para ventana de chat
 */
public class MiChat extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JTextField texto;
    private final JTextArea chat;
    private final Gson gson = new Gson();
    private final JLabel background = new JLabel(new ImageIcon("recursos//background.jpg"));
    private final DefaultCaret caret;

    /**
     * Construye el chat
     *
     * @param juego
     *            juego
     */
    public MiChat(final Juego juego) {
        setTitle("Mi Chat");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final int x = 100, y = 100, width = 450, height = 300, border = 5;

        setBounds(x, y, width, height);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        final int x1 = 10, y1 = 11, width1 = 414, height1 = 201;
        scrollPane.setBounds(x1, y1, width1, height1);
        contentPane.add(scrollPane);

        chat = new JTextArea();
        chat.setEditable(false);
        scrollPane.setViewportView(chat);
        caret = (DefaultCaret) chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        texto = new JTextField();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(final WindowEvent e) {
                texto.requestFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                if (getTitle() == "Sala") {
                    if (Pantalla.getVentContac() != null) {
                        VentanaContactos.getBotonMc().setEnabled(true);
                    }
                }
                juego.getChatsActivos().remove(getTitle());
            }
        });

        // SI TOCO ENTER
        texto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!texto.getText().equals("")) {
                    chat.append("Me: " + texto.getText() + "\n");

                    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
                    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
                    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

                    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
                    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
                    // El user receptor en espacio indica que es para todos
                    if (getTitle() == "Sala") {
                        juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
                    }

                    try {
                        juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
                    } catch (final IOException e1) {
                        JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
                    }
                    texto.setText("");
                }
                texto.requestFocus();
            }
        });

        // SI TOCO ENVIAR
        final JButton enviar = new JButton("ENVIAR");
        enviar.setIcon(new ImageIcon("recursos//enviarButton.png"));
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!texto.getText().equals("")) {
                    chat.append("Me: " + texto.getText() + "\n");

                    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
                    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
                    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

                    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
                    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
                    // El user receptor en espacio indica que es para todos
                    if (getTitle() == "Sala") {
                        juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
                    }

                    try {
                        juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
                    } catch (final IOException e1) {
                        JOptionPane.showMessageDialog(null, "Error al enviar mensaje");

                    }
                    texto.setText("");
                }
                texto.requestFocus();
            }
        });

        final int x2 = 334, y2 = 225, width2 = 81, height2 = 23;
        enviar.setBounds(x2, y2, width2, height2);
        contentPane.add(enviar);

        final int x3 = 10, y3 = 223, width3 = 314, height3 = 27;
        texto.setBounds(x3, y3, width3, height3);
        contentPane.add(texto);

        final int columns = 10;
        texto.setColumns(columns);

        final int x4 = -20, y4 = 0, width4 = 480, height4 = 283;
        background.setBounds(x4, y4, width4, height4);
        contentPane.add(background);
    }

    /**
     * Devuelve el textarea de chat
     *
     * @return JTextArea chat
     */
    public JTextArea getChat() {
        return chat;
    }

    /**
     * Devuelve el text field
     *
     * @return JTextField textField
     */
    public JTextField getTexto() {
        return texto;
    }
}
