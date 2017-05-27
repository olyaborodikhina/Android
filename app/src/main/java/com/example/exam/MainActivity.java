package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login;
    private EditText username,password;
    private TextView registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.signUp);
        login.setOnClickListener(this);

        username = (EditText) findViewById(R.id.username);

        password = (EditText) findViewById(R.id.password);

        registration = (TextView) findViewById(R.id.Registration);
        registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signUp:
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();
                if ((usernameStr.length() == 0) || (passwordStr.length() == 0)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please, enter name and password!", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Intent intentToStartActivity = new Intent(this, NewActivity.class);
                    startActivity(intentToStartActivity);
                }
                break;

            case R.id.Registration:
                Intent intentRegistrationActivity = new Intent(this, RegistrationActivity.class);
                startActivity(intentRegistrationActivity);
                break;
        }
    }


}
