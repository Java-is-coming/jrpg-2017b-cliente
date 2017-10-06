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
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Pantalla;
import mensajeria.Comando;

public class MenuAsignarSkills extends JFrame {

	private final static int MAX = 200;

	private JPanel contentPane;

	private int puntosFuerzaInicial;
	private int puntosDestrezaInicial;
	private int puntosInteligenciaInicial;

	private int puntosFuerzaActuales;
	private int puntosDestrezaActuales;
	private int puntosInteligenciaActuales;

	private int fuerzaBase;
	private int destrezaBase;
	private int inteligenciaBase;

	private int puntosTotales;
	private int puntosAsignados;
	private int puntosSinAsignarIniciales;
	private int puntosSinAsignar;

	private boolean apretoReasignar = false;
	private String casta;

	private final Gson gson = new Gson();

	/**
	 * Create the frame.
	 */
	public MenuAsignarSkills(final Cliente cliente) {
		puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();

		puntosFuerzaActuales = puntosFuerzaInicial;
		puntosDestrezaActuales = puntosDestrezaInicial;
		puntosInteligenciaActuales = puntosInteligenciaInicial;

		fuerzaBase = 10;
		destrezaBase = 10;
		inteligenciaBase = 10;

		String casta = cliente.getPaquetePersonaje().getCasta();
		if (casta.equals("Guerrero"))
			fuerzaBase += 5;
		else if (casta.equals("Asesisno"))
			destrezaBase += 5;
		else if (casta.equals("Hechicero"))
			inteligenciaBase += 5;

		puntosTotales = cliente.getPaquetePersonaje().getNivel() * 3;
		puntosAsignados = puntosFuerzaInicial - fuerzaBase + puntosDestrezaInicial - destrezaBase
				+ puntosInteligenciaInicial - inteligenciaBase;
		puntosSinAsignarIniciales = puntosTotales - puntosAsignados;
		puntosSinAsignar = puntosSinAsignarIniciales;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
		setTitle("Asignar");
		setBounds(100, 100, 298, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});

		final JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(50, 101, 56, 16);
		labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
		contentPane.add(labelFuerza);

		final JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(50, 159, 56, 16);
		labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
		contentPane.add(labelDestreza);

		final JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(50, 217, 56, 16);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
		contentPane.add(labelInteligencia);

