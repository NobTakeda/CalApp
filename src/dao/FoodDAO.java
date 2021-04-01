package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Food;

public class FoodDAO {
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
	public void insertOne(Food food) {
		try {
			this.connect();
			ps=db.prepareStatement("INSERT INTO foods(name,cal,time_id,updated)"
					+ " VALUES(?,?,?,?)");
			ps.setString(1, food.getName());
			ps.setInt(2, food.getCal());
			ps.setInt(3, food.getTime());
			ps.setString(4, food.getDate());
			System.out.println("insertOne実行"+ps);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public Food findOne(int id) {
		Food food=null;
		try{
			this.connect();
			ps=db.prepareStatement("SELECT * FROM foods WHERE id=?");
			ps.setInt(1, id);
			System.out.println("findOne実行"+ps);
			rs=ps.executeQuery();
			if(rs.next()) {
				int idNum=rs.getInt("id");
				String name=rs.getString("name");
				int cal=rs.getInt("cal");
				int time=rs.getInt("time_id");
				String date=rs.getString("updated");
				food=new Food(idNum,name,cal,time,date);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}

		return food;
	}
	public List<Food> findAll(){
		List<Food> list=new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM foods");
			System.out.println("findAll実行"+ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int cal=rs.getInt("cal");
				int time=rs.getInt("time_id");
				String date=rs.getString("updated");
				list.add(new Food(id,name,cal,time,date));
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}
	//指定した日付に登録したFoodを全て返すメソッド
	public List<Food> findToday(String date){
		List<Food> list=new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM foods WHERE updated=? ORDER BY time_id ASC");
			ps.setString(1, date);
			System.out.println("FindToday実行"+ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int cal=rs.getInt("cal");
				int time=rs.getInt("time_id");
				String updated=rs.getString("updated");
				String timeStr="";
				switch(time) {
				case 0:
					timeStr="朝";
					break;
				case 1:
					timeStr="昼";
					break;
				case 2:
					timeStr="晩";
					break;
				}
				list.add(new Food(id,name,cal,time,updated,timeStr));
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;
	}
	public void updateFood(Food food) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE foods SET name=?,cal=?,time_id=?,updated=? WHERE id=?");
			ps.setString(1, food.getName());
			ps.setInt(2, food.getCal());
			ps.setInt(3, food.getTime());
			ps.setString(4, food.getDate());
			ps.setInt(5, food.getId());
			System.out.println("updateFood:foodテーブル更新"+ps);
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}


	}
	public void deleteFood(int id) {
		try {
			this.connect();
			ps=db.prepareStatement("DELETE FROM foods WHERE id=?");
			ps.setInt(1, id);
			System.out.println("deleteFood実行"+ps);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}
