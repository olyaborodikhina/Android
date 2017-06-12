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

import static com.example.exam.R.id.AddTitle;


/**
 * Created by Ольга on 20.05.2017.
 */

public class ShapeListActivity extends AppCompatActivity implements View.OnClickListener {

    public static String LIST_SUFFIX = "_list";
    public static String AUTH_NAME = "AUTH_NAME";

    private RecyclerView shapeList;
    private SharedPreferences prefs;
    private TextView AddTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_list);
        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

       AddTitle = (TextView) findViewById(R.id.AddTitle);
        if (prefs.getString(AUTH_NAME, "").isEmpty()) {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

        //initViews();
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
                AddTitle.setVisibility(View.GONE);
            }
        }
        return list;

    }

    private void initViews() {
        shapeList = (RecyclerView) findViewById(R.id.shapes_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        ArrayList<Shape> shapes = getShapes();
        ShapeListAdapter adapter = new ShapeListAdapter(shapes);
        shapeList.setLayoutManager(layoutManager);
        shapeList.setAdapter(adapter);

        if (shapes.size() > 0) {
            AddTitle.setVisibility(View.GONE);
        }
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
                prefs.edit().putString(AUTH_NAME, "").apply();
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


    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }
}
