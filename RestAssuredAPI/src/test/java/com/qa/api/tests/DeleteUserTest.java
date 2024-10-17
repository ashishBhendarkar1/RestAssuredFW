package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	@Test
	public void deleteUserTest() {
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
				
		//3. delete: delete newly create userid
		Response deleteresponse = restclient.delete("/public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(deleteresponse.getStatusCode(), 204);
		System.out.println("-------------------------------");
		
		//4. get: recheck with the same userid
		Response getdeleteuseridresponse =restclient.get("/public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
						
		Assert.assertEquals(getdeleteuseridresponse.getStatusCode(), 404);
		Assert.assertEquals(getdeleteuseridresponse.jsonPath().getString("message"), "Resource not found");
		
	}

}
