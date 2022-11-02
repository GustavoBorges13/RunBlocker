package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class ManipuladorDeArquivo {
	public void leitor(String path, String nomeDoArquivo) throws IOException {
		try (BufferedReader buffRead = new BufferedReader(new FileReader(path+nomeDoArquivo))) {
			String[] split;// Vetor de separacao de cada conteudo na linha
			ArrayList<String> filePath = new ArrayList<String>();
			ArrayList<String> fileName = new ArrayList<String>();
			
			// - - - Lendo o arquivo...
			String linha = buffRead.readLine(); // ler a primeira linha do arquivo
			try {
				while (linha != null) {

					split = linha.split(" - "); // Separacao -> Nome=0 ; Matricula=1; Nota=2

					fileName.add(split[0]); // Salva a leitura do nome do aluno para um vetor.
					filePath.add(split[1]); // Salva a leitura do path para uma lista.

					linha = buffRead.readLine();
					System.out.println(split[0]+" >> "+split[1]);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();

				System.out.println(
						"Ja foram lidos todas as linhas... o programa desconsiderou o que existe depois das ultimas palavras...");
			}
			System.out.println("- Chegou no fim do arquivo!\n");
			
			buffRead.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void escritor(String path, String nomeDoArquivo, ArrayList<String> fileName, ArrayList<String> filePath) throws IOException {
		File file = new File(path+nomeDoArquivo);
		file.getParentFile().mkdirs();
		FileWriter writer = new FileWriter(file);
		
		for (int i = 0; i < fileName.size(); i++) {
			writer.write(fileName.get(i)+" - "+filePath.get(i)+"\n");
		}
		
		writer.close();
	}
}
