package org.cofezuwo.nsuluofuo.main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import static javax.swing.JFrame.*;

public class Launcher {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private Game game;
	static String address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Launcher window = new Launcher();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
		game = new Game("NSULUOFUO", 640, 480);
		address = textField_1.getText();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 262);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(e -> {
			game.start();
			frame.dispose();
		});
		btnNewButton.setBounds(335, 189, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(335, 122, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		btnLogin.setBackground(Color.LIGHT_GRAY);
		
		
		textField = new JTextField();
		textField.setBounds(335, 36, 89, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(335, 11, 89, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(335, 67, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(335, 92, 89, 22);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPlayer = new JLabel("> Player1 <");
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBounds(335, 164, 89, 14);
		frame.getContentPane().add(lblPlayer);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 315, 167);
		frame.getContentPane().add(textPane);
		
		JLabel lblServeraddress = new JLabel("Serveraddress:");
		lblServeraddress.setBounds(10, 193, 89, 14);
		frame.getContentPane().add(lblServeraddress);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(109, 190, 216, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
