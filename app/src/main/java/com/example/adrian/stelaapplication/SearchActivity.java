package com.example.adrian.stelaapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.adrian.stelaapplication.Models.tempStar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.ib_search) ImageButton ibSearch;
    @BindView(R.id.et_search) EditText etSearch;

    StelaClient client= new StelaClient(new AsyncHttpClient());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
    }

    public void onSearch(View v) {
        // implement the onSearch method
        String star = etSearch.getText().toString();

        client.search(star, getApplicationContext(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                try {
                    tempStar tstar = tempStar.fromJSON(response);
                    tstar.printtoString();

//                    // Start the Details Activity
//                    Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
//                    i.putExtra(tempStar.class.getName(), Parcels.wrap(tstar));
//                    startActivity(i);
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
