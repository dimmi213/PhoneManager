package GiaoDien;

import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectExportOrder;
import Main.exportOrder;
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

public class ExportOrder extends JFrame {

	private ArrayList<exportOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableExportOrder;
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
					j++,i.getIdE(),i.getSku(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
			});
		}
	}
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	int q;
	public ExportOrder() {
		Calendar calendar = Calendar.getInstance();
		final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
		System.out.print(tfDate);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1485, 847);
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
		
		scrollPane.setBounds(441, 59, 996, 548);
		contentPane.add(scrollPane);
		
		tableExportOrder = new JTable();
		tableExportOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)tableExportOrder.getModel();
				int row = tableExportOrder.getSelectedRow();
				tfExportOrderID.setText(model.getValueAt(row, 1).toString());
				tfSKU.setText(model.getValueAt(row, 2).toString());
				tfSupplierID.setText(model.getValueAt(row, 3).toString());
				tfName.setText(model.getValueAt(row, 4).toString());
				tfPrice.setText(model.getValueAt(row, 5).toString());
				tfAmount.setText(model.getValueAt(row, 6).toString());
				
			}
		});
		JLabel lblNewLabel = new JLabel("SKU:");
		lblNewLabel.setBounds(10, 114, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID:");
		lblNewLabel_1.setBounds(10, 152, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(10, 192, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price:");
		lblNewLabel_3.setBounds(10, 230, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		lblNewLabel_4.setBounds(10, 271, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		tfSKU = new JTextField();
		tfSKU.setBounds(120, 111, 293, 20);
		contentPane.add(tfSKU);
		tfSKU.setColumns(10);
		
		tfSupplierID = new JTextField();
		tfSupplierID.setBounds(120, 149, 293, 20);
		contentPane.add(tfSupplierID);
		tfSupplierID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(120, 189, 293, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(120, 227, 293, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(120, 268, 293, 20);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//		LocalDateTime now = LocalDateTime.now();  
//		String tfDate =dtf.format(now);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfExportOrderID.getText().equals("") || tfSKU.getText().equals("") || tfSupplierID.getText().equals("") || tfName.getText().equals("") || tfPrice.getText().equals("") || tfAmount.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Please fill complete information");
				}
				else {
					Calendar calendar = Calendar.getInstance();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
					exportOrder i = new exportOrder();
					i.setIdE(tfExportOrderID.getText());
					i.setSku(tfSKU.getText());
					i.setSupplierID(tfSupplierID.getText());
					i.setName(tfName.getText());
					i.setPrice(Long.parseLong(tfPrice.getText()) );
					i.setAmount(Long.parseLong(tfAmount.getText()));
					i.setDate(tfDate);
					if(new ConnectExportOrder().addProducts(i)) {
						list.add(i);
						JOptionPane.showMessageDialog(rootPane, "Save Successfully");
						showResult();
						tfExportOrderID.setText("");
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
				int row = tableExportOrder.getSelectedRow();
				if(row >=0) {
					try {
						Calendar calendar = Calendar.getInstance();
						final String tfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/exportorder","root","yunbrayyunh");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "update tblExportOrder set SKU=?,SupplierID=?,Name=?,Price=?,Amount=?,Date=? where ExportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(7, tfExportOrderID.getText());
						ps.setString(1, tfSKU.getText());
						ps.setString(2, tfSupplierID.getText());
						ps.setString(3, tfName.getText());
						ps.setString(4, tfPrice.getText());
						ps.setString(5, tfAmount.getText());
						ps.setString(6, tfDate);
						ps.executeUpdate();
						model.setValueAt(tfExportOrderID.getText(), row, 1);
						model.setValueAt(tfSKU.getText(), row, 2);
						model.setValueAt(tfSupplierID.getText(), row, 3);
						model.setValueAt(tfName.getText(), row, 4);
						model.setValueAt(tfPrice.getText(), row, 5);
						model.setValueAt(tfAmount.getText(), row, 6);
						model.setValueAt(tfDate, row, 7);
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
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/exportorder","root","yunbrayyunh");
						String value =(tableExportOrder.getModel().getValueAt(row, 0).toString());
						String query = "delete from tblexportorder where ExportOrderID=?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps = conn.prepareStatement(query);
						ps.setString(1, tfExportOrderID.getText());
						ps.executeUpdate();
						model.removeRow(row);
						JOptionPane.showMessageDialog(null, "Delete Successfully");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
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
				tfExportOrderID.setText("");
				tfSKU.setText("");
				tfSupplierID.setText("");
				tfName.setText("");
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
			
			JLabel lblNewLabel_5 = new JLabel("ExportOrderID:");
			lblNewLabel_5.setBounds(10, 79, 94, 14);
			contentPane.add(lblNewLabel_5);
			
			tfExportOrderID = new JTextField();
			tfExportOrderID.setBounds(120, 70, 293, 20);
			contentPane.add(tfExportOrderID);
			tfExportOrderID.setColumns(10);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Order().setVisible(true);
				}
			});
			btnBack.setBounds(118, 448, 89, 23);
			contentPane.add(btnBack);
		this.setLocationRelativeTo(null);
		list = new ConnectExportOrder().getListexportOrder();
		model = (DefaultTableModel) tableExportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "ID","SKU","Supplier ID", "Name", "Price", "Amount", "Date"
		});
		showTable();
	}
	
	int j = 1;
	private JTextField tfExportOrderID;
	public void showResult() {
		exportOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getIdE(),i.getSku(), i.getSupplierID(), i.getName(), i.getPrice(), i.getAmount(),i.getDate()
		});
	}
}