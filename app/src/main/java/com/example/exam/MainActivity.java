package com.example.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button login;
    private EditText username,password;
    private TextView registration;
    private SharedPreferences prefs;

    public static final int CHECK_EMPTY_NAME_OR_PASSWORD = 1;
    public static final int CHECK_WRONG_NAME = 2;
    public static int CHECK_SUCCESS = 3;
    public static int CHECK_WRONG_PASSWORD = 4;
    public static String AUTH_NAME = "AUTH_NAME";


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

        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.signUp:
                String usernameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                int is = logIn(usernameStr, passwordStr);

                if (is == CHECK_EMPTY_NAME_OR_PASSWORD) {//если хоть одно из полей ввода не заполнено
                    Toast toast = Toast.makeText(getApplicationContext(), "Please, enter name and password!", Toast.LENGTH_SHORT);
                    toast.show();

                } else if(is==CHECK_WRONG_NAME) {//если пользователь с таким именем отсутсвует в системе
                    Toast toast = Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(is==CHECK_WRONG_PASSWORD) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(is == CHECK_SUCCESS){//передадим имя пользователя ShapeListActivity для SharedList
                    Intent intentToStartActivity = new Intent(this, ShapeListActivity.class);
                    intentToStartActivity.putExtra("username", usernameStr);
                    startActivity(intentToStartActivity);
                }
                break;

            case R.id.Registration:
                Intent intentRegistrationActivity = new Intent(this, RegistrationActivity.class);
                startActivity(intentRegistrationActivity);
                break;
        }
    }

    public int logIn(String name, String password) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password))//если имя или пароль пусты
           return CHECK_EMPTY_NAME_OR_PASSWORD;

        String pwd = prefs.getString(name, null);

        if (TextUtils.isEmpty(pwd)) {//Если пользователь не найден
            return CHECK_WRONG_NAME;


        } else if (password.equals(pwd)) {//если есть пользователь и успешно
            prefs.edit().putString(AUTH_NAME, name).apply();
            return CHECK_SUCCESS;

        } else //неверный пароль
           return CHECK_WRONG_PASSWORD;

    }


}
