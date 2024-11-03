package com.qa.api.schemavalidation.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class GoRestAPISchemaTest extends BaseTest{
	
	@Test 
	public void gorestAPISchemaTest() {
		
		RestAssured.given()
		              .baseUri("https://gorest.co.in")
		              .header("Authorization", "Bearer " +ConfigManager.get("bearerToken"))
		              .when()
		              .get("/public/v2/users/7478770")
		              .then().log().all()
		              .assertThat()
		              .statusCode(200)
		              .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/gorest-schema.json"));
	}

}
