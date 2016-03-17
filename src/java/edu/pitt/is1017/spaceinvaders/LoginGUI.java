package edu.pitt.is1017.spaceinvaders;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 * 
 * @author Micah Calamosca
 *
 */
public class LoginGUI extends JFrame {
	private JLabel lblEmail, lblPassword;
	private JTextField txtEmail, txtPassword;
	private JButton btnRegister, btnLogin;
	private JPanel pnlMain;


	public LoginGUI() {
		initComponents();
	}

	public static void main(String[] args) {
		LoginGUI loginGUI = new LoginGUI();
	}
	private void initComponents(){
		setTitle("SpaceInvaders - Login");
		setSize(475,254);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		pnlMain = new JPanel();
		pnlMain.setBounds(0, 0, 466, 222);
		getContentPane().add(pnlMain);
		pnlMain.setLayout(null);

		//Labels
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(37, 37, 38, 19);
		pnlMain.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(37, 94, 66, 19);
		pnlMain.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));

		//TextFields
		txtEmail = new JTextField();
		txtEmail.setBounds(125, 34, 260, 25);
		pnlMain.add(txtEmail);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(125, 91, 260, 25);
		pnlMain.add(txtPassword);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setColumns(10);

		//Buttons
		btnRegister = new JButton("Register");
		btnRegister.setBounds(37, 155, 97, 25);
		pnlMain.add(btnRegister);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));

		btnLogin = new JButton("Login");
		btnLogin.setBounds(179, 155, 104, 25);
		pnlMain.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(331, 155, 97, 25);
		pnlMain.add(btnCancel);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		//Set enter key to default 

		pnlMain.getRootPane().setDefaultButton(btnLogin);

		//Button Action Listeners
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegisterActionPerformed(e);
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelActionPerformed(e);
			}
		});

		//add to layout

	}

	private void btnLoginActionPerformed(ActionEvent evt){
		if(txtEmail.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Please enter an email");
		else if(txtPassword.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Please enter a password");
		else {
			User user = new User(txtEmail.getText(),txtPassword.getText());

			if(user.getFirstName() != null){
				dispose();
				Thread t = new Thread("gameLaunchThread"){
					public void run(){
						Game g = new Game(user);
						g.gameLoop();
					}
				};

				t.start();

			}
		}
	}

	private void btnCancelActionPerformed(ActionEvent evt){
		System.exit(0);
	}

	private void btnRegisterActionPerformed(ActionEvent evt){
		RegisterGUI registerGUI = new RegisterGUI();
	}
}
