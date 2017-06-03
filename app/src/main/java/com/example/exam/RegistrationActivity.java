package com.example.exam;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ольга on 25.05.2017.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signUpReg;
    private EditText usernameReg,passwordReg,repeatPasswordReg;
    private SharedPreferences prefs;
    public static String AUTH_NAME = "AUTH_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        signUpReg = (Button) findViewById(R.id.signUpReg);
        signUpReg.setOnClickListener(this);

        usernameReg = (EditText) findViewById(R.id.usernameReg);

        passwordReg = (EditText) findViewById(R.id.passwordReg);

        repeatPasswordReg = (EditText) findViewById(R.id.repeat_password);

        prefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signUpReg: {
                String usernameStr = usernameReg.getText().toString().trim();
                String passwordStr = passwordReg.getText().toString().trim();
                String repeatPasswordStr = repeatPasswordReg.getText().toString().trim();

                //1)проверка заполненности полей
                if ((usernameStr.length() == 0) || (passwordStr.length() == 0) || (repeatPasswordStr.length() == 0)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else
                    //проверка на совпадение паролей
                    if (!passwordStr.equals(repeatPasswordStr)) {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Passwords should match", Toast.LENGTH_SHORT);
                        toast1.show();

                    }
                    else
                    //проверка 3 - Если пользователь с именем введённым в поле Username уже зарегистрирован в системе вывести сообщение “User already exists”
                   if(prefs.contains(usernameStr)){
                       Toast toast1 = Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT);
                       toast1.show();
                   }


                else{//Если ни одно из условий пунктов 1-3 не выполняется, то необходимо перевести пользователя на экран “Shapes list”, очистив back stack.prefs.edit().putString(name, password).apply();
                       prefs.edit().putString(usernameStr, passwordStr).apply();
                       prefs.edit().putString(AUTH_NAME, usernameStr).apply();
                    Intent intent = new Intent(this, NewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
            break;
        }
    }
}
