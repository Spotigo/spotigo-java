// The only difference here is that only the bare minimum album
// information required to provide for a typical song is obtained
// here and returned. This takes significantly less time to parse
// an album than the other available method.

package com.joshuadoes.Spotigo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SpotigoMinimalAlbumInfo {
        public String gid;
        public String name;
        public ArrayList<SpotigoArtistInfo> artist;
        public SpotigoDate date;

        public SpotigoMinimalAlbumInfo(JSONObject json) {
            this.gid = (String) json.get("gid");
            this.name = (String) json.get("name");
            this.date = new SpotigoDate((JSONObject) json.get("date"));
            this.artist = new ArrayList<SpotigoArtistInfo>();
            
            JSONArray alist = (JSONArray) json.get("artist");
            for (Object a : alist) {
                this.artist.add(new SpotigoArtistInfo((JSONObject) a));
            }
        }
}