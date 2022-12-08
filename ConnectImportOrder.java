package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Main.importOrder;

//import GiaoDien.ImportOrder;

public class ConnectImportOrder {
	private Connection conn;
	ResultSet rs = null;
	int q;
	public ConnectImportOrder() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/importorder","root","yunbrayyunh");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(conn);
	}
	public boolean addProducts(importOrder i) {
		String sql = "INSERT INTO tblimportorder(ImportOrderID,SKU,SupplierID, Name, Price, Amount,Date)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getIdI());
			ps.setString(2, i.getSku());
			ps.setString(3, i.getSupplierID());
			ps.setString(4, i.getName());
			ps.setLong(5, i.getPrice());
			ps.setLong(6, i.getAmount());
			ps.setString(7, i.getDate());
//			ps.setLong(6, getDate);
			return  ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	} 
	
	
	public ArrayList<importOrder>getListimportOrder(){
		ArrayList<importOrder> list = new ArrayList <>();
		String sql = "SELECT * FROM tblimportorder";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	importOrder i = new importOrder();
            	i.setIdI(rs.getString("ImportOrderID"));
            	i.setSku(rs.getString("SKU"));
            	i.setSupplierID(rs.getString("SupplierID"));
            	i.setName(rs.getString("Name"));
            	i.setPrice(rs.getLong("Price"));
            	i.setAmount(rs.getLong("Amount"));
            	i.setDate(rs.getString("Date"));
            	list.add(i);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		new ConnectImportOrder();
	}
}
