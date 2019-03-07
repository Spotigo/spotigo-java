package com.joshuadoes.Spotigo;

import org.json.simple.JSONObject;

public class SpotigoDate {
	
	public long year;
	// public long month;
	// public long day;

	public SpotigoDate(JSONObject json) {
		this.year = (Long) json.get("year");
		// this.month = (Long) json.get("month");
		// this.day = (Long) json.get("day");
	}

}
