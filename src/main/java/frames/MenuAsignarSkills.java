package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaquetePersonaje;

/**
 * Menu para asignar skills al subir de nivel
 */
public class MenuAsignarSkills extends JFrame {

    private static final int MAX_PUNTOS = 200;
    private static final int PUNTOS_NIVEL = 3;
    private static final int PUNTOS_INICIALES = 10;
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private int puntosAsignarInicial = PUNTOS_INICIALES;
    private int puntosFuerzaInicial = 0;
    private int puntosDestrezaInicial = 0;
    private int puntosInteligenciaInicial = 0;
    private int puntosAsignar = puntosAsignarInicial;
    private int puntosFuerza = puntosFuerzaInicial;
    private int puntosDestreza = puntosDestrezaInicial;
    private int puntosInteligencia = puntosInteligenciaInicial;
    private final Gson gson = new Gson();

    private int puntosFuerzaBase = 0;
    private int puntosDestrezaBase = 0;
    private int puntosIntBase = 0;

    static final int[] FUERZA_BASE = {15, 10, 10};
    static final int[] DESTREZA_BASE = {10, 10, 15};
    static final int[] INTELIGENCIA_BASE = {10, 15, 10};

    static final String[] CASTA = {"Guerrero", "Hechicero", "Asesino"};

    private boolean reAsigno = false;

