package com.dropbeatzadmin.model;

public class AdminProductModel {

	
	private String product_id;
	private String product_name;
	private String price;
	private String description;
	private String link;
	
	public AdminProductModel(String product_id, String product_name, String price, String description,String link) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.description = description;
		this.link = link;
	}

	public String getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
