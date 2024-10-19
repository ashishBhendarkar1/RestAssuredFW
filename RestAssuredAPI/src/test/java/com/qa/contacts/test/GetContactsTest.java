package com.qa.contacts.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetContactsTest extends BaseTest {

	
	public String getToken() {
		
		ContactsCredentials cred = ContactsCredentials.builder()
		                    .email("apitest3@gmail.com")
		                    .password("apitest@123")
		                    .build();
		
		Response response = restclient.post("/users/login", cred, null, null, AuthType.NO_AUTH, ContentType.JSON);
		String token = response.jsonPath().getString("token");
		System.out.println("contacts token id==" +token);	
		return token;
	}
	   
	@Test
	public void getContactsTest() {
		
		String token = getToken();
		ConfigManager.set("contacts_bearer_token", token);
		Response response = restclient.get("/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	                                     
}
