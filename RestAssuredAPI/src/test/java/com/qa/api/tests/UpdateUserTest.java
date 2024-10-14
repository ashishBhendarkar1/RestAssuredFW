package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class UpdateUserTest extends BaseTest{
	
	@Test
	public void updateUserTest() {
		User user = User.builder()
		               .name("ashish")
		                  .email("email@gmail.com")
		                  .gender("male")
		                  .status("active")
		                  .build();
		
		Response response = restclient.put("/public/v2/users/7440218", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test
	public void partialupdateUserTest() {
		User user = User.builder()
		                  .status("inactive")
		                  .build();
		
		Response response = restclient.patch("/public/v2/users/7440218", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
