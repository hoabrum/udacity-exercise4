package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModifyCartRequest {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("item_id")
	private long itemId;
	
	@JsonProperty("quantity")
	private int quantity;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
