package com.qa.api.schemavalidation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ProductAPISchemaTest extends BaseTest{
	
	@Test 
	public void productAPISchemaTest() {
		
/**		RestAssured.given()
		              .baseUri("https://fakestoreapi.com")
		              .when()
		              .get("/products")
		              .then().log().all()
		              .assertThat()
		              .statusCode(200)
		//              .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/productschema.json"));
		.body(JsonSchemaValidator.matchesJsonSchema("{\r\n" + 
				"	\"$schema\": \"http://json-schema.org/draft-07/schema#\",\r\n" + 
				"	\"title\": \"Generated schema for Root\",\r\n" + 
				"	\"type\": \"array\",\r\n" + 
				"	\"items\": {\r\n" + 
				"		\"type\": \"object\",\r\n" + 
				"		\"properties\": {\r\n" + 
				"			\"id\": {\r\n" + 
				"				\"type\": \"number\"\r\n" + 
				"			},\r\n" + 
				"			\"title\": {\r\n" + 
				"				\"type\": \"string\"\r\n" + 
				"			},\r\n" + 
				"			\"price\": {\r\n" + 
				"				\"type\": \"number\"\r\n" + 
				"			},\r\n" + 
				"			\"description\": {\r\n" + 
				"				\"type\": \"string\"\r\n" + 
				"			},\r\n" + 
				"			\"category\": {\r\n" + 
				"				\"type\": \"string\"\r\n" + 
				"			},\r\n" + 
				"			\"image\": {\r\n" + 
				"				\"type\": \"string\"\r\n" + 
				"			},\r\n" + 
				"			\"rating\": {\r\n" + 
				"				\"type\": \"object\",\r\n" + 
				"				\"properties\": {\r\n" + 
				"					\"rate\": {\r\n" + 
				"						\"type\": \"number\"\r\n" + 
				"					},\r\n" + 
				"					\"count\": {\r\n" + 
				"						\"type\": \"number\"\r\n" + 
				"					}\r\n" + 
				"				},\r\n" + 
				"				\"required\": [\r\n" + 
				"					\"rate\",\r\n" + 
				"					\"count\"\r\n" + 
				"				]\r\n" + 
				"			}\r\n" + 
				"		},\r\n" + 
				"		\"required\": [\r\n" + 
				"			\"id\",\r\n" + 
				"			\"title\",\r\n" + 
				"			\"price\",\r\n" + 
				"			\"description\",\r\n" + 
				"			\"category\",\r\n" + 
				"			\"image\",\r\n" + 
				"			\"rating\"\r\n" + 
				"		]\r\n" + 
				"	}\r\n" + 
				"}"));
		**/
		
		Response response = restclient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		
		Assert.assertEquals(SchemaValidator.validateSchema(response, "schema/productschema.json") , true);
				
	}

}
