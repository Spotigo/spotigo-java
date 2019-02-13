package com.joshuadoes.Spotigo;

import org.json.simple.JSONObject;

public class SpotigoEmbed {
	
	public String thumbnailURL;
	
	public SpotigoEmbed(JSONObject json) {
		this.thumbnailURL = (String) json.get("thumbnail_url");
	}

}
