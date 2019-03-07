package com.joshuadoes.Spotigo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SpotigoTrackInfo {
	
	public String gid;
	public String name;
	public long trackNumber;
	public long discNumber;
	public long duration;
	public String albumGid;
	public ArrayList<SpotigoArtistInfo> artist;
	
	private JSONObject json;
	
	public SpotigoTrackInfo(JSONObject json) {
		this.gid = (String) json.get("gid");
		this.name = (String) json.get("name");
		this.trackNumber = (Long) json.get("number");
		this.discNumber = (Long) json.get("disc_number");
		this.duration = (Long) json.get("duration");
		this.artist = new ArrayList<SpotigoArtistInfo>();
		
		JSONArray alist = (JSONArray) json.get("artist");
		for (Object a : alist) {
			this.artist.add(new SpotigoArtistInfo((JSONObject) a));
		}

		JSONObject albumObject = (JSONObject) json.get("album");
		this.albumGid = (String) albumObject.get("gid"); 
		
		this.json = json;
	}
	
	public Object get(Object key) {
		return this.json.get(key);
	}

}
