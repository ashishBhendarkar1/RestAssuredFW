package com.qa.products.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPathValidator extends BaseTest {
	
	@Test
	public void getProductTest() {
		
		Response response = restclient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		List<Number> prices = JsonPathValidator.readList(response, "$[?(@.price > 50)].price");
		System.out.println("price greater then 50 ="+prices);
		
		List<Integer> ids = JsonPathValidator.readList(response, "$[?(@.price > 50)].id");
		System.out.println("ids ="+ids);
		
		//get map:
		List<Map<String, Object>> jeweleryList = JsonPathValidator.readListOfMaps(response, "$[?(@.category == 'jewelery')].['title','price']");
		System.out.println(jeweleryList.size());
		
		for (Map<String, Object> product : jeweleryList) {
			String titles = (String) product.get("title");
			Number price = (Number) product.get("price");
			System.out.println("title is :"+titles);
			System.out.println("price is :"+price);
		}
		
		//get min price
		Double minprice = JsonPathValidator.read(response, "min($[*].price)");
		System.out.println("min price is :"+minprice);
	}

}
