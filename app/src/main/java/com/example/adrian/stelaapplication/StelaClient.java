package com.example.adrian.stelaapplication;


import com.github.scribejava.core.builder.api.BaseApi;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by parkerandrews on 2/21/18.
 */

// TODO: A BUNCH OF FREAKING STUFF
public class StelaClient extends AsyncHttpClient /* OAuthBaseClient  */ {

    public AsyncHttpClient client;

//    public static final String REST_URL= "http://127.0.0.1:5000/";
    public static final String REST_URL="192.168.0.102:5123/";

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
    }

    /**
     * Method to send a movement request to the Stela Server
     */
    public void sendMovement(double[] coords, AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "coordinates";

        RequestParams params = new RequestParams();
        params.put("pandrews", coords);
        System.out.println(apiUrl);
        client.post(apiUrl, params, handler);

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
        String apiUrl = REST_URL + "set_";
        client.post(apiUrl, handler);
    }

    /**
     * Method to begin Calibration Setup
     */
    public void setup(String time, AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "setup";

        RequestParams params = new RequestParams();
        params.put("time", time);
        System.out.println(time);

        client.post(apiUrl, params, handler);
    }

    /**
     * Method to send first Configuration Point
     */
    public void finishCalib(AsyncHttpResponseHandler handler) {
        String apiUrl = REST_URL + "calibrate";
        client.post(apiUrl, handler);
    }

}
