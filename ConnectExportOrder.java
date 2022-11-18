package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
		String sql = "INSERT INTO tblExportOrder(ProductID, Name, Type, Price, Amount)"
				+ "VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getId());
			ps.setString(2, i.getName());
			ps.setString(3, i.getType());
			ps.setLong(4, i.getPrice());
			ps.setLong(5, i.getAmount());
//			ps.setLong(6, getDate);
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
            	i.setId(rs.getString("ProductID"));
            	i.setName(rs.getString("Name"));
            	i.setType(rs.getString("Type"));
            	i.setPrice(rs.getLong("Price"));
            	i.setAmount(rs.getLong("Amount"));
            	
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
