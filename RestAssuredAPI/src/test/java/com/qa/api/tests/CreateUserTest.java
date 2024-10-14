package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{

	@Test
	public void createUserTest() {
		
		User user = new User("ashish", "ashishapifw@gmail.com", "male", "active");
		
		Response response = restclient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void createUserWithBuilderTest() {
		
	 User user = User.builder()
	              .name("ashishapi")
	               .email("ashishapifwww@api.com")
	               .gender("male")
	               .status("active")
	               .build();
		
		Response response = restclient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void createUserUsingJsonTest() {
		
	   File jsonfile = new File(".//src/test/resources/jsons/user.json");
		
		Response response = restclient.post("/public/v2/users", jsonfile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	

}
