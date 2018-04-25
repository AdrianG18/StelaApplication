package com.example.adrian.stelaapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoveContActivity extends AppCompatActivity {

    // Set the client
    AsyncHttpClient tempClient = new AsyncHttpClient();
    StelaClient client;

    Double x;
    Double y;

    String xString;
    String yString;

    @BindView(R.id.xCoordinate) TextView xCoord;
    @BindView(R.id.yCoordinate) TextView yCoord;

    @BindView(R.id.bup) Button bup;
    @BindView(R.id.bdown) Button bdown;
    @BindView(R.id.bleft) Button bleft;
    @BindView(R.id.bright) Button bright;
    @BindView(R.id.bul) Button bul;
    @BindView(R.id.bur) Button bur;
    @BindView(R.id.bdl) Button bdl;
    @BindView(R.id.bdr) Button bdr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_cont);

        ButterKnife.bind(this);

        client = new StelaClient(tempClient);

        getCoordinates();
    }

    /**
     * Method to make server request to get position of the telescope
     * Reads the data into the xCoord and yCoord TextViews
     */

    public void getCoordinates() {
        client.getPosition(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray position;
                try {
                    position = response.getJSONArray("pos");
                    x = position.getDouble(0);
                    y = position.getDouble(1);


                    xCoord.setText(x.toString());
                    yCoord.setText(y.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                    xCoord.setText("No Position Array");
                    yCoord.setText("No Position Array");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                xCoord.setText("getPosition Failed");
                yCoord.setText("getPosition Failed");
            }
        });
    }

    /**
     * Method to update position through calculation
     * Called after each increment request to the server
     * Not sure if we are going to use this or not yet
     */
    public void updateCoordinates() {
        // do something
//        x += // whatever constant value we chose to increment x by
//        y += // whatever constant value we chose to increment y by
    }

    /**
     *
     * @param view
     * Method to move when up button is pressed
     */
    public void onBup(View view) {

    }

    public void onBdown(View view) {

    }

    public void onBleft(View view) {

    }

    public void onBright(View view) {

    }

    public void onBul(View view) {

    }

    public void onBur(View view) {

    }

    public void onBdl(View view) {

    }

    public void onBdr(View view) {

    }
}
