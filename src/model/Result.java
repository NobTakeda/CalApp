package model;

import java.io.Serializable;

public class Result implements Serializable{
	private int id;
	private String name;
	private int cal;
	private int time_id;
	private String updated;
	private int breakfastCal;
	private int lunchCal;
	private int supperCal;
	private int totalCal;
	private String timeStr;
	private int count;

	public Result() {}
	public Result(int id,String name,int cal,int time_id,String timeStr,int breakfastCal,int lunchCal,int supperCal,int totalCal,String updated) {
		this.id=id;
		this.name=name;
		this.cal=cal;
		this.time_id=time_id;
		this.timeStr=timeStr;
		this.breakfastCal=breakfastCal;
		this.lunchCal=lunchCal;
		this.supperCal=supperCal;
		this.totalCal=totalCal;
		this.updated=updated;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTime_id() {
		return time_id;
	}

	public void setTime_id(int time_id) {
		this.time_id = time_id;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public int getBreakfastCal() {
		return breakfastCal;
	}

	public void setBreakfastCal(int breakfastCal) {
		this.breakfastCal = breakfastCal;
	}

	public int getLunchCal() {
		return lunchCal;
	}

	public void setLunchCal(int lunchCal) {
		this.lunchCal = lunchCal;
	}

	public int getSupperCal() {
		return supperCal;
	}

	public void setSupperCal(int supperCal) {
		this.supperCal = supperCal;
	}

	public int getTotalCal() {
		return totalCal;
	}

	public void setTotalCal(int totalCal) {
		this.totalCal = totalCal;
	}

}
