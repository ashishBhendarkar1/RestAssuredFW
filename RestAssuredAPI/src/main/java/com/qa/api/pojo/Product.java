package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	private Integer id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating{
		private double rate;
		private Integer count;
	}

}
