package UserManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;

import GiaoDien.GiaoDienMenu;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Manager extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textUsername;
	private JTextField textPhone;
	private JTextField textName;
	private JTextField textEmail;
	private JPasswordField textPassword;
	private JPasswordField textConfirm;
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTextField textSearch;
	/**
	 * Create the frame.
	 */
	
	public void table() {
		try {
			pst = con.prepareStatement("select * from user");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Manager() {
		
		setTitle("CREATE USER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1134, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE USER");
		lblNewLabel.setBounds(426, 11, 219, 66);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(49, 142, 90, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(49, 193, 90, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone:");
		lblNewLabel_3.setBounds(422, 98, 90, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email:");
		lblNewLabel_2_1.setBounds(422, 149, 90, 14);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setBounds(49, 100, 90, 14);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Gender:");
		lblNewLabel_2_1_1.setBounds(422, 196, 90, 14);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Birthday:");
		lblNewLabel_2_1_2.setBounds(422, 241, 90, 19);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2_1_2);
		
		textID = new JTextField();
		textID.setBounds(130, 101, 174, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textUsername = new JTextField();
		textUsername.setBounds(130, 143, 174, 20);
		textUsername.setColumns(10);
		contentPane.add(textUsername);
		
		textPhone = new JTextField();
		textPhone.setBounds(503, 99, 142, 20);
		textPhone.setColumns(10);
		contentPane.add(textPhone);
		
		textName = new JTextField();
		textName.setBounds(130, 195, 174, 20);
		textName.setColumns(10);
		contentPane.add(textName);
		
		textEmail = new JTextField();
		textEmail.setBounds(503, 145, 175, 20);
		textEmail.setColumns(10);
		contentPane.add(textEmail);
		
		final JComboBox textUserType = new JComboBox();
		textUserType.setBounds(503, 293, 111, 23);
		textUserType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUserType.setModel(new DefaultComboBoxModel(new String[] {"Import_User", "Export_User", "Admin"}));
		contentPane.add(textUserType);
		
		final JComboBox textGender = new JComboBox();
		textGender.setBounds(503, 190, 90, 23);
		textGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		textGender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(textGender);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("UserType:");
		lblNewLabel_2_1_2_1.setBounds(422, 292, 90, 19);
		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2_1_2_1);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(503, 242, 111, 20);
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password:");
		lblNewLabel_1_2.setBounds(49, 243, 90, 14);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Confirm:");
		lblNewLabel_2_2.setBounds(49, 295, 79, 14);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2_2);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(130, 244, 174, 20);
		contentPane.add(textPassword);
		
		textConfirm = new JPasswordField();
		textConfirm.setBounds(130, 296, 174, 20);
		contentPane.add(textConfirm);
		
		JPanel panel = new JPanel();
		panel.setBounds(738, 144, 324, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(46, 85, 89, 23);
		panel.add(btnReset);
		
		JButton btnSave = new JButton("Add");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(199, 30, 89, 23);
		panel.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(199, 85, 89, 23);
		panel.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JFrame("JOptionPane showMessageDialog example");
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
					pst = con.prepareStatement("update user set Name =?,Phone = ?,Email = ?,Gender = ? where ID = ?");

					pst.setString(1, textName.getText());
					pst.setString(2, textPhone.getText());
					pst.setString(3, textEmail.getText());
					pst.setString(4, textGender.getSelectedItem().toString());
					pst.setString(5, textSearch.getText());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "User have been updated");

					table();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(46, 30, 89, 23);
		panel.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 369, 1059, 106);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				textID.setText(model.getValueAt(row, 0).toString());
				textUsername.setText(model.getValueAt(row, 1).toString());
				textName.setText(model.getValueAt(row, 2).toString());
				textUserType.setSelectedItem(model.getValueAt(row, 3).toString());
				textPassword.setText(model.getValueAt(row, 4).toString());
				textConfirm.setText(model.getValueAt(row, 4).toString());
				textEmail.setText(model.getValueAt(row, 5).toString());
				textGender.setSelectedItem(model.getValueAt(row, 6).toString());
				textPhone.setText(model.getValueAt(row, 8).toString());
				dateChooser.setDateFormatString(model.getValueAt(row, 7).toString());

			}
		});
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Name", "Usertype", "Password", "Email", "Gender", "Birthday", "Phone"
			}
		));
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String id_search = textSearch.getText();
					try {
						pst = con.prepareStatement("select ID,Username, Name, UserType, Password, Email, Gender, Birthday, Phone from user where ID =?");
						pst.setString(1, id_search);
						ResultSet rs = pst.executeQuery();
						
						if (rs.next()==true) {
							String ID = rs.getString(1);
							String username = rs.getString(2);
							String name = rs.getString(3);
							rs.getString(4);
							String password = rs.getString(5);
							String email = rs.getString(6);
							rs.getString(7);
							rs.getString(8);
							String phone = rs.getString(9);
							
							textID.setText(ID);
							textUsername.setText(username);
							textName.setText(name);
							textUserType.getSelectedItem().toString();
							textPassword.setText(password);
							textPhone.setText(phone);
							textEmail.setText(email);
							
//							DefaultTableModel model = (DefaultTableModel)table.getModel();
//							int row = table.getSelectedRow();
//							textID.setText(model.getValueAt(row, 0).toString());
//							textUsername.setText(model.getValueAt(row, 1).toString());
//							textName.setText(model.getValueAt(row, 2).toString());
//							textUserType.setSelectedItem(model.getValueAt(row, 3).toString());
//							textPassword.setText(model.getValueAt(row, 4).toString());
//							textConfirm.setText(model.getValueAt(row, 4).toString());
//							textEmail.setText(model.getValueAt(row, 5).toString());
//							textGender.setSelectedItem(model.getValueAt(row, 6).toString());
//							textPhone.setText(model.getValueAt(row, 8).toString());
//							dateChooser.setDateFormatString(model.getValueAt(row, 7).toString());
						}else {
							textID.setText("");
							textUsername.setText("");
							textName.setText("");
							textPassword.setText("");
							textConfirm.setText("");
							textPhone.setText("");
							textEmail.setText("");
							dateChooser.setCalendar(null);
						}
					
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
			}
		});
		textSearch.setColumns(10);
		textSearch.setBounds(820, 92, 142, 20);
		contentPane.add(textSearch);
		
		JLabel lblNewLabel_3_1 = new JLabel("Search:");
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Users\\ACER\\OneDrive\\Máy tính\\Studying\\JavaSwing\\images\\search x30.png"));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(739, 91, 90, 21);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ForgotPassword().setVisible(true);
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChangePassword.setBounds(820, 305, 154, 23);
		contentPane.add(btnChangePassword);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GiaoDienMenu().setVisible(true);
			}
		});
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(textPassword.getPassword());
				String confirm = new String(textConfirm.getPassword());
				String birthday = null;
				StringBuilder c = new StringBuilder();
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				
				
				if(textID.getText().equals("")) {
					c.append("ID is empty!\n");
				}
				 if(textUsername.getText().equals("")) {
					c.append("Username is empty!\n");
				}
				 if(textName.getText().equals("")) {
					c.append("Name is empty!\n");
				}
				 if(password.equals("")) {
					c.append("Password is empty!\n");
				}
				 if(confirm.equals("")) {
					c.append("You haven't confirmed password!\n");
				}
				 if(textPhone.getText().equals("")) {
					c.append("Phone number is empty!\n");
				}
				 if(textEmail.getText().equals("")) {
					c.append("Please write your email!\n");
				}
				 
				 
				 if (!password.equals(confirm)) {
					JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);
				} else {
					if (dateChooser.getDate() != null) {
						SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
						birthday = dateformat.format(dateChooser.getDate());
					} else {
						c.append("You haven't chosen birthday for user!\n");
					}
				}
				
				if (textUserType.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "You haven't selected gender for user!\n");
				}

				if(c.length()>0) {
					JOptionPane.showMessageDialog(frame, c.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
					pst = con.prepareStatement("insert into `user`(`ID`,`Username`,`Name`,`UserType`,`Password`,`Phone`,`Email`,`Gender`,`Birthday`)values(?,?,?,?,?,?,?,?,?)");

					pst.setString(1, textID.getText());
					pst.setString(2, textUsername.getText());
					pst.setString(3, textName.getText());
					pst.setString(4, textUserType.getSelectedItem().toString());
					pst.setString(5, password);
					pst.setString(6, textPhone.getText());
					pst.setString(7, textEmail.getText());
					pst.setString(8, textGender.getSelectedItem().toString());
					if (birthday != null) {
						pst.setString(9, birthday);
					}else {
						pst.setNull(9, 0);
					}
					
					if (password.equals(confirm)) {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "User have been created");
					}
					
					table();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textID.setText("");
				textUsername.setText("");
				textName.setText("");
				textPassword.setText("");
				textConfirm.setText("");
				textPhone.setText("");
				textEmail.setText("");
				dateChooser.setCalendar(null);
			}
		});
	}
}