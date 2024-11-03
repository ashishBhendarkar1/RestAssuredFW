package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {
	
	public static boolean validateSchema(Response response, String schemaFilename) {
		
		try {
			
				response
		        .then()
		        .assertThat()
		        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilename));
				
				System.out.println("schema validation passed");
				return true;
		} catch (Exception e) {
			System.out.println("schema validation failed"+ e.getMessage());
			return false;
		}
	}

}
