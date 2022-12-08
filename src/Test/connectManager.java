package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import GiaoDien.ImportOrder;

public class connectManager {
	private Connection conn;
	ResultSet rs = null;
	int q;
	public connectManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mySQL://localhost:3306/usermanagement","root","yunbrayyunh");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(conn);
	}
	public boolean addProducts(Manager i) {
		String sql = "INSERT INTO user(ID,Username, Name, UserType, Password,Email, Gender, Birthday, Phone)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getID());
			ps.setString(2, i.getUsername());
			ps.setString(3, i.getName());
			ps.setString(4, i.getUsertype());
			ps.setString(5, i.getPassword());
			ps.setString(6, i.getEmail());
			ps.setString(7, i.getBirthday());
			ps.setString(8, i.getPhone());
			
//			ps.setLong(6, getDate);
			return  ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	} 
	
	
	public ArrayList<Manager> getListMain_Managers(){
		ArrayList<Manager> list = new ArrayList <>();
		String sql = "SELECT * FROM user";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	Manager i = new Manager();
            	i.setID(rs.getString("ID"));
            	i.setUsername(rs.getString("USername"));
            	i.setName(rs.getString("Name"));
            	i.setUsertype(rs.getString("Usertype"));
            	i.setPassword(rs.getString("Password"));
            	i.setEmail(rs.getString("Email"));
            	i.setGender(rs.getString("Gender"));
            	i.setBirthday(rs.getString("Birthday"));
            	i.setPhone(rs.getString("Phone"));
            	list.add(i);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		new connectManager();
	}
}