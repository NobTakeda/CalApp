package model;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String name;
	private Double height;
	private Double weight;
	private Double bmi;
	private int cal;
	private int targetCal;
	private String userid;
	private String userpass;

	public User() {}
	public User(String name,Double height,Double weight) {
		this.name=name;
		this.height=height;
		this.weight=weight;
	}
	public User(int id,String name,Double height,Double weight,Double bmi,int cal,int targetCal,String userid) {
		this.id=id;
		this.name=name;
		this.height=height;
		this.weight=weight;
		this.bmi=bmi;
		this.cal=cal;
		this.targetCal=targetCal;
		this.userid=userid;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getName() {
		return name;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	public int getCal() {
		return cal;
	}
	public void setCal(int cal) {
		this.cal = cal;
	}
	public int getTargetCal() {
		return targetCal;
	}
	public void setTargetCal(int targetCal) {
		this.targetCal = targetCal;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
