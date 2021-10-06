package com.example.life_saver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   Button sin,sup,sad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Life_Saver);
        setContentView(R.layout.activity_main);
        sin = findViewById(R.id.sign_in);
        sad = findViewById(R.id.sign_in_admin);
        sup = findViewById(R.id.sign_up);
        sin.setOnClickListener(this);
        sup.setOnClickListener(this);
        sad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sign_in) {
            Intent intent = new Intent(this, LoginWindow.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.sign_up)
        {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.sign_in_admin)
        {
            Intent intent = new Intent(this, AdminLogin.class);
            startActivity(intent);
        }
    }

}