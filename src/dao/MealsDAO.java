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

import model.Meals;

public class MealsDAO {
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
			System.out.println("MealsDAO,connect OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	//1日の朝、昼、晩、合計のカロリーをmealsテーブルに登録
	public void insertMeals(Meals meals,String userid) {
		try {
			this.connect();
			ps=db.prepareStatement("INSERT INTO meals(breakfastCal,lunchCal,supperCal,totalCal,updated,userid)"
					+ " VALUES(?,?,?,?,?,?)");
			ps.setInt(1, meals.getBreakfastCal());
			ps.setInt(2, meals.getLunchCal());
			ps.setInt(3, meals.getSupperCal());
			ps.setInt(4, meals.getTotalCal());
			ps.setString(5, meals.getDate());
			ps.setString(6, userid);
			System.out.println("insertMeals実行"+ps);
			ps.execute();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public Meals findOneMeals(String date,String userid) {
		Meals meals=new Meals();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM meals WHERE updated=? AND userid=?");
			ps.setString(1, date);
			ps.setString(2,userid);
			System.out.println("findOneMeals実行"+ps);
			rs=ps.executeQuery();
			while(rs.next()) {
			meals.setId(rs.getInt("id"));
			meals.setBreakfastCal(rs.getInt("breakfastCal"));
			meals.setLunchCal(rs.getInt("lunchCal"));
			meals.setSupperCal(rs.getInt("supperCal"));
			meals.setTotalCal(rs.getInt("totalCal"));
			meals.setDate(rs.getString("updated"));
			meals.setDate(rs.getString("userid"));
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return meals;
	}
	//7日分のfodテーブルを返す
	public List<Meals> findWeek(String userid){
		List<Meals> mealsList=new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM meals WHERE userid=? ORDER BY updated DESC LIMIT 7");
			ps.setString(1, userid);
			System.out.println("findWeek(ユーザー"+userid+"のmealsテーブルにデータがあるかチェック)実行"+ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				int breakfastCal=rs.getInt("breakfastCal");
				int lunchCal=rs.getInt("lunchCal");
				int supperCal=rs.getInt("supperCal");
				int totalCal=rs.getInt("totalCal");
				String date=rs.getString("updated");
				System.out.printf("id=%d,朝=%d,昼=%d,晩=%d,合計=%d,日付=%s%n",id,breakfastCal,lunchCal,supperCal,totalCal,date);
				mealsList.add(new Meals(id,breakfastCal,lunchCal,supperCal,totalCal,date));
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return mealsList;
	}
	public void updateMeals(Meals meals,String userid) {
		try {
			this.connect();
			ps=db.prepareStatement("UPDATE meals SET breakfastCal=?,lunchCal=?,supperCal=?,totalCal=? WHERE updated=? AND userid=?");
			ps.setInt(1, meals.getBreakfastCal());
			ps.setInt(2, meals.getLunchCal());
			ps.setInt(3, meals.getSupperCal());
			ps.setInt(4, meals.getTotalCal());
			ps.setString(5, meals.getDate());
			ps.setString(6, userid);
			System.out.println("updateMeals:mealsテーブル更新"+ps);
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
}
