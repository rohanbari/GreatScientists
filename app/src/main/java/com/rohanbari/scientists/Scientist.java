package com.rohanbari.scientists;

import com.google.gson.annotations.SerializedName;

public class Scientist {

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("wikiUrl")
	private String wikiUrl;

	@SerializedName("description")
	private String description;

	public String getImage(){
		return image;
	}

	public String getName(){
		return name;
	}

	public String getWikiUrl(){
		return wikiUrl;
	}

	public String getDescription(){
		return description;
	}
}