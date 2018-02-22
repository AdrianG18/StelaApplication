package com.example.adrian.stelaapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoveActivity extends AppCompatActivity {

    StelaClient client;

    @BindView(R.id.xText) EditText xText;
    @BindView(R.id.yText) EditText yText;
    @BindView(R.id.move_button) Button moveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        // Bind the layout items with ButterKnife
        ButterKnife.bind(this);
    }

    public void onMove(View v) {
        // get the coordinates from the number values they input
        Double x = 10.0;
        Double y = 11.0;
        if (!(x instanceof Double) || !(y instanceof Double)) {
            return;
        }

        double[] coords = new double[]{x, y};

        client.sendMovement(coords, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }


        });


    }
}
