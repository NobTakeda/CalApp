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

import model.Result;

public class ResultDAO {
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
			System.out.println("ResultDAO,connect OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
	}
	public List<Result> findCount(String date,String calcDate,String userid) {
		List<Result> list=new ArrayList<>();
		try {
			this.connect();
			ps=db.prepareStatement("SELECT foods.updated,count(*) FROM foods\n" +
					"JOIN meals\n" +
					"ON foods.updated=meals.updated\n" +
					"WHERE\n" +
					"foods.userid=? AND\n" +
					"foods.updated BETWEEN\n" +
					"? AND ?\n" +
					"GROUP BY foods.updated;");
			ps.setString(1, userid);
			ps.setString(2, calcDate);
			ps.setString(3, date);

			System.out.println(ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				String dateStr=rs.getString("updated");
				System.out.println(dateStr);
				int count=rs.getInt("count(*)");
				System.out.println(count);
				Result result=new Result();
				result.setUpdated(dateStr);
				result.setCount(count);
				list.add(result);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return list;

	}
	public List<Result> findData(String date,String userid) {
		List<Result> resultList=new ArrayList<>();

		try {
			this.connect();
			ps=db.prepareStatement("SELECT * FROM foods\n" +
					"JOIN meals\n" +
					"ON foods.updated=meals.updated\n" +
					"WHERE foods.updated =? AND foods.userid=?\n" +
					"ORDER BY foods.updated ASC,time_id ASC;");
			ps.setString(1, date);
			ps.setString(2, userid);
			System.out.println("ResultDAO内、findDATA実行:"+ps);
			rs=ps.executeQuery();

			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int cal=rs.getInt("cal");
				int time_id=rs.getInt("time_id");
				String timeStr="";
				switch(time_id) {
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
				int breakfastCal=rs.getInt("breakfastCal");
				int lunchCal=rs.getInt("lunchCal");
				int supperCal=rs.getInt("supperCal");
				int totalCal=rs.getInt("totalCal");
				String updated=rs.getString("updated");
				Result result=new Result(id,name,cal,time_id,timeStr,breakfastCal,lunchCal,supperCal,totalCal,updated);
				resultList.add(result);
			}
		} catch (NamingException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		return resultList;
	}
}
