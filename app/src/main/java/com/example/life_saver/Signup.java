package com.example.life_saver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton btn;
    private Button reg;
    private EditText suphone,supass,email,suparea,supbg,supdate;
    private FirebaseAuth mauth;
    DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Life_Saver);
        setContentView(R.layout.sign_up);
        mauth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User Profile");
        btn = findViewById(R.id.back2);
        reg = findViewById(R.id.sign_up_regbtn);
        suphone = findViewById(R.id.sign_up_phone);
        supass = findViewById(R.id.sign_up_pass);
        email = findViewById(R.id.sign_up_email);
        suparea = findViewById(R.id.sign_up_area);
        supbg = findViewById(R.id.sign_up_bg);
        supdate = findViewById(R.id.sign_up_dob);
        btn.setOnClickListener(this);
        reg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.back2)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.sign_up_regbtn)
        {
            //userRegister();
            saveData();
        }
    }

    private void saveData() {
        String savemail = email.getText().toString().trim();
        String savephone = suphone.getText().toString().trim();
        String savepass = supass.getText().toString().trim();
        String savearea = suparea.getText().toString().trim();
        String savebg = supbg.getText().toString().trim();
        String savedate = supdate.getText().toString().trim();

        String key = databaseReference.push().getKey();
        UserProfile user = new UserProfile(savemail,savephone,savepass,savebg,savearea,savedate);
        databaseReference.child(key).setValue(user);
        Toast.makeText(getApplicationContext(),"User Information added",Toast.LENGTH_SHORT).show();
    }

    /*private void userRegister() {
        String mail = email.getText().toString().trim();
        String pass = supass.getText().toString().trim();
        if(mail.isEmpty())
        {
            email.setError("Enter a Valid Email Address");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            email.setError("Enter a Valid Email Address");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty() || pass.length()<6){
            supass.setError("Enter a Valid Password");
            supass.requestFocus();
            return;
        }
        mauth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Register is Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Email Already Registered", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }*/
}
