package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileActions {
	private static FileActions uniqueInstance;
	private FileActions() {
	}

	public static FileActions getInstance() {
		return uniqueInstance;
	}
	
	public static void save(String FilePath, String text) {
		if(!FilePath.equals("notAValidPath")) {
			FileWriter arquivo;
			try {
				arquivo = new FileWriter(FilePath);
				arquivo.write(text);
				arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getText(String pathFile) throws IOException {
		File arquivo = new File(pathFile);
		String aux = "";
		String test;
		try {
			Scanner scanner = new Scanner(arquivo);
			
			while (scanner.hasNext()) {
				System.out.println("entrou");
				test = scanner.nextLine();
				
				aux += test;
				if (scanner.hasNext()) {
					aux += '\n';
				}
			}
		} catch (IOException exe) {
			exe.printStackTrace();
		}
		return aux;
	}

	public static String getPath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select path");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnD = fileChooser.showOpenDialog(null);
		if (returnD == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file.getPath();
		} else {
			return "notAValidPath";
		}
	}
	public static String filesGetFilePath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("text files","txt"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Select file");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnD = fileChooser.showOpenDialog(null);
		if (returnD == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file.getPath();
		} else {
			return "notAValidPath";
		}
	}
}
