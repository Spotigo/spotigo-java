package com.joshuadoes.Spotigo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SpotigoDiscInfo {
	
	public long number;
	public ArrayList<SpotigoGID> tracks;
	
	public SpotigoDiscInfo(JSONObject json) {
		this.number = (Long) json.get("number");
		this.tracks = new ArrayList<SpotigoGID>();
		JSONArray tlist = (JSONArray) json.get("track");
		for (Object t : tlist) {
			tracks.add(new SpotigoGID((JSONObject) t));
		}
	}

}
