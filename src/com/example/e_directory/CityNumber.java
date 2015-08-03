package com.example.e_directory;

public class CityNumber {

	private String city; 
	private String hospNum, fireNum, policeNum, customHosp;
	
	public CityNumber(String city, String pNum, String fNum, String hNum){
		
		this.city = city; 
		this.fireNum = fNum; 
		this.hospNum = hNum; 
		this.policeNum = pNum;
		this.customHosp = null;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHospNum() {
		return hospNum;
	}
	public void setHospNum(String hospNum) {
		this.hospNum = hospNum;
	}
	public String getFireNum() {
		return fireNum;
	}
	public void setFireNum(String fireNum) {
		this.fireNum = fireNum;
	}
	public String getPoliceNum() {
		return policeNum;
	}
	public void setPoliceNum(String policeNum) {
		this.policeNum = policeNum;
	}
	public String getCustomHosp() {
		return customHosp;
	}
	public void setCustomHosp(String customHosp) {
		this.customHosp = customHosp;
	}
	
}
