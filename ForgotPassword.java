package JavaSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField textNewPassword;
	private JPasswordField textConfirm;
	private JTextField textUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		setTitle("Forgot Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Password System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(185, 38, 223, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("New password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(74, 168, 116, 20);
		contentPane.add(lblNewLabel_4);
		
		textNewPassword = new JPasswordField();
		textNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewPassword.setBounds(216, 169, 237, 20);
		contentPane.add(textNewPassword);
		
		JLabel lblNewLabel_5 = new JLabel("Confirm password:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(74, 233, 131, 19);
		contentPane.add(lblNewLabel_5);
		
		textConfirm = new JPasswordField();
		textConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textConfirm.setBounds(216, 232, 237, 20);
		contentPane.add(textConfirm);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String newpassword = new String(textNewPassword.getPassword());
				String confirm = new String(textConfirm.getPassword());
				
				StringBuilder cd = new StringBuilder();
				
				if(newpassword.equals("")) {
					cd.append("You haven't entered your new password!\n");
				}
				
				if(confirm.equals("")) {
					cd.append("You haven't confirmed your new password!\n");
				}
				
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				if(cd.length()>0) {
					JOptionPane.showMessageDialog(frame, cd.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
					
					Statement statement = con.createStatement();
					Statement statement1 = con.createStatement();
					Statement statement2 = con.createStatement();
					
					String query_IU = "SELECT * FROM usermanagement.import_user";
					String query_EU = "SELECT * FROM usermanagement.export_user";
					String query_A = "SELECT * FROM usermanagement.admin";
					
					ResultSet resultSet_iu = statement.executeQuery(query_IU);
					ResultSet resultSet_eu = statement1.executeQuery(query_EU);
					ResultSet resultSet_a = statement2.executeQuery(query_A);
					
					con.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);

					
					if(resultSet_iu.next()) {
						statement.executeUpdate("update import_user set Password ='"+newpassword+"' where Username ='"+username+"'");
						if (newpassword.equals(confirm)) {
							JOptionPane.showMessageDialog(null, "Your password is successfully updated");
							setVisible(false);
							new LoginForm().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);
						}
						resultSet_iu.close();
					}
					if(resultSet_eu.next()) {
						statement1.executeUpdate("update export_user set Password ='"+newpassword+"' where Username ='"+username+"'");
						if (newpassword.equals(confirm)) {
							JOptionPane.showMessageDialog(null, "Your password is successfully updated");
							setVisible(false);
							new LoginForm().setVisible(true);
							
						} else {
							JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);
						}
						
					}if(resultSet_a.next()) {
					
						statement2.executeUpdate("update admin set Password ='"+newpassword+"' where Username ='"+username+"'");
						if (newpassword.equals(confirm)) {
							JOptionPane.showMessageDialog(null, "Your password is successfully updated");
							setVisible(false);
							new LoginForm().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, "The entered passwords do not match. Try again.", "Failture", JOptionPane.ERROR_MESSAGE);
						}
						resultSet_a.close();
					}
					else {
						JOptionPane.showMessageDialog(frame, "Please write correct Username or Answer", "Failture", JOptionPane.ERROR_MESSAGE);
					}
				
				
				}catch (SQLException e2) {
					e2.printStackTrace();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error in connection");
					e2.printStackTrace();
				}
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(96, 308, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginForm().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(399, 308, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNewPassword.setText("");
				textConfirm.setText("");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(249, 310, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Username:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(74, 98, 116, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUsername.setColumns(10);
		textUsername.setBounds(216, 97, 237, 20);
		contentPane.add(textUsername);
		
		
	}
}
