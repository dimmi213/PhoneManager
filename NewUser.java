package JavaSwing;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Driver;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JPasswordField textConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst;

	/**
	 * Create the frame.
	 */
	public NewUser() {
		setTitle("Create New User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(233, 51, 109, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(86, 108, 84, 20);
		contentPane.add(lblNewLabel_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUsername.setBounds(233, 110, 226, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(86, 176, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Confirm Password:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(86, 243, 127, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(84, 391, 447, 2);
		contentPane.add(separator);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("User Type:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(86, 307, 84, 20);
		contentPane.add(lblNewLabel_3);
		
		final JComboBox textUserType = new JComboBox();
		textUserType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUserType.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		textUserType.setToolTipText("");
		textUserType.setBounds(233, 308, 63, 22);
		contentPane.add(textUserType);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username  = textUsername.getText();
				String password = new String(textPassword.getPassword());
				String confirm = new String(textConfirm.getPassword());
				String usertype = textUserType.getSelectedItem().toString();				

				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				
				if (username.length()==0) {
					JOptionPane.showMessageDialog(frame, "Please type the user name");
				} else if (password.length()==0) {
					JOptionPane.showMessageDialog(frame, "Please type the password");
				} else if (password.equals(confirm)== false) {
					JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
						pst = con.prepareStatement("insert into user(Username,Password,UserType)values(?,?,?)");
						pst.setString(1, username);
						pst.setString(2, confirm);
						pst.setString(3, usertype);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "User created");
						textUsername.setText("");
						textPassword.setText("");
						textConfirm.setText("");
						textUserType.getSelectedIndex();
						textUserType.requestFocus();
						
					} catch (ClassNotFoundException e1) {
						Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE,null,e1);
						e1.printStackTrace();
					} catch (SQLException e2) {
						Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE,null,e2);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(143, 451, 89, 30);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(428, 451, 88, 30);
		contentPane.add(btnBack);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
				textConfirm.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(281, 452, 94, 29);
		contentPane.add(btnClear);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPassword.setBounds(233, 175, 226, 20);
		contentPane.add(textPassword);
		
		textConfirm = new JPasswordField();
		textConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textConfirm.setBounds(233, 237, 226, 20);
		contentPane.add(textConfirm);
	}
}
