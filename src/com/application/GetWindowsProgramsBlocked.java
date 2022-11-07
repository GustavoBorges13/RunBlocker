package com.application;

import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Win32Exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

@SuppressWarnings("unused")
public class GetWindowsProgramsBlocked {
	private ArrayList<String> fileNameTemp = new ArrayList<String>();

	public GetWindowsProgramsBlocked() throws IndexOutOfBoundsException {
		ManipuladorDeArquivo arquivo = new ManipuladorDeArquivo();

		// Define o path padrao
		String username = System.getProperty("user.name");
		// System.out.println(username);
		String path = ".\\data\\";
		String nomeDoArquivo = "ProgramsBlocked.txt";

		InterfaceJframe.fileNameB.clear();
		InterfaceJframe.filePathB.clear();
		
		// Faz um mapeamento das chaves com seus respectivos valores
		Map<String, Object> values;
		int pos = 0;

		try {
			values = Advapi32Util.registryGetValues(HKEY_CURRENT_USER,
					"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");

			for (Object value : values.values()) {
				InterfaceJframe.fileNameB.add(value+"");
			}

			for (int i = 0; i < InterfaceJframe.fileNameB.size(); i++) {
				InterfaceJframe.filePathB.add(GetWindowsPrograms.filePath
						.get(GetWindowsPrograms.fileName.indexOf(InterfaceJframe.fileNameB.get(i))));
			}

		} catch (Win32Exception e) {
			System.out.println(e);

			// Cria as chaves necessarias para bloquear os programas - Explorer -
			// DisallowRun
			Advapi32Util.registryCreateKey(HKEY_CURRENT_USER,
					"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer\\DisallowRun");

			// Atribui o valor DisallowRun na chave Explorer
			Advapi32Util.registrySetIntValue(HKEY_CURRENT_USER,
					"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\Explorer", "DisallowRun", 1);

			System.out.println("\nRegistros adicionados com sucesso!");
		}

		try {
			arquivo.escritor(path, nomeDoArquivo, InterfaceJframe.fileNameB, InterfaceJframe.filePathB); // Cria o arquivo e salva os dados
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error when opening the locked programs file!\nError: " + e1);
		}
		
	}
	
}
