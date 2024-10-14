package com.qa.api.base;

import org.testng.annotations.BeforeMethod;

import com.qa.api.client.RestClient;

public class BaseTest {
	
	protected RestClient restclient;
	
	@BeforeMethod
	public void setup() {
		restclient = new RestClient();
	}

}
