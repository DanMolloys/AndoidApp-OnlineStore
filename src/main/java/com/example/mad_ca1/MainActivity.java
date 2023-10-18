package com.example.mad_ca1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.ElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText email, password, confirmPassword;

    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        button = findViewById(R.id.butConfirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser() {
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        String sConfirmPassword = confirmPassword.getText().toString();

        boolean valid = true;
        boolean digitFound = false;

        for (int i = 0; i < sPassword.length(); i++) {
            char character = sPassword.charAt(i);
            if (Character.isDigit(character)) {
                digitFound = true;
                break;
            }
        }


        if (!sEmail.contains("@") || sEmail.contains(" ")) {
            Toast.makeText(this, "Invalid Email address", Toast.LENGTH_SHORT).show();
        }
        else if(sEmail.isEmpty()){
            Toast.makeText(this, "Must input email", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if (sPassword.length() < 6) {
            Toast.makeText(this, "Invalid password. It must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if (sPassword.contains(" ")) {
            Toast.makeText(this, "Invalid password. It must have no spaces.", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if (digitFound == false) {
            Toast.makeText(this, "Invalid password. It must contain at least one digit", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if(sPassword.isEmpty()) {
            Toast.makeText(this, "Must input password", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if (!sConfirmPassword.equals(sPassword)) {
            Toast.makeText(this, "Must match password inputted", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if(sConfirmPassword.isEmpty()) {
            Toast.makeText(this, "Must input password", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        if (valid == true) {
            Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
            intent.putExtra("EMAIL", sEmail);
            startActivity(intent);
        } else if (valid == false) {
            email.setText("");
            password.setText("");
            confirmPassword.setText("");
        }



    }




}