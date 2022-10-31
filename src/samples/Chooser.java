package samples;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

//Codigo de referencia para lembrar como que usa o JFileChooser e filtros para abrir arquivos
public class Chooser extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chooser frame = new Chooser();
					frame.setTitle("Programa de exemplo - Menu");
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Chooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpcoes = new JMenu("Opções");
		menuBar.add(mnOpcoes);
		
		JMenuItem mntmCalcularScore = new JMenuItem("Calcular Score");
		mnOpcoes.add(mntmCalcularScore);
		
		JMenu mnExibir = new JMenu("Exibir");
		menuBar.add(mnExibir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDiretorio = new JLabel("");
		lblDiretorio.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNomeArquivo = new JLabel("Nenhum arquivo foi selecionado.");
		lblNomeArquivo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNomeArquivo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeArquivo, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
								.addComponent(lblDiretorio, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(lblNomeArquivo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDiretorio, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 15));
		textArea.setEditable(true);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		
		//Abrir arquivo
		JMenuItem mntmAbrirArquivo = new JMenuItem("Abrir Arquivo");
		mntmAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				
				// Restringir o usuário para selecionar arquivos de todos os tipos
	            fc.setAcceptAllFileFilterUsed(false);
	            
	            // Coloca um titulo para a janela de dialogo
	            fc.setDialogTitle("Selecione um arquivo .txt");
	            
	            // Habilita para o user escolher apenas arquivos do tipo: txt
	            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Somente arquivos .txt", "txt");
	            fc.addChoosableFileFilter(restrict);
	            
	            // Invocar a função showOpenDialog para mostrar a janela de selecao de arquivos
	            int r = fc.showOpenDialog(null);
	            
	            // Se o usuario Selecionar o arquivo...
	            if (r == JFileChooser.APPROVE_OPTION)
	            {
	            	try {
	            		FileReader arq = new FileReader(fc.getSelectedFile()); //path
						BufferedReader buffRead = new BufferedReader(arq);
						String linha = buffRead.readLine();
						String tmp = "";
						//List<String> conteudo = new ArrayList<>();
						
						//Print path
						System.out.println("Diretorio do arquivo: " +fc.getSelectedFile()+"");
						lblDiretorio.setText(fc.getSelectedFile()+"");
	
						//Print nome do arquivo
						System.out.println("Nome do arquivo: " + fc.getSelectedFile().getName());
						lblNomeArquivo.setText(fc.getSelectedFile().getName()+"");
						
						//Ler o conteudo do arquivo
						//Print do conteudo do arquivo na Text Area.
						while (linha != null) {
				            //System.out.printf("%s\n", linha);
							//conteudo.add(linha+"\n");
							tmp = tmp + linha + "\n";
				            linha = buffRead.readLine(); // lê da segunda até a última linha
				        }
						textArea.setText(tmp);
						
						arq.close();
	            	}catch (IOException e) {
	            		e.printStackTrace();
	            		lblNomeArquivo.setText("Nenhum arquivo foi selecionado.");		
		                lblDiretorio.setText("O usuario selecionou um arquivo inexistente ou o arquivo está vazio.");
	            	}
	            }
	            // Se o usuario cancelar a operacao...
	            else {
	                lblNomeArquivo.setText("Nenhum arquivo foi selecionado.");		
	                lblDiretorio.setText("O usuário cancelou a operação.");
	            }
			}
		});
		mnOpcoes.add(mntmAbrirArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnOpcoes.add(mntmSair);
		
	}
}

