package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by parkerandrews on 2/15/18.
 */

public class ConfigureActivity extends AppCompatActivity {

    public boolean clicked = false;
    public String myTime;
    public JSONArray coords;

    @BindView(R.id.button_configure) Button buttonConfigure;
    @BindView(R.id.linear_layout) LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // Bind the layout items with ButterKnife
        ButterKnife.bind(this);

        // set the Button up
        setButton();

        // get Time
        String pattern = "yyyy-MM-dd-HH-mm-ss-SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        myTime = simpleDateFormat.format(Calendar.getInstance().getTime());
        client.setup(myTime, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    coords = response.getJSONArray("calib_coors");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setButton() {
        buttonConfigure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // call configure
                configure();

            }
        });
    }

    public void configure() {
        // configure the Telescope
        if (!clicked) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            buttonConfigure.setText("Back");
            clicked = true;
        }
        else {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
            // Go back to the Main activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            // set the transition
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);


            clicked = false;
        }
    }
}
