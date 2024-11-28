package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class UpdateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField existingTextField;
	private JTextField newTextField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Load Oracle JDBC Driver
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            // Connect to Oracle database
		            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
		            System.out.println("Connection Tested");
					UpdateFrame frame = new UpdateFrame("5", conn);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 * @param conn 
	 * @param string 
	 */
	public UpdateFrame(String pid, Connection conn) {
		System.out.println("UpdateFrameCreated");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 619, 329);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(207, 255, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel updateTitle = new JLabel("UPDATE AN ATTRIBUTE");
		updateTitle.setFont(new Font("Noto Sans", Font.BOLD, 26));
		updateTitle.setBounds(176, 21, 298, 29);
		contentPane.add(updateTitle);
		
		
		JLabel selectAttributeLabel = new JLabel("Select an Attribute:");
		selectAttributeLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		selectAttributeLabel.setBounds(37, 76, 182, 21);
		contentPane.add(selectAttributeLabel);
		
		JLabel existingLabel = new JLabel("Existing Value:");
		existingLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		existingLabel.setBounds(37, 119, 182, 21);
		contentPane.add(existingLabel);
		
		JLabel newValueLabel = new JLabel("New Value:");
		newValueLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		newValueLabel.setBounds(37, 168, 182, 21);
		contentPane.add(newValueLabel);
		
		JComboBox attributeComboBox = new JComboBox();
		attributeComboBox.setModel(new DefaultComboBoxModel(new String[] {"product_name", "category", "brand", "model", "specification", "price", "available_stock", "is_trending"}));
		attributeComboBox.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		attributeComboBox.setBounds(229, 70, 277, 29);
		contentPane.add(attributeComboBox);
		
		existingTextField = new JTextField();
		existingTextField.setEditable(false);
		existingTextField.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		existingTextField.setBounds(229, 117, 277, 29);
		contentPane.add(existingTextField);
		existingTextField.setColumns(10);
		
		newTextField = new JTextField();
		newTextField.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		newTextField.setColumns(10);
		newTextField.setBounds(229, 166, 277, 29);
		contentPane.add(newTextField);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		cancelButton.setBounds(133, 219, 118, 43);
		contentPane.add(cancelButton);
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((existingTextField.getText().equalsIgnoreCase("")) || (newTextField.getText().equalsIgnoreCase(""))) {
					JOptionPane.showMessageDialog(null, "Both Existing and New values must present\nPlease press the refresh button and try again");
				}else {
					String attribute = (String) attributeComboBox.getSelectedItem();
					String newvalue = newTextField.getText();
					String query = "UPDATE product1 SET "+ attribute +" = '" + newvalue + "' WHERE product_id = "+ pid + "";
					PreparedStatement stmt = null;
					try {
						stmt = conn.prepareStatement(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ResultSet rs = null;
		            try {
						rs = stmt.executeQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            JOptionPane.showMessageDialog(null, "Attribute updated successfuly");
				}

			}
		});
		updateButton.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		updateButton.setBounds(313, 219, 118, 43);
		contentPane.add(updateButton);
		
		JButton refreshButton = new JButton("");
		refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attribute = (String) attributeComboBox.getSelectedItem();
				String query = "SELECT "+ attribute +" FROM product1 WHERE product_id = "+ pid +"";
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs = null;
	            try {
					rs = stmt.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					if(!(rs.next())) {
						JOptionPane.showMessageDialog(null, "No record found with product ID: "+pid);
					}else {
						String existing = rs.getString(attribute);
						existingTextField.setText(existing);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\reload.png"));
		refreshButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		refreshButton.setBounds(516, 119, 60, 27);
		contentPane.add(refreshButton);
	}

}
