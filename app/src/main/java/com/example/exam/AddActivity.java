package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ольга on 03.06.2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private AppCompatSpinner spinnerRectangel,spinnerColor;

    private EditText titleText,descriptionText;
    private TextView chooseColor,addDescription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        spinnerRectangel = (AppCompatSpinner) findViewById(R.id.Rectangel_spinner);
        String[] data = new String[]{"Rectangle", "Circle", "Line"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, data);
        spinnerRectangel.setAdapter(adapter);
        spinnerRectangel.setSelection(0);

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
                String type = spinnerColor.getSelectedItem().toString();


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
        Shape shape = new Shape(type,color,title,description);
        Intent intent = new Intent(this, ShapeListActivity.class);
        intent.putExtra("AUTH_NAME", shape);
        startActivity(intent);
    }
}
