package com.example.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.exam.MainActivity.AUTH_NAME;
import static com.example.exam.R.id.AddTitle;
import static com.example.exam.ShapeListActivity.LIST_SUFFIX;

/**
 * Created by Ольга on 03.06.2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private AppCompatSpinner spinnerType,spinnerColor;

    private SharedPreferences prefs;

    private EditText titleText,descriptionText;
    private TextView chooseColor,addDescription;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        spinnerType = (AppCompatSpinner) findViewById(R.id.Rectangel_spinner);
        String[] data = new String[]{"Rectangle", "Circle", "Line"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, data);
        spinnerType.setAdapter(adapter);
        spinnerType.setSelection(0);

        spinnerColor = (AppCompatSpinner) findViewById(R.id.color_spinner);
        String[] dataColor = new String[]{"Red", "Green", "Blue"};
        ArrayAdapter<String> adapterColor = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, dataColor);
        spinnerColor.setAdapter(adapterColor);
        spinnerColor.setSelection(0);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);

        titleText = (EditText) findViewById(R.id.Title_text);
        descriptionText = (EditText) findViewById(R.id.Description_text);
        chooseColor = (TextView) findViewById(R.id.Choose_color);
        addDescription = (TextView) findViewById(R.id.add_Description);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                String titleTextStr = titleText.getText().toString();
                String descriptionStr = descriptionText.getText().toString();
                String  chooseColorStr = chooseColor.getText().toString();
                String addDescriptionStr = addDescription.getText().toString();
                String color = spinnerColor.getSelectedItem().toString();
                String type = spinnerType.getSelectedItem().toString();



                //Если хотя бы одно из полей не заполнено
                if((titleTextStr.length() == 0) || (descriptionStr.length() == 0) ||
                        (chooseColorStr.length() == 0) ||  (addDescriptionStr.length() == 0)){

                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else {//добавление фигуры
                    startShapeScreen(titleTextStr,descriptionStr,color,type);
                }
                break;

        }
    }

    public void startShapeScreen(String title, String description, String color, String type){
        saveShape(new Shape(type, color, title, description));
        finish();
/*        Intent intent = new Intent(this, ShapeListActivity.class);
        intent.putExtra("AUTH_NAME", shape);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }

    public void saveShape(Shape shape) {
        ArrayList<Shape> shapes = getShapes();
        shapes.add(shape);
        String name = prefs.getString(AUTH_NAME, "");
        String result = "";
        for (int i = 0; i < shapes.size(); i++) {
            result = result + shapes.get(i).convertToString();
            if (i < shapes.size() - 1) {
                result = result + "__-__";
            }
        }
        prefs.edit().putString(name+LIST_SUFFIX, result).apply();

    }

    public ArrayList<Shape> getShapes() {
        String title = "";
        String type = "";
        String color = "";
        String description = "";

        ArrayList<Shape> list = new ArrayList<>();
        String name = prefs.getString(AUTH_NAME, "");
        String[] source =
                prefs.getString(name+LIST_SUFFIX, "").split("__-__");

        for(int i = 0; i < source.length;i++){
            if (!source[i].isEmpty()) {
                list.add(new Shape(source[i]));
            }
        }
        return list;

    }
}
