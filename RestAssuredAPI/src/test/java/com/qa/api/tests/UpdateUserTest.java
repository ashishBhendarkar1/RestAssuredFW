package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class UpdateUserTest extends BaseTest{
	
	@Test
	public void updateUserTest() {
		//1. POST: create a user 
		User user = User.builder()
		                  .name("apiname")
		                  .email(StringUtility.getRandomEmailId())
		                  .gender("male")
		                  .status("active")
		                  .build();
		
		Response response = restclient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);		
		Assert.assertEquals(response.getStatusCode(), 201);
		//fetch userid
		String userid = response.jsonPath().getString("id");
		
		
		//2. GET: verify created user is same or not
		Response responseget = restclient.get(BASE_URL_GOREST,"/public/v2/users/"+ userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseget.getStatusCode(),200);
		Assert.assertEquals(responseget.jsonPath().getString("id"), userid);
		
		//update user details using setter method
		user.setGender("female");
		user.setStatus("inactive");
		
		//3. PUT: user same userid
		Response responseput = restclient.put(BASE_URL_GOREST,"/public/v2/users/"+userid, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseput.getStatusCode(),200);
		Assert.assertEquals(responseput.jsonPath().getString("id"), userid);
		Assert.assertEquals(responseput.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responseput.jsonPath().getString("status"), user.getStatus());
		
	}

	}
