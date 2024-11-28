package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextField;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable itemTable;
	private JTextField productNameTextField;
	private JTextField modelTextField;
	private JTextField priceTextField;
	private JTextField availabletextField;
	private Connection conn = null;
	private JTextField searchTextField;
	private JComboBox pidComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	public Form() {
		// Load Oracle JDBC Driver
        try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Connect to Oracle database
        try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Connection Tested");
        
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\productSmall.png"));
		setTitle("Inventory Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1456, 693);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(207, 255, 220));
		contentPane.setFont(new Font("Noto Sans", Font.BOLD, 11));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(new Color(37, 61, 44));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setForeground(new Color(207, 255, 220));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setFont(new Font("Noto Sans", Font.BOLD, 11));
		scrollPane.setBackground(new Color(207, 255, 220));
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(759, 11, 671, 585);
		contentPane.add(scrollPane);
		
		itemTable = new JTable();
		itemTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		scrollPane.setViewportView(itemTable);
		itemTable.setModel(new DefaultTableModel(
		    new Object[][] {
		        {"1", "sadf", "sdf", "asdf", "sadf", "sdf", "safd", "sdf", "23", "asdf"},
		        {"2", "asdf", "asdf", "asdf", "asf", "asfd", "asdf", "asdf", "23", "sadf"},
		        {"3", "fddfd", "asdf", "asdf", "sadf", "sadf", "asdf", "sadf", "23", "null"},
		    },
		    new String[] {
		        "ID", "Name", "Category", "Brand", "Model", "Specification", "Price", "In Date", "Available Stock", "Is Trendy"
		    }
		) {
		    private static final long serialVersionUID = 1L;
		    @SuppressWarnings("rawtypes")
		    Class[] columnTypes = new Class[] {
		        String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
		    };

		    @Override
		    @SuppressWarnings({ "rawtypes", "unchecked" })
		    public Class getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Disable editing
		    }
		});

		itemTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		itemTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		itemTable.getColumnModel().getColumn(2).setPreferredWidth(69);
		itemTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		itemTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		itemTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		itemTable.getColumnModel().getColumn(7).setPreferredWidth(85);
		itemTable.getColumnModel().getColumn(8).setResizable(false);
		itemTable.getColumnModel().getColumn(8).setPreferredWidth(58);
		itemTable.setBackground(new Color(207, 255, 220));
		itemTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		itemTable.setFont(new Font("Noto Sans Lisu", Font.BOLD, 11));
		
		
		loadTableData();
		
		
		
		
		JLabel titlelabel = new JLabel("Product Management");
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlelabel.setFont(new Font("Noto Sans", Font.BOLD | Font.ITALIC, 30));
		titlelabel.setBounds(177, 11, 407, 37);
		contentPane.add(titlelabel);
		
		
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(104, 186, 127));
		formPanel.setBounds(10, 58, 739, 585);
		contentPane.add(formPanel);
		formPanel.setLayout(null);
		
		JLabel productNameLabel = new JLabel("Prodcut Name                :");
		productNameLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		productNameLabel.setBounds(23, 24, 224, 28);
		formPanel.add(productNameLabel);
		
		JLabel categroyLabel = new JLabel("Category                          :");
		categroyLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		categroyLabel.setBounds(23, 61, 224, 28);
		formPanel.add(categroyLabel);
		
		JLabel brandLabel = new JLabel("Brand                               :");
		brandLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		brandLabel.setBounds(23, 100, 224, 28);
		formPanel.add(brandLabel);
		
		JLabel modelLabel = new JLabel("Model                               :");
		modelLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		modelLabel.setBounds(23, 139, 224, 28);
		formPanel.add(modelLabel);
		
		JLabel specificationLabel = new JLabel("Specification                   :");
		specificationLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		specificationLabel.setBounds(23, 178, 224, 28);
		formPanel.add(specificationLabel);
		
		pidComboBox = new JComboBox();
		
		pidComboBox.setEditable(true);
		pidComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		pidComboBox.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 18));
		pidComboBox.setBounds(656, 10, 73, 22);
		formPanel.add(pidComboBox);
		
		productNameTextField = new JTextField();
		productNameTextField.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 16));
		productNameTextField.setBounds(257, 26, 354, 26);
		formPanel.add(productNameTextField);
		productNameTextField.setColumns(10);
		
		loadComboData();
		
		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setFont(new Font("Noto Sans Lisu", Font.BOLD | Font.ITALIC, 16));
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"Mobile", "Laptop", "Tablet", "Accessories"}));
		categoryComboBox.setBounds(257, 61, 354, 33);
		formPanel.add(categoryComboBox);
		
		JComboBox brandComboBox = new JComboBox();
		brandComboBox.setModel(new DefaultComboBoxModel(new String[] {"Apple", "Samsung", "Sony", "Dell", "Lenovo"}));
		brandComboBox.setFont(new Font("Noto Sans Lisu", Font.BOLD | Font.ITALIC, 16));
		brandComboBox.setEditable(true);
		brandComboBox.setBounds(257, 100, 354, 33);
		formPanel.add(brandComboBox);
		
		modelTextField = new JTextField();
		modelTextField.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 16));
		modelTextField.setColumns(10);
		modelTextField.setBounds(257, 139, 354, 26);
		formPanel.add(modelTextField);
		
		JTextArea specificationTextArea = new JTextArea();
		specificationTextArea.setLineWrap(true);
		specificationTextArea.setAutoscrolls(false);
		specificationTextArea.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 16));
		specificationTextArea.setBounds(257, 178, 354, 146);
		formPanel.add(specificationTextArea);
		
		JLabel priceLabel = new JLabel("Price                                 :");
		priceLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		priceLabel.setBounds(23, 337, 224, 28);
		formPanel.add(priceLabel);
		
		JLabel availableLabel = new JLabel("Available Stock               :");
		availableLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		availableLabel.setBounds(23, 376, 224, 28);
		formPanel.add(availableLabel);
		
		JLabel trendyLabel = new JLabel("Is Trending                      :");
		trendyLabel.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		trendyLabel.setBounds(23, 415, 224, 28);
		formPanel.add(trendyLabel);
		
		priceTextField = new JTextField();
		priceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!Character.isDigit(keyChar)) {
			        // The pressed key is not a number
					JOptionPane.showMessageDialog(null, "Only numeric vlaues allowed in this field");
			        System.out.println("Non-numeric key pressed: " + keyChar);
			        priceTextField.setText("");
			    }
			}
		});
		priceTextField.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 16));
		priceTextField.setColumns(10);
		priceTextField.setBounds(257, 335, 354, 26);
		formPanel.add(priceTextField);
		
		availabletextField = new JTextField();
		availabletextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!Character.isDigit(keyChar)) {
			        // The pressed key is not a number
					JOptionPane.showMessageDialog(null, "Only numeric vlaues allowed in this field");
			        System.out.println("Non-numeric key pressed: " + keyChar);
			        availabletextField.setText("");
			    }
			}
		});
		availabletextField.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 16));
		availabletextField.setColumns(10);
		availabletextField.setBounds(257, 378, 354, 26);
		formPanel.add(availabletextField);
		
		JRadioButton YesRadioButton = new JRadioButton("Yes");
		YesRadioButton.setBackground(new Color(104, 186, 127));
		YesRadioButton.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 16));
		YesRadioButton.setBounds(262, 420, 109, 23);
		formPanel.add(YesRadioButton);
		
		JRadioButton noRadioButton = new JRadioButton("No");
		noRadioButton.setSelected(true);
		noRadioButton.setFont(new Font("Noto Sans Arabic", Font.BOLD | Font.ITALIC, 16));
		noRadioButton.setBackground(new Color(104, 186, 127));
		noRadioButton.setBounds(383, 420, 109, 23);
		formPanel.add(noRadioButton);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(YesRadioButton);
		bg.add(noRadioButton);
		
		JButton previousButton = new JButton("");
		previousButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		previousButton.setForeground(new Color(104, 186, 127));
		previousButton.setBackground(new Color(240, 240, 240));
		previousButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\left-arrow.png"));
		previousButton.setBounds(10, 541, 89, 33);
		formPanel.add(previousButton);
		
		JButton nextButton = new JButton("");
		nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		nextButton.setIcon(new ImageIcon("C:\\PEC-26\\5th SEM\\DBMS_LAB\\eclipse_workspace_inventory\\DuplicatedInventoryJar\\src\\main\\java\\ui\\images\\right-arrow.png"));
		nextButton.setForeground(new Color(104, 186, 127));
		nextButton.setBackground(new Color(240, 240, 240));
		nextButton.setBounds(640, 541, 89, 33);
		formPanel.add(nextButton);
		
		JButton insertButton = new JButton("INSERT");
		insertButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		insertButton.setBackground(new Color(240, 240, 240));
		insertButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		insertButton.setBounds(19, 480, 124, 33);
		formPanel.add(insertButton);
		
		JButton clearButton = new JButton("CLEAR");
		clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		clearButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		clearButton.setBackground(new Color(240, 240, 240));
		clearButton.setBounds(162, 480, 124, 33);
		formPanel.add(clearButton);
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		updateButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		updateButton.setBackground(new Color(240, 240, 240));
		updateButton.setBounds(305, 480, 124, 33);
		formPanel.add(updateButton);
		
		TextField pidTextField = new TextField();
		pidTextField.setVisible(false);
		pidTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String pid = pidTextField.getText();
				if (pid.equalsIgnoreCase("0")) {
					return;
				}
				String query = "SELECT * FROM product1 WHERE product_id = "+pid+"";
				Statement stmt = null;
				ResultSet rs = null;
				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery(query);
					System.out.println("------------");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(!rs.next()) {
						System.out.println("No record found with product ID: " + pidTextField.getText());
						productNameTextField.setText("");
						categoryComboBox.setSelectedItem("");
						brandComboBox.setSelectedItem("");
						modelTextField.setText("");
						specificationTextArea.setText("");
						priceTextField.setText("");
						availabletextField.setText("");
						noRadioButton.doClick();
					}
					else {
						String name = rs.getString("product_name");
			            String category = rs.getString("category");
			            String brand = rs.getString("brand");
			            String p_model = rs.getString("model");
			            String specification = rs.getString("specification");
			            String price = rs.getString("price");
			            String inDate = rs.getString("in_date");
			            String availableStock = rs.getString("available_stock");
			            String isTrendy = rs.getString("is_trending");
			            System.out.println(name+" "+category+" "+brand+" "+p_model+" "+specification+" "+price+" "+inDate+" "+availableStock+" "+isTrendy);
			            
						productNameTextField.setText(name);
						categoryComboBox.setSelectedItem(category);
						brandComboBox.setSelectedItem(brand);
						modelTextField.setText(p_model);
						specificationTextArea.setText(specification);
						priceTextField.setText(price);
						availabletextField.setText(availableStock);
						if(isTrendy.equalsIgnoreCase("Yes")) {
							YesRadioButton.doClick();
						}else {
							noRadioButton.doClick();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		pidTextField.setBounds(656, 10, 73, 22);
		pidTextField.setVisible(true);
		formPanel.add(pidTextField);
		
		JLabel committedBit = new JLabel("");
		committedBit.setFont(new Font("Noto Sans Lisu", Font.BOLD | Font.ITALIC, 14));
		committedBit.setBounds(342, 541, 46, 33);
		formPanel.add(committedBit);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pid = String.valueOf(pidComboBox.getSelectedItem());
				String name = productNameTextField.getText();
		        String category = (String) categoryComboBox.getSelectedItem();
		        String brand = (String) brandComboBox.getSelectedItem();
		        String p_model = modelTextField.getText();
		        String specification = specificationTextArea.getText();
		        String price = priceTextField.getText();
		        String available = availabletextField.getText();
		        String istrendy = getSelectedButtonText(bg);
		        String comBit = committedBit.getText();
		        
		        if ((pid.equalsIgnoreCase("")) || (name.equalsIgnoreCase("")) || (category.equalsIgnoreCase("")) || (brand.equalsIgnoreCase("")) || (specification.equalsIgnoreCase("")) || (p_model.equalsIgnoreCase("")) || (price.equalsIgnoreCase("")) || (available.equalsIgnoreCase(""))) {
		        	JOptionPane.showMessageDialog(null, "All the fields must be filled");
		        	return;
		        }
		        // Check if the record already exists in the table
		        String checkQuery = "SELECT * FROM product1 WHERE product_id = ?";
		        try {
		            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
		            checkStmt.setString(1, pid);
		            ResultSet rs = checkStmt.executeQuery();
		            if (rs.next()) {
		                // Update existing record
		                String updateQuery = "UPDATE product1 SET product_name = '"+ name + "' ,category = '"+ category +"' ,brand = '"+ brand +"' ,model = '"+ p_model +"' ,"
		                		+ "specification = '"+specification+"' ,price = "+ price +" ,available_stock = "+ available +" ,is_trending = '"+ istrendy +"', committed = 'No' WHERE product_id ="+ pid +"";
		                System.out.println(updateQuery);
		                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
		                updateStmt.executeUpdate();
		            } else {
		                // Insert new record
		                String insertQuery = "INSERT INTO product1 (product_name, category, brand, model, specification, price, available_stock, is_trending, committed) VALUES ('"+ name +"', '"+category+"', "
		                		+ "'"+ brand +"', '"+p_model+"', '"+specification+"', "+price+", "+available+" ,'"+istrendy+"', 'No')";
		                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
		                insertStmt.executeUpdate();
		            }
		            System.out.println("Save Successful");
		            JOptionPane.showMessageDialog(null, "Record saved to Buffer successfully");
		            loadTableData();  // Refresh table after insert/update
		            loadComboData();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		saveButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		saveButton.setBackground(new Color(240, 240, 240));
		saveButton.setBounds(448, 480, 124, 33);
		formPanel.add(saveButton);
		
		JButton commitButton = new JButton("COMMIT");
		
		
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("0")) {
					JOptionPane.showMessageDialog(null, "Choose a record or Enter a Product ID");
					return;
				}
				Integer pid = Integer.parseInt(String.valueOf(pidComboBox.getSelectedItem()));
				
				if(checkExistence(pid)) {
					;
				}else {
					JOptionPane.showMessageDialog(null, "No Record Found!");
				}
			}

			private boolean checkExistence(Integer pid) {
				// TODO Auto-generated method stub
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				 
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
					stmt = con.createStatement();
					String query = "SELECT * FROM product1 WHERE product_id = "+ (pid + 1) +"";
			        rs = stmt.executeQuery(query);
		            System.out.println("Executed the query : "+ query);
		            if(!rs.next()) {
		            	return false;
		            }
		            else {
		            	System.out.println("-------------------");
		            	String name = rs.getString("product_name");
			            String category = rs.getString("category");
			            String brand = rs.getString("brand");
			            String p_model = rs.getString("model");
			            String specification = rs.getString("specification");
			            String price = rs.getString("price");
			            String inDate = rs.getString("in_date");
			            String availableStock = rs.getString("available_stock");
			            String isTrendy = rs.getString("is_trending");
			            String comBit = rs.getString("committed");

			            System.out.println(name+" "+category+" "+brand+" "+p_model+" "+specification+" "+price+" "+inDate+" "+availableStock+" "+isTrendy);
			            
			            committedBit.setText(comBit);
			            pidTextField.setText(""+(pid + 1));
			            pidComboBox.setSelectedItem(pid + 1);
			            productNameTextField.setText(name);
						categoryComboBox.setSelectedItem(category);
						brandComboBox.setSelectedItem(brand);
						modelTextField.setText(p_model);
						specificationTextArea.setText(specification);
						priceTextField.setText(price);
						availabletextField.setText(availableStock);
						if(isTrendy.equalsIgnoreCase("Yes")) {
							YesRadioButton.doClick();
						}else {
							noRadioButton.doClick();
						}
						
						if (committedBit.getText().equalsIgnoreCase("Yes")) {
			            	commitButton.setEnabled(false);
			            }else {
			            	commitButton.setEnabled(true);
			            }
		            }
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		});
		
		
		pidComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String pid = String.valueOf(pidComboBox.getSelectedItem());
				if(pid == null) {
					return;
				}
				if (pid.equalsIgnoreCase("0") || pid.equalsIgnoreCase("") || pidComboBox.getSelectedIndex() == 0) {
					committedBit.setText("");
					productNameTextField.setText("");
					categoryComboBox.setSelectedItem("");
					brandComboBox.setSelectedItem("");
					modelTextField.setText("");
					specificationTextArea.setText("");
					priceTextField.setText("");
					availabletextField.setText("");
					noRadioButton.doClick();
					return;
				}
				String query = "SELECT * FROM product1 WHERE product_id = "+pid+"";
				Statement stmt = null;
				ResultSet rs = null;
				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery(query);
					//System.out.println("------------");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(!rs.next()) {
						System.out.println(pidComboBox.getSelectedItem());
						if (pidComboBox.getSelectedItem() == null) {
							System.out.println(pidComboBox.getSelectedIndex());
							return;
						}
						JOptionPane.showMessageDialog(null, "No record found with product ID: " + String.valueOf(pidComboBox.getSelectedItem()));
						
						committedBit.setText("");
						productNameTextField.setText("");
						categoryComboBox.setSelectedItem("");
						brandComboBox.setSelectedItem("");
						modelTextField.setText("");
						specificationTextArea.setText("");
						priceTextField.setText("");
						availabletextField.setText("");
						noRadioButton.doClick();
						return;
					}
					else {
						String name = rs.getString("product_name");
			            String category = rs.getString("category");
			            String brand = rs.getString("brand");
			            String p_model = rs.getString("model");
			            String specification = rs.getString("specification");
			            String price = rs.getString("price");
			            String inDate = rs.getString("in_date");
			            String availableStock = rs.getString("available_stock");
			            String isTrendy = rs.getString("is_trending");
			            String comBit = rs.getString("committed");
			            System.out.println(name+" "+category+" "+brand+" "+p_model+" "+specification+" "+price+" "+inDate+" "+availableStock+" "+isTrendy);
			            
						productNameTextField.setText(name);
						categoryComboBox.setSelectedItem(category);
						brandComboBox.setSelectedItem(brand);
						modelTextField.setText(p_model);
						specificationTextArea.setText(specification);
						priceTextField.setText(price);
						availabletextField.setText(availableStock);
						if(isTrendy.equalsIgnoreCase("Yes")) {
							YesRadioButton.doClick();
						}else {
							noRadioButton.doClick();
						}
						committedBit.setText(comBit);
						
						if(committedBit.getText().equalsIgnoreCase("Yes")) {
							commitButton.setEnabled(false);
						}else {
							commitButton.setEnabled(true);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		commitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		commitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		commitButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String pid = String.valueOf(pidComboBox.getSelectedItem());
		        if (pid.equalsIgnoreCase("0")){
		        	JOptionPane.showMessageDialog(null, "No Record Selected");
		        }else {
		        	if(committedBit.getText().equalsIgnoreCase("Yes")) {
		        		JOptionPane.showMessageDialog(null, "Record already committed to Main Database");
		        		return;
		        	}
		        	// Check if the record already exists in the product2 table
			        String mergeQuery = "MERGE INTO product2 p2 " +
			                            "USING (SELECT * FROM product1 WHERE product_id = "+ pid +") p1 " +
			                            "ON (p2.product_id = p1.product_id) " +
			                            "WHEN MATCHED THEN " +
			                            "UPDATE SET p2.product_name = p1.product_name, " +
			                            "p2.category = p1.category, " +
			                            "p2.brand = p1.brand, " +
			                            "p2.model = p1.model, " +
			                            "p2.specification = p1.specification, " +
			                            "p2.price = p1.price, " +
			                            "p2.available_stock = p1.available_stock, " +
			                            "p2.is_trending = p1.is_trending " +
			                            "WHEN NOT MATCHED THEN " +
			                            "INSERT (product_id, product_name, category, brand, model, specification, price, available_stock, is_trending) " +
			                            "VALUES (p1.product_id, p1.product_name, p1.category, p1.brand, p1.model, p1.specification, p1.price, p1.available_stock, p1.is_trending)";
			        String updateQuery = "UPDATE product1 SET committed = 'Yes' WHERE product_id = "+ pid +"";
			        
			        try {
			            // Execute the merge query
			            PreparedStatement mergeStmt = conn.prepareStatement(mergeQuery);
			            mergeStmt.executeUpdate();
			            
			            System.out.println("Commit Successful");
			            JOptionPane.showMessageDialog(null, "Record saved to Main DB successfully");
			            
			            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			            updateStmt.executeUpdate();
			            System.out.println("Committed bit updated!");
			            
			            pidComboBox.setSelectedItem("");
			            committedBit.setText("");
			            productNameTextField.setText("");
						categoryComboBox.setSelectedItem("");
						brandComboBox.setSelectedItem("");
						modelTextField.setText("");
						specificationTextArea.setText("");
						priceTextField.setText("");
						availabletextField.setText("");
						noRadioButton.doClick();
						
			            loadTableData();  // Refresh table after merge
			            loadComboData();
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			        }
		        }
		        
		    }
		});
		
		
		commitButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		commitButton.setBackground(new Color(240, 240, 240));
		commitButton.setBounds(591, 480, 124, 33);
		formPanel.add(commitButton);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		lblId.setBackground(new Color(104, 186, 127));
		lblId.setBounds(621, 10, 32, 23);
		formPanel.add(lblId);
		
		JButton deleteButton = new JButton("DELETE");
		
		deleteButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		deleteButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		deleteButton.setBackground(new Color(240, 240, 240));
		deleteButton.setBounds(448, 541, 124, 33);
		formPanel.add(deleteButton);
		
		JLabel committedLabel = new JLabel("Committed to Main DataBase :");
		committedLabel.setFont(new Font("Noto Sans Lisu", Font.BOLD | Font.ITALIC, 14));
		committedLabel.setBounds(123, 541, 224, 33);
		formPanel.add(committedLabel);
		
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					loadTableData(searchTextField.getText());
				}
			}
		});
		searchTextField.setFont(new Font("Noto Sans Georgian", Font.BOLD | Font.ITALIC, 16));
		searchTextField.setColumns(10);
		searchTextField.setBounds(942, 607, 354, 36);
		contentPane.add(searchTextField);
		
		JButton searchButton = new JButton("SEARCH");
		
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTableData(searchTextField.getText());
			}
		});
		searchButton.setFont(new Font("Noto Sans Lao", Font.BOLD | Font.ITALIC, 18));
		searchButton.setBackground(UIManager.getColor("Button.background"));
		searchButton.setBounds(1306, 607, 124, 33);
		contentPane.add(searchButton);
		
		JButton refreshButton = new JButton("");
		refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTextField.setText("");
				loadTableData();
				pidComboBox.setSelectedIndex(0);
				//loadComboData();
				if (!(String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("0"))) {
					String query = "SELECT * FROM product1 WHERE product_id = "+(String.valueOf(pidComboBox.getSelectedItem()))+"";
					Statement stmt = null;
					ResultSet rs = null;
					try {
						stmt = conn.createStatement();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						rs = stmt.executeQuery(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if(!rs.next()) {
							String pid = String.valueOf(pidComboBox.getSelectedItem());
							if(pid == null) {
								return;
							}
							System.out.println("No record found with product ID: " + pid);
						}
						else {
							while(rs.next()) {
								String pid = rs.getString("product_id");
					            String name = rs.getString("product_name");
					            String category = rs.getString("category");
					            String brand = rs.getString("brand");
					            String p_model = rs.getString("model");
					            String specification = rs.getString("specification");
					            String price = rs.getString("price");
					            String inDate = rs.getString("in_date");
					            String availableStock = rs.getString("available_stock");
					            String isTrendy = rs.getString("is_trending");
					            String comBit = rs.getString("committed");
					            
					            committedBit.setText(comBit);
								productNameTextField.setText(name);
								categoryComboBox.setSelectedItem(category);
								brandComboBox.setSelectedItem(brand);
								modelTextField.setText(p_model);
								specificationTextArea.setText(specification);
								priceTextField.setText(price);
								availabletextField.setText(availableStock);
								if(isTrendy.equalsIgnoreCase("Yes")) {
									YesRadioButton.doClick();
								}else {
									noRadioButton.doClick();
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					committedBit.setText("");
				}
			}
		});
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (productNameTextField.isEnabled()) {
					committedBit.setText("");
					pidTextField.setEnabled(false);
					pidComboBox.setEnabled(false);
					productNameTextField.setEnabled(false);
					categoryComboBox.setEnabled(false);
					brandComboBox.setEnabled(false);
					modelTextField.setEnabled(false);
					specificationTextArea.setEnabled(false);
					priceTextField.setEnabled(false);
					availabletextField.setEnabled(false);
					YesRadioButton.setEnabled(false);
					noRadioButton.setEnabled(false);
				}else {
					pidTextField.setEnabled(true);
					pidComboBox.setEnabled(true);
					productNameTextField.setEnabled(true);
					categoryComboBox.setEnabled(true);
					brandComboBox.setEnabled(true);
					modelTextField.setEnabled(true);
					specificationTextArea.setEnabled(true);
					priceTextField.setEnabled(true);
					availabletextField.setEnabled(true);
					YesRadioButton.setEnabled(true);
					noRadioButton.setEnabled(true);
				}
			}
		});
		
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("0"))) {
					JOptionPane.showMessageDialog(null, "Choose a record or Enter a Product ID");
					return;
				}
				Integer pid = Integer.parseInt((String.valueOf(pidComboBox.getSelectedItem())));
				
				if(checkExistence(pid)) {
					;
				}else {
					JOptionPane.showMessageDialog(null, "No Record Found!");
				}
			}

			private boolean checkExistence(Integer pid) {
				// TODO Auto-generated method stub
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
				 
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "thamizh", "thamizh");
					stmt = con.createStatement();
					String query = "SELECT * FROM product1 WHERE product_id = "+ (pid - 1) +"";
			        rs = stmt.executeQuery(query);
		            System.out.println("Executed the query : "+ query);
		            if(!rs.next()) {
		            	return false;
		            }
		            else {
		            	System.out.println("-------------------");
		            	String name = rs.getString("product_name");
			            String category = rs.getString("category");
			            String brand = rs.getString("brand");
			            String p_model = rs.getString("model");
			            String specification = rs.getString("specification");
			            String price = rs.getString("price");
			            String inDate = rs.getString("in_date");
			            String availableStock = rs.getString("available_stock");
			            String isTrendy = rs.getString("is_trending");
			            String comBit = rs.getString("committed");

			            System.out.println(name+" "+category+" "+brand+" "+p_model+" "+specification+" "+price+" "+inDate+" "+availableStock+" "+isTrendy);
			            
			            committedBit.setText(comBit);
			            pidTextField.setText(""+(pid - 1));
			            pidComboBox.setSelectedItem(pid - 1);
			            productNameTextField.setText(name);
						categoryComboBox.setSelectedItem(category);
						brandComboBox.setSelectedItem(brand);
						modelTextField.setText(p_model);
						specificationTextArea.setText(specification);
						priceTextField.setText(price);
						availabletextField.setText(availableStock);
						if(isTrendy.equalsIgnoreCase("Yes")) {
							YesRadioButton.doClick();
						}else {
							noRadioButton.doClick();
						}
			            
			            if (committedBit.getText().equalsIgnoreCase("Yes")) {
			            	commitButton.setEnabled(false);
			            }else {
			            	commitButton.setEnabled(true);
			            }
		            }
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		});
		
		refreshButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		refreshButton.setIcon(new ImageIcon(Form.class.getResource("/assets/reload.png")));
		refreshButton.setBounds(759, 607, 60, 36);
		contentPane.add(refreshButton);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AuthenticateLogin().setVisible(true);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Noto Sans Arabic", Font.BOLD, 18));
		btnNewButton.setIcon(new ImageIcon(Form.class.getResource("/assets/logout.png")));
		btnNewButton.setBounds(10, 11, 144, 41);
		contentPane.add(btnNewButton);
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pidTextField.setText("");
				pidComboBox.setSelectedItem(0);
				productNameTextField.setText("");
				categoryComboBox.setSelectedIndex(0);
				brandComboBox.setSelectedIndex(0);
				modelTextField.setText("");
				specificationTextArea.setText("");
				priceTextField.setText("");
				availabletextField.setText("");
				noRadioButton.doClick();
				
			}
			
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("0")) {
					JOptionPane.showMessageDialog(null, "Please Select a Record");
				}else {
					new UpdateFrame(String.valueOf(pidComboBox.getSelectedItem()), conn).setVisible(true);
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pidComboBox.getSelectedIndex() == 0 || String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("") || String.valueOf(pidComboBox.getSelectedItem()).equalsIgnoreCase("0")) {
					JOptionPane.showMessageDialog(null, "No record selected!");
					return;
				}
				// Check if the record already exists in the product2 table
		        String deleteBufferQuery = "DELETE product1 WHERE product_id = "+ pidComboBox.getSelectedItem() +"";
		        String deleteMainQuery = "DELETE FROM product2 WHERE product_id = " + pidComboBox.getSelectedItem() + " AND EXISTS (SELECT 1 FROM product2 WHERE product_id = " + pidComboBox.getSelectedItem() + ")";

		        int dialogResult = JOptionPane.showConfirmDialog(
		        	    				null,
		        	    				"Are you sure that you want to delete the record with product id: " + pidComboBox.getSelectedItem() + " ?",
		        	    				"Warning",
		        	    				JOptionPane.YES_NO_OPTION
		        					);

		        if (dialogResult == JOptionPane.NO_OPTION) {
		        	   return;
		        }
		        else {
		        	if(committedBit.getText().equalsIgnoreCase("Yes")) {
		        		try {
				            // Execute the merge query
				            PreparedStatement stmt = conn.prepareStatement(deleteBufferQuery);
				            stmt.executeUpdate();
				            System.out.println("Deletion Successful in Buffer");
				            
				            stmt = conn.prepareStatement(deleteMainQuery);
				            stmt.executeUpdate();
				            System.out.println("Deletion Successful in Main Database");
				            
				            JOptionPane.showMessageDialog(null, "Record Deleted from Buffer and Main Database successfully!");
				            
				            pidComboBox.setSelectedIndex(0);
				            committedBit.setText("");
				            productNameTextField.setText("");
							categoryComboBox.setSelectedItem("");
							brandComboBox.setSelectedItem("");
							modelTextField.setText("");
							specificationTextArea.setText("");
							priceTextField.setText("");
							availabletextField.setText("");
							noRadioButton.doClick();
							
				            loadTableData();  // Refresh table after merge
				            loadComboData();
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        }
		        	}else {
		        		try {
				            // Execute the merge query
				            PreparedStatement stmt = conn.prepareStatement(deleteBufferQuery);
				            stmt.executeUpdate();
				            System.out.println("Deletion Successful in Buffer");
				            
				            JOptionPane.showMessageDialog(null, "Record Deleted from Buffer successfully!");
				            
				            pidComboBox.setSelectedIndex(0);
				            committedBit.setText("");
				            productNameTextField.setText("");
							categoryComboBox.setSelectedItem("");
							brandComboBox.setSelectedItem("");
							modelTextField.setText("");
							specificationTextArea.setText("");
							priceTextField.setText("");
							availabletextField.setText("");
							noRadioButton.doClick();
							
				            loadTableData();  // Refresh table after merge
				            loadComboData();
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        }
		        	}
		        	
		        }
		        
			}
		});
		
		itemTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        int selectedRow = itemTable.getSelectedRow();
		        if (selectedRow != -1) {
		        	pidTextField.setText(itemTable.getValueAt(selectedRow, 0).toString());
		        	pidComboBox.setSelectedItem(itemTable.getValueAt(selectedRow, 0).toString());
		            productNameTextField.setText(itemTable.getValueAt(selectedRow, 1).toString());
		            categoryComboBox.setSelectedItem(itemTable.getValueAt(selectedRow, 2).toString());
		            brandComboBox.setSelectedItem(itemTable.getValueAt(selectedRow, 3).toString());
		            modelTextField.setText(itemTable.getValueAt(selectedRow, 4).toString());
		            specificationTextArea.setText(itemTable.getValueAt(selectedRow, 5).toString());
		            priceTextField.setText(itemTable.getValueAt(selectedRow, 6).toString());
		            availabletextField.setText(itemTable.getValueAt(selectedRow, 8).toString());
		            // Set 'Yes' or 'No' based on value in 'Is Trendy'
		            String isTrendy = itemTable.getValueAt(selectedRow, 9).toString();
		            YesRadioButton.setSelected(isTrendy.equals("Yes"));
		            noRadioButton.setSelected(!isTrendy.equals("Yes"));
		            committedBit.setText(getCommittedBit(pidTextField.getText()));
		            
		            if (committedBit.getText().equalsIgnoreCase("Yes")) {
		            	commitButton.setEnabled(false);
		            }else {
		            	commitButton.setEnabled(true);
		            }
		        }
		    }

			private String getCommittedBit(String pid) {
				// TODO Auto-generated method stub
				String query = "SELECT COMMITTED FROM PRODUCT1 WHERE product_id = "+ pid + "";  // Example query
				System.out.println("Executed query : " + query);
			    try {
			        Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(query);
			        if(rs.next()) {
			        	return(rs.getString("committed"));
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
				return null;
			}
		});
	}
	
	public void loadTableData(String searchTxt) {
		if (!(searchTxt.equalsIgnoreCase(""))) {
			String query = "SELECT * FROM product1 WHERE LOWER(product_name) LIKE LOWER('%"+searchTxt+"%') OR LOWER(brand) LIKE LOWER('%"+searchTxt+"%') OR LOWER(category) LIKE LOWER('%"+searchTxt+"%') ORDER BY product_id";
			try {
				Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
	            if (!(rs.next())) {
	            	JOptionPane.showMessageDialog(null, "No Reocrds found");
	            	searchTextField.setText("");
	            	pidComboBox.setSelectedIndex(0);
	            	loadTableData();
	            	//loadComboData();
	            	return;
	            }
		        model.setRowCount(0);  // Clear existing rows
		        while (rs.next()) {
		        	String pid = rs.getString("product_id");
		            String name = rs.getString("product_name");
		            String category = rs.getString("category");
		            String brand = rs.getString("brand");
		            String p_model = rs.getString("model");
		            String specification = rs.getString("specification");
		            String price = rs.getString("price");
		            String inDate = rs.getString("in_date");
		            String availableStock = rs.getString("available_stock");
		            String isTrendy = rs.getString("is_trending");
		            model.addRow(new Object[]{pid, name, category, brand, p_model, specification, price, inDate, availableStock, isTrendy});
		        }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return;
		}
	    // Assume you have a database connection established
	    String query = "SELECT * FROM PRODUCT1 ORDER BY product_id";  // Example query
	    try {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
	        model.setRowCount(0);  // Clear existing rows
	        while (rs.next()) {
	        	String pid = rs.getString("product_id");
	            String name = rs.getString("product_name");
	            String category = rs.getString("category");
	            String brand = rs.getString("brand");
	            String p_model = rs.getString("model");
	            String specification = rs.getString("specification");
	            String price = rs.getString("price");
	            String inDate = rs.getString("in_date");
	            String availableStock = rs.getString("available_stock");
	            String isTrendy = rs.getString("is_trending");
	            model.addRow(new Object[]{pid, name, category, brand, p_model, specification, price, inDate, availableStock, isTrendy});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadTableData() {
	    // Assume you have a database connection established
	    String query = "SELECT * FROM PRODUCT1 ORDER BY product_id";  // Example query
	    try {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
	        model.setRowCount(0);  // Clear existing rows
	        while (rs.next()) {
	        	String pid = rs.getString("product_id");
	            String name = rs.getString("product_name");
	            String category = rs.getString("category");
	            String brand = rs.getString("brand");
	            String p_model = rs.getString("model");
	            String specification = rs.getString("specification");
	            String price = rs.getString("price");
	            String inDate = rs.getString("in_date");
	            String availableStock = rs.getString("available_stock");
	            String isTrendy = rs.getString("is_trending");
	            model.addRow(new Object[]{pid, name, category, brand, p_model, specification, price, inDate, availableStock, isTrendy});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadComboData() {
	    // Assume you have a database connection established
	    String query = "SELECT product_id FROM PRODUCT1 ORDER BY product_id";  // Example query
	    try {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        DefaultComboBoxModel model = (DefaultComboBoxModel) pidComboBox.getModel();
	        model.removeAllElements(); // Clear existing rows
	        model.addElement("0");
	        while (rs.next()) {
	        	String pid = rs.getString("product_id");
	        	model.addElement(pid);
	        }
	        pidComboBox.setSelectedIndex(0);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
