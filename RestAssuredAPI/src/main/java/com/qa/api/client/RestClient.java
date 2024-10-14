 package com.qa.api.client;

import java.io.File;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;

public class RestClient {
	
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	
	private String baseurl = ConfigManager.get("baseUrl");
	
	private RequestSpecification setupRequest(AuthType authtype, ContentType contentType) {
		RequestSpecification request =RestAssured.given().log().all()
		                                   .baseUri(baseurl)
		                                   .contentType(contentType)
		                                   .accept(contentType);
		
		
		switch (authtype) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " +ConfigManager.get("bearerToken"));
			break;
		case OAUTH2:
			request.header("Authorization", "Bearer "+generateOAUTH2Token());
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic ");
			break;
		case API_KEY:
			request.header("x-api-key", ConfigManager.get("API_KEY"));
			break;
		case NO_AUTH:
			System.out.println("Auth is not required ...");
			break;
		default:
			System.out.println("This Auth is not supported ...please pass the right AuthType");
			throw new FrameworkException("NO AUTH SUPPORTED");
		}  
		   return request;
	}
	
	private String generateOAUTH2Token() {
		return RestAssured.given()
				            .formParam("client_id", ConfigManager.get("clientId"))
				            .formParam("client_secret", ConfigManager.get("clientSecret"))
				            .formParam("grant_type", ConfigManager.get("grantType"))
				            .post(ConfigManager.get("tokenUrl"))
				            .then()
				            .extract()
				            .path("access_token");
	}
	
	//******************CURD method*********************
	
    /**
     * This method is used to call GET call
     * @param endpoint
     * @param queryParam
     * @param pathParam
     * @param authtype
     * @param contentType
     * @return it returns the get api response
     */
	public Response get(String endpoint,Map<String, String> queryParam , Map<String, String> pathParam, AuthType authtype, ContentType contentType) {
		RequestSpecification request = setupRequest(authtype, contentType);
		
		if(queryParam != null) {
			request.queryParams(queryParam);
		}
		
		if(pathParam != null) {
			request.pathParams(pathParam);
		}
		
	Response response = request.get(endpoint)
		                .then()
		                  .spec(responseSpec200)
		                    .and()
		                     .extract()
		                        .response();
	response.prettyPrint();
	return response;	
	}
	
	/**
	 * This method is used to call POST call
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authtype
	 * @param contentType
	 * @return it returns the post api response
	 */
	public <T> Response post(String endpoint, T body ,Map<String, String> queryParam , Map<String, String> pathParam,
		                      	AuthType authtype, ContentType contentType) {
	
		RequestSpecification request = setupRequest(authtype, contentType);
		applyParam(request,queryParam,pathParam);
		
		Response response = request.body(body)
		        .post(endpoint)
		         .then()
		          .spec(responseSpec201)
		           .and()
		            .extract()
		             .response();
		response.prettyPrint();
		return response;
		
	}
	
	/**
	 * This method is used to call POST Apis for the Jsonfile body
	 * @param endpoint
	 * @param file
	 * @param queryParam
	 * @param pathParam
	 * @param authtype
	 * @param contentType
	 * @return it returns the post api response
	 */
	public Response post(String endpoint, File file ,Map<String, String> queryParam , Map<String, String> pathParam,
          	AuthType authtype, ContentType contentType) {

           RequestSpecification request = setupRequest(authtype, contentType);
           applyParam(request,queryParam,pathParam);

           Response response = request.body(file)
                                       .post(endpoint)
                                       .then()
                                       .spec(responseSpec201)
                                       .and()
                                       .extract()
                                       .response();
          response.prettyPrint();
          return response;

}
	
	private void applyParam(RequestSpecification request, Map<String, String> queryParam , Map<String, String> pathParam) {
		if(queryParam != null) {
			request.queryParams(queryParam);
		}
		
		if(pathParam != null) {
			request.pathParams(pathParam);
		}
	}
	
	/**
	 * This method is used to call PUT Apis
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authtype
	 * @param contentType
	 * @return it returns the put api response
	 */
	public <T> Response put(String endpoint, T body ,Map<String, String> queryParam , Map<String, String> pathParam,
          	           AuthType authtype, ContentType contentType) {
		RequestSpecification request = setupRequest(authtype, contentType);
		applyParam(request,queryParam,pathParam);
		
		Response response = request.body(body)
		                           .put(endpoint)
		                           .then()
		                           .spec(responseSpec200)
		                           .and()
		                           .extract()
		                           .response();
		response.prettyPrint();
		return response;
	}
	
	
	/**
	 * This method is used to call PATCH Apis
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authtype
	 * @param contentType
	 * @return it returns the patch api response
	 */
	public <T> Response patch(String endpoint, T body ,Map<String, String> queryParam , Map<String, String> pathParam,
	           AuthType authtype, ContentType contentType) {
         RequestSpecification request = setupRequest(authtype, contentType);
         applyParam(request,queryParam,pathParam);

         Response response = request.body(body)
                        .patch(endpoint)
                        .then()
                        .spec(responseSpec200)
                        .and()
                        .extract()
                        .response();
        response.prettyPrint();
        return response;
}
	
	/**
	 * This method is used to call DELETE apis
	 * @param endpoint
	 * @param queryParam
	 * @param pathParam
	 * @param authtype
	 * @param contentType
	 * @return it returns the deleteapi response
	 */
	public Response delete(String endpoint, Map<String, String> queryParam , Map<String, String> pathParam,
	           AuthType authtype, ContentType contentType) {
      RequestSpecification request = setupRequest(authtype, contentType);
      applyParam(request,queryParam,pathParam);

      Response response = request.delete(endpoint)
                     .then()
                     .spec(responseSpec204)
                     .and()
                     .extract()
                     .response();
     response.prettyPrint();
     return response;
}	
	
}

   
