package interface_grafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InterfaceMiniGolf extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	protected JPanel contentPane;
	protected JLabel lblBola;
	protected TileMapPane panel;
	protected JLabel lblStatus;
	private InterfaceJogador atorJogador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceMiniGolf frame = new InterfaceMiniGolf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceMiniGolf() {
		atorJogador = new InterfaceJogador();

		setTitle("Minigolf");
		setBackground(new Color(0, 102, 51));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 599);

		DrawMenuBar();

		contentPane = new JPanel();
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Jogador da Vez");
		lblNewLabel.setBounds(5, 11, 221, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel);

		lblStatus = new JLabel("Status");
		lblStatus.setBounds(0, 514, 779, 24);
		lblStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblStatus);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new TileMapPane("/images/grass.jpg");
		panel.setForeground(new Color(0, 51, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel.setBounds(0, 43, 779, 469);
		panel.setBackground(new Color(0, 128, 0));
		contentPane.add(panel);

		JLabel lblJogadorDaVez = new JLabel("Rodada N");
		lblJogadorDaVez.setVerticalAlignment(SwingConstants.TOP);
		lblJogadorDaVez.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblJogadorDaVez.setBounds(664, 11, 115, 31);
		contentPane.add(lblJogadorDaVez);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(206, 11, 369, 25);
		contentPane.add(progressBar);
		UIManager.put("ProgressBar.selectionBackground", Color.RED);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				progressBar.setValue((int) p.getX() % 100);
			}
		});

		DrawBola();

		DrawPontilhado();

		DrawBuraco();

	}

	protected void DrawBuraco() {
		JLabel lblBuraco = new JLabel("");
		lblBuraco.setBounds(158, 185, 30, 30);
		lblBuraco.setIcon(new ImageIcon(InterfaceMiniGolf.class.getResource("/images/buraco.png")));
		panel.add(lblBuraco);

		JLabel lblPontJog1 = new JLabel("Jogador1: ");
		lblPontJog1.setBounds(0, 26, 213, 20);
		panel.add(lblPontJog1);
		lblPontJog1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblpontuacao = new JLabel("Pontua\u00E7\u00E3o: ");
		lblpontuacao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblpontuacao.setBounds(0, 0, 213, 30);
		panel.add(lblpontuacao);

		JLabel lblPontJog2 = new JLabel("Jogador2: ");
		lblPontJog2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPontJog2.setBounds(0, 45, 213, 20);
		panel.add(lblPontJog2);

		TileMapPane panel_1 = new TileMapPane("/images/wood.png");
		panel_1.setBounds(111, 96, 9, 243);
		panel.add(panel_1);

		TileMapPane panel_1_1 = new TileMapPane("/images/wood.png");
		panel_1_1.setBounds(607, 96, 9, 243);
		panel.add(panel_1_1);

		TileMapPane panel_1_2 = new TileMapPane("/images/wood.png");
		panel_1_2.setBounds(111, 88, 505, 9);
		panel.add(panel_1_2);

		TileMapPane panel_1_2_1 = new TileMapPane("/images/wood.png");
		panel_1_2_1.setBounds(111, 330, 505, 9);
		panel.add(panel_1_2_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(InterfaceMiniGolf.class.getResource("/images/bandeira.png")));
		lblNewLabel_1.setBounds(168, 129, 58, 96);
		panel.add(lblNewLabel_1);
	}

	protected void DrawBola() {
		panel.setLayout(null);
		lblBola = new JLabel("");
		lblBola.setBounds(277, 287, 20, 20);
		lblBola.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBola.setHorizontalAlignment(SwingConstants.CENTER);
		lblBola.setIcon(new ImageIcon(InterfaceMiniGolf.class.getResource("/images/golfball.png")));
		panel.add(lblBola);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, panel);
				p.setLocation(p.getX() - lblBola.getWidth() / 2, p.getY() - lblBola.getHeight() / 2);
				lblBola.setLocation(p);
			}
		});
	}

	protected void DrawMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mntmConectar.setAction(action);
		mntmConectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblStatus.setText("Conectado");
			}
		});
		mnMenu.add(mntmConectar);

		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.setAction(action_1);
		mntmDesconectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblStatus.setText("Desconectado");
			}
		});
		mnMenu.add(mntmDesconectar);

		JMenuItem mntmIniciarPartida = new JMenuItem("Iniciar Partida");
		mntmIniciarPartida.setAction(action_2);
		mntmIniciarPartida.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblStatus.setText("Iniciado a partida");
			}
		});
		mnMenu.add(mntmIniciarPartida);
	}

	protected void DrawPontilhado() {
		JLabel lblPontilhado = new JLabel("_ _ _ _ _ _ _ _ _");
		lblPontilhado.setBounds(225, 185, 202, 30);
		lblPontilhado.setForeground(Color.WHITE);
		lblPontilhado.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.add(lblPontilhado);

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, panel);

				lblPontilhado.setLocation(p);
			}
		});
	}
	
	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "conectar");
			putValue(SHORT_DESCRIPTION, "conectar a Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			// Necessário definir endereço do servidor e nome do jogador
			String mensagem = atorJogador.conectar("localhost", "nomeJogador?");
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "Desconectar");
			putValue(SHORT_DESCRIPTION, "desconectar de Netgames Server");
		}
		public void actionPerformed(ActionEvent e) {
			String mensagem = atorJogador.desconectar();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "iniciar partida");
			putValue(SHORT_DESCRIPTION, "iniciar partida do seu jogo");
		}
		public void actionPerformed(ActionEvent e) {
			String mensagem = atorJogador.iniciarPartida();
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
}
