package com.joshuadoes.Spotigo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class SpotigoClient {
	
	private String host;
	private String pass;
	
	public SpotigoClient(String host, String pass) {
		this.host = host;
		this.pass = pass;
	}
	
	public String getHost() {
		return this.host;
	}

	public String getPass() {
		return pass;
	}
	
	public Album getAlbumInfo(String url) {
		Album data = new Album();
		String regex = "^(https:\\/\\/open.spotify.com\\/album\\/|spotify:album:)([a-zA-Z0-9]+)(.*)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		String albumID;
		if (m.find()) {
			albumID = m.group(2);
		} else {
			return null;
		}
		String rurl = String.format("http://%s/album/%s?pass=%s", this.host, albumID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:album:%s", albumID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject ajson = Util.getJSON(rurl);
		SpotigoAlbumInfo info = new SpotigoAlbumInfo(ajson);
		
		data.albumID = albumID;
		data.title = info.name;
		data.uri = String.format("spotify:album:%s", albumID);
		data.artURL = embed.thumbnailURL;
		data.discs = new ArrayList<Disc>();
		
		for(SpotigoDiscInfo dinfo : info.discs) {
			data.discs.add(this.getDiscInfo(dinfo));
		}
		
		return data;
	}
	
	public Album getAlbumInfo(SpotigoGID gid) {
		Album data = new Album();
		String albumID = gid.id;
		
		String rurl = String.format("http://%s/album/%s?pass=%s", this.host, albumID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:album:%s", albumID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject ajson = Util.getJSON(rurl);
		SpotigoAlbumInfo info = new SpotigoAlbumInfo(ajson);
		
		data.albumID = albumID;
		data.title = info.name;
		data.uri = String.format("spotify:album:%s", albumID);
		data.artURL = embed.thumbnailURL;
		data.discs = new ArrayList<Disc>();
		
		for(SpotigoDiscInfo dinfo : info.discs) {
			data.discs.add(this.getDiscInfo(dinfo));
		}
		
		return data;
	}
	
	public Track getTrackInfo(String url) {
		Track data = new Track();
		String regex = "^(https:\\/\\/open.spotify.com\\/track\\/|spotify:track:)([a-zA-Z0-9]+)(.*)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		String trackID;
		if (m.find()) {
			trackID = m.group(2);
		} else {
			return null;
		}
		String rurl = String.format("http://%s/track/%s?pass=%s", this.host, trackID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:track:%s", trackID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject tjson = Util.getJSON(rurl);
		SpotigoTrackInfo info = new SpotigoTrackInfo(tjson);
		
		data.title = info.name;
		data.duration = info.duration;
		data.artURL = embed.thumbnailURL;
		data.trackID = trackID;
		data.uri = String.format("spotify:track:%s", trackID);
		data.streamURL = String.format("http://%s/download/%s?pass=%s", this.host, trackID, this.pass);
		data.artists = new ArrayList<Artist>();
		for (SpotigoArtistInfo ainfo : info.artist) {
			data.artists.add(this.getArtistInfo(new SpotigoGID(ainfo.gid)));
		}
		data.artist = data.artists.get(0).name;
		
		return data;
	}

	public Track getTrackInfo(SpotigoGID gid) {
		Track data = new Track();
		String trackID = gid.id;
//		System.out.println(trackID);

		String rurl = String.format("http://%s/track/%s?pass=%s", this.host, trackID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:track:%s", trackID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject tjson = Util.getJSON(rurl);
		SpotigoTrackInfo info = new SpotigoTrackInfo(tjson);
		
		data.title = info.name;
		data.duration = info.duration;
		data.artURL = embed.thumbnailURL;
		data.trackID = trackID;
		data.uri = String.format("spotify:track:%s", trackID);
		data.streamURL = String.format("http://%s/download/%s?pass=%s", this.host, trackID, this.pass);
		data.artists = new ArrayList<Artist>();
		for (SpotigoArtistInfo ainfo : info.artist) {
			data.artists.add(this.getArtistInfo(new SpotigoGID(ainfo.gid)));
		}
		data.artist = data.artists.get(0).name;
		
		return data;
	}
	
	public Artist getArtistInfo(String url) {
		Artist data = new Artist();
		String regex = "^(https:\\/\\/open.spotify.com\\/artist\\/|spotify:album:)([a-zA-Z0-9]+)(.*)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		String artistID;
		if (m.find()) {
			artistID = m.group(2);
		} else {
			return null;
		}
		String rurl = String.format("http://%s/artist/%s?pass=%s", this.host, artistID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:artist:%s", artistID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject ajson = Util.getJSON(rurl);
		SpotigoArtistInfo info = new SpotigoArtistInfo(ajson);
		
		data.name = info.name;
		data.artistID = artistID;
		data.artURL = embed.thumbnailURL;
		data.uri = data.uri = String.format("spotify:artist:%s", artistID);
		
		return data;
	}
	
	public Artist getArtistInfo(SpotigoGID gid) {
		Artist data = new Artist();
		String artistID = gid.id;
		
		String rurl = String.format("http://%s/artist/%s?pass=%s", this.host, artistID, this.pass);
		String eurl = String.format("https://embed.spotify.com/oembed?url=spotify:artist:%s", artistID);
		JSONObject ejson = Util.getJSON(eurl);
		SpotigoEmbed embed = new SpotigoEmbed(ejson);
		JSONObject ajson = Util.getJSON(rurl);
		SpotigoArtistInfo info = new SpotigoArtistInfo(ajson);
		
		data.name = info.name;
		data.artistID = artistID;
		data.artURL = embed.thumbnailURL;
		data.uri = data.uri = String.format("spotify:artist:%s", artistID);
		
		return data;
	}
	
	public Disc getDiscInfo(SpotigoDiscInfo info) {
		Disc data = new Disc();
		data.number = info.number;
		data.tracks = new ArrayList<Track>();
		
		for (SpotigoGID gid : info.tracks) {
			data.tracks.add(this.getTrackInfo(gid));
		}
		
		return data;
	}

}
