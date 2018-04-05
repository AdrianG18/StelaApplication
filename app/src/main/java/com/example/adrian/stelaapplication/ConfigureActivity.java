package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by parkerandrews on 2/15/18.
 */

public class ConfigureActivity extends AppCompatActivity {

    public boolean clicked = false;
    public int setCount = 0;

    @BindView(R.id.button_point) Button buttonPoint;
    @BindView(R.id.button_up) Button buttonUp;
    @BindView(R.id.button_left) Button buttonLeft;
    @BindView(R.id.button_right) Button buttonRight;
    @BindView(R.id.button_down) Button buttonDown;
    @BindView(R.id.linear_layout) LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        // Bind the layout items with ButterKnife
        ButterKnife.bind(this);

//        // set the Button up
//        setButton();
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


    public void setPoint(View view) {
        // send a POST request to the server to set this as the first point

        if (setCount == 0) {
            // once you implement the server request then there will be a delay
                // so set the button to say loading or something
                // also set the button as unclickable for the time being
                // maybe lighten it up to make it seem unclickable at least

            // server request
            // on success
                setCount++;
                buttonPoint.setText("Set Second Point");
        }
        else if (setCount == 1) {
            // once you implement the server request then there will be a delay
            // so set the button to say loading or something
            // also set the button as unclickable for the time being
            // maybe lighten it up to make it seem unclickable at least

            // server request
            // on success
            setCount++;
            buttonPoint.setText("Set Third Point");
        }

        else if (setCount == 2) {
            // once you implement the server request then there will be a delay
            // so set the button to say loading or something
            // also set the button as unclickable for the time being
            // maybe lighten it up to make it seem unclickable at least

            // server request
            // on success
            setCount++;
            buttonPoint.setText("Finish");
        }
        else if (setCount == 3) {
            setCount = 0;

            // Go back to the Main activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            // set the transition
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public void up(View view) {
        // move up

        //all this is the old code to test the activity

        // configure the Telescope
        if (!clicked) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            buttonUp.setText("Back");
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

    public void left(View view) {
        // move left
    }

    public void right(View view) {
        // move right
    }

    public void down(View view) {
        // move down
    }
}
