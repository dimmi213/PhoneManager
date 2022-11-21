package GiaoDien;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectImportOrder;
import Main.importOrder;
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

public class ImportOder extends JFrame {
	private ArrayList<importOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableImportOrder;
	private JTextField tfSKU;
	private JTextField tfSupplierID;
	private JTextField tfName;
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
					ImportOder frame = new ImportOder();
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
		for(importOrder i : list) {
			model.addRow(new Object[] {
					j++,i.getIdI(),i.getSku(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount()
			});
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public ImportOder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1379, 847);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelImportOrder = new JLabel("IMPORT ORDER");
		LabelImportOrder.setHorizontalAlignment(SwingConstants.CENTER);
		LabelImportOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		LabelImportOrder.setBounds(10, 11, 251, 48);
		contentPane.add(LabelImportOrder);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(492, 59, 673, 548);
		contentPane.add(scrollPane);
		
		tableImportOrder = new JTable();
		tableImportOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableImportOrder.getModel();
				int row = tableImportOrder.getSelectedRow();
				tfImportOrderID.setText(model.getValueAt(row, 1).toString());
				tfSKU.setText(model.getValueAt(row, 2).toString());
				tfSupplierID.setText(model.getValueAt(row, 3).toString());
				tfName.setText(model.getValueAt(row, 4).toString());
				tfPrice.setText(model.getValueAt(row, 5).toString());
				tfAmount.setText(model.getValueAt(row, 6).toString());
				
			}
		});
		JLabel lblNewLabel = new JLabel("SKU:");
		lblNewLabel.setBounds(10, 123, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID:");
		lblNewLabel_1.setBounds(10, 161, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(10, 201, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price:");
		lblNewLabel_3.setBounds(10, 239, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(10, 280, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tfSKU = new JTextField();
		tfSKU.setBounds(120, 120, 293, 20);
		contentPane.add(tfSKU);
		tfSKU.setColumns(10);
		
		tfSupplierID = new JTextField();
		tfSupplierID.setBounds(120, 158, 293, 20);
		contentPane.add(tfSupplierID);
		tfSupplierID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(120, 198, 293, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(120, 236, 293, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(120, 277, 293, 20);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfImportOrderID.getText().equals("") || tfSKU.getText().equals("") || tfSupplierID.getText().equals("") || tfName.getText().equals("") || tfPrice.getText().equals("") || tfAmount.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
					importOrder i = new importOrder();
					i.setIdI(tfImportOrderID.getText());
					i.setSku(tfSKU.getText());
					i.setSupplierID(tfSupplierID.getText());
					i.setName(tfName.getText());
					i.setPrice(Long.parseLong(tfPrice.getText()) );
					i.setAmount(Long.parseLong(tfAmount.getText()));
					if(new ConnectImportOrder().addProducts(i)) {
						list.add(i);
						JOptionPane.showMessageDialog(rootPane, "Save Successfully");
						showResult();
						tfImportOrderID.setText("");
						tfSKU.setText("");
						tfSupplierID.setText("");
						tfName.setText("");
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
				int row = tableImportOrder.getSelectedRow();
				if(row >=0) {
					try {
		
						conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ImportOrder;user=sa;password=1234");
						String value =(tableImportOrder.getModel().getValueAt(row, 0).toString());
						String query = "update tblImportOrder set SKU=?,SupplierID=?,Name=?,Price=?,Amount=? where ImportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(6, tfImportOrderID.getText());
						ps.setString(1, tfSKU.getText());
						ps.setString(2, tfSupplierID.getText());
						ps.setString(3, tfName.getText());
						ps.setString(4, tfPrice.getText());
						ps.setString(5, tfAmount.getText());
						ps.executeUpdate();
						model.setValueAt(tfImportOrderID.getText(), row, 1);
						model.setValueAt(tfSKU.getText(), row, 2);
						model.setValueAt(tfSupplierID.getText(), row, 3);
						model.setValueAt(tfName.getText(), row, 4);
						model.setValueAt(tfPrice.getText(), row, 5);
						model.setValueAt(tfAmount.getText(), row, 6);
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
				int row = tableImportOrder.getSelectedRow();

				if(row>=0) {
					try {
						conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ImportOrder;user=sa;password=1234");
						String value =(tableImportOrder.getModel().getValueAt(row, 0).toString());
						String query = "delete tblImportOrder where ImportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(1, tfImportOrderID.getText());
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
				tfImportOrderID.setText("");
				tfSKU.setText("");
				tfSupplierID.setText("");
				tfName.setText("");
				tfPrice.setText("");
				tfAmount.setText("");
			}
		});
		btnClear.setBounds(215, 376, 89, 23);
		contentPane.add(btnClear);
		tableImportOrder.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableImportOrder);
			
			JLabel lblNewLabel_5 = new JLabel("Import Order ID:");
			lblNewLabel_5.setBounds(10, 86, 99, 14);
			contentPane.add(lblNewLabel_5);
			
			tfImportOrderID = new JTextField();
			tfImportOrderID.setBounds(120, 83, 293, 20);
			contentPane.add(tfImportOrderID);
			tfImportOrderID.setColumns(10);
		this.setLocationRelativeTo(null);
		list = new ConnectImportOrder().getListimportOrder();
		model = (DefaultTableModel) tableImportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "ID","SKU", "SupplierID", "Name", "Price", "Amount", "Date"
		});
		showTable();
	}
	int j = 1;
	private JTextField tfImportOrderID;
	public void showResult() {
		importOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getIdI(),i.getSku(), i.getSupplierID(),i.getName(), i.getPrice(), i.getAmount()
		});
	}
}
