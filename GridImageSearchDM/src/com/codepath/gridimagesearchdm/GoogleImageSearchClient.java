package com.codepath.gridimagesearchdm;

import com.loopj.android.http.AsyncHttpClient;

public class GoogleImageSearchClient {

    private final String API_BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";
    		
    private AsyncHttpClient client;

    public GoogleImageSearchClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    //public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
    //    String url = getApiUrl("lists/movies/box_office.json");
    //    RequestParams params = new RequestParams("apikey", API_KEY);
    //    client.get(url, params, handler);
    //}

	
}
