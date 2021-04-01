package model;

import java.io.Serializable;

public class Meals implements Serializable{
	private int breakfastCal;
	private int lunchCal;
	private int supperCal;
	private int totalCal;
	private String date;
	private int id;

	public Meals(){
	}
	public Meals(String date) {
		this.date=date;
	}
	public Meals(int breakfastCal,int lunchCal,int supperCal,int totalCal,String date) {
		this(date);
		this.breakfastCal=breakfastCal;
		this.lunchCal=lunchCal;
		this.supperCal=supperCal;
		this.totalCal=totalCal;
	}
	public Meals(int id,int breakfastCal,int lunchCal,int supperCal,int totalCal,String date) {
		this(breakfastCal,lunchCal,supperCal,totalCal,date);
		this.id=id;
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

}
