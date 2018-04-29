package com.example.adrian.stelaapplication;

import android.content.Context;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

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
    //public JSONArray coords;
    public JSONArray coords1;
    public JSONArray coords2;
    public JSONArray coords3;

    double c11;
    double c12;

    double c21;
    double c22;

    double c31;
    double c32;

    double current1;
    double current2;

    JsonHttpResponseHandler handler1 = new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            String responseString = null;
            try {
                responseString = response.getString("response");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(responseString);
            getPosition();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }
    };


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
        String pattern = "yyyy-MM-dd-HH-mm-ss-SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        myTime = simpleDateFormat.format(Calendar.getInstance().getTime());

//        JSONObject timeJSON = new JSONObject();
//        try {
//            timeJSON.put("time", myTime);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        //  System.out.println("TIME!!!!!!!!!!!!!! " + myTime);
        Context context = getApplicationContext();
//        client.setup(myTime, context, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    coords1 = response.getJSONArray("coor1");
//                    coords2 = response.getJSONArray("coor2");
//                    coords3 = response.getJSONArray("coor3");
//
//                    c11 = coords1.getDouble(0);
//                    c12 = coords1.getDouble(1);
//
//                    c21 = coords2.getDouble(0);
//                    c22 = coords2.getDouble(1);
//
//                    c31 = coords3.getDouble(0);
//                    c32 = coords3.getDouble(1);
//
//                    System.out.println("c11: " + c11);
//                    System.out.println("c12: " + c12);
//                    System.out.println("c21: " + c21);
//                    System.out.println("c22: " + c22);
//                    System.out.println("c21: " + c31);
//                    System.out.println("c32: " + c32);
//
//
////                    System.out.println(coords);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.d("setup", "Failed setup time");
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//        });
        // set the Button up
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
        Double[] coords = new Double[2];
        coords[0] = 0.0;
        coords[1] = 5.0;
        System.out.println("DOUBLE COORDS: " + Arrays.toString(coords));
        client.sendMovement(coords, getApplicationContext(), handler1); /*new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object");
                System.out.println(response.toString());
                String responseString = null;
                try {
                    responseString = response.getString("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(responseString);
                getPosition();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        }); */
    }

    public void left(View view) {
        // move left
        // move up
        Double [] coords = {-5.0,0.0};
        System.out.println("DOUBLE COORDS: " + Arrays.toString(coords));
        client.sendMovement(coords, getApplicationContext(), handler1); /* new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object");
                System.out.println(response.toString());
                String responseString = null;
                try {
                    responseString = response.getString("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(responseString);
                getPosition();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        }); */
    }

    public void right(View view) {
        // move right
        // move up
        Double [] coords = {5.0,0.0};
        System.out.println("DOUBLE COORDS: " + Arrays.toString(coords));
        client.sendMovement(coords, getApplicationContext(), handler1); /*new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object");
                System.out.println(response.toString());
                String responseString = null;
                try {
                    responseString = response.getString("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(responseString);
                getPosition();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        }); */
    }

    public void down(View view) {
        // move down
        // move up
        Double [] coords = {0.0,-5.0};
        System.out.println("DOUBLE COORDS: " + Arrays.toString(coords));
        client.sendMovement(coords, getApplicationContext(), handler1); /*new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Object");
                System.out.println(response.toString());
                String responseString = null;
                try {
                    responseString = response.getString("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(responseString);
                getPosition();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        }); */
    }

//    @Override
//    public void onBackPressed() {
//        if (complete) {
//            super.onBackPressed();
//        }
//    }


    public void getPosition() {
        client.getPosition(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray position = response.getJSONArray("pos");
                    current1 = position.getDouble(0);
                    current2 = position.getDouble(1);
                    System.out.println("Current1: " + current1);
                    System.out.println("Current2: " + current2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
