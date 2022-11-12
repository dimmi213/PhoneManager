package Supplier.view;

import Supplier.entity.Suppliers;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierForm {
    private JTextField txtID;
    private JTextField txtPhoneNumber;
    private JTextField txtAddress;
    private JTextField txtProducts;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table1;
    private JButton resetButton;
    private JTextField textField1;
    private static Container rootPanel = new Container();

    public SupplierForm() {
        List<Suppliers> suppliers = new ArrayList<>();
        suppliers.add(new Suppliers("1","A","123","xyz","dienthoai1"));
        suppliers.add(new Suppliers("2","B","123","xyz","dienthoai1"));
        suppliers.add(new Suppliers("3","C","123","xyz","dienthoai1"));
        suppliers.add(new Suppliers("4","D","123","xyz","dienthoai1"));

        SupplierTabMode supplierTabMode = new SupplierTabMode(suppliers);
        table1.setModel(supplierTabMode);
        table1.setAutoCreateRowSorter(true);

    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Supplier Form");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(500,500);
        frame.setVisible(true);
    }

    private static class SupplierTabMode extends AbstractTableModel{
        private final String[] COLUMNS ={"ID","NAME","PHONE NUMBER","ADDRESS","PRODUCTS SUPPLY"};
        private List<Suppliers> suppliers;

        public SupplierTabMode(List<Suppliers> suppliers) {
            this.suppliers = suppliers;
        }

        @Override
        public int getRowCount() {
            return suppliers.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex){
                case 0 -> suppliers.get(rowIndex).getId();
                case 1 -> suppliers.get(rowIndex).getName();
                case 2 -> suppliers.get(rowIndex).getPhoneNumber();
                case 3 -> suppliers.get(rowIndex).getAddress();
                case 4 -> suppliers.get(rowIndex).getProductsSupply();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!= null) {
                return getValueAt(0, columnIndex).getClass();
            }else
                return Object.class;
        }
    }

}
