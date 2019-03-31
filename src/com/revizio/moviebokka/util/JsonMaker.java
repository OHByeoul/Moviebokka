package com.revizio.moviebokka.util;

import com.google.gson.Gson;

public class JsonMaker {
	private Gson gson;
	

	public JsonMaker() {
		gson = new Gson();
	}
	
	public String convertObjectToJson(Object obj) {
		String json = gson.toJson(obj);
		return json;
	}
	public Gson getGson() {
		return gson;
	}
	
	public void setGson(Gson gson) {
		this.gson = gson;
	}
}
