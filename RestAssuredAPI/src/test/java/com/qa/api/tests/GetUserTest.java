package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getUserTest() {
		Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("name", "naveen");
			queryParam.put("status", "active");
			
		
		Response response = restclient.get("/public/v2/users", queryParam, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);		

	}
	
	@Test
	public void getSingleUserTest() {
	
		Response response = restclient.get("/public/v2/users/7440218", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		int id = response.then().extract().path("id");
		
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(id, 7440218);

	}

}
