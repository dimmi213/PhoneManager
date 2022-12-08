package Test;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Test.connectManager;
import GiaoDien.Order;
import Main.importOrder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Main_Manager extends JFrame {
	private ArrayList<Manager> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableManager;
	private JTextField tflUsername;
	private JTextField tflName;
	private JTextField tflUsertype;
	private JTextField tflPassword;
	private JTextField tflEmail;
	
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Manager frame = new Main_Manager();
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
	public void showTable() {
		for(Manager i : list) {
			model.addRow(new Object[] {
					j++,i.getID(),i.getUsername(), i.getName(), i.getUsertype(), i.getEmail(), i.getGender(), i.getBirthday(),i.getPhone()
			});
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public Main_Manager() {
		Calendar calendar = Calendar.getInstance();
		final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
		System.out.print(tfDate);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1520, 847);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelImportOrder = new JLabel("MANAGER");
		LabelImportOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		LabelImportOrder.setBounds(10, 11, 251, 48);
		contentPane.add(LabelImportOrder);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(330, 85, 913, 698);
		contentPane.add(scrollPane);
		
		tableManager = new JTable();
		tableManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableManager.getModel();
				int row = tableManager.getSelectedRow();
				tfIID.setText(model.getValueAt(row, 1).toString());
				tflUsername.setText(model.getValueAt(row, 2).toString());
				tflName.setText(model.getValueAt(row, 3).toString());
				tflUsertype.setText(model.getValueAt(row, 4).toString());
				tflPassword.setText(model.getValueAt(row, 5).toString());
				tflEmail.setText(model.getValueAt(row, 6).toString());
				tflGender.setText(model.getValueAt(row, 7).toString());
				tflBirthday.setText(model.getValueAt(row, 8).toString());
				tflPhone.setText(model.getValueAt(row, 9).toString());
				
				
			}
		});
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 123, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 161, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usertype:");
		lblNewLabel_2.setBounds(10, 201, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(10, 239, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(10, 280, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tflUsername = new JTextField();
		tflUsername.setBounds(114, 122, 206, 20);
		contentPane.add(tflUsername);
		tflUsername.setColumns(10);
		
		tflName = new JTextField();
		tflName.setBounds(114, 160, 206, 20);
		contentPane.add(tflName);
		tflName.setColumns(10);
		
		tflUsertype = new JTextField();
		tflUsertype.setBounds(114, 200, 206, 20);
		contentPane.add(tflUsertype);
		tflUsertype.setColumns(10);
		
		tflPassword = new JTextField();
		tflPassword.setBounds(114, 238, 206, 20);
		contentPane.add(tflPassword);
		tflPassword.setColumns(10);
		
		tflEmail = new JTextField();
		tflEmail.setBounds(114, 279, 206, 20);
		contentPane.add(tflEmail);
		tflEmail.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfIID.getText().equals("") || tflUsername.getText().equals("") || tflName.getText().equals("") || tflUsertype.getText().equals("") || tflPassword.getText().equals("") || tflEmail.getText().equals("") || tflGender.getText().equals("") || tflBirthday.getText().equals("") || tflPhone.getText().equals("") ) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
					Calendar calendar = Calendar.getInstance();
					final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
					System.out.print(tfDate);
					Manager i = new Manager();
					i.setID(tfIID.getText());
					i.setUsername(tflUsername.getText());
					i.setName(tflName.getText());
					i.setUsertype(tflUsertype.getText());
					i.setPassword(tflPassword.getText());
					i.setEmail(tflEmail.getText());
					i.setGender(tflGender.getText());
					i.setBirthday(tflBirthday.getText());
					i.setPhone(tflPhone.getText());
					if(new connectManager().addProducts(i)) {
						list.add(i);
						JOptionPane.showMessageDialog(rootPane, "Save Successfully");
						showResult();
						tfIID.setText("");
						tflUsername.setText("");
						tflName.setText("");
						tflUsertype.setText("");
						tflPassword.setText("");
						tflEmail.setText("");
						tflGender.setText("");
						tflBirthday.setText("");
						tflPhone.setText("");
					} 
					else {
						JOptionPane.showMessageDialog(rootPane, "Product's ID cannot be duplicated!");
					}
					
				}
			}
		});
		btnAdd.setBounds(26, 454, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableManager.getSelectedRow();
				if(row >=0) {
					try {
						Calendar calendar = Calendar.getInstance();
						new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
						tableManager.getModel().getValueAt(row, 0).toString();
						String query = "update user set Username=?,Name=?,Usertype=?,Password=?,Email=?,Gender=?,Birthday = ?,Phone=? where ID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(9, tfIID.getText());
						ps.setString(1, tflUsername.getText());
						ps.setString(2, tflName.getText());
						ps.setString(3, tflUsertype.getText());
						ps.setString(4, tflPassword.getText());
						ps.setString(5, tflEmail.getText());
						ps.setString(6, tflGender.getText());
						ps.setString(7, tflBirthday.getText());
						ps.setString(8, tflPhone.getText());
						ps.executeUpdate();
						model.setValueAt(tfIID.getText(), row, 1);
						model.setValueAt(tflUsername.getText(), row, 2);
						model.setValueAt(tflName.getText(), row, 3);
						model.setValueAt(tflUsertype.getText(), row, 4);
						model.setValueAt(tflPassword.getText(), row, 5);
						model.setValueAt(tflEmail.getText(), row, 6);
						model.setValueAt(tflGender.getText(), row, 7);
						model.setValueAt(tflBirthday.getText(), row, 8);
						model.setValueAt(tflPhone.getText(), row, 9);
						JOptionPane.showMessageDialog(null, "Update Successfully");
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Plese select a row first");
				}
			}
		});
		btnUpdate.setBounds(212, 454, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfIID.setText("");
				tflUsername.setText("");
				tflName.setText("");
				tflUsertype.setText("");
				tflPassword.setText("");
				tflEmail.setText("");
				tflGender.setText("");
				tflBirthday.setText("");
				tflPhone.setText("");
			}
		});
		btnClear.setBounds(26, 517, 89, 23);
		contentPane.add(btnClear);
		tableManager.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableManager);
			
			JLabel lblNewLabel_5 = new JLabel("ID:");
			lblNewLabel_5.setBounds(10, 86, 99, 14);
			contentPane.add(lblNewLabel_5);
			
			tfIID = new JTextField();
			tfIID.setBounds(114, 85, 206, 20);
			contentPane.add(tfIID);
			tfIID.setColumns(10);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Order().setVisible(true);
				}
			});
			btnBack.setBounds(212, 517, 89, 23);
			contentPane.add(btnBack);
			
			JLabel lblNewLabel_4_1 = new JLabel("Phone");
			lblNewLabel_4_1.setBounds(10, 402, 83, 14);
			contentPane.add(lblNewLabel_4_1);
			
			tflPhone = new JTextField();
			tflPhone.setColumns(10);
			tflPhone.setBounds(114, 401, 206, 20);
			contentPane.add(tflPhone);
			
			JLabel lblNewLabel_3_1 = new JLabel("Birthday");
			lblNewLabel_3_1.setBounds(10, 361, 83, 14);
			contentPane.add(lblNewLabel_3_1);
			
			tflBirthday = new JTextField();
			tflBirthday.setColumns(10);
			tflBirthday.setBounds(114, 360, 206, 20);
			contentPane.add(tflBirthday);
			
			JLabel lblNewLabel_2_1 = new JLabel("Gender:");
			lblNewLabel_2_1.setBounds(10, 323, 83, 14);
			contentPane.add(lblNewLabel_2_1);
			
			tflGender = new JTextField();
			tflGender.setColumns(10);
			tflGender.setBounds(114, 322, 206, 20);
			contentPane.add(tflGender);
		this.setLocationRelativeTo(null);
		list = new connectManager().getListMain_Managers();
		model = (DefaultTableModel) tableManager.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "ID","Username", "Name", "Usertype", "Password", "Email", "Gender","Birthday", "Phone"
		});
		showTable();
	}
	int j = 1;
	private JTextField tfIID;
	private JTextField tflPhone;
	private JTextField tflBirthday;
	private JTextField tflGender;
	public void showResult() {
		Manager i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getID(),i.getUsername(), i.getName(),i.getUsertype(),i.getPassword(), i.getEmail(), i.getGender(), i.getBirthday(), i.getPhone()
		});
	}
}