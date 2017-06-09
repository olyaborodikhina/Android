package com.example.exam;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Ольга on 20.05.2017.
 */

public class ShapeListActivity extends AppCompatActivity implements View.OnClickListener {

    public static String LIST_SUFFIX = "_list";
    public static String AUTH_NAME = "AUTH_NAME";

    private RecyclerView shapeList;
    private SharedPreferences prefs;
    private String usernameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_list);
        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        Intent intent = getIntent();
        usernameStr = intent.getStringExtra("usernameStr");

        if (getIntent().getExtras() != null) {
            Shape shape = (Shape) getIntent()
                    .getSerializableExtra(AUTH_NAME);
            saveShape(shape);//Не нужно ли передавать userName
            initViews();
        }
    }

    public void saveShape(Shape shape) {
        ArrayList<Shape> shapes = getShapes();
        shapes.add(shape);
        String name = prefs.getString(AUTH_NAME, usernameStr);
        String result = "";
        for (int i = 0; i < shapes.size(); i++) {
            result = result + shapes.get(i).toString();
            if (i < shapes.size() - 1) {
                result = result + "__-__";
            }
        }
        prefs.edit().putString(name+LIST_SUFFIX, result).apply();

    }

    public ArrayList<Shape> getShapes() {
        String title;
        String type;
        String color;
        String description;

        ArrayList<Shape> list = new ArrayList<>();
        String[] source =
                prefs.getString(AUTH_NAME, "").split("__-__");

//        for (int i = 0; i < source.length; i++) {
           // if (!source[i].isEmpty()) {
                type = source[0];
                color = source[1];
                title = source[2];
                description = source[3];

                list.add(new Shape(type,color,title,description));

            //}
        // }

        return list;

    }

    private void initViews() {
        shapeList = (RecyclerView) findViewById(R.id.shapes_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        ShapeListAdapter adapter = new ShapeListAdapter(getShapes());
        shapeList.setLayoutManager(layoutManager);
        shapeList.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.m_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOut://добавить проверку-  Если все поля заполнены
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            case R.id.Plus:
                Intent intentAdd = new Intent(this, AddActivity.class);
                startActivity(intentAdd);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
