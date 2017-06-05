package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ольга on 03.06.2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

   private Button add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add://добавить проверку-  Если все поля заполнены
                Intent intent = new Intent(this, NewActivity.class);
                break;
        }
    }
}
