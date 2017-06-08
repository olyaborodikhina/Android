package com.example.exam;

/**
 * Created by Ольга on 08.06.2017.
 */

public class Shape {
    private String description;
    private String color;
    private String type;
    private String title;

    public Shape(String title, String color, String type, String description){
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
