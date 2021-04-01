package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;

public class UserDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/calapp");
		this.db = ds.getConnection();
	}
	private void disconnect()  {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ConnectCheck() {
		try {
			this.connect();
			System.out.println("OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public User register(String userid) {
		User user=new User();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM users(userid)"+"VALUES(?)");
			ps.setString(1, userid);
			System.out.println("UserDAO,ID紹介実行"+ps);
			rs=ps.executeQuery();
			if(rs.next()) {
				user.setUserid(rs.getString("userid"));
				user.setUserpass(rs.getString("userpass"));
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return user;
	}
	public void insertOne(User user) {
		try {
			this.connect();
			ps=db.prepareStatement("INSERT INTO users(name,height,weight,bmi,cal,targetcal,userid,userpass)"
					+ " VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, user.getName());
			ps.setDouble(2, user.getHeight());
			ps.setDouble(3, user.getWeight());
			ps.setDouble(4, user.getBmi());
			ps.setInt(5, user.getCal());
			ps.setInt(6, user.getTargetCal());
			ps.setString(7, user.getUserid());
			ps.setString(8, user.getUserpass());
			System.out.println("UserDAO,insertOne実行"+ps);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public User findOne(int userid) {
		User user=null;
		try{
			this.connect();
			ps=db.prepareStatement("SELECT * FROM users WHERE userid=?");
			ps.setInt(1, userid);
			System.out.println("UserDAO,findOne実行"+ps);
			rs=ps.executeQuery();
			if(rs.next()) {
				int idNum=rs.getInt("id");
				String name=rs.getString("name");
				Double height=rs.getDouble("height");
				Double weight=rs.getDouble("weight");
				Double bmi=rs.getDouble("bmi");
				int cal=rs.getInt("cal");
				int targetCal=rs.getInt("targetcal");
				String user_id=rs.getString("userid");
				user=new User(idNum,name,height,weight,bmi,cal,targetCal,user_id);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return user;
	}
}
