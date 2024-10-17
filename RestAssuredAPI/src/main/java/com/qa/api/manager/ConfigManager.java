package com.qa.api.manager;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class ConfigManager {
	
	private static Properties properties = new Properties();
	
	private static String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties";

//1 way - using class loader method	
//	static {		
//		try(InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")){
//				if(input !=null) {
//					properties.load(input);
//				}	
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//	}
		

	
// 2 way - using FileInputStream	
	static {
  try {
	FileInputStream file = new FileInputStream(filepath);
	if(file !=null) {
		properties.load(file);
	}	
  } catch (IOException e) {
	e.printStackTrace();
}
}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	public static void set(String key, String value) {
		 properties.setProperty(key,value);
	}
	
	
	@Test
	public void getvalue() {
		System.out.println(get("baseUrl"));
	}
	
	@Test
	public void setvalue() {
		set("baseUrl","https://reqres.in");
		System.out.println("---"+get("baseUrl"));
	}
	
}
