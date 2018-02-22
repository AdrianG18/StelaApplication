package com.example.adrian.stelaapplication;


import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by parkerandrews on 2/21/18.
 */

// TODO: A BUNCH OF FREAKING STUFF
public class StelaClient extends /*AsyncHttpClient or */ OAuthBaseClient {

    public static final String REST_URL= "";

    public StelaClient(Context c, BaseApi apiInstance, String consumerUrl, String consumerKey, String consumerSecret, String callbackUrl) {
        super(c, apiInstance, consumerUrl, consumerKey, consumerSecret, callbackUrl);

    }



    /**
     * Method to send a movement request to the Stela Server
     */
    public void sendMovement(double[] coords, AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "/movement/update.json";

        RequestParams params = new RequestParams();
        params.put("coordinates", coords);
        client.post(apiUrl, params, handler);

    }
}
