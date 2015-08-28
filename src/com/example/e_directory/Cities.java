package com.example.e_directory;

public enum Cities {
	MANILA("Manila"),
	QUEZON("Quezon City"), 
	CALOOCAN("Caloocan City"),
	LAS_PINAS("Las Piñas City"), 
	MAKATI("Makati City"), 
	MALABON("Malabon City"),
	MANDALUYONG("Mandaluyong City"), 
	MARIKINA("Marikina City"),
	MUNTINLUPA("Muntinlupa City"), 
	NAVOTAS("Navotas City"), 
	PARANAQUE("Parañaque City"), 
	PASAY("Pasay City"),
	PASIG("Pasig City"), 
	PATEROS("Pateros City"), 
	SAN_JUAN("San Juan City"), 
	TAGUIG("Taguig City"), 
	VALENZUELA("Valenzuela City"), 
	NONE("none");
	
	private String strCity;
	private Cities(String s){
		strCity = s;	
	}
	
	public String getStringLabel(){
		return strCity;
	}
	
	public Cities getCity(String s){
		s = s.toLowerCase();
		switch(s){
			case "manila": 
				return Cities.MANILA;
			case "quezon":
				return Cities.QUEZON;
			case "caloocan":
				return Cities.CALOOCAN;
			case "las pinas": 
			case "las piñas":
				return Cities.LAS_PINAS;
			case "makati":
				return Cities.MAKATI;
			case "malabon":
				return Cities.MALABON;
			case "mandaluyong":
				return Cities.MANDALUYONG;
			case "marikina":
				return Cities.MARIKINA;
			case "muntinlupa":
				return Cities.MUNTINLUPA;
			case "navotas":
				return Cities.NAVOTAS;
			case "paranaque":
			case "parañaque":
				return Cities.PARANAQUE;
			case "pasay":
				return Cities.PASAY;
			case "pasig":
				return Cities.PASIG;
			case "pateros": return Cities.PATEROS;
			case "taguig": return Cities.TAGUIG;
			case "valenzuela": return Cities.VALENZUELA;
			default: return Cities.NONE;
		}
		
	}
	
	
}