		final JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosSinAsignarIniciales));
		contentPane.add(labelPuntos);

		final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(12, 13, 177, 29);
		contentPane.add(lblCantidadDePuntos);

		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(39, 188, 83, 16);
		contentPane.add(lblInteligencia);

		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(50, 130, 56, 16);
		contentPane.add(lblDestreza);

		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(50, 72, 56, 16);
		contentPane.add(lblFuerza);

		final JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon icono_confirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(icono_confirm);
		buttonConfirm.setEnabled(false);
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				int bonusF = puntosFuerzaActuales - puntosFuerzaInicial;
				int bonusD = puntosDestrezaActuales - puntosDestrezaInicial;
				int bonusI = puntosInteligenciaActuales - puntosInteligenciaInicial;

				cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);

				try {
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar stats");

				}
				JOptionPane.showMessageDialog(null, "Se han actualizado tus atributos.");
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 112, 97, 25);
		contentPane.add(buttonConfirm);

		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon icono_c = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(icono_c);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
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

		if (puntosSinAsignar == 0) {
			buttonMore.setEnabled(false);
			buttonMore1.setEnabled(false);
			buttonMore2.setEnabled(false);
		}
		if (puntosFuerzaInicial == MAX)
			buttonMore.setEnabled(false);
		if (puntosDestrezaInicial == MAX)
			buttonMore1.setEnabled(false);
		if (puntosInteligenciaInicial == MAX)
			buttonMore2.setEnabled(false);

		ImageIcon icono_1 = new ImageIcon("recursos//botonMenoss.png");

		buttonMinus.setIcon(icono_1);
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosFuerzaActuales--;
				puntosSinAsignar++;
				buttonMore.setEnabled(true);

				if (puntosDestrezaActuales < MAX) {
					buttonMore1.setEnabled(true);
				}
				if (puntosInteligenciaActuales < MAX) {
					buttonMore2.setEnabled(true);
				}
				if (puntosSinAsignar == puntosSinAsignarIniciales)
					buttonConfirm.setEnabled(false);

				if (apretoReasignar) {
					if (puntosFuerzaActuales == fuerzaBase)
						buttonMinus.setEnabled(false);
				} else if (puntosFuerzaActuales == puntosFuerzaInicial)
					buttonMinus.setEnabled(false);

				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelFuerza.setText(String.valueOf(puntosFuerzaActuales));
			}
		});
		buttonMinus.setBounds(12, 92, 34, 25);
		contentPane.add(buttonMinus);

		buttonMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosDestrezaActuales--;
				puntosSinAsignar++;
				buttonMore1.setEnabled(true);

				if (puntosFuerzaActuales < MAX) {
					buttonMore.setEnabled(true);
				}
				if (puntosInteligenciaActuales < MAX) {
					buttonMore2.setEnabled(true);
				}
				if (puntosSinAsignar == puntosSinAsignarIniciales)
					buttonConfirm.setEnabled(false);

				if (apretoReasignar) {
					if (puntosDestrezaActuales == destrezaBase)
						buttonMinus1.setEnabled(false);
				} else if (puntosDestrezaActuales == puntosDestrezaInicial)
					buttonMinus1.setEnabled(false);

				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelDestreza.setText(String.valueOf(puntosDestrezaActuales));
			}
		});
		buttonMinus1.setIcon(icono_1);
		buttonMinus1.setBounds(12, 159, 34, 25);
		contentPane.add(buttonMinus1);

		buttonMinus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosInteligenciaActuales--;
				puntosSinAsignar++;
				buttonMore2.setEnabled(true);

				if (puntosFuerzaActuales < MAX) {
					buttonMore.setEnabled(true);
				}
				if (puntosDestrezaActuales < MAX) {
					buttonMore1.setEnabled(true);
				}
				if (puntosSinAsignar == puntosSinAsignarIniciales)
					buttonConfirm.setEnabled(false);

				if (apretoReasignar) {
					if (puntosInteligenciaActuales == inteligenciaBase)
						buttonMinus2.setEnabled(false);
				} else if (puntosInteligenciaActuales == puntosInteligenciaInicial)
					buttonMinus2.setEnabled(false);

				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelInteligencia.setText(String.valueOf(puntosInteligenciaActuales));
			}
		});
		buttonMinus2.setIcon(icono_1);
		buttonMinus2.setBounds(12, 217, 34, 25);
		contentPane.add(buttonMinus2);

		buttonMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosFuerzaActuales++;
				puntosSinAsignar--;

				if (puntosSinAsignar == 0 || puntosFuerzaActuales == MAX) {
					buttonMore.setEnabled(false);
				}
				if (puntosSinAsignar == 0) {
					buttonMore1.setEnabled(false);
					buttonMore2.setEnabled(false);
				}

				buttonConfirm.setEnabled(true);
				buttonMinus.setEnabled(true);
				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelFuerza.setText(String.valueOf(puntosFuerzaActuales));
			}
		});
		ImageIcon icono_2 = new ImageIcon("recursos//botonMass.png");
		buttonMore.setIcon(icono_2);
		buttonMore.setBounds(118, 92, 34, 25);
		contentPane.add(buttonMore);

		buttonMore1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosDestrezaActuales++;
				puntosSinAsignar--;

				if (puntosSinAsignar == 0 || puntosDestrezaActuales == MAX) {
					buttonMore1.setEnabled(false);
				}
				if (puntosSinAsignar == 0) {
					buttonMore.setEnabled(false);
					buttonMore2.setEnabled(false);
				}

				buttonConfirm.setEnabled(true);
				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelDestreza.setText(String.valueOf(puntosDestrezaActuales));
				buttonMinus1.setEnabled(true);
			}
		});
		buttonMore1.setIcon(icono_2);
		buttonMore1.setBounds(118, 159, 34, 25);
		contentPane.add(buttonMore1);

		buttonMore2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puntosInteligenciaActuales++;
				puntosSinAsignar--;

				if (puntosSinAsignar == 0 || puntosInteligenciaActuales == MAX) {
					buttonMore2.setEnabled(false);
				}
				if (puntosSinAsignar == 0) {
					buttonMore.setEnabled(false);
					buttonMore1.setEnabled(false);
				}

				buttonConfirm.setEnabled(true);
				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelInteligencia.setText(String.valueOf(puntosInteligenciaActuales));
				buttonMinus2.setEnabled(true);
			}
		});
		buttonMore2.setIcon(icono_2);
		buttonMore2.setBounds(118, 217, 34, 25);
		contentPane.add(buttonMore2);

		final JButton buttonReasignar = new JButton("Reasignar");
		//ImageIcon icono_reasignar = new ImageIcon("recursos//botonReasignar.png");
		//buttonReasignar.setIcon(icono_reasignar);
		buttonReasignar.setEnabled(true);

		buttonReasignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				apretoReasignar = true;
				puntosSinAsignar = puntosTotales;
				puntosFuerzaActuales = fuerzaBase;
				puntosDestrezaActuales = destrezaBase;
				puntosInteligenciaActuales = inteligenciaBase;

				labelPuntos.setText(String.valueOf(puntosSinAsignar));
				labelFuerza.setText(String.valueOf(fuerzaBase));
				labelDestreza.setText(String.valueOf(destrezaBase));
				labelInteligencia.setText(String.valueOf(inteligenciaBase));

				buttonMinus.setEnabled(false);
				buttonMinus1.setEnabled(false);
				buttonMinus2.setEnabled(false);

				buttonMore.setEnabled(true);
				buttonMore1.setEnabled(true);
				buttonMore2.setEnabled(true);

				buttonConfirm.setEnabled(true);
			}
		});

		buttonReasignar.setBounds(176, 78, 97, 25);
		contentPane.add(buttonReasignar);

		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
}