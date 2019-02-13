package com.joshuadoes.Spotigo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SpotigoAlbumInfo {
	
	public String gid;
	public String name;
	public ArrayList<SpotigoArtistInfo> artist;
	public ArrayList<SpotigoDiscInfo> discs;
	public SpotigoDate date;
	
//	private JSONObject json;
	
	public SpotigoAlbumInfo(JSONObject json) {
		this.gid = (String) json.get("gid");
		this.name = (String) json.get("name");
		// this.discs = (SpotigoDiscInfo[]) json.get("disc");
		this.date = new SpotigoDate((JSONObject) json.get("date"));

		this.artist = new ArrayList<SpotigoArtistInfo>();
		this.discs = new ArrayList<SpotigoDiscInfo>();
		
		JSONArray alist = (JSONArray) json.get("artist");
		for (Object a : alist) {
			this.artist.add(new SpotigoArtistInfo((JSONObject) a));
		}

		JSONArray dlist = (JSONArray) json.get("disc");
		for (Object d : dlist) {
			this.discs.add(new SpotigoDiscInfo((JSONObject) d));
		}
		
//		this.json = json;
	}

}
