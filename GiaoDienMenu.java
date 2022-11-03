package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoDienMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienMenu frame = new GiaoDienMenu();
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
	public GiaoDienMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ButtonProduct = new JButton("PRODUCT");
		ButtonProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = new Product();
				product.show();
				
				dispose();
			}
		});
		ButtonProduct.setIcon(new ImageIcon("D:\\esclipse\\DoAnOOP\\IMG\\Mobile.png"));
		ButtonProduct.setMargin(new Insets(0, 0, 0, 6));
		ButtonProduct.setMinimumSize(new Dimension(42, 10));
		ButtonProduct.setMaximumSize(new Dimension(59, 17));
		ButtonProduct.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonProduct.setBackground(new Color(192, 192, 192));
		ButtonProduct.setForeground(new Color(0, 0, 0));
		ButtonProduct.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		ButtonProduct.setBounds(10, 101, 150, 53);
		contentPane.add(ButtonProduct);
		
		JButton ButtonSupplier = new JButton("SUPPLIER");
		ButtonSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Supplier supplier = new Supplier();
				supplier.show();
				
				dispose();
			}
		});
		ButtonSupplier.setMargin(new Insets(0, 0, 0, 2));
		ButtonSupplier.setIcon(new ImageIcon("D:\\esclipse\\DoAnOOP\\IMG\\Supplier.png"));
		ButtonSupplier.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		ButtonSupplier.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonSupplier.setBackground(new Color(192, 192, 192));
		ButtonSupplier.setActionCommand("");
		ButtonSupplier.setBounds(251, 101, 150, 53);
		contentPane.add(ButtonSupplier);
		
		JButton ButtonOrder = new JButton("ORDER");
		ButtonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order order = new Order();
				order.show();
				
				dispose();
			}
		});
		ButtonOrder.setMargin(new Insets(0, 0, 0, 2));
		ButtonOrder.setIcon(new ImageIcon("D:\\esclipse\\DoAnOOP\\IMG\\Order.png"));
		ButtonOrder.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonOrder.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		ButtonOrder.setBackground(new Color(192, 192, 192));
		ButtonOrder.setBounds(142, 184, 150, 53);
		contentPane.add(ButtonOrder);
		
		JLabel LabelPhoneManager = new JLabel("PHONE MANAGER");
		LabelPhoneManager.setForeground(new Color(0, 0, 0));
		LabelPhoneManager.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPhoneManager.setFont(new Font("Algerian", Font.BOLD, 28));
		LabelPhoneManager.setBounds(74, 11, 282, 58);
		contentPane.add(LabelPhoneManager);
	}

}
