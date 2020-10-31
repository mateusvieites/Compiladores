package windows;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;

import entities.Info;
import model.InfoModel;
import services.FileActions;
import services.LexicalAnalyser;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
		if(consoleMessage.startsWith("error")) {
			colorText = Color.RED;
		}else {
			colorText = Color.BLACK;
		}
        changeColor( console, colorText);
        console.requestFocus();
        console.setText(consoleMessage);
	}
	
	public Stack validate(String toBeAnalysed) {
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!FilePath.equals("notAValidPath")) {
						FileWriter arquivo;
						try {
							arquivo = new FileWriter(FilePath);
							arquivo.write(jta.getText());
							arquivo.close();
						} catch (IOException e) {
							e.printStackTrace();
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
			}
		});
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		
		JMenuItem openFileMenuItem = new JMenuItem("Open File");
		openFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilePath = FileActions.filesGetFilePath();
				
				if(!FilePath.equals("notAValidPath")) {
					try {
						jta.setText(FileActions.getText(FilePath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(openFileMenuItem);
		JMenu settingsMenu = new JMenu("Settings");
		menuBar.add(settingsMenu);
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
		

		
		
		/* Table test */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(469, 17, 161, 374);
		contentPane.add(scrollPane);
		infoTable = new JTable();
		scrollPane.setViewportView(infoTable);
		infoTable.setModel(infoModel);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Info i = new Info();
				i.setWord("cu");
				i.setKey("1");
				infoModel.addRow(i);
			}
		});
		btnNewButton_1.setBounds(466, 405, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Compiler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stack stack = validate(jta.getText());
				String string;
				System.out.println("N�mero de linhas: " + infoModel.getLines());
				Stack aux = stack;
				
				if(infoModel.getLines() > 0) {
					
					infoModel.clearTable();
				}
				
				while(!aux.isEmpty()) {
					Info i = new Info();
					string = aux.pop().toString();
					String splitString[] = string.split("_");
					i.setWord(splitString[0]);
					i.setKey(splitString[1]);
					infoModel.addRow(i);
					if(splitString[1].startsWith("illegal")) {
						break;
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(177, 463, 89, 23);
		contentPane.add(btnNewButton);
		
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

