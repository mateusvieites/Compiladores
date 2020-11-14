package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.OberserverFile;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

public class NewFileWindows extends JFrame {

	private JPanel contentPane;
	private JTextField name_txf;
	public String name;
	public boolean check = false;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCheck(boolean bool) {
		this.check = bool;
	}
	
	public boolean getCheck() {
		return this.check;
	}
	
	
	public NewFileWindows() {
		setTitle("New File");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 111);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name_txf = new JTextField();
		name_txf.setBounds(88, 11, 336, 20);
		contentPane.add(name_txf);
		name_txf.setColumns(10);
		
		
		
		JLabel name_label = new JLabel("Name:");
		name_label.setBounds(10, 14, 46, 14);
		contentPane.add(name_label);
		
		JLabel error_label = new JLabel("error:");
		error_label.setBounds(10, 39, 315, 14);
		error_label.setVisible(false);
		
		contentPane.add(error_label);
		JButton finish_button = new JButton("Finish");
		finish_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(name_txf.getText().equals(null) || name_txf.getText().equals("") ||
						name_txf.getText().equals(" ")) {
					error_label.setText("Please put a name");
					error_label.setVisible(true);
				}else {
					setName(name_txf.getText().toString());
					setCheck(true);
					dispose();
				}
			}
		});
		
		finish_button.setBounds(335, 42, 89, 23);
		contentPane.add(finish_button);
	}
}
