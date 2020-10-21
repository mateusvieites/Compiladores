package services;

import java.io.File;
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

	public static String getText(String pathFile) throws IOException {
		File arquivo = new File(pathFile);
		String aux = "";
		String test;
		try {
			Scanner scanner = new Scanner(arquivo);
			while (scanner.hasNext()) {
				test = scanner.nextLine();
				aux += test;
				if (scanner.hasNext()) {
					aux += '\n';
				}
				scanner.close();
			}
		} catch (IOException exe) {
			exe.printStackTrace();
		}
		return aux;
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
			return null;
		}
	}
}
