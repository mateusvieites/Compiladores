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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class CompilerWindows extends JFrame {

	private JPanel contentPane;
	private static JTextArea jta;
	private static JTextArea lines;

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

	public CompilerWindows() {
		setSize(656, 502);
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

		contentPane.setLayout(null);
		jsp.setViewportView(jta);
		jsp.setRowHeaderView(lines);
		getContentPane().add(jsp);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 403, 438, 49);
		contentPane.add(textArea);

	}
}

