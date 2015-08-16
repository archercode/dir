package com.example.e_directory;

public enum UserInfo {
	NAME_FIRST("userInfo_firstName"),
	NAME_LAST("userInfo_lastName"),
	EMAIL("userInfo_email"),
	MOBILE("userInfo_mobile"),
	GENDER("userInfo_gender"),
	CITY("userInfo_city"),
	BDAY("userInfo_bday");
	
	private String key;
	private UserInfo(String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
}
