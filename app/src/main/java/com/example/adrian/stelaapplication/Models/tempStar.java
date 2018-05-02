package com.example.adrian.stelaapplication.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
/**
 * Created by parkerandrews on 3/7/18.
 */

public class tempStar {
    public double x1, y1, x2, y2;
    public double alt, az;

    public String info;
    public String name;
    public double mag;
    public double redShift;
    public String oType;
    public String spType;
    public double plx;
    public double distance;
    public double luminosity;



    public static tempStar fromJSON(JSONObject object) throws JSONException {
        tempStar star = new tempStar();
//
//        JSONArray t1 = object.getJSONArray("azimuth");
//        JSONArray t2 = object.getJSONArray("altitude");
//
//
//
//
//        star.x1 = 1;
//        star.y1 = 2;
//        star.x2 = 3;
//        star.y2 = 4;
//
//        star.x1 = t1.getDouble(0);
//        star.y1 = t1.getDouble(1);
//
//        star.x2 = t2.getDouble(0);
//        star.y2 = t2.getDouble(1);

//        star.alt = 7.7;
//        star.az = 8.8;
//
//        star.alt = object.getDouble("altitude");
//        star.az = object.getDouble("azimuth");

        star.info = object.getString("string");

        JSONArray coords = object.getJSONArray("coordinates");
        star.alt = coords.getDouble(0);
        star.az = coords.getDouble(1);

        star.mag = object.getDouble("mag");
        star.redShift = object.getDouble("redshift");
        star.oType = object.getString("otype");
        star.spType = object.getString("spType");
        star.plx = object.getDouble("plx");
        star.distance = object.getDouble("distance");
        star.luminosity = object.getDouble("luminosity");

        return star;
    }

    public void printtoString() {
//        return super.toString();
//    System.out.println("x1: " + x1);
//    System.out.println("y1: " + y1);
//    System.out.println("x2: " + x2);
//    System.out.println("y2: " + y2);
//        System.out.println("altitude: " + alt);
//        System.out.println("azimuth: " + az);
        System.out.println("Info: " + info);
        System.out.println("Coordinates: [" + alt + ", " + az + "]");
    }
}
