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

public class ChangePassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField oldPasswordField;
	private int passwordVisible = 0;
	private int oldPasswordVisible = 0;
	private JButton showOldPassButton;
	private JLabel userNameLabel;
	private JLabel oldPassLabel;
	private JButton changePassButton;
	private JButton loginButton;
	private JLabel newPassLabel;
	private JPasswordField newPasswordField;
	private JButton showNewPassButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
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
		
		JLabel changePassLabel = new JLabel("CHANGE PASSWORD");
		changePassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changePassLabel.setFont(new Font("Noto Sans Georgian", Font.BOLD, 34));
		changePassLabel.setForeground(new Color(207, 255, 220));
		changePassLabel.setBounds(10, 84, 425, 41);
		contentPane.add(changePassLabel);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		userTextField.setBounds(31, 170, 382, 41);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		oldPasswordField.setBounds(31, 253, 329, 41);
		contentPane.add(oldPasswordField);
		
		showOldPassButton = new JButton("");
		showOldPassButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showOldPassButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		showOldPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldPasswordVisible = 1 - oldPasswordVisible;
				
				if (oldPasswordVisible == 1) {
					oldPasswordField.setEchoChar((char)0);
				}else {
					oldPasswordField.setEchoChar('*');
				}
			}
		});
		showOldPassButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\eye.png"));
		showOldPassButton.setBounds(365, 253, 48, 41);
		contentPane.add(showOldPassButton);
		
		userNameLabel = new JLabel("Username :");
		userNameLabel.setForeground(new Color(207, 255, 220));
		userNameLabel.setBackground(new Color(46, 111, 64));
		userNameLabel.setFont(new Font("Noto Sans", Font.BOLD, 20));
		userNameLabel.setBounds(31, 136, 131, 27);
		contentPane.add(userNameLabel);
		
		oldPassLabel = new JLabel("Old Password :");
		oldPassLabel.setForeground(new Color(207, 255, 220));
		oldPassLabel.setFont(new Font("Noto Sans", Font.BOLD, 20));
		oldPassLabel.setBackground(new Color(46, 111, 64));
		oldPassLabel.setBounds(31, 222, 186, 27);
		contentPane.add(oldPassLabel);
		
		changePassButton = new JButton("Change Password");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String oldPassword = EncryptPassword.encryptPassword(oldPasswordField.getText());
				String newPassword = EncryptPassword.encryptPassword(newPasswordField.getText());
				
				if((username.equalsIgnoreCase("")) || (oldPassword.equalsIgnoreCase("")) || (newPasswordField.getText().equalsIgnoreCase(""))) {
					JOptionPane.showMessageDialog(null, "Username, Old Password or New Password cannot be empty");
					return;
				}
				
				if(checkOldPassword(username,oldPassword)==true){
					System.out.println("Old password matched successfully");
					if(isValidPassword(newPasswordField.getText())) {
						setNewPassword(username, newPassword);
						JOptionPane.showMessageDialog(null, "Password changed successfully");
			            dispose();
			            new AuthenticateLogin().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "New password does not meet the requirements!");
					}
		        }else{
		        	JOptionPane.showMessageDialog(null, "The Entered old password is invalid!");
		        }
				
				
			}
			
			private boolean isValidPassword(String password) {
				// TODO Auto-generated method stub
				String passwordPattern = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		        return Pattern.matches(passwordPattern, password);
			}

			private void setNewPassword(String username, String newPassword) {
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				 
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
					stmt = con.createStatement();
					String query = "UPDATE user1 SET password ='"+newPassword+"' WHERE username=TRIM('" + username + "')";
			        rs = stmt.executeQuery(query);
		            System.out.println("Executed the query : "+ query);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			private boolean checkOldPassword(String username, String passsword) {
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
		            	String oldPass = rs.getString("password");
		            	if (oldPass.equals(passsword)) {
		            		return true;
		            	}
		            }
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
		});
		changePassButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePassButton.setForeground(new Color(207, 255, 220));
		changePassButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 20));
		changePassButton.setBackground(new Color(37, 61, 44));
		changePassButton.setBounds(31, 417, 382, 41);
		contentPane.add(changePassButton);
		
		loginButton = new JButton("Back?");
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
		loginButton.setBounds(352, 497, 61, 23);
		contentPane.add(loginButton);
		
		newPassLabel = new JLabel("New Password :");
		newPassLabel.setForeground(new Color(207, 255, 220));
		newPassLabel.setFont(new Font("Noto Sans", Font.BOLD, 20));
		newPassLabel.setBackground(new Color(46, 111, 64));
		newPassLabel.setBounds(31, 305, 162, 27);
		contentPane.add(newPassLabel);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		newPasswordField.setBounds(31, 336, 329, 41);
		contentPane.add(newPasswordField);
		
		showNewPassButton = new JButton("");
		showNewPassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordVisible = 1 - passwordVisible;
				
				if (passwordVisible == 1) {
					newPasswordField.setEchoChar((char)0);
				}else {
					newPasswordField.setEchoChar('*');
				}
			}
		});
		showNewPassButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\eye.png"));
		showNewPassButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		showNewPassButton.setBounds(365, 336, 48, 41);
		contentPane.add(showNewPassButton);
	}

}
