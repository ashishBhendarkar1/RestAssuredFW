package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	@Test
	public void deleteUserTest() {
		//get
		Response getresponse =restclient.get("/public/v2/users/7469258", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		int userid = getresponse.then().extract().path("id");
		
		Assert.assertEquals(userid, 7469258);
		System.out.println("-------------------------------");
		//delete
		Response deleteresponse = restclient.delete("/public/v2/users/7469258", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(deleteresponse.getStatusCode(), 204);
		System.out.println("-------------------------------");
		//get
		Response getdeleteuseridresponse =restclient.get("/public/v2/users/7469258", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
						
		Assert.assertEquals(getdeleteuseridresponse.getStatusCode(), 404);
		
	}

}
