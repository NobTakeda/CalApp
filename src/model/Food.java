package model;

import java.io.Serializable;

public class Food implements Serializable{

	private int id;
	private String name;
	private int cal;
	private int time;
	//0,1,2の順で朝、昼、晩
	private String date;
	private String timeStr;
	private String userid;

	public Food() {}
	public Food(String name,int cal,int time,String date) {
		this.name=name;
		this.cal=cal;
		this.time=time;
		this.date=date;
	}
	//↓useridをセットする時このコンストラクタを使用。timeStrの部分にuseridを入れること。
	public Food(String name,int cal,int time,String date,String timeStr) {
		this(name,cal,time,date);
		this.setTimeStr(timeStr);
	}
	public Food(int id,String name,int cal,int time,String date) {
		this.id=id;
		this.name=name;
		this.cal=cal;
		this.time=time;
		this.date=date;
	}

	public Food(int id,String name,int cal,int time,String date,String timeStr) {
		this(id,name,cal,time,date);
		this.timeStr=timeStr;
	}
	public Food(int id,String name,int cal,int time,String date,String timeStr,String userid) {
		this(id,name,cal,time,date,timeStr);
		this.userid=userid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
