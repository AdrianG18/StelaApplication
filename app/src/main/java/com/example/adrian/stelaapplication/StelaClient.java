package com.example.adrian.stelaapplication;


import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by parkerandrews on 2/21/18.
 */

// TODO: A BUNCH OF FREAKING STUFF
public class StelaClient extends /*AsyncHttpClient or */ OAuthBaseClient {

    public static final String REST_URL= "";

    public static final BaseApi REST_API_INSTANCE = TwitterApi.instance(); // Change this
   // public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "PAcple6bRH2101sI5fT1kIKi5";       // Change this
    public static final String REST_CONSUMER_SECRET = "7KGmlg2A3GajHucjOPg9Ae78l8t4UqlWT4bbPDEFDtdyHWnd2D"; // Change this

    // Landing page to indicate the OAuth flow worked in case Chrome for Android 25+ blocks navigation back to the app.
    public static final String FALLBACK_URL = "https://codepath.github.io/android-rest-client-template/success.html";

    // See https://developer.chrome.com/multidevice/android/intents
    public static final String REST_CALLBACK_URL_TEMPLATE = "intent://%s#Intent;action=android.intent.action.VIEW;scheme=%s;package=%s;S.browser_fallback_url=%s;end";


    public StelaClient(Context c, BaseApi apiInstance, String consumerUrl, String consumerKey, String consumerSecret, String callbackUrl) {
        super(c, apiInstance, consumerUrl, consumerKey, consumerSecret, callbackUrl);

    }

    /**
     * Constructor that calls the super constructor
     * params: context
     */
    public StelaClient(Context context) {
        super(context, REST_API_INSTANCE,
                REST_URL,
                REST_URL,
                REST_URL,
                REST_URL);
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
