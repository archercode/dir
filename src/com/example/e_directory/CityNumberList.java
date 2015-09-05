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
	"+6322253093", 
				"+6325273653", 
				"+6325548400"));
		
		numbers.put("Quezon", new CityNumber("Quezon City",
		"+6329241025",
		"+6329288363",
		"+6324261314"));
		
		numbers.put("Caloocan", new CityNumber("Caloocan City",
		"+6323869363",
		"+6323106527",
		"+6323645588"));
		
		numbers.put("Las Pinas", new CityNumber("Las Piñas City", 
		"+6328718221",
		"+6328730611",
		"+6328005524"));
		
		numbers.put("Makati", new CityNumber("Makati City",
		"+6328450135",
		"+6328164014",
		"+6328888999"));
		
		numbers.put("Malabon", new CityNumber("Malabon City",
		"+6322811133",
		"+6323619712",
		"+6322946352"));
		
		numbers.put("Mandaluyong", new CityNumber("Mandaluyong City",
		"+6322322318",
		"+6325322189",
		"+6324649999"));
		
		numbers.put("Marikina", new CityNumber("Marikina City",
		"+6326466151",
		"+6326810233",
		"+6326822222"));
		
		numbers.put("Muntinlupa", new CityNumber("Muntinlupa City",
		"+6328622611",
		"+6328422201",
		"+6327750511"));
		
		numbers.put("Navotas", new CityNumber("Navotas City",
		"+6322819099",
		"+6322810854",
		"+6322819091"));
		
		numbers.put("Paranaque", new CityNumber("Parañaque City",
		"+6328262877",
		"+6328269131",
		"+6325415888"));
	
		numbers.put("Pasay", new CityNumber("Pasay City",
		"+6328317322",
		"+6328442120",
		"+6328319731"));
		
		numbers.put("Pasig", new CityNumber("Pasig City",
		"+6326411433",
		"+6326412815",
		"+6326356789"));
		
		numbers.put("Pateros", new CityNumber("Pateros City",
		"+6326428235",
		"+6326411365",
		"+6326433486"));
	
		numbers.put("San Juan", new CityNumber("San Juan City",
		"+6327442480",
		"+6327252079",
		"+63272700001"));
		
		numbers.put("Taguig", new CityNumber("Taguig City",
		"+6326423582",
		"+6328374496",
		"+6328370178"));
		
		numbers.put("Valenzuela", new CityNumber("Valenzuela City",
		"+6322940656",
		"+6322923519",
		"+6322916801"));	
	}
	
	
}
