package com.joshuadoes.Spotigo;

import org.json.simple.JSONObject;

public class SpotigoGID {
	
	public String gid;
	public String id;
	
	public SpotigoGID(JSONObject json) {
		this.gid = (String) json.get("gid");
		this.id = Util.gid2id(gid);
	}
	
	public SpotigoGID(String gid) {
		this.gid = gid;
		this.id = Util.gid2id(gid);
	}
}
