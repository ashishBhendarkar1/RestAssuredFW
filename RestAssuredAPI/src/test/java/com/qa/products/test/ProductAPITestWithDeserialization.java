package com.qa.products.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithDeserialization extends BaseTest{
	
	@Test
	public void getProductsTest() {
		
		Response response = restclient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Product[] product = JsonUtils.deserialize(response, Product[].class);
		System.out.println(Arrays.toString(product));
		
		for (Product p : product) {
			System.out.println("id is ="+p.getId());
			System.out.println("title is ="+p.getTitle());
			System.out.println("price is ="+p.getPrice());
			System.out.println("rate is ="+p.getRating().getRate());
			System.out.println("count is ="+p.getRating().getCount());
			
		}
	}

}
