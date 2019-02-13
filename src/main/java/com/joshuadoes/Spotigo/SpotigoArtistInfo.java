package com.joshuadoes.Spotigo;

import org.json.simple.JSONObject;

public class SpotigoArtistInfo {
	
	public String gid;
	public String name;
//	public SpotigoTopTracks[] topTracks;
//	public SpotigoAlbums[] albums;
//	public SpotigoAlbums[] singles;
	
	private JSONObject json;
	
	public SpotigoArtistInfo(JSONObject json) {
		this.gid = (String) json.get("gid");
		this.name = (String) json.get("name");
		
		this.json = json;
	}
	
	public Object get(Object key) {
		return this.json.get(key);
	}

}
