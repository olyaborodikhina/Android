package com.example.exam;

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
}
