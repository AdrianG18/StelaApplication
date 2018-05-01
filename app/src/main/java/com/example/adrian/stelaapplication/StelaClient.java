package com.example.adrian.stelaapplication;


import android.content.Context;

import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cz.msebera.android.httpclient.entity.StringEntity;


/**
 * Created by parkerandrews on 2/21/18.
 */

// TODO: A BUNCH OF FREAKING STUFF
public class StelaClient extends AsyncHttpClient /* OAuthBaseClient  */ {

    public AsyncHttpClient client;

//    public static final String REST_URL= "http://127.0.0.1:5000/";
    public static final String REST_URL="http://192.168.0.150:5123/";

    public static final BaseApi REST_API_INSTANCE = null; // Change this
   // public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "";       // Change this
    public static final String REST_CONSUMER_SECRET = ""; // Change this

    // Landing page to indicate the OAuth flow worked in case Chrome for Android 25+ blocks navigation back to the app.
    public static final String FALLBACK_URL = "https://codepath.github.io/android-rest-client-template/success.html";

    // See https://developer.chrome.com/multidevice/android/intents
    public static final String REST_CALLBACK_URL_TEMPLATE = "intent://%s#Intent;action=android.intent.action.VIEW;scheme=%s;package=%s;S.browser_fallback_url=%s;end";


//    public StelaClient(Context c, BaseApi apiInstance, String consumerUrl, String consumerKey, String consumerSecret, String callbackUrl) {
//        super(c, apiInstance, consumerUrl, consumerKey, consumerSecret, callbackUrl);
//
//    }

    /**
     * Constructor that calls the super constructor
     * params: context
     */
//    public StelaClient(Context context) {
//        super(context, REST_API_INSTANCE,
//                REST_URL,
//                REST_URL,
//                REST_URL,
//                REST_URL);
//    }

//    public StelaClient() {
//        return client;
//    }

    public StelaClient(AsyncHttpClient client) {
        this.client = client;
//        client.setTimeout(60000);
    }


    /**
     * Method to send a movement request to the Stela Server
     */
    public StelaClient getClient() {
        return this;
    }

    /**
     * Method to send a movement request to the Stela Server
     */
    public void sendMovement(Double[] coords, Context context, AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "move";
        System.out.println("COORDS: " + Arrays.toString(coords));

//        RequestParams params = new RequestParams();
//        params.put("increment", coords);
//        System.out.println(apiUrl);
//        client.post(apiUrl, params, handler);

        JSONObject jsonParams = new JSONObject();
//        try {
//            JSONArray jsonArray = new JSONArray(coords);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        try {
            jsonParams.put("increment", Arrays.toString(coords));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;

        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("STRING ENTITY: " + entity.toString());

        client.post(context, apiUrl, entity, "application/json", handler);
    }

    /**
     * Method to send a movement request to the Stela Server
     */
    public void getCoordinates(AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "coordinates";

        RequestParams params = new RequestParams();
//        params.put("pandrews", coords);
        System.out.println(apiUrl);
        client.get(apiUrl, params, handler);
    }

    /**
     * Method to send first Configuration Point
     */
    public void setCalib(AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "set_calib";
        client.post(apiUrl, handler);
    }

    /**
     * Method to begin Calibration Setup
     */
    public void setup(String time, Context context, AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "setup";

//        RequestParams params = new RequestParams();
//        params.put("time", time);
//
//        System.out.println(time);
//
//        client.post(apiUrl, params, handler);

        client.setTimeout(60000);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;

        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(context, apiUrl, entity, "application/json", handler);
    }

    /**
     * Method to send first Configuration Point
     */
    public void finishCalib(AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "calibrate";
        client.setTimeout(120000);
        client.post(apiUrl, handler);
    }

    public void getPosition(AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "get_pos";
        client.get(apiUrl, null, handler);
    }

}
