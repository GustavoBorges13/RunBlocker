package samples;

import java.io.File;
import java.util.ArrayList;


//Esse codigo printa tenta printar todos os atalhos existentes na maquina Windows do usuario
public class directoryAcess {
	public static void main(String[] args) {
		// Carregar programas instalados
		ArrayList<String> path = new ArrayList<String>();
		path.add("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs");
		File pasta = new File(path.get(0));
		path.clear();

		File[] listaCompleta = pasta.listFiles();
		ArrayList<String> pathAux = new ArrayList<String>();
		ArrayList<String> listaPastas = new ArrayList<String>();
		ArrayList<String> listaPastasTemp = new ArrayList<String>(); // Essas sao as subpastas que terao que passar
																		// novamente na varredura
		ArrayList<String> listaArquivos = new ArrayList<String>();

		for (int i = 0; i < listaCompleta.length; i++) {
			if (listaCompleta[i].isFile()) { // Armazena os dados dos arquivos encontrados
				listaArquivos.add(listaCompleta[i].getName());
			} else if (listaCompleta[i].isDirectory()) { // Armazena os dados das pastas encontradas
				path.add(listaCompleta[i].getAbsolutePath()); // Salva o local da pasta identificada na array de
																// caminhos
				listaPastas.add(listaCompleta[i].getName());
			}
		}

		// Se encontrou subpastas entao faz a varredura denovo e adiciona os novos
		// shortcuts
		for (int i = 0; i < listaPastas.size(); i++) {
			// Altera o path utilizando as novas informacoes
			pasta = new File(path.get(i));

			// Gera uma lista com todas as informacoes contidas naquele diretorio especifico
			listaCompleta = pasta.listFiles();

			// Verificacao se o primeiro dado analisado é uma subpasta ou arquivo de atalho
			for (int j = 0; j < listaCompleta.length; j++) {
				if (listaCompleta[j].isFile()) { // Armazena os dados dos arquivos encontrados
					listaArquivos.add(listaCompleta[j].getName());
					// System.out.println("Program: "+listaCompleta[j].getName());
				} else if (listaCompleta[j].isDirectory()) { // Armazena os dados das pastas encontradas
					pathAux.add(listaCompleta[j].getAbsolutePath());
					listaPastasTemp.add(listaCompleta[j].getName());
					// System.out.println("Directory: "+listaCompleta[j].getAbsolutePath());
				}
			}
		}
		listaPastas.clear();

		// Se encontrou mais subpastas entao faz a varredura denovo e adiciona os novos
		// shortcuts
		for (int i = 0; i < listaPastasTemp.size(); i++) {
			// Altera o path utilizando as novas informacoes
			pasta = new File(pathAux.get(i));

			// Gera uma lista com todas as informacoes contidas naquele diretorio especifico
			listaCompleta = pasta.listFiles();

			// Verificacao se o primeiro dado analisado é uma subpasta ou arquivo de atalho
			for (int j = 0; j < listaCompleta.length; j++) {
				if (listaCompleta[j].isFile()) { // Armazena os dados dos arquivos encontrados
					listaArquivos.add(listaCompleta[j].getName());
					// System.out.println("Program: "+listaCompleta[j].getName());
				} else if (listaCompleta[j].isDirectory()) { // Armazena os dados das pastas encontradas
					listaPastas.add(listaCompleta[j].getName());
					// System.out.println("Directory: "+listaCompleta[j].getName());
				}
			}
		}

		for (int i = 0; i < listaArquivos.size(); i++) {
			System.out.println(listaArquivos.get(i));
		}
	}
}
