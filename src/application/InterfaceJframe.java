package application;

import samples.Test;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import modelEclipse.ModeloTabela;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

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
	private JTable tableProgramsInstalled;
	static JPanel panelProgramsInstalled;
	private JTable tableProgramsBlocked;
	private JScrollPane scrollPaneProgramsInstalled;
	private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	private static int alinhamento = SwingConstants.LEFT;
	private JButton btnBlock;
	private JButton btnApply;
	private JButton btnRemove;
	private JButton btnAdd;

	// Lista de programas bloqueados
	private ArrayList<String> fileName = new ArrayList<String>();
	private ArrayList<String> filePath = new ArrayList<String>();
	static ArrayList<String> fileNameB = new ArrayList<String>();
	static ArrayList<String> filePathB = new ArrayList<String>();

	public InterfaceJframe() {
		windowConfigurations();

		actions();

	}

	public void actions() {
		// Manipulacao de arquivos - Criacao e leitura
		int flag;
		
		// Cria um arquivo contendo os programas instalados
		GetWindowsPrograms installedPrograms = new GetWindowsPrograms();

		// Verifica se ja existe algum arquivo dos programas bloqueados, caso contrario
		// ele cria um novo
		try {
			flag = 0;
			GetWindowsProgramsBlocked blockedPrograms = new GetWindowsProgramsBlocked(flag);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error when opening the locked programs file!\\nError: " + e);
		} finally {
			flag = 1;
			GetWindowsProgramsBlocked blockedPrograms = new GetWindowsProgramsBlocked(flag);
		}

		// Preenchimento das tabelas com os dados obtidos
		preencherTabelaProprietario(GetWindowsPrograms.fileName, GetWindowsPrograms.filePath, tableProgramsInstalled);
		preencherTabelaProprietario(fileNameB, filePathB, tableProgramsBlocked);

		// Tabela programas instalados - Controle
		tableProgramsInstalled.addMouseListener(new MouseAdapter() {
			boolean isAlreadyOneClick;

			@Override
			public void mouseClicked(MouseEvent e) {
				// Habilita/desabilita botoes
				btnBlock.setEnabled(true);
				btnRemove.setEnabled(false);

				if (isAlreadyOneClick) {
					btnBlock.doClick();
				} else {
					isAlreadyOneClick = true;

					//Limpa as selecoes
					tableProgramsBlocked.getSelectionModel().clearSelection();
					//tableProgramsInstalled.setCellSelectionEnabled(true); // ALTERNATIVA QUE DA PRA LIMPAR TB
					
					// Se clicar duas vezes, mostrar janela de dialogo
					Timer t = new Timer("doubleclickTimer", false);
					t.schedule(new TimerTask() {

						@Override
						public void run() {
							isAlreadyOneClick = false;
						}
					}, 300);
				}
			}
			public void mousePressed(MouseEvent e) {
				Timer t = new Timer("doubleclickTimer", false);
				t.schedule(new TimerTask() {

					@Override
					public void run() {
						//Limpa as selecoes
						tableProgramsBlocked.getSelectionModel().clearSelection();
						
						// Habilita/desabilita botoes
						btnBlock.setEnabled(true);
						btnRemove.setEnabled(false);
					}
				}, 100);
			}
		});

		// Tabela programas bloqueados - Controle
		tableProgramsBlocked.addMouseListener(new MouseAdapter() {
			boolean isAlreadyOneClick;

			@Override
			public void mouseClicked(MouseEvent e) {
				// Habilita/desabilita botoes
				btnRemove.setEnabled(true);
				btnBlock.setEnabled(false);

				if (isAlreadyOneClick) {

					System.out.println("Tabela block - OK");
				} else {
					isAlreadyOneClick = true;

					//Limpa as selecoes
					tableProgramsInstalled.getSelectionModel().clearSelection();

					// Se clicar duas vezes, mostrar janela de dialogo
					Timer t = new Timer("doubleclickTimer", false);
					t.schedule(new TimerTask() {

						@Override
						public void run() {
							isAlreadyOneClick = false;
						}
					}, 300);
				}
			}
			public void mousePressed(MouseEvent e) {
				Timer t = new Timer("doubleclickTimer", false);
				t.schedule(new TimerTask() {

					@Override
					public void run() {
						//Limpa as selecoes
						tableProgramsInstalled.getSelectionModel().clearSelection();
						
						// Habilita/desabilita botoes
						btnRemove.setEnabled(true);
						btnBlock.setEnabled(false);
					}
				}, 100);
			}
		});

		// Block button
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = tableProgramsInstalled.getSelectedRow();
				boolean duplicated = false;

				for (int i = 0; i < fileNameB.size(); i++) {
					if (tableProgramsInstalled.getModel().getValueAt(row, column).toString() == fileNameB.get(i)) {
						duplicated = true;
						break;
					} else {
						duplicated = false;
					}
				}

				if (duplicated == true) {
					JOptionPane.showMessageDialog(null, "Operation cancelled! You have already blocked this program.");
				} else {
					if (JOptionPane.showConfirmDialog(null,
							"Are you sure you want to block the program:\n>"
									+ tableProgramsInstalled.getModel().getValueAt(row, column).toString() + "\n"
									+ tableProgramsInstalled.getModel().getValueAt(row, column + 1).toString(),
							"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						fileNameB.add(tableProgramsInstalled.getModel().getValueAt(row, column).toString());
						filePathB.add(tableProgramsInstalled.getModel().getValueAt(row, column + 1).toString());

						// Atualiza a tabela
						preencherTabelaProprietario(fileNameB, filePathB, tableProgramsBlocked);
						
						//Limpa as selecoes
						tableProgramsBlocked.getSelectionModel().clearSelection();
						tableProgramsInstalled.getSelectionModel().clearSelection();
						
						// Habilita/desabilita botoes
						btnRemove.setEnabled(false);
						btnBlock.setEnabled(false);

						//Tira a bug da borda de selecao da tabela...
						//Faz que o programa foque no contentPane principal e nao volte na tabela.
						contentPane.requestFocus();
						
					} else {
						//Limpa as selecoes
						tableProgramsBlocked.getSelectionModel().clearSelection();
						tableProgramsInstalled.getSelectionModel().clearSelection();
					}
				}
			}
		});

		// Remove Button
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = tableProgramsBlocked.getSelectedRow();
				boolean duplicated = false;

				if (JOptionPane.showConfirmDialog(null,
						"Are you sure you want to remove the application:\n>"
								+ tableProgramsBlocked.getModel().getValueAt(row, column).toString() + "\n"
								+ tableProgramsBlocked.getModel().getValueAt(row, column + 1).toString(),
						"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					for (int i = 0; i < fileNameB.size(); i++) {
						if (tableProgramsBlocked.getModel().getValueAt(row, column).toString() == fileNameB.get(i)) {
							fileNameB.remove(i);
						}
					}

					// Atualiza a tabela
					preencherTabelaProprietario(fileNameB, filePathB, tableProgramsBlocked);
					
					//Limpa as selecoes
					tableProgramsBlocked.getSelectionModel().clearSelection();
					tableProgramsInstalled.getSelectionModel().clearSelection();

					// Habilita/desabilita botoes
					btnRemove.setEnabled(false);
					btnBlock.setEnabled(false);
					
					//Tira a bug da borda de selecao da tabela...
					//Faz que o programa foque no contentPane principal e nao volte na tabela.
					contentPane.requestFocus();
					
				} else {
					//Limpa as selecoes
					tableProgramsBlocked.getSelectionModel().clearSelection();
					tableProgramsInstalled.getSelectionModel().clearSelection();
				}

			}
		});

		// Apply Button
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to apply this setting?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					// Atualiza o arquivo de Programas Bloqueados
					try {
						int flag = 1;
						GetWindowsProgramsBlocked blockedPrograms = new GetWindowsProgramsBlocked(flag);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Error when opening the locked programs file!\\nError: " + ex);
					}
				} else {
					//Limpa as selecoes
					tableProgramsBlocked.getSelectionModel().clearSelection();
					tableProgramsInstalled.getSelectionModel().clearSelection();
				}
			}
		});

		// Add other application (.exe) Button
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
	}

	public void windowConfigurations() {
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

		// Personalizacao das barra
		CustomMenuItemUI menuItemUI_light = new CustomMenuItemUI(Color.GRAY);
		CustomMenuItemUI menuItemUI_dark = new CustomMenuItemUI(new Color(211, 211, 211));
		CustomMenuItemUI menuItemUI_blue = new CustomMenuItemUI(new Color(150, 210, 245));
		CustomMenuUI menuUI_ligth = new CustomMenuUI(Color.GRAY);
		CustomMenuUI menuUI_dark = new CustomMenuUI(Color.WHITE);
		CustomMenuUI menuUI_blue = new CustomMenuUI(new Color(30, 144, 255));

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
				System.exit(0);
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
		menuBar.setOpaque(true);
		menuBar.setBounds(0, 31, 817, 22);
		contentPane.add(menuBar);

		JMenu menuWindow = new JMenu("Window");
		menuWindow.setFont(new Font("Dialog", Font.BOLD, 12));
		menuWindow.setForeground(Color.DARK_GRAY);
		menuWindow.setOpaque(false);
		menuBar.add(menuWindow);

		// Barra de preferencias - THEME!!
		// ----------------------------------------------------
		JMenuItem menuPreference = new JMenuItem("Preference");
		menuPreference.setOpaque(true);

		menuWindow.setUI(menuUI_blue);
		menuPreference.setUI(menuItemUI_blue);

		menuPreference.addActionListener(new ActionListener() {
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
						InterfaceJframe.panelProgramsInstalled.setBackground(Color.WHITE);
						InterfaceJframe.contentPane.setBackground(InterfaceJframe.appearance);
						menuBar.setBackground(Color.WHITE);
						menuWindow.setBackground(Color.WHITE);
						menuWindow.setForeground(Color.DARK_GRAY);
						menuPreference.setBackground(Color.WHITE);
						menuPreference.setForeground(Color.DARK_GRAY);
						menuWindow.setUI(menuUI_blue);
						menuPreference.setUI(menuItemUI_dark);
						scrollPaneProgramsInstalled.setBackground(new Color(238, 238, 238));
						tableProgramsInstalled.setBackground(Color.WHITE);
						tableProgramsInstalled.setForeground(Color.DARK_GRAY);
						return;
					} else {
						appearance = Color.GRAY;
						InterfaceJframe.panelProgramsInstalled.setBackground(Color.GRAY);
						InterfaceJframe.contentPane.setBackground(InterfaceJframe.appearance);
						menuBar.setBackground(Color.DARK_GRAY);
						menuWindow.setBackground(Color.DARK_GRAY);
						menuWindow.setForeground(Color.WHITE);
						menuPreference.setBackground(Color.DARK_GRAY);
						menuPreference.setForeground(Color.WHITE);
						menuWindow.setUI(menuUI_blue);
						menuPreference.setUI(menuItemUI_dark);
						scrollPaneProgramsInstalled.setBackground(Color.GRAY);
						tableProgramsInstalled.setBackground(Color.DARK_GRAY);
						tableProgramsInstalled.setForeground(Color.WHITE);
						return;
					}

				}
			}
		});
		menuWindow.add(menuPreference);

		panelProgramsInstalled = new JPanel();
		panelProgramsInstalled.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				": : Programs Installed : :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProgramsInstalled.setBackground(Color.WHITE);
		panelProgramsInstalled.setBounds(10, 64, 396, 390);
		contentPane.add(panelProgramsInstalled);
		panelProgramsInstalled.setLayout(null);

		scrollPaneProgramsInstalled = new JScrollPane();
		scrollPaneProgramsInstalled.setBackground(new Color(238, 238, 238));
		scrollPaneProgramsInstalled.setBounds(10, 25, 374, 354);
		panelProgramsInstalled.add(scrollPaneProgramsInstalled);

		// Tabela
		tableProgramsInstalled = new JTable();
		tableProgramsInstalled.setAutoscrolls(false);
		tableProgramsInstalled.setForeground(Color.DARK_GRAY);
		tableProgramsInstalled.setBackground(Color.WHITE);
		tableProgramsInstalled.setVisible(true);
		scrollPaneProgramsInstalled.setViewportView(tableProgramsInstalled);

		JPanel panelProgramsBlocked = new JPanel();
		panelProgramsBlocked.setLayout(null);
		panelProgramsBlocked.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				": : Programs Blocked : :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProgramsBlocked.setBackground(Color.WHITE);
		panelProgramsBlocked.setBounds(405, 64, 406, 390);
		contentPane.add(panelProgramsBlocked);

		JScrollPane scrollPaneProgramsBlocked = new JScrollPane();
		scrollPaneProgramsBlocked.setBackground(new Color(238, 238, 238));
		scrollPaneProgramsBlocked.setBounds(10, 25, 384, 354);
		panelProgramsBlocked.add(scrollPaneProgramsBlocked);

		// Tabela
		tableProgramsBlocked = new JTable();
		tableProgramsBlocked.setAutoscrolls(false);
		tableProgramsBlocked.setForeground(Color.DARK_GRAY);
		tableProgramsBlocked.setBackground(Color.WHITE);
		tableProgramsBlocked.setVisible(true);
		scrollPaneProgramsBlocked.setViewportView(tableProgramsBlocked);

		JPanel panelButtonsActions = new JPanel();
		panelButtonsActions.setBounds(12, 458, 795, 38);
		contentPane.add(panelButtonsActions);
		panelButtonsActions.setLayout(null);

		btnAdd = new JButton("Add other application (.exe)");

		btnAdd.setBounds(44, 8, 192, 22);
		panelButtonsActions.add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.setBounds(508, 8, 84, 22);
		panelButtonsActions.add(btnRemove);

		btnBlock = new JButton("Block");
		btnBlock.setEnabled(false);
		btnBlock.setBounds(248, 8, 78, 22);
		panelButtonsActions.add(btnBlock);

		btnApply = new JButton("Apply");
		btnApply.setEnabled(true);
		btnApply.setBounds(604, 8, 84, 22);
		panelButtonsActions.add(btnApply);

	}

	public class CustomMenuItemUI extends BasicMenuItemUI {
		public CustomMenuItemUI(Color color) {
			super.selectionBackground = color;
		}
	}

	public class CustomMenuUI extends BasicMenuUI {
		public CustomMenuUI(Color color) {
			super.selectionBackground = color;
		}
	}

	// Customize the code to set the background and foreground color for each column
	// of a JTable
	class ColumnColorRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = -2920708207060590184L;
		Color backgroundColor, foregroundColor;

		public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
			super();
			this.backgroundColor = backgroundColor;
			this.foregroundColor = foregroundColor;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			cell.setBackground(backgroundColor);
			cell.setForeground(foregroundColor);
			return cell;
		}
	}

	public class HorizontalAlignmentHeaderRenderer implements TableCellRenderer {
		private int horizontalAlignment = SwingConstants.LEFT;

		public HorizontalAlignmentHeaderRenderer(int horizontalAlignment) {
			this.horizontalAlignment = horizontalAlignment;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			TableCellRenderer r = table.getTableHeader().getDefaultRenderer();
			JLabel l = (JLabel) r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			l.setHorizontalAlignment(horizontalAlignment);
			return l;
		}
	}

	@SuppressWarnings("static-access")
	public void preencherTabelaProprietario(ArrayList<String> fName, ArrayList<String> fPath, JTable table) {
		ArrayList<Object[]> dados = new ArrayList<>();
		String[] Colunas = new String[] { " File Name", " Path" };

		for (int i = 0; i < fName.size(); i++) {
			dados.add(new Object[] { (fName.get(i)), (fPath.get(i)) });
		}

		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		table.setModel(modelo);

		// Nao deixa a aumentar a largura das colunas da tabela usando o mouse e realiza
		// os alinhamentos das colunas e linhas!
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(alinhamento));
		table.getColumnModel().getColumn(1).setPreferredWidth(540);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(alinhamento));

		// Nao vai reodernar os nomes e titulos do cabeçalho da tabela
		table.getTableHeader().setReorderingAllowed(false);

		// Nao permite aumentar na tabela as colunas
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);

		// Faz com que o usuario selecione um dado na tabela POR VEZ
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Realiza a configuracao de fontes
		table.getTableHeader().setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		table.setFont(new Font("Dialog", Font.PLAIN, 9));
	}

	public static void main(String[] args) {
		try {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException ex) {
			} catch (InstantiationException ex) {
			} catch (IllegalAccessException ex) {
			} catch (UnsupportedLookAndFeelException ex) {
			}
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
}
