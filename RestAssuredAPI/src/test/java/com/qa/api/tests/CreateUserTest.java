package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{

	//POST
	@Test
	public void createUserTest() {
		
		User user = new User(null, "ashish", StringUtility.getRandomEmailId(), "male", "active");
		
		Response response = restclient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	
	@Test
	public void createUserWithBuilderTest() {
  //1. POST
	 User user = User.builder()
	              .name("APIname")
	               .email(StringUtility.getRandomEmailId())
	               .gender("male")
	               .status("active")
	               .build();
		
		Response response = restclient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		//fetch userid
		String userid = response.jsonPath().getString("id");
		System.out.println("User ID is ====> "+userid);
		
   //2.GET
		Response responseget = restclient.get("/public/v2/users/" + userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseget.jsonPath().getString("id"), userid);
		Assert.assertEquals(responseget.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseget.jsonPath().getString("email"), user.getEmail());
		Assert.assertEquals(responseget.jsonPath().getString("status"), user.getStatus());
	}
	
	@Test
	public void createUserUsingJsonTest() {
		
	   File jsonfile = new File(".//src/test/resources/jsons/user.json");
		
		Response response = restclient.post("/public/v2/users", jsonfile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	

}
