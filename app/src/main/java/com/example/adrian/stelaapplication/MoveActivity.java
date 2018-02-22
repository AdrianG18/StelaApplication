package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;


// TODO: get the StelaClient to work right and then initialize this client
// TODO: make sure the client.sendMovement line works with the JSON stuff
// TODO: make the constellation and movement models and fromJSON methods

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

//         client = new StelaClient(getApplicationContext());
    }

    public void onMove(View v) {
        // get the coordinates from the number values they input
        Double x = Double.parseDouble(xText.getText().toString());
        Double y = Double.parseDouble(yText.getText().toString());
        if (!(x instanceof Double) || !(y instanceof Double)) {
            return;
        }

        double[] coords = new double[]{x, y};

        System.out.println("X: " + coords[0]);
        System.out.println("Y: " + coords[1]);


//        client.sendMovement(coords, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                // Go back to the Main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                // set the transition
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }


//        });


    }
}
