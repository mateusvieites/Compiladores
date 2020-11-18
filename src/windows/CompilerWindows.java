package windows;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;

import entities.Info;
import entities.OberserverFile;
import model.InfoModel;
import services.FileActions;
import services.LexicalAnalyser;
import services.SyntaxAnalysis;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
public class CompilerWindows extends JFrame{

	private JPanel contentPane;
	private static JTextArea jta;
	private static JTextArea lines;
	private static String FilePath = "notAValidPath";
	private JPanel paneColorText;
	private Color colorText;
	JTextPane console = new JTextPane();
	private javax.swing.JScrollPane jScrollPane1;
	/* ab */
	Stack<String> stack;
	public static TitledBorder title;
	
	String error;
	
	public static void modifyTitle(String newTitle) {
		System.out.println("Entrou no titulo!");
		System.out.println(newTitle);
		title.setTitle(newTitle);
		System.out.println(title.getTitle());
	}
	
	/*Table test*/
	InfoModel infoModel = new InfoModel();
	private JTable infoTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompilerWindows frame = new CompilerWindows();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void changeColor( JTextPane textPane, Color corTexto) {
		MutableAttributeSet attr = textPane.getInputAttributes();
		StyleConstants.setForeground( attr, corTexto );
		}
	
	private void showInConsole(String consoleMessage) {
		if(consoleMessage.startsWith("Error")) {
			colorText = Color.RED;
		}else {
			colorText = Color.BLACK;
		}
        changeColor( console, colorText);
        console.requestFocus();
        console.setText(consoleMessage);
	}
	
	public Stack<String> validate(String toBeAnalysed) {
		/*
		 * if(toBeAnalysed.equals(null) || toBeAnalysed.equals("") ||
		 * toBeAnalysed.equals(" ")) { return "error=nothing to compile"; }
		 */
		LexicalAnalyser analyseThisText = new LexicalAnalyser();
		return analyseThisText.split(jta.getText());
		
	}

	public CompilerWindows() {

		setSize(656, 545);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(10, 11, 438, 380);
		jta = new JTextArea();
		lines = new JTextArea("1");

		lines.setBackground(Color.WHITE);
		lines.setEditable(false);
		

		jta.getDocument().addDocumentListener(new DocumentListener() {
			public String getText() {
				int caretPosition = jta.getDocument().getLength();
				Element root = jta.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");		
				for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
					text += i + System.getProperty("line.separator");
				}
				return text;
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}

		});
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		lines.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 5, 0, 5)));

		title = BorderFactory.createTitledBorder("title");
		jsp.setBorder(title);
		
		contentPane.setLayout(null);
		jsp.setViewportView(jta);
		jsp.setRowHeaderView(lines);
		getContentPane().add(jsp);
		
		/*text pane */
		paneColorText = new JPanel();
		console.setEditable(false);
		console.setBounds(10, 402, 438, 50);
		console.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		contentPane.add(console);
		colorText = Color.BLACK;
        paneColorText.setBackground(colorText);
		
        
        
        /* menuBar */
        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(!FilePath.equals("notAValidPath")) {
						FileWriter arquivo;
						try {
							arquivo = new FileWriter(FilePath);
							arquivo.write(jta.getText());
							arquivo.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
				}
				
				
			}
		});
		
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jta.setText("");
				FilePath = "notAValidPath";
				/*
				 * NewFileWindows fileWindows = new NewFileWindows();
				 * title.setTitle(fileWindows.getName());
				 */
				
				testDialog test = new testDialog();
				test.setVisible(true);
				title.setTitle(test.getText());
				/* aqui */
				repaint();
			}
		});
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		
		JMenuItem openFileMenuItem = new JMenuItem("Open File");
		openFileMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FilePath = FileActions.filesGetFilePath();
				
				System.out.println("Caminho: " + FilePath);
				if(!FilePath.equals("notAValidPath")) {
					try {
						jta.setText(FileActions.getText(FilePath));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(openFileMenuItem);
		
        JMenuItem renameMenuItem = new JMenuItem("Rename");
		fileMenu.add(renameMenuItem);
		
		JMenu settingsMenu = new JMenu("Settings");
		menuBar.add(settingsMenu);

		
		
		/* Table test */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(469, 17, 161, 374);
		contentPane.add(scrollPane);
		infoTable = new JTable();
		scrollPane.setViewportView(infoTable);
		infoTable.setModel(infoModel);
		
		
		JButton btnNewButton = new JButton("Compiler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.setText("");
				stack = validate(jta.getText());
				String string;
				String lineError = "line";
				Stack<String> aux = stack;
				boolean debug =  true;
				
				if(infoModel.getLines() > 0) {
					
					infoModel.clearTable();
				}
				
				while(!aux.isEmpty()) {
					Info i = new Info();
					string = aux.pop().toString();
					String splitString[] = string.split("_");
					i.setWord(splitString[0]);
					i.setKey(splitString[1]);
					System.out.println("Palavra: " + splitString[0] + " Key: " +splitString[1] + " Linha: " + splitString[2]);
					infoModel.addRow(i);
					if(splitString[1].startsWith("illegal")) {
						error = splitString[0];
						lineError = splitString[2];
						debug = false;
						break;
					}
				}
				
				if(debug) {
					SyntaxAnalysis analysis = new SyntaxAnalysis();
					analysis.name(stack);
				}else {
					showInConsole("Error: " + error + " illegal" + " in line: " + lineError);
				}
				
				
			}
		});
		btnNewButton.setBounds(177, 463, 89, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		btnNewButton_1.setBounds(479, 402, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		/* SHORTCURST */
		/*
		 * InputMap inputMap =
		 * this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		 * inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		 * this.getRootPane().getActionMap().put("forward", new AbstractAction(){ public
		 * void actionPerformed(ActionEvent arg0) {
		 * console.setText(validate(jta.getText())); } });
		 */


	}
}

