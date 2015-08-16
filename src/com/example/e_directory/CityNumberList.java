package com.example.e_directory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CityNumberList {
	
	private static CityNumberList instance = new CityNumberList();	
	private CityNumberList(){
		numbers = new HashMap<String,CityNumber>();
		setUpNumberList();
	}
	
	public static CityNumberList getInstance(){
		return instance;
	}
	
	private HashMap<String, CityNumber> numbers;
	
	
	public ArrayList<String> getListOfCities(){
		ArrayList<String> cities = new ArrayList<String>();
		for (Iterator<String> iterator = numbers.keySet().iterator(); iterator
				.hasNext();) {
			cities.add(iterator.next());
		}
		return cities;
	}
	

	public CityNumber getNumbersOfCity(int cityNum){
		return numbers.get(getListOfCities().get(cityNum));
	}
	
	
	public CityNumber getNumbersOfCity(String city){
		return numbers.get(city);
	}
	
	public boolean setCustomHospNumber(String city, String hospNumber){
		CityNumber temp = numbers.get(city);
		if(temp == null){
			return false;
		}
		
		temp.setCustomHosp(hospNumber);
		numbers.put(city, temp);
		return true;		
	}
	
	public void setUpNumberList(){
		numbers.put("Manila", new CityNumber("Manila",
	"225-3093", 
				"527-3653", 
				"554-8400"));
		
		numbers.put("Quezon City", new CityNumber("Quezon City",
		"924-1025",
		"928-8363",
		"426-1314"));
		
		numbers.put("Caloocan City", new CityNumber("Caloocan City",
		"386-9363",
		"310-6527",
		"364-5588"));
		
		numbers.put("Las Piñas City", new CityNumber("Las Piñas City", 
		"871-8221",
		"873-0611",
		"800-5524"));
		
		numbers.put("Makati City", new CityNumber("Makati City",
		"845-0135",
		"816-4014",
		"888-8999"));
		
		numbers.put("Malabon City", new CityNumber("Malabon City",
		"281-1133",
		"361-9712",
		"294-6352"));
		
		numbers.put("Mandaluyong City", new CityNumber("Mandaluyong City",
		"232-2318",
		"532-2189",
		"464-9999"));
		
		numbers.put("Marikina City", new CityNumber("Marikina City",
		"646-6151",
		"681-0233",
		"682-2222"));
		
		numbers.put("Muntinlupa City", new CityNumber("Muntinlupa City",
		"862-2611",
		"842-2201",
		"775-0511"));
		
		numbers.put("Navotas City", new CityNumber("Navotas City",
		"281-9099",
		"281-0854",
		"281-9091"));
		
		numbers.put("Parañaque City", new CityNumber("Parañaque City",
		"826-2877",
		"826-9131",
		"541-5888"));
	
		numbers.put("Pasay City", new CityNumber("Pasay City",
		"831-7322",
		"844-2120",
		"831-9731"));
		
		numbers.put("Pasig City", new CityNumber("Pasig City",
		"641-1433",
		"641-2815",
		"635-6789"));
		
		numbers.put("Pateros City", new CityNumber("Pateros City",
		"642-8235",
		"641-1365",
		"643-3486"));
	
		numbers.put("San Juan City", new CityNumber("San Juan City",
		"744-2480",
		"725-2079",
		"727-00001"));
		
		numbers.put("Taguig City", new CityNumber("Taguig City",
		"642-3582",
		"837-4496",
		"837-0178"));
		
		numbers.put("Valenzuela City", new CityNumber("Valenzuela City",
		"294-0656",
		"292-3519",
		"291-6801"));	
	}
	
	
}
