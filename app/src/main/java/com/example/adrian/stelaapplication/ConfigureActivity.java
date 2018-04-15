package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by parkerandrews on 2/15/18.
 */

public class ConfigureActivity extends AppCompatActivity {
    /* We want to pull a JSON that contains three coordinates
       Display those one by one
     */

    AsyncHttpClient tempClient = new AsyncHttpClient();
    StelaClient client;

    public boolean clicked = false;
    public String myTime;
    public JSONArray coords;



//    public boolean clicked = false;
    public int setCount = 0;
    public boolean complete = false;

    @BindView(R.id.button_point) Button buttonPoint;
    @BindView(R.id.button_up) Button buttonUp;
    @BindView(R.id.button_left) Button buttonLeft;
    @BindView(R.id.button_right) Button buttonRight;
    @BindView(R.id.button_down) Button buttonDown;
    @BindView(R.id.linear_layout) LinearLayout linearLayout;
    @BindView(R.id.tv_Coordinate) TextView tvCoordinate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // Bind the layout items with ButterKnife
        ButterKnife.bind(this);

        client = new StelaClient(tempClient);

        // set the Button up
//        setButton();

        // get Time
//        String pattern = "yyyy-MM-dd-HH-mm-ss-SSS";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        myTime = simpleDateFormat.format(Calendar.getInstance().getTime());
//        client.setup(myTime, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    coords = response.getJSONArray("calib_coors");
//                    System.out.println(coords);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        // set the Button up
//        setButton();
        complete();

        
    }

//    public void setButton() {
//        buttonUp.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // call configure
//                configure();
//
//            }
//        });
//    }

    public void complete() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            complete = b.getBoolean("complete");
        }
    }

    public void setPoint(View view) {
        // send a POST request to the server to set this as the first point

        if (setCount == 0) {
            // once you implement the server request then there will be a delay
                // so set the button to say loading or something
                // also set the button as unclickable for the time being
                // maybe lighten it up to make it seem unclickable at least

            client.setCalib(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // on success
                    setCount++;
                    buttonPoint.setText("Set Second Point");
                    tvCoordinate.setText("Coordinate 2");
                    complete = false;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }

                // server request

            });

        }
        else if (setCount == 1) {
            // once you implement the server request then there will be a delay
            // so set the button to say loading or something
            // also set the button as unclickable for the time being
            // maybe lighten it up to make it seem unclickable at least

            // server request
            client.setCalib(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // on success
                    setCount++;
                    buttonPoint.setText("Set Third Point");
                    tvCoordinate.setText("Coordinate 3");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }

            });

        }

        else if (setCount == 2) {
            // once you implement the server request then there will be a delay
            // so set the button to say loading or something
            // also set the button as unclickable for the time being
            // maybe lighten it up to make it seem unclickable at least

            // server request
            // server request
            client.setCalib(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    // on success
                    setCount++;
                    buttonPoint.setText("Finish");
                    tvCoordinate.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
        else if (setCount == 3) {


            client.finishCalib(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    setCount = 0;
                    complete = true;

                    // make an intent to go back to main Activity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    // put the boolean extra
                    Bundle b = new Bundle();
                    b.putBoolean("configured", true);
                    i.putExtras(b);
                    // Start the Activity
                    startActivity(i);
                    // set the transition
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
    }

    public void up(View view) {
        // move up

        //all this is the old code to test the activity

        // configure the Telescope
//        if (!clicked) {
//            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//            buttonUp.setText("Back");
//            clicked = true;
//        }
//        else {
//            linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
//            // Go back to the Main activity
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            // set the transition
//            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//
//
//            clicked = false;
//        }
    }

    public void left(View view) {
        // move left
    }

    public void right(View view) {
        // move right
    }

    public void down(View view) {
        // move down
    }

    @Override
    public void onBackPressed() {
        if (complete) {
            super.onBackPressed();
        }
    }
}
