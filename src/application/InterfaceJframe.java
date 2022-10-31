package application;

import samples.Test;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import modelEclipse.ModeloTabela;


@SuppressWarnings("unused")
public class InterfaceJframe extends JFrame {
	private static final long serialVersionUID = 1403833901296522920L;

	static JPanel contentPane;
	private Color BarraPrincipal = new Color(0, 100, 100);
	private JLabel exitLabel;
	private JLabel minimizeLabel;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblNewLabel;
	static Color appearance = Color.WHITE;
	static JMenuBar menuBar;
	private JTable table;
	Test lista;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceJframe frame = new InterfaceJframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "static-access" })
	public void preencherTabelaProprietario(ArrayList<String> fileName, ArrayList<String> filePath) {
		ArrayList<Object[]> dados = new ArrayList<>();
		String[] Colunas = new String[] { "File Name", "Path"};

		for (int i = 0; i < fileName.size(); i++) {
			dados.add(new Object[] { (fileName.get(i)), (filePath.get(i)) });
		}

		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		table.setModel(modelo);

		// Nao deixa a aumentar a largura das colunas da tabela usando o mouse!
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(1).setResizable(false);

		// Nao vai reodernar os nomes e titulos do cabeçalho da tabela
		table.getTableHeader().setReorderingAllowed(false);

		// Nao permite aumentar na tabela as colunas
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);

		// Faz com que o usuario selecione um dado na tabela POR VEZ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public InterfaceJframe() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 507);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(appearance);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Instancia o MotionPanel que captura os dados da posicao da janela e altera
		// caso desloca-se
		JPanel panel = new MotionPanel(this);

		panel.setBackground(BarraPrincipal);
		panel.setBounds(0, 0, 817, 31);
		contentPane.add(panel);
		panel.setLayout(null);

		// PANELS --------------------------------------------
		panelExit = new JPanel();
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InterfaceJframe.this.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelExit.setBackground(Color.RED);
				exitLabel.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelExit.setBackground(BarraPrincipal);
				exitLabel.setForeground(new Color(180, 180, 180));
			}
		});
		panelExit.setBackground(new Color(0, 100, 100));
		panelExit.setBounds(780, 0, 37, 31);
		panel.add(panelExit);
		panelExit.setLayout(null);

		panelMinimize = new JPanel();
		panelMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InterfaceJframe.this.setState(InterfaceJframe.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelMinimize.setBackground(new Color(0, 155, 155));
				minimizeLabel.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelMinimize.setBackground(BarraPrincipal);
				minimizeLabel.setForeground(new Color(180, 180, 180));
			}
		});
		panelMinimize.setLayout(null);
		panelMinimize.setBackground(BarraPrincipal);
		panelMinimize.setBounds(743, 0, 37, 31);
		panel.add(panelMinimize);

		// LABELS --------------------------------------------
		exitLabel = new JLabel("X");
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setBounds(0, 0, 37, 30);
		exitLabel.setFont(new Font("DialogInput", Font.PLAIN, 20));
		exitLabel.setForeground(new Color(180, 180, 180));
		panelExit.add(exitLabel);

		minimizeLabel = new JLabel("-");
		minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeLabel.setBounds(0, 0, 36, 31);
		minimizeLabel.setFont(new Font("DialogInput", Font.PLAIN, 20));
		minimizeLabel.setForeground(new Color(180, 180, 180));
		panelMinimize.add(minimizeLabel);

		lblNewLabel = new JLabel("Run Blocker");
		lblNewLabel.setFont(new Font("Montserrat Light", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 238, 31);
		panel.add(lblNewLabel);

		menuBar = new JMenuBar();
		menuBar.setBackground(appearance);
		menuBar.setBounds(0, 31, 817, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Window");
		menuBar.add(mnNewMenu);

		// Barra de preferencias - THEME!!
		// ----------------------------------------------------
		JMenuItem mntmNewMenuItem = new JMenuItem("Preference");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s;
				if (appearance == Color.WHITE) {
					Object[] possibilities = { "light", "dark" };
					s = (String) JOptionPane.showInputDialog(InterfaceJframe.this, "Select a THEME:\n",
							"Preferences - Personalization", JOptionPane.PLAIN_MESSAGE, null, possibilities, "light");
				} else {
					Object[] possibilities = { "light", "dark" };
					s = (String) JOptionPane.showInputDialog(InterfaceJframe.this, "Select a THEME:\n",
							"Preferences - Personalization", JOptionPane.PLAIN_MESSAGE, null, possibilities, "dark");
				}

				// Se a string retornar, aplique as alterações de cores (theme)
				if ((s != null) && (s.length() > 0)) {
					if (s == "light") {
						appearance = Color.WHITE;
						contentPane.setBackground(InterfaceJframe.appearance);
						InterfaceJframe.contentPane.setBackground(InterfaceJframe.appearance);
						InterfaceJframe.menuBar.setBackground(InterfaceJframe.appearance);
						return;
					} else {
						appearance = Color.BLACK;
						contentPane.setBackground(InterfaceJframe.appearance);
						InterfaceJframe.contentPane.setBackground(InterfaceJframe.appearance);
						InterfaceJframe.menuBar.setBackground(InterfaceJframe.appearance);
						return;
					}

				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				": : Programs Blocked : :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 64, 797, 390);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 777, 354);
		panel_1.add(scrollPane);

		// Tabela
		table = new JTable();
		table.setVisible(true);
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 458, 797, 38);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// NAVEGAR NOS DIRETORIOS
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();

				// Restringir o usuário para selecionar arquivos de todos os tipos
				fc.setAcceptAllFileFilterUsed(false);

				// Coloca um titulo para a janela de dialogo
				fc.setDialogTitle("Selecione um arquivo .txt");

				// Habilita para o user escolher apenas arquivos do tipo: txt
				FileNameExtensionFilter restrict = new FileNameExtensionFilter("Somente arquivos .txt", "txt");
				fc.addChoosableFileFilter(restrict);

				// Invocar a função showOpenDialog para mostrar a janela de selecao de arquivos
				// Binario -> 0 = arquivo selecionado.
				int r = fc.showOpenDialog(null);

				// System.out.println(r); //debug

				if (r == JFileChooser.APPROVE_OPTION) {

				}
			}
		});
		btnAdd.setBounds(315, 8, 51, 22);
		panel_2.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(371, 8, 63, 22);
		panel_2.add(btnDelete);

		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(439, 8, 59, 22);

		RegistryAction registry = new RegistryAction();;
		
		
		preencherTabelaProprietario(RegistryAction.fileName, RegistryAction.filePath);
		
	}
}
