package com.example.passwordkeeper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText pwdEditText;
    private Button loginButton;

    private SharedPreferences sp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        pwdEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginBtn);

        sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String storeUsername = sp.getString("username", "");
        String storePassword = sp.getString("password", "");

        if (storeUsername.isEmpty() || storePassword.isEmpty()) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveCredentials();
                }
            });
        } else {
            openLoginPage();
        }
    }

    private void saveCredentials() {
        String username = usernameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", pwd);
        editor.apply();

        openLoginPage();
    }

    private void openLoginPage() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}