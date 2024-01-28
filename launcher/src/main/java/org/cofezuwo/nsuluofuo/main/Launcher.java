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
	static String address;

	private enum State {
		WELCOME,
		START,
		LOAD,
		SETTINGS
	}

	private State state;

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
		this.state = State.WELCOME;

		createWindow();

		showGUI();
	}

	private void startGame() {
		//Game.getInstance().start();
		try {
			String jarFile = "/home/julian/nsuluofuo-java/core/target/core-1.0.0-jar-with-dependencies.jar";
			String command = "java -jar " + jarFile;
			Process process = Runtime.getRuntime().exec(command);
			int exitCode = process.waitFor();



			//System.exit(0);
		} catch(Exception e) {
			e.printStackTrace();
		}


		this.frame.dispose();
	}


	private void createWindow() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void showGUI() {
		frame.getContentPane().removeAll();
		frame.repaint();
		if(this.state == State.WELCOME) {
			JButton buttonPlay = new JButton("Play");
			buttonPlay.setBackground(Color.LIGHT_GRAY);
			buttonPlay.setBounds(256, 200, 128, 32);
			buttonPlay.addActionListener(e -> {
				this.state = State.START;
				showGUI();
			});
			frame.getContentPane().add(buttonPlay);
			JButton buttonSettings = new JButton("Settings");
			buttonSettings.setBackground(Color.LIGHT_GRAY);
			buttonSettings.setBounds(256, 248, 128, 32);
			buttonSettings.addActionListener(e -> {
				this.state = State.SETTINGS;
				showGUI();
			});
			frame.getContentPane().add(buttonSettings);

			/*JButton btnLogin = new JButton("Login");
			btnLogin.setForeground(Color.BLACK);
			btnLogin.setBounds(335, 122, 89, 23);
			btnLogin.addActionListener(e -> {
				frame.getContentPane().removeAll();
				frame.repaint();

				JButton btnaa = new JButton("Test");
				btnaa.setBackground(Color.LIGHT_GRAY);

				btnaa.setBounds(335, 189, 89, 23);
				frame.getContentPane().add(btnaa);
			});
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
			textField_1.setColumns(10);*/
		}
		if(this.state == State.START) {
			JButton buttonStart = new JButton("Start");
			buttonStart.setBackground(Color.LIGHT_GRAY);
			buttonStart.setBounds(256, 200, 128, 32);
			buttonStart.addActionListener(e -> {
				startGame();
			});
			frame.getContentPane().add(buttonStart);

			JButton buttonLoad = new JButton("Load");
			buttonLoad.setBackground(Color.LIGHT_GRAY);
			buttonLoad.setBounds(256, 248, 128, 32);
			buttonLoad.addActionListener(e -> {
				this.state = State.LOAD;
				showGUI();
			});
			frame.getContentPane().add(buttonLoad);
		}
		if(this.state == State.LOAD) {

		}
		if(this.state == State.SETTINGS) {

		}



	}
}
