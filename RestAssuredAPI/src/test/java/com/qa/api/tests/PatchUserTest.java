package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class PatchUserTest extends BaseTest{
	
	@Test
	public void patchUserTest() {
		//1. POST: create a user 
		User user = User.builder()
		                  .name("apiname")
		                  .email(StringUtility.getRandomEmailId())
		                  .gender("male")
		                  .status("active")
		                  .build();
		
		Response response = restclient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);		
		Assert.assertEquals(response.getStatusCode(), 201);
		//fetch userid
		String userid = response.jsonPath().getString("id");
		
		
		//2. GET: verify created user is same or not
		Response responseget = restclient.get("/public/v2/users/"+ userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseget.getStatusCode(),200);
		Assert.assertEquals(responseget.jsonPath().getString("id"), userid);
		
		//update user details using setter method
		user.setEmail(StringUtility.getRandomEmailId());
		
		//3. PATCH:update user deatil using same userid
		Response responseput = restclient.patch("/public/v2/users/"+userid, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseput.getStatusCode(),200);
		Assert.assertEquals(responseput.jsonPath().getString("id"), userid);
		Assert.assertEquals(responseput.jsonPath().getString("email"), user.getEmail());
		
	}

	}
