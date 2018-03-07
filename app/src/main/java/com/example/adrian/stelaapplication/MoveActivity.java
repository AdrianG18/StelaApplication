package com.example.adrian.stelaapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.AsyncHttpClient;


import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


// TODO: get the StelaClient to work right and then initialize this client
// TODO: make sure the client.sendMovement line works with the JSON stuff
// TODO: make the constellation and movement models and fromJSON methods

public class MoveActivity extends AppCompatActivity {


    AsyncHttpClient tempClient = new AsyncHttpClient();
    StelaClient client;
//    Context context = getApplicationContext();

    @BindView(R.id.xText) EditText xText;
    @BindView(R.id.yText) EditText yText;
    @BindView(R.id.move_button) Button moveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        // Bind the layout items with ButterKnife
        ButterKnife.bind(this);

        client = new StelaClient(tempClient);
    }

//    public boolean verifyMove(View v) {
//        boolean verify;
//        String s1 = xText.getText().toString();
//        String s2 = yText.getText().toString();
//        if ()
//
//    }

  public void errorPopup() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
        builder1.setMessage("Enter Valid Numbers");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }


    public void onMoooooove(View v) {
        // get the coordinates from the number values they input
        String s1 = xText.getText().toString();
        String s2 = yText.getText().toString();

        if (s1.isEmpty()) {
            // print some error message popup thing
            Log.d("Null Pointer", "X-Coordinate must have a value");
            errorPopup();
            return;
        }
        if (s2.isEmpty()) {
            // print some error message popup thing
            Log.d("Null Pointer", "Y-Coordinate must have a value");
            return;
        }

        Double x = Double.parseDouble(s1);
        Double y = Double.parseDouble(s2);

        if (!(x instanceof Double) || !(y instanceof Double)) {
            Log.d("Type Error", "Not a Double");
            return;
        }

        double[] coords = new double[]{x, y};

        System.out.println("X: " + coords[0]);
        System.out.println("Y: " + coords[1]);

        if (client == null) {
            System.out.println("THE CLIENT IS NULL");
        }

        client.sendMovement(coords, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                // Go back to the Main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                // set the transition
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("MoveFailure", responseString);
                throwable.printStackTrace();
            }
        });

    }

    public void onMove(View v) {
        client.getCoordinates(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("GROOVE SUCCESS!!!!!!!!!", "oh yeah");
                System.out.println("IT FREAKING WORKED");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("GrooveFailure", responseString);
                throwable.printStackTrace();
            }
        });
    }
}