    /**
     * Constructor del menu
     *
     * @param cliente
     *            instancia de cliente
     */
    public MenuAsignarSkills(final Cliente cliente) {

        for (int i = 0; i < CASTA.length && puntosFuerzaBase == 0; i++) {
            if (CASTA[i].trim().equals(cliente.getPaquetePersonaje().getCasta().trim())) {
                puntosFuerzaBase = FUERZA_BASE[i];
                puntosDestrezaBase = DESTREZA_BASE[i];
                puntosIntBase = INTELIGENCIA_BASE[i];
            }
        }

        if (cliente.getPaquetePersonaje().getNivel() > 1) {

            final PaquetePersonaje personajeAux = (PaquetePersonaje) cliente.getPaquetePersonaje().clone();
            personajeAux.removerBonus();

            final int puntosTotales = personajeAux.getInteligencia() + personajeAux.getFuerza()
                    + personajeAux.getDestreza();
            final int puntosRestantes = ((puntosFuerzaBase + puntosDestrezaBase + puntosIntBase)
                    + (cliente.getPaquetePersonaje().getNivel() * PUNTOS_NIVEL)) - puntosTotales;

            puntosAsignarInicial = puntosRestantes;

            if (puntosRestantes == 0) {
                dispose();
            }

        } else {
            puntosAsignarInicial = PUNTOS_NIVEL;
        }

        puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
        puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
        puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();

        puntosAsignar = puntosAsignarInicial;
        puntosFuerza = puntosFuerzaInicial;
        puntosDestreza = puntosDestrezaInicial;
        puntosInteligencia = puntosInteligenciaInicial;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int bounds = 100;
        final int widthMenu = 450;
        final int heightMenu = 300;
        setBounds(bounds, bounds, widthMenu, heightMenu);
        contentPane = new JPanel();
        final int border = 5;
        contentPane.setBorder(new EmptyBorder(border, border, border, border));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
        setTitle("Asignar");
        final int widthAsignar = 298;
        final int heightAsignar = 294;
        setBounds(bounds, bounds, widthAsignar, heightAsignar);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent arg0) {
                Pantalla.setMenuAsignar(null);
                dispose();
            }
        });

        final int xLabels = 50;
        final int widthLabels = 56;
        final int heightLabels = 16;

        final JLabel labelFuerza = new JLabel("");
        labelFuerza.setForeground(Color.WHITE);
        labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
        final int yFza = 101;
        labelFuerza.setBounds(xLabels, yFza, widthLabels, heightLabels);
        labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
        contentPane.add(labelFuerza);

        final JLabel labelDestreza = new JLabel("");
        labelDestreza.setForeground(Color.WHITE);
        labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
        final int yDestreza = 159;
        labelDestreza.setBounds(xLabels, yDestreza, widthLabels, heightLabels);
        labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
        contentPane.add(labelDestreza);

        final JLabel labelInteligencia = new JLabel("");
        labelInteligencia.setForeground(Color.WHITE);
        labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
        final int yInt = 217;
        labelInteligencia.setBounds(xLabels, yInt, widthLabels, heightLabels);
        labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
        contentPane.add(labelInteligencia);

        final JLabel labelPuntos = new JLabel("");
        labelPuntos.setForeground(Color.WHITE);
        labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
        final int xLblPts = 39;
        final int yLblPts = 41;
        final int widthLblPts = 83;
        final int heightLblPts = 26;
        labelPuntos.setBounds(xLblPts, yLblPts, widthLblPts, heightLblPts);
        labelPuntos.setText(String.valueOf(puntosAsignarInicial));
        contentPane.add(labelPuntos);

        final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
        lblCantidadDePuntos.setForeground(Color.WHITE);
        final int xCoord = 12;
        final int xCantPts = xCoord;
        final int yCantPts = 13;
        final int widthCantPts = 177;
        final int heightCantPts = 29;
        lblCantidadDePuntos.setBounds(xCantPts, yCantPts, widthCantPts, heightCantPts);
        contentPane.add(lblCantidadDePuntos);

        final JLabel lblInteligencia = new JLabel("Inteligencia");
        lblInteligencia.setForeground(Color.WHITE);
        lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
        final int xLblInt = 39;
        final int yLblInt = 188;
        final int widthLblInt = 83;
        lblInteligencia.setBounds(xLblInt, yLblInt, widthLblInt, heightLabels);
        contentPane.add(lblInteligencia);

        final JLabel lblDestreza = new JLabel("Destreza");
        lblDestreza.setForeground(Color.WHITE);
        lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
        final int yLblDest = 130;
        lblDestreza.setBounds(xLabels, yLblDest, widthLabels, heightLabels);
        contentPane.add(lblDestreza);

        final JLabel lblFuerza = new JLabel("Fuerza");
        lblFuerza.setForeground(Color.WHITE);
        lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
        final int yLblFz = 72;
        lblFuerza.setBounds(xLabels, yLblFz, widthLabels, heightLabels);
        contentPane.add(lblFuerza);

        final JButton buttonConfirm = new JButton("Confirmar");
        final ImageIcon iconoConfirm = new ImageIcon("recursos//botonConfirmar.png");
        buttonConfirm.setIcon(iconoConfirm);
        buttonConfirm.setEnabled(false);
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                if (reAsigno) {
                    cliente.getPaquetePersonaje().setInteligencia(puntosInteligenciaInicial);
                    cliente.getPaquetePersonaje().setDestreza(puntosDestrezaInicial);
                    cliente.getPaquetePersonaje().setFuerza(puntosFuerzaInicial);
                }

                final int bonusF = puntosFuerza - puntosFuerzaInicial;
                final int bonusD = puntosDestreza - puntosDestrezaInicial;
                final int bonusI = puntosInteligencia - puntosInteligenciaInicial;

                cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
                cliente.getPaquetePersonaje().removerBonus();
                cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
                try {
                    cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar stats");

                }
                JOptionPane.showMessageDialog(null, "Se han actualizado tus atributos.");

                if (puntosAsignar != puntosAsignarInicial) {
                    Pantalla.setMenuAsignar(null);
                }

                dispose();
            }
        });
        final int xBtn = 176;
        final int yBtn = 112;
        final int widthBtn = 97;
        final int heightBtn = 25;

        buttonConfirm.setBounds(xBtn, yBtn, widthBtn, heightBtn);
        contentPane.add(buttonConfirm);

        final JButton buttonCancel = new JButton("Cancelar");
        final ImageIcon iconoCancel = new ImageIcon("recursos//botonCancelar.png");
        buttonCancel.setIcon(iconoCancel);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                Pantalla.setMenuAsignar(null);
                dispose();
            }
        });
        final int xCancel = 176;
        final int yCancel = 146;
        final int widthCancel = 97;
        final int heightCancel = heightBtn;
        buttonCancel.setBounds(xCancel, yCancel, widthCancel, heightCancel);
        contentPane.add(buttonCancel);

        final JButton buttonMinus = new JButton("");
        final JButton buttonMinus1 = new JButton("");
        final JButton buttonMinus2 = new JButton("");
        final JButton buttonMore = new JButton("");
        final JButton buttonMore1 = new JButton("");
        final JButton buttonMore2 = new JButton("");
        buttonMinus.setEnabled(false);
        buttonMinus1.setEnabled(false);
        buttonMinus2.setEnabled(false);

        final ImageIcon iconoMenos = new ImageIcon("recursos//botonMenoss.png");
        buttonMinus.setIcon(iconoMenos);
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosFuerza > puntosFuerzaInicial) {
                    puntosFuerza--;
                    if (puntosAsignar == 0) {
                        if (puntosInteligencia != MAX_PUNTOS) {
                            buttonMore2.setEnabled(true);
                        }
                        if (puntosDestreza != MAX_PUNTOS) {
                            buttonMore1.setEnabled(true);
                        }
                    } else {
                        buttonMore.setEnabled(true);
                        buttonMore1.setEnabled(true);
                        buttonMore2.setEnabled(true);
                    }
                    puntosAsignar++;
                    if (puntosAsignar == puntosAsignarInicial) {
                        buttonConfirm.setEnabled(false);
                    }
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelFuerza.setText(String.valueOf(puntosFuerza));
                    if (puntosFuerza == puntosFuerzaInicial) {
                        buttonMinus.setEnabled(false);
                        buttonMore.setEnabled(true);
                    } else if (puntosFuerza >= puntosFuerzaInicial) {
                        buttonMore.setEnabled(true);
                    }
                }
            }
        });
        final int yCoord = 92;
        final int widthBtns = 34;
        buttonMinus.setBounds(xCoord, yCoord, widthBtns, heightBtn);
        contentPane.add(buttonMinus);

        buttonMinus1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosDestreza > puntosDestrezaInicial) {
                    puntosDestreza--;
                    if (puntosAsignar == 0) {
                        if (puntosInteligencia != MAX_PUNTOS) {
                            buttonMore2.setEnabled(true);
                        }
                        if (puntosFuerza != MAX_PUNTOS) {
                            buttonMore.setEnabled(true);
                        }
                    } else {
                        buttonMore.setEnabled(true);
                        buttonMore1.setEnabled(true);
                        buttonMore2.setEnabled(true);
                    }
                    puntosAsignar++;
                    if (puntosAsignar == puntosAsignarInicial) {
                        buttonConfirm.setEnabled(false);
                    }
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelDestreza.setText(String.valueOf(puntosDestreza));
                    if (puntosDestreza == puntosDestrezaInicial) {
                        buttonMinus1.setEnabled(false);
                        buttonMore1.setEnabled(true);
                    } else if (puntosDestreza >= puntosDestrezaInicial) {
                        buttonMore1.setEnabled(true);
                    }
                }
            }
        });

        buttonMinus1.setIcon(iconoMenos);
        final int yMinus = 159;
        buttonMinus1.setBounds(xCoord, yMinus, widthBtns, heightBtn);
        contentPane.add(buttonMinus1);

        buttonMinus2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosInteligencia > puntosInteligenciaInicial) {
                    puntosInteligencia--;
                    if (puntosAsignar == 0) {
                        if (puntosFuerza != MAX_PUNTOS) {
                            buttonMore.setEnabled(true);
                        }
                        if (puntosDestreza != MAX_PUNTOS) {
                            buttonMore1.setEnabled(true);
                        }
                    } else {
                        buttonMore.setEnabled(true);
                        buttonMore1.setEnabled(true);
                        buttonMore2.setEnabled(true);
                    }
                    puntosAsignar++;
                    if (puntosAsignar == puntosAsignarInicial) {
                        buttonConfirm.setEnabled(false);
                    }
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelInteligencia.setText(String.valueOf(puntosInteligencia));
                    if (puntosInteligencia == puntosInteligenciaInicial) {
                        buttonMinus2.setEnabled(false);
                        buttonMore2.setEnabled(true);
                    } else if (puntosInteligencia >= puntosInteligenciaInicial) {
                        buttonMore2.setEnabled(true);
                    }
                }
            }
        });
        buttonMinus2.setIcon(iconoMenos);
        final int yMinus2 = 217;
        buttonMinus2.setBounds(xCoord, yMinus2, widthBtns, heightBtn);
        contentPane.add(buttonMinus2);

        buttonMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosAsignar != 0 && !labelFuerza.getText().equals(Integer.toString(MAX_PUNTOS))) {
                    puntosFuerza++;
                    puntosAsignar--;
                    buttonConfirm.setEnabled(true);
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelFuerza.setText(String.valueOf(puntosFuerza));
                    buttonMinus.setEnabled(true);
                    if (puntosAsignar == 0) {
                        buttonMore.setEnabled(false);
                        buttonMore1.setEnabled(false);
                        buttonMore2.setEnabled(false);
                    }
                }
                if (puntosAsignar == 0 || labelFuerza.getText().equals(Integer.toString(MAX_PUNTOS))) {
                    buttonMore.setEnabled(false);
                }
            }
        });
        final ImageIcon iconoMas = new ImageIcon("recursos//botonMass.png");
        buttonMore.setIcon(iconoMas);
        final int xMore = 118;
        buttonMore.setBounds(xMore, yCoord, widthBtns, heightBtn);
        contentPane.add(buttonMore);

        buttonMore1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosAsignar != 0 && !labelDestreza.getText().equals(Integer.toString(MAX_PUNTOS))) {
                    puntosDestreza++;
                    puntosAsignar--;
                    buttonConfirm.setEnabled(true);
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelDestreza.setText(String.valueOf(puntosDestreza));
                    buttonMinus1.setEnabled(true);
                    if (puntosAsignar == 0) {
                        buttonMore.setEnabled(false);
                        buttonMore1.setEnabled(false);
                        buttonMore2.setEnabled(false);
                    }
                    if (puntosAsignar == 0 || labelDestreza.getText().equals(Integer.toString(MAX_PUNTOS))) {
                        buttonMore1.setEnabled(false);
                    }
                }
            }
        });
        buttonMore1.setIcon(iconoMas);
        final int yMore = 159;
        buttonMore1.setBounds(xMore, yMore, widthBtns, heightBtn);
        contentPane.add(buttonMore1);

        buttonMore2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (puntosAsignar != 0 && !labelInteligencia.getText().equals(Integer.toString(MAX_PUNTOS))) {
                    puntosInteligencia++;
                    puntosAsignar--;
                    buttonConfirm.setEnabled(true);
                    labelPuntos.setText(String.valueOf(puntosAsignar));
                    labelInteligencia.setText(String.valueOf(puntosInteligencia));
                    buttonMinus2.setEnabled(true);
                    if (puntosAsignar == 0) {
                        buttonMore.setEnabled(false);
                        buttonMore1.setEnabled(false);
                        buttonMore2.setEnabled(false);
                    }
                    if (puntosAsignar == 0 || labelInteligencia.getText().equals(Integer.toString(MAX_PUNTOS))) {
                        buttonMore2.setEnabled(false);
                    }
                }
            }
        });
        buttonMore2.setIcon(iconoMas);
        final int yMore2 = 217;
        buttonMore2.setBounds(xMore, yMore2, widthBtns, heightBtn);
        contentPane.add(buttonMore2);

        final JButton buttonReasignar = new JButton("Reasignar");

        final ImageIcon iconoReasignar = new ImageIcon("recursos//botonReasignar.png");
        buttonReasignar.setIcon(iconoReasignar);
        buttonReasignar.setEnabled(true);

        if (puntosAsignarInicial == 0) {
            buttonReasignar.setEnabled(false);
            buttonMore.setEnabled(false);
            buttonMore1.setEnabled(false);
            buttonMore2.setEnabled(false);
        }

        buttonReasignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final PaquetePersonaje personajeAux = (PaquetePersonaje) cliente.getPaquetePersonaje().clone();

                personajeAux.setFuerza(puntosFuerzaBase);
                personajeAux.setDestreza(puntosDestrezaBase);
                personajeAux.setInteligencia(puntosIntBase);

                personajeAux.ponerBonus();

                puntosAsignarInicial = personajeAux.getNivel() * PUNTOS_NIVEL;
                puntosAsignar = puntosAsignarInicial;

                puntosFuerzaInicial = personajeAux.getFuerza();
                puntosDestrezaInicial = personajeAux.getDestreza();
                puntosInteligenciaInicial = personajeAux.getInteligencia();

                puntosFuerza = puntosFuerzaInicial;
                puntosDestreza = puntosDestrezaInicial;
                puntosInteligencia = puntosInteligenciaInicial;

                labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
                labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
                labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
                labelPuntos.setText(String.valueOf(puntosAsignarInicial));
                buttonReasignar.setEnabled(false);
                reAsigno = true;
            }

        });

        final int xReasignar = 176;
        final int yReasingar = 78;
        final int widthReasingar = 97;
        buttonReasignar.setBounds(xReasignar, yReasingar, widthReasingar, heightBtn);
        contentPane.add(buttonReasignar);

        final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
        final int widthImg = 298;
        final int heightImg = 294;
        imageLabel.setBounds(0, 0, widthImg, heightImg);
        imageLabel.setVisible(true);
        contentPane.add(imageLabel);
    }

}
