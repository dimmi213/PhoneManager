package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import Main.exportOrder;

public class ConnectExportOrder {
	private Connection conn;
	ResultSet rs = null;
	int q;
	public ConnectExportOrder() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ExportOrder;user=sa;password=1234");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(conn);
	}
	public boolean addProducts(exportOrder i) {
		String sql = "INSERT INTO tblExportOrder(ExportOrderID,SKU,SupplierID, Name, Price, Amount,Date)"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getIdE());
			ps.setString(2, i.getSku());
			ps.setString(3, i.getSupplierID());
			ps.setString(4, i.getName());
			ps.setLong(5, i.getPrice());
			ps.setLong(6, i.getAmount());
			ps.setString(7, i.getDate());
			return  ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	} 
	
	
	public ArrayList<exportOrder>getListexportOrder(){
		ArrayList<exportOrder> list = new ArrayList <>();
		String sql = "SELECT * FROM tblExportOrder";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	exportOrder i = new exportOrder();
            	i.setIdE(rs.getString("ExportOrderID"));
            	i.setSku(rs.getString("ProductID"));
            	i.setSupplierID(rs.getString("Name"));
            	i.setName(rs.getString("Type"));
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
		new ConnectExportOrder();
	}
}
