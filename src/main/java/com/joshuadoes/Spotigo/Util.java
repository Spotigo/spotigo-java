package com.joshuadoes.Spotigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("deprecation")
public class Util {
	
	@SuppressWarnings("resource")
	public static JSONObject getJSON(String u) {
		HttpClient client = new DefaultHttpClient();
	    HttpGet request = new HttpGet(u);
	    HttpResponse response;
	    String result = null;
	    try {
	        response = client.execute(request);         
	        HttpEntity entity = response.getEntity();

	        if (entity != null) {

	            // A Simple JSON Response Read
	            InputStream instream = entity.getContent();
	            result = convertStreamToString(instream);
	            // now you have the string representation of the HTML request
//	            System.out.println("RESPONSE: " + result);
	            instream.close();
	            if (response.getStatusLine().getStatusCode() != 200) {
	            	System.out.println(response.getStatusLine().getStatusCode());
	                return null;
	            }

	        }
	        JSONParser p = new JSONParser();
		    return (JSONObject) p.parse(result);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return null;
	    }
	    
	}
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	public static String gid2id(String gid) {
		try {
//			System.out.println(String.format("http://joshuadoes.com:8080/util/gid2id/gid?gid=%s", gid));
			JSONObject json = Util.getJSON(String.format("http://joshuadoes.com:8080/util/gid2id/gid?gid=%s", URLEncoder.encode(gid, "UTF-8")));
			return (String) json.get("id");
		} catch(Exception e) {
			return null;
		}
	}

}
