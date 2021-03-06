package com.example.exam;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Ольга on 08.06.2017.
 */

public class Shape implements Serializable {
    private String description;
    private String color;
    private String type;
    private String title;

    public void setDescription(String description) {
        this.description = description;
    }

    public Shape(String type, String color, String title, String description){
        this.title = title;
        this.color = color;

        this.type = type;
        this.description = description;
    }



    public String getDescription(){
        return description;
    }

    public String getColor(){
        return color;
    }

    public String getType(){
        return  type;
    }

    public String getTitle(){
        return title;
    }

    public String convertToString() {

        JSONObject json = new JSONObject();
        try {
            json.put("type", type);
            json.put("color", color);
            json.put("description",description );
            json.put("title",title );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public Shape(String json) {
        JSONObject object = null;
        try {
            object = new JSONObject(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (object != null) {

            this.color = object.optString("color");
            this.title = object.optString("title");
            this.description = object.optString("description");
            this.type = object.optString("type");

        }

    }
}
