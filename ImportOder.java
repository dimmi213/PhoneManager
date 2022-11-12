package GiaoDien;

import java.awt.EventQueue;
import java.util.ArrayList;


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

public class ImportOder extends JFrame {
	private ArrayList<importOrder> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTable tableImportOrder;
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
					j++,i.getId(), i.getName(), i.getType(), i.getPrice(), i.getAmount()
			});
		}
	}
	
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
					importOrder i = new importOrder();
					i.setId(tfProductID.getText());
					i.setName(tfName.getText());
					i.setType(tfType.getText());
					i.setPrice(Long.parseLong(tfPrice.getText()) );
					i.setAmount(Long.parseLong(tfAmount.getText()));
					if(new ConnectImportOrder().addProducts(i)) {
						list.add(i);
						JOptionPane.showMessageDialog(rootPane, "Save Successfully");
						showResult();
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
				int index = tableImportOrder.getSelectedRow();
				
				if(index>=0) {
//					importOrder i = new importOrder();
					model.setValueAt(tfProductID.getText(), index, 0);
					model.setValueAt(tfName.getText(), index, 1);
					model.setValueAt(tfType.getText(), index, 2);
					model.setValueAt(tfPrice.getText(), index, 3);
					model.setValueAt(tfAmount.getText(), index, 4);
					JOptionPane.showMessageDialog(null, "Update Successfully");
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
				int i = tableImportOrder.getSelectedRow();
				model.removeRow(i);
				if(i>=0) {
					JOptionPane.showMessageDialog(null, "Delete Successfully");
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
		tableImportOrder.setModel(new javax.swing.table.DefaultTableModel(
				 new Object [][] {

			            },
				 new String [] {

			            }
			));
			
			scrollPane.setViewportView(tableImportOrder);
		this.setLocationRelativeTo(null);
		list = new ConnectImportOrder().getListimportOrder();
		model = (DefaultTableModel) tableImportOrder.getModel();
		model.setColumnIdentifiers(new Object[] {
				"STT", "Product ID", "Name", "Type", "Price", "Amount"
		});
		showTable();
	}
	int j = 1;
	public void showResult() {
		importOrder i = list.get(list.size() -1 );
		model.addRow(new Object[] {
				j++,i.getId(), i.getName(), i.getType(), i.getPrice(), i.getAmount()
		});
	}
}
