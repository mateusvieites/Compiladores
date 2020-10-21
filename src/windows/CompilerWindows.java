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
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
public class CompilerWindows extends JFrame{

	private JPanel contentPane;
	private static JTextArea jta;
	private static JTextArea lines;
	private static String FilePath;
	private JPanel paneColorText;
	private Color colorText;
	JTextPane console = new JTextPane();

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
	
	public String validate(String toBeAnalysed) {
		if(toBeAnalysed.equals(null) || toBeAnalysed.equals("") || toBeAnalysed.equals(" ")) {
			return "error=There is nothing to compile";
		}
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
				FileWriter arquivo;
				try {
					arquivo = new FileWriter(FilePath);
					arquivo.write(jta.getText());
					arquivo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		fileMenu.add(saveMenuItem);
		
		JMenuItem openFileMenuItem = new JMenuItem("Open File");
		openFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilePath = FileActions.filesGetFilePath();
				
				if(FilePath.equals(null) || FilePath == null) {
					/*arquivo não selecionado */
				}else {
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
		
		
		JButton btnNewButton = new JButton("Compiler");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = validate(jta.getText());
				showInConsole(aux);
				
			}
		});
		btnNewButton.setBounds(458, 13, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		/* SHORTCURST */
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		this.getRootPane().getActionMap().put("forward", new AbstractAction(){
		    public void actionPerformed(ActionEvent arg0) {
		    	console.setText(validate(jta.getText()));
		    }
		});


	}
}

