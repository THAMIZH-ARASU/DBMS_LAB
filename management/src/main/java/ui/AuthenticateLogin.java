package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class AuthenticateLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private int passwordVisible = 0;
	private JButton showPassButton;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JButton changePassButton;
	private JButton loginButton;
	private JLabel notRegisteredLabel;
	private JButton createAccButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticateLogin frame = new AuthenticateLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthenticateLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 461, 583);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(207, 255, 220));
		contentPane.setBackground(new Color(46, 111, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Welcome to Product Management System");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 11, 425, 62);
		titleLabel.setForeground(new Color(207, 255, 220));
		titleLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(titleLabel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Noto Sans Georgian", Font.BOLD, 34));
		loginLabel.setForeground(new Color(207, 255, 220));
		loginLabel.setBounds(10, 84, 425, 41);
		contentPane.add(loginLabel);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		userTextField.setBounds(31, 212, 382, 41);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		passwordField.setBounds(31, 320, 329, 41);
		contentPane.add(passwordField);
		
		showPassButton = new JButton("");
		showPassButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPassButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		showPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordVisible = 1 - passwordVisible;
				
				if (passwordVisible == 1) {
					passwordField.setEchoChar((char)0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPassButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\eye.png"));
		showPassButton.setBounds(365, 320, 48, 41);
		contentPane.add(showPassButton);
		
		userNameLabel = new JLabel("Username :");
		userNameLabel.setForeground(new Color(207, 255, 220));
		userNameLabel.setBackground(new Color(46, 111, 64));
		userNameLabel.setFont(new Font("Noto Sans", Font.BOLD, 20));
		userNameLabel.setBounds(31, 175, 131, 27);
		contentPane.add(userNameLabel);
		
		passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(new Color(207, 255, 220));
		passwordLabel.setFont(new Font("Noto Sans", Font.BOLD, 20));
		passwordLabel.setBackground(new Color(46, 111, 64));
		passwordLabel.setBounds(31, 287, 131, 27);
		contentPane.add(passwordLabel);
		
		changePassButton = new JButton("Change Password?");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ChangePassword().setVisible(true);
			}
		});
		changePassButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePassButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		changePassButton.setForeground(new Color(207, 255, 220));
		changePassButton.setBackground(new Color(46, 111, 64));
		changePassButton.setBounds(263, 372, 150, 23);
		contentPane.add(changePassButton);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = EncryptPassword.encryptPassword(passwordField.getText());
				
				if((username.equalsIgnoreCase("")) || (password.equalsIgnoreCase(""))) {
					JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
					return;
				}
				
				if(checkLogin(username,password)==true){
		            dispose();
		            new Form().setVisible(true);
		        }else{
		            JOptionPane.showMessageDialog(null, "Invalid username or password");
		        }
			}
		});
		
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setForeground(new Color(207, 255, 220));
		loginButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 20));
		loginButton.setBackground(new Color(37, 61, 44));
		loginButton.setBounds(31, 417, 382, 41);
		contentPane.add(loginButton);
		
		notRegisteredLabel = new JLabel("Not registered yet? ");
		notRegisteredLabel.setFont(new Font("Noto Sans", Font.ITALIC, 15));
		notRegisteredLabel.setBackground(new Color(46, 111, 64));
		notRegisteredLabel.setForeground(new Color(207, 255, 220));
		notRegisteredLabel.setBounds(65, 497, 131, 14);
		contentPane.add(notRegisteredLabel);
		
		createAccButton = new JButton("Create an Account");
		createAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AuthenticateSignup().setVisible(true);
			}
		});
		createAccButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		createAccButton.setForeground(new Color(128, 255, 0));
		createAccButton.setBackground(new Color(46, 111, 64));
		createAccButton.setFont(new Font("Noto Sans", Font.ITALIC, 15));
		createAccButton.setBounds(206, 495, 207, 23);
		contentPane.add(createAccButton);
	}
	
	public boolean checkLogin(String username, String password) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
			stmt = con.createStatement();
			String query = "SELECT * FROM user1 WHERE username=TRIM('" + username + "') AND password=TRIM('" + password + "') FETCH FIRST 1 ROWS ONLY";
	        rs = stmt.executeQuery(query);
            System.out.println("Executed the query : "+ query);
            if (rs.next()) {
                return true;
            }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
   
	}
}


