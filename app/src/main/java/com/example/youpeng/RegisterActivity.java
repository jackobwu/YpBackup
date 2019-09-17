package com.example.youpeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.jar.JarEntry;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText username;
    private EditText password;
    private EditText password_confirm;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirm = findViewById(R.id.password_confirm);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                register();
            }
        });
    }

    private void register () {

        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String passwrod_confirm = this.password_confirm.getText().toString().trim();

        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("email", email);
        json.addProperty("password", password);
        json.addProperty("password_confirm", passwrod_confirm);

        Ion.with(this)
                .load("http://10.0.0.9/register")
                .setJsonObjectBody(json)
                .asString()
                .setCallback(new FutureCallback<String >() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (result.equals("success")) {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            loginIntent();
                        }
                    }
                });

    }

    private void loginIntent () {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}
