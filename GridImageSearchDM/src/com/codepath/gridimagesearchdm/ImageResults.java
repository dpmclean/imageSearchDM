package com.codepath.gridimagesearchdm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fullUrl;
	private String thmbUrl;

	public ImageResults(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.thmbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thmbUrl = null;
		}
	}

	public String getFullUrl() {
		return fullUrl;
	}
	public String getThmbUrl() {
		return thmbUrl;
	}
	public String toString() {
		return this.thmbUrl;
	}

	public static ArrayList<ImageResults> fromJSONArray(
			JSONArray array ) {
		ArrayList<ImageResults> results = new ArrayList<ImageResults>();
		for (int idx = 0; idx < array.length(); idx++ ) {
			try {
				results.add(new ImageResults( array.getJSONObject(idx) ));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	
	
}
