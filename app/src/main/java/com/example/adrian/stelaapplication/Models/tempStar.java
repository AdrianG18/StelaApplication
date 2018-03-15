package com.example.adrian.stelaapplication.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by parkerandrews on 3/7/18.
 */

public class tempStar {
    public double x1, y1, x2, y2;



    public static tempStar fromJSON(JSONObject object) throws JSONException {
        tempStar star = new tempStar();

        JSONArray t1 = object.getJSONArray("azimuth");
        JSONArray t2 = object.getJSONArray("equitorial");

        star.x1 = 1;
        star.y1 = 2;
        star.x2 = 3;
        star.y2 = 4;

        star.x1 = t1.getDouble(0);
        star.y1 = t1.getDouble(1);

        star.x2 = t2.getDouble(0);
        star.y2 = t2.getDouble(1);




        return star;
    }

    public void printtoString() {
//        return super.toString();
    System.out.println("x1: " + x1);
    System.out.println("y1: " + y1);
    System.out.println("x2: " + x2);
    System.out.println("y2: " + y2);

    }
}
