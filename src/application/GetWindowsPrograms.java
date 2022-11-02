package application;

import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;
import com.sun.jna.platform.win32.Advapi32Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

@SuppressWarnings("unused")
public class GetWindowsPrograms {
	// Faz uma lista das chaves contidas no diretorio App Paths (programa.exe)
	public static ArrayList<String> filePath = new ArrayList<String>();
	public static ArrayList<String> fileName = new ArrayList<String>();
			
	//Auxiliar para contar quantos diretorios nao foram encontrados no registro
	public static ArrayList<Integer> vetPathNotFounded = new ArrayList<Integer>();
			
	//Faz uma lista de todos executaveis naquela pasta.
	public static String[] listTemp = Advapi32Util.registryGetKeys(HKEY_LOCAL_MACHINE,
					"Software\\Microsoft\\Windows\\CurrentVersion\\App Paths");

	public GetWindowsPrograms() throws IndexOutOfBoundsException {
		// Conseguir o diretorio de um lugar
		// System.out.println(Advapi32Util.registryGetStringValue(HKEY_LOCAL_MACHINE,
		// "Software\\Microsoft\\Windows\\CurrentVersion\\App Paths\\LeagueClient.exe",
		// ""));
		
		//Pega a lista temporaria e converte para um ArrayList fileName
		for(String fName : listTemp) {
			fileName.add(fName);
		}
		
		//System.out.println("List temp"+listTemp);
		
		// Faz uma lista do valor Path de cada executavel contidas no diretorio App Paths
		for (int i = 0; i < fileName.size(); i++) {
			try {
				filePath.add(Advapi32Util.registryGetStringValue(HKEY_LOCAL_MACHINE,
						"Software\\Microsoft\\Windows\\CurrentVersion\\App Paths\\" + fileName.get(i), "").replace("\"", ""));
			} catch (Exception e) {

				filePath.add("N/A");
				vetPathNotFounded.add(i);
			}
		}
		

		int pos = 0;
		//Deleta da lista os arquivos sem path de destino.
		for (int i = 0; i < vetPathNotFounded.size(); i++) {
			//System.out.println("ANTES >> "+fileName.get(vetPathNotFounded.get(i)-pos));
			fileName.remove(vetPathNotFounded.get(i)-pos);
			filePath.remove(vetPathNotFounded.get(i)-pos);
			//System.out.println("DEPOIS >> "+fileName.get(vetPathNotFounded.get(i)-pos));
			pos++;
		}
	  
		ManipuladorDeArquivo arquivo = new ManipuladorDeArquivo();

		// Define o path padrao
		String username = System.getProperty("user.name");
		// System.out.println(username);
		String path = "C:\\Users\\" + username + "\\AppData\\Local\\Run Blocker\\";
		String nomeDoArquivo = "ProgramsInstalled.txt";
		try {
			arquivo.escritor(path, nomeDoArquivo, fileName, filePath); // Cria o arquivo e salva os dados
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
