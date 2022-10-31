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
	public void leitor(String path) throws IOException {
		try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
			int pos = 0;
			int tam = 100;
			String[] parteString = new String[tam]; // Vetor de separacao de cada conteudo na linha
			String[] FileName = new String[tam]; // Vetor de separacao de cada conteudo na linha
			String[] FilePath = new String[tam]; // Vetor que armazena notas dos alunos de cada linha do arquivo

			// - - - Lendo o arquivo...
			String linha = buffRead.readLine(); // ler a primeira linha do arquivo
			try {
				while (linha != null) {

					parteString = linha.split(";"); // Separacao -> Nome=0 ; Matricula=1; Nota=2

					FileName[pos] = parteString[0]; // Salva a leitura do nome do aluno para um vetor.
					FilePath[pos] = parteString[1]; // Salva a leitura da matricula do aluno para um vetor.

					System.out.println(linha);

					linha = buffRead.readLine();
					pos += 1;

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();

				System.out.println(
						"Ja foram lidos 10 linhas... o programa desconsiderou o que existe depois de 10 linhas...");
			}
			System.out.println("- Chegou no fim do arquivo!\n");

			/*
			 * // - - - GRAVANDO OS DADOS NO ARQUIVO - - - try(BufferedWriter buffWrite =
			 * new BufferedWriter(new FileWriter(path,true))){
			 * buffWrite.write(FileName[pos]+" - "+FilePath[pos]); buffWrite.close();
			 * }catch(IOException e) { System.out.println("Error: " + e.getMessage()); }
			 */

			buffRead.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void escritor(String path, ArrayList<String> fileName, ArrayList<String> filePath) throws IOException {
		File file = new File(path);
		file.getParentFile().mkdirs();
		FileWriter writer = new FileWriter(file);
		
		for (int i = 0; i < fileName.size(); i++) {
			writer.write(fileName.get(i)+" - "+filePath.get(i)+"\n");
			System.out.println(fileName.get(i)+" - "+filePath.get(i));
		}
		
		writer.close();
		/*
		File fdir = new File("Data");
        if (!fdir.exists()) {
            if(fdir.mkdirs()){
                System.out.println("directory created");
            } else {
                System.out.println("folder creation failed");
            }
        } else {
            System.out.println("directory already exists");
        }*/

 
        //File file2 = new File(ManipuladorDeArquivo.class.getClass().getResource("/application/Teste.txt");
		//file2.getParentFile().mkdirs();
		
		/*
		writer = new FileWriter(file);
		writer.write(path, 0, 0);
		writer.close();*/
	}
}
