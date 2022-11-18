package GiaoDien;

import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectExportOrder;
import Main.exportOrder;
import javax.swing.JLabel;
import javax.swing.JList;
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExportOrder extends JFrame {

	private ArrayList<exportOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableExportOrder;
	private JTextField tfProductID;
	private JTextField tfName;
	private JTextField tfType;
	private JTextField tfPrice;
	private JTextField tfAmount;
	
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportOrder frame = new ExportOrder();
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
		for(exportOrder i : list) {
			model.addRow(new Object[] {
					j++,i.getId(), i.getName(), i.getType(), i.getPrice(), i.getAmount()
			});
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public ExportOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1379, 847);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelExportOrder = new JLabel("EXPORT ORDER");
		LabelExportOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelExportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		LabelExportOrder.setBounds(10, 11, 251, 48);
		contentPane.add(LabelExportOrder);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(492, 59, 673, 548);
		contentPane.add(scrollPane);
		
		tableExportOrder = new JTable();
		tableExportOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableExportOrder.getModel();
				int row = tableExportOrder.getSelectedRow();
				tfProductID.setText(model.getValueAt(row, 1).toString());
				tfName.setText(model.getValueAt(row, 2).toString());
				tfType.setText(model.getValueAt(row, 3).toString());
				tfPrice.setText(model.getValueAt(row, 4).toString());
				tfAmount.setText(model.getValueAt(row, 5).toString());
				
			}
		});
		JLabel lblNewLabel = new JLabel("Product ID:");
		lblNewLabel.setBounds(10, 93, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 131, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Type:");
		lblNewLabel_2.setBounds(10, 171, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price:");
		lblNewLabel_3.setBounds(10, 209, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(10, 250, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tfProductID = new JTextField();
		tfProductID.setBounds(120, 90, 293, 20);
		contentPane.add(tfProductID);
		tfProductID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(120, 128, 293, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfType = new JTextField();
		tfType.setBounds(120, 168, 293, 20);
		contentPane.add(tfType);
		tfType.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(120, 206, 293, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(120, 247, 293, 20);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfProductID.getText().equals("") || tfName.getText().equals("") || tfType.getText().equals("") || tfPrice.getText().equals("") || tfAmount.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
					exportOrder i = new exportOrder();
					i.setId(tfProductID.getText());
					i.setName(tfName.getText());
					i.setType(tfType.getText());
					i.setPrice(Long.parseLong(tfPrice.getText()) );
					i.setAmount(Long.parseLong(tfAmount.getText()));
					if(new ConnectExportOrder().addProducts(i)) {
						list.add(i);
						JOptionPane.showMessageDialog(rootPane, "Save Successfully");
						showResult();
						tfProductID.setText("");
						tfName.setText("");
						tfType.setText("");
						tfPrice.setText("");
						tfAmount.setText("");
					} 
					else {
						JOptionPane.showMessageDialog(rootPane, "Product's ID cannot be duplicated!");
					}
					
				}
				
				
			}
		});
		
		btnAdd.setBounds(29, 328, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableExportOrder.getSelectedRow();
				if(row >=0) {
					try {
		
						conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ExportOrder;user=sa;password=1234");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "update tblExportOrder set Name=?,Type=?,Price=?,Amount=? where ProductID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(5, tfProductID.getText());
						ps.setString(1, tfName.getText());
						ps.setString(2, tfType.getText());
						ps.setString(3, tfPrice.getText());
						ps.setString(4, tfAmount.getText());
						ps.executeUpdate();
						model.setValueAt(tfProductID.getText(), row, 1);
						model.setValueAt(tfName.getText(), row, 2);
						model.setValueAt(tfType.getText(), row, 3);
						model.setValueAt(tfPrice.getText(), row, 4);
						model.setValueAt(tfAmount.getText(), row, 5);
						JOptionPane.showMessageDialog(null, "Update Successfully");
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Plese select a row first");
				}
			}
		});
		btnUpdate.setBounds(215, 328, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableExportOrder.getSelectedRow();

				if(row>=0) {
					try {
						conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ExportOrder;user=sa;password=1234");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "delete tblExportOrder where ProductID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(1, tfProductID.getText());
						ps.executeUpdate();
						model.removeRow(row);
						JOptionPane.showMessageDialog(null, "Delete Successfully");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Plese select a row first");
				}
			}
		});
		btnDelete.setBounds(29, 376, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfProductID.setText("");
				tfName.setText("");
				tfType.setText("");
				tfPrice.setText("");
				tfAmount.setText("");
			}
		});
		btnClear.setBounds(215, 376, 89, 23);
		contentPane.add(btnClear);
		tableExportOrder.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableExportOrder);
		this.setLocationRelativeTo(null);
		list = new ConnectExportOrder().getListexportOrder();
		model = (DefaultTableModel) tableExportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "Product ID", "Name", "Type", "Price", "Amount", "Date"
		});
		showTable();
	}
	
	int j = 1;
	public void showResult() {
		exportOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getId(), i.getName(), i.getType(), i.getPrice(), i.getAmount()
		});
	}
}
