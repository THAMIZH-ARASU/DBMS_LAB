package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AuthenticateSignup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private int passwordVisible = 0;
	private JButton showPassButton;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JButton signInButton;
	private JLabel HaveAccLabel;
	private JButton loginButton;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticateSignup frame = new AuthenticateSignup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AuthenticateSignup() {
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
		
		JLabel signUpLabel = new JLabel("SIGNUP");
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setFont(new Font("Noto Sans Georgian", Font.BOLD, 34));
		signUpLabel.setForeground(new Color(207, 255, 220));
		signUpLabel.setBounds(71, 84, 353, 41);
		contentPane.add(signUpLabel);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		userTextField.setBounds(31, 212, 382, 41);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("The password policy is:\r\n\r\nAt least 8 chars\r\nContains at least one digit\r\nContains at least one lower alpha char and one upper alpha char\r\nContains at least one char within a set of special chars (@#%$^ etc.)\r\nDoes not contain space, tab, etc.");
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
		showPassButton.setIcon(new ImageIcon(AuthenticateSignup.class.getResource("/assets/eye.png")));
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
		
		signInButton = new JButton("SignUp");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = passwordField.getText();
				String username = userTextField.getText();
				String password = EncryptPassword.encryptPassword(pass);
				if((username.equalsIgnoreCase("")) || (pass.equalsIgnoreCase(""))) {
					JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
					return;
				}
				if(isValidPassword(pass)) {
					/*
					 * 
					 * At least 8 characters long.
					 * Contains at least one digit (0-9).
					 * Contains at least one lowercase letter (a-z).
					 * Contains at least one uppercase letter (A-Z).
					 * Contains at least one special character from the set [@#$%^&+=].
					 */
					if(checkLogin(username,password)==true){
						JOptionPane.showMessageDialog(null, "New User: "+ username +" created!");
			            dispose();
			            new AuthenticateLogin().setVisible(true);
			        }else{
			        	JOptionPane.showMessageDialog(null, username + " is already a registered user. Try a different username");
			        }
				}else {
					JOptionPane.showMessageDialog(null, "Password does not meet the requirements!");
				}
			}

			private boolean isValidPassword(String password) {
				// TODO Auto-generated method stub
				String passwordPattern = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		        return Pattern.matches(passwordPattern, password);
			}
		});
		signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signInButton.setForeground(new Color(207, 255, 220));
		signInButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 20));
		signInButton.setBackground(new Color(37, 61, 44));
		signInButton.setBounds(31, 417, 382, 41);
		contentPane.add(signInButton);
		
		HaveAccLabel = new JLabel("Already have an account?");
		HaveAccLabel.setFont(new Font("Noto Sans", Font.ITALIC, 15));
		HaveAccLabel.setBackground(new Color(46, 111, 64));
		HaveAccLabel.setForeground(new Color(207, 255, 220));
		HaveAccLabel.setBounds(112, 501, 181, 14);
		contentPane.add(HaveAccLabel);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AuthenticateLogin().setVisible(true);
			}
		});
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		loginButton.setForeground(new Color(128, 255, 0));
		loginButton.setBackground(new Color(46, 111, 64));
		loginButton.setFont(new Font("Noto Sans", Font.ITALIC, 15));
		loginButton.setBounds(286, 497, 61, 23);
		contentPane.add(loginButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AuthenticateSignup.class.getResource("/assets/login-icon.png")));
		lblNewLabel.setBounds(130, 87, 46, 48);
		contentPane.add(lblNewLabel);
	}
	
	public boolean checkLogin(String username, String password) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
			stmt = con.createStatement();
			String query = "SELECT * FROM user1 WHERE username=TRIM('" + username + "') FETCH FIRST 1 ROWS ONLY";
	        rs = stmt.executeQuery(query);
            System.out.println("Executed the query : "+ query);
            if (rs.next()) {
                return false;
            }else {
            	query = "INSERT INTO user1 (username, password) values('"+ username +"', '"+ password +"')";
            	stmt.executeQuery(query);
            	return true;
            }
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
