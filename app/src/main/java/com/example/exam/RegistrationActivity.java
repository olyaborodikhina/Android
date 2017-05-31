package com.example.exam;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        signUpReg = (Button) findViewById(R.id.signUpReg);
        signUpReg.setOnClickListener(this);

        usernameReg = (EditText) findViewById(R.id.usernameReg);

        passwordReg = (EditText) findViewById(R.id.passwordReg);

        repeatPasswordReg = (EditText) findViewById(R.id.repeat_password);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signUpReg: {
                String usernameStr = usernameReg.getText().toString().trim();
                String passwordStr = passwordReg.getText().toString().trim();
                String repeatPasswordStr = repeatPasswordReg.getText().toString().trim();

                //проверка заполненности полей
                if ((usernameStr.length() == 0) || (passwordStr.length() == 0) || (repeatPasswordStr.length() == 0)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    //проверка на совпадение паролей
                    if (passwordStr.equals(repeatPasswordStr)) {
                        Intent intentToStartActivity = new Intent(this, NewActivity.class);
                        startActivity(intentToStartActivity);

                    } else {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Passwords should match", Toast.LENGTH_SHORT);
                        toast1.show();

                    }
                }
            }
            break;
        }
    }
}
