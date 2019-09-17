package com.example.youpeng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private TextView mRegister;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.register);
        mLogin = findViewById(R.id.login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void login () {
        final String email = this.mEmail.getText().toString().trim();
        final String password = this.mPassword.getText().toString().trim();

        JsonObject Loginjson = new JsonObject();
        Loginjson.addProperty("email", email);
        Loginjson.addProperty("password", password);

        Ion.with(this)
                .load("http://10.0.0.9/login")
                .setJsonObjectBody(Loginjson)
                .asString()
                .setCallback(new FutureCallback<String >() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (result.equals("logined")) {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
                            main();
                        }
                    }
                });
    }

    private void main () {
        Intent mainPage = new Intent(this, MainActivity.class);
        startActivity(mainPage);
    }

}
